/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.service;

import com.htl.ideal.mom.dao.MomDao;
import com.htl.ideal.mom.dao.MomDaoImpl;
import com.htl.ideal.mom.dto.MomDto;
import java.util.List;

/**
 *
 * @author 16656
 */
public class MomServiceImpl implements MomService {

    public MomDao dao;

    public MomDao getDao() {
        return dao;
    }

    public void setDao(MomDao dao) {
        this.dao = dao;
    }

    public List<MomDto> getallMom(MomDto id) throws Exception {
        return dao.getallMom(id);
    }

    public int getMomCount(MomDto dto) {
        int momRecordCount = 0;
        try {
            momRecordCount = dao.getMomCount(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momRecordCount;
    }
    public int getstatuscount(MomDto id){
         int statusRecordCount = 0;
            try {
                statusRecordCount = dao.getstatuscount(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return statusRecordCount;
    }
    public List<MomDto> getMomStatus() {
        return dao.getMomStatus();
    }
 public List<MomDto> geteditemployees(String id){
      return dao.geteditemployees(id);
 }
 public List<MomDto> geteditemployees2(String id){
      return dao.geteditemployees2(id);
 }
    public List<MomDto> getConfigurationKeys() throws Exception {
        return dao.getConfigurationKeys();
    }

    public List<MomDto> getSearchList(String empVal) {
        List<MomDto> searchList = null;
        try {
            searchList = dao.getSearchList(empVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<MomDto> getSearchMomList(String momVal, String momid) {
        List<MomDto> searchList = null;
        try {
            searchList = dao.getSearchMomList(momVal, momid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public String addMoM(MomDto dto) throws Exception {
        return dao.addMom(dto);
    }

    public String getEmployeeName(String employee_id) throws Exception {
        return dao.getEmployeeName(employee_id);
    }
 public String getemployeeName(String employee_id) throws Exception{
     return dao.getemployeeName(employee_id); 
 }
    public void insertMomMembers(MomDto dto) throws Exception {
        dao.insertMomMembers(dto);
    }
    public void insertMomMembers2(MomDto dto) throws Exception {
         dao.insertMomMembers2(dto);
    }
    public String getempno(String employee_id) throws Exception{
     return dao.getempno(employee_id); 
 }

    public void insertMomAgenda(MomDto dto) throws Exception {
        dao.insertMomAgenda(dto);
    }

    public void insertMomDiscussions(MomDto dto) throws Exception {
        dao.insertMomDiscussions(dto);
    }

    public void insertActionPoints(MomDto dto) throws Exception {
        dao.insertActionPoints(dto);
    }

    public MomDto editMom(String id) throws Exception {
        return dao.editMom(id);
    }

    public List<MomDto> getPresentMembers(String id) throws Exception {
        return dao.getPresentMembers(id);
    }

    public List<MomDto> getAbsentMembers(String id) throws Exception {
        return dao.getAbsentMembers(id);
    }

    public List<MomDto> getallAgenda(String id) throws Exception {
        return dao.getallAgenda(id);
    }

    public List<MomDto> getAllDiscussion(String id) throws Exception {
        return dao.getAllDiscussion(id);
    }

    public List<MomDto> getAllActions(String id) throws Exception {
        return dao.getAllActions(id);
    }

    public MomDto updateMom(MomDto dto) throws Exception {
        return dao.updateMom(dto);
    }

    public MomDto updateMomwithSave(MomDto dto) throws Exception {
        return dao.updateMomwithSave(dto);
    }
 public MomDto updateMomwithDraft(MomDto dto) throws Exception{
      return dao.updateMomwithDraft(dto);
 }
    public List<MomDto> updateMomMembers(MomDto dto) throws Exception {
        return dao.updateMomMembers(dto);
    }

    public List<MomDto> updateAgenda(MomDto dto) throws Exception {
        return dao.updateAgenda(dto);
    }

    public List<MomDto> updateDiscussion(MomDto dto) throws Exception {
        return dao.updateDiscussion(dto);
    }

    public List<MomDto> updateActionPoint(MomDto dto) throws Exception {
        return dao.updateActionPoint(dto);
    }

    public MomDto updateActionPointItems(MomDto dto) {
        return dao.updateActionPointItems(dto);
    }

    public List<MomDto> getActionListDetails(MomDto id) {
        return dao.getActionListDetails(id);
    }
 
    public MomDto getMeetingDetails(String id) {
        return dao.getMeetingDetails(id);
    }

    public List<MomDto> getPresentDetails(String id) {
        return dao.getPresentDetails(id);
    }

    public List<MomDto> getAbsentDetails(String id) {
        return dao.getAbsentDetails(id);
    }

    public List<MomDto> getAgenda(String id) {
        return dao.getAgenda(id);
    }

    public List<MomDto> getDiscussion(String id) {
        return dao.getDiscussion(id);
    }

    public List<MomDto> getActionPoint(String id) {
        return dao.getActionPoint(id);
    }

    public List<MomDto> getStatus() {
        return dao.getStatus();
    }

    public MomDto updateActionPoints(MomDto dto) {
        return dao.updateActionPoints(dto);
    }

    public MomDto updateHistory(MomDto dto) {
        return dao.updateHistory(dto);
    }

    public List<MomDto> getHistory(MomDto dto) {
        return dao.getHistory(dto);
    }

    public int getActionRecordCount(MomDto dto) {
        return dao.getActionRecordCount(dto);
    }

    public List<MomDto> getSearchmom(String empVal, String employee_id) {
        List<MomDto> searchList = null;
        try {
            searchList = dao.getSearchmom(empVal, employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<MomDto> getHistories(MomDto dto) {
        return dao.getHistories(dto);
    }

    public MomDto updatesentback(String id) {
        return dao.updatesentback(id);
    }

    public String getMomNames(MomDto dto) {
        String momName = null;
        try {
            momName = dao.getMomNames(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momName;
    }
    public List<MomDto> editMomActionPoints(MomDto id){
        return dao.editMomActionPoints(id);
    }
    public List<MomDto> getEmpDetails(String refVal) {
        List<MomDto> refEmp = null;
         return dao.getEmpDetails(refVal);
    
    }
    public List<MomDto> totalcount(MomDto dto) {
        return dao.totalcount(dto);
    }
    public String getActionEmployeesPK(String id) {
         return dao.getActionEmployeesPK(id);
    }
}