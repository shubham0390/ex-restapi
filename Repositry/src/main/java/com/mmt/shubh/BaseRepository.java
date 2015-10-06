package com.mmt.shubh;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.*;

public abstract class BaseRepository<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) throws EntityNotFoundException {
        T obj = entityManager.find(clazz, id);
        if (obj == null) {
            throw new EntityNotFoundException("unable to locate data with given id ");
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() throws EntityNotFoundException {
        List<T> objList = entityManager.createQuery("from " + clazz.getName()).getResultList();
        return objList;
    }

    public void create(final T entity) throws EntityExistsException, IllegalArgumentException,
            TransactionRequiredException {
        entityManager.persist(entity);
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public T findByColumnNameAndStringValue(SingularAttribute<T, String> attribute, String value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndIntValue(SingularAttribute<T, Integer> attribute, int value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndLongValue(SingularAttribute<T, Long> attribute, Long value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndStringValue(CollectionAttribute<T, String> attribute, String value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndStringValue(ListAttribute<T, String> attribute, String value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndStringValue(SetAttribute<T, String> attribute, String value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndStringValue(MapAttribute<T, Object, Object> attribute, String value) {
        CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = entityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return entityManager.createQuery(q).getSingleResult();
    }

}