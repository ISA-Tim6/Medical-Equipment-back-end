package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.QRCode;

@Repository
public interface IQRCodeRespository extends JpaRepository<QRCode,Long> {
    //@Query("SELECT qr FROM QRCode qr WHERE qr.appointmentId = ?1")
    //QRCode findByAppointmentId(Long appointmentId);

    @Query("SELECT qr FROM QRCode qr WHERE qr.user.user_id = ?1")
    List<QRCode> findByUserId(Long userId);


}
