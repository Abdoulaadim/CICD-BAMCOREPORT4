package com.example.bamcoreport.repository;

import com.example.bamcoreport.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select role.name from role,usermembership where userid=:id and usermembership.roleid=role.id", nativeQuery = true)
    String findrole(@Param("id") long id);

}
