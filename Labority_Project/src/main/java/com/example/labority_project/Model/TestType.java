package com.example.labority_project.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The test name must not null")
    @Size(min = 2 , max = 50)
    @Column(columnDefinition = "varchar(50) unique not null")
    private String name;

    @NotEmpty(message = "The test description must not null")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotNull(message = "The test price must not null")
    @Column(columnDefinition = "double not null")
    private Double price;

    @Column(columnDefinition = "boolean default false")
    private Boolean appointment = false;

//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "datetime not null")
    private Date test_date;

    @ManyToOne
    @JoinColumn(name = "laboratory_id",referencedColumnName = "id")
    private Laboratories laboratory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testType")
    @JsonIgnore
    private Set<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testType")
    @JsonIgnore
    private Set<Orders> orders;
}
