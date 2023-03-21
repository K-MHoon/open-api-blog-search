package com.blog.search.service.search;

import com.blog.search.dto.response.search.BlogSearchControllerResponse;
import com.blog.search.entity.search.SearchHistory;
import com.blog.search.enums.CompanyType;
import com.blog.search.repository.search.SearchHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogSearchService {
    private final SearchHistoryJpaRepository searchHistoryJpaRepository;

    @Transactional
    public void saveSearchHistory(String query, CompanyType companyType) {
        SearchHistory searchHistory = new SearchHistory(query, companyType);
        searchHistoryJpaRepository.save(searchHistory);
    }

    @Transactional(readOnly = true)
    public BlogSearchControllerResponse.GetPopularKeywordResponse getPopularKeyword(Integer size) {
        return new BlogSearchControllerResponse.GetPopularKeywordResponse(searchHistoryJpaRepository.findAllPopularKeywordStatistics(size));
    }
}
