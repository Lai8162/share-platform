package org.lxf.app.entity;

import com.mybatisflex.annotation.Table;
import lombok.*;
import org.lxf.app.entity.base.BaseTime;

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
@Table("resource_count")
public class ResourceCount extends BaseTime {
    /**
     * 资源id
     */
    private Integer resourceId;

    /**
     * 查看数
     */
    private Long viewCount;

    /**
     * 喜欢数
     */
    private Long likeCount;

    /**
     * 收藏数
     */
    private Long collectCount;

    /**
     * 分享数
     */
    private Long shareCount;

    /**
     * 使用/采纳数
     */
    private Long useCount;
}
