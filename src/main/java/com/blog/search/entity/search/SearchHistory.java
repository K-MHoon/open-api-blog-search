package com.blog.search.entity.search;

import com.blog.search.entity.BaseEntity;
import com.blog.search.enums.CompanyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "search_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    @Enumerated(EnumType.STRING)
    private CompanyType company;

    @Builder
    public SearchHistory(String query, CompanyType company) {
        this.query = query;
        this.company = company;
    }
}
