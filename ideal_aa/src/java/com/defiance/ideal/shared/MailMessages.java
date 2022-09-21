/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class MailMessages {

    String salutation = "Dear";
    String MessageToSend;
    String SubjectToSend;
    String QpdStartDate = "29-Dec-2010";
    String QpdStartWeek = "";
    String AppraiseeEndDate = "05-Jan-2011";
    String AppraiseeEndWeek = "";
    String AppraiserStartDate = "05-Jan-2011";
    String AppraiserStartWeek = "";
    String AppraiserEndDate = "10-Jan-2011";
    String AppraiserEndWeek = "";
    String ReviewerStartDate = "10-Jan-2011";
    String ReviewerStartWeek = "";
    String ReviewerEndDate = "12-Jan-2011";
    String ReviewerEndWeek = "";
    String FinalReviewerStartDate = "12-Jan-2011";
    String FinalReviewerStartWeek = "";
    String FinalReviewerEndDate = "15-Jan-2011";
    String FinalReviewerEndWeek = "";
    String HrStartDate = "15-Jan-2011";
    String HrEndDate = "18-Jan-2011";
    String QuarterlyPayoutDate = "31-Jan-2011";
    String QpdTimeLineTable = "";
    //Calender Days
    String appraiseeCalenderDays = "";
    String appraiserCalenderDays = "";
    String reviewerCalenderDays = "";
    String finalReviewerCalenderDays = "";
    String buhCalenderDays = "";
    //Working Days
    String appraiseeWorkingDays = "";
    String appraiserWorkingDays = "";
    String reviewerWorkingDays = "";
    String finalReviewerWorkingDays = "";
    String buhWorkingDays = "";
    //Mail Subject
    String hrToAppraiseeSubjForAppraisee = "";
    String hrToAppraiseeSubjForAppraiser = "";
    String appriaseeToAppraiserSubjForAppriasee = "";
    String appriaseeToAppraiserSubjForAppraiser = "";
    String appraiserToReviewerSubjForAppraiser = "";
    String appraiserToReviewerSubjForReviewer = "";
    String appraiserToAppraiseeSendBackSubj = "";
    String reviewerToFRSubjForReviewer = "";
    String reviewerToFRSubjForFR = "";
    String fRToHRSubjForFR = "";
    String fRToHRSubjForHR = "";
    String initialMailTriggerToAppraiseeMsg = "";
    String mailToAssessorOnSelfAssessmentTriggerMsg = "";
    String mailToAssessorOnSelfAssessmentCompletionMsg = "";
    String appraiseeToAppraiserSelfMsg = "";
    String appraiserSendBackToAppraiseeMsg = "";
    String appraiserToReviewerMsgForReviewer = "";
    String appraiserToReviewerMsgForAppraiser = "";
    String reviewerToFrMsgForReviewer = "";
    String reviewerToFrMsgForFR = "";
    String frToHrMsgForFr = "";
    String frToHrMsgForHr = "";
    String generalContent = "";
    String hrToAppraiseeLevelAppraiserContent = "";
    String appraiseeToAppraiserLevelAppraiserContent = "";

    public MailMessages() {

        Properties configFile = new Properties();
        try {
            configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        QpdStartDate = configFile.getProperty("QPD_START_DATE");
        QpdStartWeek = configFile.getProperty("QPD_START_WEEK");
        AppraiseeEndDate = configFile.getProperty("APPRAISEE_END_DATE");
        AppraiseeEndWeek = configFile.getProperty("APPRAISEE_END_WEEK");

        AppraiserStartDate = configFile.getProperty("APPRAISER_START_DATE");
        AppraiserStartWeek = configFile.getProperty("APPRAISER_START_WEEK");
        AppraiserEndDate = configFile.getProperty("APPRAISER_END_DATE");
        AppraiserEndWeek = configFile.getProperty("APPRAISER_END_WEEK");

        ReviewerStartDate = configFile.getProperty("REVIEWER_START_DATE");
        ReviewerStartWeek = configFile.getProperty("REVIEWER_START_WEEK");
        ReviewerEndDate = configFile.getProperty("REVIEWER_END_DATE");
        ReviewerEndWeek = configFile.getProperty("REVIEWER_END_WEEK");

        FinalReviewerStartDate = configFile.getProperty("FINAL_REVIEWER_START_DATE");
        FinalReviewerStartWeek = configFile.getProperty("FINAL_REVIEWER_START_WEEK");
        FinalReviewerEndDate = configFile.getProperty("FINAL_REVIEWER_END_DATE");
        FinalReviewerEndWeek = configFile.getProperty("FINAL_REVIEWER_END_WEEK");

        HrStartDate = configFile.getProperty("HR_START_DATE");
        HrEndDate = configFile.getProperty("HR_END_DATE");

        QuarterlyPayoutDate = configFile.getProperty("QUARTERLY_PAYOUT_DATE");

        appraiseeWorkingDays = configFile.getProperty("APPRAISEE_WORKING_DAYS");
        appraiserWorkingDays = configFile.getProperty("APPRAISER_WORKING_DAYS");
        reviewerWorkingDays = configFile.getProperty("REVIEWER_WORKING_DAYS");
        finalReviewerWorkingDays = configFile.getProperty("FINAL_REVIEWER_WORKING_DAYS");
        buhWorkingDays = configFile.getProperty("BUH_WORKING_DAYS");

        appraiseeCalenderDays = configFile.getProperty("APPRAISEE_CALENDER_DAYS");
        appraiserCalenderDays = configFile.getProperty("APPRAISER_CALENDER_DAYS");
        reviewerCalenderDays = configFile.getProperty("REVIEWER_CALENDER_DAYS");
        finalReviewerCalenderDays = configFile.getProperty("FINAL_REVIEWER_CALENDER_DAYS");
        buhCalenderDays = configFile.getProperty("BUH_CALENDER_DAYS");

        hrToAppraiseeSubjForAppraisee = configFile.getProperty("HrToAppraiseeSubjForAppraisee");
        hrToAppraiseeSubjForAppraiser = configFile.getProperty("HrToAppraiseeSubjForAppraiser");
        appriaseeToAppraiserSubjForAppriasee = configFile.getProperty("AppriaseeToAppraiserSubjForAppriasee");
        appriaseeToAppraiserSubjForAppraiser = configFile.getProperty("AppriaseeToAppraiserSubjForAppraiser");
        appraiserToReviewerSubjForAppraiser = configFile.getProperty("AppraiserToReviewerSubjForAppraiser");
        appraiserToReviewerSubjForReviewer = configFile.getProperty("AppraiserToReviewerSubjForReviewer");
        appraiserToAppraiseeSendBackSubj = configFile.getProperty("AppraiserToAppraiseeSendBackSubj");
        reviewerToFRSubjForReviewer = configFile.getProperty("ReviewerToFRSubjForReviewer");
        reviewerToFRSubjForFR = configFile.getProperty("ReviewerToFRSubjForFR");
        fRToHRSubjForFR = configFile.getProperty("FRToHRSubjForFR");
        fRToHRSubjForHR = configFile.getProperty("FRToHRSubjForHR");

        initialMailTriggerToAppraiseeMsg = configFile.getProperty("InitialMailTriggerToAppraiseeMsg");
        mailToAssessorOnSelfAssessmentTriggerMsg = configFile.getProperty("MailToAssessorOnSelfAssessmentTriggerMsg");
        mailToAssessorOnSelfAssessmentCompletionMsg = configFile.getProperty("MailToAssessorOnSelfAssessmentCompletionMsg");
        appraiseeToAppraiserSelfMsg = configFile.getProperty("AppraiseeToAppraiserSelfMsg");
        appraiserSendBackToAppraiseeMsg = configFile.getProperty("AppraiserSendBackToAppraiseeMsg");
        appraiserToReviewerMsgForReviewer = configFile.getProperty("AppraiserToReviewerMsgForReviewer");
        appraiserToReviewerMsgForAppraiser = configFile.getProperty("AppraiserToReviewerMsgForAppraiser");
        reviewerToFrMsgForReviewer = configFile.getProperty("ReviewerToFrMsgForReviewer");
        reviewerToFrMsgForFR = configFile.getProperty("ReviewerToFrMsgForFR");
        frToHrMsgForFr = configFile.getProperty("FrToHrMsgForFr");
        frToHrMsgForHr = configFile.getProperty("FrToHrMsgForHr");


        generalContent = configFile.getProperty("GeneralContent");
        hrToAppraiseeLevelAppraiserContent = configFile.getProperty("HrToAppraiseeLevelAppraiserContent");
        appraiseeToAppraiserLevelAppraiserContent = configFile.getProperty("AppraiseeToAppraiserLevelAppraiserContent");


//        QpdTimeLineTable = "<table border='1' style='border-collapse: collapse' >" +
//                        "<tr><th><b>Annual appraisal Timelines</b></th><th><b>Start Date</b></th><th><b>End Date</b></th><th>Timeframe</th></tr>"+
//                        "<tr><td>Submission by Appraisee</td><td>"+QpdStartDate+"</td><td>"+AppraiseeEndDate+"</td><td>7 Calendar Days</td></tr>"+
//                        "<tr><td>Submission by Appraiser to Reviewer</td><td>"+AppraiserStartDate+"</td><td>"+AppraiserEndDate+"</td><td>7 Calendar Days</td></tr>"+
//                        "<tr><td>Final review</td><td>"+FinalReviewerStartDate+"</td><td>"+FinalReviewerEndDate+"</td><td>3 Calendar Days</td></tr>"+
//                        "<tr><td>Normalization</td><td colspan='3' align='center'>"+QuarterlyPayoutDate+"</td></tr></table><br>";

//                QpdTimeLineTable = "<table border='1' style='border-collapse: collapse' >" +
//                        "<tr><th><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Annual appraisal Timelines &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></th><th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Start Date</b>&nbsp;&nbsp;&nbsp;&nbsp;</th><th>&nbsp;&nbsp;&nbsp;&nbsp;<b>End Date</b>&nbsp;&nbsp;&nbsp;&nbsp;</th><th>&nbsp;&nbsp;<b>Working Days</b>&nbsp;&nbsp;</th><th>&nbsp;&nbsp;<b>Calender Days</b>&nbsp;&nbsp;</th></tr>"+
//                        "<tr><td>Appraisee self Assessment</td><td align='center'>"+QpdStartDate+"</td><td align='center'>"+AppraiseeEndDate+"</td><td align='center'>"+appraiseeWorkingDays+"</td><td align='center'>"+appraiseeCalenderDays+"</td></tr>"+
//                        "<tr><td>Appraiser Phase</td><td align='center'>"+AppraiserStartDate+"</td><td align='center'>"+AppraiserEndDate+"</td><td align='center'>"+appraiserWorkingDays+"</td><td align='center'>"+appraiserCalenderDays+"</td></tr>"+
//                        "<tr><td>Reviewer Phase</td><td align='center'>"+ReviewerStartDate+"</td><td align='center'>"+ReviewerEndDate+"</td><td align='center'>"+reviewerWorkingDays+"</td><td align='center'>"+reviewerCalenderDays+"</td></tr>"+
//                        "<tr><td>Normalization Phase</td><td align='center'>"+FinalReviewerStartDate+"</td><td align='center'>"+FinalReviewerEndDate+"</td><td align='center'>"+finalReviewerWorkingDays+"</td><td align='center'>"+finalReviewerCalenderDays+"</td></tr>"+
//                        "<tr><td>BU Head revision finalization</td><td align='center'>"+HrStartDate+"</td><td align='center'>"+HrEndDate+"</td><td align='center'>"+buhWorkingDays+"</td><td align='center'>"+buhCalenderDays+"</td></tr>"+
//                        "<tr><td>Preparation and Release of Letter</td><td colspan='4' align='center'>"+QuarterlyPayoutDate+"</td></tr></table>";
        QpdTimeLineTable = "<table border='1' style='border-collapse: collapse' >"
                + "<tr><th><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Annual appraisal Timelines &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></th><th>&nbsp;&nbsp;&nbsp;&nbsp;<b>Start Date</b>&nbsp;&nbsp;&nbsp;&nbsp;</th><th>&nbsp;&nbsp;&nbsp;&nbsp;<b>End Date</b>&nbsp;&nbsp;&nbsp;&nbsp;</th><th>&nbsp;&nbsp;<b>Calender Days</b>&nbsp;&nbsp;</th></tr>"
                + "<tr><td>Appraisee's  Phase - Self Assessment</td><td align='center'>" + QpdStartDate + "<br/>("+QpdStartWeek+")</td><td align='center'>" + AppraiseeEndDate + "<br/>("+AppraiseeEndWeek+")</td><td align='center'>" + appraiseeCalenderDays + "</td></tr>"
                + "<tr><td>Appraiser's Phase - Appraiser Assessment</td><td align='center'>" + AppraiserStartDate + "<br/>("+AppraiserStartWeek+")</td><td align='center'>" + AppraiserEndDate + "<br/>("+AppraiserEndWeek+")</td><td align='center'>" + appraiserCalenderDays + "</td></tr>"
                + "<tr><td>Reviewer's Phase - Review</td><td align='center'>" + ReviewerStartDate + "<br/>("+ReviewerStartWeek+")</td><td align='center'>" + ReviewerEndDate + "<br/>("+ReviewerEndWeek+")</td><td align='center'>" + reviewerCalenderDays + "</td></tr>"
                + "<tr><td>Normalization / BUH</td><td align='center'>" + FinalReviewerStartDate + "<br/>("+FinalReviewerStartWeek+")</td><td align='center'>" + FinalReviewerEndDate + "<br/>("+FinalReviewerEndWeek+")</td><td align='center'>" + finalReviewerCalenderDays + "</td></tr>"
                + "</table>";

    }

    /*
     public void sendMessage(int employeeId,String mailReason,String otherData,int mailCCId,MysqlDatabase dbCTRL)throws Exception{

     if(otherData!=null){
     String[] details = otherData.split("#-#");
     }
     SendMail mailObj = new SendMail();
     SendMailDTO employeeData = new SendMailDTO();
     SendMailDTO CCData = new SendMailDTO();
          
     try {
     employeeData = dbCTRL.getEmployeeEmailFromId(employeeId);
              
     CCData = dbCTRL.getEmployeeEmailFromId(mailCCId);
     } catch (Exception e) {
     e.printStackTrace();
     System.out.println("exception at sendMessage@MailMessages = " + e);
     }
          
     mailObj.smtpMail(employeeData.getEmailId(),this.getSubject(mailReason),this.getMessage(mailReason,employeeData),CCData.getEmailId());
     }
     */
    public String getSubject(String mailReason, String otherData) {

        String appraisalYear = null;
        if (otherData != null && !otherData.equals("")) {
            appraisalYear = Integer.parseInt(otherData.trim()) - 1 + "-" + otherData;
        }

        if (mailReason == "TRIGGER_APPRAISAL_APPRAISEE") {
//            SubjectToSend="Reg: Annual Appraisal for "+ appraisalYear;
            SubjectToSend = hrToAppraiseeSubjForAppraisee;
        }
        if (mailReason == "TRIGGER_APPRAISAL_APPRAISER") {
//            SubjectToSend="Reg: Annual Appraisal for "+ appraisalYear;
            SubjectToSend = hrToAppraiseeSubjForAppraiser;
        }
        if (mailReason == "UPDATE_APPRAISAL") {
            SubjectToSend = "Reg: Annual Appraisal Discussion details modification";
        }
        if (mailReason == "APPRAISEE_QPDFORM_SUBMIT") {
//            SubjectToSend="Reg: Annual Appraisal form submitted to Appraiser";
            SubjectToSend = appriaseeToAppraiserSubjForAppriasee;
        }
        if (mailReason == "APPRAISER_QPDFORM_SUBMIT") {
//            SubjectToSend="Reg: Annual Appraisal form submitted for review";
            SubjectToSend = appriaseeToAppraiserSubjForAppraiser;
        }
        if (mailReason == "APPRAISER_SUBMIT") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted to Reviewer";
            SubjectToSend = appraiserToReviewerSubjForAppraiser;
        }
        if (mailReason == "APPRAISER_SUBMIT_REVIEWER") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted for Review";
            SubjectToSend = appraiserToReviewerSubjForReviewer;
        }
        if (mailReason == "APPRAISER_SEND_BACK") {
//            SubjectToSend="Reg: Annual Appraisal form sent back for changes";
            SubjectToSend = appraiserToAppraiseeSendBackSubj;
        }
        if (mailReason == "REVIEWER_SUBMIT") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted for Final Review";
            SubjectToSend = reviewerToFRSubjForReviewer;
        }
        if (mailReason == "REVIEWER_SUBMIT_FINAL_REVIEW") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted for Final Review";
            SubjectToSend = reviewerToFRSubjForFR;
        }
        if (mailReason == "REVIEWER_SEND_BACK") {
            SubjectToSend = "Reg: Annual Appraisal Discussion Re-Enter Comments";
        }

        if (mailReason == "NORMALIZER_SUBMIT_HR") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted to HR for Review";
            SubjectToSend = fRToHRSubjForHR;
        }

        if (mailReason == "NORMALIZER_SUBMIT") {
//            SubjectToSend="Reg: Annual Appraisal forms submitted to HR for Review";
            SubjectToSend = fRToHRSubjForFR;
        }

        if (mailReason == "HR_SUBMIT") {
            SubjectToSend = "Reg: Annual Appraisal Discussion submitted to Finance";
        }
        if (mailReason == "HR_SUBMIT_FINANCE") {
            SubjectToSend = "Reg: Annual Appraisal Discussion submitted to Finance";
        }
        if (mailReason == "HR_SEND_BACK") {
            SubjectToSend = "Reg: Annual Appraisal ratings sent back for corrections";
        }
        return SubjectToSend;
    }

    public String getMessage(String mailReason, SendMailDTO employeeData, String additionalmessage) {

        if (mailReason == "TRIGGER_APPRAISAL_APPRAISEE") {
            MessageToSend = "Dear <i>" + employeeData.getEmployeeFullName() + "</i>,<br>"
                    + "Annual Appraisal Discussion has been triggered for you for the previous year,<br>"
                    + "Visit http://ideal.defiance-tech.com To complete the process";
        }
        if (mailReason == "TRIGGER_APPRAISAL_APPRAISER") {
            MessageToSend = "Dear <i>" + employeeData.getEmployeeFullName() + "</i>,<br>"
                    + "Annual Appraisal Discussion has been triggered for you For the Previous year,<br>"
                    + "Visit http://ideal.defiance-tech.com To complete the process";
        }
        if (mailReason == "UPDATE_APPRAISAL") {
            MessageToSend = "Dear <i>" + employeeData.getFullName() + "</i>,<br>"
                    + "Your Appraiser/Reviewer data was modified by HR,<br>"
                    + "Visit http://ideal.defiance-tech.com To complete the process";
        }
        if (mailReason == "APPRAISER_SUBMIT") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Appraiser has Submitted you appraisal to reviewer <br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "APPRAISER_SEND_BACK") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Reason for sending back :<b>" + additionalmessage + "</b><br>"
                    + "Please Re-Enter your comments in Appraisal form<br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "APPRAISEE_SUBMIT") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Your Comments were successfully submitted to appraiser and reviewer <br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "REVIEWER_SUBMIT") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Your Rating were sucessfully sent to HR for approval <br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "REVIEWER_SEND_BACK") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Appraisal Details were sent to concerned Appraisers for Final Review<br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "HR_SUBMIT") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Ratings were successfully sent to Finance for approval <br>"
                    + "http://ideal.defiance-tech.com";
        }
        if (mailReason == "HR_SEND_BACK") {
            MessageToSend = "Dear " + employeeData.getEmployeeFullName() + ",<br>"
                    + "Appraisal Details were sent to concerned Reviewers for Corrections<br>"
                    + "http://ideal.defiance-tech.com";
        }
        return MessageToSend;
    }

    public String getMessage(String mailReason, String dataString, String appraisalYear) {

        if (mailReason == "TRIGGER_APPRAISAL_APPRAISEE") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(appraisalYear), new String(AppraiseeEndDate)};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(initialMailTriggerToAppraiseeMsg, params) + generalContent;

