package com.defiance.ideal.qpd.managers;
import com.defiance.ideal.qpd.appraiser.bo.AppraiserBO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import com.defiance.ideal.qpd.managers.bo.ManagerBO;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import com.defiance.ideal.shared.CommonConfigurations;

import com.defiance.ideal.shared.CommonFunctions;
import com.defiance.ideal.shared.MysqlDatabase;
import com.defiance.ideal.shared.SendMailDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@Jpf.Controller()
public class ManagersController extends PageFlowController{

    private transient final Logger logger = Logger.getLogger(this.getClass().getName());

    @Control
    private ManagerBO controlObj;

     @Control
    private AppraiserBO appraiserControlObj;
    
    @Control
    private MysqlDatabase sharedControlObj;

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyReviewer",path="review.jsp")
    })

    public Forward begin() throws Exception{
        logger.info("reviewer screen");
        HttpSession sessionObj=getSession();
        HttpServletRequest request=this.getRequest();
        Forward fwd=null;
        int appraisalYear;
        int appraisalQuarter;
        
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

            
            
        String reviewerId=(String) sessionObj.getAttribute("employeeId");
        String empId=(String) sessionObj.getAttribute("loginId");
        //String selectedAppraiserId=request.getParameter("myappraiserId");
        logger.info("reviewer Id "+reviewerId);
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
                
        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);
        request.setAttribute("selectedAppraiser",(String)request.getParameter("myappraiserId"));
        
        ManagerDTO[] appraiseesbyReviewer=null;
        ManagerDTO totalAppraiseesCount=null;
        
        appraiseesbyReviewer=controlObj.getAppraiseesByReviewer(getRequest(),reviewerId,appraisalQuarter,appraisalYear);

        totalAppraiseesCount=controlObj.getEmployeesCount(reviewerId,appraisalQuarter,appraisalYear,"reviewerId");
        
        request.setAttribute("appraiseesdetails", appraiseesbyReviewer);
        request.setAttribute("appraiseesCount", totalAppraiseesCount.getAppraiseesCount());
        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        logger.info("pressed export "+button);
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
            ArrayList reviewerInfo=new ArrayList();
            ArrayList list1=new ArrayList();
            reviewerInfo.add(list1);
            ArrayList list2=new ArrayList();
            reviewerInfo.add(list2);
            ArrayList headerData =  new ArrayList();
                         headerData.add(new String("Appraisee ID"));
                         headerData.add(new String("Appraisee Name"));
                         headerData.add(new String("Appraisee Band"));
                         headerData.add(new String("Appraiser ID"));
                         headerData.add(new String("Appraiser Name"));
                         headerData.add(new String("Appraisee Status"));
                         headerData.add(new String("Appraiser Status"));
                         headerData.add(new String("Reviewer Status"));
                         headerData.add(new String("Final Review Status"));
                         headerData.add(new String("Appraiser Rating"));
                         headerData.add(new String("Appraiser Comments"));
                         headerData.add(new String("Appraiser Promotions"));
                         headerData.add(new String("Reviewer Rating"));
                         headerData.add(new String("Reviewer Comments"));
                         headerData.add(new String("Reviewer Promotions"));
            reviewerInfo.add(headerData);
            for (int i = 0; i < appraiseesbyReviewer.length; i++) {
                ArrayList rowDataList =  new ArrayList();
                          String reviewerStatus=null;
                          String appraiserStatus=null;
                          String appraiseeStatus=null;
                          String normalizerStatus=null;
                          if(appraiseesbyReviewer[i].getSubmitStatus()>=8){
                              normalizerStatus="submitted";
                          }else if(appraiseesbyReviewer[i].getSubmitStatus()==7){
                              normalizerStatus="sent back for correction";
                          }
                          else{
                              normalizerStatus="not submitted";
                          }
                          if(appraiseesbyReviewer[i].getSubmitStatus()>=6){
                              reviewerStatus="submitted";
                          }else if(appraiseesbyReviewer[i].getSubmitStatus()==5){
                              reviewerStatus="send back to reviewer";
                          }
                          else{
                              reviewerStatus="not submitted";
                          }
                          if(appraiseesbyReviewer[i].getSubmitStatus()>=4){
                              appraiserStatus="submitted";
                          }else if(appraiseesbyReviewer[i].getSubmitStatus()==3){
                              appraiserStatus="send back to appraiser";
                          }
                          else{
                              appraiserStatus="not submitted";
                          }
                          if(appraiseesbyReviewer[i].getSubmitStatus()>=2){
                              appraiseeStatus="submitted";
                          }else if(appraiseesbyReviewer[i].getSubmitStatus()==1){
                              appraiseeStatus="sent back to appraisee";
                          }
                          else{
                              appraiseeStatus="not submitted";
                          }
                          logger.info("revier rating "+appraiseesbyReviewer[i].getAppraiseeEmpId()+appraiseesbyReviewer[i].getAppraiseeName()+appraiseesbyReviewer[i].getAppraiserEmpId()+appraiseesbyReviewer[i].getAppraiserName()+appraiseesbyReviewer[i].getAppraiserRating()+appraiseesbyReviewer[i].getAppraiserComments());
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiseeEmpId());
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiseeName());
                          rowDataList.add(appraiseesbyReviewer[i].getBandName());
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiserEmpId());
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiserName());
                          rowDataList.add(appraiseeStatus);
                          rowDataList.add(appraiserStatus);
                          rowDataList.add(reviewerStatus);
                          rowDataList.add(normalizerStatus);
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiserRating());
                          rowDataList.add(appraiseesbyReviewer[i].getAppraiserComments());
                          if (appraiseesbyReviewer[i].getAppraiserPromotionRecommeded() != null) {
                            if (appraiseesbyReviewer[i].getAppraiserPromotionRecommeded().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyReviewer[i].getAppraiserPromotionRecommeded().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          rowDataList.add(appraiseesbyReviewer[i].getReviewerRating());
                          rowDataList.add(appraiseesbyReviewer[i].getReviewerComments());
                          if (appraiseesbyReviewer[i].getReviewerPromotionRec() != null) {
                            if (appraiseesbyReviewer[i].getReviewerPromotionRec().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyReviewer[i].getReviewerPromotionRec().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }

                         entireList.add(rowDataList);
            }
            CommonFunctions.exportToExcel(responseObj, reviewerInfo, entireList, empId+".xls");
        }
        fwd=new Forward("appraiseesbyReviewer");
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyNormalizer",path="normalizer.jsp")
    })

    public Forward beginNormalizer() throws Exception{
        logger.info("Final Review screen");
        HttpSession sessionObj=getSession();
        HttpServletRequest request=this.getRequest();
        Forward fwd=null;
        int appraisalYear;
        int appraisalQuarter;

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

        String normalizerId=(String) sessionObj.getAttribute("employeeId");
        String empId=(String) sessionObj.getAttribute("loginId");
        //String selectedAppraiserId=request.getParameter("myappraiserId");
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);

        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);
        
        request.setAttribute("selectedAppraiser",(String)request.getParameter("myappraiserId"));
        request.setAttribute("selectedReviewer",(String)request.getParameter("myreviewerId"));




        ManagerDTO[] appraiseesbyNormalizer=null;
        ManagerDTO totalAppraiseesCount=null;

        appraiseesbyNormalizer=controlObj.getAppraiseesByNormalizer(getRequest(),normalizerId,appraisalQuarter,appraisalYear);

        totalAppraiseesCount=controlObj.getEmployeesCount(normalizerId,appraisalQuarter,appraisalYear,"normalizerId");
        request.setAttribute("appraiseesCount", totalAppraiseesCount.getAppraiseesCount());
        
        request.setAttribute("appraiseesdetails", appraiseesbyNormalizer);
        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        logger.info("pressed export and appraisee details lenght "+button+" and "+appraiseesbyNormalizer.length);
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
            ArrayList reviewerInfo=new ArrayList();
            ArrayList list1=new ArrayList();
            reviewerInfo.add(list1);
            ArrayList list2=new ArrayList();
            reviewerInfo.add(list2);
            ArrayList headerData =  new ArrayList();
                         headerData.add(new String("Appraisee ID"));
                         headerData.add(new String("Appraisee Name"));
                         headerData.add(new String("Appraisee Band"));
                         headerData.add(new String("Appraiser ID"));
                         headerData.add(new String("Appraiser Name"));
                         headerData.add(new String("Appraisee Status"));
                         headerData.add(new String("Appraiser Status"));
                         headerData.add(new String("Reviewer Status"));
                         headerData.add(new String("Final Review Status"));
                         headerData.add(new String("Appraiser Rating"));
                         headerData.add(new String("Appraiser Comments"));
                         headerData.add(new String("Appraiser Promotions"));
                         headerData.add(new String("Reviewer Rating"));
                         headerData.add(new String("Reviewer Comments"));
                         headerData.add(new String("Reviewer Promotions"));
                         headerData.add(new String("Final Review Rating"));
                         headerData.add(new String("Final Review Corrected Rating"));
                         headerData.add(new String("Final Review Comments"));
                         headerData.add(new String("Final Review Promotions"));
            reviewerInfo.add(headerData);
            for (int i = 0; i < appraiseesbyNormalizer.length; i++) {
                ArrayList rowDataList =  new ArrayList();
                          String reviewerStatus=null;
                          String appraiserStatus=null;
                          String appraiseeStatus=null;
                          String normalizerStatus=null;
                          if(appraiseesbyNormalizer[i].getSubmitStatus()>=8){
                              normalizerStatus="submitted";
                          }else if(appraiseesbyNormalizer[i].getSubmitStatus()==7){
                              normalizerStatus="sent back for correction";
                          }
                          else{
                              normalizerStatus="not submitted";
                          }
                          if(appraiseesbyNormalizer[i].getSubmitStatus()>=6){
                              reviewerStatus="submitted";
                          }else if(appraiseesbyNormalizer[i].getSubmitStatus()==5){
                              reviewerStatus="send back to reviewer";
                          }
                          else{
                              reviewerStatus="not submitted";
                          }
                          if(appraiseesbyNormalizer[i].getSubmitStatus()>=4){
                              appraiserStatus="submitted";
                          }else if(appraiseesbyNormalizer[i].getSubmitStatus()==3){
                              appraiserStatus="send back to appraiser";
                          }
                          else{
                              appraiserStatus="not submitted";
                          }
                          if(appraiseesbyNormalizer[i].getSubmitStatus()>=2){
                              appraiseeStatus="submitted";
                          }else if(appraiseesbyNormalizer[i].getSubmitStatus()==1){
                              appraiseeStatus="sent back to appraisee";
                          }
                          else{
                              appraiseeStatus="not submitted";
                          }
                          logger.info("revier rating "+appraiseesbyNormalizer[i].getAppraiseeEmpId()+appraiseesbyNormalizer[i].getAppraiseeName()+appraiseesbyNormalizer[i].getAppraiserEmpId()+appraiseesbyNormalizer[i].getAppraiserName()+appraiseesbyNormalizer[i].getAppraiserRating()+appraiseesbyNormalizer[i].getAppraiserComments());
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiseeEmpId());
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiseeName());
                          rowDataList.add(appraiseesbyNormalizer[i].getBandName());
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiserEmpId());
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiserName());
                          rowDataList.add(appraiseeStatus);
                          rowDataList.add(appraiserStatus);
                          rowDataList.add(reviewerStatus);
                          rowDataList.add(normalizerStatus);
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiserRating());
                          rowDataList.add(appraiseesbyNormalizer[i].getAppraiserComments());
                          if (appraiseesbyNormalizer[i].getAppraiserPromotionRecommeded() != null) {
                            if (appraiseesbyNormalizer[i].getAppraiserPromotionRecommeded().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyNormalizer[i].getAppraiserPromotionRecommeded().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          rowDataList.add(appraiseesbyNormalizer[i].getReviewerRating());
                          rowDataList.add(appraiseesbyNormalizer[i].getReviewerComments());
                          if (appraiseesbyNormalizer[i].getReviewerPromotionRec() != null) {
                            if (appraiseesbyNormalizer[i].getReviewerPromotionRec().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyNormalizer[i].getReviewerPromotionRec().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          if(appraiseesbyNormalizer[i].getNormalisedReviewerRating()!=null)
                          {
                               rowDataList.add(appraiseesbyNormalizer[i].getNormalisedReviewerRating());
                          }
                          else{
                              rowDataList.add("N/A");
                          }
                          if(appraiseesbyNormalizer[i].getCorrectedNormalizerRating()!=null)
                          {
                               rowDataList.add(appraiseesbyNormalizer[i].getCorrectedNormalizerRating());
//                              if (Integer.parseInt(appraiseesbyNormalizer[i].getCorrectedNormalizerRating()) < 1) {
//                                  rowDataList.add(appraiseesbyNormalizer[i].getNormalisedReviewerRating());
//                              } 
                          }
                          else {
//                                  rowDataList.add(appraiseesbyNormalizer[i].getCorrectedNormalizerRating());
                                  rowDataList.add("N/A");
                              }
                          rowDataList.add(appraiseesbyNormalizer[i].getNormalizerComments());
                        if (appraiseesbyNormalizer[i].getNormalizerPromotionRec() != null) {
                            if (appraiseesbyNormalizer[i].getNormalizerPromotionRec().equals("1")) {
                            rowDataList.add("Yes");
                             } else if (appraiseesbyNormalizer[i].getNormalizerPromotionRec().equals("0")) {
                                rowDataList.add("No");
                                }
                            } else {
                            rowDataList.add("N/A");
                            }
                         entireList.add(rowDataList);
            }
            CommonFunctions.exportToExcel(responseObj, reviewerInfo, entireList, empId+".xls");
            fwd=null;
        }else{
            fwd=new Forward("appraiseesbyNormalizer");
        }
        
        return fwd;
    }
    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyBuh",path="buh.jsp")
    })

    public Forward beginBuh() throws Exception{
        logger.info("BUH screen");
        HttpSession sessionObj=getSession();
        HttpServletRequest request=this.getRequest();
        Forward fwd=null;
        int appraisalYear;
        int appraisalQuarter;

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

        String buhId=(String) sessionObj.getAttribute("employeeId");
        String empId=(String) sessionObj.getAttribute("loginId");
        //String selectedAppraiserId=request.getParameter("myappraiserId");
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);

        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);
        
        request.setAttribute("selectedAppraiser",(String)request.getParameter("myappraiserId"));
        request.setAttribute("selectedReviewer",(String)request.getParameter("myreviewerId"));
        request.setAttribute("selectedNormalizer",(String)request.getParameter("mynormalizerId"));




        ManagerDTO[] appraiseesbyBUH=null;
        ManagerDTO totalAppraiseesCount=null;

        appraiseesbyBUH=controlObj.getAppraiseesByBuh(getRequest(),buhId,appraisalQuarter,appraisalYear);

        totalAppraiseesCount=controlObj.getEmployeesCount(buhId,appraisalQuarter,appraisalYear,"buhId");
        request.setAttribute("appraiseesCount", totalAppraiseesCount.getAppraiseesCount());
        
        request.setAttribute("appraiseesdetails", appraiseesbyBUH);
        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        logger.info("pressed export and appraisee details lenght "+button+" and "+appraiseesbyBUH.length);
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
            ArrayList reviewerInfo=new ArrayList();
            ArrayList list1=new ArrayList();
            reviewerInfo.add(list1);
            ArrayList list2=new ArrayList();
            reviewerInfo.add(list2);
            ArrayList headerData =  new ArrayList();
                         headerData.add(new String("Appraisee ID"));
                         headerData.add(new String("Appraisee Name"));
                         headerData.add(new String("Appraisee Band"));
