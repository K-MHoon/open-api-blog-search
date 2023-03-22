package com.blog.search.enums.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.request.openapi.naver.NaverBlogSearchParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.naver.OpenApiResponseNaverBlogSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 네이버 API별로 Url과 ResponseType, Parameter Type을 가지는 Enum
 */
@RequiredArgsConstructor
@Getter
public enum NaverApiType implements ApiType {

    BLOG_SEARCH("/v1/search/blog.json", OpenApiResponseNaverBlogSearch.class, NaverBlogSearchParameter.class);

    private final String url;
    private final Class<? extends OpenApiResponse> responseClassType;

    private final Class<? extends OpenApiRequestParameter> requestParameterType;

}
