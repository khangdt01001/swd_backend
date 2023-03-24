package com.learning.swd.services.impl;

import com.learning.swd.models.Dto.CourseLessonDto;
import com.learning.swd.models.Dto.LessionDto;
import com.learning.swd.models.Entities.CouseLesson;
import com.learning.swd.models.Entities.Lesson;
import com.learning.swd.models.Entities.Students;
import com.learning.swd.payload.MessageResponse;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.payload.ResponseMess;
import com.learning.swd.repository.CourseRepository;
import com.learning.swd.repository.Course_LessonRepository;
import com.learning.swd.repository.LessonRepository;
import com.learning.swd.services.Course_LessonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseLessonServiceImpl implements Course_LessonService {

    @Autowired
    private Course_LessonRepository course_lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Object getList(Long id_coure) {
        try {
            List<CouseLesson> courseList = new ArrayList<>();
            List<Lesson> lessonList = new ArrayList<>();
            if (id_coure != null) {
                courseList = course_lessonRepository.findAllByCourse_id(id_coure);
                for (int i = 0; i < courseList.size(); i++) {
                    Lesson lesson = new Lesson();
                    lesson = lessonRepository.findLessonById(courseList.get(i).getLesson_id());
                    lessonList.add(lesson);
                }
            }
            return lessonList;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0, "Fail", false, true);
            return messageResponse;
        }
    }

    @Override
    public ResponseEntity<?> insert_data(CourseLessonDto courseLessonDto) {
        try {
            if (!courseRepository.existsById(courseLessonDto.getCourse_id())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Course_id not exsits"));
            }
            List<Long> lesson = courseLessonDto.getLessonList();
            for (int i = 0; i < lesson.size(); i++) {
                CouseLesson couseLesson = new CouseLesson();
                couseLesson.setCourse_id(courseLessonDto.getCourse_id());
                couseLesson.setLesson_id(lesson.get(i));
                course_lessonRepository.save(couseLesson);
            }
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> update(CourseLessonDto courseLessonDto) {
        try {
            if (!course_lessonRepository.existsById(courseLessonDto.getCourse_id())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Course_id not exsits"));
            } else {
                List<CouseLesson> couseLessonList = new ArrayList<>();
                couseLessonList = course_lessonRepository.findAllByCourse_id(courseLessonDto.getCourse_id());

                for (int i = 0; i < couseLessonList.size(); i++) {
                    course_lessonRepository.delete(couseLessonList.get(i));
                }
                List<Long> lessonList = courseLessonDto.getLessonList();
                for (int i = 0; i < lessonList.size(); i++) {
                    Lesson lesson = lessonRepository.findLessonById(lessonList.get(i));
                    CouseLesson couseLesson = new CouseLesson();
                    couseLesson.setLesson_id(lesson.getId());
                    couseLesson.setCourse_id(courseLessonDto.getCourse_id());
                    course_lessonRepository.save(couseLesson);
                }
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }
}
