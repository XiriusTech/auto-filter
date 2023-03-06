package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;

@AutoFilter
public class AutoFilterReferenceEntity {
    private Integer intField;
    @AutoFilterInclude(baseFilter = BasicEntityFilter.class)
    private BasicEntity otherEntity;
}
