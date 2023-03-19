package com.blog.search.service.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Getter
@RequiredArgsConstructor
@Component
public class OpenApi {
    private final RestTemplate restTemplate;

    public OpenApiResponse call(OpenApiRequest request, Class<? extends OpenApiResponse> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, request.getAuthorization());
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity<? extends OpenApiResponse> result = restTemplate.exchange(request.getRequestUrl(), HttpMethod.GET, entity, responseType);
        return result.getBody();
    }
}
