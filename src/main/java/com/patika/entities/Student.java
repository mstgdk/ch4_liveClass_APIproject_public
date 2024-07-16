package com.patika.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firsName;

    //@NotEmpty  // ""
    @NotNull  // null
    private String lastName;

    @NotNull
    private int schoolNumber;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private University university;

    @ManyToMany()
    @JoinTable(
            name = "student_course_table",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courseList =new ArrayList<>();

}
