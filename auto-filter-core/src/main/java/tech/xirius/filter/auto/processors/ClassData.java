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
 * Wrapper for important data of a Java Class
 */
public class ClassData {
    /**
     * The full name of a class, e.g. java.lang.String
     */
    private String fullClassName;
    /**
     * The package of a class, e.g. java.lang is the package of java.lang.String
     */
    private String packageName;
    /**
     * The simple name of a class, e.g. String is the package of java.lang.String
     */
    private String simpleClassName;
    /**
     * Count of generic type parameters, e.g. for String is 0, for List<T> is 1 and
     * for Map<K, V> is 2
     */
    private Integer genericTypeCount;

    public ClassData(String fullClassName) {
        genericTypeCount = calculateGenericTypeCount(fullClassName);
        this.fullClassName = fullClassName.replaceAll("<.*>", "");
        int lastDot = getLastDot(fullClassName);
        this.packageName = lastDot > 0 ? this.fullClassName.substring(0, lastDot) : null;
        this.simpleClassName = lastDot > 0 ? this.fullClassName.substring(lastDot + 1) : fullClassName;
    }

    private int calculateGenericTypeCount(String fullClassName) {
        return fullClassName.split("<|,").length - 1;
    }

    private int getLastDot(String className) {
        return className.lastIndexOf('.');
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public Integer getGenericTypeCount() {
        return genericTypeCount;
    }

    public void setGenericTypeCount(Integer genericTypeCount) {
        this.genericTypeCount = genericTypeCount;
    }

}
