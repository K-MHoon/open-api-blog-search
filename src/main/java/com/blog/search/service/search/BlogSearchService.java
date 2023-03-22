package com.blog.search.service.search;

import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.CompanyType;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
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

    /**
     * 검색한 키워드를 저장한다.
     *
     * @param query
     * @param companyType
     */
    @Transactional
    public void saveSearchHistory(String query, CompanyType companyType) {
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
