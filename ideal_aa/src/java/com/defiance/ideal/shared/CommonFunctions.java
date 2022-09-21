/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.shared;

import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.upload.FormFile;
import org.jsoup.Jsoup;

/**
 *
 * @author Admin
 */
public class CommonFunctions {

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static final String changeDateFormatToDB (String providedDate) throws ParseException{
        if(!("").equals(providedDate) && providedDate!=null){
                String[] proDateArray=providedDate.split("-");
                String formattedDate=null;
                if((proDateArray[2] == null ? "0000" != null : !proDateArray[2].equals("0000")) && (proDateArray[1] == null ? "00" != null : !proDateArray[1].equals("00")) && (proDateArray[0] == null ? "00" != null : !proDateArray[0].equals("00"))){
                formattedDate=proDateArray[2]+"-"+proDateArray[1]+"-"+proDateArray[0];
                }
            return formattedDate;
        }else{
            return providedDate=null;
        }
    }

    public static void TrimSpace(Object pageClass){
        try {
            Class c = Class.forName(pageClass.getClass().getCanonicalName());
            Method[] method = c.getDeclaredMethods();
            for(int i=0;i<method.length;i++){
                if(method[i].getName().startsWith("g")){
                    Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3,methodName.length());
                   if(obj!=null && !(method[i].getReturnType().toString().equals("int"))){
                      
                            String st = obj.toString().trim();
                            Class[] types = new Class[] {String.class};
                            Method setterMethod = c.getMethod("set"+methodName, types);
                            setterMethod.invoke(pageClass, st);
                    }
                }
            }
        }catch(Exception ex){
           
        }
    }

     public static void printRequest(HttpServletRequest requestObj){
        try {
            
            List<String> requestParameterNames = Collections.list((Enumeration<String>)requestObj.getParameterNames());
            for ( String parameterName:requestParameterNames){
            System.out.println("parameterName = " + parameterName+"\n");
            //System.out.println("requestParameterNames = " + requestParameterNames+"\n");
            }
        } catch (Exception ex) {
            
        }
    }

     public static int getAppraisalYear(){
         Calendar apprCalendar = Calendar.getInstance();
         int currentYear = apprCalendar.get(Calendar.YEAR);
         return currentYear;
     }

     public static int getAppraisalQuarter(){
         Calendar apprCalendar = Calendar.getInstance();
         int month = apprCalendar.get(Calendar.MONTH);
//         int appraisalQuarter = (month >= Calendar.JANUARY && month <= Calendar.MARCH)? 1 :(month >= Calendar.APRIL && month <= Calendar.JUNE)? 2 :(month >= Calendar.JULY && month <= Calendar.SEPTEMBER)? 3:4;
         int appraisalQuarter = (month >= Calendar.JANUARY && month <= Calendar.MARCH)? 4 :(month >= Calendar.APRIL && month <= Calendar.JUNE)? 1 :(month >= Calendar.JULY && month <= Calendar.SEPTEMBER)? 2:3;
         return appraisalQuarter;
     }


      public static int getAppraisalYear(HttpSession sessionObj){
          int currentYear;
          if(sessionObj.getAttribute("appraisalYearSession")==null){
             Calendar apprCalendar = Calendar.getInstance();
             currentYear = apprCalendar.get(Calendar.YEAR);
          }else{
              currentYear = Integer.parseInt(sessionObj.getAttribute("appraisalYearSession").toString());
          }
         
         return currentYear;
     }


     public static int getAppraisalQuarter(HttpSession sessionObj){
         int appraisalQuarter;
         if(sessionObj.getAttribute("appraisalQuarterSession")==null){
         Calendar apprCalendar = Calendar.getInstance();
         int month = apprCalendar.get(Calendar.MONTH);
//         int appraisalQuarter = (month >= Calendar.JANUARY && month <= Calendar.MARCH)? 1 :(month >= Calendar.APRIL && month <= Calendar.JUNE)? 2 :(month >= Calendar.JULY && month <= Calendar.SEPTEMBER)? 3:4;
         appraisalQuarter = (month >= Calendar.JANUARY && month <= Calendar.MARCH)? 4 :(month >= Calendar.APRIL && month <= Calendar.JUNE)? 1 :(month >= Calendar.JULY && month <= Calendar.SEPTEMBER)? 2:3;
         }else{
             appraisalQuarter = Integer.parseInt(sessionObj.getAttribute("appraisalQuarterSession").toString());
         }
         return appraisalQuarter;
     }



     public static void exportToExcel(HttpServletResponse responseObj,ArrayList headerData,ArrayList excelData,String fileName) throws Exception{
//            System.out.println("In exportToExcel Function");

        ArrayList resultList =  new ArrayList();
         try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());

            responseObj.setContentType("application/ms-excel");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            responseObj.setHeader("Content-Type","application/force-download");
            
                int dataRows = excelData.size();
            
                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
                HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
                HSSFCellStyle cs = hssfworkbook.createCellStyle();
                HSSFDataFormat df = hssfworkbook.createDataFormat();
                cs.setDataFormat(df.getFormat("General"));
