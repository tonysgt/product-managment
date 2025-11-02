package org.tonysgt.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.tonysgt.dto.UpsertUserDto;
import org.tonysgt.dto.UserDto;

import java.util.List;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
public interface UserResource {

    @GET
    @Path("/all")

    @RolesAllowed("admin")
    List<UserDto> getUsers();

    @GET
    @RolesAllowed("admin")
    UserDto getUser(@QueryParam("id")  Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    UserDto addUser(UpsertUserDto user) ;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    UserDto updateUser(@QueryParam("id")  Long id, UpsertUserDto user);

    @DELETE
    @RolesAllowed("admin")
    void deleteUser(@QueryParam("id")  Long id);

}
