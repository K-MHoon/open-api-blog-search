package com.blog.search.service.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.request.openapi.kakao.OpenApiRequestKakao;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest
class OpenApiTest {

    @Value("${api.kakao.key}")
    String key;

    @Value("${api.kakao.host}")
    String host;

    @Test
    void openApiCallTest() throws URISyntaxException {
        OpenApiRequest request = new OpenApiRequestKakao(key, host, "/v2/search/blog");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("https://" + request.getHost() + request.getUrl() + "?" + "query=링딩동");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, request.getAuthorization());
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<OpenApiResponseKakaoBlogSearch> response = restTemplate.exchange(uri, HttpMethod.GET, entity, OpenApiResponseKakaoBlogSearch.class);

        System.out.println(response.getBody());
    }

    @Test
    void dateParse() {
        String date = "2023-02-14T19:44:22.000+09:00";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime parse = LocalDateTime.parse(date, pattern);
        System.out.println(parse);
    }

}