package com.learning.swd.services.impl;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.LessionDto;
import com.learning.swd.models.Entities.Lesson;
import com.learning.swd.payload.MessageResponse;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.payload.ResponseMess;
import com.learning.swd.repository.LessonRepository;
import com.learning.swd.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessionServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;


    @Override
    public Object getList(Long id,int page, int size) {
        try {
            List<Lesson> lessons = new ArrayList<>();
            Lesson lesson = new Lesson();
            Pageable paging = PageRequest.of(page, size);
            Page<Lesson> pageTest = null;
            if(id != null){
                lesson = lessonRepository.findLessonById(id);
                lessons.add(lesson);
            }
            else{
                pageTest = lessonRepository.findAll(paging);
                lessons = pageTest.getContent();
            }
            return lessons;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0,"Fail", false, true);
            return messageResponse;
        }
    }

    @Override
    public ResponseEntity<?> insert_data(LessionDto lessionDto) {
        try {
            if (lessonRepository.existsLessonByLink(lessionDto.getLink())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Link already exsits"));
            }
            Lesson lesson = new Lesson();
            lesson.setLink(lessionDto.getLink());
            lessonRepository.save(lesson);
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> update(LessionDto lessionDto, Long id) {
        try {
            if (!lessonRepository.existsById(id) || lessonRepository.existsLessonByLink(lessionDto.getLink())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Lession Id not exsits or link realy exsits"));
            } else {
                Lesson lesson = lessonRepository.getById(id);
                lesson.setLink(lessionDto.getLink());
                lessonRepository.save(lesson);
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> delete(DeleteDto deleteDto) {
        try {
            if (!lessonRepository.existsById(Long.valueOf(deleteDto.getId()))) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Lession Id not exsits"));
            } else {
                lessonRepository.deleteById(deleteDto.getId());
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }
}
