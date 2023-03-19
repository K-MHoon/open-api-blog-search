package com.blog.search.dto.entity.search;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchHistoryDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    public static class PopularKeywordStatistics {

        private String query;
        private Long count;

        @QueryProjection
        public PopularKeywordStatistics(String query, Long count) {
            this.query = query;
            this.count = count;
        }
    }

}
