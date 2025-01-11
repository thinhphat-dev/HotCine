package com.phat.hotcine.Util.mail;

import com.phat.hotcine.DTO.NguoiDungDTO;
import jakarta.mail.MessagingException;


public interface MailerService {
	
	void send(MailInfo mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	MailInfo taoEmail(String email,String code);
	void queue(String to, String subject, String body);
}
