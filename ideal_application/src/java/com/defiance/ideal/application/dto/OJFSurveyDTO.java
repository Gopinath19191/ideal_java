/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dto;

import java.io.Serializable;

/**
 *
 * @author 14058
 */
public class OJFSurveyDTO implements Serializable {
    public String resEmpId;
    public String name;
    public String answerKey;
    public String answerValue;
    public String questionId;
    public String answerType;
    public String questionDescription;
    public String parentId;
    public String empAnswer;
    public String preJoiningRemarks;
    public String inductionRemarks;
    public String environmentRemarks;
    public String roleRemarks;
    public String communicationRemarks;
    public String ojfSaveButton;
    public String ojfSubmitButton;
    public String ojfCancelButton;
    public String ojfEmpId;
    public String exitEmpId;
    public String ojf_survey_rating;
    public String ojf_survey_status;
    public String employee_code;
    public String employee_name;
    public String pm_name;
    public String sbu_name;
    public String joined_date;

    public String getEmployee_code() {
        return employee_code;
    }

    public String getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(String joined_date) {
        this.joined_date = joined_date;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getPm_name() {
        return pm_name;
    }

    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }

    public String getSbu_name() {
        return sbu_name;
    }

    public void setSbu_name(String sbu_name) {
        this.sbu_name = sbu_name;
    }

    public String getOjf_survey_status() {
        return ojf_survey_status;
    }

    public void setOjf_survey_status(String ojf_survey_status) {
        this.ojf_survey_status = ojf_survey_status;
    }

    public String getOjf_survey_rating() {
        return ojf_survey_rating;
    }

    public void setOjf_survey_rating(String ojf_survey_rating) {
        this.ojf_survey_rating = ojf_survey_rating;
    }

    public String getOjfEmpId() {
        return ojfEmpId;
    }

    public String getExitEmpId() {
        return exitEmpId;
    }

    public void setExitEmpId(String exitEmpId) {
        this.exitEmpId = exitEmpId;
    }

    public void setOjfEmpId(String ojfEmpId) {
        this.ojfEmpId = ojfEmpId;
    }


    public String getOjfCancelButton() {
        return ojfCancelButton;
    }

    public void setOjfCancelButton(String ojfCancelButton) {
        this.ojfCancelButton = ojfCancelButton;
    }

    public String getOjfSaveButton() {
        return ojfSaveButton;
    }

    public void setOjfSaveButton(String ojfSaveButton) {
        this.ojfSaveButton = ojfSaveButton;
    }

    public String getOjfSubmitButton() {
        return ojfSubmitButton;
    }

    public void setOjfSubmitButton(String ojfSubmitButton) {
        this.ojfSubmitButton = ojfSubmitButton;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getCommunicationRemarks() {
        return communicationRemarks;
    }

    public void setCommunicationRemarks(String communicationRemarks) {
        this.communicationRemarks = communicationRemarks;
    }

    public String getEmpAnswer() {
        return empAnswer;
    }

    public void setEmpAnswer(String empAnswer) {
        this.empAnswer = empAnswer;
    }

    public String getEnvironmentRemarks() {
        return environmentRemarks;
    }

    public void setEnvironmentRemarks(String environmentRemarks) {
        this.environmentRemarks = environmentRemarks;
    }

    public String getInductionRemarks() {
        return inductionRemarks;
    }

    public void setInductionRemarks(String inductionRemarks) {
        this.inductionRemarks = inductionRemarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPreJoiningRemarks() {
        return preJoiningRemarks;
    }

    public void setPreJoiningRemarks(String preJoiningRemarks) {
        this.preJoiningRemarks = preJoiningRemarks;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getResEmpId() {
        return resEmpId;
    }

    public void setResEmpId(String resEmpId) {
        this.resEmpId = resEmpId;
    }

    public String getRoleRemarks() {
        return roleRemarks;
    }

    public void setRoleRemarks(String roleRemarks) {
        this.roleRemarks = roleRemarks;
    }
}
