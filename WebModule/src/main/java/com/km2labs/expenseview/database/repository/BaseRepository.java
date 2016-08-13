package com.km2labs.expenseview.database.repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;

public abstract class BaseRepository<T extends Serializable> {

    @PersistenceContext
    protected EntityManager mEntityManager;
    protected Class<T> clazz;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final long id) throws EntityNotFoundException {
        T obj = mEntityManager.find(clazz, id);
        if (obj == null) {
            throw new EntityNotFoundException("unable to locate data with given id ");
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() throws EntityNotFoundException {
        return (List<T>) mEntityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T create(final T entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
        mEntityManager.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return mEntityManager.merge(entity);
    }

    public void delete(final T entity) {
        mEntityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public T findByColumnNameAndValue(SingularAttribute<T, String> attribute, String value)
            throws NoResultException, NonUniqueResultException {
        CriteriaBuilder queryBuilder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = mEntityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return mEntityManager.createQuery(q).getSingleResult();
    }

    public T findByColumnNameAndValue(SingularAttribute<T, Long> attribute, Long value)
            throws NoResultException, NonUniqueResultException {
        CriteriaBuilder queryBuilder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<T> q = queryBuilder.createQuery(clazz);
        Metamodel m = mEntityManager.getMetamodel();
        EntityType<T> Pet_ = m.entity(clazz);
        Root<T> c = q.from(Pet_);
        q.select(c);
        q.where(queryBuilder.equal(c.get(attribute), value));
        return mEntityManager.createQuery(q).getSingleResult();
    }
}