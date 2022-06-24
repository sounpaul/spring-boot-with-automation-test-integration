package com.cg.employee.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Pattern;

public class EmailUtils {

    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    public static void sendEmail(String htmlContent) {
        final String username = System.getProperty("username"); //replace with your username
        final String password = System.getProperty("password"); //replace with your password
        String toAddress = System.getProperty("recipients");
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("BDD Test Results : " + BDDUtils.getCurrentDateTime());
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Email send to : " + toAddress);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
