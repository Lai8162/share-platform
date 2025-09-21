package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.ResourceOperatedDetail;
import org.lxf.app.mapper.ResourceOperatedDetailMapper;
import org.lxf.app.service.ResourceOperatedDetailService;
import org.springframework.stereotype.Service;

/**
 * 资源被操作详情API接口服务实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/15 23:46
 */
@Service
public class ResourceOperatedDetailServiceImpl extends ServiceImpl<ResourceOperatedDetailMapper, ResourceOperatedDetail> implements ResourceOperatedDetailService {
}
