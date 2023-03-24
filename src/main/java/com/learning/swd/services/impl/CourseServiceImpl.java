package com.learning.swd.services.impl;

import com.learning.swd.models.Dto.CourseDto;
import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Entities.Course;
import com.learning.swd.payload.MessageResponse;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.payload.ResponseMess;
import com.learning.swd.repository.CourseRepository;
import com.learning.swd.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Object getList(Long id, String name) {
        try {
            List<Course> courseList = new ArrayList<>();
            Course course = new Course();
            if (id != null && name == null) {
                course = courseRepository.getCourseById(id);
                courseList.add(course);
            } else if (id == null && name != null) {
                courseList = courseRepository.findAllByNameIsContaining(name);
            } else
                courseList = courseRepository.findAll();
            return courseList;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0, "Fail", false, true);
            return messageResponse;
        }
    }

    @Override
    public ResponseEntity<?> insert_data(CourseDto courseInsertDto) {
        try {
            if (courseRepository.existsCourseByName(courseInsertDto.getName())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Course Name already exsits"));
            }
            Course course = new Course();
            course.setImage(courseInsertDto.getImage());
            course.setName(courseInsertDto.getName());
            courseRepository.save(course);
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> update(CourseDto courseInsertDto, Long id) {
        try {
            if (!courseRepository.existsById(id) || courseRepository.existsCourseByName(courseInsertDto.getName())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Course Id not exsits or Name realy exsits"));
            } else {
                Course course = courseRepository.getById(id);
                course.setImage(courseInsertDto.getImage());
                course.setName(courseInsertDto.getName());
                courseRepository.save(course);
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> delete(DeleteDto deleteDto) {
        try {
            if (!courseRepository.existsById(Long.valueOf(deleteDto.getId()))) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "User Id not exsits"));
            } else {
                courseRepository.deleteById(deleteDto.getId());
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }
}
