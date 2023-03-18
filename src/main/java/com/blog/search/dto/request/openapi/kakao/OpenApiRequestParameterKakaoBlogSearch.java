package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.enums.sort.SearchSort;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Getter
public class OpenApiRequestParameterKakaoBlogSearch implements OpenApiRequestParameter {
    private String query;
    private String sort;
    private Integer page;
    private Integer size;

    @Builder
    public OpenApiRequestParameterKakaoBlogSearch(String query, SearchSort sort, Integer page, Integer size) {
        this.query = Objects.requireNonNull(query);
        this.sort = Objects.isNull(sort) ? null : sort.getValue();
        this.page = page;
        this.size = size;
    }

    @Override
    public String getUrlWithParams(String host, String url) {
        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(host)
                .path(url)
                .queryParam("query", this.getQuery())
                .queryParam("sort", this.getSort())
                .queryParam("page", this.getPage())
                .queryParam("size", this.getSize())
                .toUriString();
    }
}
