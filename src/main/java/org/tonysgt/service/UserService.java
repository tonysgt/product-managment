package org.tonysgt.service;

import org.tonysgt.dto.UpsertUserDto;
import org.tonysgt.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(Long id);

    UserDto createUser(UpsertUserDto user);

    UserDto update(Long id, UpsertUserDto user);

    void deleteUser(Long id);
}