//                MessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "The Annual Appraisal for year "+appraisalYear+" has been triggered. Your Annual appraisal form <br>" +
//                        "is available in IDEAL under ESS. Please enter your comments in the form <br>" +
//                        " and submit for review by <b>"+AppraiseeEndDate+"</b>. You can also upload any relevant documents <br>" +
//                        "along with the Annual appraisal form for substantiation of your achieved performance for the year.<br><br>" +
//                        "Please note that the online form becomes inactive after the due date and submission of the <br>" +
//                        "same for review after <b>"+AppraiseeEndDate+"</b> will not be possible.<br><br>" +
//                        "Click on the link http://ideal.defiance-tech.com/users/login to access you Appraisal form <br>" +
//                        "<br>DHR ";
        }

        if (mailReason == "TRIGGER_APPRAISAL_APPRAISER") {
            String[] data = dataString.split("#~~#");
            System.out.println("DATA----" + data[2]);
            Object[] params = new Object[]{new String(appraisalYear), new String(AppraiseeEndDate), new String(data[2])};

            System.out.print("params--->" + mailToAssessorOnSelfAssessmentTriggerMsg);
            System.out.print("table--->" + QpdTimeLineTable);
            System.out.print("table--->" + hrToAppraiseeLevelAppraiserContent);
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(mailToAssessorOnSelfAssessmentTriggerMsg, params) + QpdTimeLineTable + hrToAppraiseeLevelAppraiserContent;

//                MessageToSend = "Dear "+data[0]+",<br><br>" +
//                        "The Annual appraisal for "+appraisalYear+" has been triggered for the following appraisees.<br><br> " +data[2]+
//                        "<br><br>The Appraisal form is available in IDEAL under ESS. Your appraisee will enter comments <br>" +
//                        " and submit to you for review by "+AppraiseeEndDate+".Please note that the online form <br>" +
//                        "becomes inactive after the due date and submission of the same for review will not be possible.<br>" +
//                        "Make sure that your appraisee submits the appraisal form before the due date <b>"+AppraiseeEndDate+"</b>. Also note the <br>" +
//                        "timelines below and ensure that the Appraisal process for all your appraisees is completed on time <br><br>"+QpdTimeLineTable+
//                        "Click on the link http://ideal.defiance-tech.com/users/login to monitor the submission status of your appraisees <br><br>DHR";
        }


        if (mailReason == "UPDATE_APPRAISAL") {
            MessageToSend = "Dear <i>" + dataString + "</i>,<br>"
                    + "Your Annual appraisal data was modified by HR,<br>"
                    + "Visit http://ideal.defiance-tech.com to check the changes and resubmit your Annual appraisal form";
        }

        if (mailReason == "APPRAISEE_QPDFORM_SUBMIT") {
            String[] data = dataString.split("#~~#");
            MessageToSend = "Dear " + data[1] + ",<br><br>" + appraiseeToAppraiserSelfMsg;

//                MessageToSend = "Dear "+data[1]+",<br><br>Your Annual appraisal form has been submitted to your Appraiser for review." +
//                        "<br><br>" +
//                        "DHR";
        }
        if (mailReason == "APPRAISER_QPDFORM_SUBMIT") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[1]), new String(AppraiserEndDate)};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(mailToAssessorOnSelfAssessmentCompletionMsg, params) + QpdTimeLineTable + appraiseeToAppraiserLevelAppraiserContent;

