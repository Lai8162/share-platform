package org.lxf.app.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.excel.EasyExcel;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cursor.Cursor;
import org.lxf.app.common.enumcase.FilterTypeEnum;
import org.lxf.app.common.enumcase.MetricTypeEnum;
import org.lxf.app.common.enumcase.ResponseCodeEnum;
import org.lxf.app.common.exception.BusinessException;
import org.lxf.app.common.response.DeleteResponse;
import org.lxf.app.common.util.FieldNameUtil;
import org.lxf.app.common.util.StringUtil;
import org.lxf.app.form.base.*;
import org.lxf.app.transform.demo.base.BaseDTOCastDemo;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;

import static com.mybatisflex.core.query.QueryMethods.sum;

/**
 * 基础服务封装
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
public interface BaseService<Entity> extends IService<Entity> {
    /**
     * 基础分页查询
     *
     * @param pageQueryForm 查询表单
     * @param clazz         类定义
     * @return Page mybatisFlex分页对象
     */
    default <DTO> Page<?> basePageQuery(PageQueryForm pageQueryForm, Class<DTO> clazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        return basePageQuery(queryWrapper, pageQueryForm, clazz);
    }

    /**
     * 基础分页查询
     *
     * @param pageQueryForm 查询表单
     * @param clazz         类定义
     * @return Page mybatisFlex分页对象
     */
    default <DTO> Page<?> basePageQuery(PageQueryForm pageQueryForm, Class<DTO> clazz, boolean isHandleLevel) {
        QueryWrapper queryWrapper = new QueryWrapper();
        return basePageQuery(queryWrapper, pageQueryForm, clazz, isHandleLevel);
    }

    /**
     * 基础分页查询
     *
     * @param queryWrapper  查询wrapper
     * @param pageQueryForm 查询表单
     * @param clazz         类定义
     * @return Page mybatisFlex分页对象
     */
    default <DTO> Page<?> basePageQuery(QueryWrapper queryWrapper, PageQueryForm pageQueryForm, Class<DTO> clazz) {
        return basePageQuery(queryWrapper, pageQueryForm, clazz, true);
    }

    /**
     * 基础分页查询
     *
     * @param queryWrapper  查询wrapper
     * @param pageQueryForm 查询表单
     * @param clazz         类定义
     * @param isHandleLevel 是否处理层级字段
     * @return Page mybatisFlex分页对象
     */
    default <DTO> Page<?> basePageQuery(QueryWrapper queryWrapper, PageQueryForm pageQueryForm, Class<DTO> clazz, boolean isHandleLevel) {
        handleFilter(pageQueryForm.getFilters(), queryWrapper, clazz);
        handleOrder(pageQueryForm.getOrderField(), queryWrapper, clazz);
        Page<DTO> page = this.getMapper().paginateWithRelationsAs(pageQueryForm.getCurrentPage(), pageQueryForm.getPageSize(), queryWrapper, clazz);
        Page<Map<String, Object>> result = new Page<>();
        BeanUtil.copyProperties(page, result);
        if (isHandleLevel) {
            result.setRecords(handleLevel(page, pageQueryForm.getLevel()));
        }
        return result;
    }

    /**
     * 基础新增
     *
     * @param object 待新增对象
     * @param clazz  类定义
     * @return 带id的已新增对象
     */
    default Object baseAdd(Object object, Class<Entity> clazz) {
        Object ret;
        if (object instanceof List<?> addList) {
            List<Entity> entitiesToSave = addList.stream().map(data -> BeanUtil.copyProperties(data, clazz)).toList();
            this.saveBatch(entitiesToSave);
            ret = entitiesToSave;
        } else {
            Entity entityToSave = pojoToEntity(object, clazz);
            this.save(entityToSave);
            ret = entityToSave;
        }
        return ret;
    }

    /**
     * 基础更新
     *
     * @param updateForm 更新表单
     * @param clazz      类定义
     */
    default void baseUpdate(UpdateForm updateForm, Class<Entity> clazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        handleFilter(updateForm.getFilters(), queryWrapper, clazz);
        long records = this.count(queryWrapper);
        checkCount(updateForm.getCheckCount(), updateForm.getMaxCount(), (int) records);
        Entity updateEntity = handleUpdateObject(updateForm.getUpdates(), clazz);
        if (updateEntity != null) {
            this.update(updateEntity, queryWrapper);
        }
    }

    /**
     * 基础删除
     *
     * @param deleteForm 删除表单
     * @param clazz      类定义
     * @return 统一删除响应
     */
    default DeleteResponse baseDelete(DeleteForm deleteForm, Class<Entity> clazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        handleFilter(deleteForm.getFilters(), queryWrapper, clazz);
        long records = this.count(queryWrapper);
        checkCount(deleteForm.getCheckCount(), deleteForm.getMaxCount(), (int) records);
        DeleteResponse response = new DeleteResponse();
        response.setRowsCount(this.getMapper().deleteByQuery(queryWrapper));
        return response;
    }

    /**
     * 基础统计
     *
     * @param statisticsForm 统计表单
     * @param clazz          类定义
     * @return 统计返回列表
     */
    default List<?> baseStatistics(StatisticsForm statisticsForm, Class<Entity> clazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        handleFilter(statisticsForm.getFilters(), queryWrapper, clazz);
        handleMetric(statisticsForm.getMetrics(), queryWrapper, clazz);
        handleGroup(statisticsForm.getGroupField(), queryWrapper, clazz);
        handleOrder(statisticsForm.getOrderField(), queryWrapper, clazz);
        return this.listAs(queryWrapper, Map.class);
    }

    /**
     * 基础excel导出
     *
     * @param response        http响应
     * @param excelExportForm 导出查询表单
     * @param fileName        文件名
     * @param dtoClazz        dto类型
     * @param demoClazz       demo类型
     * @param <DTO>           dto泛型定义
     * @param <DEMO>          demo泛型定义
     * @throws IOException io异常
     */
    default <DTO extends BaseDTOCastDemo<DTO, DEMO>, DEMO> void baseExcelExport(HttpServletResponse response, ExcelExportForm excelExportForm, String fileName, Class<DTO> dtoClazz, Class<DEMO> demoClazz) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String tempFileName = URLEncoder.encode("%s.xlsx".formatted(StringUtils.isBlank(excelExportForm.getFileName())
                ? fileName : excelExportForm.getFileName()), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=%s".formatted(tempFileName));

        QueryWrapper queryWrapper = new QueryWrapper();
        handleFilter(excelExportForm.getFilters(), queryWrapper, dtoClazz);
        handleOrder(excelExportForm.getOrderField(), queryWrapper, dtoClazz);
        List<DTO> exportList = this.getMapper().paginateWithRelationsAs(excelExportForm.getCurrentPage(), excelExportForm.getPageSize(), queryWrapper, dtoClazz).getRecords();
        List<DEMO> writeList = exportList.stream().map(data -> data.buildDemo(data)).toList();
        EasyExcel.write(response.getOutputStream(), demoClazz).sheet("详情").doWrite(writeList);
    }

    /**
     * 导出excel上传模板
     *
     * @param response       响应
     * @param filePathName   resources下模板文件名
     * @param fileExportName 要导出的模板文件名
     * @throws IOException 异常
     */
    default void baseTemplateExport(HttpServletResponse response, String filePathName, String fileExportName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");

        ClassPathResource resource = new ClassPathResource("excel/%s".formatted(filePathName));
        String fileName = URLEncoder.encode(StringUtils.isNotBlank(fileExportName) ? fileExportName + ".xlsx" : filePathName, StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment;filename=%s".formatted(fileName));

        try (InputStream is = resource.getStream()) {
            EasyExcel.write(response.getOutputStream())
                    .withTemplate(is)
                    .needHead(false)
                    .sheet()
                    .doWrite(Collections.emptyList());
        }
    }

    /**
     * 基础游标处理
     *
     * @param queryWrapper  查询参数
     * @param batchSize     批数量
     * @param batchConsumer 消费者函数
     */
    @Transactional
    default void baseCursor(QueryWrapper queryWrapper, int batchSize, Consumer<List<Entity>> batchConsumer) {
        List<Entity> buffer = new ArrayList<>();

        try (Cursor<Entity> cursor = this.getMapper().selectCursorByQuery(queryWrapper)) {
            for (Entity entity : cursor) {
                buffer.add(entity);
                if (buffer.size() >= batchSize) {
                    batchConsumer.accept(buffer);
                    buffer.clear();
                }
            }
            // 处理剩余记录
            if (!buffer.isEmpty()) {
                batchConsumer.accept(buffer);
            }
        } catch (Exception e) {
            throw new BusinessException("处理中断", e);
        }
    }

    /**
     * 对象转换
     *
     * @param object 待转换对象
     * @param clazz  转换类定义
     * @return 转换实体类
     */
    private Entity pojoToEntity(Object object, Class<Entity> clazz) {
        return BeanUtil.toBean(object, clazz);
    }

    /**
     * 处理Filters参数
     *
     * @param filters      过滤参数对象
     * @param queryWrapper 查询wrapper
     * @param clazz        类定义
     */
    private void handleFilter(Map<String, Object> filters, QueryWrapper queryWrapper, Class<?> clazz) {
        if (filters == null) {
            return;
        }
        Set<String> fieldSet = FieldNameUtil.getUnderScoredFieldNames(clazz);
        filters.forEach((key, value) -> {
            // 排除key为null或者空，value为null或者空，略过
            if (StringUtils.isBlank(key) || value == null || "".equals(value)) {
                return;
            }
            if (!key.contains("__")) {
                // 如果搜索字段不存在，抛出异常
                checkFieldExist(fieldSet, key);
                queryWrapper.eq(key, value);
            } else {
                String[] conditions = key.split("__");
                // 如果搜索字段不存在，抛出异常
                checkFieldExist(fieldSet, conditions[0]);
                // 如果条件不是规定的条件，抛出异常
                if (!FilterTypeEnum.isValid(conditions[1])) {
                    throw new BusinessException(ResponseCodeEnum.FILTER_CONDITION_NOT_FOUND, key, conditions[1]);
                }
                handleFilterCondition(queryWrapper, key, value);
            }
        });
    }

    /**
     * 处理统计指标
     *
     * @param metrics      统计指标/冗余字段
     * @param queryWrapper 查询wrapper
     * @param clazz        类定义
     */
    private void handleMetric(String metrics, QueryWrapper queryWrapper, Class<?> clazz) {
        if (metrics == null) {
            return;
        }
        Set<String> fieldSet = FieldNameUtil.getUnderScoredFieldNames(clazz);
        String[] metricArray = metrics.split(",");
        for (String metric : metricArray) {
            if (StringUtils.isBlank(metric)) {
                continue;
            }
            // 去除前后空格，并将其转换为小写下划线格式
            String tempMetric = StringUtil.lowerCamelToSnake(metric.trim());
            if (!tempMetric.contains("__")) {
                checkFieldExist(fieldSet, tempMetric);
                queryWrapper.select(tempMetric);
            } else {
                handleMetricCondition(tempMetric, queryWrapper, fieldSet);
            }
        }
    }

    /**
     * 处理分组
     *
     * @param groupField   分组字段
     * @param queryWrapper 查询wrapper
     * @param clazz        类定义
     */
    private void handleGroup(String groupField, QueryWrapper queryWrapper, Class<?> clazz) {
        if (StringUtils.isBlank(groupField)) {
            return;
        }
        Set<String> fieldSet = FieldNameUtil.getUnderScoredFieldNames(clazz);
        String[] groupFieldArray = groupField.split(",");
        for (String field : groupFieldArray) {
            String tempField = StringUtil.lowerCamelToSnake(field.trim());
            checkFieldExist(fieldSet, tempField);
            queryWrapper.groupBy(tempField);
        }
    }

    /**
     * 处理待更新对象
     *
     * @param updates 待更新对象
     * @param clazz   类定义
     * @return 实体类
     */
    private Entity handleUpdateObject(Map<String, Object> updates, Class<Entity> clazz) {
        return updates == null ? null : pojoToEntity(updates, clazz);
    }

    /**
     * 处理排序
     *
     * @param orderField   排序字段
     * @param queryWrapper 查询wrapper
     * @param clazz        类定义
     */
    private void handleOrder(String orderField, QueryWrapper queryWrapper, Class<?> clazz) {
        if (orderField == null) {
            return;
        }
        String[] orderFields = orderField.split(",");
        Set<String> fieldSet = FieldNameUtil.getUnderScoredFieldNames(clazz);
        for (String field : orderFields) {
            if (StringUtils.isBlank(field)) {
                continue;
            }
            String fieldName;
            if (field.contains("-")) {
                fieldName = field.substring(1);
            } else {
                fieldName = field;
            }
            // 如果字段不存在，抛出异常
            checkFieldExist(fieldSet, fieldName);
            queryWrapper.orderBy(fieldName, !field.contains("-"));
        }
    }

    /**
     * 处理查询类型
     *
     * @param queryWrapper 查询wrapper
     * @param key          查询参数的key
     * @param value        查询参数的value
     */
    private void handleFilterCondition(QueryWrapper queryWrapper, String key, Object value) {
        String[] conditions = key.split("__");
        // 特殊处理isNull和isNotNull的情况
        if ((value == null || "".equals(value)) && !Arrays.asList(FilterTypeEnum.IS_NUll.getType(),
                FilterTypeEnum.IS_NOT_NULL.getType()).contains(conditions[1])) {
            return;
        }
        switch (Objects.requireNonNull(FilterTypeEnum.ofType(conditions[1]))) {
            case EQ -> queryWrapper.eq(conditions[0], value);
            case NE -> queryWrapper.ne(conditions[0], value);
            case GE -> queryWrapper.ge(conditions[0], value);
            case GT -> queryWrapper.gt(conditions[0], value);
            case LE -> queryWrapper.le(conditions[0], value);
            case LT -> queryWrapper.lt(conditions[0], value);
            case STARTS_WITH -> queryWrapper.likeLeft(conditions[0], value);
            case ENDS_WITH -> queryWrapper.likeRight(conditions[0], value);
            case IS_NUll -> queryWrapper.isNull(conditions[0]);
            case IS_NOT_NULL -> queryWrapper.isNotNull(conditions[0]);
            case IN -> handleInCondition(queryWrapper, conditions[0], value, key);
            case NOT_IN -> handleNotInCondition(queryWrapper, conditions[0], value, key);
            case BETWEEN -> handleBetweenCondition(queryWrapper, conditions[0], value, key);
            case NOT_BETWEEN -> handleNotBetweenCondition(queryWrapper, conditions[0], value, key);
        }
    }

    /**
     * 处理指标统计类型或者冗余字段查询
     *
     * @param metric       指标字段
     * @param queryWrapper 查询wrapper
     * @param fieldSet     实体类字段集合
     */
    private void handleMetricCondition(String metric, QueryWrapper queryWrapper, Set<String> fieldSet) {
        String[] conditions = metric.split("__");
        checkFieldExist(fieldSet, conditions[0]);
        checkMetricTypeExist(conditions[1]);
        switch (Objects.requireNonNull(MetricTypeEnum.ofType(conditions[1]))) {
            case SUM -> queryWrapper.select(sum(conditions[0]).as("%s(%s)".formatted(conditions[0], conditions[1])));
            case COUNT ->
                    queryWrapper.select(QueryMethods.count(conditions[0]).as("%s(%s)".formatted(conditions[0], conditions[1])));
            case AVG ->
                    queryWrapper.select(QueryMethods.avg(conditions[0]).as("%s(%s)".formatted(conditions[0], conditions[1])));
            case MAX ->
                    queryWrapper.select(QueryMethods.max(conditions[0]).as("%s(%s)".formatted(conditions[0], conditions[1])));
            case MIN ->
                    queryWrapper.select(QueryMethods.min(conditions[0]).as("%s(%s)".formatted(conditions[0], conditions[1])));
        }
    }

    /**
     * 处理层级
     *
     * @param page  分页返回对象
     * @param level 层级
     * @param <DTO> 泛型对象
     * @return 包括需要字段的Map列表
     */
    private <DTO> List<Map<String, Object>> handleLevel(Page<DTO> page, int level) {
        try {
            List<Map<String, Object>> result = new ArrayList<>();
            for (DTO record : page.getRecords()) {
                result.add(FieldNameUtil.convertToMapFiltered(record, level));
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new BusinessException(ResponseCodeEnum.LEVEL_ERROR, e);
        }
    }

    /**
     * 处理in条件
     *
     * @param queryWrapper 查询wrapper
     * @param field        查询字段
     * @param value        查询值
     * @param key          查询参数
     */
    private void handleInCondition(QueryWrapper queryWrapper, String field, Object value, String key) {
        validateListValue(value, key);
        queryWrapper.in(field, (List<?>) value);
    }

    /**
     * 处理notIn条件
     *
     * @param queryWrapper 查询wrapper
     * @param field        查询字段
     * @param value        查询值
     * @param key          查询参数
     */
    private void handleNotInCondition(QueryWrapper queryWrapper, String field, Object value, String key) {
        validateListValue(value, key);
        queryWrapper.notIn(field, (List<?>) value);
    }

    /**
     * 处理between条件
     *
     * @param queryWrapper 查询wrapper
     * @param field        查询字段
     * @param value        查询值
     * @param key          查询参数
     */
    private void handleBetweenCondition(QueryWrapper queryWrapper, String field, Object value, String key) {
        validateListValue(value, key);
        List<?> temp = (List<?>) value;
        validateBoundaryValues(temp, key);
        queryWrapper.between(field, temp.get(0), temp.get(1));
    }

    /**
     * 处理notBetween条件
     *
     * @param queryWrapper 查询wrapper
     * @param field        查询字段
     * @param value        查询值
     * @param key          查询参数
     */
    private void handleNotBetweenCondition(QueryWrapper queryWrapper, String field, Object value, String key) {
        validateListValue(value, key);
        List<?> temp = (List<?>) value;
        validateBoundaryValues(temp, key);
        queryWrapper.notBetween(field, temp.get(0), temp.get(1));
    }

    /**
     * 验证是否是列表
     *
     * @param value 值
     * @param key   查询参数
     */
    private void validateListValue(Object value, String key) {
        if (!(value instanceof List<?>)) {
            throw new BusinessException(ResponseCodeEnum.FILTER_LIST, key);
        }
    }

    /**
     * 验证是否是两个值的列表
     *
     * @param list 参数值
     * @param key  查询参数
     */
    private void validateBoundaryValues(List<?> list, String key) {
        if (list.size() <= 1) {
            throw new BusinessException(ResponseCodeEnum.FILTER_BETWEEN, key);
        }
    }

    /**
     * 检查实体字段是否存在
     *
     * @param fieldSet 实体字段集合
     * @param field    待检查的实体字段
     */
    private void checkFieldExist(Set<String> fieldSet, String field) {
        if (!fieldSet.contains(field)) {
            throw new BusinessException(ResponseCodeEnum.FILTER_NOT_FOUND, field);
        }
    }

    /**
     * 检查集合类型是否存在
     *
     * @param metricType 聚合类型
     */
    private void checkMetricTypeExist(String metricType) {
        if (!MetricTypeEnum.isValid(metricType)) {
            throw new BusinessException(ResponseCodeEnum.STATISTICS_TYPE_NOT_FIND, metricType);
        }
    }

    /**
     * 验证操作记录数量
     *
     * @param checkCount 允许操作记录数
     * @param maxCount   允许操作最大记录数
     * @param records    实际操作记录数
     */
    private void checkCount(Integer checkCount, Integer maxCount, int records) {
        if (checkCount != null && checkCount != records) {
            throw new BusinessException(ResponseCodeEnum.DATA_CHECK, checkCount, records);
        }
        if (maxCount != null && maxCount != records) {
            throw new BusinessException(ResponseCodeEnum.DATA_TO_MANY, maxCount, records);
        }
    }
}
