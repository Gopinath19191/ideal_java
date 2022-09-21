/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee;

import com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO;
import com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO;
import com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO;
import com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMailDTO;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.upload.FormFile;
import org.apache.log4j.Logger;
//import org.apache.catalina.connector.Request;

/**
 *
 * @author Administrator
 */
@Jpf.Controller(simpleActions = {
        @Jpf.SimpleAction(name = "start",path = "failure.jsp")},multipartHandler = Jpf.MultipartHandler.memory)
public class AppraiseeController extends PageFlowController {

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private AppraiseeBO controlObj;

    @Control
    private MysqlDatabase sharedControlObj;
    String fileErrormsg = "";
    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraisee",path="myappraisal.jsp"),
        @Jpf.Forward(name="notEligible",path="appraisalnottriggered.jsp")})
    public Forward begin() throws Exception{
        
        AppraiseeDetailsDTO authenticateAppraisee=null;
        Forward fwd=null;

        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
    
        String empNum=(String) sessionObj.getAttribute("employeeId");
        String empId=(String) sessionObj.getAttribute("loginId");
        int appraiseeId=Integer.parseInt(empNum);
        String successMsg="";
        String fileErrMsg="";
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

        request.setAttribute("empNum", empNum);
        request.setAttribute("appraiseeEmpId", empId);
                int previousYear = (CommonFunctions.getAppraisalYear()-1);
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
          

        try{
                authenticateAppraisee=controlObj.authenticateAppraisee(empNum,appraisalQuarter,appraisalYear);               
                request.setAttribute("appraiseeDetails", authenticateAppraisee);
             
                if(authenticateAppraisee != null){
                AppraiseeDetailsDTO appraiseeSBU=controlObj.getAppraiseeSBU(authenticateAppraisee.getDepartmentId());

//                SendMailDTO[] fileData = sharedControlObj.getFilesList(appraiseeId,CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE);
//                SendMailDTO[] fileData = sharedControlObj.getFilesList(authenticateAppraisee.getQpdId(),CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE,appraisalYear,appraisalQuarter);
                SendMailDTO[] fileData = sharedControlObj.getFilesList(authenticateAppraisee.getQpdId(),CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE,appraisalYear);
                
                request.setAttribute("fileDetails",fileData);
                request.setAttribute("sbuName",appraiseeSBU);
                
                MyAppraisalFormDTO[] kraData = controlObj.getKraData(authenticateAppraisee.getBandId(),appraisalYear,appraisalQuarter,empNum,authenticateAppraisee.getDepartmentId());
                
                MyAppraisalFormDTO[] topAchievements = controlObj.getAchievementsData(appraiseeId,appraisalYear);
                MyAppraisalFormDTO[] devNeeds = controlObj.getDevNeedsData(appraiseeId,appraisalYear);
                MyAppraisalFormDTO[] goalData = controlObj.getGoalData(appraiseeId,appraisalYear);
                
                logger.info("kraData Length = " + kraData.length);
                request.setAttribute("kraData",kraData);
                request.setAttribute("topAchievements",topAchievements);
                request.setAttribute("developmentNeeds",devNeeds);
                request.setAttribute("goalSheet",goalData);

                String button=(String)request.getParameter("excel");
                HttpServletResponse responseObj=getResponse();
                if(("export").equals(button)){
                    ArrayList entireList = new ArrayList();
                    ArrayList appraiseeInfo = new ArrayList();

                    ArrayList appraiseeDetRow1=new ArrayList();
                              appraiseeDetRow1.add("Appraisee Name");
                              appraiseeDetRow1.add(authenticateAppraisee.getAppraiseeName());
                              appraiseeDetRow1.add("");
                              appraiseeDetRow1.add("Appraisee ID");
                              appraiseeDetRow1.add(empId);
                    appraiseeInfo.add(appraiseeDetRow1);
                    ArrayList appraiseeDetRow2=new ArrayList();
                              appraiseeDetRow2.add("Appraiser Name");
                              appraiseeDetRow2.add(authenticateAppraisee.getAppraiserName());
                              appraiseeDetRow2.add("");
                              appraiseeDetRow2.add("Reviewer Name");
                              appraiseeDetRow2.add(authenticateAppraisee.getReviewerName());
                    appraiseeInfo.add(appraiseeDetRow2);
                    ArrayList headerData = new ArrayList();
                          headerData.add(new String("KEY RESULT AREA'S"));
                          headerData.add(new String("ATTRIBUTES"));
                          headerData.add(new String("PERFORMANCE CRITERIA"));
//                          headerData.add(new String("MEASUREMENT CRITERIA"));
                          headerData.add(new String("Weightage"));
                          headerData.add(new String("KEY ACHIEVEMENTS-Comments"));
                    appraiseeInfo.add(headerData);
                    for(int i=0;i<kraData.length;i++){
                        ArrayList rowDataList=new ArrayList();
                        int weightage=kraData[i].getWeightage();
                        rowDataList.add(new String(kraData[i].getKraDescription()));
                        rowDataList.add(new String(kraData[i].getAttributes()));
                        rowDataList.add(new String(kraData[i].getPerformanceCriteria()));
//                        rowDataList.add(new String(kraData[i].getMeasurementCriteria()));
                        rowDataList.add(weightage);
                        rowDataList.add(kraData[i].getSelfComments());

                        entireList.add(rowDataList);
                    }
                    ArrayList topAchievementHeader = new ArrayList();
                    ArrayList devNeedsHeader = new ArrayList();
                    ArrayList goalSheetHeader = new ArrayList();
                    topAchievementHeader.add(new String("Top Achievements"));
                    devNeedsHeader.add(new String("Development Needs"));
                    goalSheetHeader.add(new String("Goal Sheet"));
                    entireList.add(topAchievementHeader);
                    appraiseeInfo.add(topAchievementHeader);
                    for(int i=0;i<topAchievements.length;i++){
                        ArrayList tARowDataList=new ArrayList();
                        tARowDataList.add(new String(topAchievements[i].getKeyResultArea()));
                        entireList.add(tARowDataList);
                    }
                    entireList.add(devNeedsHeader);
                    appraiseeInfo.add(devNeedsHeader);
                    for(int i=0;i<devNeeds.length;i++){
                        ArrayList dNRowDataList=new ArrayList();
                        dNRowDataList.add(new String(devNeeds[i].getDevelopmentNeed()));
                        entireList.add(dNRowDataList);
                    }
                    entireList.add(goalSheetHeader);
                    appraiseeInfo.add(goalSheetHeader);
                    for(int i=0;i<goalData.length;i++){
                        ArrayList gSRowDataList=new ArrayList();
                        gSRowDataList.add(new String(goalData[i].getGlData()));
                        entireList.add(gSRowDataList);
                    }
                  CommonFunctions.exportToExcel(responseObj, appraiseeInfo, entireList, empId+".xls");
                  successMsg = "Saved to Excel Successfully";
                  
                }
            }
                request.setAttribute("appraisalYear",appraisalYear);
                request.setAttribute("appraisalQuarter",appraisalQuarter);
                request.setAttribute("yearData",myArrrayList);
                
          fwd=new Forward("appraisee");
        }catch(Exception e){
            logger.error("Exception @ appraiseeController @ begin"+e.getMessage());
        }
        if(("savemsg").equals((String)request.getParameter("save"))) {
                successMsg = "Comments Saved Successfully";
        }
        if(("submitmsg").equals((String)request.getParameter("submit"))) {
                successMsg = "Comments Submitted Successfully";
                if(!("").equals(fileErrormsg)){
                    fileErrMsg = "QPD form has not been submitted<br>";
                }
        }
        if(!("").equals(fileErrormsg)){
                fileErrMsg += "Uploading failed,you are trying to upload 0kb files "+fileErrormsg;
        }
        request.setAttribute("savedmsg",successMsg);
        request.setAttribute("fileErrMsg", fileErrMsg);
        sessionObj.setAttribute("currentDate", CommonFunctions.getCurrentDate());
        fileErrMsg = "";
        fileErrormsg = "";
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="excelexport",path="begin.do?excel=export"),
        @Jpf.Forward(name="redirecttoappraiseesave",path="begin.do?save=savemsg"),
        @Jpf.Forward(name="redirecttoappraiseesubmit",path="begin.do?submit=submitmsg")
    })
    public Forward save(MyAppraisalFormDTO formData) throws NullPointerException{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        int appraiseeId=Integer.parseInt((String)sessionObj.getAttribute("employeeId"));
        String button=request.getParameter("button");
        String zeroSizeFileNames = "";
        try{
            
            List uploadedAttachments=formData.getAttachment();
            for (int i = 0; i < uploadedAttachments.size(); i++){
                FormFile uploadedFile=(FormFile) uploadedAttachments.get(i);
                System.out.println("uploaded file size @ AppraiseeController @ save() "+uploadedFile.getFileSize());
                if(uploadedFile.getFileSize() == 0)
                {
                     zeroSizeFileNames += uploadedFile.getFileName()+",";
                }else{
                     CommonFunctions.fileUpload(uploadedFile,formData.getQpdId(),"TEST_FILE_AA",CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE,sharedControlObj);
                }
            }
        logger.info("formData.getAppraiseeQuarter() = " + formData.getAppraiseeQuarter());
        logger.info("formData.getAppraiseeYear() = " + formData.getAppraiseeYear());

        controlObj.insertOrUpdateAppraiseeData(formData,appraiseeId);
        fileErrormsg = zeroSizeFileNames;
        
    //AppraiseeDetailsDTO appraiseeDetails=(AppraiseeDetailsDTO) sessionObj.getAttribute("appraiseeDetails");
        if(("Save").equals(button)){
            String successMsg = "Comments Saved Successfully";
            request.setAttribute("savedmsg",successMsg);
            fwd=new Forward("redirecttoappraiseesave");
            fwd.setRedirect(true);
            }else if(("Submit for Review").equals(button)){
            if(("").equals(zeroSizeFileNames)){
                controlObj.updateAppraiseeStatus(appraiseeId,formData.getAppraiseeQuarter(),formData.getAppraiseeYear());
            }
            String successMsg = "Comments Submitted Successfully";
            request.setAttribute("savedmsg",successMsg);
//            fwd=new Forward("redirecttohome");
            fwd=new Forward("redirecttoappraiseesubmit");
            fwd.setRedirect(true);
            }else if(("Export to Excel").equals(button)){
                fwd=new Forward("excelexport");
            }
        }
        catch(Exception e){
            System.out.println("Exception @ Appraisee");
            logger.error("Exception @ appraiseecontroller @ save "+e.getMessage());
        }
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraisee",path="myappraisal.jsp"),
        @Jpf.Forward(name="notEligible",path="appraisalnottriggered.jsp")})
    public Forward getPreviousQuarterComments(MyAppraisalFormDTO getPreviousComments) throws NullPointerException{
        AppraiseeDetailsDTO authenticateAppraisee=null;
        Forward fwd=null;

        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();

        String empNum=(String) sessionObj.getAttribute("employeeId");
        String empId=(String) sessionObj.getAttribute("loginId");
        request.setAttribute("empNum", empNum);
        request.setAttribute("appraiseeEmpId", empId);
        request.setAttribute("appraisalYear",getPreviousComments.getFetchApprasalYear());
        request.setAttribute("appraisalQuarter",getPreviousComments.getFetchApprasalQuarter());

        try{
//            CommonFunctions.printRequest(request);
            authenticateAppraisee=controlObj.authenticateAppraisee(empNum,getPreviousComments.getFetchApprasalQuarter(),getPreviousComments.getFetchApprasalYear());

                if(authenticateAppraisee==null){
                    logger.info("appraisee not eligible");
                    fwd=new Forward("notEligible");
                }else{
                logger.info("appraisee eligible");
                request.setAttribute("appraiseeDetails", authenticateAppraisee);
                AppraiseeDetailsDTO appraiseeSBU=controlObj.getAppraiseeSBU(authenticateAppraisee.getDepartmentId());
                request.setAttribute("sbuName", appraiseeSBU);


                MyAppraisalFormDTO[] kraData = controlObj.getKraData(authenticateAppraisee.getBandId(),getPreviousComments.getFetchApprasalYear(),getPreviousComments.getFetchApprasalQuarter(),empId,authenticateAppraisee.getDepartmentId());
                    
                request.setAttribute("kraData",kraData);
                fwd=new Forward("appraisee");
            }
        }
        catch(Exception e){
            logger.error("Exception @ appraiseecontroller @ getPreviousQuarterComments "+e.getMessage());
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

    @Jpf.Action(forwards={
    @Jpf.Forward(name="myappraisal",path="begin.do")})
    public Forward fileRemove(SendMailDTO formData) throws Exception {
        HttpServletRequest requestObj = this.getRequest();
        HttpServletResponse response =  this.getResponse();
        String filePath = CommonConfigurations.fileUploadPath;
        CommonFunctions.fileRemove(formData.getFileId(),formData.getFileName(),filePath,response,sharedControlObj);
//      controlObj.fileDownload(fileName,filePath,response);
        Forward fwd = null;
        fwd=new Forward("myappraisal");
        return fwd;
    }
}
