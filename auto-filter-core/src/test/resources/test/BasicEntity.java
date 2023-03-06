/*
 * Copyright (C) 2023 Xirius Tech S.A.S
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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