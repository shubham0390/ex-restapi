package com.km2labs.expenseview.database.repository;


import java.util.List;

public interface Repository<T, ID> {

    T save(T entity);

    T update(T entity);

    T findOne(ID var1);

    boolean exists(ID var1);

    List<T> findAll();

    List<T> findAll(List<ID> var1);

    void delete(ID var1);

    void delete(Iterable<ID> var1);

    void deleteAll();

}
