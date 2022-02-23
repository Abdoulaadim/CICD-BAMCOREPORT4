package com.example.bamcoreport.model.dto;


import com.example.bamcoreport.model.entity.Group;
import com.example.bamcoreport.model.entity.Role;
import com.example.bamcoreport.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMembershipDto {

    private long id;
    private User userId;
    private Role roleId;
    private Group groupId;
    private User assignedBy;
    private LocalDateTime assignedDate;


}