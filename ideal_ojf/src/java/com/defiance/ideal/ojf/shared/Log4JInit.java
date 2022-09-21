package com.defiance.ideal.ojf.shared;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;
/**
 *
 * @author user
 */
public class Log4JInit extends HttpServlet {
    	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() {
	 File logFile = null;
		try {
                    Properties masterConfigFile = new Properties();
                    masterConfigFile.load(new FileInputStream("d:\\propertyfiles\\jfconfiguration.properties"));
                    String log4jFile = masterConfigFile.getProperty("log4j.property.file");

                    Properties configFile = new Properties();
                    configFile.load(new FileInputStream(log4jFile));

                    logFile = new File(configFile.getProperty("log4j.appender.R.File"));

			if (!logFile.exists()) {
				logFile.createNewFile();
			}else if (logFile.exists()) {
			PropertyConfigurator.configure(log4jFile);
                        System.out.println("===============================Good Logger Working Fine======(The Last SOP)======");
			}
		}catch (final IOException e){
			e.printStackTrace();
		}
	}
}
