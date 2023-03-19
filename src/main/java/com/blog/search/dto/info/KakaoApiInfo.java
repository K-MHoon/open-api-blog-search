package com.blog.search.dto.info;

import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.request.openapi.kakao.OpenApiRequestKakao;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("KAKAO")
@Getter
@ToString
public class KakaoApiInfo implements OpenApiInfo {

    @Value("${api.kakao.key}")
    private String key;

    @Value("${api.kakao.host}")
    private String host;

    @Value("${api.kakao.url}")
    private String url;

    @Override
    public OpenApiRequest createRequest(OpenApiRequestParameter parameter) {
        return new OpenApiRequestKakao(key, host, url, parameter);
    }

    @Override
    public Class<? extends OpenApiResponse> getResponseType() {
        return OpenApiResponseKakaoBlogSearch.class;
    }

}
