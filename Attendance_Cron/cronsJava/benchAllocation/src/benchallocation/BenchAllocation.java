/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package benchallocation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author 16221
 */
public class BenchAllocation {

    /**
     * @param args the command line arguments
     */
    static String strUserName = "";
    static String strPassword = "";
    static String strDatabase = "";
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String configFile = new File("jdbc.properties").getAbsolutePath();
        FileReader fileInput = new FileReader(configFile);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();
        //
        strDatabase = "jdbc:mysql://" + properties.getProperty("SERVER") + ":" + properties.getProperty("PORT") + "/" + properties.getProperty("DATABASE");
        strUserName = properties.getProperty("USER");
        strPassword = properties.getProperty("PASSWORD");
    }
}
