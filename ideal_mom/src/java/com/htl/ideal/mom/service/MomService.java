/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.service;

import com.htl.ideal.mom.dto.MomDto;
import java.util.List;

/**
 *
 * @author 16656
 */
public interface MomService {

    public List<MomDto> getallMom(MomDto id) throws Exception;

    public int getMomCount(MomDto dto);
    public int getstatuscount(MomDto id);
    public List<MomDto> getMomStatus();
     public List<MomDto> geteditemployees(String id);
      public List<MomDto> geteditemployees2(String id);
    public List<MomDto> getConfigurationKeys() throws Exception;

    public List<MomDto> getSearchList(String empVal);

    public List<MomDto> getSearchMomList(String momVal, String momid);

    public String addMoM(MomDto dto) throws Exception;

    public String getEmployeeName(String employee_id) throws Exception;
 public String getemployeeName(String employee_id) throws Exception;
 public String getempno(String employee_id) throws Exception;
    public void insertMomMembers(MomDto dto) throws Exception;
    public void insertMomMembers2(MomDto dto) throws Exception;
    public void insertMomAgenda(MomDto dto) throws Exception;

    public void insertMomDiscussions(MomDto dto) throws Exception;

    public void insertActionPoints(MomDto dto) throws Exception;

    public MomDto editMom(String id) throws Exception;

    public List<MomDto> getPresentMembers(String id) throws Exception;

    public List<MomDto> getAbsentMembers(String id) throws Exception;

    public List<MomDto> getallAgenda(String id) throws Exception;

    public List<MomDto> getAllDiscussion(String id) throws Exception;

    public List<MomDto> getAllActions(String id) throws Exception;

    public MomDto updateMom(MomDto dto) throws Exception;

    public MomDto updateMomwithSave(MomDto dto) throws Exception;
     public MomDto updateMomwithDraft(MomDto dto) throws Exception;
    public List<MomDto> updateMomMembers(MomDto dto) throws Exception;

    public List<MomDto> updateAgenda(MomDto dto) throws Exception;

    public List<MomDto> updateDiscussion(MomDto dto) throws Exception;

    public List<MomDto> updateActionPoint(MomDto dto) throws Exception;

    public MomDto updateActionPointItems(MomDto dto);

    public List<MomDto> getActionListDetails(MomDto id);

    public List<MomDto> getStatus();

    public MomDto getMeetingDetails(String id);

    public List<MomDto> getPresentDetails(String id);

    public List<MomDto> getAbsentDetails(String id);

    public List<MomDto> getAgenda(String id);

    public List<MomDto> getDiscussion(String id);

    public List<MomDto> getActionPoint(String id);

    public MomDto updateActionPoints(MomDto dto);

    public MomDto updateHistory(MomDto dto);

    public List<MomDto> getHistory(MomDto dto);

    public int getActionRecordCount(MomDto dto);

    public List<MomDto> getSearchmom(String empVal, String employee_id);

    public List<MomDto> getHistories(MomDto dto);

    public MomDto updatesentback(String id);

    public String getMomNames(MomDto dto);
     public List<MomDto> editMomActionPoints(MomDto id);
     public List<MomDto> getEmpDetails(String refVal);
     public List<MomDto> totalcount(MomDto dto);
      public String getActionEmployeesPK(String id);
}
