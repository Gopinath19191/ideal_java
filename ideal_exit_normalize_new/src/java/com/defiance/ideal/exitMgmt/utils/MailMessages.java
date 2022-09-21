/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.utils;

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
    Properties configMailFile;
    public MailMessages() {
        Properties configFile = new Properties();
        configMailFile = new Properties();
            try {
                configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
                configMailFile.load(new FileInputStream(CommonConfigurations.ExternalConfigMailFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    public String getSubject(String mailReason,String otherData){
        String[] data = otherData.split("#~~#");
        if(mailReason.trim().equals("empToRM")){
            subjectToSend="Reg: Resignation Request";
        }
        if(mailReason.trim().equals("rmApproveToEmp")){
            subjectToSend="Reg: Resignation Form Approved by "+ data[1];
        }
        if(mailReason.trim().equals("rmRejectToEmp")){
            subjectToSend="Reg: Resignation Form Rejected by "+ data[1];
        }
        if(mailReason.trim().equals("adminToEmp")){
            subjectToSend="Acceptance of Resignation Letter "+ data[0];
        }
        if(mailReason.trim().equals("exitSurveyToHr")){
            subjectToSend="Reg: Exit Survey Submitted";
        }
        if(mailReason.trim().equals("pfRelatedNotes")){
            subjectToSend="Reg: PF related notes";
        }
        if(mailReason.trim().equals("accpLetter")){
            subjectToSend="Acceptance of Resignation Letter - "+ data[1];
        }
        if(mailReason.trim().equals("serviceLetter")){
            subjectToSend="Service Certificate and Relieving Letter - "+ data[1];
        }
        if(mailReason.trim().equals("empToRmUpdate")){
            subjectToSend="REG: Resignation Updation";
        }
        return subjectToSend;
    }

    public String getOthersMailMessage(String mailReason,String dataString,List formNoValues){
        if(mailReason.trim().equals("adminToEmpWithDue")){
            String formNoValueName = null;
            if (formNoValues.size() != 0) {
                for (int i = 0; i < formNoValues.size(); i++) {
                    
                    if(formNoValueName==null){
                        formNoValueName = i + 1 + ")" + formNoValues.get(i).toString() + "<br>";
                    }else{
                        formNoValueName = formNoValueName + (i + 1) + ")" + formNoValues.get(i).toString() + "<br>";
                    }
                    
                }
            }
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "You have the following dues pending with the <b>"+data[1]+"</b> <br>" +
//                        formNoValueName+
//                        "<br>Request you to kindly clear this at the earliest to proceed further<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Regards,<br>Human Resource.";
                //configMailFile.getProperty("adminToEmpWithDue");
                othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("adminToEmpWithDue"),
                        new String[]{"data[0]","data[1]","data[2]","formNoValueName"}, new String[]{data[0].toString(),data[1].toString(),data[2].toString(),formNoValueName.toString()});
              

        }
        if(mailReason.trim().equals("adminToEmpWithOutDue")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "Your no dues clearance with <b>"+data[1]+"</b> team has been successfully completed.<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Regards,<br>Human Resource.";
                othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("adminToEmpWithOutDue"),
                        new String[]{"data[0]","data[1]"}, new String[]{data[0],data[1]});
        }
        if(mailReason.trim().equals("hrToEmpWithOutDue")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "Your exit formalities have been approved and completed. You can now take a print out of your resignation acceptance letter available in the link below <br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Relieving letter / Service Certificate along with pending dues if any will be couriered to your residential address upon completion of your Full and Final settlement process.<br><br>FFS process will be communicated within 4 weeks from the last working date." +
//                        "<br><br>Regards,<br>Human Resource.";
                othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("hrToEmpWithOutDue"),
                        new String[]{"data[0]"}, new String[]{data[0]});
        }
        if(mailReason.trim().equals("exitSurveyToHrMsg")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear HR,<br><br>" +
//                        "Exit Interview  has been submitted by "+data[0]+"<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Regards,<br>Human Resource.";
                 othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("exitSurveyToHrMsg"),
                        new String[]{"data[0]"}, new String[]{data[0]});
        }
        if(mailReason.trim().equals("empToRM")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear "+data[2]+",<br><br>" +
//                        "The employee  <b>"+data[0]+"</b> reporting to you has initiated a resignation request.<br><br>Kindly logon to iDEAL and Approve/Reject the request.<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Please reach Human Resources for any assistance if required." +
//                        "<br><br>Regards,<br>Human Resource.";
                 othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("empToRM"),
                        new String[]{"data[2]","data[0]","data[1]"}, new String[]{data[2],data[0],data[1]});
        }
        if(mailReason.trim().equals("rmApproveToEmp")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear All,<br><br>" +
//                        "Resignation Form submitted by <b>"+data[0]+"</b> has been approved by <b>"+data[3]+"</b><br><br>" +
//                        "Kindly approve the <b>no due clearance</b>.<br><br>" +
//                       "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Regards,<br>Human Resource.";
                 othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("rmApproveToEmp"),
                        new String[]{"data[0]","data[3]","data[7]"}, new String[]{data[0],data[3],data[7]});
        }
        if(mailReason.trim().equals("rmRejectToEmp")){
                String[] data = dataString.split("#~~#");
//                othersMessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "Your Resignation Form rejected by "+data[3]+"<br><br>Reason for Rejection :" +data[2]+ "<br><br>"+
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                      " <br><br>Regards,<br>Human Resource.";
          othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("rmRejectToEmp"),
                        new String[]{"data[0]","data[3]","data[2]"}, new String[]{data[0].toString(),data[3].toString(),data[2].toString()});
        }
        if(mailReason.trim().equals("pfRelatedNotes")){
                String[] data = dataString.split("#~~#");
                othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("pfRelatedNotes"),
                new String[]{"data[0]"}, new String[]{data[0].toString()});
        }
        if(mailReason.trim().equals("accpLetter")){
            String[] data = dataString.split("#~~#");
            othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("accpLetter"),
            new String[]{"data[0]"}, new String[]{data[0].toString()});
        }
        if(mailReason.trim().equals("serviceLetter")){
            String[] data = dataString.split("#~~#");
            othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("serviceLetter"),
            new String[]{"data[0]"}, new String[]{data[0].toString()});
        }
        if(mailReason.trim().equals("empToRmUpdate")){
                String[] data = dataString.split("#~~#");
                othersMessageToSend=CommonFunctions.replaceString(configMailFile.getProperty("empToRmUpdate"),
                new String[]{"data[0]","data[1]","data[2]","data[3]","data[4]","data[5]"}, new String[]{data[3].toString(),data[0].toString(),data[3].toString(),data[4].toString(),data[5].toString(),data[6].toString()});
        }
        System.out.println("================== The other message to send ================ "+othersMessageToSend);
        return othersMessageToSend;
    }


    public String getMessage(String mailReason,String dataString){
        if(mailReason.trim().equals("empToRM")){
                String[] data = dataString.split("#~~#");
//                messageToSend = "Dear "+data[0]+",<br><br>" +
//                        "The employee  <b>"+data[1]+"</b> reporting to you has initiated an Resignation Request.<br><br>Kindly logon to iDEAL and Approve/Reject the request.<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Please reach Human Resources for any assistance if required." +
//                        "<br><br>Regards,<br>Human Resource.";
                messageToSend=CommonFunctions.replaceString(configMailFile.getProperty("getMessage.empToRM"),
                        new String[]{"data[0]","data[1]"}, new String[]{data[0].toString(),data[1].toString()});
        }
        if(mailReason.trim().equals("rmApproveToEmp")){
                String[] data = dataString.split("#~~#");
//                messageToSend = "Dear All,<br><br>" +
//                        "Resignation Form submitted by <b>"+data[0]+"</b> has been approved by <b>"+data[1]+"</b><br><br>" +
//                        "Kindly approve the <b>no due clearance</b>.<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                        "<br><br>Regards,<br>Human Resource.";
                 messageToSend=CommonFunctions.replaceString(configMailFile.getProperty("getMessage.rmApproveToEmp"),
                        new String[]{"data[0]","data[1]"}, new String[]{data[0].toString(),data[1].toString()});
        }
        if(mailReason.trim().equals("rmRejectToEmp")){
                String[] data = dataString.split("#~~#");
//                messageToSend = "Dear "+data[0]+",<br><br>" +
//                        "Your Resignation Form rejected by "+data[1]+"<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to see the details<br><b>Ideal > My Information > Exit Management </b>" +
//                       "<br><br>Regards,<br>Human Resource.";
                 messageToSend=CommonFunctions.replaceString(configMailFile.getProperty("getMessage.rmRejectToEmp"),
                        new String[]{"data[0]","data[1]"}, new String[]{data[0].toString(),data[1].toString()});
        }
        return messageToSend;
    }

}
