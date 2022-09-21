/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dto.AuditListDto;
import com.ideal.hrMaster.dto.EmployeHistoryDto;
import com.ideal.hrMaster.service.BulkUploadService;
import com.ideal.hrMaster.util.CommonConfig;
import com.lowagie.text.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFSheet;




import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class BulkUploadController extends MultiActionController {

    String returnMsg = null;

   public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession HttpSessionsession = request.getSession();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/force-download");

        ModelAndView mvc = new ModelAndView("/swipeUpload");
        try {
            FileInputStream fis = null;
            if (request.getParameter("fileName").equals("chennai")) {
                //response.setHeader("Content-Disposition", "attachment;filename=attendance_chennai.xls");
               // fis = new FileInputStream("D:/propertyfiles/swipeUpload_Template/attendance_chennai.xlsx");
                response.setHeader("Content-Disposition", "attachment;filename=Template.xls");
                fis = new FileInputStream("D:/propertyfiles/swipeUpload_Template/Template.xls");
            } else if (request.getParameter("fileName").equals("pune")) {
                response.setHeader("Content-Disposition", "attachment;filename=RM_CHANGE.xls");
                fis = new FileInputStream("D:/propertyfiles/bulkUpload_Template/RM_CHANGE.xls");
            }

            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public ModelAndView updateEmployeeBandDesigByExcel(HttpServletRequest request, HttpServletResponse response) {

        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/swipeUpload");
        AuditListDto auditdto = new AuditListDto();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            auditdto.setEmployee_number(authEmpid);
//            ArrayList bandList = service.getBands();
//            ArrayList desiList = service.getDesignations();
//            ArrayList empList = service.getEmployeNumbers();
           
            EmployeHistoryDto authDetails = service.getEmployeId(authEmpid);
            File dir = new File(CommonConfig.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2", "3","4", "5", "6", "7","8"};
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                        }
                    }//header validation
                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        for (Short i = 0; i < mandtCells.length; i++) {
                            if(i==0 || i==5 || i==6 || i==7){
                                HSSFCell mandCell = row.getCell(i);
                                if (mandCell == null) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (i+1) + " is mandatory ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            
                            if (currentCell == 5) {
                                System.out.println("inputParam "+cellData+" row "+row.getRowNum());
                                boolean isDate = true;
                                boolean isDate2 = true;
                                String inputParam = cellData;
                                String datePattern2 = "\\d{1,2}-\\d{1,2}-\\d{4}";
                                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date current = new Date();
                                String myFormatString = "dd-MM-yyyy";
                                SimpleDateFormat df = new SimpleDateFormat(myFormatString);
                                Date givenDate = df.parse(cellData);
                                Long l = givenDate.getTime();   
                                    //create date object
                                Date next = new Date(l);
                                //compare both dates
                                if(next.after(current)){  
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Future date cannot be allowed" ;
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;   
                                } else {
                                    System.out.println("The date is past day");
                                }          
                                if( inputParam.matches(datePattern2)){
                                    System.out.println("TRUEEEEEEEEEEEEEEEEE");
                                }else{
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Date format is invalid . It sholud be  dd-mm-yyyy format" ;
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                } 
                            }
                        }//cell iteration
                    }//data validation
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }
                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    EmployeHistoryDto dto = new EmployeHistoryDto();
                    EmployeHistoryDto singleEmployData = null;
                    String bandId = null;
                    String employeeId = null;
                    String desId = null;
                    String locId = null;
                    int updateCount = 0;
                    long unixTime = System.currentTimeMillis() / 1000;
                    dto.setModified_time(unixTime + "");
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        dto.setReason("Bulk Upload");
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                           
                            if (String.valueOf(currentCell).equals("0")) {
                                double d = Double.parseDouble(cellData);
                                int splitcell = (int) d / 1;
                                String empnumber = String.valueOf(splitcell);
                                singleEmployData = service.getSingleEmployee(empnumber);
                                employeeId = service.getEmpid(empnumber);
                                dto.setEmployee_number(employeeId); 
                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                dto.setEmployee_name(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                dto.setSite_code(cellData);
                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                desId = service.getDesignationid(cellData);
                                dto.setDepartmentName(cellData);
                                dto.setDepartment(desId);
                            }
                            if (String.valueOf(currentCell).equals("4")) {
                                dto.setType(cellData);
                            }
                            if (String.valueOf(currentCell).equals("5")) {
                                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                                String DATE_FORMAT_NOW = "yyyy-MM-dd";
                                Date d1 = dateFormat1.parse(cellData);
                                DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
                                String currentDate = dateFormat.format(d1);
                                dto.setDate(currentDate);
                            }
                            if (String.valueOf(currentCell).equals("6")) {
                                dto.setFirst_in(cellData);
                            }
                            if (String.valueOf(currentCell).equals("7")) {
                                dto.setLast_out(cellData);
                                DateFormat sdf = new SimpleDateFormat("hh:mm");
                                Date first_in = sdf.parse(dto.getFirst_in());
                                Date last_out = sdf.parse(dto.getLast_out()); 
                                if((last_out.getTime() - first_in.getTime()) < 0){
                                    dto.setLast_out("23:59");
                                    dto.setCarry_fwd_in("00:00");
                                    dto.setCarry_fwd_out(cellData);
                                   
                                    DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                                    String DATE_FORMAT_NOW = "yyyy-MM-dd";
                                    Date d1 = dateFormat1.parse(dto.getDate());
                                    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(dateFormat.parse(dto.getDate()));
                                    cal.add(Calendar.DATE, 1 );
                                    String next_date = dateFormat.format(cal.getTime());
                                    dto.setNext_date(next_date);
                                }else{
                                
                                }
                            }   
                            if (String.valueOf(currentCell).equals("8")) {
                                locId=service.getSingleLocation(cellData);
                                dto.setLocation(cellData);
                                dto.setLoc_id(locId);
                                System.out.println("locId"+locId);
                            }
                            dto.setSource("EXCEL");
                          
                        } //cell iteration  
                        try {
                            EmployeHistoryDto sourceDto=service.getSourceE(dto);
                            if(employeeId!=null ){
                               if(sourceDto!=null){
                                    updateCount = service.updateBandDesignation(dto);
                                    mvc.addObject("successMsg", "successfully uploaded"); 
                                }else {
                                    updateCount = service.updateBandDesignation(dto);
                                    mvc.addObject("successMsg", "successfully uploaded"); 
                                }
                            } 
                        } catch (Exception e) {
                            e.printStackTrace();
                            mvc.addObject("returnMsg", "Upload failed while updating employee ");
                            return mvc;
                        }    
                    }    
                }//while ending inserting data
            }
        }catch (Exception e) {
            returnMsg = e.getMessage();
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed ");
            return mvc;
        }
        mvc.addObject("successMsg", "Upload Completed"); 
        return mvc;
    }

    public ModelAndView searchAuditList(HttpServletRequest request, HttpServletResponse response, AuditListDto dto) {
        ModelAndView mvc = new ModelAndView("/auditList");
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        List<AuditListDto> emplist = service.getDistinctEmployees();
        System.out.println(emplist);
        List<AuditListDto> auditList = service.showAuditList(dto);
        mvc.addObject("auditList", auditList);
        mvc.addObject("filter", dto);
        mvc.addObject("emplist", emplist);
        return mvc;
    }

    public ModelAndView downloadAuditExcel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession HttpSessionsession = request.getSession();
        String filename = request.getParameter("filename");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        ModelAndView mvc = new ModelAndView("/swipeUpload");
        try {
            FileInputStream fis = new FileInputStream("D:/swipeUpload/" + filename);
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }
//    public static Object deserialize(String fileName) throws IOException,
//            ClassNotFoundException {
//        FileInputStream fis = new FileInputStream(fileName);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        Object obj = ois.readObject();
//        ois.close();
//        return obj;
//    }
}
