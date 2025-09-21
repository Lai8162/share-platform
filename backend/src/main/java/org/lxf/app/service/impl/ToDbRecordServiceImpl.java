package org.lxf.app.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.lxf.app.entity.ToDbRecord;
import org.lxf.app.mapper.ToDbRecordMapper;
import org.lxf.app.service.ToDbRecordService;
import org.springframework.stereotype.Service;

/**
 * 解析sql记录API接口Service实现
 *
 * @author lxf
 * @version 1.0
 * @since 2025/09/15 23:47
 */
@Service
public class ToDbRecordServiceImpl extends ServiceImpl<ToDbRecordMapper, ToDbRecord> implements ToDbRecordService {
}
