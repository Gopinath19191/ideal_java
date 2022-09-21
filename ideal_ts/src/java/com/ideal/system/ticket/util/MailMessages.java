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

    public String getTicketMessage(HttpServletRequest request, String mailReason, String data) throws UnknownHostException {
    
        String[] details = data.split("#-#");
        if (mailReason.equals("TicketRaised")) {
            MessageToSend = "Dear User,<br><br>"
                    + "A New Feedback entered by&nbsp;" + details[0] + "&nbsp;&nbsp;for&nbsp;" + "RefNo:&nbsp;" + details[1] + "&nbsp;&nbsp;is awaiting for Reply/Solution.<br><br><br>"
                    + "<b>The following are the details:</b><br><br>"
                    + details[2] + "<br><br>"
                    + "Please login as admin for details and reply."
                    + "<br><br>Regards,<br>"
                    + "Ideal Admin,<br>ideal.hindujatech.com<br><br><br>Note:The information transmitted is intended only for the person or entity to which it is addressed and may contain confidential and/or privileged material belonging to Hinduja Tech and/or any of its subsidiaries. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in Hinduja Tech on this e-mail is strictly prohibited and may be unlawful. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies of the original message.";
        } else if (mailReason.equals("TicketReSubmit")) {
            MessageToSend = "Dear User,<br><br>"
                    + " Ticket Resubmitted by&nbsp;" + details[0] + "&nbsp;&nbsp;for&nbsp;" + "RefNo:&nbsp;" + details[1] + "&nbsp;&nbsp;is awaiting for Reply/Solution.<br><br><br>"
                    + "<b>The following are the details:</b><br><br>"
                    + details[2] + "<br><br>"
                    + "Please login as admin for details and reply."
                    + "<br><br> Regards,<br>"
                    + "Ideal Admin,<br>ideal.hindujatech.com<br><br><br>Note:The information transmitted is intended only for the person or entity to which it is addressed and may contain confidential and/or privileged material belonging to Hinduja Tech and/or any of its subsidiaries. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in Hinduja Tech on this e-mail is strictly prohibited and may be unlawful. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies of the original message.";
        }else if (mailReason.equals("TicketClosed")) {
            MessageToSend = "Dear User,<br><br>"
                    + " Ticket RefNo: " + details[1] + " is closed by " + details[0] + ".<br><br><br>"
                    + "<b>The following are the details:</b><br><br>"
                    + details[2] + "<br><br>"
                    + "Please login as admin to view details."
                    + "<br><br> Regards,<br>"
                    + "Ideal Admin,<br>ideal.hindujatech.com<br><br><br>Note:The information transmitted is intended only for the person or entity to which it is addressed and may contain confidential and/or privileged material belonging to Hinduja Tech and/or any of its subsidiaries. Any unauthorized review, use, disclosure, dissemination, forwarding, printing or copying of this email or any action taken in Hinduja Tech on this e-mail is strictly prohibited and may be unlawful. If you are not the intended recipient, please contact the sender by reply e-mail and destroy all copies of the original message.";
        }

        return MessageToSend;
    }

    public String getTicketSubject(String mailReason, String data) {
       
        String[] details = data.split("#-#");
        if (mailReason == "TicketRaised") {
            SubjectToSend = "Reg: NEW FEEDBACK ENTRY/" + "RefNo:" + details[0];
        } else if (mailReason == "TicketReSubmit") {
            SubjectToSend = "Reg: TICKET RESUBMISSION/" + "RefNo:" + details[0];
        }else if (mailReason == "TicketClosed"){
            SubjectToSend = "Reg: TICKET CLOSED/" + "RefNo:" + details[0];
        }
        return SubjectToSend;
    }
}
