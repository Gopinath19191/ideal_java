/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.service;

import com.ideal.faq.dao.FAQDao;
import com.ideal.faq.dto.FaqDto;
import java.util.List;

/**
 *
 * @author 16711
 */
public class FAQServiceImpl implements FAQService {

    public FAQDao dao;

    public FAQDao getDao() {
        return dao;
    }

    public void setDao(FAQDao dao) {
        this.dao = dao;
    }

    @Override
    public List<FaqDto> getAllFaqFunctionUnit() {

        return dao.getAllFaqFunctionUnit();

    }

    @Override
    public List<FaqDto> getAllQuestionCategory(String unit_id) {

        return dao.getAllQuestionCategory(unit_id);

    }
    
    public void addQuestionCategoryForUnit(FaqDto questionCategoryObj){
        dao.addQuestionCategoryForUnit(questionCategoryObj);
    }

    @Override
    public void updateFaq(FaqDto faqDto) {

        dao.updateFaq(faqDto);

    }

    @Override
    public void insertFaq(FaqDto faqDto) {

        dao.insertFaq(faqDto);

    }
    
    @Override
    public List<FaqDto> listQuestionAnswer(String faq_unit_id,String questionCategoryId) {

        return dao.listQuestionAnswer(faq_unit_id,questionCategoryId);

    }
    
    @Override
    public String getFunctionUnit(String faq_unit_id) {

        return dao.getFunctionUnit(faq_unit_id);

    }
    
    @Override
    public String getQuestionCategory(String questionCategoryId) {

        return dao.getQuestionCategory(questionCategoryId);

    }
    
    @Override
    public void addFeedback(FaqDto feedback) {

        dao.addFeedback(feedback);

    }
    
    @Override
    public void updateDeleteFlag(FaqDto faqObj) {

        dao.updateDeleteFlag(faqObj);

    }
    @Override
   public List<FaqDto> getFaqFeedback(String faq_unit_id,String questionCategoryId){

        return dao.getFaqFeedback(faq_unit_id,questionCategoryId);

    }
    @Override
    public String getEmployeeName(String employee_id){
        return dao.getEmployeeName(employee_id);
        
    }
    
}
