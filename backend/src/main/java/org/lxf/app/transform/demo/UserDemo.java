package org.lxf.app.transform.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

/**
 * 用户导出模板类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/9 2:14
 */
@Data
@ContentRowHeight(15)
@HeadRowHeight(25)
@ColumnWidth(40)
@HeadStyle(fillForegroundColor = 42, fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
public class UserDemo {
    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("电话号码")
    private String phone;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("工号")
    private String employeeNumber;

    @ExcelProperty("账号")
    private String account;
}
