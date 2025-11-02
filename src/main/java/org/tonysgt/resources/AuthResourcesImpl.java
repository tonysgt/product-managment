package org.tonysgt.resources;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.tonysgt.dto.UserLoginDto;
import org.tonysgt.entities.User;
import org.tonysgt.security.TokenService;

public class AuthResourcesImpl implements AuthResources {

    @Inject
    TokenService service;

    @Override
    public String login(UserLoginDto userLoginDto) {
        User existingUser = User.find("username", userLoginDto.getUsername()).firstResult();

        if(existingUser == null || ! BcryptUtil.matches(userLoginDto.getPassword(), existingUser.getPassword())) {
            throw new WebApplicationException(Response.status(404).entity("No user found or password is incorrect").build());
        }
        return service.generateUserToken(existingUser.getEmail(), existingUser.getUsername(), existingUser.getRole());
    }
}
