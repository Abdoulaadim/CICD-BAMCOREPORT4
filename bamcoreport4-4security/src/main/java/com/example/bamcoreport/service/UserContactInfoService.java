package com.example.bamcoreport.service;


import com.example.bamcoreport.model.entity.UserContactInfo;

import java.util.List;

public interface UserContactInfoService {
    List<UserContactInfo> getAllUsersContactInfo();

    UserContactInfo createUserContactInfo(UserContactInfo userContactInfo);

    UserContactInfo updateUserContactInfo(long id, UserContactInfo userContactInfo);

    void deleteUserContactInfo(long id);

    UserContactInfo getUserContactInfoById(long id);
}
