/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.OJFSurveyDao;
import com.defiance.ideal.application.dao.OJFSurveyDaoImpl;
import com.defiance.ideal.application.dto.OJFSurveyDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public class OJFSurveyServiceImpl implements OJFSurveyService {

    public OJFSurveyDaoImpl ojfSurveyDaoImpl;

    public OJFSurveyDaoImpl getOjfSurveyDaoImpl() {
        return ojfSurveyDaoImpl;
    }

    public void setOjfSurveyDaoImpl(OJFSurveyDaoImpl ojfSurveyDaoImpl) {
        this.ojfSurveyDaoImpl = ojfSurveyDaoImpl;
    }

    public List<OJFSurveyDTO> getSurveyQuestions(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.getSurveyQuestions(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }

    public List<OJFSurveyDTO> OJFSurveyInfo(OJFSurveyDTO filterData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<OJFSurveyDTO> getSurveyAnswers() {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.getSurveyAnswers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }

    public List<OJFSurveyDTO> getSurveyQuestionsParent() {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.getSurveyQuestionsParent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }

    public void saveSurveyData(String[] questionId, String[] empAnswer, OJFSurveyDTO surveyData) {
       try {
            ojfSurveyDaoImpl.saveSurveyData(questionId,empAnswer,surveyData);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
   public void submitSurveyData(OJFSurveyDTO surveyData){
       try {
            ojfSurveyDaoImpl.submitSurveyData(surveyData);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    public List<OJFSurveyDTO> getOjfEmployeeData(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.getOjfEmployeeData(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }

    public List<OJFSurveyDTO> getOjfSurveyList(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.getOjfSurveyList(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }

    public List<OJFSurveyDTO> OJFSurveyView(OJFSurveyDTO filterData) {
        List<OJFSurveyDTO> ojfData = new ArrayList<OJFSurveyDTO>();
        try {
            ojfData = ojfSurveyDaoImpl.OJFSurveyView(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ojfData;
    }
}
