package com.example.medicalequipment.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.medicalequipment.dto.EquipmentDto;
import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.AppointmentStatus;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.repository.IAppointmentRepository;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IEquipmentRepository;
import com.example.medicalequipment.repository.IReservationRepository;

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
	@Autowired
	private final IReservationRepository ReservationRepository;
    public CompanyService(ICompanyRepository companyRepository,IEquipmentRepository equipmentRepository,
    		ICompanyAdminRepository companyAdminRepository,IAppointmentRepository appointmentRepository,
    		IReservationRepository reservationRepository){
    	this.CompanyRepository = companyRepository;
    	this.EquipmentRepository=equipmentRepository;
    	this.CompanyAdminRepository=companyAdminRepository;
    	this.AppointmentRepository=appointmentRepository;
    	this.ReservationRepository=reservationRepository;
    }
    @Cacheable("company")
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
		List<Long> newReservationsForEquipment=ReservationRepository.getNewReservationForEquipment(equipment_id);
		if(newReservationsForEquipment.size()==0)
		{
			CompanyRepository.deleteEquipmentFromCompany(equipment_id, company_id);
			Company c=findOne(company_id);
			return c;
		}
		return null;
	}
	@Override
	public List<Company> getAll() {
		return CompanyRepository.findAll();
	}
	@Cacheable("company")
	@Override
	public Company find(Long company_id) {
		return CompanyRepository.find(company_id);
	}
	@Cacheable("company")
	@Override
	public List<Company> findByName(String name){
		return CompanyRepository.findByNameContainingIgnoreCase(name);	
	}
	@Cacheable("company")
	@Override
	public List<Company> findByAddressCity(String city) {
		return CompanyRepository.findByAddressCityContainingIgnoreCase(city);
	}
	@Cacheable("company")
	@Override
	public List<Company> findByNameAndAddressCity(String name, String city) {
		return CompanyRepository.findByNameContainingIgnoreCaseAndAddressCityContainingIgnoreCase(name, city);
	}
	@Transactional(readOnly = false,  propagation = Propagation.REQUIRES_NEW)
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

	//@Transactional(readOnly = false,  propagation = Propagation.REQUIRED)
	public Appointment saveCompanyToRepository(Company c, Appointment appointment) {
		appointment = AppointmentRepository.save(appointment);
		c.getWorkingTimeCalendar().getAppointments().add(appointment);
		CompanyRepository.save(c);
		return appointment;
	}
	
	@Transactional(readOnly = false,  propagation = Propagation.REQUIRES_NEW)
	@Override
	public Integer updateAppointment(Long company_id,Long company_admin_id,Appointment appointment) {
		AppointmentStatus reserved = AppointmentRepository.findByDateAndTime(appointment.getDate(), appointment.getTime());
		if(reserved!= null && reserved.equals(AppointmentStatus.RESERVED))
		{
			List<Reservation> stored = ReservationRepository.getByAppointmentId(appointment.getAppointment_id());
			if(stored!=null)
				ReservationRepository.delete(stored.get(1));
			return 0;
		}
			

		
		Company c=findOne(company_id);
		CompanyAdmin ca=CompanyAdminRepository.getWithCompany(company_admin_id);
		appointment.setAdmin(ca);
		appointment.setEnd(appointment.getTime().plusHours(1));
		AppointmentRepository.save(appointment);
		return 1;
	
	}
	
	@Override
	public List<LocalTime> findAvailableAppointments(String date, Long company_id) {
	    LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
	    Company company = findOne(company_id);
	    Set<Appointment> existing = company.getWorkingTimeCalendar().getAppointments();
	    List<Appointment> appointmentsForDate = getAppointmentsForDate(parsedDate, existing);
	    Collections.sort(appointmentsForDate, Comparator.comparing(Appointment::getTime));

	    List<LocalTime> freeSlots = new ArrayList<>();

	    LocalTime currentSlot = company.getOpeningHours();

	    for (Appointment appointment : appointmentsForDate) {
	        LocalTime appointmentStart = appointment.getTime();
	        LocalTime appointmentEnd = appointment.getEnd();

	        // Provera da li postoji dovoljno vremena između trenutnog slobodnog termina i narednog zakazanog termina
	        if (currentSlot.isBefore(appointmentStart)) {
	            long durationBetweenSlots = java.time.Duration.between(currentSlot, appointmentStart).toMinutes();
	            while (durationBetweenSlots >= 60) {
	                freeSlots.add(currentSlot);
	                currentSlot = currentSlot.plusHours(1);
	                durationBetweenSlots -= 60;
	            }
	        }

	        // Ažuriraj trenutni termin nakon zauzetog termina
	        if (currentSlot.isBefore(appointmentEnd)) {
	            currentSlot = appointmentEnd;
	        }
	    }

	    // Provera slobodnih termina nakon poslednjeg zakazanog termina
	    long durationTillClosing = java.time.Duration.between(currentSlot, company.getClosingHours()).toMinutes();
	    while (durationTillClosing >= 60) {
	        freeSlots.add(currentSlot);
	        currentSlot = currentSlot.plusHours(1);
	        durationTillClosing -= 60;
	    }

	    List<LocalTime> retVal = new ArrayList<LocalTime>();
	  
		    for(LocalTime time : freeSlots)
		    {
		    	if(parsedDate.getYear()==LocalDate.now().getYear() && parsedDate.getMonth()==LocalDate.now().getMonth() && parsedDate.getDayOfMonth()==LocalDate.now().getDayOfMonth()  && time.isBefore(LocalTime.now()))
		    	{
				}
		    	else
		    		retVal.add(time);
		    	
		    }

	    return retVal;
	}


	
	private List<Appointment> getAppointmentsForDate(LocalDate date, Set<Appointment> appointments) {
        List<Appointment> appointmentsForDate = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                appointmentsForDate.add(appointment);
            }
        }
        return appointmentsForDate;
    }
	
	@Override
	@Transactional(readOnly = false)
	public Long addExtraordinaryAppointment(Long company_id, Appointment appointment) {
		AppointmentStatus reserved = AppointmentRepository.findByDateAndTime(appointment.getDate(), appointment.getTime());
		if(reserved!= null && reserved.equals(AppointmentStatus.RESERVED))
			return Long.parseLong("0");
		
		
	    Company c = findOne(company_id);
	    Iterator<CompanyAdmin> iterator = c.getAdmins().iterator();
	    CompanyAdmin ca = new CompanyAdmin();
	    if (iterator.hasNext()) {
	        ca = iterator.next();
	    }
	    appointment.setAdmin(ca);
	    appointment.setEnd(appointment.getTime().plusHours(1));

	    LocalTime openingHours = c.getOpeningHours();
	    LocalTime closingHours = c.getClosingHours().minusMinutes(1); // Jedan minut pre zatvaranja

	    boolean startsAtOpening = appointment.getTime().equals(openingHours);
	    boolean endsAtClosing = appointment.getEnd().equals(closingHours);

	    if ((endsAtClosing || appointment.getEnd().isBefore(closingHours)) && (startsAtOpening || appointment.getTime().isAfter(openingHours))) {
	        List<Long> overlappingStarts = AppointmentRepository.getAllOverlappingStart(company_id, appointment.getTime(), appointment.getEnd(), appointment.getDate());
	        List<Long> overlappingEnds = AppointmentRepository.getAllOverlappingEnd(company_id, appointment.getTime(), appointment.getEnd(), appointment.getDate());

	        if (overlappingStarts.isEmpty() && overlappingEnds.isEmpty()) {
	        	appointment = AppointmentRepository.save(appointment);
	    		c.getWorkingTimeCalendar().getAppointments().add(appointment);
	    		CompanyRepository.save(c);
	            return appointment.getAppointment_id();
	        }
	    }
	    return Long.parseLong("0");
	}
	
	@Cacheable("company")
	@Override
	public Company findOneByName(String name)
	{
		List<Company> stored = CompanyRepository.findByName(name);
		return stored.isEmpty() ? null : stored.get(0);	}

	
}
