package com.blog.search.enums.openapi;

import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 카카오 API별로 Url과 ResponseType, Parameter Type을 가지는 Enum
 */
@RequiredArgsConstructor
@Getter
public enum KakaoApiType implements ApiType {

    BLOG_SEARCH("/v2/search/blog", OpenApiResponseKakaoBlogSearch.class);

    private final String url;
    private final Class<? extends OpenApiResponse> responseClassType;
}
