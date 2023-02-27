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
package tech.xirius.filter.auto.processors;

/**
 * Wrapper for important data of a Class field
 */
public class FieldWrapper {
    /**
     * The name of the field
     */
    private String name;
    /**
     * The type of the field
     */
    private ClassData type;
    /**
     * The class of the filter of the field
     */
    private ClassData baseFilter;
    /**
     * String that corresponds to the type declaration of the correspondig filter, e.g. BasicFilter<Long>
     */
    private String finalFilterString;
    /**
     * Indicates wheter the field is an enum or not
     */
    private boolean isEnum;

    public FieldWrapper(String name, ClassData type, ClassData baseFilter, boolean isEnum) {
        this.baseFilter = baseFilter;
        this.name = name;
        this.type = type;
        this.isEnum = isEnum;
        this.finalFilterString = baseFilter.getSimpleClassName();
    }

    public FieldWrapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassData getType() {
        return type;
    }

    public void setType(ClassData type) {
        this.type = type;
    }

    public ClassData getBaseFilter() {
        return baseFilter;
    }

    public void setBaseFilter(ClassData filter) {
        this.baseFilter = filter;
    }

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean isEnum) {
        this.isEnum = isEnum;
    }

    public String getFinalFilterString() {
        return finalFilterString;
    }

    public void setFinalFilterString(String finalFilterString) {
        this.finalFilterString = finalFilterString;
    }

}