package com.defiance.ideal.ojf.shared;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


/**
 * <b>SendMessage</b> is  a class which is used to send messages 
 * in the form of <strong>SMS</strong> and <strong>email</strong>, from the application.
 * The Properties should be configured correctly in the property file,
 * otherwise it wont run correctly.
 * @author Sureshkumar N
 */
public class SendMessage {
	/**
	 * The logger.
	 */
	private transient Logger logger = Logger.getLogger(this.getClass().getName());
	 
	/**
	 * Default Constructor.
	 */
	public SendMessage() {
		super();
	}

	/**
	 * @param mailTo -
	 *            The Mail Recipient comma separated sequence string if more than one recipient
	 * @param mailSubject -
	 *            The Mail subject
	 * @param mailMessage -
	 *            The Mail Message Body
	 * @return -The status of the message SENT/UNSENT (True/False)
	 * @throws Exception
	 *             Exception
	 */
	public boolean sendMail(final String mailTo, final String mailSubject, final String mailMessage)
			throws Exception {
		final String mailHost = DataDictConstants.EMAIL_SERVER_NAME;
		final int mailPort = Integer.parseInt(DataDictConstants.MAIL_PORT_VALUE);
		final String mailUserName =DataDictConstants.EMAILID;
		final String mailPassword = DataDictConstants.EMAIL_PASSWORD;
            Authenticator auth = new SMTPAuthenticator();
            //Session session = Session.getInstance(DataDictConstants.emailProps, auth);
		final Session mailSession = Session.getInstance(DataDictConstants.emailProps,auth);
		mailSession.setDebug(true);
		final Transport transport = mailSession.getTransport();
		final MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(DataDictConstants.EMAILID));
		message.setSubject(mailSubject);
		message.setContent(mailMessage, "text/html");
		InternetAddress[] toList;
		toList = InternetAddress.parse(mailTo);
		message.setRecipients(Message.RecipientType.TO, toList);
                //Transport.send(message);
		transport.connect(mailHost, mailPort, mailUserName, mailPassword);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();

		return true;
	}
	/**
	 * @param mailTo -
	 *            The Mail Recipient comma separated sequence string if more than one recipient
	 * @param mailSubject -
	 *            The Mail subject
	 * @param mailMessage -
	 *            The Mail Message Body
	 * @return -The status of the message SENT/UNSENT (True/False)
	 * @throws Exception
	 *             Exception
	 */
	public boolean sendMail(final String mailTo, final String mailCc,final String mailBcc, final String mailSubject, final String mailMessage)
			throws Exception {
		final String mailHost = DataDictConstants.EMAIL_SERVER_NAME;
		final int mailPort = Integer.parseInt(DataDictConstants.MAIL_PORT_VALUE);
		final String mailUserName =DataDictConstants.EMAILID;
		final String mailPassword = DataDictConstants.EMAIL_PASSWORD;

		final Session mailSession = Session.getDefaultInstance(DataDictConstants.emailProps);
		mailSession.setDebug(true);
		final Transport transport = mailSession.getTransport();
		final MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(DataDictConstants.EMAILID));
		message.setSubject(mailSubject);
		message.setContent(mailMessage, "text/html");
		InternetAddress[] toList;
		toList = InternetAddress.parse(mailTo);
		message.setRecipients(Message.RecipientType.TO, toList);
		if(mailCc!=null) {message.setRecipients(Message.RecipientType.CC, mailCc);}
		if(mailBcc!=null) {message.setRecipients(Message.RecipientType.BCC, mailBcc);}
		transport.connect(mailHost, mailPort, mailUserName, mailPassword);
                System.out.println("inside sendmsg"+Message.RecipientType.TO);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();

		return true;
	}
	
	/**
	 * @param mailTo The Mail Recipient comma separated sequence string if more than one recipient
	 * @param mailSubject The Mail subject
	 * @param mailMessage The Mail Message Body
	 * @param mailAttachFileName The attachmentFile path
	 * @return true if sent success.
	 */
	public boolean sendMail(String mailTo,String mailSubject,String mailMessage,String mailAttachFileName){
		try{
			// Define message		}
		final Session mailSession = Session.getDefaultInstance(DataDictConstants.emailProps);
		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(DataDictConstants.EMAILID));
		message.addRecipient(Message.RecipientType.TO, 
		  new InternetAddress(mailTo));
		message.setSubject(mailSubject);

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText(mailMessage);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		DataSource source= new FileDataSource(mailAttachFileName);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(mailAttachFileName);
		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		// Send the message
		Transport tr= mailSession.getTransport();
		final String mailHost = DataDictConstants.EMAIL_SERVER_NAME;
		final int mailPort = Integer.parseInt(DataDictConstants.MAIL_PORT_VALUE);
		final String mailUserName =DataDictConstants.EMAILID;
		final String mailPassword = DataDictConstants.EMAIL_PASSWORD;
		tr.connect(mailHost, mailPort, mailUserName, mailPassword);
		tr.sendMessage(message,  message.getRecipients(Message.RecipientType.TO));
		tr.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @param mailTo The Mail Recipient comma separated sequence string if more than one recipient
	 * @param mailSubject The mail subject
	 * @param mailMessage The message body
	 * @param attachment The attachment as byte stream
	 * @param atType the type of the attachment 'text/pdf' for ex.
	 * @param mailAttachFileName the filename of the attachment.
	 * @return
	 */
	public boolean sendMail(String mailTo, String mailSubject,String mailMessage,ByteArrayOutputStream attachment,String atType, String mailAttachFileName){
		try{
		final Session mailSession = Session.getDefaultInstance(DataDictConstants.emailProps);
		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(DataDictConstants.EMAILID));
		message.addRecipient(Message.RecipientType.TO, 
		  new InternetAddress(mailTo));
		message.setSubject(mailSubject);

		// Create the message part 
		BodyPart messageBodyPart = new MimeBodyPart();

		// Fill the message
		messageBodyPart.setText(mailMessage);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		ByteArrayOutputStream bt=new ByteArrayOutputStream();
		bt.write("tes".getBytes());
		System.out.println( new String(bt.toByteArray()));
		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		DataSource source = new ByteArrayDataSource(bt.toByteArray(),atType);//( inputStream,"test") ;
		//DataSource source= new FileDataSource(mailAttachFileName);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(mailAttachFileName);
		multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		// Send the message
		Transport tr= mailSession.getTransport();
		final String mailHost = DataDictConstants.EMAIL_SERVER_NAME;
		final int mailPort = Integer.parseInt(DataDictConstants.MAIL_PORT_VALUE);
		final String mailUserName =DataDictConstants.EMAILID;
		final String mailPassword = DataDictConstants.EMAIL_PASSWORD;
		tr.connect(mailHost, mailPort, mailUserName, mailPassword);
		tr.sendMessage(message,  message.getRecipients(Message.RecipientType.TO));
		tr.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}


    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = DataDictConstants.EMAILID;
            String password = DataDictConstants.EMAIL_PASSWORD;
            return new PasswordAuthentication(username, password);
        }
    }

        public static void main(String[] args) {
        try {
            SendMessage sm = new SendMessage();
            sm.sendMail("punitha.a@hindujatech.com", "Test", "HI ");
        } catch (Exception ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
