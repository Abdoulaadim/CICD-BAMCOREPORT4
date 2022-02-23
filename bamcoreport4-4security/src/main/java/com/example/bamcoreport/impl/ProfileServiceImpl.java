package com.example.bamcoreport.impl;


import com.example.bamcoreport.model.entity.Profile;
import com.example.bamcoreport.repository.ProfileRepository;
import com.example.bamcoreport.service.ProfileService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    public ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfile() {

        return profileRepository.findAll();
    }

    @Override
    public Profile createProfile(Profile profile) {

        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(long id, Profile profileRequest) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile" + id));


        profile.setIsDefault(profileRequest.getIsDefault());
        profile.setName(profileRequest.getName());
        profile.setDescription(profileRequest.getDescription());
        profile.setCreationDate(profileRequest.getCreationDate());
        profile.setCreatedBy(profileRequest.getCreatedBy());
        profile.setLastUpdateDate(profileRequest.getLastUpdateDate());
        profile.setLastUpdateBy(profileRequest.getLastUpdateBy());

        return profileRepository.save(profile);


    }

    @Override
    public void deleteProfile(long id) {

        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile" + id));

        profileRepository.delete(profile);
    }

    @Override
    public Profile getProfileById(long id) {

        Optional<Profile> result = profileRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Profile" + id);
        }
    }
}
