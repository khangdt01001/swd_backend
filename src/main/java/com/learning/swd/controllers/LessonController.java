package com.learning.swd.controllers;

import com.learning.swd.models.Dto.CourseLessonDto;
import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.LessionDto;
import com.learning.swd.models.Entities.Lesson;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.repository.LessonRepository;
import com.learning.swd.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/get_list")
    public Object getList(@RequestParam(required = false) Long id,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "1") int size) {
        List<Lesson> lessons = (List<Lesson>) lessonService.getList(id,page-1,size);
        return  ResponseHelper.getResponse(lessons, lessonRepository.findAll().size(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody LessionDto lessionDto) {
        return lessonService.insert_data(lessionDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody LessionDto lessionDto,
                                    @RequestParam Long id) {
        return lessonService.update(lessionDto, id);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody DeleteDto deleteDto) {
        return lessonService.delete(deleteDto);
    }
}
