package Utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    public static Logger logger;

    Properties pro;

    public ReadConfig() {

        logger = Logger.getLogger("CoingeckoWeb");
        PropertyConfigurator.configure("Log4j.properties");


        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            logger.info("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL(){
        String url = pro.getProperty("baseURL");
        return url;
    }

    public String getUsername(){
        String username = pro.getProperty("username");
        return username;
    }

    public String getPassword(){
        String password = pro.getProperty("password");
        return password;
    }

    public String getStagingURl(){
        String staging = pro.getProperty("Staging1URL");
        return staging;
    }

    public String getWrongPassword(){
        String wrongPassword = pro.getProperty("incorrect_password");
        return wrongPassword;
    }



}