//                         headerData.add(new String("Appraiser ID"));
//                         headerData.add(new String("Appraiser Name"));
//                         headerData.add(new String("Appraisee Status"));
//                         headerData.add(new String("Appraiser Status"));
//                         headerData.add(new String("Reviewer Status"));
//                         headerData.add(new String("Final Review Status"));
                         headerData.add(new String("Appraiser Rating"));
                         headerData.add(new String("Appraiser Comments"));
                         headerData.add(new String("Appraiser Promotions"));
                         headerData.add(new String("Reviewer Rating"));
                         headerData.add(new String("Reviewer Comments"));
                         headerData.add(new String("Reviewer Promotions"));
                         headerData.add(new String("Final Review Rating"));
                         headerData.add(new String("Final Review Corrected Rating"));
                         headerData.add(new String("Final Review Comments"));
                         headerData.add(new String("Final Review Promotions"));
            reviewerInfo.add(headerData);
            for (int i = 0; i < appraiseesbyBUH.length; i++) {
                ArrayList rowDataList =  new ArrayList();
                          String reviewerStatus=null;
                          String appraiserStatus=null;
                          String appraiseeStatus=null;
                          String normalizerStatus=null;
//                          if(appraiseesbyBUH[i].getSubmitStatus()>=8){
//                              normalizerStatus="submitted";
//                          }else if(appraiseesbyBUH[i].getSubmitStatus()==7){
//                              normalizerStatus="sent back for correction";
//                          }
//                          else{
//                              normalizerStatus="not submitted";
//                          }
//                          if(appraiseesbyBUH[i].getSubmitStatus()>=6){
//                              reviewerStatus="submitted";
//                          }else if(appraiseesbyBUH[i].getSubmitStatus()==5){
//                              reviewerStatus="send back to reviewer";
//                          }
//                          else{
//                              reviewerStatus="not submitted";
//                          }
//                          if(appraiseesbyBUH[i].getSubmitStatus()>=4){
//                              appraiserStatus="submitted";
//                          }else if(appraiseesbyBUH[i].getSubmitStatus()==3){
//                              appraiserStatus="send back to appraiser";
//                          }
//                          else{
//                              appraiserStatus="not submitted";
//                          }
//                          if(appraiseesbyBUH[i].getSubmitStatus()>=2){
//                              appraiseeStatus="submitted";
//                          }else if(appraiseesbyBUH[i].getSubmitStatus()==1){
//                              appraiseeStatus="sent back to appraisee";
//                          }
//                          else{
//                              appraiseeStatus="not submitted";
//                          }
                          logger.info("revier rating "+appraiseesbyBUH[i].getAppraiseeEmpId()+appraiseesbyBUH[i].getAppraiseeName()+appraiseesbyBUH[i].getAppraiserEmpId()+appraiseesbyBUH[i].getAppraiserName()+appraiseesbyBUH[i].getAppraiserRating()+appraiseesbyBUH[i].getAppraiserComments());
                          rowDataList.add(appraiseesbyBUH[i].getAppraiseeEmpId());
                          rowDataList.add(appraiseesbyBUH[i].getAppraiseeName());
                          rowDataList.add(appraiseesbyBUH[i].getBandName());
//                          rowDataList.add(appraiseesbyBUH[i].getAppraiserEmpId());
//                          rowDataList.add(appraiseesbyBUH[i].getAppraiserName());
//                          rowDataList.add(appraiseeStatus);
//                          rowDataList.add(appraiserStatus);
//                          rowDataList.add(reviewerStatus);
//                          rowDataList.add(normalizerStatus);
                          rowDataList.add(appraiseesbyBUH[i].getAppraiserRating());
                          rowDataList.add(appraiseesbyBUH[i].getAppraiserComments());
                          if (appraiseesbyBUH[i].getAppraiserPromotionRecommeded() != null) {
                            if (appraiseesbyBUH[i].getAppraiserPromotionRecommeded().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyBUH[i].getAppraiserPromotionRecommeded().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          rowDataList.add(appraiseesbyBUH[i].getReviewerRating());
                          rowDataList.add(appraiseesbyBUH[i].getReviewerComments());
                          if (appraiseesbyBUH[i].getReviewerPromotionRec() != null) {
                            if (appraiseesbyBUH[i].getReviewerPromotionRec().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyBUH[i].getReviewerPromotionRec().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          if(appraiseesbyBUH[i].getNormalisedReviewerRating()!=null)
                          {
                               rowDataList.add(appraiseesbyBUH[i].getNormalisedReviewerRating());
                          }
                          else{
                              rowDataList.add("N/A");
                          }
                          if(appraiseesbyBUH[i].getCorrectedNormalizerRating()!=null)
                          {
                               rowDataList.add(appraiseesbyBUH[i].getCorrectedNormalizerRating());
//                              if (Integer.parseInt(appraiseesbyNormalizer[i].getCorrectedNormalizerRating()) < 1) {
//                                  rowDataList.add(appraiseesbyNormalizer[i].getNormalisedReviewerRating());
//                              } 
                          }
                          else {
//                                  rowDataList.add(appraiseesbyNormalizer[i].getCorrectedNormalizerRating());
                                  rowDataList.add("N/A");
                              }
                          rowDataList.add(appraiseesbyBUH[i].getNormalizerComments());
                        if (appraiseesbyBUH[i].getNormalizerPromotionRec() != null) {
                            if (appraiseesbyBUH[i].getNormalizerPromotionRec().equals("1")) {
                            rowDataList.add("Yes");
                             } else if (appraiseesbyBUH[i].getNormalizerPromotionRec().equals("0")) {
                                rowDataList.add("No");
                                }
                            } else {
                            rowDataList.add("N/A");
                            }
                         entireList.add(rowDataList);
            }
            CommonFunctions.exportToExcel(responseObj, reviewerInfo, entireList, empId+".xls");
            fwd=null;
        }else{
            fwd=new Forward("appraiseesbyBuh");
        }
        
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyReviewer",path="begin.do"),
        @Jpf.Forward(name="exportExcel",path="begin.do?excel=export")
    })

    public Forward reviewerAction(ManagerDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        String button=(String)request.getParameter("button");
        System.out.println("button = sssssssssssssss" + button);
        logger.info("button pressed "+button);
        int appraisalYear = 0;
        int appraisalQuarter = 0;
        if(request.getParameter("appraisalYear") == null || "".equals(request.getParameter("appraisalYear"))){
        appraisalYear=CommonFunctions.getAppraisalYear(this.getRequest().getSession());
        appraisalQuarter=CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());
        }else{
            appraisalYear = Integer.parseInt(request.getParameter("appraisalYear"));
        }
        String reviewerId=(String) sessionObj.getAttribute("employeeId");
        logger.info("reviewer Id "+reviewerId);
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);


        if("Save".equals(button)){
            request.setAttribute("savedMessage","Data Saved Successfully");
        }
          
        if(formData.getSubmitToNormalizer()!=null){
            request.setAttribute("submittedMessage","Data Submitted Successfully");
        }
        request.setAttribute("selectedAppraiser",formData.getMyappraiserId());
        // Problem with the multiple radio button so that create an array and assign the value to that array and set that array value to formbaen value
