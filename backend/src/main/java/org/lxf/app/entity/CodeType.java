package org.lxf.app.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码类型
 *
 * @author lxf
 * @version 1.0
 * @since 2025-09-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("code_type")
public class CodeType {
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}
