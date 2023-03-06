package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterExclude;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;
import tech.xirius.filter.filtering.RangeFilter;

@AutoFilter
public class ExcludeAndIncludeFieldEntity {
    @AutoFilterExclude
    @AutoFilterInclude(baseFilter = RangeFilter.class)
    private int intField;
    private int intField2;
}
