package org.lxf.app.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 公共时间-创建/更新时间
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTime extends BaseLongId {
    @Column(onInsertValue = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;
}
