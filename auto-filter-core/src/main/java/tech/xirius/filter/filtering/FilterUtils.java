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

import java.util.function.Supplier;

public class FilterUtils {
    public static <T> T getWrapper(SingleFilter<T> filter) {
        return filter != null ? filter.getValue() : null;
    }

    public static <F extends SingleFilter<T>, T> F setWrapper(F filter, T value, Supplier<F> constructor) {
        if (filter == null)
            filter = constructor.get();
        filter.setValue(value);
        return (F) filter;
    }
}
