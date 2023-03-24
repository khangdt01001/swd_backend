package com.learning.swd.repository;

import com.learning.swd.models.Entities.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Lesson findLessonById(Long id);

    @Query(value = "SELECT * FROM lesson", nativeQuery = true)
    Page<Lesson> findAll(Pageable pageable);

    Boolean existsLessonByLink(String link);
}
