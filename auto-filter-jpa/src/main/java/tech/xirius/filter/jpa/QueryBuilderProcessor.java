package tech.xirius.filter.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import tech.xirius.filter.filtering.Filter;

public class QueryBuilderProcessor {
    private static Map<Class<?>, QueryBuilder> builders = new HashMap<>();
    private CriteriaBuilder criteriaBuilder;

    static {
        QueryBuilderLoader.loadQueryBuilders();
    }

    public QueryBuilderProcessor(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public static void registerQueryBuilder(QueryBuilder builder) {
        builders.put(builder.getClass(), builder);
    }

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
