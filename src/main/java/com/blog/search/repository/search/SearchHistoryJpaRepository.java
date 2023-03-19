package com.blog.search.repository.search;

import com.blog.search.entity.search.SearchHistory;
import com.blog.search.repository.search.support.SearchHistoryJpaRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistory, Long>, SearchHistoryJpaRepositorySupport {
}