//              HSSFRow rowhead = sheet.createRow((short) 0);
//              rowhead.createCell((short) 0).setCellValue("Hariharan");
                HSSFRow rowhead = sheet.createRow((short) 0);

                HSSFFont font = hssfworkbook.createFont();
                font.setFontName(HSSFFont.FONT_ARIAL);
              //font.setFontHeightInPoints((short) 20);
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
              //font.setColor(HSSFColor.BLUE.index);
                cs.setFont(font);
                ArrayList headerList=new ArrayList();
                
                for(int header=0;header<headerData.size();header++){
                rowhead = sheet.createRow((short) header);
                headerList = (ArrayList) headerData.get(header);
                for(int i=0;i<headerList.size();i++){
                    HSSFCell cell = rowhead.createCell((short) i);
                    cell.setCellValue(headerList.get(i).toString());
                    cell.setCellStyle(cs);
                }
                //HSSFCell cell = rowhead.createCell((short) header);
                //cell.setCellValue(headerData.get(header).toString());
                //cell.setCellStyle(cs);
                }
                
                for(int i=0;i<dataRows;i++){
                     rowhead = sheet.createRow((short) (i+3));
                     resultList = (ArrayList) excelData.get(i);
                        for(int j=0;j<resultList.size();j++){
                            if(resultList.get(j)!=null){
                            rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
                            }
                        }
                }

                hssfworkbook.write(bos);
                bos.flush();
                bos.close();
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
     }


     public static void fileUpload(FormFile fileprop,int refId,String referenceName,String moduleName,MysqlDatabase dbCTRL) throws  IOException{
        try {
            if(!fileprop.getFileName().equals(""))
            {
            String contentType = fileprop.getContentType();
            String fileName =refId+"~~"+referenceName+"~~"+fileprop.getFileName();
//            byte[] fileData = fileprop.getFileData();
            String uploadDir=CommonConfigurations.fileUploadPath;

            new File(uploadDir).mkdir();
                    
            if (!fileName.equals("")) {
                File fileToCreate = new File(uploadDir, fileName);
                if (!fileToCreate.exists()){
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(fileprop.getFileData());
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
            dbCTRL.addFileDb(fileName,contentType,referenceName,refId,moduleName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


     public static void fileRemove(int fileId,String fileName,String filePath,HttpServletResponse response,MysqlDatabase dbCTRL) throws  IOException{
        try {
            System.out.println("file id to be removed "+fileId);
            String uploadDir=CommonConfigurations.fileUploadPath;
            File removeFile=new File(uploadDir+fileName);
            removeFile.delete();
            dbCTRL.removeFileDb(fileId);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
    }


     public static void fileDownload(String fileName,String filePath,String fileType,HttpServletResponse response) {

        try{
            response.setContentType(fileType);
            response.setHeader("Content-disposition","attachment; filename=\""+fileName+"\"");
            File file = new File(filePath+"\\"+fileName);
            //prepare input stream
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //prepare output stream
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            //start reading and writing data
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        }
    }

     public static String removeNull(String stringToProcess){
         String processedString;
         if(stringToProcess == null){
         processedString="";
         }else{
         processedString = html2text(stringToProcess);
         }
         return processedString;
     }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }


    public static int checkLevelSkip(int appraiseeId,int appraisalYear,MysqlDatabase dbCTRL,String levelcheck){
        int skipLevelCount = 0;
        try {
            ManagerDTO getAppraiserReviewerId = dbCTRL.getAppraiserReviewerId(appraiseeId,appraisalYear);
            if (getAppraiserReviewerId != null) {
                if (levelcheck != null && levelcheck.equals("appraiser")) {
                    if (getAppraiserReviewerId.getAppraiserId() == getAppraiserReviewerId.getReviewerId()) {
                        skipLevelCount = 1;
                        if (getAppraiserReviewerId.getReviewerId() == getAppraiserReviewerId.getNormalizerId()) {
                            skipLevelCount = 2;
                        }
                    }
                }else if(levelcheck != null && levelcheck.equals("reviewer")){
                    if (getAppraiserReviewerId.getReviewerId() == getAppraiserReviewerId.getNormalizerId()) {
                        skipLevelCount = 2;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skipLevelCount;
    }


}
