package com.patika.controller;

import com.patika.dto.request.StudentSaveRequest;
import com.patika.message.PatikaResponse;
import com.patika.message.ResponseMessage;
import com.patika.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<PatikaResponse> saveStudent(@RequestBody StudentSaveRequest studentSaveRequest){
        studentService.saveStudent(studentSaveRequest);

        PatikaResponse response = new PatikaResponse();
        response.setSuccess(true);
        response.setMessage(ResponseMessage.STUDENT_CREATED_RESPONSE);

        //return ResponseEntity.ok(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
