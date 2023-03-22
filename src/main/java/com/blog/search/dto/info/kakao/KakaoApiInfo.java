package com.blog.search.dto.info.kakao;

import com.blog.search.dto.info.OpenApiInfo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 카카오 Api 호출 관련 정보를 담고있는 Information
 */
@Component("KAKAO")
@Getter
public class KakaoApiInfo implements OpenApiInfo {

    @Value("${api.kakao.key}")
    private String key;

    @Value("${api.kakao.host}")
    private String host;

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + getKey());
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public String getRequestUrl(UriComponentsBuilder parameter, String url) {
        return parameter.scheme("https").host(this.host).path(url).build().toUriString();
    }
}
