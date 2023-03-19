package com.blog.search.service.search;

import com.blog.search.config.OpenApiInfoLocator;
import com.blog.search.dto.info.OpenApiInfo;
import com.blog.search.dto.request.openapi.OpenApiRequest;
import com.blog.search.dto.request.openapi.OpenApiRequestParameter;
import com.blog.search.dto.request.openapi.kakao.OpenApiRequestParameterKakaoBlogSearch;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.ApiCompany;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import com.blog.search.service.openapi.OpenApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogSearchServiceImpl implements BlogSearchService {

    private final OpenApi openApi;

    private final OpenApiInfoLocator openApiInfoLocator;

    private final SearchHistoryJpaRepository searchHistoryJpaRepository;

    @Override
    @Transactional
    public OpenApiResponse getKakaoBlogSearchResult(String query, SearchSort sort, Integer page, Integer size) {
        OpenApiRequestParameterKakaoBlogSearch parameter = OpenApiRequestParameterKakaoBlogSearch.builder()
                .query(query)
                .sort(sort)
                .page(page)
                .size(size)
                .build();

        searchHistoryJpaRepository.save(new SearchHistory(query, ApiCompany.KAKAO));

        return getOpenApiResponse(ApiCompany.KAKAO, parameter);
    }

    private OpenApiResponse getOpenApiResponse(ApiCompany company, OpenApiRequestParameter parameter) {
        OpenApiInfo openApiInfo = openApiInfoLocator.getOpenApiInfo(company);

        OpenApiRequest request = openApiInfo.createRequest(parameter);
        Class<? extends OpenApiResponse> responseType = openApiInfo.getResponseType();

        return openApi.call(request, responseType);
    }

    @Override
    @Transactional(readOnly = true)
    public BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(Integer size) {
        return new BlogSearchControllerResponse.GetPopularKeywordResponse(searchHistoryJpaRepository.findAllPopularKeywordStatistics(size));
    }
}
