package com.mmt.shubh.service.category;

import com.mmt.shubh.entity.CategoryEntity;
import com.mmt.shubh.rest.model.Category;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
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
