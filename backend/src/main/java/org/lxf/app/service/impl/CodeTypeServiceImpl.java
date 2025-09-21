package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.CodeType;
import org.lxf.app.mapper.CodeTypeMapper;
import org.lxf.app.service.CodeTypeService;
import org.springframework.stereotype.Service;

/**
 * 代码类型API接口Service实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/21 23:06
 */
@Service
public class CodeTypeServiceImpl extends ServiceImpl<CodeTypeMapper, CodeType> implements CodeTypeService {
}
