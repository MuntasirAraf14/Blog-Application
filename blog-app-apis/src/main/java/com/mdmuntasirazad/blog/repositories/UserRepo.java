package com.mdmuntasirazad.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdmuntasirazad.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