//                MessageToSend = "Dear "+data[0]+",<br><br>" +
////                        "The Annual appraisal form of "+data[1]+" for the year <b>"+appraisalYear+"</b> has been submitted for review. Please complete the review within the due date and "+
//                        "The Annual appraisal form of "+data[1]+"</b> has been submitted for review. Please complete the review within the due date and "+
//                        "submit to Reviewer. If you require your appraisee to change/add remarks, you may use the 'send back to appraisee' "+
//                        "option and add your comments on the changes/additions you want made. Please make sure that your appraisee resubmits "+
//                        "the Appraisal form before the due date is "+AppraiserEndDate+". <br> "+
//                        "Also note the timelines below and ensure that the Annual appraisal process for all your appraisees "+
//                        "is completed on time <br> "+QpdTimeLineTable+
//                        "Click on the link http://ideal.defiance-tech.com/users/login to monitor the submission status of your appraisees <br><br>DHR";
        }

        if (mailReason == "APPRAISER_SEND_BACK") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(AppraiserEndDate)};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(appraiserSendBackToAppraiseeMsg, params) + generalContent;
//            MessageToSend = "Dear "+data[0]+",<br><br>" +
//                    "Your Annual appraisal form has been sent back by your appraiser for incorporating the following changes:<br>"+
//                    data[1]+"<br>"+
//                    "Please enter your comments  and re-submit for review by "+AppraiserEndDate+". You can also upload any "+
//                    "relevant documents with the appraisal form for substantiation of your achieved performance.<br> Please note that the online forms becomes "+
//                    "inactive after the due date and submission of the same for review after "+AppraiserEndDate+" will not be possible.<br>"+
//                    "Click on the link http://ideal.defiance-tech.com/users/login to access you Annual appraisal form <br><br>DHR";
        }
        if (mailReason == "APPRAISER_SUBMIT") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[2]), new String(data[0])};
            MessageToSend = "Dear " + data[1] + ",<br><br>"
                    + MessageFormat.format(appraiserToReviewerMsgForAppraiser, params);
