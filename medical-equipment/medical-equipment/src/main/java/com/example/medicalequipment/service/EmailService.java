package com.example.medicalequipment.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.iservice.IEmailService;
import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;
@Service
public class EmailService implements IEmailService{
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;
	public void sendVerificationEmail(RegistratedUser user)
			throws MailException, InterruptedException, MessagingException {
		System.out.println(user.getVerificationCode());
	    String toAddress = user.getEmail();
	    String fromAddress = "Your email address";
	    String senderName = "Your company name";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<a href=\"http://localhost:81/api/auth/verify/" + user.getVerificationCode() + "\">Activate Account</a>" 
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setTo(user.getEmail());
        helper.setSubject(subject); 
	    content = content.replace("[[name]]", user.getName());
	    
	    
	    helper.setText(content, true);
	     
	    javaMailSender.send(message);
	     
	}
	public void sendConfirmationEmail(Reservation reservation,String mail, byte[] qrCodeImage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo("kivana0191@gmail.com");
            helper.setSubject("Potvrda rezervacije");
            
            // Dodajte informacije o rezervaciji u tekst emaila
            String emailText = String.format("Hvala vam, na vašoj rezervaciji. Detalji:\nDatum: \nMjesto: \n...");

            helper.setText(mail, true); // true označava da je tekst HTML, možete koristiti false ako koristite običan tekst

            // Dodaj privitak s QR kodom
            helper.addAttachment("QRCode.png", new ByteArrayResource(qrCodeImage));

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Postupajte s izuzetkom, npr. ispišite ga u konzolu ili logirajte
            e.printStackTrace();
        }
    }
	
	public void sendDeliveryEmail(String mail) {
        try {
    	    MimeMessage message = javaMailSender.createMimeMessage();
    	    MimeMessageHelper helper = new MimeMessageHelper(message,true);
            
            helper.setTo("milicavujic2001@gmail.com");
            helper.setSubject("Isporuka rezervisane opreme");
            
            // Dodajte informacije o rezervaciji u tekst emaila
            String emailText = String.format("Vaša rezervacija je uspešno poslata");

            helper.setText(mail, true); // true označava da je tekst HTML, možete koristiti false ako koristite običan tekst

            javaMailSender.send(message);
            

        } catch (MessagingException e) {
            // Postupajte s izuzetkom, npr. ispišite ga u konzolu ili logirajte
            e.printStackTrace();
            System.out.println(e);
        }
    }
	
}
