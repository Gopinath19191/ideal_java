package com.defiance.ideal.qpd.admin.bo;

import com.defiance.ideal.qpd.admin.dao.AdminDAO;
import com.defiance.ideal.qpd.admin.db.AdminDBCTL;
import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import com.defiance.ideal.qpd.admin.dto.AdminFilterDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlImplementation;

/**
 *
 * @author Hariharan.C
 */
@ControlImplementation(isTransient = true)
public class AdminBOImpl implements AdminBO {

    @Control
    private AdminDAO controlObj;
    @Control
    private AdminDBCTL controlDBCTLObj;
    
    public AdminDTO[] getCompanyStructure() {

        AdminDTO[] structureDetails = null;

        try {
            structureDetails = controlObj.getStructureDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return structureDetails;
    }

    public AdminDTO[] filterEmployees(HttpServletRequest requestObj, AdminFilterDTO formData, String changeRequest) throws Exception{

        AdminDTO[] appraiseeData = null;
        String appraisalPeriod = formData.getAppraisalPeriodFilter();
        String appraisalYear = formData.getAppraisalYearFilter();
        String[] employeeStatuses = formData.getEmployeeStatusFilter();
        String[] companyStructures = formData.getCompanyStructureFilter();
        String appraiserData = formData.getAppraiserId();
        String[] bandData = formData.getBandDataFilter();
        String concatenatedStatus = "";
        String concatenatedStructure = "";
        String concatenatedBand = "";
        String dojCheck = "";
        int selectedAppraisalMonth = 0;
        Properties configFile = new Properties();
        configFile.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));

        if (appraisalPeriod == null) {
            appraisalPeriod = Integer.toString(CommonFunctions.getAppraisalQuarter(requestObj.getSession()));
        }
        if (appraisalYear == null) {
            appraisalYear = Integer.toString(CommonFunctions.getAppraisalYear(requestObj.getSession()));
        }

        if ((!("").equals(appraisalYear) && appraisalYear != null)
                || (!("").equals(appraisalPeriod) && appraisalPeriod != null)
                || (employeeStatuses != null)
                || (bandData != null)
                || (companyStructures != null)) {

            if (employeeStatuses != null) {
                int status;

                for (status = 0; status < employeeStatuses.length; status++) {
                    concatenatedStatus += "'" + employeeStatuses[status] + "',";
                    System.out.println("concatenatedStatus = " + concatenatedStatus);
                }
                concatenatedStatus = concatenatedStatus.substring(0, (concatenatedStatus.length() - 1));
                System.out.println("concatenatedStatus Final = " + concatenatedStatus);
            }

            if (companyStructures != null) {
                int structure;

                System.out.println("Company Structure" + companyStructures.length);
                for (structure = 0; structure < companyStructures.length; structure++) {
                    concatenatedStructure += "'" + companyStructures[structure] + "',";
                    System.out.println("concatenatedStructure = " + concatenatedStructure);
                }
                concatenatedStructure = concatenatedStructure.substring(0, (concatenatedStructure.length() - 1));
                System.out.println("concatenatedStructure Final = " + concatenatedStructure);
            }

            if (bandData != null) {
                int band;
                for (band = 0; band < bandData.length; band++) {
                    concatenatedBand += "'" + bandData[band] + "',";
                    System.out.println("concatenatedStructure = " + concatenatedBand);
                }
                concatenatedBand = concatenatedBand.substring(0, (concatenatedBand.length() - 1));
                System.out.println("concatenatedBand Final = " + concatenatedBand);
            }
            
            
            
            selectedAppraisalMonth = Integer.parseInt(configFile.getProperty("AA_MONTH"));
            
            System.out.println(" AA_ MONTH ======== >>>>>>>>> "+selectedAppraisalMonth);
            Calendar apprCalendar = Calendar.getInstance();
            //int selectedAppraisalMonth = 0;
//       selectedAppraisalMonth = (Integer.parseInt(appraisalPeriod)*3);

//            if (Integer.parseInt(appraisalPeriod) == 1) {
//                selectedAppraisalMonth = 4;}
//            } else if (Integer.parseInt(appraisalPeriod) == 2) {
//                selectedAppraisalMonth = 9;
//            } else if (Integer.parseInt(appraisalPeriod) == 3) {
//                selectedAppraisalMonth = 12;
//            } else if (Integer.parseInt(appraisalPeriod) == 4) {
//                selectedAppraisalMonth = 3;
//            }

            apprCalendar.set(Integer.parseInt(appraisalYear), selectedAppraisalMonth, 01);

            int lastDate = apprCalendar.getActualMaximum(Calendar.DATE);
            dojCheck = appraisalYear + "-" + selectedAppraisalMonth + "-" + lastDate;

           System.out.println("dojCheck ========>>>>>>>>>> " + dojCheck);
//            System.out.println("concatenatedBand = " + concatenatedBand);
            System.out.println("changeRequest = " + changeRequest);
            if (changeRequest == null) {
                appraiseeData = controlObj.filterEmployeeData(appraiserData, concatenatedBand, dojCheck, concatenatedStatus, concatenatedStructure, Integer.parseInt(appraisalYear), Integer.parseInt(appraisalPeriod));
            } else {
                appraiseeData = controlObj.filterEmployeeDataChangeReq(appraiserData, concatenatedBand, dojCheck, concatenatedStatus, concatenatedStructure, Integer.parseInt(appraisalYear), Integer.parseInt(appraisalPeriod));
            }


        }

        return appraiseeData;

    }

    public AdminDTO[] filterAppraiser(String changeRequest) {
        AdminDTO[] appraiseeData = null;
        System.out.println("changeRequest = " + changeRequest);
        try{
        if (changeRequest.equals("before")) {
            appraiseeData = controlDBCTLObj.getAppraiserDataBeforeTrigger();
        } else {
            appraiseeData = controlDBCTLObj.getAppraiserDataAfterTrigger();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return appraiseeData;

    }

    public void triggerAppraisal(AdminDTO formData, int empId) {
        controlObj.triggerAppraisal(formData, empId);
    }
    
    public AdminDTO[] getEmployeeName(String searchString) {
        AdminDTO[] employeeName = null;
        try {
            employeeName = controlObj.getEmployeeName(searchString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeName;
    }

    public void updateAppraisal(AdminDTO formData, int empId) {
        controlObj.updateAppraisal(formData, empId);
    }

    public AdminDTO[] getBandData() {

        AdminDTO[] bandDetails = null;

        try {
            bandDetails = controlObj.getBandData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bandDetails;
    }
}
