package com.example.monolithic.dto.response;

import com.example.monolithic.dto.PageMetadata;
import lombok.Getter;

@Getter
public class ListResponseDTO<T> {
    private T items;

    private PageMetadata pageMetadata;

    public ListResponseDTO(T items, PageMetadata pageMetadata) {
        this.items = items;
        this.pageMetadata = pageMetadata;
    }
}
