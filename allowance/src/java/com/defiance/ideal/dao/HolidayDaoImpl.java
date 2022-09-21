            /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.HolidayDto;
import com.defiance.ideal.dto.DetailsDto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.defiance.ideal.util.MailMessages;
import com.defiance.ideal.util.SendMail;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 14053
 */
public class HolidayDaoImpl extends SqlMapClientDaoSupport implements HolidayDao {

    public boolean authenticate(HolidayDto authenParams) {
        try {
            HolidayDto userAuthentication = null;
            HolidayDto groupAuthentication = null;
            HolidayDto loginAuthentication = null;

            loginAuthentication = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.loginCheck", authenParams);

            if (loginAuthentication == null) {
                System.out.println("Not Logged IN");
                return false;
            } else {
                userAuthentication = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        System.out.println("Group Auth Failed");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HolidayDto getUserDetails(String empId) {
        System.out.println("Empppp" + empId);
        return (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getUserDetails", empId);
    }

    public List getProjectList(HolidayDto formData) {

        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getProjectList", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List getHolidayList(HolidayDto formData) {
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getHolidayList", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public void updateHolidayAllowance(HolidayDto formData) {
        HttpServletRequest requestObj = null;

        ArrayList<HolidayDto> connectionRes;
        connectionRes =  (ArrayList<HolidayDto>) getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData","10");
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();

        HolidayDto managerData = null;
        HolidayDto employeeData = null;
        SendMail mailObj = null;
        HolidayDto resultData = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HolidayDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        MailMessages mailMessageObj = new MailMessages();
        try {
            String hour = "";
            String minute = "";
            String[] temp;
            String access = formData.getAccessParameter();
            String alllowance_details = "";
            String statusType = null;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (access != null) {
                if (access.equalsIgnoreCase("EMPLOYEE")) {
                    temp = formData.getAllowance_date().split("-");
                    formData.setAllowance_date(temp[2] + "-" + temp[1] + "-" + temp[0]);
                    System.out.println("===========>" + formData.getAllowance_date());
                    formData.setCreated(dateFormat.format(calendar.getTime()));
                    formData.setModified(dateFormat.format(calendar.getTime()));

                    if (Integer.parseInt(formData.getSelHr()) <= 9) {
                        hour = "0" + formData.getSelHr();
                    } else {
                        hour = formData.getSelHr();
                    }
                    if (Integer.parseInt(formData.getSelMin()) <= 9) {
                        minute = "0" + formData.getSelMin();
                    } else {
                        minute = formData.getSelMin();
                    }
                    formData.setTotal_hours(hour + ":" + minute);

                    if (formData.getStatus().equals("m")) {
                        employeeData = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeRecord", formData.getEmployeeId());
                        managerData = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getManagerRecord", formData.getProject_id());
                        alllowance_details += "<ul>";
                        alllowance_details += "<li><b>Applied By:</b>  " + employeeData.getEmployeeName() + "</li>";
                        alllowance_details += "<li><b>Project:</b>  " + getSqlMapClientTemplate().queryForObject("queryMap.getProjectName", formData.getProject_id()) + "</li>";
                        alllowance_details += "<li><b>Total Hours:</b>  " + hour + " hours : " + minute + " minutes</li>";
                        alllowance_details += "<li><b>Allowance Date:</b>  " + temp[0] + "-" + temp[1] + "-" + temp[2] + "</li>";
                        alllowance_details += "<li><b>Reason:</b>  " + formData.getReason() + "</li>";
                        alllowance_details += "</ul>";
                    }

                    if (formData.getRequestId() == null || formData.getRequestId().equals("")) {
                        getSqlMapClientTemplate().insert("queryMap.addRequest", formData);
                    } else {
                        getSqlMapClientTemplate().update("queryMap.updateRequest", formData);
                    }
//             
                    System.out.println("alllowance_details" + alllowance_details);
                    System.out.println("alllowance_details" + formData.getStatus());
                    if(formData.getStatus().equals("m")){
                        System.out.println("alllowance_details mail");
                        String mailSubject = "New Holiday Allowance has been applied";
                        String mailMessage = mailMessageObj.getMessage(requestObj, alllowance_details, managerData.getManagerName(), employeeData.getEmployeeName(), "m", "EMPLOYEE");
                        mailObj.smtpMail(managerData.getManagerEmail(), mailSubject, mailMessage, employeeData.getEmployeeEmail());
                    }
                    
                }
                if (access.equalsIgnoreCase("RM")) {
                    formData.setModified(dateFormat.format(calendar.getTime()));
                    System.out.println("modified Date======"+formData.getModified());
                    resultData = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getRequestData", formData.getRequestId());
                    System.out.println("REsulrt Data----" + resultData);
                    if (formData.getStatus() != null) {
                        System.out.println("----------------" + formData.getStatus());
                        if (formData.getStatus().equalsIgnoreCase("a")) {
                            formData.setApproved_hours(formData.getTotal_hours());
                            getSqlMapClientTemplate().update("queryMap.pmUpdateStatus", formData);
                        } else {
                            getSqlMapClientTemplate().update("queryMap.pmUpdateRejectStatus", formData);
                            formData.setApproved_hours("---");
                        }
                    }
                    System.out.println("-------------------" + resultData.getApproved_hours() + "=======" + formData.getApproved_hours());
                    employeeData = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeRecord", resultData.getEmployeeId());
                    managerData = (HolidayDto) getSqlMapClientTemplate().queryForObject("queryMap.getManagerRecord", resultData.getProject_id());
                    alllowance_details += "<ul>";
                    alllowance_details += "<li><b>Applied By:</b>  " + employeeData.getEmployeeName() + "</li>";
                    alllowance_details += "<li><b>Project:</b> " + getSqlMapClientTemplate().queryForObject("queryMap.getProjectName", resultData.getProject_id()) + "</li>";
                    alllowance_details += "<li><b>Total Hours:</b>  " + resultData.getTotal_hours() + " </li>";
                    alllowance_details += "<li><b>Approved Hours:</b>  " + formData.getApproved_hours() + " </li>";
                    alllowance_details += "<li><b>Allowance Date:</b>  " + resultData.getAllowance_date() + "</li>";
                    alllowance_details += "<li><b>Reason:</b>  " + resultData.getReason() + "</li>";
                    if (formData.getRejectReason() != null) {
                        alllowance_details += "<li><b>Reason For Send back:</b>  " + formData.getRejectReason() + "</li>";
                    }
                    alllowance_details += "</ul>";
                    if (formData.getStatus() != null) {
                        if (formData.getStatus().equalsIgnoreCase("a")) {
                            statusType = "Approved";
                        } else {
                            statusType = "Send Back";
                        }
                    }
                    String mailSubject = "Holiday Allowance " + statusType;
//                System.out.println("--------------------FormData status-------" + formData.getStatus()+"allowancedetails"+alllowance_details+"emplname--"+employeeData.getEmployeeName()+"---managername---"+managerData.getManagerName());
                    String mailMessage = mailMessageObj.getMessage(requestObj, alllowance_details, employeeData.getEmployeeName(), managerData.getManagerName(), formData.getStatus(), "RM");
                    mailObj.smtpMail(employeeData.getEmployeeEmail(), mailSubject, mailMessage, managerData.getManagerEmail());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getAllowanceDetails(HolidayDto formData) {
        List<HolidayDto> template = null;
        String temp[];
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getAllowanceDetails", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public String getRecordCount(HolidayDto formData) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getRecordCount", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public Object getRequestData(String allowanceId) {
        Object template = null;
        try {
            template = getSqlMapClientTemplate().queryForObject("queryMap.getRequestData", allowanceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<DetailsDto> getEmployeeSearchList(String val) {
        List<DetailsDto> template = null;
        try {
            String key = val + "%";
            template = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeSearchList", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public String getEmployeeName(String val) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeName", val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List getTotalAllowanceDetails(HolidayDto formData) {
        List<HolidayDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getTotalAllowanceDetails", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public String getTotalRecordCount(HolidayDto formData) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTotalRecordCount", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List getWorkedDaysList(HolidayDto formData) {
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getWorkedDaysList", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    //For RM Approval
    public List getRMApprovalDetails(HolidayDto formData) {
        List<HolidayDto> template = null;
        try {
//            System.out.println("Forma Data********" + formData.getRequestStatus());
//            System.out.println("Serch Value********" + formData.getRequestList());
//            System.out.println("Forma Data Employee Id********" + formData.getEmployee_id());
            template = getSqlMapClientTemplate().queryForList("queryMap.getRMApprovalDetails", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<HolidayDto> getEmployeeList(String filterData) {
        List<HolidayDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<HolidayDto> getProjectName(HolidayDto formData) {
        List<HolidayDto> template = null;
        try {
            System.out.println("**************"+formData.getEmploId());
            template = getSqlMapClientTemplate().queryForList("queryMap.getProjectsName", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
//
//    public void updatePmStatus(HolidayDto formData) {
//        try {
//
//            getSqlMapClientTemplate().update("queryMap.pmUpdateStatus", formData);
//            System.out.println("--------------------FormData status-------" + formData.getStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String getPmRecordCount(HolidayDto formData) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPmRecordCount", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public String getPMTotalRecordCount(HolidayDto formData) {
        String template = null;
        try {
            System.out.println("--------------------------------------pmtotalcount method---"+formData.getRequestStatus());
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPMTotalRecordCount", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<HolidayDto> getConfigValues(String parentId) {
        List<HolidayDto> configvalue = null;
        try {
            configvalue = getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configvalue;
    }
}
