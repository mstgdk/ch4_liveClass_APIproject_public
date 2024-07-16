package com.patika.service;

import com.patika.dto.request.StudentSaveRequest;
import com.patika.entities.Course;
import com.patika.entities.Student;
import com.patika.entities.University;
import com.patika.exception.ConflictException;
import com.patika.exception.ResourceNotFoundException;
import com.patika.message.ErrorMessage;
import com.patika.repository.StudentRepository;
import com.patika.repository.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;

    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, UniversityRepository universityRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.courseService = courseService;
    }


    public void saveStudent(StudentSaveRequest studentSaveRequest) {
        University university = universityRepository.findById(studentSaveRequest.getUniversityId()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION, studentSaveRequest.getUniversityId()))
        );


        boolean isExist = studentRepository.existsBySchoolNumber(studentSaveRequest.getSchoolNumber());
        if (isExist){
            throw new ConflictException(String.format(ErrorMessage.STUDENT_ALREADY_EXIST_EXCEPTION,studentSaveRequest.getSchoolNumber()));
        }
        Student student = new Student();
        student.setFirsName(studentSaveRequest.getFirsName());
        student.setLastName(studentSaveRequest.getLastName());
        student.setSchoolNumber(studentSaveRequest.getSchoolNumber());
        student.setUniversity(university);

        if (studentSaveRequest.getCourseId()!=null){
            Course course =courseService.getCourseById(studentSaveRequest.getCourseId());
            student.getCourseList().add(course);
        }
        studentRepository.save(student);

    }
}
