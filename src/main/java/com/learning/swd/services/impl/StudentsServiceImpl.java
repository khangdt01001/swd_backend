package com.learning.swd.services.impl;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.StudentDto;
import com.learning.swd.models.Entities.Students;
import com.learning.swd.payload.MessageResponse;

import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.payload.ResponseMess;
import com.learning.swd.repository.StudentsRespository;
import com.learning.swd.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentsRespository studentsRespository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Object getList(String name, Long id) {
        try {
            List<Students> students = new ArrayList<>();
            if(name == null && id != null){
                students = studentsRespository.findStudentsById(id);
            }
            else if(name != null && id == null){
                students = studentsRespository.findStudentsByNameContaining(name);
            }
            if (name == null && id == null)
                students = studentsRespository.findAll();
            return students;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0,"Fail", false, true);
            return messageResponse;
        }
    }

    @Override
    public ResponseEntity<?> insert_data(StudentDto studentDto) {
        try {
            if (studentsRespository.existsByName(studentDto.getName())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "StusentName already exsits"));
            }
            studentDto.setCreateBy("Khang");
            Students students = new Students();
            students = modelMapper.map(studentDto,Students.class);
            studentsRespository.save(students);
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> update(StudentDto studentDt, Long id) {
        try {
            if (!studentsRespository.existsById(id)) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "StudentId not exsits"));
            } else {
                Students students = studentsRespository.getById(id);
                Students students1 = new Students();
                students1 = modelMapper.map(studentDt,Students.class);
                students1.setId(students.getId());
                students1.setUpdateBy("Khang");
                students1.setCreateBy(students.getCreateBy());
                students1.setCreate_time(students.getCreate_time());
                students1.setModify_time(students.getModify_time());
                BeanUtils.copyProperties(students1, students);
                studentsRespository.saveAndFlush(students);
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> delete(DeleteDto deleteDto) {
        try {
            if (!studentsRespository.existsById(Long.valueOf(deleteDto.getId()))) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "StudentId not exsits"));
            } else {
                studentsRespository.deleteById(deleteDto.getId());
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }
}