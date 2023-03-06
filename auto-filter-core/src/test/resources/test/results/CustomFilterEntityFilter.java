package test;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.RangeFilter;

public class CustomFilterEntityFilter implements Filter {

    private RangeFilter<String> myString = null;
    private CustomFilter<Integer> myInteger = null;

    public void setMyString(RangeFilter<String> value) {
        this.myString = value;
    }

    public RangeFilter<String> getMyString() {
        return this.myString;
    }

    public void setMyInteger(CustomFilter<Integer> value) {
        this.myInteger = value;
    }

    public CustomFilter<Integer> getMyInteger() {
        return this.myInteger;
    }


}
