package com.mmt.shubh.service.converter;

import org.springframework.stereotype.Service;

import java.util.List;

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

    List<E> toEntity(List<M> m);

    List<M> toModel(List<E> e);

}
