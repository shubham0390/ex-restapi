package com.km2labs.expenseview.service.converter;

import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
public interface IEntityModelConverter<E, M> {

    E toEntity(M m);

    M toModel(E e);

    Collection<E> toEntity(Collection<M> m);

    Collection<M> toModel(Collection<E> e);

}
