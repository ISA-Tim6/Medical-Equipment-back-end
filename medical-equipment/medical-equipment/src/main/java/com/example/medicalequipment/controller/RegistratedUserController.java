package com.example.medicalequipment.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IUserRepository;
import com.example.medicalequipment.service.ActivationTokenService;
import com.example.medicalequipment.service.EmailService;
import com.example.medicalequipment.service.RegistratedUserService;
import com.example.medicalequipment.service.UserService;

import io.swagger.annotations.Authorization;



@RestController
@RequestMapping(path="api/registratedUser")
public class RegistratedUserController {
	private final RegistratedUserService userService;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private ActivationTokenService tokenService;
	@Autowired
	private IActivationTokenRepository tokenRepository;
    @Autowired
    public RegistratedUserController(RegistratedUserService userService){
        this.userService = userService;
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveUser")
    public RegistratedUser save(@RequestBody RegistratedUser user) throws MailException, InterruptedException, MessagingException {
    	System.out.println("infoAboutInstitution received on the server: " + user.getInfoAboutInstitution() + user.getCity());
    	return userService.saveSystemAdmin(user);
    }
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{id}")
	public ResponseEntity<RegistratedUser> getUser(@PathVariable Long id) {

		RegistratedUser user = userService.findOne(id);
		for(GrantedAuthority i:user.getAuthorities()) {
			System.out.println(i.getAuthority());
			System.out.println(i.getAuthority().compareTo("ROLE_REGISTRATED_USER"));
			System.out.println("uslooooo");
		}
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ResponseEntity r = new ResponseEntity<>(user, HttpStatus.OK);
		return r;
	}
    
    
    @CrossOrigin(origins="http://localhost:4200")
    @PutMapping("updateUser/{oldUsername}")
    @PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
	public ResponseEntity<RegistratedUser> updateUser(@PathVariable String oldUsername, @RequestBody RegistratedUser user) {
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println(user.getUsername());
		System.out.println(oldUsername);
		return new ResponseEntity<>(userService.update(user, oldUsername), HttpStatus.OK);
	}
   
}
