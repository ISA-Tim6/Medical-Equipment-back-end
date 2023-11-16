package com.example.medicalequipment.service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.Employment;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private final IUserRepository UserRepository;
	public UserService(IUserRepository userRepository){
    	this.UserRepository = userRepository;
    }
	/*@Override
    public User save(User u) 
	{

		System.out.println(IsValidToAdd(u));
		if(IsValidToAdd(u))
		{
			return this.UserRepository.save(u);
		}
			
		return null;
    }

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
