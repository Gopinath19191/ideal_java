/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class MailMessages {

    String messageToSend;
    String othersMessageToSend;
    String subjectToSend;

    public MailMessages() {
        Properties configFile = new Properties();
//            try {
//                configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
    }

    public String getSubject(String mailReason, String otherData) {
        String[] data = otherData.split("#~~#");
        if (mailReason.trim().equals("customerSubmit")) {
            subjectToSend = "REG:NEW CUSTOMER IS ADDED AND AWAITING APPROVAL";
        }
        if (mailReason.trim().equals("customerAmended")) {
            subjectToSend = "REG: CUSTOMER DETAILS AMENDED AND AWAITING APPROVAL ";
        }
        if (mailReason.trim().equals("customerResubmitted")) {
            subjectToSend = "REG: CUSTOMER DETAILS RESUBMITTED AND AWAITING APPROVAL ";
        }
        if (mailReason.trim().equals("customerApproved")) {
            subjectToSend = "REG: CUSTOMER DETAILS APPROVED ";
        }
        if (mailReason.trim().equals("customerRejected")) {
            subjectToSend = "REG: CUSTOMER DETAILS REFERRED BACK ";
        }
        if (mailReason.trim().equals("RequestorSubmit")) {
            subjectToSend = "REG: " + data[0];
        }
        if (mailReason.trim().equals("QualityAdminSubmit")) {
            subjectToSend = "REG: " + data[0];
        }
        return subjectToSend;
    }

    public String getOthersMailMessage(String mailReason, String dataString) {
        if (mailReason.trim().equals("customerSubmit")) {
            String[] data = dataString.split("#~~#");
            othersMessageToSend = "A new customer created by <b>" + data[1] + "</b> is awaiting approval.<br><br>"
                    + " The following are the customer details:<BR><BR>"
                    + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[0] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Sales Person Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[1] + "</td></tr>"
//                    + "<tr><td>About Customer</td><td>" + data[2] + "</td></tr>"
//                    + "<tr><td>Currency</td><td>" + data[3] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Country :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[2] + "</td></tr>"
                    + "</table><BR>"
                    + "Regards,<BR>"
                    + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
        }
        if (mailReason.trim().equals("customerResubmitted")) {
            String[] data = dataString.split("#~~#");
            othersMessageToSend = "A customer created by <b>" + data[1] + "</b> has been resubmitted and awaiting approval.<br><br>"
                    + " The following are the customer details:<BR><BR>"
                    + "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[0] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Sales Person Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[1] + "</td></tr>"
//                    + "<tr><td>About Customer</td><td>" + data[2] + "</td></tr>"
//                    + "<tr><td>Currency</td><td>" + data[3] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Country :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[2] + "</td></tr>"
                    + "</table><BR>"
                    + "Regards,<BR>"
                    + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
        }
        if (mailReason.trim().equals("customerAmended")) {
            String[] data = dataString.split("#~~#");
            othersMessageToSend = "Customer details amended by " + data[1] + " is awaiting approval.<br><br>"
                    + "The following are the customer details<BR><BR>"
                    + "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[0] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Sales Person Reference :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[1] + "</td></tr>"
//                    + "<tr><td><b>About Customer :</b></td><td> " + data[2] + "</td></tr>"
//                    + "<tr><td>Currency</td><td>" + data[3] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Country :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + data[2] + "</td></tr>"
                    + "</table><BR>";
        }
        if (mailReason.trim().equals("customerApproved")) {
            String[] data = dataString.split("#~~#");
            othersMessageToSend = "Customer Approved by Finance.<br><br>"
                    + "The following are the customer details<BR><BR>"
                    + "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[0] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Sales Person Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[1] + "</td></tr>"
//                    + "<tr><td>Currency</td><td>" + data[2] + "</td></tr>"
//                    + "<tr><td>Country</td><td>" + data[3] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Code :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[2] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Country :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[3] + "</td></tr>"
                    + "</table><BR>"
                    + "Regards,<BR>"
                    + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
        }
        if (mailReason.trim().equals("customerRejected")) {
            String[] data = dataString.split("#~~#");
            othersMessageToSend = "Customer Referred Back by Finance.<br><br>"
                    + "The following are the customer details<BR><BR>"
                    + "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[0] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Sales Person Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[1] + "</td></tr>"
//                    + "<tr><td>Currency</td><td>" + data[2] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Country :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[2] + "</td></tr>"
                    + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Referred Back remarks :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + data[3] + "</td></tr>"
                    + "</table><BR>"
                    + "Regards,<BR>"
                    + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
        }
        if (mailReason.trim().equals("RequestorSubmit")) {
            String[] data = dataString.split("#~~#");
            String action = null;
            if (data[1].equalsIgnoreCase("QMS Query")) {
                action = "clarification";
            } else if (data[1].equalsIgnoreCase("QMS Complaint")) {
                action = "resolution";
            } else if (data[1].equalsIgnoreCase("Process improvement feedback")) {
                action = "evaluation";
            } else {
                action = "action";
            }
            othersMessageToSend = "Dear Quality Team,<br><br>"
                    + data[3] + " has submitted " + data[1] + " for your " + action + "." + "<br><br>"
                    + "Reference Id  : " + data[0] + "<br><br>"
                    + "Description   : " + data[2] + "<br><br>"
                    + "Regards,<BR>"
                    + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
        }
        if (mailReason.trim().equals("QualityAdminSubmit")) {
            String[] data = dataString.split("#~~#");
            if (data[1].equalsIgnoreCase("QMS Query") || data[1].equalsIgnoreCase("QMS Complaint")) {
                othersMessageToSend = "Dear " + data[6] + "," + "<br><br>"
                        + data[7] + "   responded your request " + "<br><br>"
                        + "Reference Id  : " + data[0] + "<br><br>"
                        +"Response       : " + data[8] +"<br><br>"
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";

            } else if (data[1].equalsIgnoreCase("Process improvement feedback")) {
                othersMessageToSend = "Dear " + data[6] + "," + "<br><br>"
                        + data[7] + "   responded your QMS request " + "<br><br>"
                        + "Reference Id  : " + data[0] + "<br><br>"
                        + "Evaluation Status :" + data[5] + "<br><br>"
 
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
            }

        }
        return othersMessageToSend;
    }

    public String getMessage(String mailReason, String dataString) {
        if (mailReason.trim().equals("empToRM")) {
            String[] data = dataString.split("#~~#");
            messageToSend = "Dear " + data[0] + ",<br><br>"
                    + "The employee  <b>" + data[1] + "</b> reporting to you has initiated an Resignation Request.<br><br>Kindly logon to iDEAL and Approve/Reject the request.<br><br>"
                    + "Click on the link http://ideal.hindujatech.com/users/login to see the details<br><br>Please reach Human Resources for any assistance if required."
                    + "<br><br>With Warm Regards,<br>DHR.";
        }
        if (mailReason.trim().equals("rmApproveToEmp")) {
            String[] data = dataString.split("#~~#");
            messageToSend = "Dear All,<br><br>"
                    + "Resignation Form submitted by <b>" + data[0] + "</b> has been approved by <b>" + data[1] + "</b><br><br>"
                    + "Kindly approve the <b>no due clearance</b>.<br><br>"
                    + "Click on the link http://ideal.hindujatech.com/users/login to see the details<br>"
                    + "<br>DHR ";
        }
        if (mailReason.trim().equals("rmRejectToEmp")) {
            String[] data = dataString.split("#~~#");
            messageToSend = "Dear " + data[0] + ",<br><br>"
                    + "Your Resignation Form rejected by " + data[1] + "<br><br>"
                    + "Click on the link http://ideal.hindujatech.com/users/login to see the details<br>"
                    + "<br>DHR ";
        }
        return messageToSend;
    }
}
