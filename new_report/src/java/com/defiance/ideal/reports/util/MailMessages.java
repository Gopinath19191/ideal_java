/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.util;

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

    public String getSubject(String mailReason,String otherData){
        String[] data = otherData.split("#~~#");
        if(mailReason.trim().equals("customerSubmit")){
            subjectToSend="REG:NEW CUSTOMER IS ADDED AND AWAITING APPROVAL";
        }
        if(mailReason.trim().equals("customerAmended")){
            subjectToSend="REG: CUSTOMER DETAILS AMENDED AND AWAITING APPROVAL ";
        }
        if(mailReason.trim().equals("customerApproved")){
            subjectToSend="REG: CUSTOMER DETAILS APPROVED ";
        }
        if(mailReason.trim().equals("customerRejected")){
            subjectToSend="REG: CUSTOMER DETAILS REJECTED ";
        }
        return subjectToSend;
    }

    public String getOthersMailMessage(String mailReason,String dataString) {
        if(mailReason.trim().equals("customerSubmit")) {
                String[] data = dataString.split("#~~#");
                othersMessageToSend = "A new customer created by "+data[1]+" is awaiting approval.<br><br>"
                        + " The following are the customer details "
                        + "<style>"
                        + "tr td, tr th { border:1px solid #7E7E7E; padding: 8px 10px; font-family: Arial, Helvetica, sans-serif; font-size: 12px;} </style>"
                        + " <table> <tr><td>Customer Name</td><td>"+data[0]+"</td></tr>"
                        + "<tr><td>Sales Person Reference</td><td>"+data[1]+"</td></tr>"
                        + "<tr><td>About Customer</td><td>"+data[2]+"</td></tr>"
                        + "<tr><td>Currency</td><td>"+data[3]+"</td></tr>"
                        + "</table><BR>"
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.defiance-tech.com/users/login";
        }
        if(mailReason.trim().equals("customerAmended")) {
                String[] data = dataString.split("#~~#");
                othersMessageToSend = "Customer details amended by "+data[1]+" is awaiting approval.<br><br>"
                        + "The following are the customer details"
                        + "<style>"
                        + "tr td, tr th { border:1px solid #7E7E7E; padding: 8px 10px; font-family: Arial, Helvetica, sans-serif; font-size: 12px;} </style>"
                        + " <table> <tr><td>Customer Name</td><td>"+data[0]+"</td></tr>"
                        + "<tr><td>Sales Person Reference</td><td>"+data[1]+"</td></tr>"
                        + "<tr><td>About Customer</td><td>"+data[2]+"</td></tr>"
                        + "<tr><td>Currency</td><td>"+data[3]+"</td></tr>"
                        + "</table><BR>"
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.defiance-tech.com/users/login";
        }
        if(mailReason.trim().equals("customerApproved")) {
                String[] data = dataString.split("#~~#");
                othersMessageToSend = "Customer Approved by Finance.<br><br>"
                        + "The following are the customer details"
                        + "<style>"
                        + "tr td, tr th { border:1px solid #7E7E7E; padding: 8px 10px; font-family: Arial, Helvetica, sans-serif; font-size: 12px;} </style>"
                        + " <table> <tr><td>Customer Name</td><td>"+data[0]+"</td></tr>"
                        + "<tr><td>Sales Person Reference</td><td>"+data[1]+"</td></tr>"
                        + "<tr><td>Currency</td><td>"+data[2]+"</td></tr>"
                        + "</table><BR>"
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.defiance-tech.com/users/login";
        }
        if(mailReason.trim().equals("customerRejected")) {
                String[] data = dataString.split("#~~#");
                othersMessageToSend = "Customer Rejected by Finance.<br><br>"
                        + "The following are the customer details"
                        + "<style>"
                        + "tr td, tr th { border:1px solid #7E7E7E; padding: 8px 10px; font-family: Arial, Helvetica, sans-serif; font-size: 12px;} </style>"
                        + " <table> <tr><td>Customer Name</td><td>"+data[0]+"</td></tr>"
                        + "<tr><td>Sales Person Reference</td><td>"+data[1]+"</td></tr>"
                        + "<tr><td>Currency</td><td>"+data[2]+"</td></tr>"
                        + "</table><BR>"
                        + "Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.defiance-tech.com/users/login";
        }
        return othersMessageToSend;
    }


    public String getMessage(String mailReason,String dataString){
        if(mailReason.trim().equals("empToRM")){
                String[] data = dataString.split("#~~#");
                messageToSend = "Dear "+data[0]+",<br><br>" +
                        "The employee  <b>"+data[1]+"</b> reporting to you has initiated an Resignation Request.<br><br>Kindly logon to iDEAL and Approve/Reject the request.<br><br>" +
                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><br>Please reach Human Resources for any assistance if required." +
                        "<br><br>With Warm Regards,<br>DHR.";
        }
        if(mailReason.trim().equals("rmApproveToEmp")){
                String[] data = dataString.split("#~~#");
                messageToSend = "Dear All,<br><br>" +
                        "Resignation Form submitted by <b>"+data[0]+"</b> has been approved by <b>"+data[1]+"</b><br><br>" +
                        "Kindly approve the <b>no due clearance</b>.<br><br>" +
                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br>" +
                        "<br>DHR ";
        }
        if(mailReason.trim().equals("rmRejectToEmp")){
                String[] data = dataString.split("#~~#");
                messageToSend = "Dear "+data[0]+",<br><br>" +
                        "Your Resignation Form rejected by "+data[1]+"<br><br>" +
                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br>" +
                        "<br>DHR ";
        }
        return messageToSend;
    }

}
