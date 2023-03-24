package com.learning.swd.services;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Object getList(String name,Long id);

    ResponseEntity<?> insert_data (UserDto studentDto);

    ResponseEntity<?> update(UserDto studentDt, Long id);

    ResponseEntity<?> delete(DeleteDto deleteDto);

    Object login(String name,String pass);
}
