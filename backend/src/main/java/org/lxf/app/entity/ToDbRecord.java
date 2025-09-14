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
@Table("to_db_record")
public class ToDbRecord extends BaseAudit {
    /**
     * 解析名称（如没有设置，自动生成）
     */
    private String name;

    /**
     * 解析生成内容
     */
    private String content;
}
