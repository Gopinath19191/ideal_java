/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.pc.ticket.controller;

/**
 *
 * @author 15858
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailTLS {

    public static String MAIL_USER_NAME;
    public static String MAIL_PASSWORD;
    public static String MAIL_PORT;
    public static String MAIL_HOST;

    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC) throws FileNotFoundException, IOException {
        System.out.println("OOOOOOOOOOOOOOOOOOOOOO" + mailTo);
        System.out.println("OOOOOOOOOOOOOOOOOOOOOO" + mailCC);

        Properties configFile = new Properties();
        InputStream input = null;
        input = new FileInputStream("D:\\propertyfiles\\pc_configuration.properties");
        configFile.load(input);
        System.out.println("usenane%%%%%% " + configFile.getProperty("MAIL_USER_NAME"));

        MAIL_USER_NAME = configFile.getProperty("MAIL_USER_NAME");
        MAIL_PASSWORD = configFile.getProperty("MAIL_PASSWORD");
        MAIL_HOST = configFile.getProperty("MAIL_HOST");
        MAIL_PORT = configFile.getProperty("MAIL_PORT");
        final String username = MAIL_USER_NAME;
        final String password = MAIL_PASSWORD;

        System.out.println("from config mail username %% " + username);
        System.out.println("from passwor %% " + password);
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtps.quitwait", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", MAIL_HOST);
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", "587");

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
            message.setFrom(new InternetAddress("idealadmin@hindujatech.com"));
            message.setRecipients(Message.RecipientType.TO, toList);
            message.setRecipients(Message.RecipientType.CC, ccList);


            message.setSubject(mailSubject);

            message.setContent(mailMessage, "text/html");

            Transport.send(message);

            System.out.println("Done");
            System.out.println("OOOOOOOOOOOOOOOOOOOOOO");
        } catch (MessagingException e) {
            System.out.println("Trigger Mail Exception===========" + e);
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean smtpMailRM(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC) throws FileNotFoundException, IOException, Exception {
        System.out.println("OOOOOOOOOOOOOOOOOOOOOO" + mailTo);
        System.out.println("OOOOOOOOOOOOOOOOOOOOOO" + mailCC);

        Properties configFile = new Properties();
        InputStream input = null;
        input = new FileInputStream("D:\\propertyfiles\\bulkUpload_Template\\mail_configuration.properties");
        configFile.load(input);
        System.out.println("usenane%%%%%% " + configFile.getProperty("MAIL_USER_NAME"));

        MAIL_USER_NAME = configFile.getProperty("MAIL_USER_NAME");
        MAIL_PASSWORD = configFile.getProperty("MAIL_PASSWORD");
        MAIL_HOST = configFile.getProperty("MAIL_HOST");
        MAIL_PORT = configFile.getProperty("MAIL_PORT");
        final String username = MAIL_USER_NAME;
        final String password = MAIL_PASSWORD;

        System.out.println("from config mail username %% " + username);
        System.out.println("from passwor %% " + password);
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtps.quitwait", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", MAIL_HOST);
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);

       final Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        System.out.println("mailTo = " + mailTo);
        System.out.println("mailSubject = " + mailSubject);
        System.out.println("mailMessage = " + mailMessage);

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(username, username));
        message.setSubject(mailSubject);

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPartTest);
        message.setContent(multipart);
        InternetAddress[] toList;
        InternetAddress[] ccList;

        toList = InternetAddress.parse(mailTo);
        message.setRecipients(Message.RecipientType.TO, toList);

        ccList = InternetAddress.parse(mailCC);
        message.setRecipients(Message.RecipientType.CC, mailCC);

        transport.connect(MAIL_HOST, Integer.parseInt(MAIL_PORT), username, username);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
//        System.out.println("mailMessage = " + fromAddress);
        return true;
    }
}
