package com.blog.search.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KakaoBlogSearchSort {

    // 정확도순
    ACCURACY("accuracy"),
    // 최신순
    RECENCY("recency");

    private final String value;

    public static KakaoBlogSearchSort getSearchSortByValue(String value) {
        for (KakaoBlogSearchSort searchSort : KakaoBlogSearchSort.values()) {
            if(searchSort.getValue().equals(value)) {
                return searchSort;
            }
        }

        return ACCURACY;
    }
}
