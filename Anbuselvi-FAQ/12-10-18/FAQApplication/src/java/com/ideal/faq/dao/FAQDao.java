/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.dao;

import com.ideal.faq.dto.FaqDto;
import java.util.List;

/**
 *
 * @author 16711
 */
public interface FAQDao {
    
    public List<FaqDto> getAllFaqFunctionUnit();
    public List<FaqDto> getAllQuestionCategory(String unit_id);
    public void addQuestionCategoryForUnit(FaqDto questionCategoryObj);
    public void updateFaq(FaqDto faqDto);
    public void insertFaq(FaqDto faqDto);
    public List<FaqDto> listQuestionAnswer(String faq_unit_id,String questionCategoryId);
    public String getFunctionUnit(String faq_unit_id);
    public String getQuestionCategory(String questionCategoryId);
    public void addFeedback(FaqDto feedback);
    public void updateDeleteFlag(FaqDto faqObj);
    public List<FaqDto> getFaqFeedback(String faq_unit_id,String questionCategoryId);
    public String getEmployeeName(String employee_id);
    
}
