package com.blog.search.enums.openapi;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum KakaoApiType implements ApiType {

    BLOG_SEARCH("/v2/search/blog", OpenApiResponseKakaoBlogSearch.class);

    private final String url;
    private final Class<? extends OpenApiResponse> responseClassType;
}
