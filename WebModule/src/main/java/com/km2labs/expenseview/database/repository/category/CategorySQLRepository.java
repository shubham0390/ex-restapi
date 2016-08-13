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

package com.km2labs.expenseview.database.repository.category;

import com.km2labs.expenseview.database.entity.CategoryEntity;
import com.km2labs.expenseview.database.entity.CategoryEntity_;
import com.km2labs.expenseview.database.entity.MemberEntity_;
import com.km2labs.expenseview.database.repository.BaseRepository;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "categorySQLRepository")
public class CategorySQLRepository extends BaseRepository<CategoryEntity> implements ICategoryRepository {

    public CategorySQLRepository() {
        setClazz(CategoryEntity.class);
    }

    @Override
    public long createCategory(CategoryEntity category) {
        create(category);
        return category.getId();
    }

    @Override
    public void updateCategory(CategoryEntity category) {
        update(category);
    }

    @Override
    public CategoryEntity getCategory(long serverId) {
        return findOne(serverId);
    }

    @Override
    public void deleteCategory(long serverId) {
        deleteById(serverId);
    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        delete(categoryEntity);
    }


    @Override
    public List<CategoryEntity> getCategoriesByMember(long memberServerId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Metamodel metamodel = mEntityManager.getMetamodel();
        Root<CategoryEntity> root = query.from(metamodel.entity(CategoryEntity.class));
        query.select(root);
        query.where(builder.equal(root.get(CategoryEntity_.owner).get(MemberEntity_.id), memberServerId));
        return mEntityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteAllCategoryForMember(long memberServerId) {
        CriteriaBuilder builder = mEntityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
        Metamodel metamodel = mEntityManager.getMetamodel();
        Root<CategoryEntity> root = query.from(metamodel.entity(CategoryEntity.class));
        query.select(root);
        //TODO: Delete all category for a member
    }
}
