package com.example.bamcoreport.model.dto;


import com.example.bamcoreport.model.entity.User;
import lombok.Data;

@Data

public class UserContactInfoDto {

    private long id;
    private User userId;
    private String email;
    private String phone;
    private String mobile;
    private String fax;
    private String building;
    private String room;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private String website;
    private Boolean personal;

}

