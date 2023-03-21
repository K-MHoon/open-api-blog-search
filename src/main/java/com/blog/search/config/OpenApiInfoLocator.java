package com.blog.search.config;

import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.enums.CompanyType;

public interface OpenApiInfoLocator {
    OpenApiInfo getOpenApiInfo(CompanyType apiCompany);
}
