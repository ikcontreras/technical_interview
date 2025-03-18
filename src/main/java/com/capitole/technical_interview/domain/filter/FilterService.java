package com.capitole.technical_interview.domain.filter;

import java.util.List;

public interface FilterService<T> {
    List<T> filterByCategory(List<T> items, String condition);
}
