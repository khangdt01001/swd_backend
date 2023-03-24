package com.learning.swd.controllers;

import com.learning.swd.models.Dto.CourseLessonDto;
import com.learning.swd.models.Dto.LessionDto;
import com.learning.swd.models.Entities.CouseLesson;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.repository.Course_LessonRepository;
import com.learning.swd.services.Course_LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course_lesson")
public class CourseLessonController {

    @Autowired
    private Course_LessonService course_lessonService;

    @Autowired
    private Course_LessonRepository course_lessonRepository;

    @GetMapping("/get_list")
    public Object getList(@RequestParam(required = false) Long id,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "1") int size) {
        List<CouseLesson> couseLesson = (List<CouseLesson>) course_lessonService.getList(id);
        return  ResponseHelper.getResponse(couseLesson, course_lessonRepository.findAll().size(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody CourseLessonDto lessionDto) {
        return course_lessonService.insert_data(lessionDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CourseLessonDto lessionDto) {
        return course_lessonService.update(lessionDto);
    }
}
