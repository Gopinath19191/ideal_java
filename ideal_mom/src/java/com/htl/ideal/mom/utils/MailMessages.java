/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.utils;

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

     public String getMessage(HttpServletRequest request, String mailContent, String minutedBy, String momTitle) throws UnknownHostException {
        System.out.println("mailReason = " + mailContent);
        System.out.println("Here Comes::::");
//        if (status.equals("m")) {
            MessageToSend = "Hi All,<br><br>";
            MessageToSend += "Minutes of Meeting has been created in IDEAL for "+ momTitle +" by "+ minutedBy +".<br><br>";
            MessageToSend += "Request you all to look at your individual action items and decide on Planned Closure Date and Actual Closure Date for the same.<br><br>";
            MessageToSend += "Please login into iDeal for further details.<br><br>";
            MessageToSend += "Link Path: ESS -->My Information --> My MOM.<br><br>";
            MessageToSend += "Regards,<br>iDeal Admin";
            System.out.println("MessageToSend = " + MessageToSend);

//        } else{
//            MessageToSend="";
//        }
        return MessageToSend;
    }
}