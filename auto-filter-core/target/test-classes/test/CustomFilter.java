package test;

import java.util.Arrays;
import java.util.List;

import tech.xirius.filter.filtering.EqualsFilter;
import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.MultiFilter;

public class CustomFilter<T> implements MultiFilter<T> {
    private EqualsFilter<T> equals;

    @Override
    public List<Filter> retrieveSubFilters() {
        return Arrays.asList(equals);
    }
    
}
