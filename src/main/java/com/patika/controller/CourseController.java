package com.patika.controller;

import com.patika.dto.request.CourseSaveRequest;
import com.patika.dto.request.UniversitySaveRequest;
import com.patika.message.PatikaResponse;
import com.patika.message.ResponseMessage;
import com.patika.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<PatikaResponse> saveUniversity(@Valid @RequestBody CourseSaveRequest courseSaveRequest){
        courseService.saveCourse(courseSaveRequest);

        PatikaResponse response = new PatikaResponse();
        response.setMessage(ResponseMessage.UNIVERSITY_CREATED_RESPONSE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

}
