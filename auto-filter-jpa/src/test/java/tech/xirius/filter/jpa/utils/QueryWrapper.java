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
package tech.xirius.filter.jpa.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Helper class to test JPA queries
 */
public class QueryWrapper<T> implements AutoCloseable {
    public static final String ENTITY_MANAGER_UNIT = "test";

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> query;
    private Root<T> root;

    public QueryWrapper(Class<T> clazz) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ENTITY_MANAGER_UNIT);
        entityManager = emf.createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        query = criteriaBuilder.createQuery(clazz);
        root = query.from(clazz);
    }

    @Override
    public void close() throws Exception {
        if (entityManager != null) {
            entityManager.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public CriteriaQuery<T> getQuery() {
        return query;
    }

    public void setQuery(CriteriaQuery<T> query) {
        this.query = query;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    public List<T> getResult(Predicate predicate) {
        query.select(root).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }

    public List<T> getResult(List<Predicate> predicates) {
        return getResult(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
    }
}
