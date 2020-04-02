package pl.mzuchnik.springpracadomowa6.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mzuchnik.springpracadomowa6.model.Movie;
import pl.mzuchnik.springpracadomowa6.service.EmailService;

@Aspect
@Component
public class EmailAspect {

    private EmailService emailService;

    @Autowired
    public EmailAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @After("@annotation(pl.mzuchnik.springpracadomowa6.aop.annotation.SendEmailAfter) && args(movie)")
    public void sendMessage(Movie movie){
        emailService.sendSimpleMessage("email@email.com","PostMethod","Dodałeś element " + movie.toString());
    }
}
