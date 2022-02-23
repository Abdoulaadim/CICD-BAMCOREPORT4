package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.GroupDto;
import com.example.bamcoreport.model.entity.Group;
import com.example.bamcoreport.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/group")
public class GroupController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GroupService groupService;


    @GetMapping
    public List<GroupDto> getAllGroup() {

        return groupService.getAllGroup().stream().map(group -> modelMapper.map(group, GroupDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable(name = "id") Long id) {
        Group group = groupService.getGroupById(id);

        // convert entity to DTO
        GroupDto groupResponse = modelMapper.map(group, GroupDto.class);

        return ResponseEntity.ok().body(groupResponse);
    }

    @PostMapping
    public ResponseEntity<GroupDto> createUser(@RequestBody GroupDto groupDto) {

        // convert DTO to entity
        Group groupRequest = modelMapper.map(groupDto, Group.class);

        Group group = groupService.createGroup(groupRequest);

        // convert entity to DTO
        GroupDto groupResponse = modelMapper.map(group, GroupDto.class);

        return new ResponseEntity<GroupDto>(groupResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateUser(@PathVariable long id, @RequestBody GroupDto groupDto) {

        Group groupRequest = modelMapper.map(groupDto, Group.class);
        Group group = groupService.updateGroup(id, groupRequest);
        GroupDto groupResponse = modelMapper.map(group, GroupDto.class);
        return ResponseEntity.ok().body(groupResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Long id) {
        groupService.deleteGroup(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Group deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}

