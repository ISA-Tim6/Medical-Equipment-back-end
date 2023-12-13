package com.example.medicalequipment.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.UserResponseDto;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;
import com.example.medicalequipment.service.UserService;

@RestController
@RequestMapping(path="api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final IUserRepository userRepository;

    @Autowired
    public UserController(UserService userService,IUserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }
    
    
	@CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{email}")
	public Long getByEmail(@PathVariable String email) {
		email = email.replaceAll("%40", "@");
		User user = userService.findByEmail(email);
		if(user == null)
			return (long) -1;
		return user.getUser_id();
	}
    
    @CrossOrigin(origins="http://localhost:4200")

    @GetMapping(value = "/username/{username}")
	public Long getByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if(user == null)
			return (long) -1;
		return user.getUser_id();
	}
    @GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")	
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@GetMapping("/whoami")
	public User user(Principal user) {
		User userr=this.userService.findByUsername(user.getName());
		return userr;
	}
	
	@GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
	}
   /* @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ResponseEntity r = new ResponseEntity<>(user, HttpStatus.OK);
		return r;
	}*/
    
    
    /*@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable Long id) {
    	User user = this.userService.getById(id);
    	
    	if(user == null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	 return new ResponseEntity<UserResponseDto>(new UserResponseDto(user), HttpStatus.OK);

    }*/
	
}