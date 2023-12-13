package com.example.medicalequipment.service;


import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.AppointmentStatus;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.repository.IAppointmentRepository;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IEquipmentRepository;

@Service
public class CompanyService implements ICompanyService{
	@Autowired
	private final ICompanyRepository CompanyRepository;
	@Autowired
	private final IEquipmentRepository EquipmentRepository;
	@Autowired
	private final ICompanyAdminRepository CompanyAdminRepository;
	@Autowired
	private final IAppointmentRepository AppointmentRepository;
    public CompanyService(ICompanyRepository companyRepository,IEquipmentRepository equipmentRepository,
    		ICompanyAdminRepository companyAdminRepository,IAppointmentRepository appointmentRepository){
    	this.CompanyRepository = companyRepository;
    	this.EquipmentRepository=equipmentRepository;
    	this.CompanyAdminRepository=companyAdminRepository;
    	this.AppointmentRepository=appointmentRepository;
    }
	@Override
	public Company findOne(Long id) {
		return CompanyRepository.findById(id).orElseGet(null);
	}
	@Override
	public Company save(Company company) {
		return CompanyRepository.save(company);
	}
	
	@Override 
	public Company addEquipment(Equipment equipment,Long company_id) {
		Equipment e=new Equipment(equipment.getDescription(),equipment.getName(),equipment.getType(),equipment.getPrice(),equipment.getQuantity());
		e.setCompanies(new HashSet<Company>());
		Company company=CompanyRepository.findById(company_id).orElseGet(null);
		e = EquipmentRepository.save(e);
		e.getCompanies().add(company);
		if(company!=null)
			company.addEquipment(e);
		return CompanyRepository.save(company);
	}
	@Override
	public Company removeEquipment(Long company_id, Long equipment_id) {
		CompanyRepository.deleteEquipmentFromCompany(equipment_id, company_id);
		Company c=findOne(company_id);
		return c;
	}
	@Override
	public List<Company> getAll() {
		return CompanyRepository.findAll();
	}

	@Override
	public Company find(Long company_id) {
		return CompanyRepository.find(company_id);
	}
	@Override
	public List<Company> findByName(String name){
		return CompanyRepository.findByNameContainingIgnoreCase(name);	
	}
	
	@Override
	public List<Company> findByAddressCity(String city) {
		return CompanyRepository.findByAddressCityContainingIgnoreCase(city);
	}
	
	@Override
	public List<Company> findByNameAndAddressCity(String name, String city) {
		return CompanyRepository.findByNameContainingIgnoreCaseAndAddressCityContainingIgnoreCase(name, city);
	}
	@Override
	public Integer addAppointment(Long company_id, Long company_admin_id, Appointment appointment) {
		Company c=findOne(company_id);
		CompanyAdmin ca=CompanyAdminRepository.getWithCompany(company_admin_id);
		appointment.setAdmin(ca);
		appointment.setAppointmentStatus(AppointmentStatus.AVAILABLE);
		appointment.setEnd(appointment.getTime().plusHours(1));
		if(appointment.getEnd().isBefore(c.getClosingHours()) && appointment.getTime().isAfter(c.getOpeningHours()))
		{
			List<Long> overLapingStarts=AppointmentRepository.getAllOverlappingStart(company_id, appointment.getTime(), appointment.getEnd(), appointment.getDate());
			List<Long> overLapingEnds=AppointmentRepository.getAllOverlappingEnd(company_id, appointment.getTime(), appointment.getEnd(), appointment.getDate());

			if(overLapingStarts.size()==0 && overLapingEnds.size()==0)
				{
					appointment = AppointmentRepository.save(appointment);
					c.getWorkingTimeCalendar().getAppointments().add(appointment);
					CompanyRepository.save(c);
					return 2;
				}
			else 
				return 0;

		}
		else 
			return 1;


	}
	
}
