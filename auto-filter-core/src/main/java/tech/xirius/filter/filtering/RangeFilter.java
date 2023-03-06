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
package tech.xirius.filter.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tech.xirius.filter.utils.FilterUtils;

/**
 * Filter that extends {@link BasicFilter} with conditions related to
 * ranges of values
 */
public class RangeFilter<T> extends BasicFilter<T> {
    private LessThanFilter<T> lessThan = null;
    private GreaterThanFilter<T> greaterThan = null;
    private LessThanOrEqualToFilter<T> lessThanOrEqualTo = null;
    private GreaterThanOrEqualToFilter<T> greaterThanOrEqualTo = null;

    public T getLessThan() {
        return FilterUtils.getWrapper(lessThan);
    }

    public void setLessThan(T value) {
        this.lessThan = FilterUtils.setWrapper(lessThan, value, () -> new LessThanFilter<>());
    }

    public T getGreaterThan() {
        return FilterUtils.getWrapper(greaterThan);
    }

    public void setGreaterThan(T value) {
        this.greaterThan = FilterUtils.setWrapper(greaterThan, value, () -> new GreaterThanFilter<>());
    }

    public T getLessThanOrEqualTo() {
        return FilterUtils.getWrapper(lessThanOrEqualTo);
    }

    public void setLessThanOrEqualTo(T value) {
        this.lessThanOrEqualTo = FilterUtils.setWrapper(lessThanOrEqualTo, value, () -> new LessThanOrEqualToFilter<>());
    }

    public T getGreaterThanOrEqualTo() {
        return FilterUtils.getWrapper(greaterThanOrEqualTo);
    }

    public void setGreaterThanOrEqualTo(T value) {
        this.greaterThanOrEqualTo = FilterUtils.setWrapper(greaterThanOrEqualTo, value, () -> new GreaterThanOrEqualToFilter<>());
    }

    @Override
    public List<Filter> retrieveSubFilters() {
        List<Filter> filters = new ArrayList<>(super.retrieveSubFilters());
        filters.addAll(Arrays.asList(lessThan, lessThanOrEqualTo, greaterThan, greaterThanOrEqualTo));
        return filters;
    }
}
