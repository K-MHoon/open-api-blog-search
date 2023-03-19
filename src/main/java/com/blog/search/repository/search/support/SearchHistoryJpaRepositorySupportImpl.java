package com.blog.search.repository.search.support;

import com.blog.search.entity.search.SearchHistory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class SearchHistoryJpaRepositorySupportImpl extends QuerydslRepositorySupport implements SearchHistoryJpaRepositorySupport {

    public SearchHistoryJpaRepositorySupportImpl() {
        super(SearchHistory.class);
    }


}
