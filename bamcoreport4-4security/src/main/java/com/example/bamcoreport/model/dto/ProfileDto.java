package com.example.bamcoreport.model.dto;


import com.example.bamcoreport.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileDto {

    private long id;
    private Boolean isDefault;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private User createdBy;
    private LocalDateTime lastUpdateDate;
    private User lastUpdateBy;


}