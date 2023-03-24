package com.learning.swd.repository;

import com.learning.swd.models.Entities.CouseLesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Course_LessonRepository extends JpaRepository<CouseLesson, Long> {

    @Query(value = "SELECT * FROM course_lesson where course_id = :id",nativeQuery = true)
    List<CouseLesson> findAllByCourse_id(@Param("id") Long course_id);
}
