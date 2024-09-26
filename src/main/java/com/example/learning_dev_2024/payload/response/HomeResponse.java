package com.example.learning_dev_2024.payload.response;

import com.example.learning_dev_2024.DTO.CourseDTO;
import com.example.learning_dev_2024.DTO.InstructorDTO;
import com.example.learning_dev_2024.model.Category;
import com.example.learning_dev_2024.model.Lesson;
import com.example.learning_dev_2024.model.User;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponse {
    private List<Category> categories;
    private List<CourseDTO> courserByRating;
    private List<CourseDTO> courseByInstructorFollowing;
    private List<CourseDTO> courseByCategoryFollowing;
    private List<InstructorDTO> instructorDTOS;

    public HomeResponse(List<Category> categories,
                        List<CourseDTO> courserByRating,
                        List<CourseDTO> courseByInstructorFollowing,
                        List<CourseDTO> courseByCategoryFollowing,
                        List<InstructorDTO> instructorDTOS) {
        this.categories = categories;
        this.courserByRating = courserByRating;
        this.courseByInstructorFollowing = courseByInstructorFollowing;
        this.courseByCategoryFollowing = courseByCategoryFollowing;
        this.instructorDTOS = instructorDTOS;
    }
}
