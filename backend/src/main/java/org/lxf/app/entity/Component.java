package org.lxf.app.entity;

import com.mybatisflex.annotation.Table;
import lombok.*;
import org.lxf.app.entity.base.BaseAudit;

/**
 *  实体类。
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
@Table("component")
public class Component extends BaseAudit {
    /**
     * 组件名称
     */
    private String name;

    /**
     * 组件类型：1-vue 2-其它（默认1）
     */
    private Integer type;

    /**
     * 组件内容
     */
    private String content;
}
