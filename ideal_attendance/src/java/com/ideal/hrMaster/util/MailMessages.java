/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.util;

import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 16221
 */
public class MailMessages {
    HttpServletRequest request;
    String salutation = "Dear";
    String MessageToSend = "";
    String SubjectToSend = "";
//

    public String getMessage(HttpServletRequest request, String mailContent, String employeeName, String rmName, String status, String buhName) throws UnknownHostException {
        System.out.println("mailReason = " + mailContent);
        System.out.println("Here Comes::::");
        if (status.equals("m")) {
            MessageToSend = "Dear " + rmName + ",<br><br>";
            MessageToSend += "This is to inform you that new Purchase request submited by "+ employeeName +" is waiting for your approval. <br><br>Please login into iDeal for further details.<br><br>";
            MessageToSend += "Regards,<BR>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);

        } else if (status.equals("a")) {
            MessageToSend = "Dear " + buhName + ",<br><br>";
            MessageToSend += "This is to inform you that new Purchase request submited by "+ employeeName +" is approved by RM "+ rmName +" and waiting for your approval. <br><br>Please login into iDeal for further details.<br><br>";
            MessageToSend += "Regards,<BR>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);
        }else if (status.equals("r")){
            MessageToSend = "Dear " + employeeName + ",<br><br>";
            MessageToSend += "This is to inform you that new Purchase request submited by you is rejected by RM "+ rmName +". <br><br>Please login into iDeal for further details.<br><br>";
            MessageToSend += "Regards,<BR>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);
        }else if (status.equals("e")){
            MessageToSend = "Dear " + employeeName + ",<br><br>";
            MessageToSend += "This is to inform you that new Purchase request submited by  "+rmName+" approved by BUH "+ buhName +". <br><br>Please login into iDeal for further details.<br><br>";
            MessageToSend += "Regards,<BR>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);
        }else if (status.equals("j")){
            MessageToSend = "Dear " + employeeName + ",<br><br>";
            MessageToSend += "This is to inform you that new Purchase request submited by you is rejected by BUH "+ buhName +". <br><br>Please login into iDeal for further details.<br><br>";
            MessageToSend += "Regards,<BR>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);
        }else{
            MessageToSend="";
        }
        
        return MessageToSend;
    }
}
