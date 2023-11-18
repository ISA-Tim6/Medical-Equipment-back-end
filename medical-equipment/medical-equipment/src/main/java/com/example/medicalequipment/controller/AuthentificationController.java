package com.example.medicalequipment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.service.ActivationTokenService;
import com.example.medicalequipment.service.EmailService;
import com.example.medicalequipment.service.RegistratedUserService;
import com.example.medicalequipment.service.UserService;

@RestController
@RequestMapping(path="api/auth/")
public class AuthentificationController {
	private final RegistratedUserService registratedUserService;
	private final UserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private ActivationTokenService tokenService;
	@Autowired
	private IActivationTokenRepository tokenRepository;
    @Autowired
    public AuthentificationController(RegistratedUserService registratedUserService, UserService userService){
        this.registratedUserService = registratedUserService;
		this.userService = userService;
    }
    
	
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("registerUser")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistratedUser u) throws MailException, InterruptedException, MessagingException {
    	User userByEmail = userService.findByEmail(u.getEmail());
    	User userByUsername = userService.findByUsername(u.getUsername());
    	if(userByEmail == null && userByUsername==null){
    		RegistratedUser newUser = new RegistratedUser(u);
       	 	newUser.setUsername(u.getUsername());
       	 	this.registratedUserService.save(newUser);
	       	Map<String, String> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("message", "Successfully registered.");
	        return ResponseEntity.ok(response);
        }else if(userByEmail != null && userByUsername==null) {
        	Map<String, String> response = new HashMap<>();
            response.put("status", "emailError");
            response.put("message", "Email is already taken.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }else if(userByEmail == null && userByUsername!=null) {
        	Map<String, String> response = new HashMap<>();
            response.put("status", "usernameError");
            response.put("message", "Username is already taken.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    	Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Email and username are already taken.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }
}
