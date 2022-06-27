package az.express.notification.service;

public interface MailService {

    void sendMail(String mailTo, String subject, String message);

}
