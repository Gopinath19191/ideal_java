/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.travelplan.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Admin
 */
public class SendMail {
    static Properties emailProps;
    String fromAddress ;
    String mailPassword;
    String mailHost;
    String mailType;
    String mailPort;
    String fromName;
    PasswordAuthentication auth;
    private MimeBodyPart messageBodyPart;

    public SendMail(String username,String password,String host,String port) throws FileNotFoundException, IOException{
        System.out.println("fromAddress+++++++++"+username);
        this.fromAddress = username;
        this.mailPassword = password;
        this.mailHost = host;
        this.mailPort = port;
        mailType      = "smtp";
        fromName      = "Ideal Admin";

            System.out.println("fromAddress = " + fromAddress);
            System.out.println("mailPassword = " + mailPassword);
            System.out.println("mailHost = " + mailHost);
            System.out.println("mailPort = " + mailPort);
            System.out.println("mailType = " + mailType);
        
        emailProps = new Properties();

        emailProps.put("mail.transport.protocol",mailType);
        emailProps.put("mail.smtps.host",mailHost);
        emailProps.put("mail.smtps.auth","true");
        emailProps.put("mail.smtp.ssl.trust", mailHost);
        emailProps.put("mail.smtps.quitwait", "false");
        emailProps.put("mail.smtp.starttls.enable", "true");
        emailProps.put("mail.smtp.port",mailPort);
  }

    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC) throws Exception {
        System.out.println("emailProps = " + emailProps);
        final Session mailSession = Session.getInstance(emailProps,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                { return new PasswordAuthentication(fromAddress,mailPassword);	}
        });

        System.out.println("mailTo = " + mailTo);
        System.out.println("mailSubject = " + mailSubject);
        System.out.println("mailMessage = " + mailMessage);

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromAddress,fromName));
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

        if(mailCC != null && !mailCC.equals("")) {
            ccList = InternetAddress.parse(mailCC);
            message.setRecipients(Message.RecipientType.CC, mailCC);
        }

        transport.connect(mailHost,Integer.parseInt(mailPort),fromAddress,mailPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        System.out.println("mailMessage = " + fromAddress);
        return true;
    }
    
    public boolean smtpMailAttachment(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC,final String[] attachment) throws Exception {
        System.out.println("emailProps = " + emailProps);
        final Session mailSession = Session.getInstance(emailProps,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                { return new PasswordAuthentication(fromAddress,mailPassword);	}
        });

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromAddress,fromName));
        message.setSubject(mailSubject);
        
        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTest);
        message.setContent(multipart);
        messageBodyPartTest = new MimeBodyPart();
        if (attachment != null && attachment.length > 0) {
            for (String filePath : attachment) {
                if(filePath!=null){
                    addAttachment(multipart,filePath);
                    System.out.println("file name "+filePath);
                }
            }
        }
        message.setContent(multipart);
        InternetAddress[] toList;
        InternetAddress[] ccList;

        toList = InternetAddress.parse(mailTo);
        message.setRecipients(Message.RecipientType.TO, toList);

        if(mailCC != null && !mailCC.equals("")) {
            ccList = InternetAddress.parse(mailCC);
            message.setRecipients(Message.RecipientType.CC, mailCC);
        }

        transport.connect(mailHost,Integer.parseInt(mailPort),fromAddress,mailPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        return true;
    }
    private static void addAttachment(Multipart multipart, String filename) throws MessagingException{
        DataSource source = new FileDataSource(CommonConfigurations.travelDocumentsPath+filename);
        BodyPart messageBodyPart = new MimeBodyPart();        
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
    }
   
    public MimeBodyPart attachImage(String imgPath,String imgName) throws MessagingException{

        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(imgPath);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+imgName+">");
        return messageBodyPart;
    }
}

