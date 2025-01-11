package com.phat.hotcine.Util.mail;

import com.phat.hotcine.DTO.NguoiDungDTO;
import com.phat.hotcine.Util.RandomCodeGenerator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class MailerServiceImpl implements MailerService {

	@Autowired
	JavaMailSender sender;

	List<MailInfo> list = new ArrayList<>();

	@Override
	public void send(MailInfo mail) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = sender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		helper.setReplyTo(mail.getFrom());

		if(mail.getCc() != null && mail.getCc().length > 0) {
			helper.setCc(mail.getCc());
		}

		if(mail.getBcc() != null && mail.getBcc().length > 0) {
			helper.setBcc(mail.getBcc());
		}

		if(mail.getAttachments() != null && mail.getAttachments().length > 0) {
			for(String path:mail.getAttachments()) {
				File file = new File(path);
				helper.addAttachment(file.getName(), file);
			}
		}

		sender.send(message);
	}

	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		// TODO Auto-generated method stub
		this.send(new MailInfo(to, subject, body));
	}

	@Override
	public void queue(MailInfo mail) {
		// TODO Auto-generated method stub
		list.add(mail);
	}

	@Override
	public MailInfo taoEmail(String email, String code) {
		MailInfo mail = new MailInfo();
		mail.setTo(email);
		mail.setSubject("YÊU CẦU KHÔI PHỤC MẬT KHẨU - HotCine");
		StringBuilder body = new StringBuilder();
		body.append("<h1>Mã xác nhận</h1>");
		body.append("<h1>"+code+"</h1>");
		mail.setBody(body.toString());
		mail.setFrom("HotCine");
		return mail;
	}

	@Override
	public void queue(String to, String subject, String body) {
		// TODO Auto-generated method stub
		queue(new MailInfo(to, subject, body));
	}

	@Scheduled(fixedDelay = 5000)
	public void run() {
		while(!list.isEmpty()) {
			MailInfo mail = list.remove(0);
			try {
				this.send(mail);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
