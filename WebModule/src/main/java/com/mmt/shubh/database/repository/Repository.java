package com.mmt.shubh.database.repository;


import java.util.List;

public interface Repository<T, ID> {

    <S extends T> S save(S var1);

    <S extends T> Iterable<S> save(Iterable<S> var1);

    T findOne(ID var1);

    boolean exists(ID var1);

    List<T> findAll();

    List<T> findAll(List<ID> var1);

    void delete(ID var1);

    void delete(Iterable<? extends ID> var1);

    void deleteAll();

}
