package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.ProfileMemberDto;
import com.example.bamcoreport.model.entity.ProfileMember;
import com.example.bamcoreport.service.ProfileMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/profilemember")
public class ProfileMemberController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProfileMemberService profileMemberService;


    @GetMapping
    public List<ProfileMemberDto> getAllProfileMember() {

        return profileMemberService.getAllProfileMember().stream().map(profileMember -> modelMapper.map(profileMember, ProfileMemberDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileMemberDto> getProfileMemberById(@PathVariable(name = "id") Long id) {
        ProfileMember profileMember = profileMemberService.getProfileMemberById(id);

        // convert entity to DTO
        ProfileMemberDto profilMemberResponse = modelMapper.map(profileMember, ProfileMemberDto.class);

        return ResponseEntity.ok().body(profilMemberResponse);
    }

    @PostMapping
    public ResponseEntity<ProfileMemberDto> createProfileMember(@RequestBody ProfileMemberDto profileMemberDto) {

        // convert DTO to entity
        ProfileMember profileMemberRequest = modelMapper.map(profileMemberDto, ProfileMember.class);

        ProfileMember profileMember = profileMemberService.createProfileMember(profileMemberRequest);

        // convert entity to DTO
        ProfileMemberDto profileMemberResponse = modelMapper.map(profileMember, ProfileMemberDto.class);

        return new ResponseEntity<ProfileMemberDto>(profileMemberResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<ProfileMemberDto> updateProfileMember(@PathVariable long id, @RequestBody ProfileMemberDto profileMemberDto) {

        ProfileMember profileMemberRequest = modelMapper.map(profileMemberDto, ProfileMember.class);
        ProfileMember profileMember = profileMemberService.updateProfileMember(id, profileMemberRequest);
        ProfileMemberDto profileMemberResponse = modelMapper.map(profileMember, ProfileMemberDto.class);
        return ResponseEntity.ok().body(profileMemberResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProfileMember(@PathVariable(name = "id") Long id) {
        profileMemberService.deleteProfileMember(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "ProfileMember deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
