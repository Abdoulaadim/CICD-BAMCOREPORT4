package com.example.bamcoreport.service;

import com.example.bamcoreport.model.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    Role createRole(Role role);

    Role updateRole(long id, Role role);

    void deleteRole(long id);

    Role getRoleById(long id);
}
