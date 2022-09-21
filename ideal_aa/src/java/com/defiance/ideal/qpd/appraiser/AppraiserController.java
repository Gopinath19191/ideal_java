/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser;

import com.defiance.ideal.qpd.appraiser.bo.AppraiserBO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMailDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import org.apache.log4j.Logger;
//import org.apache.catalina.connector.Request;

/**
 *
 * @author Administrator
 */
@Jpf.Controller()
public class AppraiserController extends PageFlowController {

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private AppraiserBO controlObj;

    @Control
    private MysqlDatabase sharedControlObj;
    

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseelist",path="appraiseelist.jsp")
    })
    public Forward begin() throws Exception
    {
        Forward fwd=null;

        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        int employeeId = Integer.parseInt((String)sessionObj.getAttribute("employeeId"));





         int appraisalQuarter;
         int appraisalYear;

           if(request.getParameter("appraisalYear")==null){
                    appraisalYear = CommonFunctions.getAppraisalYear(this.getRequest().getSession());

            }else{
                    appraisalYear = Integer.parseInt((String)request.getParameter("appraisalYear"));
            }
            if(request.getParameter("appraisalQuarter")==null){
                    appraisalQuarter = CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());
            }else{
                    appraisalQuarter = Integer.parseInt((String)request.getParameter("appraisalQuarter"));
            }

              int previousYear = (CommonFunctions.getAppraisalYear()-1);
              ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
              myArrrayList.add(CommonFunctions.getAppraisalYear());
              myArrrayList.add(previousYear);
          





        AppraiseeListDTO[] appraiseeData = controlObj.getAppraiseeList(employeeId,appraisalYear,appraisalQuarter);
        request.setAttribute("appraiseeData", appraiseeData);
        request.setAttribute("appraisalYear", appraisalYear);
        request.setAttribute("appraisalQuarter", appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);
        
        fwd = new Forward("appraiseelist");
        return fwd;
    }


     @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiserscreen",path="appraiseedetails.jsp"),
        @Jpf.Forward(name="AppraiseeNotTriggeredPreviousQuarter",path="appraiseeNotTrigPrevious.jsp")
    })
    public Forward fetchAppraiseeData(AppraiseeListDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        //String empId=(String) sessionObj.getAttribute("loginId");
        if(request.getAttribute("initialData")!=null){
            formData = (AppraiseeListDTO) request.getAttribute("initialData");
        }

        int bandId;
        int appraisalQuarter;
        int appraisalYear;
        int appraiseeId;
        
        //int submitStatus;
        //String appraiseeName;
        //String appraiseeJoiningDate;
        String appraiseeStatus;
        bandId = formData.getBandIdForm();
        appraisalQuarter = formData.getAppraisalQuarterForm();
        appraisalYear = formData.getAppraisalYearForm();
        appraiseeId = formData.getAppraiseeIdForm();
        appraiseeStatus = formData.getAppraiseeStatusForm();
         logger.info("-----------------appraiseeStatus------------------" + appraiseeStatus);
        int currentYear=CommonFunctions.getAppraisalYear();
        int previousYear = (currentYear-1);
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(currentYear);
                myArrrayList.add(previousYear);
        request.setAttribute("yearData",myArrrayList);
        request.setAttribute("appraiseeInitialData",formData);
        AppraiseeListDTO appraisalSubmitStatus=controlObj.getSubmitStatus(appraiseeId,appraisalQuarter,appraisalYear);
         
         
        /* if(appraisalSubmitStatus.getAppraisalSubmitStatus()!=2){
             System.out.println("appraisalSubmitStatus---"+appraisalSubmitStatus.getDiscussionDate());
                 String dcndate = appraisalSubmitStatus.getDiscussionDate();
        String[] dt = dcndate.split("-");
        String date  = dt[0];
        String month = dt[1];
        String year = dt[2];
        
        String moddate = date+"/"+month+"/"+year;
         
        
        //String disdate = sdf.format(new Date(formData.getDatePicker().replaceAll("-", "/")));
         appraisalSubmitStatus.setDiscussionDate(moddate);}*/
         
        if(appraisalSubmitStatus != null){
//                  request.setAttribute("getSelectedAppraiseeDetails", submitStatus);
                    request.setAttribute("submitStatus",appraisalSubmitStatus.getAppraisalSubmitStatus());
                    request.setAttribute("appraiserComments", appraisalSubmitStatus.getAppraiserComments());
                    request.setAttribute("appraiserObj", appraisalSubmitStatus);
                    
                    
             logger.info("appraiseeId = "+appraiseeStatus+"--"+bandId+"--"+appraisalQuarter+"--"+appraisalYear+"--"+appraiseeId);
        
                    CommonConfigurations commonConfig=new CommonConfigurations();
                    HashMap employeeStatus=commonConfig.employeeStatus;
             logger.info(employeeStatus.get(appraiseeStatus));
                    request.setAttribute("employeeStatus",employeeStatus.get(appraiseeStatus));
                    
        AppraiserFormDTO[] kraData = controlObj.getKraData(bandId,appraisalQuarter,appraisalYear,appraiseeId,appraisalSubmitStatus.getDepartmentId());
        AppraiserFormDTO[] topAchievements = controlObj.getAchievementsData(appraisalYear,appraiseeId);
        AppraiserFormDTO[] developmentNeeds = controlObj.getDevelopmentData(appraisalYear,appraiseeId);
        AppraiserFormDTO[] goalSheet = controlObj.getGoalData(appraisalYear,appraiseeId);

        AppraiserRatingFormDTO[] qpdRatingDetails = controlObj.getLastFourQPDRating(appraiseeId);
        request.setAttribute("kraData",kraData);
        request.setAttribute("qpdRatingDetails",qpdRatingDetails);
        request.setAttribute("topAchievements",topAchievements);
        request.setAttribute("developmentNeeds",developmentNeeds);
        request.setAttribute("goalSheet",goalSheet);
        
        if(kraData != null){
            SendMailDTO[] fileData = sharedControlObj.getFilesList(formData.getQpdId(),CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE,appraisalYear);
            request.setAttribute("fileDetails",fileData);
         }

        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        System.out.println("selected button "+button);
        logger.info("details "+formData.getAppraiseeNameForm()+formData.getAppraiseeNumberForm()+employeeStatus.get(appraiseeStatus)+formData.getAppraiseeJoiningDateForm()+kraData.length);
        if(("export").equals(button)){
            logger.info("inside condition ");
        //CommonFunctions.exportToExcel(responseObj, myArrrayList, myArrrayList, empId);
        }
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
                    ArrayList appraiseeInfo = new ArrayList();

                    ArrayList appraiseeDetRow1=new ArrayList();
                              appraiseeDetRow1.add("Appraisee Name");
                              appraiseeDetRow1.add(formData.getAppraiseeNameForm());
                              appraiseeDetRow1.add("");
                              appraiseeDetRow1.add("Appraisee ID");
                              appraiseeDetRow1.add(formData.getAppraiseeNumberForm());
                              appraiseeDetRow1.add("Appraisee Band");
                              appraiseeDetRow1.add(appraisalSubmitStatus.getBandName());
                    appraiseeInfo.add(appraiseeDetRow1);
                    ArrayList appraiseeDetRow2=new ArrayList();
                              appraiseeDetRow2.add("Appraisee Status");
                              appraiseeDetRow2.add(employeeStatus.get(appraiseeStatus));
                              appraiseeDetRow2.add("");
                              appraiseeDetRow2.add("Appraisee Joining Date");
                              appraiseeDetRow2.add(formData.getAppraiseeJoiningDateForm());
                    appraiseeInfo.add(appraiseeDetRow2);
                    ArrayList headerData = new ArrayList();
                          headerData.add(new String("KEY RESULT AREA'S"));
                          headerData.add(new String("ATTRIBUTES"));
                          headerData.add(new String("PERFORMANCE CRITERIA"));
//                          headerData.add(new String("MEASUREMENT CRITERIA"));
                          headerData.add(new String("Weightage"));
                          headerData.add(new String("KEY ACHIEVEMENTS-Comments"));
                          headerData.add(new String("APPRAISER RATING"));
                          headerData.add(new String("RATING WEIGHTAGE"));
                          headerData.add(new String("Appraiser Comments"));
                    appraiseeInfo.add(headerData);
                    float performanceRate=0;
                    for(int i=0;i<kraData.length;i++){
                        ArrayList rowDataList=new ArrayList();
                        int weightage=kraData[i].getWeightage();
                        int appraserRate=kraData[i].getAppraiserComments();
                        float rateWeightage=(float)(weightage*appraserRate)/100;
                        
                        logger.info("rate weight"+rateWeightage);
                        rowDataList.add(new String(kraData[i].getKraDescription()));
                        rowDataList.add(new String(kraData[i].getAttributes()));
                        rowDataList.add(new String(kraData[i].getPerformanceCriteria()));
//                        rowDataList.add(new String(kraData[i].getMeasurementCriteria()));
                        rowDataList.add(weightage);
                        rowDataList.add(kraData[i].getSelfComments());
                        rowDataList.add(kraData[i].getAppraiserComments());
                        rowDataList.add(rateWeightage);
                        rowDataList.add(kraData[i].getAppraiserCommentsNew());
                        performanceRate=rateWeightage+performanceRate;

                        entireList.add(rowDataList);
                    }
                    logger.info("performance rating "+(int)performanceRate+Math.round(performanceRate));
                    ArrayList lastRow=new ArrayList();
                    lastRow.add("");
                    lastRow.add("Performance Level");
                    lastRow.add(Math.round(performanceRate));                     
                    entireList.add(lastRow);
                   
                    ArrayList appraiserDisscussionDate =new ArrayList();
                    appraiserDisscussionDate.add("");
                    appraiserDisscussionDate.add("Disscusion Date");
                    appraiserDisscussionDate.add(appraisalSubmitStatus.getDiscussionDate());
                     entireList.add(appraiserDisscussionDate);

                    ArrayList appraiserPromotionRow =new ArrayList();
                    appraiserPromotionRow.add("");
                    appraiserPromotionRow.add("Appraiser Promotion");
                    if(appraisalSubmitStatus.getAppraiserPromotionRecommeded()!=null)
                    {
                        if(appraisalSubmitStatus.getAppraiserPromotionRecommeded().equals("1"))
                        appraiserPromotionRow.add("Yes");
                        else
                        appraiserPromotionRow.add("NO");
                    }
                    else{
                        appraiserPromotionRow.add("N/A");
                    }

                    entireList.add(appraiserPromotionRow);
                    
                    ArrayList lastRow0=new ArrayList();
                    lastRow0.add("Justified Rating comments");
                    lastRow0.add(appraisalSubmitStatus.getAppraiserComments());
                    entireList.add(lastRow0);

                    ArrayList lastRow2=new ArrayList();
                    lastRow2.add("TECHNOLOGY TRAINING");
                        lastRow2.add(appraisalSubmitStatus.getTechnologyTraining());
                    entireList.add(lastRow2);
                    ArrayList lastRow3=new ArrayList();
                    lastRow3.add("AREAS OF IMPROVEMENT");
                        lastRow3.add(appraisalSubmitStatus.getAreasOfImprovement());
                    entireList.add(lastRow3);
                    ArrayList lastRow4=new ArrayList();
                    lastRow4.add("SOFT SKILL TRAINING");
                        lastRow4.add(appraisalSubmitStatus.getSoftskillTraining());
                    entireList.add(lastRow4);
                    ArrayList topAchievementHeader = new ArrayList();
                    ArrayList devNeedsHeader = new ArrayList();
                    ArrayList goalSheetHeader = new ArrayList();
                    topAchievementHeader.add(new String("Appraisee Top Achievements"));
                    topAchievementHeader.add(new String("Appraiser Remarks"));
                    devNeedsHeader.add(new String("Appraisee Development Needs"));
                    devNeedsHeader.add(new String("Appraiser Remarks"));
                    goalSheetHeader.add(new String("Appraisee Goal Sheet"));
                    goalSheetHeader.add(new String("Appraiser Remarks"));
                    entireList.add(topAchievementHeader);
                    appraiseeInfo.add(topAchievementHeader);
                    for(int i=0;i<topAchievements.length;i++){
                        ArrayList tARowDataList=new ArrayList();
                        tARowDataList.add(new String(topAchievements[i].getKeyResultArea()));
                        tARowDataList.add(new String(topAchievements[i].getAppraiserRemarks()));
                        entireList.add(tARowDataList);
                    }
                    entireList.add(devNeedsHeader);
                    appraiseeInfo.add(devNeedsHeader);
                    for(int i=0;i<developmentNeeds.length;i++){
                        ArrayList dNRowDataList=new ArrayList();
                        dNRowDataList.add(new String(developmentNeeds[i].getDevelopmentNeeds()));
                        dNRowDataList.add(new String(developmentNeeds[i].getApprDevRemarks()));
                        entireList.add(dNRowDataList);
                    }
                    entireList.add(goalSheetHeader);
                    appraiseeInfo.add(goalSheetHeader);
                    for(int i=0;i<goalSheet.length;i++){
                        ArrayList gSRowDataList=new ArrayList();
                        gSRowDataList.add(new String(goalSheet[i].getGoalData()));
                        gSRowDataList.add(new String(goalSheet[i].getAppGoalRemarks()));
                        entireList.add(gSRowDataList);
                    }
                    
                  CommonFunctions.exportToExcel(responseObj, appraiseeInfo, entireList, formData.getAppraiseeNumberForm()+".xls");
        }}
        fwd = new Forward("appraiserscreen");
        return fwd;
    }


       @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseelist",path="begin.do"),
        @Jpf.Forward(name="exportForward",path="fetchAppraiseeData.do?excel=export"),
        @Jpf.Forward(name="redirecttoappraiser",path="fetchAppraiseeData.do")
    })
    public Forward save(AppraiserRatingFormDTO formData) throws NullPointerException{
           
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj = request.getSession();
        Forward fwd=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //AppraiseeListDTO getSelectedAppraiseeDetails=(AppraiseeListDTO) sessionObj.getAttribute("getSelectedAppraiseeDetails");
       
        String button=request.getParameter("button");
        int appraiseeId=formData.getAppraiseeId();
        String appraiserId=(String) sessionObj.getAttribute("employeeId");
        logger.info("send back reason "+formData.getReasonSendbackAppraiser());
        String appPromoRecomm = request.getParameter("appraiserPromotionRecommeded");
        System.out.println("App Promo Recomm:::"+formData.getAppraiserPromotionRecommeded()+"-----"+appPromoRecomm+"--"+button);
        formData.setAppraiserId(appraiserId);
        formData.setButtonAction(button);
       // for(int i=0;i<=formData.getAppraiserRemarks().length;i++){
       // System.out.println("appraiserRemarks----"+formData.getAppraiserRemarks()[i]);}
           System.out.println("formData.getDatePicker()"+formData.getDatePicker());
        if(formData.getDatePicker()!=null){
        System.out.println("dissssssssssssss date"+formData.getDatePicker()); 
        String dcndate = formData.getDatePicker();
        String[] dt = dcndate.split("/");
        String date  = dt[0];
        String month = dt[1];
        String year = dt[2];
        
        String moddate = year+"-"+month+"-"+date;
        
        //String disdate = sdf.format(new Date(formData.getDatePicker().replaceAll("-", "/")));
         formData.setDatePicker(moddate);
         System.out.println("newwww date"+formData.getDatePicker());
        controlObj.updateAppraiseeData(formData);}
        if(("Save").equals(button) || ("Export to Excel").equals(button)){
            AppraiseeListDTO initialData = new AppraiseeListDTO();
            initialData.setBandIdForm(formData.getBandId());
            initialData.setQpdId(formData.getQpdId());
            initialData.setAppraisalQuarterForm(formData.getAppraiseeQuarter());
            initialData.setAppraisalYearForm(formData.getAppraiseeYear());
            initialData.setAppraiseeIdForm(appraiseeId);

            initialData.setAppraiseeSubmitStatusForm(formData.getSubmitStatus());
            
            initialData.setAppraiseeJoiningDateForm(formData.getAppraiseeJoiningDate());
            initialData.setAppraiseeNameForm(formData.getAppraiseeName());
            initialData.setAppraiseeNumberForm(formData.getAppraiseeNumber());
            initialData.setAppraiseeStatusForm(formData.getAppraiseeStatus());
            
            request.setAttribute("initialData",initialData);
            if(("Save").equals(button)){
            String successMsg = "Comments Saved Successfully";
            request.setAttribute("savedmsg",successMsg);
            fwd=new Forward("redirecttoappraiser");
            }else{
            String successMsg = "Saved to excel Successfully";
            request.setAttribute("savedmsg",successMsg);
             logger.info("export to excel");
             fwd=new Forward("exportForward");
            }

        }else  if(("Send Back To Appraisee").equals(button)){
            int sendBackStatus = 1;
            controlObj.updateAppraiseeStatus(button,formData.getAppraiseeId(),formData.getAppraiseeQuarter(),formData.getAppraiseeYear(),sendBackStatus,formData.getReasonSendbackAppraiser());
            String successMsg = "Comments Sent Back Successfully";
            request.setAttribute("savedmsg",successMsg);
            fwd=new Forward("appraiseelist");
            fwd.setRedirect(true);
        }else{

            int sendBackStatus = 0;
            controlObj.updateAppraiseeStatus(button,formData.getAppraiseeId(),formData.getAppraiseeQuarter(),formData.getAppraiseeYear(),sendBackStatus,formData.getReasonSendbackAppraiser());
            String successMsg = "Comments Submitted Successfully";
            request.setAttribute("savedmsg",successMsg);
//          fwd=new Forward("redirecttohome");
            fwd=new Forward("appraiseelist");
            fwd.setRedirect(true);
        }
        return fwd;
    }

    @Jpf.Action()
    public Forward fileDownload(SendMailDTO formData) throws Exception {
        HttpServletRequest requestObj = this.getRequest();
        HttpServletResponse response =  this.getResponse();
        String filePath = CommonConfigurations.fileUploadPath;
        CommonFunctions.fileDownload(formData.getFileName(),filePath,formData.getFileType(),response);
//      controlObj.fileDownload(fileName,filePath,response);
        Forward fwd = null;
        return fwd;
    }

}
