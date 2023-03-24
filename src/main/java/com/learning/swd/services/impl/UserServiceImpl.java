package com.learning.swd.services.impl;

import com.learning.swd.models.Dto.DeleteDto;
import com.learning.swd.models.Dto.UserDto;
import com.learning.swd.models.Entities.User;
import com.learning.swd.payload.MessageResponse;
import com.learning.swd.payload.ResponseHelper;
import com.learning.swd.payload.ResponseMess;
import com.learning.swd.repository.UserRepository;
import com.learning.swd.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Object getList(String name, Long id) {
        try {
            List<User> users = new ArrayList<>();
            User user = new User();
            if(name == null && id != null){
                user = userRepository.findUserById(id);
                users.add(user);
            }
            else if(name != null && id == null){
                user = userRepository.findByUsername(name);
                users.add(user);
            }
            if (name == null && id == null)
                users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0,"Fail", false, true);
            return messageResponse;
        }
    }

    @Override
    public ResponseEntity<?> insert_data(UserDto userDto) {
        try {
            if (userRepository.existsByUsername(userDto.getUsername())) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "UserName already exsits"));
            }
            User user = new User();
            user = modelMapper.map(userDto,User.class);
            user.setCreateBy("Khang");
            user.setUpdateBy("Khang");
            userRepository.save(user);
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> update(UserDto userDto, Long id) {
        try {
            if (!userRepository.existsById(id)) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "UserId not exsits"));
            } else {
                User user = userRepository.getById(id);
                user.setName(userDto.getName());
                user.setPassword(userDto.getPassword());
                user.setUsername(userDto.getPassword());
                user.setStatus(userDto.isStatus());
                user.setCreateBy(user.getCreateBy());
                user.setUpdateBy("Khang");
                userRepository.save(user);
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public ResponseEntity<?> delete(DeleteDto deleteDto) {
        try {
            if (!userRepository.existsById(Long.valueOf(deleteDto.getId()))) {
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "User Id not exsits"));
            } else {
                userRepository.deleteById(deleteDto.getId());
                return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(0, "SUCCESS"));
            }
        } catch (Exception e) {
            return ResponseHelper.getResponseSearchMess(HttpStatus.OK, new ResponseMess(50, "Error exception"));
        }
    }

    @Override
    public Object login(String name, String pass) {
        try {
            User user = new User();
            user = userRepository.login(name, pass);
            if(user == null){
                MessageResponse messageResponse = new MessageResponse(0,"Fail Login", false, true);
                return messageResponse;
            }
            MessageResponse messageResponse = new MessageResponse(0,"Login Success", false, true);
            return messageResponse;
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse(0,"Fail", false, true);
            return messageResponse;
        }
    }
}
