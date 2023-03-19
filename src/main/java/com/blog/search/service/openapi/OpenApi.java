package com.blog.search.service.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Getter
@RequiredArgsConstructor
public class OpenApi {
    private final Class<? extends OpenApiResponse> responseType;
    private final OpenApiRequest request;

    public OpenApiResponse call() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<? extends OpenApiResponse> result = restTemplate.exchange(request.getRequestUrl(), HttpMethod.GET, getJsonRequestHttpEntity(), responseType);
        return result.getBody();
    }

    private HttpEntity getJsonRequestHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, request.getAuthorization());
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
