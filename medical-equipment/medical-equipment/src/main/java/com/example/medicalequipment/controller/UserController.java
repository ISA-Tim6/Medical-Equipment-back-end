package com.example.medicalequipment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.UserResponseDto;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;
import com.example.medicalequipment.service.UserService;

@RestController
@RequestMapping(path="api/user")
public class UserController {
    private final UserService userService;
    private final IUserRepository userRepository;

    @Autowired
    public UserController(UserService userService,IUserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
   
    
    




}