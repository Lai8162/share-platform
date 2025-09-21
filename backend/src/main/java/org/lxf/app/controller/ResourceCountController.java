package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.entity.ResourceCount;
import org.lxf.app.form.base.DeleteForm;
import org.lxf.app.form.base.PageQueryForm;
import org.lxf.app.form.base.UpdateForm;
import org.lxf.app.service.ResourceCountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资源计数API接口Controller
 *
 * @author lxf
 * @version 1.0
 * @since 2025/9/15 19:31
 */
@RestController
@RequestMapping("/resource/count")
public class ResourceCountController {
    @Resource
    private ResourceCountService resourceCountService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(resourceCountService.basePageQuery(pageQueryForm, ResourceCount.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(resourceCountService.baseAdd(object, ResourceCount.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        resourceCountService.baseUpdate(updateForm, ResourceCount.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(resourceCountService.baseDelete(deleteForm, ResourceCount.class));
    }
}
