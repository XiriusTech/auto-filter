package test;

import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class ExcludeFieldEntityFilter implements Filter {

    private BasicFilter<Integer> intField2 = null;

    public void setIntField2(BasicFilter<Integer> value) {
        this.intField2 = value;
    }

    public BasicFilter<Integer> getIntField2() {
        return this.intField2;
    }


}