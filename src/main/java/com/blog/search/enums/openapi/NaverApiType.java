package com.blog.search.enums.openapi;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.naver.OpenApiResponseNaverBlogSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NaverApiType implements ApiType {

    BLOG_SEARCH("/v1/search/blog.json", OpenApiResponseNaverBlogSearch.class);

    private final String url;
    private final Class<? extends OpenApiResponse> responseClassType;
}
