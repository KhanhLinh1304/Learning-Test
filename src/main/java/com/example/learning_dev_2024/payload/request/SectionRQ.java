package com.example.learning_dev_2024.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class SectionRQ {
    private String title;
    private List<LessonRQ> lessons;
}
