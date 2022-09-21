/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import com.defiance.ideal.travelplan.dao.DomesticTravelDaoImpl;
import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.dto.DomesticTravelDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.CommonFunctions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class DomesticTravelController extends MultiActionController {

    public synchronized ModelAndView getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv;
        mv = new ModelAndView("/common/domesticTravel");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String employee_id = (String) session.getAttribute("employeeId");
        List<DomesticTravelDto> list = dao.getList(employee_id);
        mv.addObject("list", list);
        return mv;
    }

    public synchronized ModelAndView addDomesticTravel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv;
        mv = new ModelAndView("/common/addDomesticTravel");
        DomesticTravelDto employeeDetails = new DomesticTravelDto();
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String employee_id = (String) session.getAttribute("employeeId");
        employeeDetails = dao.getEmployeeDetails(employee_id);
        List<DomesticTravelDto> buhList = dao.getBuhList();
        List<DomesticTravelDto> customerList = dao.getCustomerList(employee_id);
        List<DomesticTravelDto> projectList = dao.getProjectList(employee_id);
        List<DomesticTravelDto> settlementType = dao.getSettlementType();
        List<DomesticTravelDto> bookingType = dao.getBookingType();
        mv.addObject("employee_details", employeeDetails);
        mv.addObject("buh_list", buhList);
        mv.addObject("customer_list", customerList);
        mv.addObject("project_list", projectList);
        mv.addObject("settlementPolicy", settlementType);
        mv.addObject("bookingType", bookingType);
        return mv;
    }

    public synchronized ModelAndView submitDomestic(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto formData) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        int admin_action = 0;
        int deviation = 0;
        int treasury_action = 0;
        String india_lastId = dao.getLastRequestId();
        Date date = new Date();
        List<String> band_AL= dao.getbandList();
        if(formData.getStatus().equals("1")){
            String tp_request_id = CommonFunctions.getRequestId(india_lastId, "DT", date);
            formData.setTp_reference_id(tp_request_id);
        }else{
            formData.setTp_reference_id("");
        }
        
        if (formData.getTicket_booking_type().length > 0) {
            for (int k = 0; k < formData.getTicket_booking_type().length; k++) {
                if (formData.getTicket_booking_type()[k].equals("co")) {
                    admin_action ++;
                }
                if(formData.getTravel_mode()[k].equals("Air") && formData.getTicket_booking_type()[k].equals("co")){
                    if(band_AL.contains(formData.getBand_id())){
                        deviation++;
                    }
                }
            }
        }
        if(formData.getHotel_booking_type().length>0){
            for(int j=0;j<formData.getHotel_booking_type().length;j++){
                if (formData.getHotel_booking_type()[j].equals("co")) {
                    admin_action ++;
                }
            }
        }
        if(Integer.parseInt(formData.getCab_id())>0){
            admin_action ++;
        }
        for(int i=0;i<formData.getArr_advance_date().length;i++){
            if(!formData.getArr_advance_amount()[i].equals("") && !formData.getArr_advance_amount()[i].equals(null)){
                if(Integer.parseInt(formData.getArr_advance_amount()[i]) > 0){
                    treasury_action++;
                }
            }
        }
        
        if(admin_action>0){
            formData.setAdmin_action("Y");
        }else{
            formData.setAdmin_action("N");
        }
        if(deviation>0){
            formData.setDeviation("Y");
        }else{
            formData.setDeviation("N");
        }
        if(treasury_action>0){
            formData.setTreasury_action("Y");
        }else{
            formData.setTreasury_action("N");
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf1.parse(formData.getTravel_start_date());
        formData.setTravel_start_date(sdf.format(fromDate));
        Date toDate = sdf1.parse(formData.getTravel_end_date());
        formData.setTravel_end_date(sdf.format(toDate));
        Date requestDate = sdf1.parse(formData.getRequested_date());
        formData.setRequested_date(sdf.format(requestDate));
        String employee_rm = dao.getRMId(formData.getEmployee_id());
        System.out.println("manger id "+employee_rm);
        if(formData.getEmployee_id().equals(employee_rm) && formData.getStatus().equals("1")){
            formData.setStatus("6");
        }
        if(formData.getEmployee_id().equals(formData.getRm_id()) && formData.getStatus().equals("1")){
            formData.setStatus("2");
        }
        String last_id = dao.insertTravel(request, response, formData);
        
        if(last_id!=null && (formData.getStatus().equals("1") || formData.getStatus().equals("6") || formData.getStatus().equals("2"))){
            dao.mailSendProcess(formData, "0");
        }
        mv = new ModelAndView("redirect:getList.htm");
        return mv;
    }
    
    public synchronized ModelAndView citySearch(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv;
        mv = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
            String val = request.getParameter("q");
            String type = request.getParameter("type");
            List<CommonDto> result = dao.getAutocomplete(val,type);
            mv.addObject("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public synchronized ModelAndView viewTravelDetails(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv;
        mv = new ModelAndView("/common/viewTravelDetails");
        DomesticTravelDto travelDetails = new DomesticTravelDto();
//        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String master_id = (String) request.getParameter("master_id");
        travelDetails = dao.getTravelDetails(master_id);
        List<DomesticTravelDto> advanceDetails = dao.getAdvanceDetails(master_id);
        List<DomesticTravelDto> ticketDetails = dao.getTicketDetails(master_id);
        List<DomesticTravelDto> hotelDetails = dao.getHotelDetails(master_id);
        List<DomesticTravelDto> cabDetails = dao.getCabDetails(master_id);
        mv.addObject("travel_details",travelDetails);
        mv.addObject("advance_details",advanceDetails);
        mv.addObject("ticket_details",ticketDetails);
        mv.addObject("hotel_details",hotelDetails);
        mv.addObject("cab_details",cabDetails);
        return mv;
    }
    
    public synchronized ModelAndView listPendingApprovers(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv;
        mv = new ModelAndView("/common/approverList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String employee_id = (String) session.getAttribute("employeeId");
        String approver_type = (String) request.getParameter("approver_type");
        String list_type = (String) request.getParameter("type");
        DomesticTravelDto dto = new DomesticTravelDto();
        dto.setEmployee_id(employee_id);
        dto.setApprover_type(approver_type);
        dto.setList_type(list_type);
        List<DomesticTravelDto> approver_list = null;
        if(approver_type.equals("rm")){
            approver_list = dao.getRMList(dto);
        }else if(approver_type.equals("buh")){
            approver_list = dao.getBUHList(dto);
        }else if(approver_type.equals("finance")){
            approver_list = dao.getFinanceList(dto);
        }else if(approver_type.equals("admin")){
            approver_list = dao.getAdminList(dto);
        }else if(approver_type.equals("treasury")){
            approver_list = dao.getTreasuryList(dto);
        }else{
        
        }
        mv.addObject("travel_details",approver_list);
        mv.addObject("approver_type",approver_type);
        mv.addObject("type",list_type);
        return mv;
    }
    
    public synchronized ModelAndView approverTravelView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv;
        mv = new ModelAndView("/common/approverTravelView");
        DomesticTravelDto travelDetails = new DomesticTravelDto();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String master_id = (String) request.getParameter("master_id");
        String approver_type = request.getParameter("approver_type");
        String list_type = (String) request.getParameter("type");
        travelDetails = dao.getTravelDetails(master_id);
        List<DomesticTravelDto> advanceDetails = dao.getAdvanceDetails(master_id);
        List<DomesticTravelDto> ticketDetails = dao.getTicketDetails(master_id);
        List<DomesticTravelDto> hotelDetails = dao.getHotelDetails(master_id);
        List<DomesticTravelDto> cabDetails = dao.getCabDetails(master_id);
        List<DomesticTravelDto> buhList = dao.getBuhList();
        List<DomesticTravelDto> settlementType = dao.getSettlementType();
        List<DomesticTravelDto> bookingType = dao.getBookingType();
        List<String> attachment_type = dao.getAttachments(master_id);
//        List<DomesticTravelDto> currencyList = dao.getCurrency();
        mv.addObject("travel_details",travelDetails);
        mv.addObject("advance_details",advanceDetails);
        mv.addObject("ticket_details",ticketDetails);
        mv.addObject("hotel_details",hotelDetails);
        mv.addObject("cab_details",cabDetails);
        mv.addObject("buh_list",buhList);
        mv.addObject("settlementPolicy",settlementType);
        mv.addObject("bookingType",bookingType);
        mv.addObject("approver_type",approver_type);
        mv.addObject("type",list_type);
        mv.addObject("attachment_type", attachment_type);
        return mv;
    }
    
    public synchronized ModelAndView approveDomestic(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto formData) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        if(formData.getApprover_type().equals("rm")){
            String rm_id = (String) session.getAttribute("employeeId");
            formData.setRm_id(rm_id);
            dao.updateRmApproval(formData);
            DomesticTravelDto dto = new DomesticTravelDto();
            dto = dao.getApproverDetails(formData.getMaster_id());
            if(rm_id.equals(dto.getBuh_id()) && formData.getDeviation().equals("Y")){
                String buh_id = (String) session.getAttribute("employeeId");
                formData.setBuh_id(buh_id);
                formData.setBuh_remarks(formData.getRm_remarks());
                formData.setStatus("4");
                dao.updateBuhApproval(formData);
            }
            if(dto.getEmployee_id().equals(dto.getBuh_id()) && formData.getDeviation().equals("Y")){
                formData.setBuh_id(dto.getBuh_id());
                formData.setBuh_remarks("Auto Approved");
                formData.setStatus("4");
                dao.updateBuhApproval(formData);
            }
        }
        if(formData.getApprover_type().equals("buh")){
            String buh_id = (String) session.getAttribute("employeeId");
            formData.setBuh_id(buh_id);
            dao.updateBuhApproval(formData);
        }
        if(formData.getApprover_type().equals("finance")){
            String finance_id = (String) session.getAttribute("employeeId");
            formData.setFinance_id(finance_id);
            dao.updateFinanceApproval(formData);
        }
        if(formData.getApprover_type().equals("admin")){
            String admin_id = (String) session.getAttribute("employeeId");
            formData.setAdmin_id(admin_id);
            dao.updateAdminApproval(formData);
            String[] attachment_file_name = dao.updateTicketDetails(request, formData);
            formData.setTravel_preference(attachment_file_name);
        }
        if(formData.getApprover_type().equals("treasury")){
            String treasury_id = (String) session.getAttribute("employeeId");
            
//            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date fromDate = sdf1.parse(formData.getDeposited_date());
//            formData.setDeposited_date(sdf.format(fromDate));
            formData.setTreasury_id(treasury_id);
            dao.updateTreasuryApproval(formData);
            dao.updateAdvanceDetails(formData);
        }
        dao.mailSendProcess(formData, "0");
        String type=formData.getList_type();
        String approver_type=formData.getApprover_type();
        mv = new ModelAndView("redirect:listPendingApprovers.htm?type="+type+"&approver_type="+approver_type);
        return mv;
    }
    public synchronized ModelAndView editDomesticTravel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv;
        mv = new ModelAndView("/common/editDomesticTravel");
        DomesticTravelDto employeeDetails = new DomesticTravelDto();
        DomesticTravelDto travelDetails = new DomesticTravelDto();
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String employee_id = (String) session.getAttribute("employeeId");
        String master_id = (String) request.getParameter("master_id");
        String cancel_status="0";
        String end_date_edit="0";
        String advance_deposited = "0";
        String settlement_add = "0";
        employeeDetails = dao.getEmployeeDetails(employee_id);
        travelDetails = dao.getTravelDetails(master_id);
        List<DomesticTravelDto> buhList = dao.getBuhList();
        List<DomesticTravelDto> customerList = dao.getCustomerList(employee_id);
        List<DomesticTravelDto> projectList = dao.getProjectList(employee_id);
        List<DomesticTravelDto> settlementType = dao.getSettlementType();
        List<DomesticTravelDto> bookingType = dao.getBookingType();
        List<DomesticTravelDto> advanceDetails = dao.getAdvanceDetails(master_id);
        List<DomesticTravelDto> ticketDetails = dao.getTicketDetails(master_id);
        List<DomesticTravelDto> hotelDetails = dao.getHotelDetails(master_id);
        List<DomesticTravelDto> cabDetails = dao.getCabDetails(master_id);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date fromDate = sdf1.parse(travelDetails.getTravel_start_date());
        Date toDate = sdf1.parse(travelDetails.getTravel_end_date());
        Date date1 = sdf.parse(sdf.format(fromDate));
        Date date_end = sdf.parse(sdf.format(toDate));
        String curentDate = sdf.format(date);
        Date date2 = sdf.parse(curentDate);
        if(date1.compareTo(date2)<0){
            cancel_status = "1";
        }
        if(date_end.compareTo(date2)<0){
            end_date_edit = "1";
        }
        if(date_end.compareTo(date2)<0){
            settlement_add = "1";
        }
        if(travelDetails.getTreasury_approved_date() != null){
            advance_deposited="1";
        }
        mv.addObject("employee_details", employeeDetails);
        mv.addObject("buh_list", buhList);
        mv.addObject("customer_list", customerList);
        mv.addObject("project_list", projectList);
        mv.addObject("settlementPolicy", settlementType);
        mv.addObject("bookingType", bookingType);
        mv.addObject("travel_details",travelDetails);
        mv.addObject("advance_details",advanceDetails);
        mv.addObject("ticket_details",ticketDetails);
        mv.addObject("hotel_details",hotelDetails);
        mv.addObject("cab_details",cabDetails);
        mv.addObject("cancel_status",cancel_status);
        mv.addObject("advance_deposited",advance_deposited);
        mv.addObject("settlement_add", settlement_add);
        mv.addObject("end_date_edit", end_date_edit);
        return mv;
    }
    
    public synchronized ModelAndView updateDomestic(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto formData) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        int admin_action = 0;
        int deviation = 0;
        int treasury_action = 0;
        String india_lastId = dao.getLastRequestId();
        List<String> band_AL= dao.getbandList();
        Date date = new Date();
        if(formData.getTp_reference_id().equals("") || formData.getTp_reference_id().equals(null)){
            String tp_request_id = CommonFunctions.getRequestId(india_lastId, "DT", date);
            formData.setTp_reference_id(tp_request_id);
        }
        
        if (formData.getTicket_booking_type().length > 0) {
            for (int k = 0; k < formData.getTicket_booking_type().length; k++) {
                if (formData.getTicket_booking_type()[k].equals("co")) {
                    admin_action ++;
                }
                if(formData.getTravel_mode()[k].equals("Air") && formData.getTicket_booking_type()[k].equals("co")){
                    if(band_AL.contains(formData.getBand_id())){
                        deviation++;
                    }
                }
            }
        }
        if(formData.getHotel_booking_type().length>0){
            for(int j=0;j<formData.getHotel_booking_type().length;j++){
                if (formData.getHotel_booking_type()[j].equals("co")) {
                    admin_action ++;
                }
            }
        }
        if(Integer.parseInt(formData.getCab_id())>0){
            admin_action ++;
        }
        for(int i=0;i<formData.getArr_advance_date().length;i++){
            if(!formData.getArr_advance_amount()[i].equals("") && !formData.getArr_advance_amount()[i].equals(null)){
                if(Integer.parseInt(formData.getArr_advance_amount()[i]) > 0){
                    treasury_action++;
                }
            }
        }
        
        if(admin_action>0){
            formData.setAdmin_action("Y");
        }else{
            formData.setAdmin_action("N");
        }
        if(deviation>0){
            formData.setDeviation("Y");
        }else{
            formData.setDeviation("N");
        }
        if(treasury_action>0){
            formData.setTreasury_action("Y");
        }else{
            formData.setTreasury_action("N");
        }
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = sdf1.parse(formData.getTravel_start_date());
        formData.setTravel_start_date(sdf.format(fromDate));
        Date toDate = sdf1.parse(formData.getTravel_end_date());
        System.out.println("to date "+toDate);
        formData.setTravel_end_date(sdf.format(toDate));
        Date requestDate = sdf1.parse(formData.getRequested_date());
        formData.setRequested_date(sdf.format(requestDate));
        
        String status = formData.getStatus();
        String previous_status = formData.getPrevious_status();
        System.out.println("status "+status);
        if(status.equals("1")){
            String last_id = dao.insertTravel(request, response, formData);
            dao.mailSendProcess(formData, previous_status);
        }else{
            dao.cancelTravel(formData);
            dao.mailSendProcess(formData, previous_status);
        }
        
        
//        if(last_id!=null){
//            dao.insertAdvanceDetails(formData);
//        }
        mv = new ModelAndView("redirect:getList.htm");
        return mv;
    }
    
    public synchronized ModelAndView addFurtherAdvance(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto formData) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        DomesticTravelDaoImpl dao = (DomesticTravelDaoImpl) ctx.getBean("DomesticTravelDao");
        String status = formData.getStatus();
        System.out.println("status "+status);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate = sdf1.parse(formData.getTravel_end_date());
        System.out.println("to date "+toDate);
        formData.setTravel_end_date(sdf.format(toDate));
        dao.addFurtherAdvance(request, response, formData);
        mv = new ModelAndView("redirect:getList.htm");
        return mv;
    }
}
