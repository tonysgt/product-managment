package org.tonysgt.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.dto.AddProductDto;
import org.tonysgt.dto.ProductDto;

import java.util.List;

@Path("/api/product")
public interface ProductResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    List<ProductDto> getProducts();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","user"})
    ProductDto getProduct(@QueryParam("id")  Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    ProductDto addProduct(AddProductDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    ProductDto updateProduct(@QueryParam("id")  Long id, AddProductDto product);

    @DELETE
    @RolesAllowed("admin")
    void deleteProduct(@QueryParam("id")  Long id);

}
