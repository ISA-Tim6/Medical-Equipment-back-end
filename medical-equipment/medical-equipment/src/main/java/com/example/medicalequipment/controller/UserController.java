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
@RequestMapping(path="api/")
public class UserController {
    private final UserService userService;
    private final IUserRepository userRepository;

    @Autowired
    public UserController(UserService userService,IUserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
   
    
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveUser")
    public User save(@RequestBody User u) {
    	System.out.println("infoAboutInstitution received on the server: " + u.getInfoAboutInstitution() + u.getCity());
    	return userService.save(u);
    }
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("userView")
    public int get() {
        return 2;
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ResponseEntity r = new ResponseEntity<>(user, HttpStatus.OK);
		return r;
	}
    
    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("updateUser/{oldUsername}")
	public ResponseEntity<User> updateUser(@PathVariable String oldUsername, @RequestBody User user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println(user.getUsername());
		System.out.println(oldUsername);
		return new ResponseEntity<>(userService.update(user, oldUsername), HttpStatus.OK);
	}

    
    /*@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable Long id) {
    	User user = this.userService.getById(id);
    	
    	if(user == null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	 return new ResponseEntity<UserResponseDto>(new UserResponseDto(user), HttpStatus.OK);

    }*/

   
    
    /*@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/registerUser", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User u) {
    	User user = userRepository.findByEmail(u.getEmail());
        if(user == null){
            this.userService.registerUser(u);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Successfully registered. Please activate your account.");
            return ResponseEntity.ok(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Email is already taken.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }*/




}