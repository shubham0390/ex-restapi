package com.km2labs.expenseview.rest.resources.category;

import com.km2labs.expenseview.rest.model.Category;
import com.km2labs.expenseview.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */

@Component
public class CategoryResourceImpl implements ICategoryResource {

    @Qualifier(value = "categoryServiceImpl")
    @Autowired
    private ICategoryService mCategoryService;

    @Override
    public long createCategory(Category category, long memberServerId) {
        return mCategoryService.createCategory(category, memberServerId);
    }

    @Override
    public void updateCategory(Category category) {
        mCategoryService.updateCategory(category);
    }

    @Override
    public Category getCategory(long serverId) {
        //return mCategoryService.getCategory(serverId);
        Category category
                 =  new Category();
        category.setId(123456789);
        category.setName("sakjdfhksdjhfv");
        category.setType("dkcksjdnvndfkv");
        return category;
    }

    @Override
    public void deleteCategory(long serverId) {
        mCategoryService.deleteCategory(serverId);
    }

    @Override
    public List<Category> getCategoriesByMember(long memberServerId) {
        return mCategoryService.getCategoriesByMember(memberServerId);
    }

    @Override
    public void deleteAllCategoryForMember(long memberServerId) {
        mCategoryService.deleteAllCategoryForMember(memberServerId);
    }
}
