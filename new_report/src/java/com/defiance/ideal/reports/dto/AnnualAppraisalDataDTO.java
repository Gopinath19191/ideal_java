/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 8000168
 */
public class AnnualAppraisalDataDTO {

	public String empId;
	public String empName;
	public String key;
	public String appraisalYear;
        public String appraiserPromotion;
        public String reviewerPromotion;
        public String normalizerPromotion;

    public String getAppraiserPromotion() {
        return appraiserPromotion;
    }

    public void setAppraiserPromotion(String appraiserPromotion) {
        this.appraiserPromotion = appraiserPromotion;
    }

    public String getReviewerPromotion() {
        return reviewerPromotion;
    }

    public void setReviewerPromotion(String reviewerPromotion) {
        this.reviewerPromotion = reviewerPromotion;
    }

    public String getNormalizerPromotion() {
        return normalizerPromotion;
    }

    public void setNormalizerPromotion(String normalizerPromotion) {
        this.normalizerPromotion = normalizerPromotion;
    }
        
        

	public String getAppraisalYear() {
		return appraisalYear;
	}

	public void setAppraisalYear(String appraisalYear) {
		this.appraisalYear = appraisalYear;
	}

	
	

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public String practiceGroup;
	public String appraiseeNumber;
	public String appraiseeName;
	public String appriaserNumber;
	public String appraiserName;
	public String reviewerNumber;
	public String reviewerName;
	public String normalizerNumber;
	public String normalizerName;
	public String appraiserRating;
	public String reviewerRating;
	public String normalisedReviewerRating;
	private String submittedStatus;
	private String subPracticeGroup;

	public String getPracticeGroup() {
		return practiceGroup;
	}

	public void setPracticeGroup(String practiceGroup) {
		this.practiceGroup = practiceGroup;
	}

	public String getAppraiseeNumber() {
		return appraiseeNumber;
	}

	public void setAppraiseeNumber(String appraiseeNumber) {
		this.appraiseeNumber = appraiseeNumber;
	}

	public String getAppraiseeName() {
		return appraiseeName;
	}

	public void setAppraiseeName(String appraiseeName) {
		this.appraiseeName = appraiseeName;
	}

	public String getAppriaserNumber() {
		return appriaserNumber;
	}

	public void setAppriaserNumber(String appriaserNumber) {
		this.appriaserNumber = appriaserNumber;
	}

	public String getAppraiserName() {
		return appraiserName;
	}

	public void setAppraiserName(String appraiserName) {
		this.appraiserName = appraiserName;
	}

	public String getReviewerNumber() {
		return reviewerNumber;
	}

	public void setReviewerNumber(String reviewerNumber) {
		this.reviewerNumber = reviewerNumber;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getNormalizerNumber() {
		return normalizerNumber;
	}

	public void setNormalizerNumber(String normalizerNumber) {
		this.normalizerNumber = normalizerNumber;
	}

	public String getNormalizerName() {
		return normalizerName;
	}

	public void setNormalizerName(String normalizerName) {
		this.normalizerName = normalizerName;
	}

	public String getAppraiserRating() {
		return appraiserRating;
	}

	public void setAppraiserRating(String appraiserRating) {
		this.appraiserRating = appraiserRating;
	}

	public String getReviewerRating() {
		return reviewerRating;
	}

	public void setReviewerRating(String reviewerRating) {
		this.reviewerRating = reviewerRating;
	}

	public String getNormalisedReviewerRating() {
		return normalisedReviewerRating;
	}

	public void setNormalisedReviewerRating(String normalisedReviewerRating) {
		this.normalisedReviewerRating = normalisedReviewerRating;
	}

	public String getSubmittedStatus() {
		return submittedStatus;
	}

	public void setSubmittedStatus(String submittedStatus) {
		this.submittedStatus = submittedStatus;
	}

	public String getSubPracticeGroup() {
		return subPracticeGroup;
	}

	public void setSubPracticeGroup(String subPracticeGroup) {
		this.subPracticeGroup = subPracticeGroup;
	}
	
}
