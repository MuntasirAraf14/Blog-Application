package com.mdmuntasirazad.blog.services;

import com.mdmuntasirazad.blog.payloads.UserDto;
import java.util.List;

public interface UserService {

    /**
     * Registers a new user with a default "STUDENT" role. This is for the public registration endpoint.
     */
    UserDto registerNewUser(UserDto userDto);

    /**
     * Updates an existing user's details.
     */
    UserDto updateUser(UserDto userDto, Integer userId);

    /**
     * Gets a single user by their ID.
     */
    UserDto getUserById(Integer userId);

    /**
     * Gets a list of all users. (Should be an admin-only operation).
     */
    List<UserDto> getAllUsers();

    /**
     * Deletes a user. (Should be an admin-only operation).
     */
    void deleteUser(Integer userId);
}