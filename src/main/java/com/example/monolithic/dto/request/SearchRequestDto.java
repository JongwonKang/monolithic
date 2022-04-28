package com.example.monolithic.dto.request;

import com.example.monolithic.enums.Keyword;
import lombok.Getter;

@Getter
public class SearchRequestDto {
    private Keyword keyword;
    private String keyValue;
}
