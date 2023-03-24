package com.learning.swd.controllers;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.StudentDto;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.models.Entities.Students;
import com.learning.swd.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get_list")
    public Object getList(@RequestParam(required = false) String name,
                          @RequestParam(required = false) Long id) {
        List<Students> students = (List<Students>) studentService.getList(name,id);
        return  ResponseHelper.getResponse(students, students.size(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody StudentDto studentDto) {
        return studentService.insert_data(studentDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto,
                                    @RequestParam Long id) {
        return studentService.update(studentDto, id);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteDto deleteDto) {
        return studentService.delete(deleteDto);
    }
}
