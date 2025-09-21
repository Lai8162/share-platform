package org.lxf.app.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.lxf.app.common.response.PageResponse;
import org.lxf.app.common.response.Response;
import org.lxf.app.entity.TechnicalFeatureShare;
import org.lxf.app.form.base.DeleteForm;
import org.lxf.app.form.base.PageQueryForm;
import org.lxf.app.form.base.UpdateForm;
import org.lxf.app.service.TechnicalFeatureShareService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特性分享API接口Controller
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:17
 */
@RestController
@RequestMapping("/technical/feature/share")
public class TechnicalFeatureShareController {
    @Resource
    private TechnicalFeatureShareService technicalFeatureShareService;

    @PostMapping("/query")
    public Response<PageResponse<?>> pageQuery(@RequestBody @Valid PageQueryForm pageQueryForm) {
        return Response.ok(new PageResponse<>(technicalFeatureShareService.basePageQuery(pageQueryForm, TechnicalFeatureShare.class)));
    }

    @PostMapping("/add")
    public Response<Object> add(@RequestBody Object object) {
        return Response.ok(technicalFeatureShareService.baseAdd(object, TechnicalFeatureShare.class));
    }

    @PostMapping("/update")
    public Object update(@RequestBody @Valid UpdateForm updateForm) {
        technicalFeatureShareService.baseUpdate(updateForm, TechnicalFeatureShare.class);
        return Response.ok();
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody @Valid DeleteForm deleteForm) {
        return Response.ok(technicalFeatureShareService.baseDelete(deleteForm, TechnicalFeatureShare.class));
    }
}
