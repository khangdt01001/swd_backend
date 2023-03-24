package com.learning.swd.services;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.LessionDto;
import org.springframework.http.ResponseEntity;

public interface LessonService {

    Object getList(Long id, int page, int size);

    ResponseEntity<?> insert_data (LessionDto lessionDto);

    ResponseEntity<?> update(LessionDto lessionDto, Long id);

    ResponseEntity<?> delete(DeleteDto deleteDto);
}
