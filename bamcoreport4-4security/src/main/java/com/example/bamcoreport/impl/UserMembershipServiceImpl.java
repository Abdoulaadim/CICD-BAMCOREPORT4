package com.example.bamcoreport.impl;

import com.example.bamcoreport.model.entity.UserMembership;
import com.example.bamcoreport.repository.UserMembershipRepository;
import com.example.bamcoreport.service.UserMembershipService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMembershipServiceImpl implements UserMembershipService {

    @Autowired
    public UserMembershipRepository userMembershipRepository;

    @Override
    public List<UserMembership> getAllUserMembership() {

        return userMembershipRepository.findAll();
    }

    @Override
    public UserMembership createUserMembership(UserMembership uerMembership) {
        return userMembershipRepository.save(uerMembership);
    }

    @Override
    public UserMembership updateUserMembership(long id, UserMembership userMembershipRequest) {

        UserMembership userMembership = userMembershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserMemberShip" + id));


        userMembership.setUserId(userMembershipRequest.getUserId());
        userMembership.setRoleId(userMembershipRequest.getRoleId());
        userMembership.setGroupId(userMembershipRequest.getGroupId());
        userMembership.setAssignedBy(userMembershipRequest.getAssignedBy());
        userMembership.setAssignedBy(userMembershipRequest.getAssignedBy());

        return userMembershipRepository.save(userMembership);
    }

    @Override
    public void deleteUserMembership(long id) {

        UserMembership userMembership = userMembershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserMemberShip" + id));

        userMembershipRepository.delete(userMembership);
    }

    @Override
    public UserMembership getUserMembershipById(long id) {
        Optional<UserMembership> result = userMembershipRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("UserMemberShip" + id);
        }
    }
}
