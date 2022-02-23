package com.example.bamcoreport.controller;

import com.example.bamcoreport.model.dto.ApiResponse;
import com.example.bamcoreport.model.dto.ProfileDto;
import com.example.bamcoreport.model.entity.Profile;
import com.example.bamcoreport.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/profile")
public class ProfileController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProfileService profileService;


    @GetMapping
    public List<ProfileDto> getAllProfile() {

        return profileService.getAllProfile().stream().map(profile -> modelMapper.map(profile, ProfileDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable(name = "id") Long id) {
        Profile profile = profileService.getProfileById(id);

        // convert entity to DTO
        ProfileDto profilResponse = modelMapper.map(profile, ProfileDto.class);

        return ResponseEntity.ok().body(profilResponse);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {

        // convert DTO to entity
        Profile profileRequest = modelMapper.map(profileDto, Profile.class);

        Profile profile = profileService.createProfile(profileRequest);

        // convert entity to DTO
        ProfileDto profileResponse = modelMapper.map(profile, ProfileDto.class);

        return new ResponseEntity<ProfileDto>(profileResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable long id, @RequestBody ProfileDto profileDto) {

        Profile profileRequest = modelMapper.map(profileDto, Profile.class);
        Profile profile = profileService.updateProfile(id, profileRequest);
        ProfileDto profileResponse = modelMapper.map(profile, ProfileDto.class);
        return ResponseEntity.ok().body(profileResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProfile(@PathVariable(name = "id") Long id) {
        profileService.deleteProfile(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Profile deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}

