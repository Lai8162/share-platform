package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.entity.ToDbRecord;
import org.lxf.app.form.DeleteForm;
import org.lxf.app.form.PageQueryForm;
import org.lxf.app.form.UpdateForm;
import org.lxf.app.service.ToDbRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 解析SQL记录API接口控制层
 *
 * @author lxf
 * @version 1.0
 * @since 2025/9/15 19:31
 */
@RestController
@RequestMapping("/to/db/record")
public class ToDbRecordController {
    @Resource
    private ToDbRecordService toDbRecordService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(toDbRecordService.basePageQuery(pageQueryForm, ToDbRecord.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(toDbRecordService.baseAdd(object, ToDbRecord.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        toDbRecordService.baseUpdate(updateForm, ToDbRecord.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(toDbRecordService.baseDelete(deleteForm, ToDbRecord.class));
    }
}
