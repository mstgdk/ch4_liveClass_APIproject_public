package com.patika.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSaveRequest {
    @NotNull
    private String firsName;

    @NotNull
    private String lastName;

    @NotNull
    private int schoolNumber;

    @NotNull
    private Long universityId;

    // @NotNull
    private Long courseId;
}
