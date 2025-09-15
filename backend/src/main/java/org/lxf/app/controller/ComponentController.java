package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.entity.Component;
import org.lxf.app.form.*;
import org.lxf.app.service.ComponentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组件API接口控制层
 *
 * @author lxf
 * @version 1.0
 * @since 2025/9/15 19:31
 */
@RestController
@RequestMapping("/component")
public class ComponentController {
    @Resource
    private ComponentService componentService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(componentService.basePageQuery(pageQueryForm, Component.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(componentService.baseAdd(object, Component.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        componentService.baseUpdate(updateForm, Component.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(componentService.baseDelete(deleteForm, Component.class));
    }
}
