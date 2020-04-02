package pl.mzuchnik.springpracadomowa6.service;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);
}
