package com.blog.search.dto.info;

import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;

public interface OpenApiInfo {

    String getKey();
    String getUrl();
    String getHost();
    OpenApiRequest createRequest(OpenApiRequestParameter parameter);
    Class<? extends OpenApiResponse> getResponseType();
}
