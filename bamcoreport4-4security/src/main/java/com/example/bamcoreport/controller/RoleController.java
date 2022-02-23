package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.RoleDto;
import com.example.bamcoreport.model.entity.Role;
import com.example.bamcoreport.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/role")
public class RoleController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleService roleService;


    @GetMapping
    public List<RoleDto> getAllRoles() {

        return roleService.getAllRole().stream().map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") Long id) {
        Role role = roleService.getRoleById(id);

        // convert entity to DTO
        RoleDto roleResponse = modelMapper.map(role, RoleDto.class);

        return ResponseEntity.ok().body(roleResponse);
    }

    @PostMapping
    public ResponseEntity<RoleDto> createUser(@RequestBody RoleDto roleDto) {

        // convert DTO to entity
        Role roleRequest = modelMapper.map(roleDto, Role.class);

        Role role = roleService.createRole(roleRequest);

        // convert entity to DTO
        RoleDto roleResponse = modelMapper.map(role, RoleDto.class);

        return new ResponseEntity<RoleDto>(roleResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateUser(@PathVariable long id, @RequestBody RoleDto roleDto) {

        Role roleRequest = modelMapper.map(roleDto, Role.class);
        Role role = roleService.updateRole(id, roleRequest);
        RoleDto roleResponse = modelMapper.map(role, RoleDto.class);
        return ResponseEntity.ok().body(roleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Role deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}


