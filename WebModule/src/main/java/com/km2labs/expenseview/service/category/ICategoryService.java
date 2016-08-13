package com.km2labs.expenseview.service.category;

import com.km2labs.expenseview.database.entity.CategoryEntity;
import com.km2labs.expenseview.rest.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */

@Service
public interface ICategoryService {

    long createCategory(Category category, long memberServerId);

    void updateCategory(Category category);

    Category getCategory(long serverId);

    CategoryEntity getCategoryEntity(long serverId);

    void deleteCategory(long serverId);

    List<Category> getCategoriesByMember(long memberServerId);

    void deleteAllCategoryForMember(long memberServerId);

}
