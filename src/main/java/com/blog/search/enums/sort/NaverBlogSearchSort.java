package com.blog.search.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaverBlogSearchSort {

    // 정확도순 내림차순
    SIM("sim"),
    // 날짜순 내림차순
    DATE("date");

    private final String value;

    public static NaverBlogSearchSort getSearchSortByValue(String value) {
        for (NaverBlogSearchSort searchSort : NaverBlogSearchSort.values()) {
            if(searchSort.getValue().equals(value)) {
                return searchSort;
            }
        }

        return SIM;
    }
}
