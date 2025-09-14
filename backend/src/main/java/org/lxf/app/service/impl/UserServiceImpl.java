package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.User;
import org.lxf.app.mapper.UserMapper;
import org.lxf.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户API服务实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
