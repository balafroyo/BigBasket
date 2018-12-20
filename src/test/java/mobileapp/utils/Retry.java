package mobileapp.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// implement IRetryAnalyzer interface
public class Retry implements IRetryAnalyzer{
 
      // set counter to 0
       int minretryCount=0;
      // set maxcounter value this will execute our tests 3 times
      int maxretryCount=2;
      // override retry Method
       public boolean retry(ITestResult result) {
            // this will run until max count completes if tests pass within this frame it will come out of for loop
              if(minretryCount<=maxretryCount)
              {
                   // print the tests name for log purpose
                     System.out.println("Following tests is failed ==== "+result.getName());
                   // print the counter value    
                     System.out.println("Retry the tests "+result.getName()+" === "+ (minretryCount+1));
                   // increment counter each time by 1  
                     minretryCount++;
                     return true;
              }
              return false;
       }
}