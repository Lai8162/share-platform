package org.lxf.app.entity;

import com.mybatisflex.annotation.Table;
import lombok.*;
import org.lxf.app.entity.base.BaseAudit;

/**
 * 文章
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
@Table("article")
public class Article extends BaseAudit {
   /**
     * 文章名称
     */
    private String name;

    /**
     * 1-原创 2-外部分享链接
     */
    private Integer type;

    /**
     * 文章内容
     */
    private String content;
}
