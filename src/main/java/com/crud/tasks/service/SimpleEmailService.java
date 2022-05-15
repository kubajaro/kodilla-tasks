package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Preparing e-mail");
        try {
            SimpleMailMessage mailMessage = createSimpleMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("E-mail has been sent");
        } catch (MailException e) {
            log.error("Failed to process e-mail" + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createSimpleMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailReceiver());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc() != null && !mail.getToCc().isEmpty()){
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
