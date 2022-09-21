/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
    PasswordAuthentication auth;
    private MimeBodyPart messageBodyPart;

    public SendMail() throws FileNotFoundException, IOException{
    Properties configFile = new Properties();
   

//    File f2 = new File(".");
//    File[] strFilesDirs = f2.listFiles ( );
//     for ( int i = 0 ; i < strFilesDirs.length ; i ++ ) {
//     if ( strFilesDirs[i].isDirectory ( ) ){
//     System.out.println ( "Directory: " + strFilesDirs[i] ) ;}
//     else if ( strFilesDirs[i].isFile ( ) ){
//     System.out.println ( "File: " + strFilesDirs[i] + " (" + strFilesDirs[i].length ( ) + ")" ) ;
//     }
//     }
                     
        configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
                                                                   
        
        fromAddress   = configFile.getProperty("MAIL_USER_NAME").toString().trim();
        mailPassword  = configFile.getProperty("MAIL_PASSWORD").toString().trim();
        mailHost      = configFile.getProperty("MAIL_HOST");
        mailType      = configFile.getProperty("MAIL_TYPE");
        mailPort      = configFile.getProperty("MAIL_PORT");

       // System.out.println("fromAddress === " + fromAddress);
       // System.out.println("mailPassword === " + mailPassword);
//     //   System.out.println("Mail id and password Hard coded Here");
//        fromAddress = "idealadmin@defiance-tech.com";
//        mailPassword="Passw0rd@123";
        
       emailProps = new Properties();
       emailProps.put("mail.transport.protocol",mailType);
       emailProps.put("mail.smtps.host",mailHost);
       emailProps.put("mail.smtps.auth","true");
       emailProps.put("mail.smtps.quitwait", "false");
       emailProps.put("mail.smtp.starttls.enable", "true");
       emailProps.put("mail.smtp.port",mailPort);
  }

    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC)
			throws Exception {
               // System.out.println(mailTo+mailSubject+mailMessage+mailCC+" forwarded details from insertExitEmpDetails");
               // System.out.println("emailProps = " + emailProps);
                final Session mailSession = Session.getInstance(emailProps,new javax.mail.Authenticator()
		{
                        @Override
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication(fromAddress,mailPassword);	}
		});

              //  System.out.println("mailTo = " + mailTo);
              //  System.out.println("mailSubject = " + mailSubject);
              //  System.out.println("mailMessage = " + mailMessage);
                
		mailSession.setDebug(true);
		final Transport transport = mailSession.getTransport();
		final MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(fromAddress));
		message.setSubject(mailSubject);

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPartTest);
//        multipart.addBodyPart(attachImage("test.png","image"));
//        multipart.addBodyPart(attachImage("test2.jpg","image2"));
//        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        
		InternetAddress[] toList;
		InternetAddress[] ccList;

                toList = InternetAddress.parse(mailTo);
		message.setRecipients(Message.RecipientType.TO, toList);

                ccList = InternetAddress.parse(mailCC);
		message.setRecipients(Message.RecipientType.CC, mailCC);

                
		transport.connect(mailHost,Integer.parseInt(mailPort),fromAddress,mailPassword);
		transport.sendMessage(message,message.getAllRecipients());
		transport.close();
                //System.out.println("mailMessage = " + fromAddress);
            	return true;
	}

    public MimeBodyPart attachImage(String imgPath,String imgName) throws MessagingException{

        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(imgPath);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+imgName+">");
        return messageBodyPart;
    }
}

