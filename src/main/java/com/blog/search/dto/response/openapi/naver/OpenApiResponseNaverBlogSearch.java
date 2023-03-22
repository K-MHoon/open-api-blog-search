package com.blog.search.dto.response.openapi.naver;

import com.blog.search.dto.Pagination;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 네이버 블로그 검색 API Response
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiResponseNaverBlogSearch implements OpenApiResponse {

    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<Item> items;

    @Getter
    @JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggerName;
        private String bloggerLink;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
        private LocalDate postDate;
    }

    @Override
    public Pagination getPagination() {
        Pagination.PageInfo pageInfo = Pagination.PageInfo.builder()
                .totalPage(getTotal()/getDisplay())
                .totalElements(getTotal())
                .build();
        return new Pagination<>(pageInfo, this.getItems());
    }
}
