package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.enums.sort.KakaoBlogSearchSort;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Data
public class KakaoBlogSearchParameter implements OpenApiRequestParameter {

    @NotNull
    private String query;

    private KakaoBlogSearchSort sort;

    @Min(1)
    @Max(50)
    private Integer page;

    @Min(1)
    @Max(50)
    private Integer size;

    @Builder
    public KakaoBlogSearchParameter(String query, KakaoBlogSearchSort sort, Integer page, Integer size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    @Override
    public UriComponentsBuilder getParameters() {
        return UriComponentsBuilder
                .newInstance()
                .queryParam("query", this.getQuery())
                .queryParam("sort", getSortValue())
                .queryParam("page", this.getPage())
                .queryParam("size", this.getSize());
    }

    private String getSortValue() {
        return Objects.isNull(this.getSort())
                ? null
                : this.getSort().getValue();
    }
}
