package com.blog.search.config;

import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.enums.ApiCompany;

public interface OpenApiInfoLocator {
    OpenApiInfo getOpenApiInfo(ApiCompany apiCompany);
}
