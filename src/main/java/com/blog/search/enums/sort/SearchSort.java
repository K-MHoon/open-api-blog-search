package com.blog.search.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchSort {
    ACCURACY("accuracy"), RECENCY("recency");

    private final String value;

    public static SearchSort getSearchSortByValue(String value) {
        for (SearchSort searchSort : SearchSort.values()) {
            if(searchSort.getValue().equals(value)) {
                return searchSort;
            }
        }

        return ACCURACY;
    }
}