//        if (formData.reviewerRatingForm != null) {
//            String[] reviewerPromotionRecom = new String[formData.reviewerRatingForm.length];
//            for (int i = 0; i < formData.reviewerRatingForm.length; i++) {
//                reviewerPromotionRecom[i] = request.getParameter("reviewerPromotionRecommended" + i);
//            }
//            formData.setReviewerPromotionRecommended(reviewerPromotionRecom);
//        }
        controlObj.updateReviewerRating(reviewerId,appraisalQuarter,appraisalYear,formData,button);
        if(("Export to Excel").equals(button)){
           fwd=new Forward("exportExcel");
        } else {
        fwd=new Forward("appraiseesbyReviewer");
//        fwd.setRedirect(true);
        }
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyNormalizer",path="beginNormalizer.do"),
        @Jpf.Forward(name="exportExcel",path="beginNormalizer.do?excel=export")
    })

    public Forward normalizerAction(ManagerDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        String button=(String)request.getParameter("button");

        logger.info("button pressed "+button);
        int appraisalYear=CommonFunctions.getAppraisalYear(this.getRequest().getSession());
        int appraisalQuarter=CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());
        String normalizerId=(String) sessionObj.getAttribute("employeeId");
        
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);

        System.out.println("formData.getMyappraiserId()"+formData.getMyappraiserId());
        System.out.println("formData.getMyappraiserId()"+formData.getMyreviewerId());

        if("Save".equals(button)){
            request.setAttribute("savedMessage","Data Saved Successfully");
        }
        if(formData.getButton()!=null && formData.getButton().equals("Submit To HR")){
            request.setAttribute("submittedMessage","Data Submitted Successfully");
        }
        request.setAttribute("selectedAppraiser",formData.getMyappraiserId());
        request.setAttribute("selectedReviewer",formData.getMyreviewerId());

       // Problem with the multiple radio button so that create an array and assign the value to that array and set that array value to formbaen value
