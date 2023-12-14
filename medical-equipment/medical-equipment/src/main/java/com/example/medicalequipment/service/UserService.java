package com.example.medicalequipment.service;


import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.dto.UserRequest;
import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Role;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserService implements IUserService{
	@Autowired
	private final IUserRepository UserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;
	public UserService(IUserRepository userRepository){
    	this.UserRepository = userRepository;
    }
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.UserRepository.findByUsername(username);
	}
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.UserRepository.findByEmail(email);
	}
	public User findById(Long id) throws AccessDeniedException {
		return UserRepository.findById(id).orElseGet(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return UserRepository.findAll();
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setUsername(userRequest.getUsername());
		
		// pre nego sto postavimo lozinku u atribut hesiramo je kako bi se u bazi nalazila hesirana lozinka
		// treba voditi racuna da se koristi isi password encoder bean koji je postavljen u AUthenticationManager-u kako bi koristili isti algoritam
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		u.setName(userRequest.getFirstname());
		u.setSurname(userRequest.getLastname());
		u.setActive(true);
		u.setEmail(userRequest.getEmail());

		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		
		
		return this.UserRepository.save(u);
	}
	
	@Override
	public User saveSystemAdmin(User user) {
		//if(IsValidToAdd(user))
		//{
			String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
			User newUser =  this.UserRepository.save(user);
		    user.setActive(true);
		    List<Role> roles = roleService.findByName("ROLE_SYSTEM_ADMIN");
			user.setRoles(roles);
		    this.UserRepository.save(user);
			return newUser;
		
		//}

			
		//}

			
		//return null;
	}
	
	@Override
	public Long findIdByUsername(String username) {
		// TODO Auto-generated method stub
		return this.UserRepository.findIdByUsername(username);
	}


/*
	@Override
	public User findOne(Long id) 
	{
		return UserRepository.findById(id).orElseGet(null);
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> feat/COMPANY_SEARCH
	
	/*@Override
	public User findByUsername(String username) 
	{
		return UserRepository.getByUsername(username);
	}
	
	
	
	private boolean IsValidToUpdate(User user, String oldUsername)
	{
		return IsUsernameValidToUpdate(user.getUsername(), oldUsername) && IsNameValid(user.getName()) && IsNameValid(user.getSurname()) && IsPhoneNumberValid(user.getPhoneNumber())
				&& AreOtherInfoValid(user.getPassword()) && IsNameValid(user.getCity()) && IsNameValid(user.getCountry()) && IsEmploymentValid(user.getEmployment()) && AreOtherInfoValid(user.getInfoAboutInstitution());
	}
	
	
	private boolean IsValidToAdd(User user)
	{
		return IsUsernameValid(user.getUsername()) && IsNameValid(user.getName()) && IsNameValid(user.getSurname()) && IsPhoneNumberValid(user.getPhoneNumber())
				&& AreOtherInfoValid(user.getPassword()) && IsNameValid(user.getCity()) && IsNameValid(user.getCountry()) && IsEmploymentValid(user.getEmployment()) && AreOtherInfoValid(user.getInfoAboutInstitution());
	}
	
	private boolean IsUsernameValid(String username)
	{
		return (username!=null && !username.equals("") && findByUsername(username)==null);
	}
	
	private boolean IsUsernameValidToUpdate(String username, String oldUsername)
	{
		return (username!=null && !username.equals("") && (findByUsername(username)==null || username.equals(oldUsername)));
	}
	
	private boolean IsNameValid(String name)
	{
		if (name == null || name.isEmpty()) {
            return false;
        }

        String regex = "^[A-Z][A-Za-z ]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        return matcher.matches();
	}
	
	private boolean IsPhoneNumberValid(String number)
	{
		if (number == null || number.isEmpty()) {
            return false;
        }

        String regex = "^[0-9]{9,10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
		
	}
	
	private boolean AreOtherInfoValid(String info)
	{
		System.out.println(info);
		return (info!=null && !info.isEmpty());
	}
	
	private boolean IsEmploymentValid(Employment employment)
	{
		try {
            Employment toCheck = Employment.valueOf(employment.toString());
            return true;
            
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	
    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }*/




}