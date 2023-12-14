package com.example.medicalequipment.dto;


import java.util.Date;

public class QRCodeDto {
    private byte[] image;
    private Long reservationId;

    public QRCodeDto(byte[] image, Long reservationId) {
        this.image = image;
        this.reservationId=reservationId;
    }

    public QRCodeDto() {

    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    
    public byte[] getImage() {
        return image;
    }

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

    
}
