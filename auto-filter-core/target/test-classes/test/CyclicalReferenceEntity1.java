package test;

import tech.xirius.filter.auto.annotations.AutoFilter;
import tech.xirius.filter.auto.annotations.AutoFilterInclude;

@AutoFilter
public class CyclicalReferenceEntity1 {
    private Integer intField;
    @AutoFilterInclude(baseFilter = CyclicalReferenceEntity2Filter.class)
    private CyclicalReferenceEntity2 otherEntity;
}
