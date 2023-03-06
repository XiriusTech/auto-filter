package tech.xirius.filter.jpa.utils;

import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.RangeFilter;

public class SampleEntityFilter implements Filter {

    private BasicFilter<String> name = null;
    private RangeFilter<Integer> id = null;

    public void setName(BasicFilter<String> value) {
        this.name = value;
    }

    public BasicFilter<String> getName() {
        return this.name;
    }

    public void setId(RangeFilter<Integer> value) {
        this.id = value;
    }

    public BasicFilter<Integer> getId() {
        return this.id;
    }


}