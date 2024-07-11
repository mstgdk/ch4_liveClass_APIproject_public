package com.patika.dto.response;

import com.patika.entities.Student;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityResponseDto {

    private String name;


    private String city;


    private List<Student> students;
}
