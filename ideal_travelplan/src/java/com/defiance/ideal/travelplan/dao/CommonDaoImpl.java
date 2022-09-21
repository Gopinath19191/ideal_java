/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.sql.Connection;


/**
 *
 * @author 14583
 */
public class CommonDaoImpl extends SqlMapClientDaoSupport implements CommonDao {

    public List<CommonDto> getDashBoardList(CommonDto formData) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getDashBoardList", formData);
    }
        
    public List<CommonDto> getAdvanceDashBoardList(CommonDto formData) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getAdvanceDashBoardList", formData);
    }

    public String getDashBoardCount(CommonDto formData) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getDashBoardCount", formData);
    }
    
    public String getAdvanceDashBoardCount(CommonDto formData) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getAdvanceDashBoardCount", formData);
    }

    public String getCustomerName(String customerId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getCustomerName", customerId);
    }

    public String getProjectName(String projectId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getProjectName", projectId);
    }

    public String getCountryName(String countryId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getCountryName",countryId);
    }

    public String getCityName(String cityId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getCityName",cityId);
    }

    public List<CommonDto> getAutocomplete(String value, String type) {
        List<CommonDto> result = null;
        value = '%' + value + '%';
        if (type.equals("city")) {
            result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getAutoCity", value);
        } else if(type.equals("employee")) {
            result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getAutoEmployee", value);
        }
        return result;
    }

    public CommonDto getEmployeeDetails(String employee_id) {
        CommonDto lt = null;
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getEmployeeDetails", employee_id);
    }
    
    public CommonDto getTpDetailsForAdvance(String tpId) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getTpDetailsForAdvance", tpId);
    }

    public List<CommonDto> getCustomerDetails() {
        List<CommonDto> result = null;
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getCustomerDetails");
        return result;
    }

    public List<CommonDto> getBUHApproverList(String buh_id) {
        List<CommonDto> result = null;
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getBUHApproverList",buh_id);
        return result;
    }


    public List<CommonDto> getProjects(String customer_id) {
        List<CommonDto> result = null;
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getProjects", customer_id);
        return result;
    }

    public Map getCurrencyList() {
        Map result = null;
        result =  getSqlMapClientTemplate().queryForMap("Common.getCurrencyList","","currency_id","currency_code");
        return result;
    }

    public Map getVisaList() {
        Map result = null;
        result =  getSqlMapClientTemplate().queryForMap("Common.getVisaList","","visaId","visaType");
        return result;
    }

    public List<CommonDto> getCountryList() {
        List<CommonDto> result = null;
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getCountryList");
        return result;
    }

    public CommonDto getStatusFlowInfo(CommonDto formData) {
        formData.setEmpBandId("#"+formData.getEmpBandId()+"#");
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getStatusFlowInfo", formData);
    }
    
    public CommonDto getAdvanceStatusFlowInfo(CommonDto formData) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getAdvanceStatusFlowInfo", formData);
    }

    public CommonDto getEmpDetails(CommonDto formData) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getEmpDetails", formData);
    }

    public List<CommonDto> getApprovalDashBoardList(CommonDto commonFormData) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getApprovalDashBoardList", commonFormData);
    }

     public List<CommonDto> getApprovalDashBoardListForExport(CommonDto commonFormData) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getApprovalDashBoardListForExport", commonFormData);
    }



    public int getApprovalDashBoardCount(CommonDto commonFormData) {
        System.out.println("::::" + commonFormData.getModuleId() + "---" + commonFormData.getApproveType() + commonFormData.getTravelActionCode());
        return (Integer) getSqlMapClientTemplate().queryForObject("Common.getApprovalDashBoardCount", commonFormData);
    }

    public CommonDto getApprovalStatusFlowInfo(CommonDto commonFormData) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getApprovalStatusFlowInfo", commonFormData);
    }

    public void updateApprovalStatus(CommonDto commonFormData) {
        getSqlMapClientTemplate().update("Common.updateApprovalStatus", commonFormData);
    }

    public String getRejectStatus(String rejectCode) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getRejectStatus", rejectCode);
    }

    public String getNextLevelEmpId(CommonDto commonFormData) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getNextLevelEmpId", commonFormData);
    }
    
    public String getRMApprover(String tp_id) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getRMApprover", tp_id);
    }
    
     public String getBuhName(String buh_id) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getBuhName", buh_id);
    }

    public List<CommonDto> getTravelHotelData(String master_id) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getTravelHotelData", master_id);
    }

    public List<CommonDto> getTravelTicketData(String master_id) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getTravelTicketData", master_id);
    }

    public List<CommonDto> getTravelConveyanceData(String master_id) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getTravelConveyanceData", master_id);
    }

    public CommonDto getTravelVisa(String master_id) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getTravelVisa", master_id);
    }

    public CommonDto getTravelData(String travel_id) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getTravelData", travel_id);
    }

    public String getLastRequestId() {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getLastRequestId");
    }

    public String updateTravelData(HttpServletRequest request, HttpServletResponse response, CommonDto formData) throws IOException {
        String last_id = null;
        if (formData.getMaster_id() != null && !formData.getMaster_id().equals("")) {
            last_id = formData.getMaster_id();
            if("".equals(formData.getTravel_other_country())){
                formData.setTravel_other_country(null);
            }
            System.out.println("travel_other_country : "+formData.getTravel_other_country());
            getSqlMapClientTemplate().update("Common.updateTravel", formData);
        } else {
            System.out.println("formData==========="+formData.getAdmin_action_required());
            if("".equals(formData.getTravel_other_country())){
                formData.setTravel_other_country(null);
            }
            System.out.println("formData==========="+formData.getTravel_other_country());
            last_id = (String) getSqlMapClientTemplate().insert("Common.insertTravel", formData);
            formData.setMaster_id(last_id);
            System.out.println("last_id===========>" + last_id);
        }

        if(formData.getTicket_id().length > 0 ) {
            for(int k=0;k<formData.getTicket_id().length;k++) {
                if(!formData.getTravel_preference()[k].equals("") && !formData.getTravel_mode()[k].equals("") && formData.getTravel_date()[k] != null ) {
                    CommonDto outData = new CommonDto();
                    outData.setMaster_id(formData.getMaster_id());
                    if(!formData.getFrom_city_id()[k].equals("") && !formData.getFrom_city_id()[k].equals("0")) {
                        outData.setOut_from_city_id(formData.getFrom_city_id()[k]);
                        outData.setOut_from_city("");
                    } else {
                        outData.setOut_from_city_id("");
                        outData.setOut_from_city(formData.getFrom_city()[k]);
                    }
                    if(!formData.getTo_city_id()[k].equals("") && !formData.getTo_city_id()[k].equals("0")) {
                        outData.setOut_to_city_id(formData.getTo_city_id()[k]);
                        outData.setOut_to_city("");
                    } else {
                        outData.setOut_to_city_id("");
                        outData.setOut_to_city(formData.getTo_city()[k]);
                    }
                    if("".equals(formData.getFrom_country()[k])){
                        outData.setOut_from_country("0");
                    }else{
                    outData.setOut_from_country(formData.getFrom_country()[k]);
                    }
                    if("".equals(formData.getTo_country()[k])){
                        outData.setOut_to_country("0");
                    }else{
                    outData.setOut_to_country(formData.getTo_country()[k]);
                    }
                    outData.setOut_travel_preference(formData.getTravel_preference()[k]);
                    outData.setOut_travel_mode(formData.getTravel_mode()[k]);
                    outData.setOut_ticket_remarks(formData.getTicket_remarks()[k]);
                    outData.setOut_ticketdeleted(formData.getTicketdeleted()[k]);
                    outData.setTravel_ticket_date(formData.getTravel_date()[k]);
                    outData.setOut_ticket_id(formData.getTicket_id()[k]);
                    if("".equals(outData.getOut_to_city_id())){
                        outData.setOut_to_city_id("0");
                    }

                    if(formData.getTicket_id()[k] != null && !formData.getTicket_id()[k].equals("")) {
                        if("".equals(outData.getOut_from_city_id()) || outData.getOut_from_city_id() == null){
                            outData.setOut_from_city_id("0");
                        }
                        getSqlMapClientTemplate().update("Common.updateTicket", outData);
                    } else {
                        if("".equals(outData.getOut_from_city_id()) || outData.getOut_from_city_id() == null){
                            outData.setOut_from_city_id("0");
                        }
                        System.out.println("From City "+outData.getOut_from_city_id());
                        getSqlMapClientTemplate().insert("Common.insertTicket", outData);
                    }
                }
            }
        }

        if(formData.getHotel_id().length > 0 ) {
            for(int k=0;k<formData.getHotel_id().length;k++) {
                if(formData.getFrom_date()[k] != null && formData.getTo_date()[k] != null && !formData.getLocation()[k].equals("") ) {
                    CommonDto outData = new CommonDto();
                    outData.setMaster_id(formData.getMaster_id());
                    if(!formData.getCity_id()[k].equals("") && !formData.getCity_id()[k].equals("0")) {
                        outData.setOut_city_id(formData.getCity_id()[k]);
                        outData.setOut_city("");
                    } else {
                        outData.setOut_city_id("");
                        outData.setOut_city(formData.getCity()[k]);
                    }
                    outData.setOut_country(formData.getCountry()[k]);
                    outData.setOut_location(formData.getLocation()[k]);
                    outData.setHotel_from_date(formData.getFrom_date()[k]);
                    outData.setHotel_to_date(formData.getTo_date()[k]);
                    outData.setOut_hotel_remarks(formData.getHotel_remarks()[k]);
                    outData.setOut_hoteldeleted(formData.getHoteldeleted()[k]);
                    outData.setOut_hotel_id(formData.getHotel_id()[k]);

                    if(formData.getHotel_id()[k] != null && !formData.getHotel_id()[k].equals("")) {
                        getSqlMapClientTemplate().update("Common.updateHotel", outData);
                    } else {
                        getSqlMapClientTemplate().insert("Common.insertHotel", outData);
                    }
                }
            }
        }

        if(formData.getConveyance_id() != null && formData.getConveyance_id().length > 0 ) {
            System.out.print("length"+formData.getConveyance_id().length);
            for(int k=0;k<formData.getConveyance_id().length;k++) {
                System.out.print("k"+k);
                System.out.println("formData.getStart_date()"+formData.getStart_date());
                System.out.println("formData.getEnd_date()"+formData.getEnd_date());
                if(formData.getStart_date()[k] != null && formData.getEnd_date()[k] != null && !formData.getPickup()[k].equals("") && !formData.getDropto()[k].equals("") ) {
                    CommonDto outData = new CommonDto();
                    outData.setMaster_id(formData.getMaster_id());
                    if(!formData.getConveyance_city_id()[k].equals("") && !formData.getConveyance_city_id()[k].equals("0")) {
                        outData.setOut_conveyance_city_id(formData.getConveyance_city_id()[k]);
                        outData.setOut_conveyance_city("");
                    } else {
                        outData.setOut_conveyance_city_id("");
                        outData.setOut_conveyance_city(formData.getConveyance_city()[k]);
                    }
                    System.out.print("countries"+formData.getCountry());
                    outData.setOut_conveyance_country(formData.getConveyance_country()[k]);
                    outData.setOut_pickup(formData.getPickup()[k]);
                    outData.setOut_dropto(formData.getDropto()[k]);
                    outData.setConveyance_start_date(formData.getStart_date()[k]);
                    outData.setConveyance_end_date(formData.getEnd_date()[k]);
                    outData.setOut_conveyance_remarks(formData.getConveyance_remarks()[k]);
                    outData.setOut_conveyancedeleted(formData.getConveyancedeleted()[k]);
                    outData.setOut_conveyance_id(formData.getConveyance_id()[k]);

                    outData.setOut_travel_time(formData.getTime_hr()[k]+":"+formData.getTime_min()[k]);
                    if(formData.getConveyance_id()[k] != null && !formData.getConveyance_id()[k].equals("")) {
                        getSqlMapClientTemplate().update("Common.updateConveyance", outData);
                    } else {
                        getSqlMapClientTemplate().insert("Common.insertConveyance", outData);
                    }
                }
            }
        }

        uploadFile(request, formData);
		
        if (formData.getTravelType().equals("I")) {
            if (formData.getVisa_id() != null && !formData.getVisa_id().equals("")) {
                if("".equals(formData.getCountry_issue()) || formData.getCountry_issue() == null){
                    formData.setCountry_issue("0");
                }
                getSqlMapClientTemplate().update("Common.updateVisa", formData);
            } else {
                if("".equals(formData.getCountry_issue()) || formData.getCountry_issue() == null){
                    formData.setCountry_issue("0");
                }
                getSqlMapClientTemplate().insert("Common.insertVisa", formData);
            }
        }
        return last_id;
    }
    
    public void updateTravelAdvanceData(HttpServletRequest request, HttpServletResponse response, CommonDto formData) throws IOException {
        if (formData.getAdvanceTpId() != null && !formData.getAdvanceTpId().equals("")) {
            //getSqlMapClientTemplate().update("Common.updateTravelAdvance", formData);
            System.out.println("Record Updated....");
        } else {
            getSqlMapClientTemplate().insert("Common.insertTravelAdvance", formData);
            System.out.println("Record Inserted....");
        }
      //  uploadFile(request, formData);
    }

    public void uploadFile(HttpServletRequest request, CommonDto formData) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        if(request.getParameter("attachmentCount") != null) {
            for (int k = 1; k <= Integer.parseInt(request.getParameter("attachmentCount")); k++) {
                if(request.getParameter("attachmentdeleted_"+k).equals("0")) {
                    MultipartFile files = multipartRequest.getFile("attach_doc_"+k);
                    if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                        String file_name = createdDate.getTime() + "-" + formData.getMaster_id() + "-" + files.getOriginalFilename();
                        uploadData.put("file_name", file_name);
                        uploadData.put("created_date", createdDate);
                        uploadData.put("master_id", formData.getMaster_id());
                        File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
                        files.transferTo(path);
                        getSqlMapClientTemplate().insert("Common.saveAttachment", uploadData);
                    }
                }
            }
        }
        String attach_count = request.getParameter("attach_count");
        if(Integer.parseInt(attach_count) > 0 ) {
            for (int k = 1; k <= Integer.parseInt(attach_count); k++) {
                if(!request.getParameter("attach_del_"+k).equals("0") ) {
                    getSqlMapClientTemplate().update("Common.updateAttachment", request.getParameter("attachment_id_"+k));
                }
            }
        }
