package com.blog.search.service.search;

import com.blog.search.dto.request.openapi.kakao.OpenApiRequestKakao;
import com.blog.search.dto.request.openapi.kakao.OpenApiRequestParameterKakaoBlogSearch;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.openapi.kakao.OpenApiResponseKakaoBlogSearch;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.ApiCompany;
import com.blog.search.enums.sort.SearchSort;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import com.blog.search.service.openapi.OpenApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogSearchServiceImpl implements BlogSearchService {

    @Value("${api.kakao.key}")
    private String key;

    @Value("${api.kakao.host}")
    private String host;

    @Value("${api.kakao.url}")
    private String url;

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
        OpenApiRequestKakao kakaoRequest = new OpenApiRequestKakao(key, host, url, parameter);
        searchHistoryJpaRepository.save(new SearchHistory(query, ApiCompany.KAKAO));

        OpenApi openApi = new OpenApi(OpenApiResponseKakaoBlogSearch.class, kakaoRequest);
        return openApi.call();
    }

    @Override
    @Transactional(readOnly = true)
    public BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(Integer size) {
        return new BlogSearchControllerResponse.GetPopularKeywordResponse(searchHistoryJpaRepository.findAllPopularKeywordStatistics(size));
    }
}
