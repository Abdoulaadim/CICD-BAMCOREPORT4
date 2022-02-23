package com.example.bamcoreport.impl;

import com.example.bamcoreport.model.entity.UserContactInfo;
import com.example.bamcoreport.repository.UserContactInfoRepository;
import com.example.bamcoreport.service.UserContactInfoService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserContactInfoServiceImpl implements UserContactInfoService {

    @Autowired
    public UserContactInfoRepository userContactInfoRepository;

    @Override
    public List<UserContactInfo> getAllUsersContactInfo() {
        return userContactInfoRepository.findAll();
    }

    @Override
    public UserContactInfo createUserContactInfo(UserContactInfo userContactInfo) {
        return userContactInfoRepository.save(userContactInfo);
    }

    @Override
    public UserContactInfo updateUserContactInfo(long id, UserContactInfo userContactInfoRequest) {
        UserContactInfo userContactInfo = userContactInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("userContactInfo" + id));


        userContactInfo.setUserId(userContactInfoRequest.getUserId());
        userContactInfo.setEmail(userContactInfoRequest.getEmail());
        userContactInfo.setPhone(userContactInfoRequest.getPhone());
        userContactInfo.setMobile(userContactInfoRequest.getMobile());
        userContactInfo.setFax(userContactInfoRequest.getFax());
        userContactInfo.setBuilding(userContactInfoRequest.getBuilding());
        userContactInfo.setRoom(userContactInfoRequest.getRoom());
        userContactInfo.setAddress(userContactInfoRequest.getAddress());
        userContactInfo.setZipCode(userContactInfoRequest.getZipCode());
        userContactInfo.setCity(userContactInfoRequest.getCity());
        userContactInfo.setState(userContactInfoRequest.getState());
        userContactInfo.setCountry(userContactInfoRequest.getCountry());
        userContactInfo.setWebsite(userContactInfoRequest.getWebsite());
        userContactInfo.setPersonal(userContactInfoRequest.getPersonal());
        return userContactInfoRepository.save(userContactInfo);
    }

    @Override
    public void deleteUserContactInfo(long id) {
        UserContactInfo userContactInfo = userContactInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("userContactInfo" + id));

        userContactInfoRepository.delete(userContactInfo);
    }

    @Override
    public UserContactInfo getUserContactInfoById(long id) {
        Optional<UserContactInfo> result = userContactInfoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("userContactInfo" + id);
        }
    }
}
