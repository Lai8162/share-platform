package org.lxf.app.service.impl;


import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.Component;
import org.lxf.app.mapper.ComponentMapper;
import org.lxf.app.service.ComponentService;
import org.springframework.stereotype.Service;

/**
 * 组件API服务实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/15 23:44
 */
@Service
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements ComponentService {
}
