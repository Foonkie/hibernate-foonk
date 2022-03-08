package com.foonk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

//@AllArgsConstructor
////@NoArgsConstructor
////@Data
////@Entity

//public class Programmer  {
//    @Enumerated(EnumType.STRING)
//    private Language language;
//@Builder
//    public Programmer(Long id, String username, PersonalInfo personalInfo, String info, Role role, Company company, Profile profile, Set<Chat> chats, Language language) {
//        super(id, username, personalInfo, info, role, company, profile, chats);
//        this.language = language;
//    }
//}
