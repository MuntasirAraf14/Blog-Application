package com.mdmuntasirazad.blog.repositories;

import com.mdmuntasirazad.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    
    @Modifying
    @Transactional
    @Query(value = "INSERT IGNORE INTO role (id, name) VALUES (:id, :name)", nativeQuery = true)
    void insertIgnoreRole(@Param("id") Integer id, @Param("name") String name);
}