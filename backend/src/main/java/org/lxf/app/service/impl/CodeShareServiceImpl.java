package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.CodeShare;
import org.lxf.app.mapper.CodeShareMapper;
import org.lxf.app.service.CodeShareService;
import org.springframework.stereotype.Service;

/**
 * 代码分享API接口Service实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:06
 */
@Service
public class CodeShareServiceImpl extends ServiceImpl<CodeShareMapper, CodeShare> implements CodeShareService {
}
