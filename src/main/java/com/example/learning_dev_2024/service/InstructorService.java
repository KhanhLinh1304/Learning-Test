package com.example.learning_dev_2024.service;

import com.example.learning_dev_2024.DTO.CourseInstructorDTO;
import com.example.learning_dev_2024.payload.response.ResponseService;
import com.example.learning_dev_2024.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public ResponseService getCourseByInstructorID() {
        List<CourseInstructorDTO> courses = courseRepository.getCoursesByUserID(UserService.getIDUserAuthenticatedFromSecurityContextHolder());
        return new ResponseService(200, courses);

    }
}
