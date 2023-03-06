package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterExclude;

@AutoFilter
public class ExcludeFieldEntity {
    @AutoFilterExclude
    private int intField;
    private int intField2;
}
