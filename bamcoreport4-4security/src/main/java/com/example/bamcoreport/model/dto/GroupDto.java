package com.example.bamcoreport.model.dto;

import com.example.bamcoreport.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private long id;
    private String name;
    private String parentPath;
    private String displayName;
    private String description;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;


}