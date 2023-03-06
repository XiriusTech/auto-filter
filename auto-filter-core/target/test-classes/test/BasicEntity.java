package test;

import java.math.BigDecimal;

import tech.xirius.filter.auto.annotations.AutoFilter;

/**
 * Entity with all the types that are supported by default (except enums)
 */
@AutoFilter
public class BasicEntity {
    // Primitives
    private int intField;
    private boolean booleanField;
    private char charField;
    private byte byteField;
    private short shortField;
    private long longField;
    private float floatField;
    private double doubleField;

    // Boxes
    private Integer integerField;
    private Boolean boxedBooleanField;
    private Character characterField;
    private Byte boxedByteField;
    private Short boxedShortField;
    private Long boxedLongField;
    private Float boxedFloatField;
    private Double boxedDoubleField;

    // Additional supported types
    private String stringField;
    private BigDecimal bigDecimalField;
}