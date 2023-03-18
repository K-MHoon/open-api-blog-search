package com.blog.search.dto.request.openapi.kakao;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import lombok.Getter;

@Getter
public class OpenApiRequestKakao extends OpenApiRequest {

    public OpenApiRequestKakao(String key, String host, String url) {
        super(key, host, url);
    }

    @Override
    public String getAuthorization() {
        return "KakaoAK " + getKey();
    }
}
