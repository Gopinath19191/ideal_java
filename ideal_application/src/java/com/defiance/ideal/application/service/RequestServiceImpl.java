/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.RequestorDAOImpl;
import com.defiance.ideal.application.dto.RequestorDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14355
 */
public class RequestServiceImpl implements RequestorService {

    public RequestorDAOImpl requestorDAOImpl;

    public RequestorDAOImpl getRequestorDAOImpl() {
        return requestorDAOImpl;
    }

    public void setRequestorDAOImpl(RequestorDAOImpl requestorDAOImpl) {
        this.requestorDAOImpl = requestorDAOImpl;
    }
    
    public List<RequestorDTO> getdashBoardList(String empId,String moduleId,String selectedValue,String list)
    {
        List<RequestorDTO> dashBoardList=null;
        try{
            dashBoardList = requestorDAOImpl.getdashBoardList(empId,moduleId,selectedValue,list);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return dashBoardList;
    }

    public void insertNewRequest(RequestorDTO requestformData) {
        try {
            requestorDAOImpl.insertNewRequest(requestformData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestorDTO getEmpDetails(String empId) {
        RequestorDTO empDetails = null;
        try {
            empDetails = requestorDAOImpl.getEmpDetails(empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empDetails;
    }

    public List<RequestorDTO> getFocusArea(String empId) {
        List<RequestorDTO> focusValues = null;
            try {
            focusValues = requestorDAOImpl.getFocusArea(empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return focusValues;
    }


//public void insertNewQuality(RequestorDTO requestformData) {
//        try {
//         requestorDAOImpl.insertNewQuality(requestformData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public String getRequestorName(String employeeId)
//    {
//        String requestorName = null;
//        try
//        {
//           requestorName = requestorDAOImpl.getRequestorName(employeeId);
//        } catch (Exception e) {
//        }
//         return requestorName;
//    }
    public void insertFile(RequestorDTO fileData)
    {
        try{
            requestorDAOImpl.insertFile(fileData);
        }
        catch(Exception e)
        {

            e.printStackTrace();
        }
    }
    
    public RequestorDTO getRequestDetails(String refId)
    {
        RequestorDTO individualRequestDetails = null;
        try{
            individualRequestDetails = requestorDAOImpl.getRequestDetails(refId);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return individualRequestDetails;
    }
    public int getLastInsert()
    {
        Integer lastInsertId = null;
        try{
            lastInsertId = requestorDAOImpl.getLastInsert();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return lastInsertId;
    }
    
    public RequestorDTO getFiledownload(String refId)
    {
        RequestorDTO fileDetails = null;
        try{
            fileDetails = requestorDAOImpl.getFiledownload(refId);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return fileDetails;
    }
    
    public void updateRequest(RequestorDTO formData)
    {
        try{
            requestorDAOImpl.updateRequest(formData);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
       public List<RequestorDTO> getEmployeeList(String empVal) {
        List<RequestorDTO> employeeList = null;
        try {
            employeeList = requestorDAOImpl.getEmployeeList(empVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }
       
       public void updateReferenceId(String refId,int id) {
       try{
          requestorDAOImpl.updateReferenceId(refId, id);            
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       }
       
       public RequestorDTO qualityInformation(String empId)
       {
           RequestorDTO qualityEmpDetails = null;
           try{
               qualityEmpDetails = requestorDAOImpl.qualityInformation(empId);
           }catch(Exception e)
           {
               e.printStackTrace();
           }
           return qualityEmpDetails;
       }
       
       public void triggerMailAction(RequestorDTO formData,String updatedRefId)
       {
           try{
               requestorDAOImpl.triggerMailAction(formData,updatedRefId);
           }catch(Exception e)
           {
               e.printStackTrace();
           }
       }
//  public List<RequestorDTO> getReferenceNo() {
//        List<RequestorDTO> referenceNo = null;
//        try {
//            System.out.println("INside 2---");
//            referenceNo = requestorDAOImpl.getReferenceNo();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return referenceNo;
//    }

//    public String getRequestorName(String employeeId) {
//        String requestorName = null;
//        try {
//           // requestorName = requestorDAOImpl.getRequestorName(employeeId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return requestorName;
//    }
}
