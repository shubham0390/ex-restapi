package com.mmt.shubh.service.converter;

import org.springframework.stereotype.Service;

/**
 * Created by Subham Tyagi
 * On 6/6/2015.
 * <p>
 * TODO: Add class comments
 */
@Service
public interface EntityModelConverter<E, M> {

    E toEntity(M m);

    M toModel(E e);
}
