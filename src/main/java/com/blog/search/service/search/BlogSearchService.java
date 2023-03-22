package com.blog.search.service.search;

import com.blog.search.dto.request.openapi.kakao.KakaoBlogSearchParameter;
import com.blog.search.dto.request.openapi.naver.NaverBlogSearchParameter;
import com.blog.search.dto.response.openapi.OpenApiResponse;
import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.CompanyType;
import com.blog.search.enums.openapi.KakaoApiType;
import com.blog.search.enums.openapi.NaverApiType;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import com.blog.search.service.openapi.OpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 블로그 검색과 관련된 역할을 처리하는 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BlogSearchService {
    private final SearchHistoryJpaRepository searchHistoryJpaRepository;

    private final OpenApiService openApiService;

    /**
     * 카카오 API의 검색한 키워드를 저장하고 해당 요청을 호출한다.
     *
     * @param parameter
     * @return
     */
    @Transactional
    public OpenApiResponse getKakaoBlogSearchResult(KakaoBlogSearchParameter parameter) {
        saveSearchHistory(CompanyType.KAKAO, parameter.getQuery());
        return openApiService.call(CompanyType.KAKAO, KakaoApiType.BLOG_SEARCH, parameter);
    }

    /**
     * 네이버 API의 검색한 키워드를 저장하고 해당 요청을 호출한다.
     *
     * @param parameter
     * @return
     */
    @Transactional
    public OpenApiResponse getNaverBlogSearchResult(NaverBlogSearchParameter parameter) {
        saveSearchHistory(CompanyType.NAVER, parameter.getQuery());
        return openApiService.call(CompanyType.NAVER, NaverApiType.BLOG_SEARCH, parameter);
    }

    private void saveSearchHistory(CompanyType companyType, String query) {
        SearchHistory searchHistory = new SearchHistory(query, companyType);
        searchHistoryJpaRepository.save(searchHistory);
    }

    /**
     * 검색한 키워드를 조회한다.
     *
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(Integer size) {
        return new BlogSearchControllerResponse.GetPopularKeywordResponse(searchHistoryJpaRepository.findAllPopularKeywordStatistics(size));
    }
}
