package tech.xirius.filter.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.GreaterThanFilter;

@AutoService(QueryBuilder.class)
public class GreaterThanQueryFilter implements QueryBuilder {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter) {
        if (filter instanceof GreaterThanFilter) {
            return Arrays.asList(processor.getCriteriaBuilder().greaterThan(x, ((GreaterThanFilter<T>) filter).getValue()));
        }
        return null;
    }
}
