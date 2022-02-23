package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.UserMembershipDto;
import com.example.bamcoreport.model.entity.UserMembership;
import com.example.bamcoreport.service.UserMembershipService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/usermembership")
public class UserMemberShipController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserMembershipService userMembershipService;

    @GetMapping
    public List<UserMembershipDto> getAllusersMembreShip() {

        return userMembershipService.getAllUserMembership().stream().map(user -> modelMapper.map(user, UserMembershipDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMembershipDto> getuserMembreShipById(@PathVariable(name = "id") Long id) {
        UserMembership userMembership = userMembershipService.getUserMembershipById(id);

        // convert entity to DTO
        UserMembershipDto userMembreShipResponse = modelMapper.map(userMembership, UserMembershipDto.class);

        return ResponseEntity.ok().body(userMembreShipResponse);
    }

    @PostMapping
    public ResponseEntity<UserMembershipDto> createUserMembreShip(@RequestBody UserMembershipDto userMembershipDto) {

        // convert DTO to entity
        UserMembership userMembreShipRequest = modelMapper.map(userMembershipDto, UserMembership.class);

        UserMembership userMembership = userMembershipService.createUserMembership(userMembreShipRequest);

        // convert entity to DTO
        UserMembershipDto userMembreShipResponse = modelMapper.map(userMembership, UserMembershipDto.class);

        return new ResponseEntity<UserMembershipDto>(userMembreShipResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<UserMembershipDto> updateUserMembreShip(@PathVariable long id, @RequestBody UserMembershipDto userMembershipDto) {

        UserMembership userMembreShipRequest = modelMapper.map(userMembershipDto, UserMembership.class);
        UserMembership userMembership = userMembershipService.updateUserMembership(id, userMembreShipRequest);
        UserMembershipDto userMembreShipResponse = modelMapper.map(userMembership, UserMembershipDto.class);
        return ResponseEntity.ok().body(userMembreShipResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserMembreShip(@PathVariable(name = "id") Long id) {
        userMembershipService.deleteUserMembership(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "User deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
