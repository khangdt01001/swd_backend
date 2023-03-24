package com.learning.swd.controllers;

import com.learning.swd.models.Dto.CourseDto;
import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Entities.Course;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.repository.CourseRepository;
import com.learning.swd.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/get_list")
    public Object getList(@RequestParam(required = false) Long id,
                          @RequestParam(required = false) String name) {
        List<Course> courseList = (List<Course>) courseService.getList(id, name);
        return  ResponseHelper.getResponse(courseList, courseRepository.findAll().size(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody CourseDto courseInsertDto) {
        return courseService.insert_data(courseInsertDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CourseDto courseInsertDto,
                                    @RequestParam Long id) {
        return courseService.update(courseInsertDto, id);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteDto deleteDto) {
        return courseService.delete(deleteDto);
    }
}
