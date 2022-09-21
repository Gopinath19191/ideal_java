/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class MailMessages {

    HttpServletRequest request;
    String salutation = "Dear";
    String MessageToSend = "";
    String SubjectToSend = "";
//

    public String getMessage(HttpServletRequest request, String mailContent, String toName, String approverName, String status, String accessType) throws UnknownHostException {
        System.out.println("mailReason = " + mailContent);
        System.out.println("Here Comes::::");
        if (accessType.equalsIgnoreCase("EMPLOYEE")) {
            MessageToSend = "Dear " + toName + ",<br><br>";
            MessageToSend += "This is to inform you that new holiday allowance has been applied and waiting for your approval. The details are as follow<br> " + mailContent + " <br>Please login into iDeal for further details and approve before 18th of this month else it will be elapsed.<br><br>";
            MessageToSend += "Regards,<BR>Ideal Admin";
            System.out.println("MessageToSend = " + MessageToSend);

        } else if (accessType.equalsIgnoreCase("RM")) {
            String statusValue;
            if (status.equals("a")) {
                statusValue = "accepted";
            } else {
                statusValue = "rejected";
            }
            MessageToSend = "Dear " + toName + ",<br><br>";
            MessageToSend += "This is to inform you that the holiday allowance applied by you has been " + statusValue + " by " + approverName + " . The details are as follow<br> " + mailContent + " <br>Please login into iDeal for further details.	<br><br>";
            MessageToSend += "Regards,<BR>Ideal Admin";
            System.out.println("MessageToSend = " + MessageToSend);
        }else{
            String statusValue;
            if (status.equals("1")) {
                statusValue = "accepted";
            } else {
                statusValue = "rejected";
            }
            MessageToSend += mailContent;
        }
        return MessageToSend;
    }
}
