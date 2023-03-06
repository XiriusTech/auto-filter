package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;

@AutoFilter
public class CyclicalReferenceEntity2 {
    private Integer intField;
    @AutoFilterInclude(baseFilter = CyclicalReferenceEntity1Filter.class)
    private CyclicalReferenceEntity1 otherEntity;
}

