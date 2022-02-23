package com.example.bamcoreport.service;

import com.example.bamcoreport.model.entity.UserMembership;

import java.util.List;

public interface UserMembershipService {

    List<UserMembership> getAllUserMembership();

    UserMembership createUserMembership(UserMembership uerMembership);

    UserMembership updateUserMembership(long id, UserMembership userMembership);

    void deleteUserMembership(long id);

    UserMembership getUserMembershipById(long id);
}
