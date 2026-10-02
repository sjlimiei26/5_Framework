package com.kh.easymail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	
	private final JavaMailSender sender;
	
	public void sendCode(String email) throws MessagingException {
		
		String subject = "[KH] 인증 코드";
		/*
		String text = "즐거운 금요일입니다. 인증 코드는 기다려주세요...";
		
		sendMail(subject, text, email);
		*/
		
		String text = """
				<h2>즐거운 금요일입니다.</h2>
				<p>인증 코드는 추후 발송될 예정입니다..;-;</p>
				""";
		String[] to = { email };
		sendHTMLMail(subject, text, to);
	}
	
	/**
	 * 메일 전송 메소드
	 * @param subject 메일 제목
	 * @param text	  메일 내용
	 * @param to	  받는 사람
	 */
	private void sendMail(String subject, String text, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setSubject(subject);
		message.setText(text);
		message.setTo(to);
		
		sender.send(message);
	}
	
	/**
	 * HTML 형식의 메일 전송
	 * 
	 * @param subject    메일 제목
	 * @param text		 메일 내용
	 * @param to		 받는 사람
	 * @throws MessagingException
	 */
	private void sendHTMLMail(String subject, String text, String[] to) throws MessagingException {
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm);
		
		helper.setSubject(subject);
		helper.setText(text, true);
		helper.setTo(to);
		
		sender.send(mm);
	}

}





