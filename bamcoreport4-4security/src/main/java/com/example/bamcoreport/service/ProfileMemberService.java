package com.example.bamcoreport.service;


import com.example.bamcoreport.model.entity.ProfileMember;

import java.util.List;

public interface ProfileMemberService {
    List<ProfileMember> getAllProfileMember();

    ProfileMember createProfileMember(ProfileMember profileMember);

    ProfileMember updateProfileMember(long id, ProfileMember profileMember);

    void deleteProfileMember(long id);

    ProfileMember getProfileMemberById(long id);
}
