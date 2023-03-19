package com.blog.search.dto.response.search;

import com.blog.search.dto.entity.search.SearchHistoryDto;
import lombok.*;

import java.util.List;

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
