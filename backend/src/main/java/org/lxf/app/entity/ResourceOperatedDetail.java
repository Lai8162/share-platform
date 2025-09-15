package org.lxf.app.entity;

import com.mybatisflex.annotation.Table;
import lombok.*;
import org.lxf.app.entity.base.BaseAudit;

/**
 * 资源被操作详情
 *
 * @author lxf
 * @version 1.0
 * @since 2025-09-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("resource_operated_detail")
public class ResourceOperatedDetail extends BaseAudit {
    /**
     * 资源被操作类型：1-查看 2-赞 3-收藏 4-分享 5-采纳/使用
     */
    private Integer type;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 用户id
     */
    private Long userId;
}
