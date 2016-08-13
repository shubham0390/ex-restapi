/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

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
