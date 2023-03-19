package com.blog.search.repository.search.support;

import com.blog.search.dto.entity.search.SearchHistoryDto;

import java.util.List;

public interface SearchHistoryJpaRepositorySupport {

    List<SearchHistoryDto.PopularKeywordStatistics> findAllPopularKeywordStatistics(Integer size);
}
