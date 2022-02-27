package com.foonk.entitiy;

import com.foonk.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"company, profile"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private PersonalInfo personalInfo;
    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType")
    private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "company_id") // company_id
    private Company company;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Profile profile;
}
