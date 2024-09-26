package com.example.learning_dev_2024.payload.response;

import com.example.learning_dev_2024.DTO.SearchCourseDTO;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
@Data
public class SearchCourseResponse {
    private List<SearchCourseDTO> searchCourseDTOS;
    private Integer total;

    public SearchCourseResponse(List<SearchCourseDTO> courseDTOS) {
        this.searchCourseDTOS = courseDTOS;
        this.total = courseDTOS.size();
    }
}
