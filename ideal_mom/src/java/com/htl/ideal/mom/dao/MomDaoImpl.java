/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.dao;

import com.htl.ideal.mom.dto.MomDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16656
 */
public class MomDaoImpl extends SqlMapClientDaoSupport implements MomDao {

    public List<MomDto> getallMom(MomDto id) throws Exception {

        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAllDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getMomCount(MomDto dto) {
        int momCount = 0;
        try {
            momCount = (Integer) getSqlMapClientTemplate().queryForObject("MomMap.getMomRecordCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momCount;
    }
    public int getstatuscount(MomDto id) {
        int statusCount = 0;
        try {
            statusCount = (Integer) getSqlMapClientTemplate().queryForObject("MomMap.getstatuscount", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusCount;
    }

    public List<MomDto> getMomStatus() {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getMomStatus");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<MomDto> getMailDetails() {
        ArrayList mailDetails = null;
        try {
            mailDetails = (ArrayList<MomDto>) getSqlMapClientTemplate().queryForList("MomMap.getConfigValueData");
        } catch (Exception e) {
        }
        return mailDetails;
    }

    public String getEmployeesPK(String id) {
        String employee_pk = null;
        try {
            employee_pk = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmployeesPK", id);
//            dto.setEmployee_id(employee_pk);
//            getSqlMapClientTemplate().queryForList("MomMap.getEmployeeaMail");
        } catch (Exception e) {
        }
        return employee_pk;
    }
     public String getActionEmployeesPK(String id) {
        String employee_pk = null;
        try {
            employee_pk = (String) getSqlMapClientTemplate().queryForObject("MomMap.getActionEmployeesPK", id);
//            dto.setEmployee_id(employee_pk);
//            getSqlMapClientTemplate().queryForList("MomMap.getEmployeeaMail");
        } catch (Exception e) {
        }
        return employee_pk;
    }

    public String getEmployeesMail(String empPrimaryKey) {
        String employee_mail = null;
        try {
            employee_mail = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmployeesMail", empPrimaryKey);
        } catch (Exception e) {
        }
        return employee_mail;
    }

    public String getMomCreatorMail(String minuted_by) {
        String momCreator_mail = null;
        try {
            momCreator_mail = (String) getSqlMapClientTemplate().queryForObject("MomMap.getMomCreatorMail", minuted_by);
        } catch (Exception e) {
        }
        return momCreator_mail;
    }

    public List<MomDto> getConfigurationKeys() throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getConfigurationKeys");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getSearchList(String empVal) {
        List<MomDto> searchList = null;
        try {
            String key = empVal + "%";
            System.out.println("in daoimpl::::" + key);
            searchList = getSqlMapClientTemplate().queryForList("MomMap.getSearchList", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<MomDto> getSearchMomList(String momVal, String momid) {
        List<MomDto> searchList = null;
        try {
            String key = momVal + "%";
            HashMap map = new HashMap();
            map.put("key", key);
            map.put("momid", momid);
            System.out.println("in daoimpl::::" + key);
            searchList = getSqlMapClientTemplate().queryForList("MomMap.getSearchMomList", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public String addMom(MomDto dto) throws Exception {
        String mom_id = null;
        try {
//            getSqlMapClientTemplate().insert("MomMap.addMom",dto);
            mom_id = (String) getSqlMapClientTemplate().insert("MomMap.addMom", dto);
            System.out.println("last id is " + mom_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mom_id;
    }

    public String getEmployeeName(String employee_id) throws Exception {
        String employee_name = null;
        try {
            employee_name = (String) getSqlMapClientTemplate().queryForObject("MomMap.getMinutedbyName", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_name;
    }
 public String getemployeeName(String employee_id) throws Exception {
        String employee_name = null;
        try {
            employee_name = (String) getSqlMapClientTemplate().queryForObject("MomMap.getemployeeName", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_name;
    }
 public String getempno(String employee_id) throws Exception {
        String employee_name = null;
        try {
            employee_name = (String) getSqlMapClientTemplate().queryForObject("MomMap.getempno", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_name;
    }
    public void insertMomMembers(MomDto dto) throws Exception {
        String employee_id = null;
        try {
            employee_id = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmployeeId", dto.getWork_email_address());
            dto.setEmployee_id(employee_id);
            getSqlMapClientTemplate().insert("MomMap.insertMomMembers", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertMomMembers2(MomDto dto) throws Exception {
        String employee_id = null;
        try {
            String employee_no=dto.getEmployee_no();
            employee_id = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmployeeId2",employee_no );
            dto.setEmployee_id(employee_id);
            getSqlMapClientTemplate().insert("MomMap.insertMomMembers2", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMomAgenda(MomDto dto) throws Exception {
        try {
            getSqlMapClientTemplate().insert("MomMap.insertMomAgenda", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMomDiscussions(MomDto dto) throws Exception {
        try {
            getSqlMapClientTemplate().insert("MomMap.insertMomDiscussions", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertActionPoints(MomDto dto) throws Exception {
        String employee_id = null;
        try {
            employee_id = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmailId", dto.getAction_point_employee_id());
            dto.setAction_point_employee_id(employee_id);
            getSqlMapClientTemplate().insert("MomMap.insertActionPoints", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MomDto editMom(String id) throws Exception {
        MomDto list = new MomDto();
//         List<EmployeeDTO> list  =new ArrayList<EmployeeDTO>();
        try {
            list = (MomDto) getSqlMapClientTemplate().queryForObject("MomMap.editMom", id);
//            list = getSqlMapClientTemplate().queryForList("studentMap.editStudent");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getPresentMembers(String id) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getPresentMembers", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getAbsentMembers(String id) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAbsentMembers", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getallAgenda(String id) throws Exception {
        List<MomDto> list = null;
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAllAgenda", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getAllDiscussion(String id) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAllDiscussion", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getAllActions(String id) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAllActions", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto updateMom(MomDto dto) throws Exception {
        MomDto list = new MomDto();
//         List<EmployeeDTO> list  =new ArrayList<EmployeeDTO>();`
        try {
            getSqlMapClientTemplate().update("MomMap.updateMom", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto updateMomwithSave(MomDto dto) throws Exception {
        MomDto list = new MomDto();
//         List<EmployeeDTO> list  =new ArrayList<EmployeeDTO>();`
        try {
            getSqlMapClientTemplate().update("MomMap.updateMomwithSave", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 public MomDto updateMomwithDraft(MomDto dto) throws Exception {
        MomDto list = new MomDto();
//         List<EmployeeDTO> list  =new ArrayList<EmployeeDTO>();`
        try {
            getSqlMapClientTemplate().update("MomMap.updateMomwithDraft", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<MomDto> updateMomMembers(MomDto dto) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        String employee_id = null;
        try {
            employee_id = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmployeeId", dto.getEmployee_no());
            dto.setEmployee_id(employee_id);
            getSqlMapClientTemplate().update("MomMap.updateMomMembers", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> updateAgenda(MomDto dto) throws Exception {
//        MomDto list =new MomDto();
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            getSqlMapClientTemplate().update("MomMap.updateAgenda", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> updateDiscussion(MomDto dto) throws Exception {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            getSqlMapClientTemplate().update("MomMap.updateDiscussion", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> updateActionPoint(MomDto dto) throws Exception {
//        MomDto list =new MomDto();
        String employee_id = null;
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            employee_id = (String) getSqlMapClientTemplate().queryForObject("MomMap.getEmailId", dto.getAction_point_employee_id());
            dto.setAction_point_employee_id(employee_id);
            getSqlMapClientTemplate().update("MomMap.updateActionPoint", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto updateActionPointItems(MomDto dto) {
        MomDto list = new MomDto();
        try {
            getSqlMapClientTemplate().update("MomMap.updateActionPointItems", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getActionListDetails(MomDto id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getActionListDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto getMeetingDetails(String id) {
        MomDto list = new MomDto();
        try {
            list = (MomDto) getSqlMapClientTemplate().queryForObject("MomMap.getMeetingDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getAgenda(String id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAgenda", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getDiscussion(String id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getDiscussion", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getActionPoint(String id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getActionPoint", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getPresentDetails(String id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getPresentDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getStatus() {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getStatus");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 public List<MomDto> geteditemployees(String mom_id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.geteditemployees",mom_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 public List<MomDto> geteditemployees2(String mom_id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.geteditemployees2",mom_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<MomDto> getAbsentDetails(String id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getAbsentDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto updateActionPoints(MomDto dto) {
        MomDto list = new MomDto();
        try {
            getSqlMapClientTemplate().update("MomMap.updateActionPoints", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MomDto updateHistory(MomDto dto) {
        MomDto list = new MomDto();
        try {
            getSqlMapClientTemplate().insert("MomMap.updateHistory", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MomDto> getHistory(MomDto dto) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getHistory", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getActionRecordCount(MomDto dto) {
        int ActionCount = 0;
        try {
//            System.out.println("attendanceDetails : "+attendanceCount);
            ActionCount = (Integer) getSqlMapClientTemplate().queryForObject("MomMap.getActionRecordCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionCount;
    }

    public List<MomDto> getSearchmom(String empVal, String employee_id) {
        List<MomDto> searchList = null;
        try {
            String key = empVal + "%";
            HashMap map = new HashMap();
            map.put("key", key);
            map.put("employee_id", employee_id);
            System.out.println("in daoimpl::::" + key);
            searchList = getSqlMapClientTemplate().queryForList("MomMap.getSearchmom", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<MomDto> getHistories(MomDto dto) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.getHistory1", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getempPK(String id) {
        String employee_pk = null;
        try {
            employee_pk = (String) getSqlMapClientTemplate().queryForObject("MomMap.getempPK", id);
//            dto.setEmployee_id(employee_pk);
//            getSqlMapClientTemplate().queryForList("MomMap.getEmployeeaMail");
        } catch (Exception e) {
        }
        return employee_pk;
    }

    public MomDto updatesentback(String id) {
        MomDto list = new MomDto();
        try {
            getSqlMapClientTemplate().update("MomMap.updatesentback", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getMomNames(MomDto dto) {
        String momName = null;
        try {
            momName = (String) getSqlMapClientTemplate().queryForObject("MomMap.getMomNames", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momName;
    }
     public List<MomDto> editMomActionPoints(MomDto id) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.editMomActionPoints", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public List<MomDto> getEmpDetails(String refVal) {
        List<MomDto> refEmp = null;
        refVal = "'%" + refVal + "%'";
        logger.info("refVal " + refVal);
        refEmp = getSqlMapClientTemplate().queryForList("MomMap.getEmpIds", refVal);

        return refEmp;
    }
    
    public List<MomDto> totalcount(MomDto dto) {
        List<MomDto> list = new ArrayList<MomDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("MomMap.totalcount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
