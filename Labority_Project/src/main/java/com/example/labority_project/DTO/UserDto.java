package com.example.labority_project.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Valid
public class UserDto {
    private  Integer user_id;
    private String blood_type;
    private Boolean pregnantOrNot;
    private Boolean isIllness;
    private String illness_type;


}