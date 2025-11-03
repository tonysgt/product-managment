package org.tonysgt.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.CategoryDto;
import org.tonysgt.dto.ProductDto;

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
