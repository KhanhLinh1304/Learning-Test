package com.example.learning_dev_2024.controller;

import com.example.learning_dev_2024.payload.request.CourseRQ;
import com.example.learning_dev_2024.payload.response.APIResponse;
import com.example.learning_dev_2024.payload.response.ResponseFormat;
import com.example.learning_dev_2024.payload.response.ResponseService;
import com.example.learning_dev_2024.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {
    private final CourseService courseService;
    private final ResponseFormat format;

    @Autowired
    public CourseController(CourseService courseService, ResponseFormat format) {
        this.courseService = courseService;
        this.format = format;
    }

    @GetMapping(value = "/{courseID}")
    public ResponseEntity<APIResponse> getCourseDetail(@PathVariable Integer courseID) {
        if (courseID == null ) return format.badRequest("BAD REQUEST");
        ResponseService rs = courseService.getCourseDetail(courseID);
        if (rs.getCode() == 400) return format.badRequest("BAD INPUT");
        if (rs.getCode() == 200) return format.success(rs.getObject());
        if (rs.getCode() == 404) return format.notFound("NOT FOUND");
        return format.confictRequest("CONFLICT");
    }
}
