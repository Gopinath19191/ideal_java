/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.dto;
import java.util.Date;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
/**
 *
 * @author Administrator
 */
@Jpf.FormBean()
public class AppraiseeListDTO {

    private String appraiseeName;
    private String appraiseeNumber;
    private String bandName;
    private int appraiseeId;
    private int submitStatus;
    private int appraisalSubmitStatus;
    private int bandId;
    private int qpdId;

    private int appraisalYearForm;
    private int appraisalQuarterForm;
    private int bandIdForm;
    private int appraiseeIdForm;
    private int appraiseeSubmitStatusForm;

    private String appraiseeNameForm;
    private String appraiseeJoiningDateForm;
    private String appraiseeStatusForm;
    private String appraiseeNumberForm;

    public String appraiserComments;
    public int departmentId;
    public String areasOfImprovement;
    public String technologyTraining;
    public String softskillTraining;
   

    // For Promotion recommended by the appraiser need the below variable
    public String appraiserPromotionRecommeded;

    public String getAppraiserPromotionRecommeded() {
        return appraiserPromotionRecommeded;
    }

    public void setAppraiserPromotionRecommeded(String appraiserPromotionRecommeded) {
        this.appraiserPromotionRecommeded = appraiserPromotionRecommeded;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getAppraiserComments() {
        return appraiserComments;
    }

    public void setAppraiserComments(String appraiserComments) {
        this.appraiserComments = appraiserComments;
    }

    public int getAppraisalSubmitStatus() {
        return appraisalSubmitStatus;
    }

    public void setAppraisalSubmitStatus(int appraisalSubmitStatus) {
        this.appraisalSubmitStatus = appraisalSubmitStatus;
    }


    public String getAppraiseeNumberForm() {
        return appraiseeNumberForm;
    }

    public void setAppraiseeNumberForm(String appraiseeNumberForm) {
        this.appraiseeNumberForm = appraiseeNumberForm;
    }

    public int fetchApprasalQuarter;
    public int fetchApprasalYear;

    public String appraiseeNameMail;
    public String appraiseeEmail;
    public String appraiserName;
    public String appraiserEmail;
    public String reviewerName;
    public String reviewerEmail;
    public String employeeStatus;
    public String joiningDate;
    public int appraiseeEmpId;
    public int appraiserRating;

    public String appraiseeJoiningDate;
    public String appraiseeStatus;
    public String discussionDate;

    public String getAppraiseeJoiningDateForm() {
        return appraiseeJoiningDateForm;
    }

    public void setAppraiseeJoiningDateForm(String appraiseeJoiningDateForm) {
        this.appraiseeJoiningDateForm = appraiseeJoiningDateForm;
    }

    public String getAppraiseeNameForm() {
        return appraiseeNameForm;
    }

    public void setAppraiseeNameForm(String appraiseeNameForm) {
        this.appraiseeNameForm = appraiseeNameForm;
    }

    public String getAppraiseeStatusForm() {
        return appraiseeStatusForm;
    }

    public void setAppraiseeStatusForm(String appraiseeStatusForm) {
        this.appraiseeStatusForm = appraiseeStatusForm;
    }

    public String getAppraiseeJoiningDate() {
        return appraiseeJoiningDate;
    }

    public void setAppraiseeJoiningDate(String appraiseeJoiningDate) {
        this.appraiseeJoiningDate = appraiseeJoiningDate;
    }

    public String getAppraiseeStatus() {
        return appraiseeStatus;
    }

    public void setAppraiseeStatus(String appraiseeStatus) {
        this.appraiseeStatus = appraiseeStatus;
    }

    

    public int getAppraiserRating() {
        return appraiserRating;
    }

    public void setAppraiserRating(int appraiserRating) {
        this.appraiserRating = appraiserRating;
    }

    public int getAppraiseeEmpId() {
        return appraiseeEmpId;
    }

    public void setAppraiseeEmpId(int appraiseeEmpId) {
        this.appraiseeEmpId = appraiseeEmpId;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getAppraiseeEmail() {
        return appraiseeEmail;
    }

    public void setAppraiseeEmail(String appraiseeEmail) {
        this.appraiseeEmail = appraiseeEmail;
    }

    public String getAppraiseeNameMail() {
        return appraiseeNameMail;
    }

    public void setAppraiseeNameMail(String appraiseeNameMail) {
        this.appraiseeNameMail = appraiseeNameMail;
    }

    public String getAppraiserEmail() {
        return appraiserEmail;
    }

    public void setAppraiserEmail(String appraiserEmail) {
        this.appraiserEmail = appraiserEmail;
    }

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getAppraiseeSubmitStatusForm() {
        return appraiseeSubmitStatusForm;
    }

    public int getFetchApprasalQuarter() {
        return fetchApprasalQuarter;
    }

    public void setFetchApprasalQuarter(int fetchApprasalQuarter) {
        this.fetchApprasalQuarter = fetchApprasalQuarter;
    }

    public int getFetchApprasalYear() {
        return fetchApprasalYear;
    }

    public void setFetchApprasalYear(int fetchApprasalYear) {
        this.fetchApprasalYear = fetchApprasalYear;
    }

    public void setAppraiseeSubmitStatusForm(int appraiseeSubmitStatusForm) {
        this.appraiseeSubmitStatusForm = appraiseeSubmitStatusForm;
    }


    public int getAppraisalQuarterForm() {
        return appraisalQuarterForm;
    }

    public void setAppraisalQuarterForm(int appraisalQuarterForm) {
        this.appraisalQuarterForm = appraisalQuarterForm;
    }

    public int getAppraisalYearForm() {
        return appraisalYearForm;
    }

    public void setAppraisalYearForm(int appraisalYearForm) {
        this.appraisalYearForm = appraisalYearForm;
    }

    public int getAppraiseeIdForm() {
        return appraiseeIdForm;
    }

    public void setAppraiseeIdForm(int appraiseeIdForm) {
        this.appraiseeIdForm = appraiseeIdForm;
    }

    public int getBandIdForm() {
        return bandIdForm;
    }

    public void setBandIdForm(int bandIdForm) {
        this.bandIdForm = bandIdForm;
    }


    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getAppraiseeId() {
        return appraiseeId;
    }

    public void setAppraiseeId(int appraiseeId) {
        this.appraiseeId = appraiseeId;
    }

    public String getAppraiseeName() {
        return appraiseeName;
    }

    public void setAppraiseeName(String appraiseeName) {
        this.appraiseeName = appraiseeName;
    }

    public String getAppraiseeNumber() {
        return appraiseeNumber;
    }

    public void setAppraiseeNumber(String appraiseeNumber) {
        this.appraiseeNumber = appraiseeNumber;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getAreasOfImprovement() {
        return areasOfImprovement;
    }

    public void setAreasOfImprovement(String areasOfImprovement) {
        this.areasOfImprovement = areasOfImprovement;
    }

    public String getSoftskillTraining() {
        return softskillTraining;
    }

    public void setSoftskillTraining(String softskillTraining) {
        this.softskillTraining = softskillTraining;
    }

    public String getTechnologyTraining() {
        return technologyTraining;
    }

    public void setTechnologyTraining(String technologyTraining) {
        this.technologyTraining = technologyTraining;
    }

    public int getQpdId() {
        return qpdId;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDiscussionDate() {
        return discussionDate;
    }

    public void setDiscussionDate(String discussionDate) {
        this.discussionDate = discussionDate;
    }

    
}


