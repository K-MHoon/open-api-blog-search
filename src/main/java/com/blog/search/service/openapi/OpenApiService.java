package com.blog.search.service.openapi;

import com.blog.search.config.OpenApiInfoLocator;
import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.enums.CompanyType;
import com.blog.search.enums.openapi.ApiType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 오픈 API 관련 호출 역할을 하는 서비스
 */
@Getter
@RequiredArgsConstructor
@Service
public class OpenApiService {
    private final RestTemplate restTemplate;
    private final OpenApiInfoLocator openApiInfoLocator;

    /**
     * OpenApi를 호출한다.
     *
     * @param company 호출할 회사
     * @param apiType 호출한 Api 타입
     * @param parameter 호출할 파라미터
     * @return
     */
    public OpenApiResponse call(CompanyType company, ApiType apiType, OpenApiRequestParameter parameter) {
        OpenApiInfo openApiInfo = openApiInfoLocator.getOpenApiInfo(company);

        String requestUrl = openApiInfo.getRequestUrl(parameter.getParameters(), apiType.getUrl());
        HttpEntity entity = new HttpEntity<>(openApiInfo.getHttpHeaders());
        Class<? extends OpenApiResponse> responseType = apiType.getResponseClassType();

        ResponseEntity<? extends OpenApiResponse> result = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, responseType);

        return result.getBody();
    }
}
