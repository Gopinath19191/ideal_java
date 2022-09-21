/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.dto.DomesticTravelDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.MailMessages;
import com.defiance.ideal.travelplan.utils.SendMail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author 16221
 */
public class DomesticTravelDaoImpl extends SqlMapClientDaoSupport implements DomesticTravelDao {

    public List<DomesticTravelDto> getList(String employee_id) {
        List<DomesticTravelDto> list = null;
        try {
            list = getSqlMapClientTemplate().queryForList("DomesticTravel.getDashBoardList", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public DomesticTravelDto getEmployeeDetails(String employee_id) {
        DomesticTravelDto employee_details = null;
        try {
            employee_details = (DomesticTravelDto) getSqlMapClientTemplate().queryForObject("DomesticTravel.getEmployeeDetails", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee_details;
    }

    public List<DomesticTravelDto> getBuhList() {
        List<DomesticTravelDto> buh_list = null;
        try {
            buh_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getBuhList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buh_list;
    }

    public List<DomesticTravelDto> getProjectList(String employee_id) {
        List<DomesticTravelDto> project_list = null;
        try {
            project_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getProjectList", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return project_list;
    }

    public List<DomesticTravelDto> getSettlementType() {
        List<DomesticTravelDto> settlement_type = null;
        try {
            settlement_type = getSqlMapClientTemplate().queryForList("DomesticTravel.getSettlementType");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settlement_type;
    }
    
    public List<DomesticTravelDto> getCurrency() {
        List<DomesticTravelDto> currency_list = null;
        try {
            currency_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getCurrency");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currency_list;
    }

    public List<DomesticTravelDto> getBookingType() {
        List<DomesticTravelDto> booking_type = null;
        try {
            booking_type = getSqlMapClientTemplate().queryForList("DomesticTravel.getBookingType");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking_type;
    }
    public List<String> getAttachments(String master_id){
        List<String> attached_files = null;
        try {
            attached_files = getSqlMapClientTemplate().queryForList("DomesticTravel.getAttachments",master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attached_files;
    }
    public List<DomesticTravelDto> getCustomerList(String employee_id) {
        List<DomesticTravelDto> customer_list = null;
        try {
            customer_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getCustomerList", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer_list;
    }

    public String getLastRequestId() {
        String last_id = null;
        try {
            last_id = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getLastRequestId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return last_id;
    }

    public List<CommonDto> getAutocomplete(String val, String type) {
        List<CommonDto> result = null;
        val = '%' + val + '%';
        String country_id = type;
        Map<String, String> m = new HashMap<String, String>();
        m.put("m", val);
        m.put("c", country_id);
        result = (List<CommonDto>) getSqlMapClientTemplate().queryForList("DomesticTravel.getAutoCity", m);
        return result;
    }

    public String insertTravel(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto formData) throws IOException, ParseException {
        String last_insert_id = null;
        try {
            if(formData.getMaster_id() != null){
                getSqlMapClientTemplate().update("DomesticTravel.updateTravel", formData);
                last_insert_id = formData.getMaster_id();
            }else{
                last_insert_id = (String) getSqlMapClientTemplate().insert("DomesticTravel.insertTravel", formData);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        formData.setMaster_id(last_insert_id);
        if (last_insert_id != null) {
            insertAdvanceDetails(formData);
            insertTicketDetails(formData);
            insertHotelDetails(formData);
            insertConveyanceDetails(formData);
            insertAttachments(request, formData);
        }
        return last_insert_id;
    }
    public String getRMId(String employee_id){
        String rm_id = null;
        try {
            rm_id = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getRmId",employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rm_id;
    }
    public void insertAdvanceDetails(DomesticTravelDto formData) throws ParseException {
        for(int l=0;l<formData.getArr_advance_date().length;l++){
            formData.setAdvance_amount(formData.getArr_advance_amount()[l]);
            formData.setCurrency_id(formData.getArr_currency_id()[l]);
            formData.setAdvance_remarks(formData.getArr_advance_remarks()[l]);
            formData.setAdvance_id(formData.getArr_advance_id()[l]);
            formData.setAdvance_deleted(formData.getArr_advance_deleted()[l]);
            if(formData.getArr_advance_date()[l] != null && !formData.getArr_advance_date()[l].equals("")){
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = sdf1.parse(formData.getArr_advance_date()[l]);
                formData.setAdvance_date(sdf.format(fromDate));
                if(formData.getArr_advance_id()[l].equals("0") && formData.getArr_advance_deleted()[l].equals("0")){
                    try {
                        getSqlMapClientTemplate().insert("DomesticTravel.insertAdvanceDetails", formData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        getSqlMapClientTemplate().insert("DomesticTravel.updateAdvance", formData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void insertTicketDetails(DomesticTravelDto formData) throws ParseException {
        if (formData.getTicket_booking_type().length > 0) {
            for (int i = 0; i < formData.getTicket_booking_type().length; i++) {
                formData.setTicket_book_type(formData.getTicket_booking_type()[i]);
                if (!formData.getFrom_city_id()[i].equals("") && !formData.getFrom_city_id()[i].equals(null)) {
                    formData.setTravel_from_city_id(formData.getFrom_city_id()[i]);
                    formData.setTravel_from_city_others("");
                } else {
                    formData.setTravel_from_city_others(formData.getFrom_city()[i]);
                    formData.setTravel_from_city_id("");
                }
                if (!formData.getTo_city_id()[i].equals("") && !formData.getTo_city_id()[i].equals(null)) {
                    formData.setTravel_to_city_id(formData.getTo_city_id()[i]);
                    formData.setTravel_to_city_others("");
                } else {
                    formData.setTravel_to_city_others(formData.getTo_city()[i]);
                    formData.setTravel_to_city_id("");
                }
                formData.setTravel_travel_preference(formData.getTravel_preference()[i]);
                formData.setTravel_travel_mode(formData.getTravel_mode()[i]);
                formData.setTravel_ticket_remarks(formData.getTicket_remarks()[i]);
                formData.setTravel_ticket_id(formData.getTicket_id()[i]);
                formData.setTravel_ticketdeleted(formData.getTicket_deleted()[i]);
                if(formData.getTravel_date()[i] != null && !formData.getTravel_date()[i].equals("")){
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fromDate = sdf1.parse(formData.getTravel_date()[i]);
                    formData.setTravel_ticket_date(sdf.format(fromDate));
                    if(formData.getTicket_deleted()[i].equals("0") && formData.getTicket_id()[i].equals("0")){
                        try {
                            getSqlMapClientTemplate().insert("DomesticTravel.insertTicketDetails", formData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            getSqlMapClientTemplate().insert("DomesticTravel.updateTicket", formData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void insertHotelDetails(DomesticTravelDto formData) throws ParseException {
        if (formData.getHotel_booking_type().length > 0) {
            for (int j = 0; j < formData.getHotel_booking_type().length; j++) {
                formData.setHotel_book_type(formData.getHotel_booking_type()[j]);
                if (!formData.getCity_id()[j].equals("") && !formData.getCity_id()[j].equals(null)) {
                    formData.setHotel_city_id(formData.getCity_id()[j]);
                    formData.setHotel_city_others("");
                } else {
                    formData.setHotel_city_id("");
                    formData.setHotel_city_others(formData.getCity()[j]);
                }
                formData.setHotel_location(formData.getLocation()[j]);
                formData.setHotel_hotel_remarks(formData.getHotel_remarks()[j]);
                formData.setHotelid(formData.getHotel_id()[j]);
                formData.setHoteldeleted(formData.getHotel_deleted()[j]);
                if(formData.getFrom_date()[j] != null && formData.getFrom_date()[j] != null && !formData.getFrom_date()[j].equals("") && !formData.getFrom_date()[j].equals("")){
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fromDate = sdf1.parse(formData.getFrom_date()[j]);
                    formData.setHotel_from_date(sdf.format(fromDate));
                    Date toDate = sdf1.parse(formData.getTo_date()[j]);
                    formData.setHotel_to_date(sdf.format(toDate));
                    if(formData.getHotel_deleted()[j].equals("0") && formData.getHotel_id()[j].equals("0")){
                        try {
                            getSqlMapClientTemplate().insert("DomesticTravel.insertHotelDetails", formData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            getSqlMapClientTemplate().insert("DomesticTravel.updateHotel", formData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void insertConveyanceDetails(DomesticTravelDto formData) throws ParseException {
        if (formData.getConveyance_city().length > 0) {
            for (int k = 0; k < formData.getConveyance_city().length; k++) {
                if(!formData.getConveyance_city()[k].equals("") && !formData.getConveyance_city()[k].equals(null)){
                    if(!formData.getConveyance_city_id()[k].equals("") && !formData.getConveyance_city_id()[k].equals(null)){
                        formData.setCab_city_id(formData.getConveyance_city_id()[k]);
                        formData.setCab_city_others("");
                    }else{
                        formData.setCab_city_id("");
                        formData.setCab_city_others(formData.getConveyance_city()[k]);
                    }
                    formData.setCab_pickup(formData.getPickup()[k]);
                    formData.setCab_drop(formData.getDropto()[k]);
                    formData.setCab_time_hrs(formData.getTime_hr()[k]+":"+formData.getTime_min()[k]);
                    formData.setCab_remarks(formData.getConveyance_remarks()[k]);
                    formData.setCab_id(formData.getConveyance_id()[k]);
                    formData.setCab_deleted(formData.getConveyancedeleted()[k]);
                    if(formData.getStart_date()[k] != null && formData.getStart_date()[k] != ""){
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fromDate = sdf1.parse(formData.getStart_date()[k]);
                        formData.setCab_date(sdf.format(fromDate));
                        if(formData.getConveyance_id()[k].equals("0") && formData.getConveyancedeleted()[k].equals("0")){
                            try {
                                getSqlMapClientTemplate().insert("DomesticTravel.insertConveyanceDetails", formData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                getSqlMapClientTemplate().insert("DomesticTravel.updateConveyance", formData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void insertAttachments(HttpServletRequest request, DomesticTravelDto formData) throws IOException{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        MultipartFile files = multipartRequest.getFile("attach_doc_1");
        if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
            String file_name = formData.getTp_reference_id()+"_"+createdDate.getTime()+"_" + files.getOriginalFilename();
            uploadData.put("file_name", file_name);
            uploadData.put("created_date", createdDate);
            uploadData.put("master_id", formData.getMaster_id());
            uploadData.put("type", "a");
            File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
            files.transferTo(path);
            String last_insert_id = (String)getSqlMapClientTemplate().insert("DomesticTravel.saveAttachment", uploadData);
        }
    }

    public DomesticTravelDto getTravelDetails(String master_id) {
        DomesticTravelDto travel_details = null;
        try {
            travel_details = (DomesticTravelDto) getSqlMapClientTemplate().queryForObject("DomesticTravel.getTravelDetails", master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travel_details;
    }

    public List<DomesticTravelDto> getAdvanceDetails(String master_id) {
        List<DomesticTravelDto> advance_details = null;
        try{
            advance_details = getSqlMapClientTemplate().queryForList("DomesticTravel.getAdvanceDetails", master_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return advance_details;
    }
    
    public List<DomesticTravelDto> getTicketDetails(String master_id) {
        List<DomesticTravelDto> ticket_details = null;
        try{
            ticket_details = getSqlMapClientTemplate().queryForList("DomesticTravel.getTicketDetails", master_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return ticket_details;
    }
    
    public List<DomesticTravelDto> getHotelDetails(String master_id) {
        List<DomesticTravelDto> hotel_details = null;
        try{
            hotel_details = getSqlMapClientTemplate().queryForList("DomesticTravel.getHotelDetails", master_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return hotel_details;
    }
    
    public List<DomesticTravelDto> getCabDetails(String master_id) {
        List<DomesticTravelDto> hotel_details = null;
        try{
            hotel_details = getSqlMapClientTemplate().queryForList("DomesticTravel.getCabDetails", master_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return hotel_details;
    }
    
    public List<DomesticTravelDto> getRMList(DomesticTravelDto dto){
        List<DomesticTravelDto> rm_list = null;
        if(dto.getList_type().equals("pending")){
            try{
                rm_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getRMPendingList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
             try{
                rm_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getRMProcessedList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return rm_list;
    }
    
    public void updateRmApproval(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateRmApproval", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public DomesticTravelDto getApproverDetails(String master_id){
        DomesticTravelDto approver_details = null;
        try {
            approver_details = (DomesticTravelDto) getSqlMapClientTemplate().queryForObject("DomesticTravel.getApproverDetails", master_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approver_details;
    }
    public List<DomesticTravelDto> getBUHList(DomesticTravelDto dto){
        List<DomesticTravelDto> buh_list = null;
        if(dto.getList_type().equals("pending")){
            try{
                buh_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getBUHPendingList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
             try{
                buh_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getBUHProcessedList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return buh_list;
    }
    
    public void updateBuhApproval(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateBuhApproval", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<DomesticTravelDto> getFinanceList(DomesticTravelDto dto){
        List<DomesticTravelDto> finance_list = null;
        if(dto.getList_type().equals("pending")){
            try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getFinancePendingList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
             try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getFinanceProcessedList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return finance_list;
    }
    public void updateFinanceApproval(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateFinanceApproval", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<DomesticTravelDto> getAdminList(DomesticTravelDto dto){
        List<DomesticTravelDto> finance_list = null;
        if(dto.getList_type().equals("pending")){
            try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getAdminPendingList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
             try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getAdminProcessedList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return finance_list;
    }
    public void updateAdminApproval(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateAdminApproval", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<DomesticTravelDto> getTreasuryList(DomesticTravelDto dto){
        List<DomesticTravelDto> finance_list = null;
        if(dto.getList_type().equals("pending")){
            try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getTreasuryPendingList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
             try{
                finance_list = getSqlMapClientTemplate().queryForList("DomesticTravel.getTreasuryProcessedList", dto);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return finance_list;
    }
    public void updateTreasuryApproval(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateTreasuryApproval", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void updateAdvanceDetails(DomesticTravelDto dto){
        try{
            for (int k = 0; k < dto.getArr_advance_id().length; k++) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = sdf1.parse(dto.getArr_deposited_date()[k]);
                dto.setDeposited_date(sdf.format(fromDate));
                dto.setAdvance_id(dto.getArr_advance_id()[k]);
                dto.setDeposited_amount(dto.getArr_deposited_amount()[k]);
                dto.setDeposited_currency(dto.getArr_deposited_currency()[k]);
                getSqlMapClientTemplate().update("DomesticTravel.updateAdvanceDetails", dto);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String[] updateTicketDetails(HttpServletRequest request, DomesticTravelDto dto) throws IOException{
        String[] attachment_name = new String[10];
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        for (int k = 0; k < dto.getTicket_id().length; k++) {
            if(dto.getTicket_booking_type()[k].equals("co")) {
                MultipartFile files = multipartRequest.getFile("ticket_attachment_"+dto.getTicket_id()[k]);
                if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                    String file_name = dto.getTp_reference_id()+"_"+createdDate.getTime()+"_" + files.getOriginalFilename();
                    uploadData.put("file_name", file_name);
                    uploadData.put("created_date", createdDate);
                    uploadData.put("master_id", dto.getMaster_id());
                    uploadData.put("type", "t");
                    attachment_name[k] = file_name;
                    File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
                    files.transferTo(path);
                    String last_insert_id = (String)getSqlMapClientTemplate().insert("DomesticTravel.saveAttachment", uploadData);
                    dto.setFile_id(last_insert_id);
                    dto.setTravel_ticket_id(dto.getTicket_id()[k]);
                    dto.setBooking_status(dto.getTravel_booking_status()[k]);
                    dto.setTicket_book_type(dto.getTicket_booking_type()[k]);
                    getSqlMapClientTemplate().update("DomesticTravel.updateTicketDetails", dto);
                }
            }else if(dto.getTicket_booking_type()[k].equals("s")){
                dto.setTravel_ticket_id(dto.getTicket_id()[k]);
                dto.setBooking_status(null);
                dto.setFile_id(null);
                dto.setTicket_book_type(dto.getTicket_booking_type()[k]);
                getSqlMapClientTemplate().update("DomesticTravel.updateTicketDetails", dto);
            }else{
            }
        }
        
        for (int j = 0; j < dto.getHotel_id().length; j++) {
            if(dto.getHotel_booking_type()[j].equals("co")) {
                MultipartFile files = multipartRequest.getFile("hotel_attachment_"+dto.getHotel_id()[j]);
                int arrray_length = dto.getTicket_id().length;
                if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                    String file_name = dto.getTp_reference_id()+"_"+createdDate.getTime()+"_" + files.getOriginalFilename();
                    uploadData.put("file_name", file_name);
                    uploadData.put("created_date", createdDate);
                    uploadData.put("master_id", dto.getMaster_id());
                    attachment_name[arrray_length+j] = file_name;
                    File path = new File(CommonConfigurations.travelDocumentsPath + file_name);
                    files.transferTo(path);
                    String last_insert_id = (String)getSqlMapClientTemplate().insert("DomesticTravel.saveAttachment", uploadData);
                    dto.setFile_id(last_insert_id);
                    dto.setHotelid(dto.getHotel_id()[j]);
                    dto.setBooking_status(dto.getHotel_booking_status()[j]);
                    dto.setHotel_book_type(dto.getHotel_booking_type()[j]);
                    getSqlMapClientTemplate().update("DomesticTravel.updateHotelDetails", dto);
                }
            }else if(dto.getHotel_booking_type()[j].equals("s")){
                dto.setHotelid(dto.getHotel_id()[j]);
                dto.setBooking_status(null);
                dto.setFile_id(null);
                dto.setHotel_book_type(dto.getHotel_booking_type()[j]);
                getSqlMapClientTemplate().update("DomesticTravel.updateHotelDetails", dto);
            }
        }
        String ticket_count = null;
        String hotel_count = null;
        ticket_count = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getTicketBookingCount",dto.getMaster_id());
        hotel_count = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getHotelBookingCount",dto.getMaster_id());
        int admin_action_count = Integer.parseInt(hotel_count) + Integer.parseInt(ticket_count);
        if(admin_action_count>0){
            dto.setAdmin_action("Y");
            getSqlMapClientTemplate().update("DomesticTravel.updateAdminActionStatus", dto);
        }else{
            dto.setAdmin_action("N");
            getSqlMapClientTemplate().update("DomesticTravel.updateAdminActionStatus", dto);
        }
        return attachment_name;
    }
    
    public void cancelTravel(DomesticTravelDto dto){
        try{
            getSqlMapClientTemplate().update("DomesticTravel.cancelTravel", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addFurtherAdvance(HttpServletRequest request, HttpServletResponse response, DomesticTravelDto dto) throws ParseException{
        int adv_add = Integer.parseInt(request.getParameter("advance_added"));
        if(adv_add>0){
            dto.setTreasury_action("Y");
        }else{
            dto.setTreasury_action("N");
        }
        try{
            getSqlMapClientTemplate().update("DomesticTravel.updateTravelEndDate", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(adv_add>0){
            for(int l=0;l<dto.getArr_advance_date().length;l++){
                System.out.println("adding advance "+dto.getArr_advance_date().length);
                dto.setAdvance_date(dto.getArr_advance_date()[l]);
                dto.setAdvance_amount(dto.getArr_advance_amount()[l]);
                dto.setCurrency_id(dto.getArr_currency_id()[l]);
                dto.setAdvance_remarks(dto.getArr_advance_remarks()[l]);
                dto.setAdvance_id(dto.getArr_advance_id()[l]);
                dto.setAdvance_deleted(dto.getArr_advance_deleted()[l]);
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fromDate = sdf1.parse(dto.getArr_advance_date()[l]);
                dto.setAdvance_date(sdf.format(fromDate));
                if(!dto.getArr_advance_date()[l].equals("")){
                    System.out.println("advance id "+dto.getArr_advance_id()[l]);
                    System.out.println("advance ideleted "+dto.getArr_advance_deleted()[l]);
                    if(dto.getArr_advance_id()[l].equals("0") && dto.getArr_advance_deleted()[l].equals("0")){
                        System.out.println("here it comes qqwwwwwwew");
                        try {
                            getSqlMapClientTemplate().insert("DomesticTravel.insertAdvanceDetails", dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    public void mailSendProcess(DomesticTravelDto dto, String previous_status) throws Exception {
        ArrayList<CommonDto> connectionRes;
        connectionRes = getMailDetails();
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        MailMessages mailMessageObj = new MailMessages();
        try{
            String travel_id = dto.getTp_reference_id();
            HttpServletRequest requestObj = null;
            String mailSubject = "";
            String mailTo = "";
            String mailCC = "";
            String mailMessage = "";
            if(previous_status.equals("0") && dto.getStatus().equals("1")){
                mailSubject = travel_id + " - New Travel Request is Waiting For Approval";
                DomesticTravelDto manager_mail = getEmployeeMailId(dto.getRm_id());
                mailTo = manager_mail.getMail_id();
                DomesticTravelDto employee_mail = getEmployeeMailId(dto.getEmployee_id());
                String customerName = "";
                if(dto.getCustomer_id() != null && !dto.getCustomer_id().equals("-1") && !dto.getCustomer_id().equals("")){
                    customerName = getCustomerName(dto.getCustomer_id());
                }else{
                    customerName = "-";
                }
                String projectName = "";
                if(dto.getProject_id() != null && !dto.getProject_id().equals("-1") && !dto.getProject_id().equals("")){
                    projectName = getProjectName(dto.getProject_id());
                }else{
                    projectName = "-";
                }                
                mailCC = employee_mail.getMail_id();
                String project_travel = null;
                String client_reimbursable = null;
                if(dto.getTravel_billable().equals("Y")){
                    project_travel = "Yes";
                }else{
                    project_travel = "No";
                }
                if(dto.getClient_reimbursable().equals("Y")){
                    client_reimbursable = "Yes";
                }else{
                    client_reimbursable = "No";
                }
                mailMessage += "Dear "+manager_mail.getEmployee_name()+",<br><br>";
                mailMessage += "New domestic travel request submitted by "+employee_mail.getEmployee_name()+" is waiting for your action, below are the travel details.<br><br>"
                                + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Request Type :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Domestic Travel </td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + customerName + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + projectName + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Travel :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + project_travel + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Reimbursable by Customer :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + client_reimbursable + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Travel Remarks :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + dto.getTravel_purpose() + "</td></tr>"
                                + "</table>"
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(!previous_status.equals("0") && dto.getStatus().equals("1")){
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                String customerName = "";
                if(dto.getCustomer_id() != null && !dto.getCustomer_id().equals("-1") && !dto.getCustomer_id().equals("")){
                    customerName = getCustomerName(dto.getCustomer_id());
                }else{
                    customerName = "-";
                }
                String projectName = "";
                String client_reimbursable = null;
                if(dto.getProject_id() != null && !dto.getProject_id().equals("-1") && !dto.getProject_id().equals("")){
                    projectName = getProjectName(dto.getProject_id());
                }else{
                    projectName = "-";
                }
                if(dto.getClient_reimbursable().equals("Y")){
                    client_reimbursable = "Yes";
                }else{
                    client_reimbursable = "No";
                }
                mailCC = employee_mail.getMail_id();
                String project_travel = null;
                if(dto.getTravel_billable().equals("Y")){
                    project_travel = "Yes";
                }else{
                    project_travel = "No";
                }
                mailSubject = travel_id + " - Travel Request is Resubmitted";
                mailMessage += "Dear All,<br><br>";
                mailMessage += "Domestic travel is resubmitted by "+employee_mail.getEmployee_name()+" waiting for your action below are the travel details.<br><br>"
                                + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Request Type :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Domestic Travel </td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + customerName + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + projectName + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Travel :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + project_travel + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Reimbursable by Customer :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + client_reimbursable + "</td></tr>"
                                + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Travel Remarks :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + dto.getTravel_purpose() + "</td></tr>"
                                + "</table>"
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailCC = employee_mail.getMail_id();
                if(previous_status.equals("2") || previous_status.equals("1") || previous_status.equals("3")){
                    mailTo = manager_mail.getMail_id();
                }else if(previous_status.equals("4") || previous_status.equals("5")){
                    DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                    mailTo = manager_mail.getMail_id() + "," + buh_mail.getMail_id();
                }else if(previous_status.equals("6") || previous_status.equals("7")){
                    String finance_mail = getFinanceMailId(travelDetails.getUnit_id());
                    if(travelDetails.getDeviation().equals("Y")){
                        DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                        mailTo = manager_mail.getMail_id() + "," + buh_mail.getMail_id();
                    }else{
                        mailTo = manager_mail.getMail_id();
                    }
                }else{
                    String finance_mail = getFinanceMailId(travelDetails.getUnit_id());
                    mailTo = manager_mail.getMail_id();
                    if(travelDetails.getDeviation().equals("Y")){
                        DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                        mailTo = mailTo + "," + buh_mail.getMail_id();
                    }
                    if(travelDetails.getAdmin_action().equals("Y")){
                        mailTo = mailTo + "," + getAdminMailId(travelDetails.getBuh_id());
                    }
                    if(travelDetails.getTreasury_action().equals("Y")){
                        mailTo = mailTo + "," + getTreasuryMailId(travelDetails.getUnit_id());
                    }
                }
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("2")){
                // RM/PM approvel amil to buh or finance team
                mailSubject = travel_id + " - New Travel Request is Waiting For Approval";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                String customerName = "";
                if(travelDetails.getCustomer_id() != null && !travelDetails.getCustomer_id().equals("-1")){
                    customerName = travelDetails.getCustomer_name();
                }
//                else{
//                    customerName = travelDetails.getCustomer_others();
//                }
                String projectName = "";
                if(travelDetails.getProject_id() != null && !travelDetails.getProject_id().equals("-1")){
                    projectName = travelDetails.getProject_name();
                }
//                else{
//                    projectName = travelDetails.getProject_others();
//                }
                String project_travel = travelDetails.getTravel_billable();
                String client_reimbursable = travelDetails.getClient_reimbursable();
                String travel_remarks = travelDetails.getTravel_purpose();
                if(travelDetails.getDeviation().equals("Y")){
                    DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                    mailTo = buh_mail.getMail_id();
                    mailCC = manager_mail.getMail_id()+","+employee_mail.getMail_id();
                    mailMessage += "Dear "+buh_mail.getEmployee_name()+",<br><br>";
                }else{
                    mailTo = employee_mail.getMail_id();
                    mailCC = manager_mail.getMail_id();
                    mailMessage += "Dear Business Operation in charge,<br><br>";
                }
                mailMessage += "New domestic travel request submitted by "+employee_mail.getEmployee_name()+", which is approved by "+manager_mail.getEmployee_name()+" is waiting for your action, below are the travel details.<br><br>"
                            + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Request Type :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Domestic Travel </td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + customerName + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + projectName + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Travel :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + project_travel + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Reimbursable by Customer :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + client_reimbursable + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Travel Remarks :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + travel_remarks + "</td></tr>"
                            + "</table>"
                            + "<br><br>Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("4")){
                mailSubject = travel_id + " - New Travel Request is Waiting For Approval";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                String customerName = "";
                if(travelDetails.getCustomer_id() != null && !travelDetails.getCustomer_id().equals("-1")){
                    customerName = travelDetails.getCustomer_name();
                }
//                else{
//                    customerName = travelDetails.getCustomer_others();
//                }
                String projectName = "";
                if(travelDetails.getProject_id() != null && !travelDetails.getProject_id().equals("-1")){
                    projectName = travelDetails.getProject_name();
                }
//                else{
//                    projectName = travelDetails.getProject_others();
//                }
                String project_travel = travelDetails.getTravel_billable();
                String client_reimbursable = travelDetails.getClient_reimbursable();
                String travel_remarks = travelDetails.getTravel_purpose();
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear Business Operation in charge,<br><br>";
                mailMessage += "New domestic travel request submitted by "+employee_mail.getEmployee_name()+", which is approved by "+buh_mail.getEmployee_name()+" is waiting for your action, below are the travel details.<br><br>"
                            + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Request Type :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Domestic Travel </td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Customer Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + customerName + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Name :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + projectName + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Project Travel :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + project_travel + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Reimbursable by Customer :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + client_reimbursable + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Travel Remarks :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + travel_remarks + "</td></tr>"
                            + "</table>"
                            + "<br><br>Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                mailCC = buh_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("6")){
                mailSubject = travel_id + " - New Travel Request is Waiting for your Action";
                String admin_id = null;
                String treasury = null;
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                if(travelDetails.getAdmin_action().equals("Y")){
                    admin_id = getAdminMailId(travelDetails.getBuh_id());
                }
                if(travelDetails.getTreasury_action().equals("Y")){
                    treasury = getTreasuryMailId(travelDetails.getUnit_id());
                }
                if(admin_id == null && treasury == null){
                    mailTo = employee_mail.getMail_id();
                }else if(treasury == null){
                    mailTo = admin_id;
                }else if(admin_id == null){
                    mailTo = employee_mail.getMail_id();
                }else{
                    mailTo = admin_id + "," + employee_mail.getMail_id();
                }
                mailMessage += "Dear All,<br><br>";
                mailMessage += "New domestic travel request submitted by "+employee_mail.getEmployee_name()+" is approved by Finance and waiting for your action.<br><br>"
                            + "<br><br>Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                String emp_id = employee_mail.getMail_id();
                String fin_id = getFinanceMailId(travelDetails.getUnit_id());
                mailCC = emp_id;
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("8")){
                mailSubject = travel_id + " - Travel Ticket/Hotel Booked";
                String admin_id = null;
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                admin_id = getAdminMailId(travelDetails.getBuh_id());
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear "+employee_mail.getEmployee_name()+",<br><br>";
                mailMessage += "Travel ticket and lodging are booked by travel desk team as per the request, please find the detail in the attachments.<br><br>"
                            + "<br><br>Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                mailCC = admin_id;
                mailObj.smtpMailAttachment(mailTo, mailSubject, mailMessage, mailCC,dto.getTravel_preference());
            }else if(dto.getStatus().equals("9")){
                mailSubject = travel_id + " - Travel Advance Deposited";
                String treasury_mail = null;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                Date fromDate = sdf.parse(dto.getDeposited_date());
                Date date1 = sdf1.parse(sdf1.format(fromDate));
                List<DomesticTravelDto> advance_details = getSqlMapClientTemplate().queryForList("DomesticTravel.getAdvanceDetails", dto.getMaster_id());
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                treasury_mail = getTreasuryMailId(travelDetails.getBuh_id());
                int length = advance_details.size();
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String numberAsString = decimalFormat.format(Integer.parseInt(advance_details.get(length-1).getDeposited_amount()));
                mailTo = employee_mail.getMail_id();
                mailMessage += "Dear "+employee_mail.getEmployee_name()+",<br><br>";
                mailMessage += "We are pleased to inform you that, Travel advance of "+advance_details.get(length-1).getDeposited_currency()+" "+numberAsString+" has been deposited on "+advance_details.get(length-1).getDeposited_date()+" by Hinduja Tech in your bank account registered with payroll."
                            + "<br><br>Please login to iDeal to view the details.<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
                mailCC = employee_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("10")){
                mailSubject = travel_id + " - Travel Request Cancelled";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                mailMessage += "Dear All,<br><br>";
                mailMessage += "Domestic travel request raise by "+employee_mail.getEmployee_name()+" is cancelled by him/her, please find the reason below.<br><br>"
                                + "<b>Cancel Reason: </b>"+dto.getCancel_remarks()
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailCC = employee_mail.getMail_id();
                if(previous_status.equals("2") || previous_status.equals("1") || previous_status.equals("3")){
                    mailTo = manager_mail.getMail_id();
                }else if(previous_status.equals("4") || previous_status.equals("5")){
                    DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                    mailTo = manager_mail.getMail_id() + "," + buh_mail.getMail_id();
                }else if(previous_status.equals("6") || previous_status.equals("7")){
                    String finance_mail = getFinanceMailId(travelDetails.getUnit_id());
                    if(travelDetails.getDeviation().equals("Y")){
                        DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                        mailTo = manager_mail.getMail_id() + "," + buh_mail.getMail_id();
                    }else{
                        mailTo = manager_mail.getMail_id();
                    }
                }else{
                    String finance_mail = getFinanceMailId(travelDetails.getUnit_id());
                    mailTo = manager_mail.getMail_id() + "," + finance_mail;
                    if(travelDetails.getDeviation().equals("Y")){
                        DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                        mailTo = mailTo + "," + buh_mail.getMail_id();
                    }
                    if(travelDetails.getAdmin_action().equals("Y")){
                        mailTo = mailTo + "," + getAdminMailId(travelDetails.getBuh_id());
                    }
                    if(travelDetails.getTreasury_action().equals("Y")){
                        mailTo = mailTo + "," + getTreasuryMailId(travelDetails.getUnit_id());
                    }
                }
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("3")){
                mailSubject = travel_id + " - Travel Request Sent Back by RM/PM";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                mailMessage += "Dear "+employee_mail.getEmployee_name()+",<br><br>";
                mailMessage += "Domestic travel request raise by you is sent back by "+manager_mail.getEmployee_name()+", please find the reason below.<br><br>"
                                + "<b>Cancel Reason: </b>"+dto.getRm_remarks()
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                mailCC = manager_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("5")){
                mailSubject = travel_id + " - Travel Request Sent Back by BUH";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                mailMessage += "Dear "+employee_mail.getEmployee_name()+",<br><br>";
                mailMessage += "Domestic travel request raise by you is sent back by "+buh_mail.getEmployee_name()+", please find the reason below.<br><br>"
                                + "<b>Cancel Reason: </b>"+dto.getBuh_remarks()
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                mailCC = manager_mail.getMail_id()+ "," +buh_mail.getMail_id();
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }else if(dto.getStatus().equals("7")){
                mailSubject = travel_id + " - Travel Request Sent Back by Business Operation In Charge";
                DomesticTravelDto travelDetails = getTravelDetails(dto.getMaster_id()); 
                DomesticTravelDto employee_mail = getEmployeeMailId(travelDetails.getEmployee_id());
                DomesticTravelDto manager_mail = getEmployeeMailId(travelDetails.getRm_id());
                mailMessage += "Dear "+employee_mail.getEmployee_name()+",<br><br>";
                mailMessage += "Domestic travel request raise by you is sent back by finance in charge, please find the reason below.<br><br>"
                                + "<b>Cancel Reason: </b>"+dto.getFinance_remarks()
                                + "<br><br>Please login to iDeal to view the details.<br><br>"
                                + "Regards,<br>"
                                + "iDeal Admin<br>"
                                + "http://ideal.hindujatech.com";
                mailTo = employee_mail.getMail_id();
                String finance_mail = getFinanceMailId(travelDetails.getUnit_id());
                if(travelDetails.getDeviation().equals("Y")){
                    DomesticTravelDto buh_mail = getEmployeeMailId(travelDetails.getBuh_id());
                    mailCC = manager_mail.getMail_id()+ "," +buh_mail.getMail_id()+ "," + finance_mail;
                }else{
                    mailCC = manager_mail.getMail_id()+ "," + finance_mail;
                }
                
                mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
            }

            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<CommonDto> getMailDetails(){
        ArrayList<CommonDto> list = null;
        try{
            list = (ArrayList) getSqlMapClientTemplate().queryForList("DomesticTravel.getConfigValueData");
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public DomesticTravelDto getEmployeeMailId(String emp_id){
        DomesticTravelDto mail_id = null;
        try{
            mail_id = (DomesticTravelDto) getSqlMapClientTemplate().queryForObject("DomesticTravel.getEmployeeMailId",emp_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_id;
    }
    
    public String getFinanceMailId(String unit_id){
        String mail_id = null;
        try{
            mail_id = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getFinanceMailId",unit_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_id;
    }
    
    public String getAdminMailId(String unit_id){
        String mail_id = null;
        try{
            mail_id = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getAdminMailId",unit_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_id;
    }
    
    public String getTreasuryMailId(String unit_id){
        String mail_id = null;
        try{
            mail_id = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getTreasuryMailId",unit_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_id;
    }
    
    public String getCustomerName(String customer_id){
        String customer_name = "";
        try{
            customer_name = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getCustomerName",customer_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return customer_name;
    }
    
    public String getProjectName(String project_id){
        String project_name = null;
        try{
            project_name = (String) getSqlMapClientTemplate().queryForObject("DomesticTravel.getProjectName",project_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return project_name;
    }
    public ArrayList<String> getbandList(){
        ArrayList<String> list = null;
        try{
            list = (ArrayList) getSqlMapClientTemplate().queryForList("DomesticTravel.bandList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
