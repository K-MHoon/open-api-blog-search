package com.blog.search.repository.search.support;

import com.blog.search.dto.entity.search.SearchHistoryDto;

import java.util.List;

/**
 * 검색 키워드 Querydsl 관련 Interface
 */
public interface SearchHistoryJpaRepositorySupport {

    List<SearchHistoryDto.PopularKeywordStatistics> findAllPopularKeywordStatistics(Integer size);
}
