package org.tonysgt.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.tonysgt.dto.CreateProductDto;
import org.tonysgt.dto.ProductDto;

@Path("/product")
public interface ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto getProduct(@QueryParam("id")  Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto createProduct(CreateProductDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto updateProduct(@QueryParam("id")  Long id, CreateProductDto product);

    @DELETE
    Response deleteProduct(@QueryParam("id")  Long id);


}
