package com.blog.search.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchSort {
    ACCURACY("accuracy"), RECENCY("recency");

    private final String value;
}