//            MessageToSend = "Dear "+data[1]+",<br><br>" +
//                    "The Annual appraisal form of "+data[2]+" has been submitted to "+data[0]+" for review." +
//                    "<br><br>DHR";
        }
        if (mailReason == "APPRAISER_SUBMIT_REVIEWER") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[2]), new String(ReviewerEndDate)};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(appraiserToReviewerMsgForReviewer, params) + QpdTimeLineTable + appraiseeToAppraiserLevelAppraiserContent;
//            MessageToSend = "Dear "+data[0]+",<br><br>" +
//                    "The Annual appraisal form of <b>"+data[2]+"</b> has been submitted for review. Please complete the review within the due date and submit "+
//                    "for Final review. Please make sure that the forms of all the appraisees under your purview, are submitted for review before the due date:<br>"+ReviewerEndDate+"<br>"+
//                    "Also note the timelines below and ensure that the Appraisal process for all the appraisees under your purview is completed on time for payroll connection<br>"+QpdTimeLineTable+
//                        "Click on the link http://ideal.defiance-tech.com/users/login to complete your review and monitor the submission status of the Appraisal forms for review<br><br>DHR";
        }

        if (mailReason == "REVIEWER_SUBMIT") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[1])};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(reviewerToFrMsgForReviewer, params);
