package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.entity.TechnicalFeatureType;
import org.lxf.app.form.base.DeleteForm;
import org.lxf.app.form.base.PageQueryForm;
import org.lxf.app.form.base.UpdateForm;
import org.lxf.app.service.TechnicalFeatureTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特性类型API接口Controller
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:17
 */
@RestController
@RequestMapping("/technical/feature/type")
public class TechnicalFeatureTypeController {
    @Resource
    private TechnicalFeatureTypeService technicalFeatureTypeService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(technicalFeatureTypeService.basePageQuery(pageQueryForm, TechnicalFeatureType.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(technicalFeatureTypeService.baseAdd(object, TechnicalFeatureType.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        technicalFeatureTypeService.baseUpdate(updateForm, TechnicalFeatureType.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(technicalFeatureTypeService.baseDelete(deleteForm, TechnicalFeatureType.class));
    }
}
