/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.OJFSurveyDTO;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.SendMail;
import com.defiance.ideal.application.util.MailMessages;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class OJFSurveyDaoImpl extends SqlMapClientDaoSupport implements OJFSurveyDao {

    public List<OJFSurveyDTO> getSurveyQuestions(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.getSurveyQuestionsAndAnswers",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }

    public List<OJFSurveyDTO> getSurveyAnswers() {
         List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.getSurveyAnswers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }

    public List<OJFSurveyDTO> getSurveyQuestionsParent() {
        List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.getSurveyQuestionsParent");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }

    public void saveSurveyData(String[] questionId, String[] empAnswer, OJFSurveyDTO surveyData) throws Exception {
        List<OJFSurveyDTO> checkSavedData=null;
        try {
            checkSavedData=( List<OJFSurveyDTO>)getSqlMapClientTemplate().queryForList("OJFSurvey.checkSavedData",surveyData.getExitEmpId());
            getSqlMapClient().startTransaction();
            getSqlMapClient().startBatch();
            for (int i = 0; i < questionId.length; i++) {
                surveyData.setQuestionId(questionId[i]);
                surveyData.setEmpAnswer(empAnswer[i]);
                System.out.println(":QN:"+surveyData.questionId+":ANS:"+surveyData.empAnswer);
                if(checkSavedData.size()==0){
                    getSqlMapClientTemplate().insert("OJFSurvey.insertSurveyData", surveyData);
                }else{
                    getSqlMapClientTemplate().update("OJFSurvey.updateSurveyData",surveyData);
                }
            }
            surveyData.setOjf_survey_status("0");
            getSqlMapClientTemplate().update("OJFSurvey.updateSurveyStatus",surveyData);
            getSqlMapClient().executeBatch();
            getSqlMapClient().commitTransaction();
        } finally {
            //getSqlMapClient().endTransaction();
        }
    }

    public void submitSurveyData(OJFSurveyDTO surveyData)
    {
        try{
            //surveyData.setExitEmpId(empId);
            surveyData.setOjf_survey_status("1");
            getSqlMapClientTemplate().update("OJFSurvey.updateSurveyStatus",surveyData);
//            String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
//            triggerMail(exitSurveyList, toMailApprovalModules, empFormData, "exitSurveyToHr", "exitSurveyToHrMsg", "exitSurveyToHrMsg", CommonConfigurations.exitSurveyModuleName, empId);
            System.out.println("Exit Survey Data Submitted and mail triggered");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<OJFSurveyDTO> getOjfEmployeeData(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.getOjfEmployeeData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }

    public List<OJFSurveyDTO> getOjfSurveyList(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.getOjfSurveyList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }

    public List<OJFSurveyDTO> OJFSurveyView(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> surveyData = null;
        try {
            surveyData = getSqlMapClientTemplate().queryForList("OJFSurvey.checkSavedData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surveyData;
    }
    
}
