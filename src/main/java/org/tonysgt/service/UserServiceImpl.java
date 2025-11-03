package org.tonysgt.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.tonysgt.dto.UpsertUserDto;
import org.tonysgt.dto.UserDto;
import org.tonysgt.entities.User;
import org.tonysgt.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepo;

    @Override
    public List<UserDto> getUsers() {
        return userRepo.findAll().stream().map(this::getUserDto).toList();
    }

    @Override
    public UserDto getUser(Long id) {
        return userRepo.findByIdOptional(id)
                .map(this::getUserDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    @Override
    @Transactional
    public UserDto createUser(UpsertUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setPassword(BcryptUtil.bcryptHash(createUserDto.getPassword()));
        user.setEmail(createUserDto.getEmail());
        user.setRole(createUserDto.getRole());
        userRepo.persist(user);
        return userRepo.find("username=?1", createUserDto.getUsername()).firstResultOptional().map(this::getUserDto).orElseThrow(() -> new EntityNotFoundException("User not found: " + createUserDto.getUsername()));
    }

    @Override
    @Transactional
    public UserDto update(Long id, UpsertUserDto upsertUserDto) {
        User user = userRepo.findByIdOptional(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        user.setUsername(upsertUserDto.getUsername());
        user.setEmail(upsertUserDto.getEmail());
        user.setRole(upsertUserDto.getRole());
        userRepo.persist(user);
        return userRepo.find("username=?1", upsertUserDto.getUsername()).firstResultOptional().map(this::getUserDto).orElseThrow(() -> new EntityNotFoundException("User not found: " + upsertUserDto.getUsername()));

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.id);
        userDto.setUsername(userDto.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
