package mobileapp.reporting;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {
  private static ExtentReports extent;
  public synchronized static ExtentReports getReporter(){
      if(extent == null){
          extent = new ExtentReports("testReport.html", true);
      }
      return extent;
  }
}