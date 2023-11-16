package com.example.medicalequipment.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IUserRepository;
import com.example.medicalequipment.service.EmailService;
import com.example.medicalequipment.service.RegistratedUserService;
import com.example.medicalequipment.service.UserService;



@RestController
@RequestMapping(path="api/")
public class RegistratedUserController {
	private final RegistratedUserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private EmailService emailService;
	
    @Autowired
    public RegistratedUserController(RegistratedUserService userService){
        this.userService = userService;
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveUser")
    public RegistratedUser save(@RequestBody RegistratedUser user) throws MailException, InterruptedException {
    	System.out.println("infoAboutInstitution received on the server: " + user.getInfoAboutInstitution() + user.getCity());
    	return userService.save(user);
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{id}")
	public ResponseEntity<RegistratedUser> getUser(@PathVariable Long id) {

		RegistratedUser user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ResponseEntity r = new ResponseEntity<>(user, HttpStatus.OK);
		return r;
	}
    
    
    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("updateUser/{oldUsername}")
	public ResponseEntity<RegistratedUser> updateUser(@PathVariable String oldUsername, @RequestBody RegistratedUser user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println(user.getUsername());
		System.out.println(oldUsername);
		return new ResponseEntity<>(userService.update(user, oldUsername), HttpStatus.OK);
	}
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("registerUser")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistratedUser u) throws MailException, InterruptedException {
    	RegistratedUser user = userService.findByEmail(u.getEmail());
        if(user == null){
            this.userService.save(u);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Successfully registered.");
            return ResponseEntity.ok(response);
        }
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Email is already taken.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }
}
