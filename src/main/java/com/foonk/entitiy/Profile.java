package com.foonk.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String language;
    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    public void setUser(User user){
        user.setProfile(this);
        this.user=user;
    }
}
