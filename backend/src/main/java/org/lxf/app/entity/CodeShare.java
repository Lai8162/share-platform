package org.lxf.app.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码分享
 *
 * @author lxf
 * @version 1.0
 * @since 2025-09-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("code_share")
public class CodeShare {
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型（外键：code_type）
     */
    private Long type;

    /**
     * 描述
     */
    private String description;
}