//                MessageToSend = "Dear "+data[0]+",<br><br>The Annual appraisal forms of the following appraisees have been submitted for final review<br>" +
//                        "<br>" +data[1]+
//                        "<br><br> DHR";
        }

        if (mailReason == "REVIEWER_SUBMIT_FINAL_REVIEW") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[1]), new String(FinalReviewerEndDate)};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + MessageFormat.format(reviewerToFrMsgForFR, params) + QpdTimeLineTable + appraiseeToAppraiserLevelAppraiserContent;
//                    MessageToSend = "Dear "+data[0]+",<br><br>" +
//                    "The Annual appraisal forms of the following appraisees have been submitted for your review.<br><br>"+data[1]+
//                    "<br><br>Please make sure that the forms of all the appraisees under your purview, are submitted before the due date: <b>"+ReviewerEndDate+"</b><br>"+
//                    "Also note the timelines below and ensure that the Appraisal process for all the appraisees under your purview is completed on time <br>"+QpdTimeLineTable+
//                    "Click on the link http://ideal.defiance-tech.com/users/login to complete your review and monitor the submission status of the Appraisal forms <br><br> DHR";
        }

        if (mailReason == "NORMALIZER_SUBMIT_HR") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[1]), new String(data[2])};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    //+ MessageFormat.format(frToHrMsgForHr, params);
                    +"The Assessment Forms from <b>"+data[1]+"</b> have been submitted for HR review.<br><br>The following Appraisee's Assessment data has been submitted <br><br> <b>"+data[2]+"</b><br>We wish you a successful Appraisal Season.<br><br>Regards<br>Human Resources.";
