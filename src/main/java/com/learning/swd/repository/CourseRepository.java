package com.learning.swd.repository;

import com.learning.swd.models.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByNameIsContaining(String name);

    Course getCourseById( Long id);

    Boolean existsCourseByName(String name);
}
