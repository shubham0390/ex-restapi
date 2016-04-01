package com.mmt.shubh.database.repository.category;

import com.mmt.shubh.database.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */

@Service
public interface ICategoryRepository {

    long createCategory(CategoryEntity category);

    void updateCategory(CategoryEntity category);

    CategoryEntity getCategory(long serverId);

    void deleteCategory(long serverId);

    void deleteCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> getCategoriesByMember(long memberServerId);

    void deleteAllCategoryForMember(long memberServerId);

}
