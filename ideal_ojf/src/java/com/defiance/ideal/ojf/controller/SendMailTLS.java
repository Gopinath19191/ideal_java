/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.controller;

/**
 *
 * @author 15858
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.BodyPart;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
 
public  class SendMailTLS {
 public static  String MAIL_USER_NAME;
 public static  String MAIL_PASSWORD;
 public static  String MAIL_HOST;
 public static  String MAIL_PORT;
	public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC) throws FileNotFoundException, IOException {
            System.out.println("OOOOOOOOOOOOOOOOOOOOOO"+mailTo);
            System.out.println("OOOOOOOOOOOOOOOOOOOOOO"+mailCC);
                
            Properties configFile=new Properties();
            InputStream input = null;
            input = new FileInputStream("D:\\propertyfiles\\ojfconfiguration.properties");
            configFile.load(input);
            System.out.println("usenane%%%%%% "+configFile.getProperty("MAIL_USER_NAME"));
             
            MAIL_USER_NAME= configFile.getProperty("MAIL_USER_NAME");
            MAIL_PASSWORD= configFile.getProperty("MAIL_PASSWORD");
            MAIL_HOST= configFile.getProperty("MAIL_HOST");
            MAIL_PORT= configFile.getProperty("MAIL_PORT");
            final String username =MAIL_USER_NAME;
            final String password =MAIL_PASSWORD;  
                  
            System.out.println("from config mail username %% "+username);
            System.out.println("from passwor %% "+password);
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", MAIL_HOST);
            props.put("mail.smtp.host", MAIL_HOST);
            props.put("mail.smtp.port", MAIL_PORT);
 
            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                          return new PasswordAuthentication(username, password);
                  }
            });
 
            try {
                InternetAddress[] toList;
                InternetAddress[] ccList;
                toList = InternetAddress.parse(mailTo);
                ccList = InternetAddress.parse(mailCC);

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ideal.ojf@hindujatech.com"));
                message.setRecipients(Message.RecipientType.TO, toList);
                message.setRecipients(Message.RecipientType.CC, ccList);
                message.setSubject(mailSubject);
                message.setContent(mailMessage,"text/html" ); 
                Transport.send(message);
                System.out.println("Done");
                System.out.println("OOOOOOOOOOOOOOOOOOOOOO");
            } catch (MessagingException e) {
                System.out.println("Trigger Mail Exception===========" +e);
                throw new RuntimeException(e); 
            }
            return true;
	}
}
