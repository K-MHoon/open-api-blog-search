package com.blog.search.config;

import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.enums.CompanyType;

/**
 * CompanyType에 따른 Information 객체를 찾아주는 Locator
 */
public interface OpenApiInfoLocator {
    OpenApiInfo getOpenApiInfo(CompanyType apiCompany);
}
