package tech.xirius.filter.jpa;

import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import tech.xirius.filter.filtering.Filter;

public interface QueryBuilder {
    <T extends Comparable<? super T>> List<Predicate> createPredicates(
            QueryBuilderProcessor processor, Expression<T> x, Filter filter);
}
