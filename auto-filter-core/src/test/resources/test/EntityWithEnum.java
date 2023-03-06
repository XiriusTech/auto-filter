package test;

import java.math.BigDecimal;

import tech.xirius.filter.auto.annotations.AutoFilter;

@AutoFilter
public class EntityWithEnum {
    private BigDecimal myNumber;
    private EnumTest myEnum;
}
