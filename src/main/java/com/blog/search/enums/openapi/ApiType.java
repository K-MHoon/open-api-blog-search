package com.blog.search.enums.openapi;

import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;

/**
 * Open APi 관련 Url, Response, Parameter Type Interface
 */
public interface ApiType {

    String getUrl();
    Class<? extends OpenApiResponse> getResponseClassType();
    Class<? extends OpenApiRequestParameter> getRequestParameterType();
}
