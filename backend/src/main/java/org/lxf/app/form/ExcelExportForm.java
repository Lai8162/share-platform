package org.lxf.app.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * excel导出查询表单
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/9 1:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExcelExportForm extends PageQueryForm {
    private String fileName;
}
