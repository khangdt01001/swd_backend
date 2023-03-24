package com.learning.swd.services;
import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.StudentDto;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    Object getList(String name,Long id);

    ResponseEntity<?> insert_data (StudentDto studentDto);

    ResponseEntity<?> update(StudentDto studentDt, Long id);

    ResponseEntity<?> delete(DeleteDto deleteDto);

}
