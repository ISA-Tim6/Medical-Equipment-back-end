package com.example.medicalequipment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.AuthResponse;
import com.example.medicalequipment.dto.JwtAuthenticationRequest;
import com.example.medicalequipment.dto.UserTokenState;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.service.ActivationTokenService;
import com.example.medicalequipment.service.EmailService;
import com.example.medicalequipment.service.RegistratedUserService;
import com.example.medicalequipment.service.UserService;
import com.example.medicalequipment.util.TokenUtils;

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
	private TokenUtils tokenUtils;
	
	private AuthenticationManager authenticationManager;
	
    public AuthentificationController(RegistratedUserService registratedUserService, UserService userService, AuthenticationManager authenticationManager){
        this.registratedUserService = registratedUserService;
		this.userService = userService;
		this.authenticationManager=authenticationManager;
    }
    @PostMapping("/login")
	public ResponseEntity<AuthResponse> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
    	System.out.println(authenticationRequest.getUsername());
    	System.out.println(authenticationRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		System.out.println(jwt);
		long expiresIn = tokenUtils.getExpiredIn();
		System.out.println(expiresIn);
		// Vrati token kao odgovor na uspesnu autentifikaciju*/
		UserTokenState state=new UserTokenState();
		System.out.println(state.getAccessToken());
		System.out.println(state.getExpiresIn());
		state.setAccessToken(jwt);
		state.setExpiresIn(expiresIn);
		System.out.println(state);
		return ResponseEntity.ok(new AuthResponse(jwt));
    	//return ResponseEntity.ok(null);
	}
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("registerUser")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegistratedUser u,HttpServletRequest request) throws MailException, InterruptedException, MessagingException {
    	User userByEmail = userService.findByEmail(u.getEmail());
    	User userByUsername = userService.findByUsername(u.getUsername());
    	if(userByEmail == null && userByUsername==null){
    		RegistratedUser newUser = new RegistratedUser(u);
       	 	newUser.setUsername(u.getUsername());
       	 	newUser.setRoles(u.getRoles());
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
	@GetMapping("/verify/{code}")
    public String activateAccount(@PathVariable String code) {
		System.out.println("uslo");
        if (registratedUserService.verify(code)) {
            String successMessage = "<div style=\"text-align: center; padding: 20px; background-color: #dff0d8;\">" +
                    "<h1 style=\"font-size: 24px;\">Account Activated Successfully!</h1>" +
                    "<p style=\"font-size: 16px;\">Thank you for activating your account. You can now log in using the link below:</p>" +
                    "<a href=\"http://localhost:4200/login\" style=\"font-size: 18px; color: #007bff; text-decoration: none;\">Login</a>" +
                    "</div>";
            return successMessage;
        } else {
            String errorMessage = "<div style=\"text-align: center; padding: 20px; background-color: #f2dede;\">" +
                    "<h1 style=\"font-size: 24px; color: #d9534f;\">Invalid or Already Activated Code</h1>" +
                    "<p style=\"font-size: 16px;\">The activation code you provided is invalid or has already been used. If you believe this is an error, please contact our support team.</p>" +
                    "</div>";
            return errorMessage;
        }
    }
}
