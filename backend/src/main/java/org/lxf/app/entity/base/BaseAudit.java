package org.lxf.app.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审计实体-创建人/更新人
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseAudit extends BaseTime {
    private Long creatorId;

    private Long modifierId;
}
