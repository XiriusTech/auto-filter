package tech.xirius.filter.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.LessThanFilter;

@AutoService(QueryBuilder.class)
public class LessThanQueryBuilder implements QueryBuilder {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter) {
        if (filter instanceof LessThanFilter) {
            return Arrays.asList(processor.getCriteriaBuilder().lessThan(x, ((LessThanFilter<T>) filter).getValue()));
        }
        return null;
    }
}
