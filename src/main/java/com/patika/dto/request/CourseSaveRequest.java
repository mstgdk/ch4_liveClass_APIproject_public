package com.patika.dto.request;

import com.patika.entities.Student;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CourseSaveRequest {
    @NotNull
    private String name;

}
