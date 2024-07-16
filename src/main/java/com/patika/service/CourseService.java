package com.patika.service;

import com.patika.dto.request.CourseSaveRequest;
import com.patika.entities.Course;
import com.patika.exception.ConflictException;
import com.patika.exception.ResourceNotFoundException;
import com.patika.message.ErrorMessage;
import com.patika.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(CourseSaveRequest courseSaveRequest) {
        Optional<Course> course = courseRepository.findByName(courseSaveRequest.getName());
        if (course.isPresent()){
            throw new ConflictException(String.format(ErrorMessage.COURSE_ALREADY_EXIST_EXCEPTION,courseSaveRequest.getName()));
        }
        Course course1 = new Course();
        course1.setName(courseSaveRequest.getName());
        courseRepository.save(course1);
    }

    public Course getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,courseId))
        );
        return  course;
    }
}
