package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.form.base.*;
import org.lxf.app.transform.demo.UserDemo;
import org.lxf.app.entity.User;
import org.lxf.app.transform.vo.UserVO;
import org.lxf.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 用户API接口Controller
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(userService.basePageQuery(pageQueryForm, UserVO.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(userService.baseAdd(object, User.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        userService.baseUpdate(updateForm, User.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(userService.baseDelete(deleteForm, User.class));
    }

    @PostMapping("/statistics")
    public Object statistics(@RequestBody @Valid StatisticsForm statisticsForm) {
        return Response.ok(userService.baseStatistics(statisticsForm, User.class));
    }

    @PostMapping("/excel/export")
    public void excelExport(HttpServletResponse response, @RequestBody @Valid ExcelExportForm excelExportForm) throws IOException {
        userService.baseExcelExport(response, excelExportForm, "用户详情数据", User.class, UserDemo.class);
    }

    @PostMapping("/template/export")
    public void templateExport(HttpServletResponse response, @RequestBody @Valid ExcelExportForm excelExportForm) throws IOException {
        userService.baseTemplateExport(response, "UserUploadTemplate.xlsx", excelExportForm.getFileName());
    }
}
