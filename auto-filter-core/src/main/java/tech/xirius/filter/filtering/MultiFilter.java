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

import java.util.List;

/**
 * Interface to define filters for a single field and multiple conditions, e.g.
 * myField.equals=1&myField.in=1,2&myField.isNull=false
 */
public interface MultiFilter<T> extends Filter {
    /**
     * Retrieves the list of filters that conform the {@code MultiFilter}. For example
     * if a filter has the following structure:
     *  <pre>
     *  public class MyFilter<T> implements MultiFilter<T> {
     *    private EqualsFilter<T> equals = null;
     *    private InFilter<T> in = null;
     *    private IsNullFilter isNull = null;
     *    ...
     *  }
     * 
     * </pre>
     * Then this method implementation can be:
     * <pre>
     * public List<Filter> retrieveSubFilters() {
     *    return Arrays.asList(equals, in, isNull);
     * }
     * </pre>
     * 
     * @return the list of subfilters
     */
    List<Filter> retrieveSubFilters();
}
