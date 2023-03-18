package com.blog.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Pagination<T> {

    private PageInfo pageInfo;
    private List<T> data;

    @Getter
    @AllArgsConstructor
    @ToString
    public static class PageInfo {
        private int totalPage;
        private int totalElements;
        private boolean isLast;
    }

}
