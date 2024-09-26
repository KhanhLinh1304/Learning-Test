package com.example.learning_dev_2024.repository;

import com.example.learning_dev_2024.DTO.CourseDTO;
import com.example.learning_dev_2024.DTO.CourseInstructorDTO;
import com.example.learning_dev_2024.DTO.SearchCourseDTO;
import com.example.learning_dev_2024.DTO.courseDetail.CourseDetailDTO;
import com.example.learning_dev_2024.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(name = "CourseDTO.FilterTenCourse", nativeQuery = true)
    List<CourseDTO> get10CourseSortByRating();

    @Query(name = "CourseDTO.InstructorFollowing", nativeQuery = true)
    List<CourseDTO> getCourseByInstructorFollowing(@Param("userID") Integer userID);

    @Query(name = "CourseDTO.CategoryFollowing")
    List<CourseDTO> getCoursesByCategoryFollowing(@Param("userID") Integer userID);

    @Query(name = "SearchCourseDTO.SearchCourse", nativeQuery = true)
    List<SearchCourseDTO> searchCourseByTitle(@Param("keyword") String keyword);
    @Query(name = "CourseDetailDTO.CourseDetail", nativeQuery = true)
    CourseDetailDTO getCourseDetailByCourseID(@Param("courseID") Integer courseID);

    Course findCourseById(Integer courseId);

    @Query(name = "CourseInstructor.CourseInstructorDTO")
    List<CourseInstructorDTO> getCoursesByUserID(@Param("userID") Integer userID);
}
