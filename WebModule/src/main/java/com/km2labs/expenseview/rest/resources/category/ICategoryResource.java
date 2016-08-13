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
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Subham Tyagi
 * On 10/19/2015.
 * <p>
 * TODO: Add class comments
 */
@Path("/category")
@Service
@Produces("application/json")
@Consumes("application/json")
public interface ICategoryResource {

    @POST
    long createCategory(Category category, @QueryParam("memberServerId") long memberServerId);

    @PUT
    void updateCategory(Category category);

    @GET
    Category getCategory(@QueryParam("serverId")long serverId);

    @DELETE
    void deleteCategory(@QueryParam("categoryServerId") long serverId);

    @GET
    @Path("/member")
    List<Category> getCategoriesByMember(@QueryParam("memberServerId") long memberServerId);

    @DELETE
    @Path("/member")
    void deleteAllCategoryForMember(@QueryParam("memberServerId") long memberServerId);

}
