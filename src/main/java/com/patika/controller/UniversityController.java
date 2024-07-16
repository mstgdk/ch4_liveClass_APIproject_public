package com.patika.controller;

import com.patika.dto.request.UniversitySaveRequest;
import com.patika.dto.request.UniversityUpdateRequest;
import com.patika.dto.response.UniversityResponseDto;
import com.patika.entities.University;
import com.patika.message.PatikaResponse;
import com.patika.message.ResponseMessage;
import com.patika.service.UniversityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/{id}")
    public  ResponseEntity<UniversityResponseDto> getById(@PathVariable("id") Long id ){
        UniversityResponseDto university = universityService.getById(id);

        return ResponseEntity.ok(university);

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<PatikaResponse> deleteById(@PathVariable("id") Long id ){
        universityService.removeById(id);

        PatikaResponse response =new PatikaResponse();
        response.setMessage(ResponseMessage.UNIVERSITY_DELETED_RESPONSE);
        response.setSuccess(true);

        return ResponseEntity.ok(response);

    }
    @GetMapping()
    public ResponseEntity<List<UniversityResponseDto>> getAll(){
        List<UniversityResponseDto> universities =  universityService.getAll();

        return ResponseEntity.ok(universities);
    }
    @GetMapping("/pages")
    public  ResponseEntity<Page<UniversityResponseDto>> getAllUniversitiesPage(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "direction",required = false,defaultValue = "ASC") Sort.Direction direction
            ){
        Pageable pageable = PageRequest.of(pageNumber,size,Sort.by(direction,prop));

        Page<UniversityResponseDto> universityResponseDtos = universityService.getAllPage(pageable);

        return ResponseEntity.ok(universityResponseDtos);
    }
    @PutMapping()
    public ResponseEntity<PatikaResponse> updateUniversity(@RequestParam("id") Long id,
                                                          @Valid @RequestBody UniversityUpdateRequest universityUpdateRequest){
        universityService.updateUniversity(id,universityUpdateRequest);

        PatikaResponse response = new PatikaResponse();
        response.setSuccess(true);
        response.setMessage(ResponseMessage.UNIVERSITY_UPDATED_RESPONSE);

        return ResponseEntity.ok(response);
    }


}
