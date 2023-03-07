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
package tech.xirius.filter.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.jpa.builders.QueryBuilder;

/**
 * Class that coordinates a set of {@link QueryBuilder} to transform
 * a {@link Filter} into a full database Query that is compatible with JPA.
 * 
 * You can use this method in conjunction with a Spring Specification and the
 * JPA static metamodel like so:
 * <pre>
 * 
 * {@literal @Override} <b></b>
 * public Predicate toPredicate(Root&lt;Person&gt; root, CriteriaQuery&lt;?&gt; query, CriteriaBuilder criteriaBuilder) {
 *    List&lt;Predicate&gt; listP = new ArrayList&lt;&gt;();
 *    QueryBuilderProcessor processor = new QueryBuilderProcessor(criteriaBuilder);
 *    listP.addAll(processor.createPredicates(root.get(Person_.id), filter.getId()));
 *    return criteriaBuilder.and(listP.toArray(new Predicate[0]));
 * }
 * 
 * </pre>
 */
public class QueryBuilderProcessor {
    private static Map<Class<?>, QueryBuilder> builders = new HashMap<>();
    private CriteriaBuilder criteriaBuilder;

    static {
        QueryBuilderLoader.loadQueryBuilders();
    }

    public QueryBuilderProcessor(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    /**
     * Adds a {@link QueryBuilder} to the register
     * 
     * @param builder the builder to be registered
     */
    public static void registerQueryBuilder(QueryBuilder builder) {
        builders.put(builder.getClass(), builder);
    }


    /**
     * Transforms an {@link Expression} and {@link Filter} into a list of {@link Predicate}
     * that can be used to perform a database query.
     * 
     * @param <T>    the type of the {@code Expression}
     * @param x      the expression
     * @param filter the filter that is going to be transformed
     * @return a list of {@code Predicate} that are equivalent to the {@code filter} applied to the expression {@code x}
     */
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            Expression<T> x, Filter filter) {
        List<Predicate> predicates = new ArrayList<>();
        
        if (filter == null)
            return predicates;

        for (QueryBuilder builder : builders.values()) {
            List<Predicate> partialPredicates = builder.createPredicates(this, x, filter);
            if (partialPredicates != null)
                predicates.addAll(partialPredicates);
        }
        return predicates;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }
}
