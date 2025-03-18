package com.capitole.technical_interview.domain.sort;

import java.util.List;

public interface SortService <T> {
    List<T> sort(List<T> items, String column, boolean ascending);
}
