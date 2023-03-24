package com.learning.swd.services;

import com.learning.swd.models.Dto.CourseDto;
import com.learning.swd.models.Dto.DeleteDto;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    Object getList(Long id, String name);

    ResponseEntity<?> insert_data (CourseDto courseInsertDto);

    ResponseEntity<?> update(CourseDto courseInsertDto, Long id);

    ResponseEntity<?> delete(DeleteDto deleteDto);
}
