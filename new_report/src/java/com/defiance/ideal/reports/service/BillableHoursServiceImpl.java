/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.BillableHoursDao;
import com.defiance.ideal.reports.dto.BillableHoursDataDTO;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author 14517
 */
public class BillableHoursServiceImpl implements BillableHoursService {

    public BillableHoursDao billableHoursDao;

    public BillableHoursDao getBillableHoursDao() {
        return billableHoursDao;
    }

    public void setBillableHoursDao(BillableHoursDao billableHoursDao) {
        this.billableHoursDao = billableHoursDao;
    }

    public Map<String, String> getSbuList() {
        return billableHoursDao.getSbuList();
    }

    public Map<String, String> getCustomerList() {
        return billableHoursDao.getCustomerList();
    }

    public List getProjectList(String sbuId,String subSbuId) {
        List<BillableHoursFilterDTO> projectList = billableHoursDao.getProjectList(sbuId,subSbuId);
//        String mainStr="";
//        for(int i=0;i<projectList.size();i++){
//            //System.out.println("%%%%%%%%%%%"+projectList.size()+"******"+i);
//            if(i!=projectList.size()-1){
//            mainStr=mainStr+"\""+projectList.get(i).getProjId()+"\""+",";
//            }else{
//                mainStr=mainStr+"\""+projectList.get(i).getProjId()+"\"";
//            }
//        }
      //  System.out.println("$$$$$$$$$$"+mainStr);
      return projectList;
    }

    public List<BillableHoursDataDTO> fetchBillableHoursData(BillableHoursFilterDTO filterData) {
        List<BillableHoursDataDTO> billableData = new ArrayList<BillableHoursDataDTO>();
        billableData = billableHoursDao.fetchBillableHoursData(filterData);
        System.out.println(":DATA:"+billableData.size());

        try {
            if (billableData != null) {
                for (int i = 0; i < billableData.size(); i++) {
                    if (billableData.get(i).getAccrued() == null || billableData.get(i).getAccrued().equals("00:00") || billableData.get(i).getAccrued().equals("0:00") || billableData.get(i).getAccrued().equals("")) {
                        billableData.get(i).setAccrued("0");
                    }
                    if(billableData.get(i).getAccruedStatus()!=null){
                    if(billableData.get(i).getAccruedStatus().equals("a")){
                        billableData.get(i).setInvoicedHrs(billableData.get(i).getAccrued());
                    }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("EX" + ex.getMessage());
        }

        return billableData;
    }
}
