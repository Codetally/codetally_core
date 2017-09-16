package com.codetally.configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by greg on 16/09/17.
 */
public class JavaMail {
    public static void SendMail(String ownername) {

        //Get the session object
        Properties properties = System.getProperties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("gemartin@gmail.com", "hockey");
                    }
                });

        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gemartin@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gemartin@gmail.com"));
            message.setSubject("Ping");
            message.setText("Hello, "+ownername+" just synched repos.");
            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

