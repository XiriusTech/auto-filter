
package test;

import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class AutoFilterReferenceEntityFilter implements Filter {

    private BasicFilter<Integer> intField = null;
    private BasicEntityFilter otherEntity = null;

    public void setIntField(BasicFilter<Integer> value) {
        this.intField = value;
    }

    public BasicFilter<Integer> getIntField() {
        return this.intField;
    }

    public void setOtherEntity(BasicEntityFilter value) {
        this.otherEntity = value;
    }

    public BasicEntityFilter getOtherEntity() {
        return this.otherEntity;
    }


}