//            MessageToSend = "Dear "+data[0]+",<br><br>" +
//            "The Annual appraisal forms from <b>"+data[1]+"</b> have been submitted for HR review.<br><br>The following appraisees Appraisal data has been submitted <br><br>"+data[2]+"<br><br> DHR";
        }

        if (mailReason == "NORMALIZER_SUBMIT") {
            String[] data = dataString.split("#~~#");
            Object[] params = new Object[]{new String(data[1])};
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    //+ MessageFormat.format(frToHrMsgForFr, params);
                    +"The Assessment Forms under your purview have been submitted for HR review<br><br>The following Appraisee's Assessment data has been submitted <br><br><b>"+data[1]+"</b><br>We wish you a successful Appraisal Season.<br><br>Regards<br>Human Resources.";
//            MessageToSend = "Dear "+data[0]+",<br><br>" +
//            "The Appraisal forms under your purview have been submitted for HR review<br><br>The following appraisees appraisal data has been submitted <br><br>"+data[1]+"<br><br> DHR";
        }

        if (mailReason == "HR_SUBMIT_FINANCE") {

            String[] data = dataString.split("#~~#");
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + "The Appraisal forms from <b>" + data[1] + "</b> have been submitted for further processing.<br><br>The following appraisees appraisal data has been submitted <br><br>" + data[2] + "<br><br> DHR";
        }

        if (mailReason == "HR_SUBMIT") {
            String[] data = dataString.split("#~~#");
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + "The Annual appraisal forms under your purview has been submitted to Finance<br><br>The following appraisees annual appraisal data has been submitted <br><br>" + data[1] + "<br><br> DHR";
        }

        if (mailReason == "HR_SEND_BACK") {
            String[] data = dataString.split("#~~#");
            MessageToSend = "Dear " + data[0] + ",<br><br>"
                    + "The Appraisal ratings under your purview has been sent back for the necessary corrections. <br>"
                    + "Reason for sending back :<b>" + data[1] + ".</b><br>"
                    + "Please resubmit before the due date : " + FinalReviewerEndDate + " to ensure connection to payroll<br><br>DHR";
        }
        return MessageToSend;
    }
}
