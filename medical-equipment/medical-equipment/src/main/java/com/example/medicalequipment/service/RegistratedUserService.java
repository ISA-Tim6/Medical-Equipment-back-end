package com.example.medicalequipment.service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IRegistratedUserService;
import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Employment;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.model.Role;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IRoleRepository;
import com.example.medicalequipment.repository.IUserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class RegistratedUserService implements IRegistratedUserService {
	@Autowired
	private final IRegistratedUserRepository RegistratedUserRepository;
	@Autowired
	private final IUserRepository UserRepository;
	@Autowired
	private final IActivationTokenRepository TokenRepository;
	@Autowired
	private final EmailService EmailService;
	@Autowired
	private final IRoleRepository RoleRepository;
	@Autowired
	private final RoleService RoleService;
	@Autowired
    public PasswordEncoder passwordEncoder;
	@Autowired
	private ICompanyAdminRepository CompanyAdminRepository;

    public RegistratedUserService(IRegistratedUserRepository registratedUserRepository,EmailService emailService,IActivationTokenRepository tokenRepository, IUserRepository userRepository, IRoleRepository roleRepository, RoleService roleService,
    		ICompanyAdminRepository companyAdminRepository){
    	this.RegistratedUserRepository = registratedUserRepository;
		this.UserRepository = userRepository;
		this.TokenRepository = tokenRepository;
    	this.EmailService=emailService;
		this.RoleRepository = roleRepository;
		this.RoleService = roleService;
		this.CompanyAdminRepository=companyAdminRepository;
    }
	
	@Override
	public RegistratedUser save(RegistratedUser user) throws MailException, InterruptedException, MessagingException {
		//if(IsValidToAdd(user))
		//{
			String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
			user.setPenals(0);
			user.setCategory(Category.REGULAR);
			RegistratedUser newUser =  this.RegistratedUserRepository.save(user);
			String randomCode = RandomString.make(64);
		    user.setVerificationCode(randomCode);
		    user.setActive(false);
		    List<Role> roles = RoleService.findByName("ROLE_REGISTRATED_USER");
			System.out.println(roles+"---------");
			user.setRoles(roles);
		    this.RegistratedUserRepository.save(user);
			EmailService.sendVerificationEmail(user);
			return newUser;
		
		//}

			
		//}

			
		//return null;
	}
	
	
	@Override
	public User changePasswordUser(User  user, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		user.setLoggedBefore(true);
		UserRepository.save(user);
		return user;
	}
	
	@Override
	public RegistratedUser saveSystemAdmin(RegistratedUser user) throws MailException, InterruptedException, MessagingException {
		//if(IsValidToAdd(user))
		//{
			String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
			user.setPenals(0);
			user.setCategory(Category.REGULAR);
			RegistratedUser newUser =  this.RegistratedUserRepository.save(user);
			String randomCode = RandomString.make(64);
		    user.setVerificationCode(randomCode);
		    user.setActive(true);
		    List<Role> roles = RoleService.findByName("ROLE_SYSTEM_ADMIN");
			System.out.println(roles+"---------");
			user.setRoles(roles);
		    this.RegistratedUserRepository.save(user);
			return newUser;
		
		//}

			
		//}

			
		//return null;
	}
	public RegistratedUser getByEmail(String email){
		return RegistratedUserRepository.findByEmail(email);
	}

	@Override
	public RegistratedUser findOne(Long id) {
		RegistratedUser user=RegistratedUserRepository.findById(id).orElseGet(null);
		
		return RegistratedUserRepository.findById(id).orElseGet(null);
	}

	@Override
	public RegistratedUser findByUsername(String username) {
		return RegistratedUserRepository.getByUsername(username);
	}

	@Override
	public RegistratedUser update(RegistratedUser user, String oldUsername) {
		//if(IsValidToUpdate(user, oldUsername))
		//{
			if(!user.getPassword().startsWith("$")) {
				String encodedPassword = passwordEncoder.encode(user.getPassword());
		        user.setPassword(encodedPassword);
			}
			user.setPenals(user.getPenals());
			user.setCategory(Category.REGULAR);	//popraviti kasnije
			user.setActive(true);
			List<Role> roles = RoleService.findByName("ROLE_REGISTRATED_USER");
			System.out.println(roles+"---------");
			user.setRoles(roles);
			return this.RegistratedUserRepository.save(user);
		//}
			
		//return null;
	}
	
	@Override
	public RegistratedUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return this.RegistratedUserRepository.findByEmail(email);
	}
	public boolean verify(String verificationCode) {
	    RegistratedUser user = this.RegistratedUserRepository.findByVerificationCode(verificationCode);
	     
	    if (user == null || user.isActive()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setActive(true);
	        RegistratedUserRepository.save(user);
	         
	        return true;
	    }
	     
	}
	@Override
	public CompanyAdmin changePassword(String password, Long id) {
		CompanyAdmin ca=CompanyAdminRepository.getWithCompany(id);	
		String encodedPassword = passwordEncoder.encode(password);
        ca.setPassword(encodedPassword);
        ca.setLoggedBefore(true);
        ca.setActive(true);
        return CompanyAdminRepository.save(ca);
	}
	
	public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Dohvatanje korisničkog imena
        RegistratedUser user = getByEmail(username);
        System.out.print("=============================="+user.getEmail()+"\n");
        // Možete dohvatiti više informacija kao što su role, authorities, itd.
        return user;
	}
	
	public RegistratedUser getCurrentRegisteredUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Dohvatanje korisničkog imena
        RegistratedUser user = findByUsername(username);
        //System.out.print("=============================="+user.getEmail()+"\n");
        // Možete dohvatiti više informacija kao što su role, authorities, itd.
        return user;
    }
	
	@Scheduled(cron = "0 0 0 1 * ?")//u ponoc prvog u mjesecu
	 public void resetPenals() {
		List<RegistratedUser> all = RegistratedUserRepository.findAllUsers();
		for (RegistratedUser user : all)
		{
			user.setPenals(0);
			update(user, user.getUsername());
		}
	 }
}

	

