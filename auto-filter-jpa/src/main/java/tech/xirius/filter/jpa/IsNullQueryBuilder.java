package tech.xirius.filter.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.google.auto.service.AutoService;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.filtering.IsNullFilter;

@AutoService(QueryBuilder.class)
public class IsNullQueryBuilder implements QueryBuilder {
    @Override
    public <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter) {
        if (filter instanceof IsNullFilter) {
            Boolean isNull = ((IsNullFilter) filter).getValue();
            if (isNull == Boolean.TRUE) {
                return Arrays.asList(processor.getCriteriaBuilder().isNull(x));
            } else if (isNull == Boolean.FALSE) {
                return Arrays.asList(processor.getCriteriaBuilder().isNotNull(x));
            }
        }
        return null;
    }
}
