package com.foonk.dto;

import com.foonk.entity.PersonalInfo;
import com.foonk.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Value;
@AllArgsConstructor
@Value
public class UserReadDto {
    Long id;
    PersonalInfo personalInfo;
    String username;
    String info;
    Role role;
    CompanyReadDto company;
}
