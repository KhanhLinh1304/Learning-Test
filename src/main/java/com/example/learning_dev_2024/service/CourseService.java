package com.example.learning_dev_2024.service;

import com.example.learning_dev_2024.DTO.courseDetail.CourseDetailDTO;
import com.example.learning_dev_2024.model.Course;
import com.example.learning_dev_2024.model.Lesson;
import com.example.learning_dev_2024.model.Section;
import com.example.learning_dev_2024.payload.request.CourseRQ;
import com.example.learning_dev_2024.payload.request.LessonRQ;
import com.example.learning_dev_2024.payload.request.SectionRQ;
import com.example.learning_dev_2024.payload.response.CourseDetailResponse;
import com.example.learning_dev_2024.payload.response.ResponseService;
import com.example.learning_dev_2024.repository.CourseRepository;
import com.example.learning_dev_2024.repository.LessonRepository;
import com.example.learning_dev_2024.repository.SectionRepository;
import com.example.learning_dev_2024.service.transaction.CreateCourseTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;
    private final LessonRepository lessonRepository;
    private final CreateCourseTransaction courseTransaction;


    @Autowired
    public CourseService(CourseRepository courseRepository,
                         LessonRepository lessonRepository,
                         SectionRepository sectionRepository,
                         CreateCourseTransaction courseTransaction
                         ) {
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.lessonRepository = lessonRepository;
        this.courseTransaction = courseTransaction;

    }

    public ResponseService getCourseDetail(Integer courseID) {
        CourseDetailDTO courseDetailDTO = courseRepository.getCourseDetailByCourseID(courseID);

        List<Section> sections = sectionRepository.findAllByCourseID(courseID);
        List<Lesson> newLessons = new ArrayList<>();

        for (Section section : sections) {
            List<Lesson> lessons = lessonRepository.findLessonsBySectionID(section.getId());
            newLessons.addAll(lessons);
        }

        if (courseDetailDTO == null) return new ResponseService(404, null);
       // LocalDateTime time = ConvertDateTime.convertStringToTime(courseDetailDTO.getTotalTime().toString());
        CourseDetailResponse detailResponse = new CourseDetailResponse(courseDetailDTO, sections, newLessons);

        return new ResponseService(200, detailResponse);
    }

    public ResponseService addCourse(CourseRQ courseRQ) {
        Course course = new Course(courseRQ.getCategoryID(), courseRQ.getTitle(),
                                   courseRQ.getDescription(), courseRQ.getPrice(),
                                   courseRQ.getUrl());
        List<SectionRQ> sections = courseRQ.getSections();

        try {
            Integer userID = UserService.getIDUserAuthenticatedFromSecurityContextHolder();
            courseTransaction.createCourse(course, sections, userID);
        } catch (Exception e) {
            return new ResponseService(407, null);
        }

        return new ResponseService(200, null);
    }
}
