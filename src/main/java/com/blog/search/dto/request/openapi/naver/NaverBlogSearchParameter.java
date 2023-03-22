package com.blog.search.dto.request.openapi.naver;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.enums.sort.NaverBlogSearchSort;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Data
public class NaverBlogSearchParameter implements OpenApiRequestParameter {

    @NotNull
    private String query;
    private NaverBlogSearchSort sort;

    @Min(1)
    @Max(100)
    private Integer display; // 한 번에 표시할 검색 결과 개수

    @Min(1)
    @Max(1000)
    private Integer start; // 검색 시작 위치

    @Builder
    public NaverBlogSearchParameter(String query, NaverBlogSearchSort sort, Integer display, Integer start) {
        this.query = query;
        this.sort = Objects.isNull(sort) ? NaverBlogSearchSort.SIM : sort;
        this.display = Objects.isNull(display) ? 10 : display;
        this.start = Objects.isNull(start) ? 1 :start;
    }

    @Override
    public UriComponentsBuilder getParameters() {
        return UriComponentsBuilder
                .newInstance()
                .queryParam("query", this.getQuery())
                .queryParam("sort", this.getSort())
                .queryParam("display", this.getDisplay())
                .queryParam("start", this.getStart());
    }
}