//        List<MultipartFile> files = multipartRequest.getFiles("attach_doc");
//        for (int k = 0; k < files.size(); k++) {
//            if (!files.get(k).getOriginalFilename().equals("") && files.get(k).getSize() > 0) {
//                String file_name = formData.getMaster_id() + "-" + files.get(k).getOriginalFilename();
//                uploadData.put("file_name", file_name);
//                uploadData.put("created_date", createdDate);
//                uploadData.put("master_id", formData.getMaster_id());
//                File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
//                files.get(k).transferTo(path);
//                getSqlMapClientTemplate().insert("Common.saveAttachment", uploadData);
//            }
//        }
    }

    public List<CommonDto> getTravelAttachment(String travel_id) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getTravelAttachment", travel_id);
    }

    public void updateFinanceToCfo(CommonDto commonFormData) {
        getSqlMapClientTemplate().update("Common.updateFinanceToCfo", commonFormData);
    }

    public List<CommonDto> getNationalityList() {
        List<CommonDto> result = null;
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getNationalityList");
        return result;
    }

    public CommonDto getEmpGenericDetails(String empId) {
        CommonDto empGenericDetails = null;
        empGenericDetails = (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getEmpGenericDetails", empId);
        return empGenericDetails;

    }

    public CommonDto getGenericDetails(String empId) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getGenericDetails", empId);
    }
    
    public String getEmployeeName(String empId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getEmployeeName", empId);
    }

    public void insertEmpGenericDetails(CommonDto genericDto) {
        CommonDto genericId = (CommonDto) getSqlMapClientTemplate().queryForObject("Common.checkGenericId", genericDto.getEmpId());
        if (genericId != null) {
            getSqlMapClientTemplate().update("Common.updateEmpGenericDetails", genericDto);
            getSqlMapClientTemplate().update("Common.updateGenericDetails", genericDto);
        } else {
            getSqlMapClientTemplate().insert("Common.insertEmpGenericDetails", genericDto);
            getSqlMapClientTemplate().update("Common.updateGenericDetails", genericDto);
        }
    }
    
    public CommonDto getTravelDetails(String empId) {
        return (CommonDto) getSqlMapClientTemplate().queryForObject("Common.getTravelDetails", empId);
    }

    public List<CommonDto> getConfigValueData(String empId) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getConfigValueData", empId);
    }

    public List<CommonDto> getApprovers(CommonDto travelData) {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getApprovers", travelData);
    }

    public String getApproverEmail(String empId) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getApproverEmail", empId);
    }

    public void updateTreasuryDetails(CommonDto travelData) {
        getSqlMapClientTemplate().update("Common.updateTreasuryDetails", travelData);
    }

    public String getEmployee(String employee_id) {
        return (String) getSqlMapClientTemplate().queryForObject("Common.getEmployee", employee_id);
    }

    public List<CommonDto> getDisclaimer() {
        return (List<CommonDto>) getSqlMapClientTemplate().queryForList("Common.getDisclaimer");
    }
     public Connection getConnectionObject() throws Exception{        
        return getSqlMapClientTemplate().getSqlMapClient().getDataSource().getConnection(); 
    }

    public String getBuhid(String group_id) {
        String buhid = "";
        buhid = (String) getSqlMapClientTemplate().queryForObject("Common.getBuhid", group_id);
        return buhid;
    }

}
