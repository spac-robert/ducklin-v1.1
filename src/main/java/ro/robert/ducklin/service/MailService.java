package ro.robert.ducklin.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ro.robert.ducklin.dto.EmailNotification;

import javax.annotation.Resource;

@Service
public class MailService {

    public JavaMailSender mailSender;
    public MailContentBuilder mailContentBuilder;

    @Autowired
    public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Async
    public void sendEmail(EmailNotification notificationEmail) throws Exception {
        MimeMessagePreparator messagePreparatory = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("spring.ducklin@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparatory);
            LOGGER.info("Activation email!");
        } catch (MailException e) {
            throw new Exception("Exception occurred when sending email to " + notificationEmail.getRecipient());
        }
    }
}
