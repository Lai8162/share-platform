package org.lxf.app.config;

import com.mybatisflex.core.audit.AuditManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MyBatisFlexConfig {
    public MyBatisFlexConfig() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);
        // 设置SQL审计收集器
        AuditManager.setMessageCollector(auditMessage ->
                log.info("{},{}ms", auditMessage.getFullSql(), auditMessage.getElapsedTime())
        );
    }
}
