/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.dao;

import com.ideal.faq.dto.FaqDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16711
 */
public class FAQDaoImpl extends SqlMapClientDaoSupport implements FAQDao {
    final static Logger logger = Logger.getLogger(FAQDaoImpl.class);
    
    public List<FaqDto> getAllFaqFunctionUnit(){
        
        List<FaqDto> listFunctionUnit = new ArrayList<FaqDto>();
    
        try {
            
            listFunctionUnit = getSqlMapClientTemplate().queryForList("faqMap.getFunctionUnits");
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return listFunctionUnit;
    }
    public List<FaqDto> getFaqFeedback(String faq_unit_id,String questionCategoryId){
        
        List<FaqDto> listFaqFeedback = new ArrayList<FaqDto>();
        FaqDto faqQuesAns = new FaqDto();
        
        faqQuesAns.setFaq_unit_id(faq_unit_id);
        faqQuesAns.setQuestion_category_id(questionCategoryId);
    
        try {
            
            listFaqFeedback = getSqlMapClientTemplate().queryForList("faqMap.getFaqFeedback",faqQuesAns);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return listFaqFeedback;
    }
    
     public String getFunctionUnit(String faq_unit_id){
        
        String functionUnit = new String();
    
        try {
            
            functionUnit = (String)getSqlMapClientTemplate().queryForObject("faqMap.getFunctionUnit",faq_unit_id);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return functionUnit;
    }
   public String getQuestionCategory(String questionCategoryId){
        
        String question_category = new String();
    
        try {
            
            question_category = (String)getSqlMapClientTemplate().queryForObject("faqMap.getQuestioncategory",questionCategoryId);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return question_category;
    }
    
    
    public List<FaqDto> listQuestionAnswer(String faq_unit_id,String questionCategoryId){
        
        String deleted = "0";
        
        List<FaqDto> listQuestionAnswer = new ArrayList<FaqDto>();
        
        FaqDto faqQuesAns = new FaqDto();
        
        faqQuesAns.setFaq_unit_id(faq_unit_id);
        faqQuesAns.setQuestion_category_id(questionCategoryId);
        faqQuesAns.setDeleted(deleted);
    
        try {
            
            listQuestionAnswer = getSqlMapClientTemplate().queryForList("faqMap.listQuestionAnswer",faqQuesAns);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return listQuestionAnswer;
    }   
    
    public List<FaqDto> getAllQuestionCategory(String unit_id){
        
        List<FaqDto> listQuestionCategory = new ArrayList<FaqDto>();
    
        try {
            
            listQuestionCategory = getSqlMapClientTemplate().queryForList("faqMap.getQuestionCategory",unit_id);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return listQuestionCategory;
    }
    
    public void addQuestionCategoryForUnit(FaqDto questionCategoryObj){
        
        try {
            getSqlMapClientTemplate().insert("faqMap.insertQuestionCategory",questionCategoryObj);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void addFeedback(FaqDto feedback){
        
        try {
            getSqlMapClientTemplate().insert("faqMap.insertFeedback",feedback);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    
    
    public void updateFaq(FaqDto faqDto){
        
        try {
            getSqlMapClientTemplate().update("faqMap.updateFaq",faqDto);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void updateDeleteFlag(FaqDto faqObj){
        
        try {
            getSqlMapClientTemplate().update("faqMap.updateFaqFlag",faqObj);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void insertFaq(FaqDto faqDto){
        
        try {
            getSqlMapClientTemplate().insert("faqMap.insertFaq",faqDto);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public String getEmployeeName(String employee_id) {
        String employee_name = null;
        try {
            employee_name = (String) getSqlMapClientTemplate().queryForObject("faqMap.getEmployeeName", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_name;
    }
    
}
