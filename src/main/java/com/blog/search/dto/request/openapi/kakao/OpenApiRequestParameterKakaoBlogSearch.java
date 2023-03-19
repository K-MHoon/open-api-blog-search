package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.enums.sort.SearchSort;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Getter
@ToString
public class OpenApiRequestParameterKakaoBlogSearch implements OpenApiRequestParameter {

    private String query;
    private SearchSort sort;
    private Integer page;
    private Integer size;

    @Builder
    public OpenApiRequestParameterKakaoBlogSearch(String query, SearchSort sort, Integer page, Integer size) {
        this.query = Objects.requireNonNull(query, "검색어는 필수 값입니다.");
        this.sort = sort;
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
                .queryParam("sort", getSortValue())
                .queryParam("page", this.getPage())
                .queryParam("size", this.getSize())
                .build()
                .toUriString();
    }

    private Object getSortValue() {
        return Objects.isNull(this.getSort())
                ? null
                : this.getSort().getValue();
    }
}
