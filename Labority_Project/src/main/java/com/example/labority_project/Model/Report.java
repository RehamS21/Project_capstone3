package com.example.labority_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @Id
    private Integer id;

    @NotEmpty(message = "The title must not empty")
    @Size(min = 3, max = 50 , message = "The title must between 3 to 50")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @NotEmpty(message = "The report content must not empty")
    @Size(min = 3, max = 255 , message = "The title must between 3 to 255")
    @Column(columnDefinition = "varchar(255) not null")
    private String reportContent;

    @OneToOne
    @MapsId
    private Orders orders;

}
