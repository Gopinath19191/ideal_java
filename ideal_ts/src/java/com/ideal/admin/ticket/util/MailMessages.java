/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.admin.ticket.util;

import java.net.UnknownHostException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class MailMessages {
    final static Logger logger = Logger.getLogger(MailMessages.class);

    HttpServletRequest request;
    String salutation = "Dear";
    String MessageToSend = "";
    String SubjectToSend = "";
    private static final String[] EMPTY_ARRAY = new String[0];

    public String getResponseMessage(HttpServletRequest request, String mailReason, String[] resp, String[] sts, String[] user, String[] admin, String data, String r_date, String created_date_mail, String[] replied_date_mail, String[] adminName, String adminResponseName) throws UnknownHostException {

        String[] details = data.split("#-#");
        String a = "";
       
        String creator_msg = "";
        String responder_msg = "";
        String[] rdm;
        String[] aName;
        if (replied_date_mail != null && replied_date_mail.length > 0) {

            rdm = replied_date_mail;
           
        } else {
            rdm = EMPTY_ARRAY;
            aName = EMPTY_ARRAY;
        }
        int respCount=resp.length;
        //int totCount = respCount-1;
       aName = adminName;
        logger.info("" + rdm.length + "ddd" + respCount);
        logger.info("name---"+aName.length);
        logger.info("resp[k]---"+resp.length);
        logger.info("sts---"+sts.length);
        logger.info("rdm---"+rdm.length);
            
         responder_msg += " " + adminResponseName + " Responded on :" + r_date + "<br>";
          responder_msg +=  "<br>" + details[0]+"<br>";
          if(details[2].equals("Waiting Feedback")){
              responder_msg +=  "<br><b>Note : </b>: If you are satisfied with the solution provided, please close the ticket. If you do not respond within 2 days, the ticket will automatically be closed.<br>";
          }
          
          responder_msg +=  "<br><br><b>Status: " + details[2] + "</b><br><hr>"; 
          
        for (int k = respCount-1; k >=0; k--) {
            
            

            if (k == 0) {
                creator_msg += "Ticket raised by "+aName[k]+ " on :" + created_date_mail + "<br>";               
                creator_msg += "<br>" + resp[k] + "<br><br><b>Status: " + sts[k] + "</b><br><hr>";
               
            }
          
            else if(k>0)
            {
                logger.info("k1>>>"+(k)+"admin>>"+aName[k]+"rspnd>>>"+rdm[k-1]);
                responder_msg += " " + aName[k] + " Responded on :" + rdm[k-1] + "<br>";               
                responder_msg +=  "<br>" + resp[k] + "<br><br><b>Status: " + sts[k] + "</b><br><hr>"; 
                
            }else{
                logger.info("else dummy");
            }
        }
             
        
        if (mailReason.equals("TicketRaised")) {

            MessageToSend = "Dear User,<br><br>"
                    + responder_msg
                    + creator_msg + "<br>"
                    + "Please login to iDeal on RefNo&nbsp;" + details[1] + "&nbsp;for further details.<br/>"
                    + "<br>Regards,<br>"
                    + "Ideal Admin,<br>ideal.hindujatech.com<br><br><br>Note:The information transmitted is intended only for the person or entity to which it is addressed and may contain confidential and/or privileged material belonging to Hinduja Tech and/or any of its subsidiaries. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in Hinduja Tech on this e-mail is strictly prohibited and may be unlawful. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies of the original message.";

        }
        return MessageToSend;
    }

    public String getResponseSubject(String mailReason, String data) {

        String[] details = data.split("#-#");
        if (mailReason == "TicketRaised") {
            SubjectToSend = "Reg: FEEDBACK REPLIED BY ADMIN / RefNo.:" + details[0];
        }
        return SubjectToSend;
    }
}
