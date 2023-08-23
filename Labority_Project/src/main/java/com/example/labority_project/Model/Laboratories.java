package com.example.labority_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Laboratories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The lab name must not null")
    @Size(min = 3, max = 25 , message = "The lab name must between 3 to 25")
    @Column(columnDefinition = "varchar(25) unique not null")
    private String name;

    @NotEmpty(message = "The lab phone number must not null")
    @Column(columnDefinition = "varchar(11) unique not null")
    private String phone_num;

    @NotEmpty(message = "The lab email must not null")
    @Email(message = "The lab email must have a same format of emails")
    @Column(columnDefinition = "varchar(30) unique not null")
    private String email;

    @NotEmpty(message = "The lab city must not null")
    @Size(min = 3, max = 50)
    private String city;

    @NotEmpty(message = "The lab district must not null")
    @Size(min = 3, max = 50)
    private String district;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "laboratory")
    @JsonIgnore
    private Set<TestType> testTypes;

    @ManyToMany(mappedBy = "laboratories")
    @JsonIgnore
    private Set<User> users;

}
