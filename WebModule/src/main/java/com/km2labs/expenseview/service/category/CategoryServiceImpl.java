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

package com.km2labs.expenseview.service.category;

import com.km2labs.expenseview.database.entity.CategoryEntity;
import com.km2labs.expenseview.database.repository.category.ICategoryRepository;
import com.km2labs.expenseview.rest.model.Category;
import com.km2labs.expenseview.rest.dto.Member;
import com.km2labs.expenseview.service.converter.IEntityModelConverter;
import com.km2labs.expenseview.service.member.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */
@Component(value = "categoryServiceImpl")
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Qualifier(value = "memberServiceImpl")
    @Autowired
    private IMemberService memberService;

    @Qualifier(value = "categorySQLRepository")
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    @Qualifier("categoryEntityModelConverter")
    private IEntityModelConverter<CategoryEntity, Category> categoryEntityModelConverter;

    @Override
    public long createCategory(Category category, long memberServerId) {
        Member member = memberService.getMemberById(memberServerId);
        category.setOwner(member);
        return 0;
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.updateCategory(categoryEntityModelConverter.toEntity(category));
    }

    @Override
    public Category getCategory(long serverId) {
        CategoryEntity entity = null;
        try {
            entity = getCategoryEntity(serverId);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following  Id" + serverId).build());
        } catch (EmptyResultDataAccessException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following  Id" + serverId).build());
        }
        return categoryEntityModelConverter.toModel(entity);
    }

    @Override
    public CategoryEntity getCategoryEntity(long serverId) {
        CategoryEntity entity = null;
        try {
            entity = categoryRepository.getCategory(serverId);
        } catch (EmptyResultDataAccessException e) {
            log.info("category is not present");
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following  Id" + serverId).build());
        }
        return entity;
    }

    @Override
    public void deleteCategory(long serverId) {
        try {
            categoryRepository.deleteCategory(categoryRepository.getCategory(serverId));
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following  Id" + serverId).build());
        } catch (EmptyResultDataAccessException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following  Id" + serverId).build());
        }
    }

    @Override
    public List<Category> getCategoriesByMember(long memberServerId) {
        List<Category> categories = null;
        try {
            List<CategoryEntity> entities = categoryRepository.getCategoriesByMember(memberServerId);
            categories = (List<Category>) categoryEntityModelConverter.toModel(entities);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following member with Id" + memberServerId).build());
        } catch (EmptyResultDataAccessException e) {
            throw new WebApplicationException(Response.status(Response.Status.NO_CONTENT).entity("No category present for" +
                    " following member with Id" + memberServerId).build());
        }
        return categories;
    }

    @Override
    public void deleteAllCategoryForMember(long memberServerId) {

    }
}
