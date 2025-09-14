package org.lxf.app.common.response;

import com.mybatisflex.core.paginate.Page;
import lombok.Data;

import java.util.List;

/**
 * 统一分页返回响应
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
public class PageResponse<T> {
    private long currentPage;
    private long pageSize;
    private long total;
    private List<T> items;

    public PageResponse(long currentPage, long pageSize, long total, List<T> items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.items = items;
    }

    public PageResponse(Page<T> page) {
        this.currentPage = page.getPageNumber();
        this.pageSize = page.getPageSize();
        this.total = page.getTotalRow();
        this.items = page.getRecords();
    }
}
