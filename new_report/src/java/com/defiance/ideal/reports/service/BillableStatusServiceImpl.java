/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.BillableStatusDao;
import com.defiance.ideal.reports.dto.BillableStatusDataDTO;
import com.defiance.ideal.reports.dto.BillableStatusFilterDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author 14053
 */
public class BillableStatusServiceImpl implements BillableStatusService {

    public BillableStatusDao billableDao;

    public BillableStatusDao getBillableDao() {
        return billableDao;
    }

    public void setBillableDao(BillableStatusDao billableDao) {
        this.billableDao = billableDao;
    }

    public Map<String, String> getSbuList() {
        return billableDao.getSbuList();
    }

    public List<BillableStatusDataDTO> fetchBillableAssociateData(BillableStatusFilterDTO filterData) {
        List<BillableStatusDataDTO> billableData = new ArrayList<BillableStatusDataDTO>();
        billableData = billableDao.fetchBillableAssociateData(filterData);
       
        for (int i = 0; i < billableData.size(); i++) {
            if(("r").equals(billableData.get(i).geteStat()) || ("b").equals(billableData.get(i).geteStat()) || ("t").equals(billableData.get(i).geteStat())){
//                System.out.println(" ---  "+billableData.get(i).getEmpNumber());
                billableData.get(i).setProjectCode("");
                billableData.get(i).setProjectName("");
                billableData.get(i).setCustName("");
                billableData.get(i).setBillStatus("");
            }
        }

        

//                <?php echo ($employee['Employee']['employment_status'] == 'r' || $employee['Employee']['employment_status'] == 'b' || $employee['Employee']['employment_status'] == 't')?"":$employee['Project']['project_code']; ?>
//        </td>
//		<td>
//            <?php echo ($employee['Employee']['employment_status'] == 'r' || $employee['Employee']['employment_status'] == 'b' || $employee['Employee']['employment_status'] == 't')?"":$employee['Project']['project_name']; ?>
//        </td>
//		<td>
//            <?php echo ($employee['Employee']['employment_status'] == 'r' || $employee['Employee']['employment_status'] == 'b' || $employee['Employee']['employment_status'] == 't')?"":$employee[0]['proj_status']; ?>





        return billableData;
    }
    

//    public List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData) {
//        List<ReportsDataDTO> associateData = null;
//        return associateData;
//    }

  

//    public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData) {
//        return accrualDao.fetchAccrualData(filterData);
//    }
}
