package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.ResourceCount;
import org.lxf.app.mapper.ResourceCountMapper;
import org.lxf.app.service.BaseService;
import org.lxf.app.service.ResourceCountService;
import org.springframework.stereotype.Service;

/**
 * 资源计数API服务实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/15 23:44
 */
@Service
public class ResourceCountServiceImpl extends ServiceImpl<ResourceCountMapper, ResourceCount> implements ResourceCountService {
}
