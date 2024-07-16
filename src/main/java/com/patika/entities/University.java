package com.patika.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unv_name",nullable = false)
    private String name;

    @Column(name = "unv_city",nullable = false)
    private String city;

    @OneToMany(mappedBy = "university",orphanRemoval = true)
    private List<Student> students;
}
