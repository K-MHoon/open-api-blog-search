package com.blog.search.converter;

import com.blog.search.enums.sort.KakaoBlogSearchSort;
import org.springframework.core.convert.converter.Converter;

/**
 * Request 받은 String을 KakaoBlogSearchSort Type으로 변환해주는 Converter Class
 */
public class StringToKakaoBlogSearchSortConverter implements Converter<String, KakaoBlogSearchSort> {

    @Override
    public KakaoBlogSearchSort convert(String source) {
        return KakaoBlogSearchSort.getSearchSortByValue(source);
    }
}
