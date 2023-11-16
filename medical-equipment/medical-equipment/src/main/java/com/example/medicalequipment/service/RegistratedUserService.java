package com.example.medicalequipment.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IRegistratedUserService;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.Employment;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class RegistratedUserService implements IRegistratedUserService {

	private final IRegistratedUserRepository UserRepository;
	private final EmailService EmailService;
	@Autowired
    public RegistratedUserService(IRegistratedUserRepository userRepository,EmailService emailService){
    	this.UserRepository = userRepository;
    	this.EmailService=emailService;
    }
	
	@Override
	public RegistratedUser save(RegistratedUser user) throws MailException, InterruptedException {
		if(IsValidToAdd(user))
		{
			user.setPenals(0);
			user.setCategory(Category.REGULAR);
			EmailService.sendNotificaitionSync(user);
			return this.UserRepository.save(user);
		}
			
		return null;
	}
	
	public RegistratedUser getByEmail(String email){
		return UserRepository.findByEmail(email);
	}

	@Override
	public RegistratedUser findOne(Long id) {
		return UserRepository.findById(id).orElseGet(null);
	}

	@Override
	public RegistratedUser findByUsername(String username) {
		return UserRepository.getByUsername(username);
	}

	@Override
	public RegistratedUser update(RegistratedUser user, String oldUsername) {
		if(IsValidToUpdate(user, oldUsername))
		{
			user.setPenals(0);
			user.setCategory(Category.REGULAR);	//popraviti kasnije
			return this.UserRepository.save(user);
		}
			
		return null;
	}
	
	@Override
	public RegistratedUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.UserRepository.findByEmail(email);
	}
	
	
	
	
	
	
	
	
	
	//Validation
	private boolean IsValidToUpdate(RegistratedUser user, String oldUsername)
	{
		return IsUsernameValidToUpdate(user.getUsername(), oldUsername) && IsNameValid(user.getName()) && IsNameValid(user.getSurname()) && IsPhoneNumberValid(user.getPhoneNumber())
				&& AreOtherInfoValid(user.getPassword()) && IsNameValid(user.getCity()) && IsNameValid(user.getCountry()) && IsEmploymentValid(user.getEmployment()) && AreOtherInfoValid(user.getInfoAboutInstitution());
	}
	
	
	private boolean IsValidToAdd(RegistratedUser user)
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

	

}
