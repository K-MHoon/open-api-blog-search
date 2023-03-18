package com.blog.search.service.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OpenApi {
    private final OpenApiResponse response;
    private final OpenApiRequest request;

    public void call() {
    }
}
