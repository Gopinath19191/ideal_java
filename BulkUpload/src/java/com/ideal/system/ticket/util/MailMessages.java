/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.system.ticket.util;

import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 15858
 */
public class MailMessages {

    HttpServletRequest request;
    String salutation = "Dear";
    String MessageToSend = "";
    String SubjectToSend = "";

    public String getMessage(HttpServletRequest request, String mailReason, String data) throws UnknownHostException {
        System.out.println("data = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        System.out.println("details:::" + details.length);
        System.out.println("details = " + details[0] + details[1] + details[2] + details[3]);
        System.out.println("mailReason = " + mailReason);

        if (mailReason.equals("NewJoinee")) {
            System.out.println("Here Comes::::");
            MessageToSend = "Dear " + details[0] + ",<br><br><br>"
                    + //                    "Welcome To Defiance Technologies, <br><br><br>Use the link below to complete your Joining Formalities<br><br>" +"http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/index.jsp"+"<br><br><br>"+
                    //                    " Login Details:<br><br>"+"UserName:&nbsp;&nbsp;&nbsp;"+details[2]+"<br>"+"Password:&nbsp;&nbsp;&nbsp;"+details[3]+"<br><br><br><br> Regards:<br>" +
                    //                    "Human Resource<br>Defiance Technologies Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
                    "Warm greetings from Hindujatech!"
                    + "<br><br>Congratulations and welcome to our Hindujatech family!! We are sure that there will be a lot of mutual wins with the beginning of your association with Hindujatech!"
                    + "<br><br>Kindly logon to the below link and fill, upload all the relevant fields and submit the same. "
                    + "<br><br>" + "http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath() + "/index.jsp" + "<br><br>"
                    + " Login Details:<br><br>" + "UserName:&nbsp;&nbsp;&nbsp;" + details[2] + "<br>" + "Password:&nbsp;&nbsp;&nbsp;" + details[3]
                    + "<br><br>Kindly complete your online joining formalities atleast three days before your joining date with Hindujatech. Incase if you face any difficulities during this proces, kindly email to Humanresources@hindujatech.com."
                    + "<br><br><br><br> Regards:<br>"
                    + "Human Resource<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }

    public String getOjfMessage(HttpServletRequest request, String mailReason, String data) throws UnknownHostException {
        System.out.println("getOjfMessage = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        System.out.println("details:::" + details.length);
        System.out.println("details = " + details[0] + details[1] + details[2] + details[3] + details[4] +details[5]);
        System.out.println("mailReason = " + mailReason);
        StringBuffer msgBuffer = new StringBuffer();
        if (mailReason.equals("NewJoinee")) {

            MessageToSend = "Dear All,<br><br>"
                    + "Please Find the Partner Consultant joiner Details for the below mentioned Employee"
                    + //                   
                    msgBuffer.append("<br/><br/><strong>Employee  details:</strong><br/><br/>").
                    append("<table border=\"1\" style=\"border-collapse: collapse\"><tr><th>Consultant ID</th><th>Consultant Name</th><th>Partnering company name</th><th>Contract Startdate</th><th>Contract Enddate</th><th>Mail Id</th></tr><tr><td>").
                    append(details[0]).
                    append("</td><td>").
                    append(details[1]).
                    append("</td><td>").
                    append(details[2]).
                    append("</td><td>").
                    append(details[3]).
                    append("</td><td>").
                    append(details[4]).
                     append("</td><td>").
                     append(details[5]).
                    append("</td></tr></table><br/><br/>")
                    + "<br>Regards:<br>"
                    + "Ideal Admin<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        if (mailReason.equals("NewJoineeUpdate")) {

            MessageToSend = "Dear All,<br><br>"
                    + "Please Find the Partner Consultant Details for the below mentioned Employee"
                    + //                   
                    msgBuffer.append("<br/><br/><strong>Employee  details:</strong><br/><br/>").
                    append("<table border=\"1\" style=\"border-collapse: collapse\"><tr><th>Consultant ID</th><th>Consultant Name</th><th>Partnering company name</th><th>Contract Startdate</th><th>Contract Enddate</th><th>Mail Id</th></tr><tr><td>").
                    append(details[0]).
                    append("</td><td>").
                    append(details[1]).
                    append("</td><td>").
                    append(details[2]).
                    append("</td><td>").
                    append(details[3]).
                    append("</td><td>").
                    append(details[4]).
                     append("</td><td>").
                     append(details[5]).
                    append("</td></tr></table><br/><br/>")
                    + "<br>Regards:<br>"
                    + "Ideal Admin<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }
    
    public String getRmMessage(HttpServletRequest request, String mailReason, String data) throws UnknownHostException {
        System.out.println("getOjfMessage = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        System.out.println("details:::" + details.length);
        System.out.println("details = " + details[0] + details[1]+"test");
        System.out.println("mailReason = " + mailReason);
        StringBuffer msgBuffer = new StringBuffer();
        if (mailReason.equals("RMchange")) {

            MessageToSend = "Dear "+details[0]+",<br><br>"
                    + "Your Reporting Manager has been changed from "+details[1]+" to "+details[2]
                     +"<br>Regards:<br>"
                    + "Ideal Admin<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }

    public String getOjfSubject(String mailReason) {

        if ("NewJoinee".equals(mailReason)) {
            SubjectToSend = "Reg: New Joiner Partner Consultant";
        }
        if ("NewJoineeUpdate".equals(mailReason)) {
            SubjectToSend = "Reg: Partner Consultant Contract Modification";
        }
        if ("RMchange".equals(mailReason)) {
            SubjectToSend = "Reg:Reporting Manager Changed";
        }
        return SubjectToSend;
    }
    
    public String getRmSubject(String mailReason,String empname) {

        if ("NewJoinee".equals(mailReason)) {
            SubjectToSend = "Reg: New Joiner Partner Consultant";
        }
        if ("NewJoineeUpdate".equals(mailReason)) {
            SubjectToSend = "Reg: Partner Consultant Contract Modification";
        }
        if ("RMchange".equals(mailReason)) {
            SubjectToSend = "Reg: "+empname+" -Reporting Manager Changed";
        }
        return SubjectToSend;
    }

    public String getMessageForDoj(HttpServletRequest request, String mailReason, String data) throws UnknownHostException {
        System.out.println("data = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        System.out.println("details:::" + details.length);
        System.out.println("details = " + details[0] + details[1] + details[2]);
        System.out.println("mailReason = " + mailReason);

        if (mailReason.trim().equals("Date of Joining")) {
            System.out.println("Here Comes::::");
            MessageToSend = "Dear " + details[0] + ",<br><br><br>"
                    + "Welcome To Hinduja Tech, <br><br><br>Your Joining Formalities has been verfied by HR team.<br><br>Your DOJ : " + details[2] + "<br><br>"
                    + "Kindly reply to Hr Team.<br><br><br>Regards:<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }

    public String getMailMessageForJoinerSubmit(HttpServletRequest requestObj, String data) {
        System.out.println("data = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        MessageToSend = "Dear HR,<br><br>"
                + "Joining Formalities completed by " + details[0] + " " + details[1] + "<br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        return MessageToSend;
    }

    public String getMailMessageForSendBack(HttpServletRequest requestObj, String data) {
        System.out.println("data = " + data.length() + "Data:::" + data);
        String[] details = data.split("#-#");
        MessageToSend = "Dear " + details[0] + " " + details[1] + ",<br><br>"
                + "Your Joining Formalities has been send back by HR team<br><br>Reason:" + details[2] + "<br><br>Kindly resubmit the form using the below url"
                + "<br><br>" + "http://" + requestObj.getServerName() + ":" + requestObj.getLocalPort() + requestObj.getContextPath() + "/index.jsp<br><br>"
                + " Login Details:<br><br>" + "UserName:&nbsp;&nbsp;&nbsp;" + details[3] + "<br>" + "Password:&nbsp;&nbsp;&nbsp;" + details[4] + "<br><br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        return MessageToSend;
    }

    public String getSubject(String mailReason) {

        if (mailReason == "NewJoinee") {
            SubjectToSend = "Reg: Joining Formalities From Hindujatech";
        }
        return SubjectToSend;
    }
}
