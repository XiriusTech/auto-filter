package tech.xirius.filter.jpa.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import tech.xirius.filter.filtering.Filter;
import tech.xirius.filter.jpa.QueryBuilderProcessor;
import tech.xirius.filter.jpa.builders.QueryBuilder;

public class QueryTestUtils {

    public static List<SampleEntity> queryEntity(SampleEntityFilter filter) throws Exception {
        List<SampleEntity> result = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        try (QueryWrapper<SampleEntity> query = new QueryWrapper<>(SampleEntity.class)) {
            Root<SampleEntity> root = query.getRoot();
            QueryBuilderProcessor processor = new QueryBuilderProcessor(query.getCriteriaBuilder());
            predicates.addAll(processor.createPredicates(root.get(SampleEntity_.id), filter.getId()));
            predicates.addAll(processor.createPredicates(root.get(SampleEntity_.name), filter.getName()));
            result = query.getResult(predicates);
        }
        return result;
    }

    public static <Y extends Comparable<? super Y>> List<SampleEntity> queryEntity(QueryBuilder queryBuilder,
            SingularAttribute<? super SampleEntity, Y> attribute, Filter filter) throws Exception {
        List<SampleEntity> result = new ArrayList<>();
        List<Predicate> predicates = new ArrayList<>();
        try (QueryWrapper<SampleEntity> query = new QueryWrapper<>(SampleEntity.class)) {
            Root<SampleEntity> root = query.getRoot();
            QueryBuilderProcessor processor = new QueryBuilderProcessor(query.getCriteriaBuilder());
            List<Predicate> partialPredicates = queryBuilder.createPredicates(processor, root.get(attribute), filter);
            if (partialPredicates != null)
                predicates.addAll(partialPredicates);

            result = query.getResult(predicates);
        }
        return result;
    }

    public static void insertData() throws Exception {
        try (QueryWrapper<SampleEntity> query = new QueryWrapper<>(SampleEntity.class)) {
            EntityManager entityManager = query.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(new SampleEntity("mark", 1));
            entityManager.persist(new SampleEntity("adam", 2));
            entityManager.persist(new SampleEntity("marie", 3));
            entityManager.persist(new SampleEntity("peter", 4));
            entityManager.persist(new SampleEntity("mark", 5));
            entityManager.persist(new SampleEntity(null, 6));
            entityManager.getTransaction().commit();
        }
    }

    public static void deleteData() throws Exception {
        try (QueryWrapper<SampleEntity> query = new QueryWrapper<>(SampleEntity.class)) {
            EntityManager entityManager = query.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaDelete<SampleEntity> delete = criteriaBuilder.createCriteriaDelete(SampleEntity.class);
            delete.from(SampleEntity.class);
            entityManager.createQuery(delete).executeUpdate();

            entityManager.getTransaction().commit();
        }
    }
}
