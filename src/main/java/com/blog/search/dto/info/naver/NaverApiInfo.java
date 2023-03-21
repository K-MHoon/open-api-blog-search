package com.blog.search.dto.info.naver;

import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component("NAVER")
@Getter
public class NaverApiInfo implements OpenApiInfo {

    @Value("${api.naver.client}")
    private String client;

    @Value("${api.naver.secret}")
    private String secret;

    @Value("${api.naver.host}")
    private String host;

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", client);
        headers.set("X-Naver-Client-Secret", secret);
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public String getRequestUrl(UriComponentsBuilder parameter, String url) {
        return parameter.scheme("https")
                .host(this.host)
                .path(url)
                .build()
                .toUriString();
    }
}
