package org.lxf.app.entity.base;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;
import org.lxf.app.common.annotation.Level;

/**
 * 公共主键ID
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
public class BaseLongId {
    @Level(0)
    @Id(keyType = KeyType.Auto)
    private Long id;
}
