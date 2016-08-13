package com.km2labs.expenseview.service.converter;

import com.km2labs.expenseview.database.entity.CategoryEntity;
import com.km2labs.expenseview.rest.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public Collection<CategoryEntity> toEntity(Collection<Category> m) {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        m.forEach(category -> {
            categoryEntities.add(toEntity(category));
        });
        return categoryEntities;
    }

    @Override
    public Collection<Category> toModel(Collection<CategoryEntity> e) {
        List<Category> categories = new ArrayList<>();
        e.forEach(categoryEntity -> {
            categories.add(toModel(categoryEntity));
        });
        return categories;
    }
}
