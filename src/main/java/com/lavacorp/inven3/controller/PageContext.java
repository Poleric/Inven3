package com.lavacorp.inven3.controller;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PageContext {
    int pageSize;
    int currentRow;
    int lastRow;
    int totalRow;
    int currentPage;
    int totalPage;

    public PageContext(int pageSize, int currentRow, int totalRow, int currentPage, int totalPage) {
        this(pageSize, currentRow, Math.max(currentRow + pageSize - 1, totalRow), totalRow, currentPage, totalPage);
    }

    public PageContext(int pageSize, int totalRow, int currentPage) {
        this(pageSize, (currentPage - 1) * pageSize + 1, totalRow, currentPage, totalRow / pageSize + 1);
    }

    public static PageContext makeDefault() {
        return new PageContext(25, 0, 1);
    }
}
