package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.TechnicalFeatureType;
import org.lxf.app.mapper.TechnicalFeatureTypeMapper;
import org.lxf.app.service.TechnicalFeatureTypeService;
import org.springframework.stereotype.Service;

/**
 * 特性类型API接口Service实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:08
 */
@Service
public class TechnicalFeatureTypeServiceImpl extends ServiceImpl<TechnicalFeatureTypeMapper, TechnicalFeatureType>
        implements TechnicalFeatureTypeService {
}
