package com.defiance.ideal.qpd.admin.bo;

import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import com.defiance.ideal.qpd.admin.dto.AdminFilterDTO;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.ControlInterface;
/**
 *
 * @author Hariharan.C
 */

@ControlInterface
public interface AdminBO{

    public AdminDTO[] getCompanyStructure();
    public void triggerAppraisal(AdminDTO formData,int empId);
    
    public AdminDTO[] getEmployeeName(String searchString);
    public AdminDTO[] filterEmployees(HttpServletRequest requestObj,AdminFilterDTO formData, String changeRequest)throws Exception;
    public AdminDTO[] filterAppraiser(String changeRequest);
    public void updateAppraisal(AdminDTO formData, int parseInt);
    public AdminDTO[] getBandData();

}
