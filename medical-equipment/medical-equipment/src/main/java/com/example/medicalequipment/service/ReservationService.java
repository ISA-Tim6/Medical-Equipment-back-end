package com.example.medicalequipment.service;

import java.util.ArrayList;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.dto.ReservationDto;
import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.model.ReservationStatus;
import com.example.medicalequipment.repository.IEquipmentRepository;
import com.example.medicalequipment.repository.IItemRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IReservationRepository;
import com.google.zxing.WriterException;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private final IReservationRepository ReservationRepository;
	@Autowired
	private final IEquipmentRepository EquipmentRepository;
	@Autowired
	private final EmailService emailService;
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/images/QRCode.png";
	public ReservationService(IReservationRepository reservationRepository, EmailService emailService, IEquipmentRepository equipmentRepository){
    	this.ReservationRepository = reservationRepository;
		this.emailService = emailService;
		this.EquipmentRepository=equipmentRepository;
    }
	private String generateReservationDetails(Reservation reservation) {
		//registratedUserService.
		String date[] = reservation.getAppointment().getDate().toString().split(" ");
		Reservation newReservation=ReservationRepository.getFullReservation(reservation.getReservation_id()).get(0);
		System.out.println("dur:"+newReservation.getAppointment().getDuration());
		String appDetails = "Reservation details \n"
				+ "Appointment date time: "+ newReservation.getAppointment().getDate()+", "+newReservation.getAppointment().getTime()+"\n"
				+ "Appointment duration: 60min"+"\n"
				+ "Admin: " + newReservation.getAppointment().getAdmin().getName()+" "+newReservation.getAppointment().getAdmin().getSurname() + "\n"
				+ "Company: " + newReservation.getAppointment().getAdmin().getCompany().getName()+"\n"
				+ "User: " + newReservation.getUser().getName()+" "+newReservation.getUser().getSurname()+"\n"
				+ "Equipment: ";
		
		Iterator<Item> iter=newReservation.getItems().iterator();
		for(int i=0;i<newReservation.getItems().size();i++) {
			appDetails+=iter.next().getEquipment().getName()+",";
		}

		return appDetails;
	}

	private String generateEmailMessage(Reservation reservation) {
		String date[] = reservation.getAppointment().getDate().toString().split(" ");
		Reservation newReservation=ReservationRepository.getFullReservation(reservation.getReservation_id()).get(0);
		String message = "Hi. You are successfuly scheduled appointment in our center."
				+ " You appointment are on "+newReservation.getAppointment().getDate().toString()+" at " + newReservation.getAppointment().getTime().toString() + ".";
		return message;
	}
	@Override
	public Reservation save(Reservation reservation) throws MailException, InterruptedException, MessagingException {
		// TODO Auto-generated method stub
		Reservation newReservation=ReservationRepository.save(reservation);
		String qrCodeData = generateReservationDetails(newReservation);
		String mail=generateEmailMessage(newReservation);
		byte[] qrCodeImageBytes = null;
		try {
			QRCodeGenerator.generateQRCodeImage(qrCodeData,250,250, QR_CODE_IMAGE_PATH);
		}catch (WriterException | IOException e) {}
		try {
	        qrCodeImageBytes = QRCodeGenerator.getQRCodeImage(qrCodeData, 250, 250,"mobilenotes://compose");
	    } catch (WriterException | IOException e) {
	        // Postupajte s izuzetkom, npr. ispišite ga u konzolu ili logirajte
	        e.printStackTrace();
	    }
		emailService.sendConfirmationEmail(newReservation,mail, qrCodeImageBytes);
		return newReservation;
	}
	@Override
	public List<Reservation> getFullReservation(Long id) {
		return ReservationRepository.getFullReservation(id);
	}
	@Override
	public List<Appointment> getAllUserReservation(Long id) {
		List<Reservation> storedUserReservation=ReservationRepository.getAllUserReservation(id);
		List<Appointment> userReservation=new ArrayList<Appointment>();
		for(Reservation r : storedUserReservation) {
			if(r.getAppointment().getDate().isAfter(LocalDate.now())) {
				userReservation.add(r.getAppointment());
				System.out.println("usao u rezervaciju");
			
			}
		}
		return userReservation;
	}
	
	public List<ReservationDto> getAllByCompany(Long company_id){
		List<ReservationDto> result = new ArrayList<ReservationDto>();
		List<Reservation> reservations = ReservationRepository.getAllByCompany(company_id);
		if(!reservations.isEmpty())
		{
			for (Reservation reservation : reservations) {
				result.add(new ReservationDto(reservation.getReservation_id(), reservation.getUser(), reservation.getItems(), reservation.getAppointment(), reservation.getReservationStatus().toString()));
			}
		}
		return result;
	}
	@Override
	public ReservationDto getNewByCompanyAdmin(Long user_id){
		ReservationDto result = null;
		List<Reservation> reservations=ReservationRepository.getAllByCompanyAdmin(user_id);


		for(Reservation reservation: reservations) {
			boolean isToday=reservation.getAppointment().getDate().isEqual(LocalDate.now());
			boolean isAfter=reservation.getAppointment().getDate().isAfter(LocalDate.now());
			if(reservation!=null && (isToday||isAfter) && reservation.getReservationStatus()==ReservationStatus.NEW)
				result=new ReservationDto(reservation.getReservation_id(), reservation.getUser(), reservation.getItems(), reservation.getAppointment(), reservation.getReservationStatus().toString());
		}
		
		return result;
	}
	@Override
	@Transactional(readOnly = false,  propagation = Propagation.REQUIRES_NEW)
	public ReservationDto DeliverReservation(Long id){
		Long Id=null;
		String mailText="Poštovani, oprema koju ste rezervisali Vam je isporučena.\n"
				+ "Oprema: ";
		
		List<Reservation> reservations=ReservationRepository.getFullReservation(id);
		for(Reservation reservation:reservations)
		{
			Id=reservation.getAppointment().getAdmin().getUser_id();
			reservation.setReservationStatus(ReservationStatus.ACCEPTED);
			ReservationRepository.save(reservation);
					for(Item i:reservation.getItems())
					{
						mailText+=i.getEquipment().getName();
						mailText+=" ";
						mailText+=i.getEquipment().getDescription();
						mailText+=" ,količina: ";
						mailText+=i.getQuantity();
					}
			this.UpdateEquipmentQuantity(reservation);
		}
		emailService.sendDeliveryEmail(mailText);
		
		return getNewByCompanyAdmin(Id);
	}

	@Transactional(readOnly = false)
	public void UpdateEquipmentQuantity(Reservation reservation)
	{
		for(Item i:reservation.getItems())
		{
			Equipment equipment=i.getEquipment();
			Integer oldQuantity=equipment.getQuantity();
			Integer newQuantity=oldQuantity-i.getQuantity();
			equipment.setQuantity(newQuantity);
			EquipmentRepository.save(equipment);
			
		}
	}
	
	private boolean hasExpired(Reservation reservation) {
		return reservation.getAppointment().getDate().isBefore(LocalDate.now()) || (reservation.getAppointment().getDate().isEqual(LocalDate.now()) && reservation.getAppointment().getEnd().isBefore(LocalTime.now()));
	}
	
	@Scheduled(cron = "0 0 * * * *")
	public void checkExpiration() {
		List<Reservation> reservations = ReservationRepository.getNotRejected();
		for(var r: reservations)
		{
			if(hasExpired(r))
			{
				r.setReservationStatus(ReservationStatus.REJECTED);
				ReservationRepository.save(r);
			}
		}
	}
}