//        if(formData.reviewerRatingForm!=null){
//        String[] normalizerPromotionRecom = new String[formData.reviewerRatingForm.length];
//        for(int i=0;i<formData.reviewerRatingForm.length;i++){
//            normalizerPromotionRecom[i]=request.getParameter("normalizerPromotionRecommended"+i);
//        }
//        formData.setNormalizerPromotionRecommended(normalizerPromotionRecom);
//        }
        controlObj.updateNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData,button);
        if(("Export to Excel").equals(button)){
           fwd=new Forward("exportExcel");
        } else {
           fwd=new Forward("appraiseesbyNormalizer");
//           fwd.setRedirect(true);
        }
        return fwd;
    }
    
    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyBuh",path="beginBuh.do"),
        @Jpf.Forward(name="exportExcel",path="beginBuh.do?excel=export")
    })

    public Forward buhAction(ManagerDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        String button=(String)request.getParameter("button");

        logger.info("button pressed "+button);
        int appraisalYear=CommonFunctions.getAppraisalYear(this.getRequest().getSession());
        int appraisalQuarter=CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());
        String normalizerId=(String) sessionObj.getAttribute("employeeId");
        
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
        request.setAttribute("appraisalYear",appraisalYear);
        request.setAttribute("appraisalQuarter",appraisalQuarter);
        request.setAttribute("yearData",myArrrayList);

        System.out.println("formData.getMyappraiserId()"+formData.getMyappraiserId());
        System.out.println("formData.getMyappraiserId()"+formData.getMyreviewerId());
        System.out.println("formData.getMyNormalizerId"+formData.getMynormalizerId());

        if("Save".equals(button)){
            request.setAttribute("savedMessage","Data Saved Successfully");
        }
        if(formData.getButton()!=null && formData.getButton().equals("Submit To HR")){
            request.setAttribute("submittedMessage","Data Submitted Successfully");
        }
        request.setAttribute("selectedAppraiser",formData.getMyappraiserId());
        request.setAttribute("selectedReviewer",formData.getMyreviewerId());
        request.setAttribute("selectedNormalizer",formData.getMynormalizerId());

       // Problem with the multiple radio button so that create an array and assign the value to that array and set that array value to formbaen value
