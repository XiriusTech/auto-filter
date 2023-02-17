package tech.xirius.filter.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.EqualsFilter;
import tech.xirius.filter.filtering.Filter;

@AutoService(QueryBuilder.class)
public class EqualQueryBuilder implements QueryBuilder {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter) {
        if (filter instanceof EqualsFilter) {
            return Arrays.asList(processor.getCriteriaBuilder().equal(x, ((EqualsFilter<T>) filter).getValue()));
        }
        return null;
    }
}
