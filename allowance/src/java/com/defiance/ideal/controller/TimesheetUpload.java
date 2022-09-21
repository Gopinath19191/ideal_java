/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dto.TimesheetDto;
import com.defiance.ideal.service.TimesheetService;
import com.defiance.ideal.util.CommonFunctions;
import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class TimesheetUpload extends MultiActionController{
    private ModelAndView mv;
    String returnMsg = null;
    
    public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Type", "application/force-download");
            try {
                FileInputStream fis = null;
                response.setHeader("Content-Disposition", "attachment;filename=timesheet_format.xls");
                fis = new FileInputStream("D:/propertyfiles/bulkUpload_Template/timesheet_format.xls");
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
            mv = null;
        }
        return mv;
    }
    
    public ModelAndView readExcelFile(HttpServletRequest request, HttpServletResponse response) {

        final WebApplicationContext ctx = getWebApplicationContext();
        TimesheetService service = (TimesheetService) ctx.getBean("TimesheetService");
        ModelAndView mvc = new ModelAndView("/com/defiance/timesheetUpload");
        TimesheetDto searchDto = new TimesheetDto();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            File dir = new File(CommonFunctions.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            service.deleteTimesheetTemp();
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2"};
                uplodedFilename = CommonFunctions.commonCodeForFileUpload(dir, file);
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        int empty_cell = 0;
                        for (Short i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(i);
                            if (mandCell == null) {
                                empty_cell++;
                            }
                        }
                        String employee_number = null;
                        String project_code = null; 
                        String currentDate = null;
                        String normal_hours = null;
                        String ot_hours = null;
                        TimesheetDto project_search = null;
                        String error="Empty Data";
                        if(empty_cell==0){
                            while (cells.hasNext()) {
                                HSSFCell cell = (HSSFCell) cells.next();
                                String cellData = cell.toString();
                                short currentCell = cell.getCellNum();
                                if (currentCell == 0) {
                                    if(cell.getCellType() == 1){
                                        employee_number = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
                                        employee_number = "";
                                    }else{
                                        double d = Double.parseDouble(cellData);
                                        int splitcell = (int) d / 1;
                                        employee_number = String.valueOf(splitcell).trim();
                                    }
                                    searchDto.setEmployee_number(employee_number);
                                }
                                if (currentCell == 1) {
                                    if(cell.getCellType() == 1){
                                        project_code = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
                                        project_code = "";
                                    }else{
                                        double d = Double.parseDouble(cellData);
                                        int splitcell = (int) d / 1;
                                        project_code = String.valueOf(splitcell).trim();
                                    }
                                    while(project_code.length()<8){
                                        project_code = "0"+project_code;
                                    }
                                    searchDto.setProject_code(project_code);
                                }
                                if (currentCell == 2) {
                                    String datePattern1= "\\d{1,2}-\\d{1,2}-\\d{4}";
                                    if(cellData.matches(datePattern1)){
                                        DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String DATE_FORMAT_NOW = "yyyy-MM-dd";
                                        Date d1 = dateFormat1.parse(cellData);
                                        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
                                        currentDate = dateFormat.format(d1);
                                    }
                                    String datePattern2= "\\d{1,2}-[a-zA-Z]{3}-\\d{4}";
                                    if(cellData.matches(datePattern2)){
                                        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
                                        String DATE_FORMAT_NOW = "yyyy-MM-dd";
                                        Date d1 = dateFormat1.parse(cellData);
                                        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
                                        currentDate = dateFormat.format(d1);
                                    }
                                    searchDto.setDate(currentDate);
                                }
                                if (currentCell == 3) {
                                    if(cell.getCellType() == 1){
                                        normal_hours = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == 0){
                                        normal_hours = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
                                        normal_hours = "0";
                                    }else{
                                        double d = Double.parseDouble(cellData);
                                        int splitcell = (int) d / 1;
                                        normal_hours = String.valueOf(splitcell).trim();
                                    }
                                }
                                if (currentCell == 4) {
                                    if(cell.getCellType() == 1){
                                        ot_hours = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == 0){
                                        ot_hours = String.valueOf(cellData.toString()).trim();
                                    }else if(cell.getCellType() == cell.CELL_TYPE_BLANK){
                                        ot_hours = "0";
                                    }else{
                                        double d = Double.parseDouble(cellData);
                                        int splitcell = (int) d / 1;
                                        ot_hours = String.valueOf(splitcell).trim();
                                    }
                                }
                            }//cell iteration
                        }
                        int weekend=0;
                        if(normal_hours!=null && !normal_hours.equals("0") && !normal_hours.equals("0.0")){
                            System.out.println("hours not nulls");
                            String employee_id = service.validateEmployeeId(searchDto);
                            searchDto.setRole_type("n");
                            searchDto.setTimesheet_hours(normal_hours);
                            searchDto.setRow_id(Integer.toString(row.getRowNum()));
                            if(employee_id!=null){
                                searchDto.setEmployee_id(employee_id);
                                project_search = service.getProjectId(searchDto);
                                if(project_search!=null){
                                    searchDto.setProject_id(project_search.getProject_id());
                                    searchDto.setRole_id(project_search.getRole_id());
                                    error="";
                                }else{
                                    searchDto.setProject_id("");
                                    searchDto.setRole_id("");
                                    error="Project Allocation issue";
                                }
                            }else{
                                searchDto.setEmployee_id("");
                                error="Employee Id issue";
                            }
                            searchDto.setReason(error);
                            service.insertTimesheetTemp(searchDto);
                        }else{
                            weekend++;
                        }
                        if(ot_hours!=null && !ot_hours.equals("0") && !ot_hours.equals("0.0")){
                            System.out.println("ot hours not nulls");
                            String employee_id = service.validateEmployeeId(searchDto);
                            searchDto.setRole_type("o");
                            searchDto.setTimesheet_hours(ot_hours);
                            searchDto.setRow_id(Integer.toString(row.getRowNum()));
                            if(employee_id!=null){
                                searchDto.setEmployee_id(employee_id);
                                project_search = service.getProjectId(searchDto);
                                if(project_search!=null){
                                    searchDto.setProject_id(project_search.getProject_id());
                                    searchDto.setRole_id(project_search.getRole_id());
                                    error="";
                                }else{
                                    searchDto.setProject_id("");
                                    searchDto.setRole_id("");
                                    error="Project Allocation issue";
                                }
                            }else{
                                searchDto.setEmployee_id("");
                                error="Employee Id issue";
                            }
                            searchDto.setReason(error);
                            service.insertTimesheetTemp(searchDto);
                        }else{
                            weekend++;
                        }
                        if(weekend==2){
                            searchDto.setEmployee_id("");
                            searchDto.setProject_id("");
                            searchDto.setRole_id("");
                            searchDto.setTimesheet_hours("");
                            searchDto.setRole_type("");
                            searchDto.setReason("Empty Data");
                            searchDto.setRow_id(Integer.toString(row.getRowNum()));
                            service.insertTimesheetTemp(searchDto);
                        }
                    }//data validation
                    
                }//row iteration
                ArrayList entireList = new ArrayList();
                ArrayList rowDataHead =  new ArrayList();
                rowDataHead.add(new String("0"));
                rowDataHead.add(new String("5"));
                rowDataHead.add(new String("Upload Status"));
                entireList.add(rowDataHead);
                ArrayList resultList = new ArrayList();
                service.uploadTimesheet();
                List<TimesheetDto> uploadStatus = service.getUploadStatus();
                if(uploadStatus.size()>0){
                    for(int i=0; i<uploadStatus.size(); i++){
                        ArrayList rowData =  new ArrayList();
                        rowData.add(new String(""+uploadStatus.get(i).getRow_id()));
                        rowData.add(new String("5"));
                        rowData.add(new String(""+uploadStatus.get(i).getReason()));
                        entireList.add(rowData);
                    }
                }

                try {
                    System.out.println("uplodedFilename "+uplodedFilename);
                    InputStream fis = null;
                    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                    response.setContentType("application/ms-excel");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + uplodedFilename);
                    fis = new FileInputStream("D:/bulkupload/" + uplodedFilename);

                    HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
                    final HSSFSheet sheet1 = hssfworkbook.getSheet("Timesheet");

                    HSSFCellStyle cs = hssfworkbook.createCellStyle();
                    HSSFCellStyle contentCs = hssfworkbook.createCellStyle();
                    HSSFDataFormat df = hssfworkbook.createDataFormat();
                    cs.setDataFormat(df.getFormat("General"));

                    for(int i=0; i<entireList.size(); i++){
                        resultList = (ArrayList) entireList.get(i);
                        CommonFunctions.commonUpdateCode(hssfworkbook, sheet1, cs, contentCs, i, 5, resultList.get(2).toString()); // Emp Number
                    
                    }
//                    fis.close();
                    hssfworkbook.write(bos);
                    
                    bos.flush();
                    bos.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }
            }
        } catch (Exception e) {
            returnMsg = e.getMessage();
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed ");
            return mvc;
        }
        System.out.println("file downloaded");
        mvc = new ModelAndView("/com/defiance/timesheetUpload");
        mvc.addObject("successMsg", "successfully uploaded");
        return mvc;
    }
}
