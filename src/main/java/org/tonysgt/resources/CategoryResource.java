package org.tonysgt.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.dto.CategoryDto;

import java.util.List;

@Path("/api/category")
public interface CategoryResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDto> getAllCategories();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto getCategory(@QueryParam("id")  Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto addCategory(CategoryDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto updateCategory(@QueryParam("id")  Long id, CategoryDto product);

    @DELETE
    void deleteCategory(@QueryParam("id")  Long id);

}
