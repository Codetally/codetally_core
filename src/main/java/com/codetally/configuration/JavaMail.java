package com.codetally.configuration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
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
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.username", "gemartin@gmail.com");
        properties.setProperty("mail.smtp.password", "hockey");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port","465");
        Session session = Session.getDefaultInstance(properties);

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

