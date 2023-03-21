package com.blog.search.enums.openapi;

import com.blog.search.dto.response.openapi.OpenApiResponse;

public interface ApiType {

    String getUrl();
    Class<? extends OpenApiResponse> getResponseClassType();
}
