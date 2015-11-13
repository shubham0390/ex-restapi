package com.mmt.shubh.rest.resources.category;

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
