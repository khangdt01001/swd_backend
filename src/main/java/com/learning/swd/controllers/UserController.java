package com.learning.swd.controllers;

import com.learning.swd.models.Dto.StudentDto;
import com.learning.swd.models.Dto.UserDto;
import com.learning.swd.models.Entities.Students;
import com.learning.swd.models.Entities.User;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get_list")
    public Object getList(@RequestParam(required = false) String name,
                          @RequestParam(required = false) Long id) {
        List<User> users = (List<User>) userService.getList(name,id);
        return  ResponseHelper.getResponse(users, users.size(), HttpStatus.OK);
    }

    @GetMapping("/login")
    public Object login(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String pass) {
        return userService.login(name,pass);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody UserDto userDto) {
        return userService.insert_data(userDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto,
                                    @RequestParam Long id) {
        return userService.update(userDto, id);
    }
}
