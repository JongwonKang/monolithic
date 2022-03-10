package com.example.monolithic.dto;

import lombok.Data;

@Data
public class PageMetadata {
    private long size;
    private long totalPages;
    private long totalElements;
    private boolean isLast;
}
