package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import lombok.Getter;

@Getter
public class OpenApiRequestKakao extends OpenApiRequest {

    public OpenApiRequestKakao(String key, String host, String url, OpenApiRequestParameter parameter) {
        super(key, host, url, parameter);
    }

    @Override
    public String getAuthorization() {
        return "KakaoAK " + getKey();
    }

    @Override
    public String getRequestUrl() {
        return getParameter().getUrlWithParams(getHost(), getUrl());
    }


}
