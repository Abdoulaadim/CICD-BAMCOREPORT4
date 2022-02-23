package com.example.bamcoreport.model.dto;


import com.example.bamcoreport.model.entity.Group;
import com.example.bamcoreport.model.entity.Profile;
import com.example.bamcoreport.model.entity.Role;
import com.example.bamcoreport.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileMemberDto {

    private long id;
    private Profile profileId;
    private User userId;
    private Group groupId;
    private Role roleId;

}
