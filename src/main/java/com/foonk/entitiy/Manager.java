package com.foonk.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends User {

    private String projectName;
@Builder
    public Manager(Long id, String username, PersonalInfo personalInfo, String info, Role role, Company company, Profile profile, Set<Chat> chats, String projectName) {
        super(id, username, personalInfo, info, role, company, profile, chats);
        this.projectName = projectName;
    }
}
