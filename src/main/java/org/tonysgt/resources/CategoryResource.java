package org.tonysgt.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;

import java.util.List;

@Path("/api/categories")
public interface CategoryResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> getAllCategories();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto getCategory(@QueryParam("id")  Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto addCategory(AddProductDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto updateCategory(@QueryParam("id")  Long id, AddProductDto product);

    @DELETE
    void deleteCategory(@QueryParam("id")  Long id);

}
