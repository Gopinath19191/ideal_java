/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.system.ticket.util;

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
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class SendMailTLS {
final static Logger logger = Logger.getLogger(SendMailTLS.class);
    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC, final String username, final String password, final String host, final String port) throws FileNotFoundException, IOException {
//        final String username;
//        final String password;
//        Properties configFile=new Properties();
//        InputStream input = null;
//        input = new FileInputStream("D:\\propertyfiles\\TicketProperties.properties");
//        configFile.load(input);
//        username=configFile.getProperty("MAIL_USER_NAME");
//        password=configFile.getProperty("MAIL_PASSWORD");
        logger.info("tet user side %%%% "+username+"  passs user side "+password);
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
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
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, toList);
            message.setRecipients(Message.RecipientType.CC, ccList);
            message.setSubject(mailSubject);
            message.setContent(mailMessage, "text/html");
            final Transport transport = session.getTransport();
            transport.connect(host,Integer.parseInt(port),username,password);
//            Transport.send(message);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
