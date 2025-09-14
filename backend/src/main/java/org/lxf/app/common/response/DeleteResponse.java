package org.lxf.app.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一删除响应
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/8 23:07
 */
@Data
public class DeleteResponse {
    private Integer rowsCount;
    private Map<String, Integer> relations;

    public DeleteResponse() {
        this.rowsCount = 0;
        this.relations = new HashMap<>();
    }
}
