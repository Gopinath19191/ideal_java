package headcountjob;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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

public class SendMail {
    
    static Properties emailProps;
    String fromAddress;
    String mailPassword;
    String mailHost;
    String mailType;
    String mailPort;
    String fromName;
    PasswordAuthentication auth;
    private MimeBodyPart messageBodyPart;

    public SendMail(String username, String password, String host, String port) throws FileNotFoundException, IOException {

        System.out.println("fromAddress+++++++++" + username);
        this.fromAddress = username;
        this.mailPassword = password;
        this.mailHost = host;
        this.mailPort = port;
        mailType = "smtp";
        fromName = "Ideal Admin";

        System.out.println("fromAddress = " + fromAddress);
        System.out.println("mailPassword = " + mailPassword);
        System.out.println("mailHost = " + mailHost);
        System.out.println("mailPort = " + mailPort);
        System.out.println("mailType = " + mailType);

        emailProps = new Properties();

        emailProps.put("mail.transport.protocol", mailType);
        emailProps.put("mail.smtps.host", mailHost);
        emailProps.put("mail.smtps.auth", "true");
        emailProps.put("mail.smtps.quitwait", "false");
        emailProps.put("mail.smtp.starttls.enable", "true");
        emailProps.put("mail.smtp.port", mailPort);
        emailProps.put("mail.smtp.ssl.trust", mailHost);
        emailProps.put("mail.smtps.user", fromAddress);
        emailProps.put("mail.smtps.password", mailPassword);

    }

    public boolean smtpMail(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC)
            throws Exception {
        System.out.println("emailProps = " + emailProps);
        final Session mailSession = Session.getInstance(emailProps, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress, mailPassword);
            }
        });

        System.out.println("mailTo = " + mailTo);
        System.out.println("mailSubject = " + mailSubject);
        System.out.println("mailMessage = " + mailMessage);
        System.out.println("mailCc = " + mailCC);

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
//        System.out.println("trans: " + transport);
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromAddress, fromName));
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
        message.setRecipients(Message.RecipientType.CC, ccList);

//        System.out.println("mailMessage = " + fromAddress);
        transport.connect(mailHost, Integer.parseInt(mailPort), fromAddress, mailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
//        System.out.println("mailMessage = " + fromAddress);
        return true;
    }

    public boolean smtpMailAttachment(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC, final String attachment) throws Exception {
        final Session mailSession = Session.getInstance(emailProps, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress, mailPassword);
            }
        });

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromAddress, fromName));
        message.setSubject(mailSubject);

        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTest);
        message.setContent(multipart);
        messageBodyPartTest = new MimeBodyPart();
        if (attachment != null) {

            addAttachment(multipart, attachment);

        }
        message.setContent(multipart);
        InternetAddress[] toList;
        InternetAddress[] ccList;

        toList = InternetAddress.parse(mailTo);
        message.setRecipients(Message.RecipientType.TO, toList);

        if (mailCC != null && !mailCC.equals("")) {
            ccList = InternetAddress.parse(mailCC);
            message.setRecipients(Message.RecipientType.CC, mailCC);
        }

        transport.connect(mailHost, Integer.parseInt(mailPort), fromAddress, mailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return true;
    }

    private static void addAttachment(Multipart multipart, String filename) throws MessagingException {
        DataSource source = new FileDataSource("D:\\HeadCountJobWorksheet\\" + filename);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
    }

    public MimeBodyPart attachImage(String imgPath, String imgName) throws MessagingException {

        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource(imgPath);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<" + imgName + ">");
        return messageBodyPart;
    }

    public boolean smtpMailBodyAttachment(final String mailTo, final String mailSubject, final String mailMessage, final String mailCC, Map<String, String> mapInlineImages) throws Exception {
        final Session mailSession = Session.getInstance(emailProps, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress, mailPassword);
            }
        });

        mailSession.setDebug(true);
        final Transport transport = mailSession.getTransport();
        final MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(fromAddress, fromName));
        message.setSubject(mailSubject);

        BodyPart messageBodyPartTest = new MimeBodyPart();
        messageBodyPartTest.setContent(mailMessage, "text/html");

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPartTest);
        message.setContent(multipart);
        messageBodyPartTest = new MimeBodyPart();

        if (mapInlineImages != null && mapInlineImages.size() > 0) {
            Set<String> setImageID = mapInlineImages.keySet();

            for (String contentId : setImageID) {
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setHeader("Content-ID", "<" + contentId + ">");
                imagePart.setDisposition(MimeBodyPart.INLINE);

                String imageFilePath = mapInlineImages.get(contentId);

                try {
                    imagePart.attachFile("D:\\wishesimages\\" + imageFilePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(imagePart);

            }
        }

        message.setContent(multipart);
        InternetAddress[] toList;
        InternetAddress[] ccList;

        toList = InternetAddress.parse(mailTo);
        message.setRecipients(Message.RecipientType.TO, toList);

        if (mailCC != null && !mailCC.equals("")) {
            ccList = InternetAddress.parse(mailCC);
            message.setRecipients(Message.RecipientType.CC, mailCC);
        }

        transport.connect(mailHost, Integer.parseInt(mailPort), fromAddress, mailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return true;
    }   
}
