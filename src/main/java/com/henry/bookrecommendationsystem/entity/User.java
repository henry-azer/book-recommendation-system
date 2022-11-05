package com.henry.bookrecommendationsystem.entity;

import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import com.henry.bookrecommendationsystem.enums.UserGender;
import com.henry.bookrecommendationsystem.enums.UserMartialStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private UserGender gender;

    @Column(name = "marital_status")
    private UserMartialStatus maritalStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
