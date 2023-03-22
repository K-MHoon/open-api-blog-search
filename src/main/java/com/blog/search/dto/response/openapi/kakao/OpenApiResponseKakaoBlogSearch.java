package com.blog.search.dto.response.openapi.kakao;

import com.blog.search.dto.Pagination;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 카카오 블로그 검색 API Response
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiResponseKakaoBlogSearch implements OpenApiResponse {

    private Meta meta;
    private List<Document> documents;

    @Getter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private int totalCount;
        private int pageableCount;
        private boolean isEnd;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {
        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbnail;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Seoul")
        private ZonedDateTime datetime;
    }

    @Override
    public Pagination getPagination() {
        Pagination.PageInfo pageInfo = new Pagination.PageInfo(this.getMeta().getPageableCount(), this.getMeta().getTotalCount(), this.getMeta().isEnd());
        return new Pagination<>(pageInfo, this.getDocuments());
    }
}
