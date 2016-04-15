package com.mmt.shubh.service.converter;

import com.mmt.shubh.database.entity.CategoryEntity;
import com.mmt.shubh.rest.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Subham Tyagi
 * On 10/20/2015.
 * <p>
 * TODO: Add class comments
 */
@Component
public class CategoryEntityModelConverter implements IEntityModelConverter<CategoryEntity, Category> {

    @Override
    public CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setType(category.getType());
        categoryEntity.setName(category.getName());
        return categoryEntity;
    }

    @Override
    public Category toModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setType(categoryEntity.getType());
        return category;
    }

    @Override
    public List<CategoryEntity> toEntity(List<Category> m) {
        return m.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Category> toModel(List<CategoryEntity> e) {
        return e.stream().map(this::toModel).collect(Collectors.toList());
    }
}
