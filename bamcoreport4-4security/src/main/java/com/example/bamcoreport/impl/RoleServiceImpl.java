package com.example.bamcoreport.impl;

import com.example.bamcoreport.model.entity.Role;
import com.example.bamcoreport.repository.RoleRepository;
import com.example.bamcoreport.service.RoleService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {

        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(long id, Role roleRequest) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role" + id));


        //  role.(roleRequest.);
        role.setName(roleRequest.getName());
        role.setDisplayName(roleRequest.getDisplayName());
        role.setDescription(roleRequest.getDescription());
        role.setCreatedBy(roleRequest.getCreatedBy());
        role.setCreationDate(roleRequest.getCreationDate());
        role.setLastUpdate(roleRequest.getLastUpdate());


        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role" + id));

        roleRepository.delete(role);
    }

    @Override
    public Role getRoleById(long id) {
        Optional<Role> result = roleRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Role" + id);
        }
    }
}
