package com.example.bamcoreport.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Boolean atrue;
    private String name;
    private Enum HttpStatus;


}
