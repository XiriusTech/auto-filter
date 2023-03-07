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
package tech.xirius.filter.utils;

import java.util.function.Supplier;

import tech.xirius.filter.filtering.SingleFilter;

/**
 * Class with utilities to create properly formatted filters
 */
public class FilterUtils {
    /**
     * Wrapper for a getter that masks the {@link SingleFilter#getValue()} of
     * the filter.
     * For example, for:
     * 
     * <pre>
     * private EqualsFilter&lt;T&gt; equals;
     * 
     * public T getEquals() {
     *     return FilterUtils.getWrapper(equals);
     * }
     * </pre>
     * 
     * Now the value of the filter can be obtained by using {@code getEquals()}
     * instead
     * of {@code getEquals().getValue()}.
     * The primary use of this method is so that when a filter is serialized, its
     * value remains with its primary name, instead of adding {@code .value} at the
     * end, e.g.
     * myFilter.equals instead of myFilter.equals.value.
     * 
     * @param <T>    the type parameter of the {@link SingleFilter}
     * @param filter the filter to be wrapper
     * @return the result of the {@link SingleFilter#getValue()} of the filter
     */
    public static <T> T getWrapper(SingleFilter<T> filter) {
        return filter != null ? filter.getValue() : null;
    }

    /**
     * Wrapper for a setter that masks the {@link SingleFilter#setValue(Object)} of
     * the filter.
     * For example, for:
     * 
     * <pre>
     * private EqualsFilter&lt;T&gt; equals;
     * 
     * public void setEquals(T value) {
     *     this.equals = FilterUtils.setWrapper(equals, value, () -&gt; new EqualsFilter&lt;&gt;());
     * }
     * </pre>
     * 
     * Now the value of the filter can be obtained by using {@code getEquals()}
     * instead of {@code getEquals().getValue()}.
     * The primary use of this method is so that when a filter is serialized, its
     * value remains with its primary name, instead of adding {@code .value} at the
     * end, e.g.
     * myFilter.equals instead of myFilter.equals.value.
     * 
     * @param <T>         the type parameter of the {@link SingleFilter}
     * @param <F>         the type of {@link SingleFilter}
     * @param filter      the filter to be wrapped. If {@code null} then the
     *                    {@code constructor} supplier is used to create a new
     *                    instance
     * @param value       the value to be setted
     * @param constructor a supplier that creates a new and clean instance of the
     *                    filter
     * @return the filter with the new value properly setted
     */
    public static <F extends SingleFilter<T>, T> F setWrapper(F filter, T value, Supplier<F> constructor) {
        if (filter == null)
            filter = constructor.get();
        filter.setValue(value);
        return (F) filter;
    }
}
