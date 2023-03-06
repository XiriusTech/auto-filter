package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;
import tech.xirius.filter.filtering.RangeFilter;

@AutoFilter(baseFilter = RangeFilter.class)
public class CustomFilterEntity {
    private String myString;
    @AutoFilterInclude(baseFilter = CustomFilter.class)
    private Integer myInteger;
}
