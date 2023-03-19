package com.blog.search.repository.search.support;

import com.blog.search.dto.entity.search.QSearchHistoryDto_PopularKeywordStatistics;
import com.blog.search.dto.entity.search.SearchHistoryDto;
import com.blog.search.entity.search.SearchHistory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.blog.search.entity.search.QSearchHistory.searchHistory;

public class SearchHistoryJpaRepositorySupportImpl extends QuerydslRepositorySupport implements SearchHistoryJpaRepositorySupport {

    public SearchHistoryJpaRepositorySupportImpl() {
        super(SearchHistory.class);
    }

    @Override
    public List<SearchHistoryDto.PopularKeywordStatistics> findAllPopularKeywordStatistics(Integer size) {
        return from(searchHistory)
                .select(new QSearchHistoryDto_PopularKeywordStatistics(searchHistory.query, searchHistory.query.count()))
                .groupBy(searchHistory.query)
                .limit(size)
                .orderBy(new OrderSpecifier<>(Order.DESC, searchHistory.query.count()))
                .fetch();
    }
}
