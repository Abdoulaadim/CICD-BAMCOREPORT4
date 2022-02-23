package com.example.bamcoreport.service;

import com.example.bamcoreport.model.entity.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllProfile();

    Profile createProfile(Profile profile);

    Profile updateProfile(long id, Profile profile);

    void deleteProfile(long id);

    Profile getProfileById(long id);
}
