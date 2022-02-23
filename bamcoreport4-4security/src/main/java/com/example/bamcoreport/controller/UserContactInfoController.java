package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.UserContactInfoDto;
import com.example.bamcoreport.model.entity.UserContactInfo;
import com.example.bamcoreport.service.UserContactInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/ContactInfo")
public class UserContactInfoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserContactInfoService userContactInfoService;


    @GetMapping
    public List<UserContactInfoDto> getAlluserinfo() {

        return userContactInfoService.getAllUsersContactInfo().stream().map(userContactInfo -> modelMapper.map(userContactInfo, UserContactInfoDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserContactInfoDto> getuserById(@PathVariable(name = "id") Long id) {
        UserContactInfo userContactInfo = userContactInfoService.getUserContactInfoById(id);

        // convert entity to DTO
        UserContactInfoDto userContactInfoResponse = modelMapper.map(userContactInfo, UserContactInfoDto.class);

        return ResponseEntity.ok().body(userContactInfoResponse);
    }

    @PostMapping
    public ResponseEntity<UserContactInfoDto> createUser(@RequestBody UserContactInfoDto userContactInfoDto) {

        // convert DTO to entity
        UserContactInfo userContactInfoRequest = modelMapper.map(userContactInfoDto, UserContactInfo.class);

        UserContactInfo userContactInfo = userContactInfoService.createUserContactInfo(userContactInfoRequest);

        // convert entity to DTO
        UserContactInfoDto userResponse = modelMapper.map(userContactInfo, UserContactInfoDto.class);

        return new ResponseEntity<UserContactInfoDto>(userResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<UserContactInfoDto> updateUser(@PathVariable long id, @RequestBody UserContactInfoDto userContactInfoDto) {

        UserContactInfo userRequest = modelMapper.map(userContactInfoDto, UserContactInfo.class);
        UserContactInfo userContactInfo = userContactInfoService.updateUserContactInfo(id, userRequest);
        UserContactInfoDto userResponse = modelMapper.map(userContactInfo, UserContactInfoDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Long id) {
        userContactInfoService.deleteUserContactInfo(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "UserContactInfo deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}