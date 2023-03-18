package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OpenApiRequestParameterKakaoBlogSearch implements OpenApiRequestParameter {
    private String query;
    private String sort;
    private String page;
    private String size;
}
