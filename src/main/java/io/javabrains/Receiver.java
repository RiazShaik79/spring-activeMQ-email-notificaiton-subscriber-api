package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@Autowired
	private EmailService emailService;
	
  @JmsListener(destination = "mailbox", containerFactory = "myFactory")
  public void receiveMessage(Email email) {
	  
    System.out.println("Received <" + email + ">");
    try {
    emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getBody());
    } catch(Exception e) {
    	System.out.println("Email Exception : " + e);
    }
    
  }

}