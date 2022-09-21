/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.shared;

import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 15858
 */
public class MailMessages {
    
HttpServletRequest request ;
    String salutation = "Dear";
    String MessageToSend="";
    String SubjectToSend="";
    

    public String getMessage(HttpServletRequest request,String mailReason,String data) throws UnknownHostException{
        System.out.println("data = " + data.length()+"Data:::"+data);
        String[] details = data.split("#-#");
        System.out.println("details:::"+details.length);
        System.out.println("details = " + details[0]+details[1]+details[2]+details[3]);
        System.out.println("mailReason = " + mailReason);
        
        if(mailReason.equals("NewJoinee")){
            System.out.println("Here Comes::::");
            MessageToSend = "Dear "+details[0]+",<br><br><br>" +
//                    "Welcome To Defiance Technologies, <br><br><br>Use the link below to complete your Joining Formalities<br><br>" +"http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/index.jsp"+"<br><br><br>"+
//                    " Login Details:<br><br>"+"UserName:&nbsp;&nbsp;&nbsp;"+details[2]+"<br>"+"Password:&nbsp;&nbsp;&nbsp;"+details[3]+"<br><br><br><br> Regards:<br>" +
//                    "Human Resource<br>Defiance Technologies Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
                    "Warm greetings from Hindujatech!" +
                    "<br><br>Congratulations and welcome to our Hindujatech family!! We are sure that there will be a lot of mutual wins with the beginning of your association with Hindujatech!" +
                    "<br><br>Kindly logon to the below link and fill, upload all the relevant fields and submit the same. " +
                    "<br><br>" +"http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath()+"/index.jsp"+"<br><br>"+
                    " Login Details:<br><br>"+"UserName:&nbsp;&nbsp;&nbsp;"+details[2]+"<br>"+"Password:&nbsp;&nbsp;&nbsp;"+details[3]+
                    "<br><br>Kindly complete your Online Joining Formalities Immediately with Hinduja Tech. Incase if you face any difficulties during this process, kindly email to idealsupport@hindujatech.com."+
                    "<br><br><br><br> Regards:<br>" +
                    "Human Resource<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email. Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }
    
     public String getOjfMessage(HttpServletRequest request,String mailReason,String data) throws UnknownHostException{
        System.out.println("getOjfMessage = " + data.length()+"Data:::"+data);
        String[] details = data.split("#-#");
        System.out.println("details:::"+details.length);
        System.out.println("details = " + details[0]+details[1]+details[2]+details[3]+ details[4]+details[5]+details[6]+details[7]+ details[8]+ details[9]+details[10]+details[11]+details[12]);
        System.out.println("mailReason = " + mailReason);
        StringBuffer msgBuffer = new StringBuffer();
        if(mailReason.equals("NewJoinee")){
           
           MessageToSend = "Dear All,<br><br><br>" +
                   
                   "Please Find the joiner Details for the below mentioned Employee"+
                   
//                   
                   msgBuffer.append("<br/><br/><strong>Employee  details:</strong><br/><br/>").
                append("<table border=\"1\" style=\"border-collapse: collapse\"><tr><th>Employee ID</th><th>Employee Name</th><th>Designation</th><th>Band</th><th>Business Unit</th><th>Practice</th><th>Employment Status</th><th>Billable</th><th>Reporting Manager</th><th>Location</th><th>Date Of joining</th><th>Personal mailId</th><th>HTL mail id</th></tr><tr><td>").
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
                append("</td><td>").
                           append(details[6]).
              append("</td><td>").
                append(details[7]).
                append("</td><td>").
                append(details[8]).
                append("</td><td>").
                           append(details[9]).
              append("</td><td>").
                append(details[10]).
                append("</td><td>").
                append(details[11]).
                append("</td><td>").
                            append(details[12]).
         append("</td></tr></table><br/><br/>")+
                  "For any changes contact "+details[13]+" from HR Team.<br>" +
                    "<br><br><br><br> Regards:<br>" +
                    "Human Resource<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email. Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }
    
     
     public String getOjfSubject(String mailReason){

        if(mailReason == "NewJoinee"){
            SubjectToSend="Reg: New Joiner Details";
        }
        return SubjectToSend;
    }
    
    public String getMessageForDoj(HttpServletRequest request,String mailReason,String data) throws UnknownHostException{
        System.out.println("data = " + data.length()+"Data:::"+data);
        String[] details = data.split("#-#");
        System.out.println("details:::"+details.length);
        System.out.println("details = " + details[0]+details[1]+details[2]);
        System.out.println("mailReason = " + mailReason);

        if(mailReason.trim().equals("Date of Joining")){
            System.out.println("Here Comes::::");
            MessageToSend = "Dear "+details[0]+",<br><br><br>" +
                    "Welcome To Hinduja Tech, <br><br><br>Your Joining Formalities has been verfied by HR team.<br><br>Your DOJ : "+ details[2] +"<br><br>"+
                    "Kindly reply to Hr Team.<br><br><br>Regards:<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        }
        System.out.println("MessageToSend = " + MessageToSend);
        return MessageToSend;
    }

    public String getMailMessageForJoinerSubmit(HttpServletRequest requestObj,String data)
    {
        System.out.println("data = " + data.length()+"Data:::"+data);
        String[] details = data.split("#-#");
        MessageToSend = "Dear HR,<br><br>" +
                "Joining Formalities completed by "+details[0]+" "+details[1]+"<br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        return MessageToSend;
    }
    
    public String getMailMessageForSendBack(HttpServletRequest requestObj,String data)
    {
        System.out.println("data = " + data.length()+"Data:::"+data);
        String[] details = data.split("#-#");
        MessageToSend = "Dear "+details[0]+" "+details[1]+",<br><br>" +
                "Your Joining Formalities has been send back by HR team<br><br>Reason:"+details[2]+"<br><br>Kindly resubmit the form using the below url" +
                "<br><br>" +"http://"+requestObj.getServerName()+":"+requestObj.getLocalPort()+requestObj.getContextPath()+"/index.jsp<br><br>" +
                " Login Details:<br><br>"+"UserName:&nbsp;&nbsp;&nbsp;"+details[3]+"<br>"+"Password:&nbsp;&nbsp;&nbsp;"+details[4]+"<br><br><br>Regards,<br>Human Resource<br>Hinduja Tech Ltd<br><br><br><b>Note:This is system generated email.Please do not reply.</b>";
        return MessageToSend;
    }

    public String getSubject(String mailReason){

        if(mailReason == "NewJoinee"){
            SubjectToSend="Reg: Joining Formalities From Hindujatech";
        }
        return SubjectToSend;
    }
}

