package com.patika.controller;

import com.patika.dto.request.UniversitySaveRequest;
import com.patika.message.PatikaResponse;
import com.patika.message.ResponseMessage;
import com.patika.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }
    @PostMapping()
    public ResponseEntity<PatikaResponse>saveUniversity(@Valid @RequestBody UniversitySaveRequest universitySaveRequest){
        universityService.saveUniversity(universitySaveRequest);

        PatikaResponse response = new PatikaResponse();
        //response.setMessage("University succesfully created");
        response.setMessage(ResponseMessage.UNIVERSITY_CREATED_RESPONSE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

}
