package com.blog.search.repository.search;

import com.blog.search.entity.search.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistory, Long> {
}
