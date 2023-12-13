package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.iservice.ICompanyAdminService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Role;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IUserRepository;


@Service
 public class CompanyAdminService implements ICompanyAdminService{

	
	@Autowired
	private final ICompanyRepository CompanyRepository;
	@Autowired
	private final RoleService RoleService;
	
	@Autowired
	private final ICompanyAdminRepository CompanyAdminRepository;
	@Autowired
    public PasswordEncoder passwordEncoder;
	public CompanyAdminService(ICompanyAdminRepository companyAdminRepository, ICompanyRepository companyRepository, RoleService roleService){

    	this.RoleService = roleService;
		this.CompanyAdminRepository = companyAdminRepository;
    	this.CompanyRepository = companyRepository;
    }
	
	@Override
	public CompanyAdmin create(CompanyAdmin companyAdmin) {
		List<Role> roles = RoleService.findByName("ROLE_COMPANY_ADMIN");
		System.out.println(roles+"---------");
		String encodedPassword = passwordEncoder.encode(companyAdmin.getPassword());
		companyAdmin.setPassword(encodedPassword);
		companyAdmin.setRoles(roles);
		companyAdmin.getCompany().add(companyAdmin);
		companyAdmin.setActive(true);
		return this.CompanyAdminRepository.save(companyAdmin);
	}
	
	@Override
	public CompanyAdmin save(CompanyAdmin companyAdmin) {
		List<Role> roles = RoleService.findByName("ROLE_COMPANY_ADMIN");
		System.out.println(roles+"---------");
		companyAdmin.setRoles(roles);
		companyAdmin.setActive(true);
		String encodedPassword = passwordEncoder.encode(companyAdmin.getPassword());
		companyAdmin.setPassword(encodedPassword);
		return CompanyAdminRepository.save(companyAdmin);
		
	}
	
	@Override
	public CompanyAdmin findOne(Long id) {
		 return this.CompanyAdminRepository.getWithCompany(id);		
	}
	

	public CompanyAdmin createWithCompany(CompanyAdmin admin, Long id) {
		admin.getCompany().setId(id);
		List<Role> roles = RoleService.findByName("ROLE_COMPANY_ADMIN");
		System.out.println(roles+"---------");
		admin.setRoles(roles);
		admin.setActive(true);
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		admin.getCompany().add(admin);
		return this.CompanyAdminRepository.save(admin);
	}
	@Override
	public List<Long> getOtherCompanyAdminsForCompany(Long company_id, Long user_id){
		return this.CompanyAdminRepository.getOtherCompanyAdminsForCompany(company_id, user_id);

	}
}
