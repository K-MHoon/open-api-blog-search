package com.blog.search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Pagination<T> {

    private PageInfo pageInfo;
    private List<T> data;

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @AllArgsConstructor
    @ToString
    public static class PageInfo {
        private Integer totalPage;
        private Integer totalElements;
        private Boolean isLast;
    }

}
