package org.lxf.app.transform.demo.base;

/**
 * DTO转换为DEMO接口，所有需要导出的待转换的实体都需要实现此类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/9 1:28
 */
public interface BaseDTOCastDemo<DTO, DEMO> {
   DEMO buildDemo(DTO dto);
}
