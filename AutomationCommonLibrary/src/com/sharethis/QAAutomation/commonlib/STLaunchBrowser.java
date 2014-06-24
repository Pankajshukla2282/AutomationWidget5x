package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
//import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import org.testng.Reporter;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;

/************************************************************
 * STLaunchBrowser class used for launching browser. *
 * @see <br> This class contain following functions:
 * @see <li>  stLaunch()
 * @see <li>  stLaunchWidget() - Is used to launch Widget
 ************************************************************/
public class STLaunchBrowser  { 
	public static Selenium browser;

	//*******************************[ stLaunch Function Start ]***********************************
    /*********************************************************************************************
     * Function used for launching browser.
     * @param dataid - Id to fetch the browser type from Data.xml for launch.
     * @param ExpVal - 0 for successful launch, 1 for Error
	 * @param flow - "" for continue or "STOP,TC_ID" for stop the execution of script if function fails.
	 * @return Pass or FAIL
     *********************************************************************************************/
    public String stLaunch(int dataid,int expVal, String flow)
    { 
       	int actVal=1000;
		String returnVal=null;
		Block :{	
    	hm=STFunctionLibrary.stMakeData(dataid, "Common");
    	String localHost = hm.get("Localhost");		
    	String browserType = hm.get("BrowserType");
    	String url = hm.get("URL");
    	String windowtitle = hm.get("WindowTitle");
    	browser = new DefaultSelenium(localHost,4444, browserType, url);
       	browser.start();
    	try{
	    browser.setTimeout("200000");
    	    browser.open(url);
    	    browser.selectWindow("null");
    	    browser.windowMaximize();
        	browser.windowFocus();
        	browser.setSpeed("200");
	        STCommonLibrary comLib= new STCommonLibrary();	    
        	comLib.stWaitForElement(RESKINNED_WIDGET,100);
	   	    String BrowserTitle=browser.getTitle();
			if (BrowserTitle.contains(windowtitle)){
    	    	actVal=0;
    	    	break Block;				
			}
			else{
				actVal=1;
				break Block;
			}
    	}    	
    	catch (SeleniumException sexp) 
		{	
			Reporter.log(sexp.getMessage());
			//return null; 
		}		
    }    
	returnVal=STFunctionLibrary.stRetValDes(expVal, actVal, "stLaunch",flow, hm);
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
		try {
			
		} catch (Exception e) {
		}
	}
	return returnVal; 
    }
	//********************************[ stLaunch Function End ]************************************
    
   //********************************[ stLaunchWidget Function Starts ]************************************
    
    /*********************************************************************************************
     * Function used for launching Widget.
     * @param ExpVal - 0 for successful launch, 1 for Error
	 * @param flow - "" for continue or "STOP,TC_ID" for stop the execution of script if function fails.
	 * @return Pass or FAIL
     *********************************************************************************************/
    public String stLaunchWidget(String targetXPath,String verifyXPath,int expVal, String flow)
    { 
       	int actVal=1000;
		String returnVal=null;
		Block :{	
    	
	        STCommonLibrary comLib= new STCommonLibrary();	    
        	comLib.stWaitForElement(RESKINNED_WIDGET,20);
	   	    
        	comLib.stClickAndVerify(targetXPath, verifyXPath, expVal, flow);        	
        	try {
    			Thread.sleep(10000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    		String Windowtitle = browser.getText(verifyXPath);
    		System.out.println("*******" + Windowtitle);
    		
    		if (Windowtitle.contains("Share this with friends!")){
    	    	actVal=0;
    	    	break Block;				
			}
			else{
				actVal=1;
				break Block;
			}
		}

	returnVal=STFunctionLibrary.stRetValDes(expVal, actVal, "stLaunchWidget",flow, hm);
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
	}
	return returnVal;
    
    }
  //********************************[ stLaunchWidget Function Ends ]************************************
    
  //********************************[ stOpenBrowserInstance Function Starts ]***************************
    /*********************************************************************************************
     * Function used for open browser instance.
     * @param dataid - Id to fetch the url from Data.xml for launch.
     * @param ExpVal - 0 for successful launch, 1 for Error
	 * @param flow - "" for continue or "STOP,TC_ID" for stop the execution of script if function fails.
	 * @return Pass or FAIL
     *********************************************************************************************/    
    public String stOpenBrowserInstance (int dataid,int expVal, String flow){    	
    	
    	int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataid, "Post");
   	String url = hm.get("URL");		
   	String newwindowtitle = hm.get("NewWindowTitle");
   		
	try {
		
		Block : {
		
		/*Open New window*/
		browser.open(url);
//		browser.openWindow(url, newwindowtitle);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	/*Select newly open window*/
    	browser.selectWindow(newwindowtitle);
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String browsertitle = browser.getTitle();
		
		if (browsertitle.contains(newwindowtitle))
		{
			actVal = 0; /*Browser instance opens successfully*/
			break Block;
		}
		else
		{
			actVal= 1;/*Failed to open Browser instance*/
			break Block;
		}
		
	}
	}
	catch (SeleniumException sexp)
	{
		Reporter.log(sexp.getMessage());	
	} 
	
	returnVal=STFunctionLibrary.stRetValDes(expVal, actVal, "stOpenBrowserInstance",flow, hm);
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
	}
	return returnVal; 
	
    }
  //********************************[ stOpenBrowserInstance Function Starts ]********************************
    
  //********************************[ stLaunchOauthWidget Function Starts ]************************************
    
    /*********************************************************************************************
     * Function used for launching Widget.
     * @param ExpVal - 0 for successful launch, 1 for Error
	 * @param flow - "" for continue or "STOP,TC_ID" for stop the execution of script if function fails.
	 * @return Pass or FAIL
     *********************************************************************************************/
    public String stLaunchOauthWidget(String targetXPath,String verifyXPath,int expVal, String flow)
    { 
       	int actVal=1000;
		String returnVal=null;
		Block :{	
    	
	        STCommonLibrary comLib= new STCommonLibrary();	    
        	comLib.stWaitForElement(OAUTH_WIDGET,20);
	   	    
        	comLib.stClickAndVerify(targetXPath, verifyXPath, expVal, flow);        	
        	try {
    			Thread.sleep(10000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    		String Windowtitle = browser.getText(verifyXPath);
    		
    		if (Windowtitle.contains("Share this with your friends")){
    	    	actVal=0;
    	    	break Block;				
			}
			else{
				actVal=1;
				break Block;
			}
		}

	returnVal=STFunctionLibrary.stRetValDes(expVal, actVal, "stLaunchOauthWidget",flow, hm);
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
	}
	return returnVal;
    
    }
  //********************************[ stLaunchOauthWidget Function Ends ]************************************
    
} 
