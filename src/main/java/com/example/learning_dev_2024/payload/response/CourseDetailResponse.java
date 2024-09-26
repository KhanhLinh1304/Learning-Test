package com.example.learning_dev_2024.payload.response;

import com.example.learning_dev_2024.DTO.courseDetail.CourseDetailDTO;
import com.example.learning_dev_2024.model.Lesson;
import com.example.learning_dev_2024.model.Section;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailResponse {
    private CourseDetailDTO courseDetailDTO;
    private List<Section> sections;
    private List<Lesson> lessons;

    public CourseDetailResponse(CourseDetailDTO courseDetailDTO,
                                List<Section> sections, List<Lesson> lessons) {
        this.courseDetailDTO = courseDetailDTO;
        this.sections = sections;
        this.lessons = lessons;
    }
}
