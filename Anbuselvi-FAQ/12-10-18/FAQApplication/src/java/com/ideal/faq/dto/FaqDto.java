/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.dto;

import java.util.List;

/**
 *
 * @author 16711
 */
public class FaqDto {
    
    /* for faq_question_answer table */
    //private String id;// id is common for all table
    private String question_answer_id;
    private String question;
    private String answer;
    private String answer_path;
    
//    private List<FaqDto> faq_Questions_Answers;
    
    
    /*common for all table*/
    //private String faq_unit_id;
    //private String faq_category_id;
    
    private String deleted;
    private String created_date;
    private String created_by;
    private String modified_date;
    private String modified_by;
    
    /* for faq_function_unit*/    
    private String faq_unit;
    private String faq_unit_id;
    
    /* for faq_question_category*/    
    private String question_category;
    private String question_category_id;
    
    /* for faq_feedback*/    
    private String feedback_id;
    private String feedback_rating_comments;
    private String is_satisfied;
    
    /* for faq_associate_mapping*/ 
    private String associate_mapping_id;
    private String employee_id;
    private String[] faq_units;
    
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer_path() {
        return answer_path;
    }

    public void setAnswer_path(String answer_path) {
        this.answer_path = answer_path;
    }

    public String getFaq_unit_id() {
        return faq_unit_id;
    }

    public void setFaq_unit_id(String faq_unit_id) {
        this.faq_unit_id = faq_unit_id;
    }

//    public String getFaq_category_id() {
//        return faq_category_id;
//    }
//
//    public void setFaq_category_id(String faq_category_id) {
//        this.faq_category_id = faq_category_id;
//    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getFaq_unit() {
        return faq_unit;
    }

    public void setFaq_unit(String faq_unit) {
        this.faq_unit = faq_unit;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }

    public String getFeedback_rating_comments() {
        return feedback_rating_comments;
    }

    public void setFeedback_rating_comments(String feedback_rating_comments) {
        this.feedback_rating_comments = feedback_rating_comments;
    }

    public String getIs_satisfied() {
        return is_satisfied;
    }

    public void setIs_satisfied(String is_satisfied) {
        this.is_satisfied = is_satisfied;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String[] getFaq_units() {
        return faq_units;
    }

    public void setFaq_units(String[] faq_units) {
        this.faq_units = faq_units;
    }

    public String getQuestion_category_id() {
        return question_category_id;
    }

    public void setQuestion_category_id(String question_category_id) {
        this.question_category_id = question_category_id;
    }

    public String getQuestion_answer_id() {
        return question_answer_id;
    }

    public void setQuestion_answer_id(String question_answer_id) {
        this.question_answer_id = question_answer_id;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getAssociate_mapping_id() {
        return associate_mapping_id;
    }

    public void setAssociate_mapping_id(String associate_mapping_id) {
        this.associate_mapping_id = associate_mapping_id;
    }

//    public List<FaqDto> getFaq_Questions_Answers() {
//        return faq_Questions_Answers;
//    }
//
//    public void setFaq_Questions_Answers(List<FaqDto> faq_Questions_Answers) {
//        this.faq_Questions_Answers = faq_Questions_Answers;
//    }

    
   
    
}
