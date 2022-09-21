/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.util;

import com.ideal.hrMaster.service.ProcurementService;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16113
 */
public class CommonConfig {
    public static final String EmpID= "EmpID";
    public static final String EmpName = "EmpName";
    public static final String SiteCode = "SiteCode";
    public static final String Department = "Department";
    public static final String Type = "Type";
    public static final String Date = "Date(dd/MM/yyyy)";
    public static final String First = "First";
    public static final String Last = "Last";
    public static final String Location = "Location";
    public static final String FILE_UPLOAD_PATH ="D:/swipeUpload/";
    public static final String fileUploadPath = "D:\\quailtyFileUpload\\";
    public static final String ProcurementReferenceId = "743";
    public static final String ProcurementModuleName = "Procurement";
    public static final String idCardPhoto = "C:\\wamp\\www\\app\\webroot\\uploads\\id_card_photo\\";
    
    public static String string;
    public static String st1[] = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
        "Eight", "Nine",};
    public static String st2[] = {"Hundred", "Thousand", "Lakh", "Crore"};
    public static String st3[] = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen",};
    public static String st4[] = {"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
        "Eighty", "Ninty"};
    
    public static String commonCodeForFileUpload(File dir, MultipartFile file) throws IOException {
             Random random = new Random();
            int ran = random.nextInt();
        if(ran<0){
            ran = -(ran);
        }   
        String temp =  file.getOriginalFilename().trim();
        String splitFile[] = temp.split("\\.");
        String tempfile = splitFile[0] + ran +"."+splitFile[1];
        File file1 = new File(dir.getAbsolutePath() + "/" + tempfile);
        file1.createNewFile();
        file.transferTo(file1);
        return tempfile;
    }
    
    public static String fileUpload(MultipartFile fileprop,int refId,String referenceName,String moduleName, ProcurementService service) throws  IOException{
          String lastFileInsertId = null;
          try {
            if(!fileprop.getOriginalFilename().equals("")){
                String contentType = fileprop.getContentType();
                String fileName =refId+"~"+moduleName+"~"+fileprop.getOriginalFilename();
                String uploadDir=fileUploadPath;
                new File(uploadDir).mkdir();
                if (!fileName.equals("")) {
                    File fileToCreate = new File(uploadDir, fileName);
                    if (!fileToCreate.exists()){
                        FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                        byte[] fileData = fileprop.getBytes();
                        fileOutStream.write(fileData);
                        fileOutStream.flush();
                        fileOutStream.close();
                    }
                }
                lastFileInsertId = (String) service.addFileDb(fileName,contentType,referenceName,refId,moduleName);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return lastFileInsertId;
    }
    
    public static void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try{
            response.setContentType(fileType);
            System.out.println("file name>>>>>"+fileName);
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
    public static void exportToExcel(HttpServletResponse responseObj, List excelData, String fileName, String sheetName, String rename) throws Exception {
            //System.out.println("In exportToExcel Function" +excelData.size()+"---"+fileName+"---"+sheetName+"---"+rename);

        ArrayList resultList = new ArrayList();
        InputStream fis = null;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + fileName);
            responseObj.setContentType("application/ms-excel");
            if (rename == null) {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonConfig.class.getResourceAsStream("/" + fileName));
            } else {
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + rename+".xls\"");
                responseObj.setHeader("Content-Type", "application/force-download");
                fis = (CommonConfig.class.getResourceAsStream("/" + fileName + ".xls"));
            }

            // new FileInputStream(FILE_LOCATION+"/"+fileName);
//                HSSFWorkbook hssfworkbook = new HSSFWorkbook();
//                HSSFSheet sheet = hssfworkbook.createSheet("Sheet 1");
            int dataRows = excelData.size();
            HSSFWorkbook hssfworkbook = new HSSFWorkbook(fis);
            final HSSFSheet sheet = hssfworkbook.getSheet(sheetName);

            HSSFCellStyle cs = hssfworkbook.createCellStyle();
            HSSFCellStyle csInt = hssfworkbook.createCellStyle();
            HSSFDataFormat df = hssfworkbook.createDataFormat();
            cs.setDataFormat(df.getFormat("General"));
            csInt.setDataFormat(df.getFormat("0"));
//              HSSFRow rowhead = sheet.createRow((short) 0);
//              rowhead.createCell((short) 0).setCellValue("Hariharan");
            HSSFRow rowhead = sheet.createRow((short) 0);
            
            HSSFCellStyle dateFormat1 = hssfworkbook.createCellStyle();
            dateFormat1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            Calendar calendar = Calendar.getInstance();


            HSSFFont font = hssfworkbook.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            //font.setFontHeightInPoints((short) 20);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //font.setColor(HSSFColor.BLUE.index);
            cs.setFont(font);

            /*
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
             */
            String test;
            HSSFCell cell;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (int i = 0; i < dataRows; i++) {
                rowhead = sheet.createRow((short) (i + 1));
                resultList = (ArrayList) excelData.get(i);                
                for (int j = 0; j < resultList.size(); j++) {                    
                    if (resultList.get(j) != null) {
                        if (resultList.get(j).toString().contains("~~")) {
                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
                        } 
                        else if (resultList.get(j).toString().contains("@@@@@@")) {
                            cell = rowhead.createCell((short) j);       
                            calendar.setTime(sdf.parse(resultList.get(j).toString().split("@@@@@@")[1]));
                            System.out.println("ccc" +calendar);
                            cell.setCellValue(calendar);
                            cell.setCellStyle(dateFormat1); // style 1
//                            rowhead.createCell((short) j).setCellValue(new Double(removeNull(resultList.get(j).toString().split("~~")[1])));
                        } else if (resultList.get(j).toString().contains("#####")) {
                       //System.out.println(":::here comes::");
                            cell = rowhead.createCell((short) j);
                            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
                            cell.setCellValue(new Double(removeNull(resultList.get(j).toString().split("#####")[1])));
                            cell.setCellStyle(csInt);
                        }
                        else {
                            rowhead.createCell((short) j).setCellValue(removeNull(resultList.get(j).toString()));
                        }
                    }
                }
            }

            hssfworkbook.write(bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static String removeNull(String stringToProcess) {
        String processedString;
        if (stringToProcess.equals(null) || stringToProcess.equals("null")) {
            processedString = "";
        } else {
            processedString = stringToProcess;
        }
        return processedString;
    }
    public static String convert(int number) {
        int n = 1;
        int word;
        string = "";
        while (number != 0) {
            switch (n) {
                case 1:
                    word = number % 100;
                    pass(word);
                    if (number > 100 && number % 100 != 0) {
                        show("and ");
                    }
                    number /= 100;
                    break;

                case 2:
                    word = number % 10;
                    if (word != 0) {
                        show(" ");
                        show(st2[0]);
                        show(" ");
                        pass(word);
                    }
                    number /= 10;
                    break;

                case 3:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[1]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;

                case 4:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[2]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;

                case 5:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[3]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;
            }
            n++;
        }
        return string;
    }
    
    public static void pass(int number) {
        int word, q;
        if (number < 10) {
            show(st1[number]);
        }
        if (number > 9 && number < 20) {
            show(st3[number - 10]);
        }
        if (number > 19) {
            word = number % 10;
            if (word == 0) {
                q = number / 10;
                show(st4[q - 2]);
            } else {
                q = number / 10;
                show(st1[word]);
                show(" ");
                show(st4[q - 2]);
            }
        }
    }

    public static void show(String s) {
        String st;
        st = string;
        string = s;
        string += st;
    }
}
