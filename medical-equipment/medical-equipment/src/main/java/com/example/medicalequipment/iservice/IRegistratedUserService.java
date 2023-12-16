package com.example.medicalequipment.iservice;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;

@Service
public interface IRegistratedUserService {
	RegistratedUser save(RegistratedUser user) throws MailException, InterruptedException, MessagingException;
	RegistratedUser findOne(Long id);
	RegistratedUser findByUsername(String username);
	RegistratedUser update(RegistratedUser user, String oldUsername);
	RegistratedUser findByEmail(String email);
	RegistratedUser saveSystemAdmin(RegistratedUser user)
			throws MailException, InterruptedException, MessagingException;

	public User changePasswordUser(User  user, String password);
	CompanyAdmin changePassword(String password, Long id);

}
