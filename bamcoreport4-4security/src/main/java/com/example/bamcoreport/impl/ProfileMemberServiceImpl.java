package com.example.bamcoreport.impl;

import com.example.bamcoreport.model.entity.ProfileMember;
import com.example.bamcoreport.repository.ProfileMemberRepository;
import com.example.bamcoreport.service.ProfileMemberService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileMemberServiceImpl implements ProfileMemberService {

    @Autowired
    public ProfileMemberRepository profileMemberRepository;

    @Override
    public List<ProfileMember> getAllProfileMember() {
        return profileMemberRepository.findAll();
    }

    @Override
    public ProfileMember createProfileMember(ProfileMember profileMember) {
        return profileMemberRepository.save(profileMember);
    }

    @Override
    public ProfileMember updateProfileMember(long id, ProfileMember profileMemberRequest) {

        ProfileMember profileMember = profileMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfileMember" + id));


        profileMember.setProfileId(profileMemberRequest.getProfileId());
        profileMember.setUserId(profileMemberRequest.getUserId());
        profileMember.setGroupId(profileMemberRequest.getGroupId());
        profileMember.setRoleId(profileMemberRequest.getRoleId());


        return profileMemberRepository.save(profileMember);
    }

    @Override
    public void deleteProfileMember(long id) {

        ProfileMember profileMember = profileMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProfileMember" + id));

        profileMemberRepository.delete(profileMember);
    }

    @Override
    public ProfileMember getProfileMemberById(long id) {

        Optional<ProfileMember> result = profileMemberRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("ProfileMember" + id);
        }
    }
}
