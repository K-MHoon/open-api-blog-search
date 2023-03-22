package com.blog.search.enums.sort;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 네이버 블로그 검색에서 정렬 조회 조건
 */
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
