package test;

import java.math.BigDecimal;
import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class EntityWithEnumFilter implements Filter {

    private BasicFilter<BigDecimal> myNumber = null;
    private EnumTestFilter myEnum = null;

    public void setMyNumber(BasicFilter<BigDecimal> value) {
        this.myNumber = value;
    }

    public BasicFilter<BigDecimal> getMyNumber() {
        return this.myNumber;
    }

    public void setMyEnum(EnumTestFilter value) {
        this.myEnum = value;
    }

    public EnumTestFilter getMyEnum() {
        return this.myEnum;
    }


    public static class EnumTestFilter extends BasicFilter<EnumTest> {
    }
}
