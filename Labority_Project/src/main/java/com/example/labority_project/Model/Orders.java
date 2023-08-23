package com.example.labority_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(15) not null default 'processing' check(order_status='confirmed' or order_status='processing')")
    private String order_status;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null ")
    private  String lab_name;



    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "testType_id" , referencedColumnName = "id")
    @JsonIgnore
    private TestType testType;

    @OneToOne(cascade =CascadeType.ALL,mappedBy = "orders")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Report report;
}


