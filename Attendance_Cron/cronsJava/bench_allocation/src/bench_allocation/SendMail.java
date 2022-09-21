/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bench_allocation;

/**
 *
 * @author 16221
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author 16221
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
        this.fromAddress = username;
        this.mailPassword = password;
        this.mailHost = host;
        this.mailPort = port;
        mailType      = "smtp";
        fromName      = "Ideal Admin";
        emailProps = new Properties();

        emailProps.put("mail.transport.protocol",mailType);
        emailProps.put("mail.smtps.host",mailHost);
        emailProps.put("mail.smtps.auth","true");
        emailProps.put("mail.smtps.quitwait", "false");
        emailProps.put("mail.smtp.starttls.enable", "true");
        emailProps.put("mail.smtp.port",mailPort);
        emailProps.put("mail.smtp.ssl.trust", mailHost);
    }

    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC, final String mailBCC)
			throws Exception {
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

        MimeMultipart multipart = new MimeMultipart("related");
        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");
        // add it
        multipart.addBodyPart(messageBodyPartTest);
        message.setContent(multipart);
        InternetAddress[] toList;
        InternetAddress[] ccList;
        InternetAddress[] bccList;
        
        toList = InternetAddress.parse(mailTo);
        message.setRecipients(Message.RecipientType.TO, toList);

        ccList = InternetAddress.parse(mailCC);
        message.setRecipients(Message.RecipientType.CC, mailCC);
        
        bccList = InternetAddress.parse(mailBCC);
        message.setRecipients(Message.RecipientType.BCC, mailBCC);
        
        transport.connect(mailHost,Integer.parseInt(mailPort),fromAddress,mailPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
        return true;
    }
    
    public boolean smtpMailAttachment(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC,final String attachment) throws Exception {
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
        if (attachment != null) {
            if(attachment!=null){
//                addAttachment(multipart,attachment);
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
//    private static void addAttachment(Multipart multipart, String filename) throws MessagingException{
//        DataSource source = new FileDataSource(CommonConfig.fileUploadPath+filename);
//        BodyPart messageBodyPart = new MimeBodyPart();        
//        messageBodyPart.setDataHandler(new DataHandler(source));
//        messageBodyPart.setFileName(filename);
//        multipart.addBodyPart(messageBodyPart);
//    }
    
    public MimeBodyPart attachImage(String imgPath,String imgName) throws MessagingException{

        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(imgPath);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+imgName+">");
        return messageBodyPart;
    }
}
