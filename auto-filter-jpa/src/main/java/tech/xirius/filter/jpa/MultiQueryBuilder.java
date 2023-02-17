package tech.xirius.filter.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.MultiFilter;

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
