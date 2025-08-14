package com.mdmuntasirazad.blog.services.impl;

import com.mdmuntasirazad.blog.config.AppConstants;
import com.mdmuntasirazad.blog.entities.Role;
import com.mdmuntasirazad.blog.entities.User;
import com.mdmuntasirazad.blog.exceptions.ResourceNotFoundException;
import com.mdmuntasirazad.blog.payloads.UserDto;
import com.mdmuntasirazad.blog.repositories.RoleRepo;
import com.mdmuntasirazad.blog.repositories.UserRepo;
import com.mdmuntasirazad.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        // Encode the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        // Assign the default role (STUDENT)
        Role role = this.roleRepo.findById(AppConstants.STUDENT_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "role id", AppConstants.STUDENT_USER));
        user.getRoles().add(role);
        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        // SECURITY FIX: Only update password if a new one is provided, and always encode it.
        if (StringUtils.hasText(userDto.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        }

        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }
}