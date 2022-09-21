/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.utils;

import com.htl.ideal.mom.service.MomService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
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
    
    public static String fileUpload(MultipartFile fileprop,int refId,String referenceName,String moduleName, MomService service) throws  IOException{
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
//                lastFileInsertId = (String) service.addFileDb(fileName,contentType,referenceName,refId,moduleName);
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
}