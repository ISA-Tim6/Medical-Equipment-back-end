package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.CanceledAppointment;
import com.example.medicalequipment.repository.ICanceledAppointmentRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;

@Service
public class CanceledAppointmentService {
	private final ICanceledAppointmentRepository canceledApointmentRepository;
    private final IRegistratedUserRepository userRepository;

//    private final AppointmentService appointmentService;

    public CanceledAppointmentService(ICanceledAppointmentRepository canceledApointmentRepository,
                                      IRegistratedUserRepository userRepository) {
        this.canceledApointmentRepository = canceledApointmentRepository;
        this.userRepository = userRepository;

    }

    public void save(CanceledAppointment apointments){
        canceledApointmentRepository.save(apointments);
    }

    public List<CanceledAppointment>findByUserId(Long userId){
        return canceledApointmentRepository.findAllByUserId(userId);
    }
}
