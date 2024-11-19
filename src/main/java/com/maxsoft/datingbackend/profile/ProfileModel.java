package com.maxsoft.datingbackend.profile;

import com.maxsoft.datingbackend.profile.alcohol.AlcoholModel;
import com.maxsoft.datingbackend.profile.education.EducationModel;
import com.maxsoft.datingbackend.profile.gender.GenderModel;
import com.maxsoft.datingbackend.profile.smoke.SmokeModel;
import com.maxsoft.datingbackend.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String description;

    private Short age;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private GenderModel gender;

    @ManyToOne
    @JoinColumn(name = "education_id", referencedColumnName = "id")
    private EducationModel education;

    @ManyToOne
    @JoinColumn(name = "alcohol_id", referencedColumnName = "id")
    private AlcoholModel alcohol;

    @ManyToOne
    @JoinColumn(name = "smoke_id", referencedColumnName = "id")
    private SmokeModel smoke;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
}
