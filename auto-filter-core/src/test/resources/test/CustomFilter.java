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

import java.util.Arrays;
import java.util.List;

import tech.xirius.filter.filtering.EqualsFilter;
import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.MultiFilter;

public class CustomFilter<T> implements MultiFilter<T> {
    private EqualsFilter<T> equals;

    @Override
    public List<Filter> retrieveSubFilters() {
        return Arrays.asList(equals);
    }
    
}
