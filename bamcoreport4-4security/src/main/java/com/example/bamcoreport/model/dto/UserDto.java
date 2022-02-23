package com.example.bamcoreport.model.dto;


import com.example.bamcoreport.model.entity.User;
import com.example.bamcoreport.model.entity.UserContactInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private long id;
    private UserContactInfo userContactInfo;
    private Boolean enabled;
    private String username;
    private String password;
    private String newpassword;
    private String firstname;
    private String lastname;
    private String title;
    private String jobTitle;
    private User managerUserId;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;


}