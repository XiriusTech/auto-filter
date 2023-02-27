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

import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.jpa.QueryBuilderProcessor;

/**
 * This is used iy the {@link QueryBuilderProcessor} to transform a
 * {@link Filter} into a set of JPA {@link Predicate}.
 * <p>
 * It is recommended that a QueryBuilder is only related
 * to a single {@code Filter}.
 * </p>
 */
public interface QueryBuilder {
    /**
     * Creates a List of JPA Predicates that can be used to perform queries
     * to a database.
     * 
     * @param <T>       the datatype of the field being filtered
     * @param processor the {@link QueryBuilderProcessor} that is using the
     *                  QueryBuilder
     * @param x         the expression that identifies the field(s) of the database
     * @param filter    the filter being processed
     * @return a List of Predicates
     */
    <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter);
}
