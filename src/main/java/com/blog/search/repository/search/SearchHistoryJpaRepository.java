package com.blog.search.repository.search;

import com.blog.search.entity.search.SearchHistory;
import com.blog.search.repository.search.support.SearchHistoryJpaRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 검색 키워드 JpaRepository
 */
public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistory, Long>, SearchHistoryJpaRepositorySupport {
}
