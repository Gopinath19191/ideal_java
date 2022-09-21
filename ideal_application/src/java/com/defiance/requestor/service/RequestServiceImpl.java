/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.requestor.service;

import com.defiance.requestor.dao.RequestorDAOImpl;
import com.defiance.requestor.dto.RequestorDTO;
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

  
  public List<RequestorDTO> getReferenceNo() {
        List<RequestorDTO> referenceNo = null;
        try {
            System.out.println("INside 2---");
            referenceNo = requestorDAOImpl.getReferenceNo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return referenceNo;
    }
public RequestorDTO getRequestDetails(String employeeId) {
        RequestorDTO requestDetails = null;
        try {
            //requestDetails = requestorDAOImpl.getRequestDetails(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestDetails;
    }
public void insertRequestDetails(RequestorDTO requestformData) {
        try {
         //  requestorDAOImpl.insertRequestDetails(requestformData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getRequestorName(String employeeId) {
        String requestorName = null;
        try {
           // requestorName = requestorDAOImpl.getRequestorName(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestorName;
    }
}
