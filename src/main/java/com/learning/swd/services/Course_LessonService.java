package com.learning.swd.services;

import com.learning.swd.models.Dto.CourseLessonDto;
import com.learning.swd.models.Dto.LessionDto;
import org.springframework.http.ResponseEntity;

public interface Course_LessonService {

    Object getList(Long id_coure);

    ResponseEntity<?> insert_data (CourseLessonDto courseLessonDto);

    ResponseEntity<?> update(CourseLessonDto courseLessonDto);
}
