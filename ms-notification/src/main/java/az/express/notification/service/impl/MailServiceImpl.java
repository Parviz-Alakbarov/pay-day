package az.express.notification.service.impl;

import az.express.notification.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String mailTo, String subject, String message) {
        var msg = new SimpleMailMessage();
        msg.setTo(mailTo);
        msg.setText(message);
        msg.setSubject(subject);

        javaMailSender.send(msg);
    }
}
