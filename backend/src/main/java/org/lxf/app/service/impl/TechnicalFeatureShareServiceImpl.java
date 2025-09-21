package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.TechnicalFeatureShare;
import org.lxf.app.mapper.TechnicalFeatureShareMapper;
import org.lxf.app.service.TechnicalFeatureShareService;
import org.springframework.stereotype.Service;

/**
 * 特性分享API接口Service实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:08
 */
@Service
public class TechnicalFeatureShareServiceImpl extends ServiceImpl<TechnicalFeatureShareMapper, TechnicalFeatureShare>
        implements TechnicalFeatureShareService {
}
