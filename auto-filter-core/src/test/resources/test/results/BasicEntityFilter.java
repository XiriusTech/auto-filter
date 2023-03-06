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
import tech.xirius.filter.filtering.BasicFilter;
import tech.xirius.filter.filtering.Filter;

public class BasicEntityFilter implements Filter {

    private BasicFilter<Integer> intField = null;
    private BasicFilter<Boolean> booleanField = null;
    private BasicFilter<Character> charField = null;
    private BasicFilter<Byte> byteField = null;
    private BasicFilter<Short> shortField = null;
    private BasicFilter<Long> longField = null;
    private BasicFilter<Float> floatField = null;
    private BasicFilter<Double> doubleField = null;
    private BasicFilter<Integer> integerField = null;
    private BasicFilter<Boolean> boxedBooleanField = null;
    private BasicFilter<Character> characterField = null;
    private BasicFilter<Byte> boxedByteField = null;
    private BasicFilter<Short> boxedShortField = null;
    private BasicFilter<Long> boxedLongField = null;
    private BasicFilter<Float> boxedFloatField = null;
    private BasicFilter<Double> boxedDoubleField = null;
    private BasicFilter<String> stringField = null;
    private BasicFilter<BigDecimal> bigDecimalField = null;

    public void setIntField(BasicFilter<Integer> value) {
        this.intField = value;
    }

    public BasicFilter<Integer> getIntField() {
        return this.intField;
    }

    public void setBooleanField(BasicFilter<Boolean> value) {
        this.booleanField = value;
    }

    public BasicFilter<Boolean> getBooleanField() {
        return this.booleanField;
    }

    public void setCharField(BasicFilter<Character> value) {
        this.charField = value;
    }

    public BasicFilter<Character> getCharField() {
        return this.charField;
    }

    public void setByteField(BasicFilter<Byte> value) {
        this.byteField = value;
    }

    public BasicFilter<Byte> getByteField() {
        return this.byteField;
    }

    public void setShortField(BasicFilter<Short> value) {
        this.shortField = value;
    }

    public BasicFilter<Short> getShortField() {
        return this.shortField;
    }

    public void setLongField(BasicFilter<Long> value) {
        this.longField = value;
    }

    public BasicFilter<Long> getLongField() {
        return this.longField;
    }

    public void setFloatField(BasicFilter<Float> value) {
        this.floatField = value;
    }

    public BasicFilter<Float> getFloatField() {
        return this.floatField;
    }

    public void setDoubleField(BasicFilter<Double> value) {
        this.doubleField = value;
    }

    public BasicFilter<Double> getDoubleField() {
        return this.doubleField;
    }

    public void setIntegerField(BasicFilter<Integer> value) {
        this.integerField = value;
    }

    public BasicFilter<Integer> getIntegerField() {
        return this.integerField;
    }

    public void setBoxedBooleanField(BasicFilter<Boolean> value) {
        this.boxedBooleanField = value;
    }

    public BasicFilter<Boolean> getBoxedBooleanField() {
        return this.boxedBooleanField;
    }

    public void setCharacterField(BasicFilter<Character> value) {
        this.characterField = value;
    }

    public BasicFilter<Character> getCharacterField() {
        return this.characterField;
    }

    public void setBoxedByteField(BasicFilter<Byte> value) {
        this.boxedByteField = value;
    }

    public BasicFilter<Byte> getBoxedByteField() {
        return this.boxedByteField;
    }

    public void setBoxedShortField(BasicFilter<Short> value) {
        this.boxedShortField = value;
    }

    public BasicFilter<Short> getBoxedShortField() {
        return this.boxedShortField;
    }

    public void setBoxedLongField(BasicFilter<Long> value) {
        this.boxedLongField = value;
    }

    public BasicFilter<Long> getBoxedLongField() {
        return this.boxedLongField;
    }

    public void setBoxedFloatField(BasicFilter<Float> value) {
        this.boxedFloatField = value;
    }

    public BasicFilter<Float> getBoxedFloatField() {
        return this.boxedFloatField;
    }

    public void setBoxedDoubleField(BasicFilter<Double> value) {
        this.boxedDoubleField = value;
    }

    public BasicFilter<Double> getBoxedDoubleField() {
        return this.boxedDoubleField;
    }

    public void setStringField(BasicFilter<String> value) {
        this.stringField = value;
    }

    public BasicFilter<String> getStringField() {
        return this.stringField;
    }

    public void setBigDecimalField(BasicFilter<BigDecimal> value) {
        this.bigDecimalField = value;
    }

    public BasicFilter<BigDecimal> getBigDecimalField() {
        return this.bigDecimalField;
    }


}
