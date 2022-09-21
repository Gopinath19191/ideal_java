/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.shared;

import org.apache.beehive.netui.pageflow.annotations.Jpf;

/**
 *
 * @author HARIHARAN.C
 */
@Jpf.FormBean()
public class SendMailDTO implements java.io.Serializable{
private String firstName;
private String middleName;
private String lastName;

private String fullName;
private String emailId;

private String employeeCode;
private String fileName;
private String fileType;
private String referenceName;
private int referenceId;
private int fileId;
private String moduleName;
private String otherData;
public int appraiser;
public String appraiserNameMail;
public String appraiserEmailId;
public String reviewerEmailId;
public String reviewerNameMail;

public String normaliserEmailId;
public String normaliserNameMail;

public String hrEmailId;
public String hrNameMail;

public String financeEmailId;
public String financeNameMail;

    public String getReviewerEmailId() {
        return reviewerEmailId;
    }

    public void setReviewerEmailId(String reviewerEmailId) {
        this.reviewerEmailId = reviewerEmailId;
    }

    public String getReviewerNameMail() {
        return reviewerNameMail;
    }

    public void setReviewerNameMail(String reviewerNameMail) {
        this.reviewerNameMail = reviewerNameMail;
    }

    public int getAppraiser() {
        return appraiser;
    }

    public void setAppraiser(int appraiser) {
        this.appraiser = appraiser;
    }

    public String getAppraiserEmailId() {
        return appraiserEmailId;
    }

    public void setAppraiserEmailId(String appraiserEmailId) {
        this.appraiserEmailId = appraiserEmailId;
    }

    public String getAppraiserNameMail() {
        return appraiserNameMail;
    }

    public void setAppraiserNameMail(String appraiserNameMail) {
        this.appraiserNameMail = appraiserNameMail;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmployeeFullName(){
       // return this.firstName+" "+this.middleName+" "+this.lastName;
        return this.firstName+" "+this.lastName;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public String getFinanceEmailId() {
        return financeEmailId;
    }

    public void setFinanceEmailId(String financeEmailId) {
        this.financeEmailId = financeEmailId;
    }

    public String getFinanceNameMail() {
        return financeNameMail;
    }

    public void setFinanceNameMail(String financeNameMail) {
        this.financeNameMail = financeNameMail;
    }

    public String getHrEmailId() {
        return hrEmailId;
    }

    public void setHrEmailId(String hrEmailId) {
        this.hrEmailId = hrEmailId;
    }

    public String getHrNameMail() {
        return hrNameMail;
    }

    public void setHrNameMail(String hrNameMail) {
        this.hrNameMail = hrNameMail;
    }

    public String getNormaliserEmailId() {
        return normaliserEmailId;
    }

    public void setNormaliserEmailId(String normaliserEmailId) {
        this.normaliserEmailId = normaliserEmailId;
    }

    public String getNormaliserNameMail() {
        return normaliserNameMail;
    }

    public void setNormaliserNameMail(String normaliserNameMail) {
        this.normaliserNameMail = normaliserNameMail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
