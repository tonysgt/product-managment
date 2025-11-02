package org.tonysgt.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.tonysgt.dto.UpsertUserDto;
import org.tonysgt.dto.UserDto;
import org.tonysgt.service.UserService;

import java.util.List;

@ApplicationScoped
public class UserResourceImpl implements UserResource {

    @Inject
    UserService userService;

    @Override
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @Override
    public UserDto getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public UserDto addUser(UpsertUserDto user) {
        return userService.createUser(user);
    }

    @Override
    public UserDto updateUser(Long id, UpsertUserDto user) {
        return userService.update(id, user);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

}
