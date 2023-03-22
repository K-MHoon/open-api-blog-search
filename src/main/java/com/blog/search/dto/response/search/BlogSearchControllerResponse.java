package com.blog.search.dto.response.search;

import com.blog.search.dto.entity.search.SearchHistoryDto;
import lombok.*;

import java.util.List;

/**
 * 블로그 검색 조회 Controller Response 관련 Inner Class 관리
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSearchControllerResponse {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class GetPopularKeywordResponse {

        private List<SearchHistoryDto.PopularKeywordStatistics> popularKeywordList;
    }
}
