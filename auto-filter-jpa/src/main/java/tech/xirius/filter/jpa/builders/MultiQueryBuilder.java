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
package tech.xirius.filter.jpa.builders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.MultiFilter;
import tech.xirius.filter.jpa.QueryBuilderProcessor;

/** 
 * {@link QueryBuilder} for the {@link MultiFilter}
*/
@AutoService(QueryBuilder.class)
public class MultiQueryBuilder implements QueryBuilder {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter) {
        if (filter instanceof MultiFilter) {
            List<Predicate> predicates = new ArrayList<>();
            for (Filter subFilter : ((MultiFilter<T>) filter).retrieveSubFilters()) {
                predicates.addAll(processor.createPredicates(x, subFilter));
            }
            return predicates;
        }
        return null;
    }
}
