package com.example.learning_dev_2024.repository;

import com.example.learning_dev_2024.model.Lesson;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findLessonsBySectionID(Integer sectionID);


}