//        if(formData.reviewerRatingForm!=null){
//        String[] normalizerPromotionRecom = new String[formData.reviewerRatingForm.length];
//        for(int i=0;i<formData.reviewerRatingForm.length;i++){
//            normalizerPromotionRecom[i]=request.getParameter("normalizerPromotionRecommended"+i);
//        }
//        formData.setNormalizerPromotionRecommended(normalizerPromotionRecom);
//        }
//        controlObj.updateNormalizerRating(normalizerId,appraisalQuarter,appraisalYear,formData,button);
        if(("Export to Excel").equals(button)){
           fwd=new Forward("exportExcel");
        } else {
           fwd=new Forward("appraiseesbyBuh");
//           fwd.setRedirect(true);
        }
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="hrpage",path="hr.jsp")
    })

    public Forward beginHr() throws Exception{
        logger.info("hr screen");
        HttpSession sessionObj=getSession();
        HttpServletRequest request=this.getRequest();
        Forward fwd=null;


        int appraisalYear;
        int appraisalQuarter;

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
        
        String hrId=(String) sessionObj.getAttribute("employeeId");
        //String selectedAppraiserId=request.getParameter("myappraiserId");
        logger.info("reviewer Id "+hrId);
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
        sessionObj.setAttribute("appraisalYearHr",appraisalYear);
        sessionObj.setAttribute("appraisalQuarterHr",appraisalQuarter);
        sessionObj.setAttribute("yearDataHr",myArrrayList);
        ManagerDTO[] appraiseesbyHr=null;
        appraiseesbyHr=controlObj.getAppraiseesByHr(getRequest(),hrId,appraisalQuarter,appraisalYear);
        sessionObj.setAttribute("appraiseesdetailsHr", appraiseesbyHr);
        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        String empId=(String) sessionObj.getAttribute("loginId");
        logger.info("pressed export "+button);
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
            ArrayList reviewerInfo=new ArrayList();
            ArrayList list1=new ArrayList();
            reviewerInfo.add(list1);
            ArrayList list2=new ArrayList();
            reviewerInfo.add(list2);
            ArrayList headerData =  new ArrayList();
                         headerData.add(new String("Appraisee ID"));
                         headerData.add(new String("Appraisee Name"));
                         headerData.add(new String("Appraisee Band"));
                         headerData.add(new String("Appraiser ID"));
                         headerData.add(new String("Appraiser Name"));
                         headerData.add(new String("Appraisee Status"));
                         headerData.add(new String("Appraiser Status"));
                         headerData.add(new String("Reviewer Status"));
                         headerData.add(new String("Final Review Status"));
                         headerData.add(new String("Appraiser Rating"));
                         headerData.add(new String("Appraiser Comments"));
                         headerData.add(new String("Appraiser Promotions"));
                         headerData.add(new String("Reviewer Rating"));
                         headerData.add(new String("Reviewer Comments"));
                         headerData.add(new String("Reviewer Promotions"));
                         headerData.add(new String("Final Review Rating"));
                         headerData.add(new String("Final Review Rating-Corrected"));
                         headerData.add(new String("Final Review Comments"));
                         headerData.add(new String("Final Review Promotions"));
            reviewerInfo.add(headerData);
            for (int i = 0; i < appraiseesbyHr.length; i++) {
                ArrayList rowDataList =  new ArrayList();
                          String reviewerStatus=null;
                          String appraiserStatus=null;
                          String appraiseeStatus=null;
                          String normalizerStatus=null;
                          if(appraiseesbyHr[i].getSubmitStatus()>=8){
                              normalizerStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==7){
                              normalizerStatus="send back to reviewer";
                          }
                          else{
                              normalizerStatus="not submitted";
                          }
                          if(appraiseesbyHr[i].getSubmitStatus()>=6){
                              reviewerStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==5){
                              reviewerStatus="send back to reviewer";
                          }
                          else{
                              reviewerStatus="not submitted";
                          }
                          if(appraiseesbyHr[i].getSubmitStatus()>=4){
                              appraiserStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==3){
                              appraiserStatus="send back to appraiser";
                          }
                          else{
                              appraiserStatus="not submitted";
                          }
                          if(appraiseesbyHr[i].getSubmitStatus()>=2){
                              appraiseeStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==1){
                              appraiseeStatus="sent back to appraisee";
                          }
                          else{
                              appraiseeStatus="not submitted";
                          }
                          logger.info("revier rating "+appraiseesbyHr[i].getAppraiseeEmpId()+appraiseesbyHr[i].getAppraiseeName()+appraiseesbyHr[i].getAppraiserEmpId()+appraiseesbyHr[i].getAppraiserName()+appraiseesbyHr[i].getAppraiserRating()+appraiseesbyHr[i].getAppraiserComments());
                          rowDataList.add(appraiseesbyHr[i].getAppraiseeEmpId());
                          rowDataList.add(appraiseesbyHr[i].getAppraiseeName());
                          rowDataList.add(appraiseesbyHr[i].getBandName());
                          rowDataList.add(appraiseesbyHr[i].getAppraiserEmpId());
                          rowDataList.add(appraiseesbyHr[i].getAppraiserName());
                          rowDataList.add(appraiseeStatus);
                          rowDataList.add(appraiserStatus);
                          rowDataList.add(reviewerStatus);
                          rowDataList.add(normalizerStatus);
                          rowDataList.add(appraiseesbyHr[i].getAppraiserRating());
                          rowDataList.add(appraiseesbyHr[i].getAppraiserComments());
                          if (appraiseesbyHr[i].getAppraiserPromotionRecommeded() != null) {
                            if (appraiseesbyHr[i].getAppraiserPromotionRecommeded().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyHr[i].getAppraiserPromotionRecommeded().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          rowDataList.add(appraiseesbyHr[i].getReviewerRating());
                          rowDataList.add(appraiseesbyHr[i].getReviewerComments());
                           if (appraiseesbyHr[i].getReviewerPromotionRec() != null) {
                            if (appraiseesbyHr[i].getReviewerPromotionRec().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyHr[i].getReviewerPromotionRec().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                          rowDataList.add(appraiseesbyHr[i].getNormalisedReviewerRating());
                          rowDataList.add(appraiseesbyHr[i].getCorrectedNormalizerRating());
                          rowDataList.add(appraiseesbyHr[i].getNormalizerComments());
                           if (appraiseesbyHr[i].getNormalizerComments() != null) {
                            if (appraiseesbyHr[i].getNormalizerPromotionRec().equals("1")) {
                             rowDataList.add("Yes");
                            } else if (appraiseesbyHr[i].getNormalizerPromotionRec().equals("0")) {
                              rowDataList.add("No");
                             }
                         } else {
                            rowDataList.add("N/A");
                             }
                         entireList.add(rowDataList);
            }
            CommonFunctions.exportToExcel(responseObj, reviewerInfo, entireList, empId+".xls");
        }
        fwd=new Forward("hrpage");
        return fwd;
    }




    @Jpf.Action(forwards={
        @Jpf.Forward(name="financepage",path="finance.jsp")
    })

    public Forward beginFinance() throws Exception{
        logger.info("Finance Screen");
        HttpSession sessionObj=getSession();
        HttpServletRequest request=this.getRequest();
        Forward fwd=null;
        int appraisalYear;
        int appraisalQuarter;
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
        String financeId=(String) sessionObj.getAttribute("employeeId");
        //String selectedAppraiserId=request.getParameter("myappraiserId");
        int previousYear = CommonFunctions.getAppraisalYear()-1;
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(CommonFunctions.getAppraisalYear());
                myArrrayList.add(previousYear);
        sessionObj.setAttribute("appraisalYearFinance",appraisalYear);
        sessionObj.setAttribute("appraisalQuarterFinance",appraisalQuarter);
        sessionObj.setAttribute("yearDataFinance",myArrrayList);
        ManagerDTO[] appraiseesbyHr=null;
        appraiseesbyHr=controlObj.getAppraiseesByFinance(getRequest(),financeId,appraisalQuarter,appraisalYear);
        sessionObj.setAttribute("appraiseesdetailsFinance", appraiseesbyHr);
        HttpServletResponse responseObj=getResponse();
        String button=(String)request.getParameter("excel");
        String empId=(String) sessionObj.getAttribute("loginId");
        logger.info("pressed export "+button);
        if(("export").equals(button)){
            ArrayList entireList = new ArrayList();
            ArrayList reviewerInfo=new ArrayList();
//            ArrayList list1=new ArrayList();
//            reviewerInfo.add(list1);
//            ArrayList list2=new ArrayList();
//            reviewerInfo.add(list2);
            ArrayList headerData =  new ArrayList();
                         headerData.add(new String("Appraisee ID"));
                         headerData.add(new String("Appraisee Name"));
//                         headerData.add(new String("Appraiser ID"));
//                         headerData.add(new String("Appraiser Name"));
//                         headerData.add(new String("Appraisee Status"));
//                         headerData.add(new String("Appraiser Status"));
//                         headerData.add(new String("Reviewer Status"));
                         headerData.add(new String("Final Reviewer Rating"));
            reviewerInfo.add(headerData);
            for (int i = 0; i < appraiseesbyHr.length; i++) {
                ArrayList rowDataList =  new ArrayList();
                          String reviewerStatus=null;
                          String appraiserStatus=null;
                          String appraiseeStatus=null;
                          if(appraiseesbyHr[i].getSubmitStatus()>=6){
                              reviewerStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==5){
                              reviewerStatus="send back to reviewer";
                          }
                          else{
                              reviewerStatus="not submitted";
                          }
                          if(appraiseesbyHr[i].getSubmitStatus()>=4){
                              appraiserStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==3){
                              appraiserStatus="send back to appraiser";
                          }
                          else{
                              appraiserStatus="not submitted";
                          }
                          if(appraiseesbyHr[i].getSubmitStatus()>=2){
                              appraiseeStatus="submitted";
                          }else if(appraiseesbyHr[i].getSubmitStatus()==1){
                              appraiseeStatus="sent back to appraisee";
                          }
                          else{
                              appraiseeStatus="not submitted";
                          }
                          logger.info("revier rating "+appraiseesbyHr[i].getAppraiseeEmpId()+appraiseesbyHr[i].getAppraiseeName()+appraiseesbyHr[i].getAppraiserEmpId()+appraiseesbyHr[i].getAppraiserName()+appraiseesbyHr[i].getAppraiserRating()+appraiseesbyHr[i].getAppraiserComments());
                          rowDataList.add(appraiseesbyHr[i].getAppraiseeEmpId());
                          rowDataList.add(appraiseesbyHr[i].getAppraiseeName());
//                          rowDataList.add(appraiseesbyHr[i].getAppraiserEmpId());
//                          rowDataList.add(appraiseesbyHr[i].getAppraiserName());
//                          rowDataList.add(appraiseeStatus);
//                          rowDataList.add(appraiserStatus);
//                          rowDataList.add(reviewerStatus);
//                          if(appraiseesbyHr[i].getSendbackbyhr()==0)rowDataList.add(appraiseesbyHr[i].getReviewerRating());
                          if(appraiseesbyHr[i].getSendbackbyhr()==0 && appraiseesbyHr[i].getCorrectedNormalizerRating()==null){
                              rowDataList.add(appraiseesbyHr[i].getNormalisedReviewerRating());
                          }else{
                              rowDataList.add(appraiseesbyHr[i].getCorrectedNormalizerRating());
                          }

                         entireList.add(rowDataList);
            }
            CommonFunctions.exportToExcel(responseObj, reviewerInfo, entireList, empId+".xls");
        }
        fwd=new Forward("financepage");
        return fwd;
    }





     @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyHr",path="beginHr.do"),
        @Jpf.Forward(name="exportExcel",path="beginHr.do?excel=export")
    })

    public Forward hrAction(ManagerDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
        HttpSession sessionObj=getSession();
        ManagerDTO[] appraiseesbyHr=null;
        String button=(String)request.getParameter("button");
        logger.info("button pressed "+button);
        
        int appraisalYear;
        int appraisalQuarter;

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
        
        String hrId=(String) sessionObj.getAttribute("employeeId");
        
//        int previousYear = appraisalYear-1;
//                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
//                myArrrayList.add(CommonFunctions.getAppraisalYear());
//                myArrrayList.add(previousYear);
//        request.setAttribute("appraisalYearHr",appraisalYear);
//        request.setAttribute("appraisalQuarterHr",appraisalQuarter);
//        request.setAttribute("yearDataHr",myArrrayList);
        
//        appraiseesbyHr = (ManagerDTO[]) sessionObj.getAttribute("appraiseesdetailsHr");
        if(("Export to Excel").equals(button)){
           fwd=new Forward("exportExcel");
        } else {
        controlObj.updateFinalStatus(hrId,appraisalQuarter,appraisalYear,formData,button);
        fwd=new Forward("appraiseesbyHr");

//        fwd.setRedirect(true);
        }
        return fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesbyFinance",path="beginFinance.do?excel=export")
   })

    public Forward financeAction(ManagerDTO formData) throws Exception{
        Forward fwd=null;
        HttpServletRequest request=this.getRequest();
//        HttpSession sessionObj=getSession();
//        ManagerDTO[] appraiseesbyHr=null;
        String button=(String)request.getParameter("button");
//        int appraisalYear;
//        int appraisalQuarter;
//            if(request.getParameter("appraisalYear")==null){
//                    appraisalYear = CommonFunctions.getAppraisalYear();
//
//            }else{
//                    appraisalYear = Integer.parseInt((String)request.getParameter("appraisalYear"));
//            }
//            if(request.getParameter("appraisalQuarter")==null){
//                    appraisalQuarter = CommonFunctions.getAppraisalQuarter();
//            }else{
//                    appraisalQuarter = Integer.parseInt((String)request.getParameter("appraisalQuarter"));
//            }
//        String hrId=(String) sessionObj.getAttribute("employeeId");
//        controlObj.updateFinalStatus(hrId,appraisalQuarter,appraisalYear,formData,button);
        if(("Export to Excel").equals(button)){
           fwd=new Forward("appraiseesbyFinance");
        } else {
        fwd=new Forward("appraiseesby");
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
    
    
    @Jpf.Action(forwards={
        @Jpf.Forward(name="appraiseesInfo",path="appraiseeInfoReviewScreen.jsp")
   })
    public Forward fetchAppraiseeKraData(ManagerDTO formData) throws Exception{
        HttpServletRequest request=this.getRequest();
        int bandId=formData.getBandIdSelected();
        int appraisalQuarter=formData.getAppraisalQuarterSelected();
        int appraisalYear=formData.getAppraisalYearSelected();
        int appraiseeId=formData.getAppraiseeIdSelected();
        int qpdId=formData.getQpdIdSelected();
        System.out.println("qpdId!!!!!!!!!!!!!!!!"+qpdId);
        int departmentId=formData.getDepartmentIdSelected();
        request.setAttribute("appraiseeId", appraiseeId);
         request.setAttribute("qpdId", qpdId);
        request.setAttribute("bandId", bandId);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("appraisalQuarter", appraisalQuarter);
        request.setAttribute("appraisalYear", appraisalYear);
        if(formData.getReviewerMenuCheck()!=null && formData.getReviewerMenuCheck().equals("reviewer")){
            request.setAttribute("menuCheck", "reviewer");
        }else if(formData.getReviewerMenuCheck()!=null && formData.getReviewerMenuCheck().equals("finalReviewer")){
            request.setAttribute("menuCheck", "finalReviewer");
        }else{
            request.setAttribute("menuCheck", "finance");
        }
        String backPath=formData.getAppraiseeInfoReviewer();
        logger.info("kra data from "+backPath);
        String backMethod=null;

        int currentYear=CommonFunctions.getAppraisalYear();
        int previousYear = (currentYear-1);
                ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
                myArrrayList.add(currentYear);
                myArrrayList.add(previousYear);
        request.setAttribute("yearData",myArrrayList);
                logger.info("attributes from reviewer screen "+formData.getAppraiserNameSelected()+formData.getAppraisalQuarterSelected()+formData.getAppraisalYearSelected()+appraiseeId);
        ManagerDTO[] kraData=null;

        kraData=controlObj.getKraData(bandId,appraisalQuarter,appraisalYear,appraiseeId,departmentId);

        // To display all the info regarding appraisee
        AppraiseeListDTO appraisalSubmitStatus=controlObj.getSubmitStatus(appraiseeId,appraisalQuarter,appraisalYear);

        if(appraisalSubmitStatus != null){
                    request.setAttribute("submitStatus",appraisalSubmitStatus.getAppraisalSubmitStatus());
                    request.setAttribute("appraiserComments", appraisalSubmitStatus.getAppraiserComments());
                    request.setAttribute("appraiserObj", appraisalSubmitStatus);
        }
        AppraiserFormDTO[] topAchievements = controlObj.getAchievementsData(appraisalYear,appraiseeId);
        AppraiserFormDTO[] developmentNeeds = controlObj.getDevelopmentData(appraisalYear,appraiseeId);
        AppraiserFormDTO[] goalSheet = controlObj.getGoalData(appraisalYear,appraiseeId);

        request.setAttribute("topAchievements",topAchievements);
        request.setAttribute("developmentNeeds",developmentNeeds);
        request.setAttribute("goalSheet",goalSheet);

        request.setAttribute("kraData", kraData);
        request.setAttribute("formData", formData);
        if(kraData != null){
           // SendMailDTO[] fileData = sharedControlObj.getFilesList(appraiseeId,CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE);
            SendMailDTO[] fileData = sharedControlObj.getFilesList(qpdId,CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE);
            request.setAttribute("fileDetails",fileData);
         }


        if(("reviewer").equals(backPath)){
            backMethod="begin.do";
            
        }else if(("normalizer").equals(backPath)){
            backMethod="beginNormalizer.do";

        }else if(("hr").equals(backPath)){
            backMethod="beginHr.do";
        }
        else{
            backMethod="beginFinance.do";
        }
        request.setAttribute("back", backMethod);
            //SendMailDTO[] fileData = sharedControlObj.getFilesList(appraiseeId,CommonConfigurations.APPRAISEE_FILE_UPLOAD_CODE);
            //request.setAttribute("fileDetails",fileData);
        Forward fwd=null;
        fwd=new Forward("appraiseesInfo");
        return  fwd;
    }

    @Jpf.Action(forwards={
        @Jpf.Forward(name="qpdRating",path="lastFourQpdRatings.jsp")
   })
    public Forward getQpdRating(ManagerDTO formData) throws Exception {
        HttpServletRequest requestObj = this.getRequest();
        String appraiseeId = requestObj.getParameter("appraiseeId");
        AppraiserRatingFormDTO[] qpdRatingDetails = appraiserControlObj.getLastFourQPDRating(Integer.parseInt(appraiseeId));
        requestObj.setAttribute("qpdRatingDetails", qpdRatingDetails);
        Forward fwd = null;
        fwd = new Forward("qpdRating");
        return fwd;
    }


}

