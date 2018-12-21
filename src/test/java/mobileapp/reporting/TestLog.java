package mobileapp.reporting;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.relevantcodes.extentreports.LogStatus;

public class TestLog {
		static Logger logger = Logger.getLogger("");
	    static {
	        PropertyConfigurator.configure("./config/log4j.properties");
	        logger.debug("Log4j console appender configuration is successful !!");
	    }
	    

	    public static void  logInfo(String message){
	        logger.debug(message);
        }
	    
    public static void stepInfo (String message) 
    {
    	logger.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }
    
    public static void testFail (String message) 
    {
    	logger.error(message);
        ExtentTestManager.getTest().log(LogStatus.FAIL, message);	   
    }
    
    public static void testPAss (String message) 
    {
    	logger.error(message);
        ExtentTestManager.getTest().log(LogStatus.PASS, message);	   
    }
	   
    public static void testStart (String message,String desc) 
    {
    	logger.error(message);
        ExtentTestManager.startTest(message,desc);
    }
}
