package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static junit.framework.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import org.testng.Reporter;
import com.thoughtworks.selenium.SeleniumException;

public class STLogin {
	
	// ********************[ stSharethisLogin function Start ]*************************
	/***************************************************************************
	    * stSharethisLogin function is used to login as Sharethis account. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - User is successfully signed in. <br>
	    * 1 - Username is incorrect.<br>
	    * -1 - Email Not found. <br>
	    * -2 - Username and Password is Invalid. <br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stSharethisLogin(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	String widgetType = hm.get("WidgetType");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
		
    		xPath.add(SIGNIN_EMAIL_LABEL);
    		errorMsg.add("'Email Address' field not present");
    	
	    	xPath.add(SIGNIN_PASSWORD_LABEL);
	    	errorMsg.add("Password field not present"); 
	    	
	    	xPath.add(SIGNIN_BUTTON);
	    	errorMsg.add("Sign In Button not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
		
		Block:
        {			        	
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_EMAIL_LABEL);
				browser.click(SIGNIN_EMAIL_LABEL);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_EMAIL, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_PASSWORD_LABEL);
				browser.click(SIGNIN_PASSWORD_LABEL);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Click on Sign in Button */						
	    	browser.click(SIGNIN_BUTTON);
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_ERROR_PANEL) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_ERROR_PANEL);
				System.out.println(errorpanelmsg);
				if(errorpanelmsg.contains("Username and Password combination you entered " +
						"does not match our records."))
					
				{
					actVal= -2; /*Invalid username/Password*/
					browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
					break Block;
				}
				else if (browser.isElementPresent(SIGNIN_ERROR_PANEL))
				{
					String errorpanelmsg1 = browser.getText(SIGNIN_ERROR_PANEL);
					if (errorpanelmsg1.contains("There is no ShareThis account registered with " +
						"this email address."))
					{
						actVal= -1; /*Email Not found*/
						browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
						break Block;
					}
				}
					
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);
			
			if (widgetType.equalsIgnoreCase("OAuth"))
			{	/* Verifying links on OAuth Widget */
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(COMMON_SIGN_OUT_LINK);
				browser.isElementPresent(EMAIL_GO_BACK_LINK);
				
				browser.click(EMAIL_GO_BACK_LINK);
							
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
				errorMsg.add("Email Label on Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				
				/* Fetching Full Name */
	            String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_LABEL);
	            System.out.println("Text!"+stFullName);
	            
	            if(stFullName.contains(welcomelabel))
	            {
	            	actVal= 0; /*Sign in successful with given username*/
	            	break Block;
	            }
	            else
	            {
	            	actVal= 1; /*Username is incorrect*/
	            	break Block;
	            } 
				
			}else
			{
			/* Verifying links on OAuth Widget */
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
			browser.isElementPresent(EMAIL_GO_BACK_LINK);
			
			browser.click(EMAIL_GO_BACK_LINK);
						
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
			System.out.println("Email label found on widget");
			
			/* Fetching Full Name */
            String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            System.out.println(stFullName);
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect*/
            	break Block;
            } 
			
        }  	
        }

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharethisLogin",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal;
	}
	// ********************[ stSharethisLogin function End ]*************************
	
	// ********************[ stFacebookLogin function Start ]*************************
	/***************************************************************************
	    * stFacebookLogin function is used to login as Facebook account. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - User is successfully signed in with Facebook account. <br>
	    * 1 - Username is incorrect.<br>
	    * -1 - Incorrect password. <br>
	    * -2 - Incorrect Username. <br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stFacebookLogin(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	String widgetType = hm.get("WidgetType");

   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
	
	String stFullName = null;
   	
   	try {		
    		
		
		Block:
        {	
   			xPath.add(SIGNIN_FACEBOOK_ICON);
   			errorMsg.add("'Facebook' button not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();
   			
   			comLib.stClickAndVerify(SIGNIN_FACEBOOK_ICON, SIGNIN_FACEBOOK_USERNAME, 0, "");
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_FACEBOOK_USERNAME);
				browser.click(SIGNIN_FACEBOOK_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_FACEBOOK_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_FACEBOOK_PASSWORD);
				browser.click(SIGNIN_FACEBOOK_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_FACEBOOK_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_FACEBOOK_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_FACEBOOK_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_FACEBOOK_ERROR_PANEL) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_FACEBOOK_ERROR_PANEL);
				if(errorpanelmsg.contains("The username you entered does not belong to any account."))
					
				{
					actVal= -2; /*Incorrect Username*/
					break Block;
				}
				else if (browser.isElementPresent(SIGNIN_FACEBOOK_ERROR_PANEL))
				{
					String errorpanelmsg1 = browser.getText(SIGNIN_ERROR_PANEL);
					if (errorpanelmsg1.contains("The password you entered is incorrect. " +
							"Please try again (make sure your caps lock is off)."))
					{
						actVal= -1; /*Incorrect Password.*/
						break Block;
					}
				}
					
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);			
			
			if(widgetType.equalsIgnoreCase("OAuth"))
			{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
				
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
		        /* Fetching Full Name */
			    stFullName = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
			    
			    if(stFullName.contains(welcomelabel))
			    {
			        actVal= 2; /* Sign in successful with given username */
			        break Block;
			    }
			    else
			    {
			        actVal= -3; /* Username is incorrect */
			        break Block;
			    } 
				
			}else
			{
			/*Checking Welcome Label for Reskinned Widget*/
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Re-skinned Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			xPath.clear();
			errorMsg.clear();
			
			/* Fetching Full Name */
            stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username on Re-skinned widget*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect on reskinned widget*/
            	break Block;
            } 
		}
			/*Checking Welcome Label for OAuth Widget*/ 		

   	}
        
   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stFacebookLogin",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stFacebookLogin function End ]*************************
	
	// ********************[ stGoogleLogin function Start ]*************************
	/***************************************************************************
	    * stGoogleLogin function is used to login as Google account. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - User is successfully signed in with Google account. <br>
	    * 1 - Username is incorrect.<br>
	    * -1 - Incorrect Password. <br>
	    * -2 - Incorrecft Username. <br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stGoogleLogin(int dataId,int ExpVal,String flow )
    {
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String welcomeLabel = hm.get("WelcomeLabel");
    STCommonLibrary comLib=new STCommonLibrary();
    String widgetType = hm.get("WidgetType");
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try 
    {
          xPath.add(SIGNIN_GOOGLE_ICON);
          errorMsg.add("'Google' button not present");
    
          comLib.stVerifyObjects(xPath, errorMsg, flow); 
          
          xPath.clear();
          errorMsg.clear();
    
  Block:
  {	
      comLib.stClickAndVerify(SIGNIN_GOOGLE_ICON, SIGNIN_GOOGLE_USERNAME, 0, "");  
      
    /*Typing username and Password*/
    if(!"".equals(emailadrs))
    {
          browser.focus(SIGNIN_GOOGLE_USERNAME);
          browser.click(SIGNIN_GOOGLE_USERNAME);
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
                
          browser.type(SIGNIN_GOOGLE_USERNAME, emailadrs );
          
    }
    
    if(!"".equals(password))
    {
          browser.focus(SIGNIN_GOOGLE_PASSWORD);
                browser.click(SIGNIN_GOOGLE_PASSWORD);
          
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
          browser.type(SIGNIN_GOOGLE_PASSWORD, password );                                                                      
    }
    
    /*Verify the Sign in Button */
    xPath.add(SIGNIN_GOOGLE_SIGNIN_BUTTON);
    errorMsg.add("Sign in Button is not present on Google Sign-in page");    
    comLib.stVerifyObjects(xPath, errorMsg, "STOP");
    
    xPath.clear();
    errorMsg.clear();
    
          /*Click on Sign in Button */
    
          browser.click(SIGNIN_GOOGLE_SIGNIN_BUTTON);
          
          try {
               Thread.sleep(60000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }     
          
    
    String allwindowtitle [] = browser.getAllWindowTitles();
    int numberOfwindows= allwindowtitle.length;
    System.out.println(" No of open windows= " + numberOfwindows);
    
    /* Selecting the native window */
    if (numberOfwindows <= 1)
    {
          browser.selectWindow(null);
    }else 
    	/* Breaking the block if there's an error on Google Sign in Window */
    {
          actVal = 3; /* Unknown error occurred on Google Sign in page */   
          break Block;
    }
    
    if (widgetType.equalsIgnoreCase("OAuth"))
    {/* Performing verification for Re-OAuth widget */
    	 
    	/*Verification of Sign out link on widget*/
        browser.isElementPresent(COMMON_SIGN_OUT_LINK);
                          
        /*Verification of Email Label on widget*/
        xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
        errorMsg.add("Email Label on Widget is not present");
        comLib.stVerifyObjects(xPath, errorMsg, "");

        
        /* Fetching Full Name */
    String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_LABEL);
    
    
    if(stFullName.contains(welcomeLabel))
    {
        actVal= 0; /* Sign in successful with given username */
        break Block;
    }
    else
    {
        actVal= 1; /* Username is incorrect */
        break Block;
    } 
    	
    }else
    {/* Performing verification for Re-skinned widget */
    
          /*Verification of Sign out link on widget*/
          browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
                            
          /*Verification of Email Label on widget*/
          xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
          errorMsg.add("Email Label on Widget is not present");
          comLib.stVerifyObjects(xPath, errorMsg, "");

          
          /* Fetching Full Name */
      String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
      
      
      if(stFullName.contains(welcomeLabel))
      {
          actVal= 0; /* Sign in successful with given username */
          break Block;
      }
      else
      {
          actVal= 1; /* Username is incorrect */
          break Block;
      } 
          
  }
  }
          
    }
          catch (SeleniumException sexp)
          {
              Reporter.log(sexp.getMessage());   
          } 
    returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stGoogleLogin",flow, hm);    
    if(flow.contains("STOP"))
    {
          assertEquals("PASS",returnVal);
    }
    
    return returnVal;
    }
	// ********************[ stGoogleLogin function End ]*************************
	
	// ********************[ stTwitterLogin function Start ]*************************
	/***************************************************************************
	    * stTwitterLogin function is used to login as Twitter account. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - User is successfully signed in with Twitter account. <br>
	    * 1 - Username is incorrect.<br>
	    * -1 - Incorrect password. <br>
	    * -2 - Incorrect Username. <br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stTwitterLogin(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {		
    		
		
		Block:
        {	
   			xPath.add(SIGNIN_TWITTER_ICON);
   			errorMsg.add("'Twitter' button not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();
   			
   			comLib.stClickAndVerify(SIGNIN_TWITTER_ICON, SIGNIN_TWITTER_USERNAME, 0, "");
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_TWITTER_USERNAME);
				browser.click(SIGNIN_TWITTER_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_TWITTER_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_TWITTER_PASSWORD);
				browser.click(SIGNIN_TWITTER_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_TWITTER_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_TWITTER_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_TWITTER_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_TWITTER_ERROR_PANEL) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_TWITTER_ERROR_PANEL);
				if(errorpanelmsg.contains("Invalid user name or password. Return to twitter.com"))
					
				{
					actVal= -2; /*Incorrect Username oe Password*/
					break Block;
				}
				else 
					{
						actVal= -1; /*Incorrect Password.*/
						break Block;
					}
					
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);
			
			
			
			/*Checking Welcome Label for Re-skinned Widget*/
			if (browser.isElementPresent(RESKINNED_WIDGET_EMAIL_LABEL))
			{
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
			System.out.println("Email label found on widget");
			
			/* Fetching Full Name */
            String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            System.out.println(stFullName);
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect*/
            	break Block;
            } 
			}
			/*Checking Welcome Label for OAuth Widget*/
			else
			{
				if (browser.isElementPresent(OAUTH_WIDGET_EMAIL_LABEL))
				{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
				
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
				/* Fetching Full Name */
	            String Username = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
	            System.out.println("Username : " +Username);
	            System.out.println("welcomelabel" +welcomelabel);
	            if(Username.contains(welcomelabel))
	            {
	            	actVal= 2; /*Sign in successful with given username on OAuth widget*/
	            	break Block;
	            }
	            else
	            {
	            	actVal= -3; /*Username is incorrect on Oauth widget*/
	            	break Block;
	            } 
			}
        }  
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stTwitterLogin",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stTwitterLogin function End ]*************************
	
	// ********************[ stLinkedInLogin function Start ]*************************
	/***************************************************************************
	    * stLinkedInLogin function is used to login as LinkedIn account. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - User is successfully signed in with LinkedIn account. <br>
	    * 1 - Username is incorrect.<br>
	    * -1 - Incorrect password. <br>
	    * -2 - Incorrect Username. <br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stLinkedInLogin(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {		
    		
		
		Block:
        {	
   			xPath.add(SIGNIN_LINKEDIN_ICON);
   			errorMsg.add("'Linkedin' button not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();
   			
   			comLib.stClickAndVerify(SIGNIN_LINKEDIN_ICON, SIGNIN_LINKEDIN_USERNAME, 0, "");
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_LINKEDIN_USERNAME);
				browser.click(SIGNIN_LINKEDIN_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_LINKEDIN_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_LINKEDIN_PASSWORD);
				browser.click(SIGNIN_LINKEDIN_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_LINKEDIN_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_LINKEDIN_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_LINKEDIN_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_LINKEDIN_ERROR_PANEL1) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_LINKEDIN_ERROR_PANEL1);
				if(errorpanelmsg.contains("Please correct the marked field(s) below."))
					
				{
					actVal= -2; /*Incorrect Username or Password*/
					break Block;
				}
				else if (Numberofwindows > 1 && browser.isElementPresent(SIGNIN_LINKEDIN_ERROR_PANEL2))
				{
					String errorpanelmsg2 = browser.getText(SIGNIN_LINKEDIN_ERROR_PANEL2);
					if (errorpanelmsg2 .contains("We were unable to find the authorization token"))				
					{
						actVal= -1; /*Invalid Credetials.*/
						break Block;
					}
				}	
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);			
						
			/*Verification of Email Label on Reskinned widget*/
			if (browser.isElementPresent(RESKINNED_WIDGET_EMAIL_LABEL))
			{
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
				
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
			System.out.println("Email label found on widget");
			
			/* Fetching Full Name */
            String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            System.out.println(stFullName);
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect*/
            	break Block;
            } 
		}
			/*Checking Welcome Label for OAuth Widget*/
			else
			{
				if (browser.isElementPresent(OAUTH_WIDGET_EMAIL_LABEL))
				{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
							
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
				/* Fetching Full Name */
	            String Username = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
	            System.out.println(Username);
	            if(welcomelabel.contains(Username))
	            {
	            	actVal= 2; /*Sign in successful with given username on OAuth widget*/
	            	break Block;
	            }
	            else
	            {
	            	actVal= -3; /*Username is incorrect on Oauth widget*/
	            	break Block;
	            } 
			}
        }
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stLinkedInLogin",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stLinkedInLogin function End ]*************************
	
    // ********************[ stYahooLogin function Start ]*************************
    public String stYahooLogin(int dataId,int ExpVal,String flow )
    {           
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String welcomelabel = hm.get("WelcomeLabel");
    STCommonLibrary comLib=new STCommonLibrary();
    String widgetType =hm.get("WidgetType");
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try {
          
          xPath.add(SIGNIN_YAHOO_ICON);
          errorMsg.add("'Yahoo' button field not present");
          
          comLib.stVerifyObjects(xPath, errorMsg, flow);  
          xPath.clear();
          errorMsg.clear();
          
          Block:
      {         
                comLib.stClickAndVerify(SIGNIN_YAHOO_ICON, SIGNIN_YAHOO_USERNAME, 0, "");
          /*Typing username and Password*/
          if(!"".equals(emailadrs))
          {
                browser.focus(SIGNIN_YAHOO_USERNAME);
                      browser.click(SIGNIN_YAHOO_USERNAME);
                      try {
                            Thread.sleep(3000);
                      } catch (InterruptedException e) {
                            e.printStackTrace();
                      }
                      
                browser.type(SIGNIN_YAHOO_USERNAME, emailadrs );
                
          }
          
          if(!"".equals(password))
          {
                browser.focus(SIGNIN_YAHOO_PASSWORD);
                      browser.click(SIGNIN_YAHOO_PASSWORD);
                
                      try {
                            Thread.sleep(3000);
                      } catch (InterruptedException e) {
                            e.printStackTrace();
                      }
                browser.type(SIGNIN_YAHOO_PASSWORD, password );                                                                   
          }
           /*Verify the Sign in Button */
            	xPath.add(SIGNIN_YAHOO_SIGNIN_BUTTON);
                errorMsg.add("Sign in Button is not present on Yahoo Sign-in page");    
                comLib.stVerifyObjects(xPath, errorMsg, "STOP");
  
                xPath.clear();
                errorMsg.clear();
          /*Click on Sign in Button */  
          browser.click(SIGNIN_YAHOO_SIGNIN_BUTTON);
          
          try {
                      Thread.sleep(5000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }     
                
          String allwindowtitle [] = browser.getAllWindowTitles();
          int Numberofwindows= allwindowtitle.length;
          System.out.println("No of open windows=" + Numberofwindows);
          
          /*Checking for Error Panel for Invalid username & Password*/
                if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_YAHOO_ERROR_PANEL1) )
                {
                      String errorpanelmsg = browser.getText(SIGNIN_YAHOO_ERROR_PANEL1);
                      if(errorpanelmsg.contains("Invalid ID or password. " +
                                  "Please try again using your full Yahoo! ID."))
                            
                      {
                            actVal= -2; /*Invalid username/Password*/
                            break Block;
                      }
                      else if (browser.isElementPresent(SIGNIN_YAHOO_ERROR_PANEL2))
                      {
                            String errorpanelmsg1 = browser.getText(SIGNIN_YAHOO_ERROR_PANEL2);
                            if (errorpanelmsg1.contains("Please enter your password"))
                            {
                                  actVal= 2; /*Password field blank*/
                                  break Block;
                            }
                      }
                            
                }  
                if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_YAHOO_AGREE_BUTTON) )
                {
                      browser.click(SIGNIN_YAHOO_AGREE_BUTTON);
                }
                try {
                    Thread.sleep(80000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    

                /*IF no Error occurs*/                    
                browser.selectWindow(null);    
                
                if (widgetType.equalsIgnoreCase("OAuth"))
                {/* Performing verification for OAuth widget */
                	 
                	/*Verification of Sign out link on widget*/
                    browser.isElementPresent(COMMON_SIGN_OUT_LINK);
                    
                    /*Verification of Email Label on widget*/
                    xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
                    errorMsg.add("Email Label on Widget is not present");
                    comLib.stVerifyObjects(xPath, errorMsg, "");

                    
                    /* Fetching Full Name */
                String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_LABEL);
                System.out.println(stFullName);
                
                if(stFullName.contains(welcomelabel))
                {
                    actVal= 2; /* Sign in successful with given username */
                    break Block;
                    
                }
                else
                {
                    actVal= -3; /* Username is incorrect */
                    break Block;
                    
                } 
                	
                }else
                {
                /*Verification of Sign out link on widget*/
                browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
                                  
                /*Verification of Email Label on widget*/
                xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
                errorMsg.add("Email Label on Widget is not present");
                comLib.stVerifyObjects(xPath, errorMsg, "");
                
                
                
                /* Fetching Full Name */
          String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);

          if(welcomelabel.contains(stFullName))
          {
                actVal= 0; /*Sign in successful with given username*/
                break Block;
          }
          else
          {
                actVal= 1; /*Username is incorrect*/
                break Block;
          } 
                
      }  
      }
    }
  catch (SeleniumException sexp)
  {
      Reporter.log(sexp.getMessage());   
  } 
returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stYahooLogin",flow, hm);    
if(flow.contains("STOP"))
{
  assertEquals("PASS",returnVal);
}

return returnVal;
}
    // ********************[ stYahooLogin function End ]*************************
    
 // ********************[ stFacebookLoginEmailScreen function End ]*************************    
    public String stFacebookLoginEmailScreen(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	String widgetType = hm.get("WidgetType");

   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
	
	String stFullName = null;
   	
   	try {		
    		
		
		Block:
        {	
   			xPath.add(SIGNIN_FACEBOOK_USERNAME);
   			errorMsg.add("Username field on Facebook not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();
   			
//   			comLib.stClickAndVerify(SIGNIN_FACEBOOK_ICON, SIGNIN_FACEBOOK_USERNAME, 0, "");
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_FACEBOOK_USERNAME);
				browser.click(SIGNIN_FACEBOOK_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_FACEBOOK_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_FACEBOOK_PASSWORD);
				browser.click(SIGNIN_FACEBOOK_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_FACEBOOK_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_FACEBOOK_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_FACEBOOK_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_FACEBOOK_ERROR_PANEL) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_FACEBOOK_ERROR_PANEL);
				if(errorpanelmsg.contains("The username you entered does not belong to any account."))
					
				{
					actVal= -2; /*Incorrect Username*/
					break Block;
				}
				else if (browser.isElementPresent(SIGNIN_FACEBOOK_ERROR_PANEL))
				{
					String errorpanelmsg1 = browser.getText(SIGNIN_ERROR_PANEL);
					if (errorpanelmsg1.contains("The password you entered is incorrect. " +
							"Please try again (make sure your caps lock is off)."))
					{
						actVal= -1; /*Incorrect Password.*/
						break Block;
					}
				}
					
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);			
			
			if(widgetType.equalsIgnoreCase("OAuth"))
			{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
				
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
		        /* Fetching Full Name */
			    stFullName = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
			    
			    if(stFullName.contains(welcomelabel))
			    {
			        actVal= 2; /* Sign in successful with given username */
			        break Block;
			    }
			    else
			    {
			        actVal= -3; /* Username is incorrect */
			        break Block;
			    } 
				
			}else
			{
			/*Checking Welcome Label for Reskinned Widget*/
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Re-skinned Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			xPath.clear();
			errorMsg.clear();
			
			/* Fetching Full Name */
            stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username on Re-skinned widget*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect on reskinned widget*/
            	break Block;
            } 
		}
			/*Checking Welcome Label for OAuth Widget*/ 		

   	}
        
   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stFacebookLoginEmailScreen",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stFacebookLoginEmailScreen function End ]*************************
    
 // ********************[ stGoogleLoginEmailScreen function End ]*************************    
    public String stGoogleLoginEmailScreen(int dataId,int ExpVal,String flow )
    {
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String welcomeLabel = hm.get("WelcomeLabel");
    STCommonLibrary comLib=new STCommonLibrary();
    String widgetType = hm.get("WidgetType");
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try 
    {
          xPath.add(SIGNIN_GOOGLE_USERNAME);
          errorMsg.add("Username field on Google page not present");
    
          comLib.stVerifyObjects(xPath, errorMsg, flow); 
          
          xPath.clear();
          errorMsg.clear();
    
  Block:
  {	    
      
    /*Typing username and Password*/
    if(!"".equals(emailadrs))
    {
          browser.focus(SIGNIN_GOOGLE_USERNAME);
          browser.click(SIGNIN_GOOGLE_USERNAME);
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
                
          browser.type(SIGNIN_GOOGLE_USERNAME, emailadrs );
          
    }
    
    if(!"".equals(password))
    {
          browser.focus(SIGNIN_GOOGLE_PASSWORD);
                browser.click(SIGNIN_GOOGLE_PASSWORD);
          
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
          browser.type(SIGNIN_GOOGLE_PASSWORD, password );                                                                      
    }
    
    /*Verify the Sign in Button */
    xPath.add(SIGNIN_GOOGLE_SIGNIN_BUTTON);
    errorMsg.add("Sign in Button is not present on Google Sign-in page");    
    comLib.stVerifyObjects(xPath, errorMsg, "STOP");
    
    xPath.clear();
    errorMsg.clear();
    
          /*Click on Sign in Button */
    
          browser.click(SIGNIN_GOOGLE_SIGNIN_BUTTON);
          
          try {
               Thread.sleep(60000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }     
          
    
    String allwindowtitle [] = browser.getAllWindowTitles();
    int numberOfwindows= allwindowtitle.length;
    System.out.println(" No of open windows= " + numberOfwindows);
    
    /* Selecting the native window */
    if (numberOfwindows <= 1)
    {
          browser.selectWindow(null);
    }else 
    	/* Breaking the block if there's an error on Google Sign in Window */
    {
          actVal = 3; /* Unknown error occurred on Google Sign in page */   
          break Block;
    }
    
    if (widgetType.equalsIgnoreCase("OAuth"))
    {/* Performing verification for Re-OAuth widget */
    	 
    	/*Verification of Sign out link on widget*/
        browser.isElementPresent(COMMON_SIGN_OUT_LINK);
                          
        /*Verification of Email Label on widget*/
        xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
        errorMsg.add("Email Label on Widget is not present");
        comLib.stVerifyObjects(xPath, errorMsg, "");

        
        /* Fetching Full Name */
    String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_LABEL);
    
    
    if(stFullName.contains(welcomeLabel))
    {
        actVal= 0; /* Sign in successful with given username */
        break Block;
    }
    else
    {
        actVal= 1; /* Username is incorrect */
        break Block;
    } 
    	
    }else
    {/* Performing verification for Re-skinned widget */
    
          /*Verification of Sign out link on widget*/
          browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
                            
          /*Verification of Email Label on widget*/
          xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
          errorMsg.add("Email Label on Widget is not present");
          comLib.stVerifyObjects(xPath, errorMsg, "");

          
          /* Fetching Full Name */
      String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
      
      
      if(stFullName.contains(welcomeLabel))
      {
          actVal= 0; /* Sign in successful with given username */
          break Block;
      }
      else
      {
          actVal= 1; /* Username is incorrect */
          break Block;
      } 
          
  }
  }
          
    }
          catch (SeleniumException sexp)
          {
              Reporter.log(sexp.getMessage());   
          } 
    returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stGoogleLoginEmailScreen",flow, hm);    
    if(flow.contains("STOP"))
    {
          assertEquals("PASS",returnVal);
    }
    
    return returnVal;
    }
	// ********************[ stGoogleLoginEmailScreen function End ]*************************
    
 // ********************[ stTwitterLoginEmailScreen function End ]*************************    
    public String stTwitterLoginEmailScreen(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {	    		
		
		Block:
        {	
   			xPath.add(SIGNIN_TWITTER_USERNAME);
   			errorMsg.add("Username field on Twitter page not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();   			
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_TWITTER_USERNAME);
				browser.click(SIGNIN_TWITTER_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_TWITTER_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_TWITTER_PASSWORD);
				browser.click(SIGNIN_TWITTER_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_TWITTER_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_TWITTER_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_TWITTER_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_TWITTER_ERROR_PANEL) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_TWITTER_ERROR_PANEL);
				if(errorpanelmsg.contains("Invalid user name or password. Return to twitter.com"))
					
				{
					actVal= -2; /*Incorrect Username oe Password*/
					break Block;
				}
				else 
					{
						actVal= -1; /*Incorrect Password.*/
						break Block;
					}
					
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);			
			
			
			/*Checking Welcome Label for Re-skinned Widget*/
			if (browser.isElementPresent(RESKINNED_WIDGET_EMAIL_LABEL))
			{
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
			System.out.println("Email label found on widget");
			
			/* Fetching Full Name */
            String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            System.out.println(stFullName);
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect*/
            	break Block;
            } 
			}
			/*Checking Welcome Label for OAuth Widget*/
			else
			{
				if (browser.isElementPresent(OAUTH_WIDGET_EMAIL_LABEL))
				{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
				
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
				/* Fetching Full Name */
	            String Username = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
	            System.out.println("Username : " +Username);
	            System.out.println("welcomelabel" +welcomelabel);
	            if(Username.contains(welcomelabel))
	            {
	            	actVal= 2; /*Sign in successful with given username on OAuth widget*/
	            	break Block;
	            }
	            else
	            {
	            	actVal= -3; /*Username is incorrect on Oauth widget*/
	            	break Block;
	            } 
			}
        }  
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stTwitterLoginEmailScreen",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stTwitterLoginEmailScreen function End ]*************************
    
	// ********************[ stLinkedInLoginEmailScreen function End ]*************************    
    public String stLinkedInLoginEmailScreen(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Login");
   	String emailadrs = hm.get("EmailAddress");		
   	String password = hm.get("Password");
   	String welcomelabel = hm.get("WelcomeLabel");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {		
    		
		
		Block:
        {	
   			xPath.add(SIGNIN_LINKEDIN_USERNAME);
   			errorMsg.add("Username field on LinkedIn page not present");
	
   			comLib.stVerifyObjects(xPath, errorMsg, flow);	
   			xPath.clear();
   			errorMsg.clear();   			
   		
        	/*Typing username and Password*/
	    	if(!"".equals(emailadrs))
	    	{
	    		browser.focus(SIGNIN_LINKEDIN_USERNAME);
				browser.click(SIGNIN_LINKEDIN_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(SIGNIN_LINKEDIN_USERNAME, emailadrs );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(SIGNIN_LINKEDIN_PASSWORD);
				browser.click(SIGNIN_LINKEDIN_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(SIGNIN_LINKEDIN_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Verify and Click on Sign in Button */	
	    	browser.isElementPresent(SIGNIN_LINKEDIN_SIGNIN_BUTTON);
	    	
	    	browser.click(SIGNIN_LINKEDIN_SIGNIN_BUTTON);
	    	try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	System.out.println("No of open windows=" + Numberofwindows);
	    	
            /*Checking for Error Panel for Invalid username & Password*/
			if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_LINKEDIN_ERROR_PANEL1) )
			{
				String errorpanelmsg = browser.getText(SIGNIN_LINKEDIN_ERROR_PANEL1);
				if(errorpanelmsg.contains("Please correct the marked field(s) below."))
					
				{
					actVal= -2; /*Incorrect Username or Password*/
					break Block;
				}
				else if (Numberofwindows > 1 && browser.isElementPresent(SIGNIN_LINKEDIN_ERROR_PANEL2))
				{
					String errorpanelmsg2 = browser.getText(SIGNIN_LINKEDIN_ERROR_PANEL2);
					if (errorpanelmsg2 .contains("We were unable to find the authorization token"))				
					{
						actVal= -1; /*Invalid Credetials.*/
						break Block;
					}
				}	
			}  
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);			
						
			/*Verification of Email Label on Reskinned widget*/
			if (browser.isElementPresent(RESKINNED_WIDGET_EMAIL_LABEL))
			{
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
				
			xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
			System.out.println("Email label found on widget");
			
			/* Fetching Full Name */
            String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);
            System.out.println(stFullName);
            if(welcomelabel.contains(stFullName))
            {
            	actVal= 0; /*Sign in successful with given username*/
            	break Block;
            }
            else
            {
            	actVal= 1; /*Username is incorrect*/
            	break Block;
            } 
		}
			/*Checking Welcome Label for OAuth Widget*/
			else
			{
				if (browser.isElementPresent(OAUTH_WIDGET_EMAIL_LABEL))
				{
				/*Verification of Sign out link on widget*/
				browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
							
				/*Verification of Email Label on widget*/
				xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
				errorMsg.add("Username on OAuth Widget is not present");
				comLib.stVerifyObjects(xPath, errorMsg, "");
				xPath.clear();
				errorMsg.clear();
				
				/* Fetching Full Name */
	            String Username = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
	            System.out.println(Username);
	            if(welcomelabel.contains(Username))
	            {
	            	actVal= 2; /*Sign in successful with given username on OAuth widget*/
	            	break Block;
	            }
	            else
	            {
	            	actVal= -3; /*Username is incorrect on Oauth widget*/
	            	break Block;
	            } 
			}
        }
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stLinkedInLoginEmailScreen",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stLinkedInLoginEmailScreen function End ]*************************
    
    // ********************[ stYahooLoginEmailScreen function Start ]*************************
    public String stYahooLoginEmailScreen(int dataId,int ExpVal,String flow )
    {           
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String welcomelabel = hm.get("WelcomeLabel");
    STCommonLibrary comLib=new STCommonLibrary();
    String widgetType =hm.get("WidgetType");
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try {
          
          xPath.add(SIGNIN_YAHOO_USERNAME);
          errorMsg.add("Username field on Yahoo page not present");
          
          comLib.stVerifyObjects(xPath, errorMsg, flow);  
          xPath.clear();
          errorMsg.clear();
          
          Block:
      {                
          /*Typing username and Password*/
          if(!"".equals(emailadrs))
          {
                browser.focus(SIGNIN_YAHOO_USERNAME);
                      browser.click(SIGNIN_YAHOO_USERNAME);
                      try {
                            Thread.sleep(3000);
                      } catch (InterruptedException e) {
                            e.printStackTrace();
                      }
                      
                browser.type(SIGNIN_YAHOO_USERNAME, emailadrs );
                
          }
          
          if(!"".equals(password))
          {
                browser.focus(SIGNIN_YAHOO_PASSWORD);
                      browser.click(SIGNIN_YAHOO_PASSWORD);
                
                      try {
                            Thread.sleep(3000);
                      } catch (InterruptedException e) {
                            e.printStackTrace();
                      }
                browser.type(SIGNIN_YAHOO_PASSWORD, password );                                                                   
          }
           /*Verify the Sign in Button */
            	xPath.add(SIGNIN_YAHOO_SIGNIN_BUTTON);
                errorMsg.add("Sign in Button is not present on Yahoo Sign-in page");    
                comLib.stVerifyObjects(xPath, errorMsg, "STOP");
  
                xPath.clear();
                errorMsg.clear();
          /*Click on Sign in Button */  
          browser.click(SIGNIN_YAHOO_SIGNIN_BUTTON);
          
          try {
                      Thread.sleep(15000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }     
                
          String allwindowtitle [] = browser.getAllWindowTitles();
          System.out.println("**" + allwindowtitle + "**");
          int Numberofwindows= allwindowtitle.length;
          System.out.println("No of open windows=" + Numberofwindows);
          
          /*Checking for Error Panel for Invalid username & Password*/
                if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_YAHOO_ERROR_PANEL1) )
                {
                      String errorpanelmsg = browser.getText(SIGNIN_YAHOO_ERROR_PANEL1);
                      if(errorpanelmsg.contains("Invalid ID or password. " +
                                  "Please try again using your full Yahoo! ID."))
                            
                      {
                            actVal= -2; /*Invalid username/Password*/
                            break Block;
                      }
                      else if (browser.isElementPresent(SIGNIN_YAHOO_ERROR_PANEL2))
                      {
                            String errorpanelmsg1 = browser.getText(SIGNIN_YAHOO_ERROR_PANEL2);
                            if (errorpanelmsg1.contains("Please enter your password"))
                            {
                                  actVal= 2; /*Password field blank*/
                                  break Block;
                            }
                      }
                            
                }  
                if ( Numberofwindows > 1 && browser.isElementPresent(SIGNIN_YAHOO_AGREE_BUTTON) )
                {
                      browser.click(SIGNIN_YAHOO_AGREE_BUTTON);
                }
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }    

                /*IF no Error occurs*/                    
                browser.selectWindow(null);    
                
                if (widgetType.equalsIgnoreCase("OAuth"))
                {/* Performing verification for OAuth widget */
                	 
                	/*Verification of Sign out link on widget*/
                    browser.isElementPresent(COMMON_SIGN_OUT_LINK);
                    
                    /*Verification of Email Label on widget*/
                    xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
                    errorMsg.add("Email Label on Widget is not present");
                    comLib.stVerifyObjects(xPath, errorMsg, "");

                    
                    /* Fetching Full Name */
                String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_LABEL);
                
                if(stFullName.contains(welcomelabel))
                {
                    actVal= 2; /* Sign in successful with given username */
                    break Block;
                    
                }
                else
                {
                    actVal= -3; /* Username is incorrect */
                    break Block;
                    
                } 
                	
                }else
                {
                /*Verification of Sign out link on widget*/
                browser.isElementPresent(RESKINNED_WIDGET_SIGNOUT_LINK);
                                  
                /*Verification of Email Label on widget*/
                xPath.add(RESKINNED_WIDGET_EMAIL_LABEL);
                errorMsg.add("Email Label on Widget is not present");
                comLib.stVerifyObjects(xPath, errorMsg, "");
                
                
                
                /* Fetching Full Name */
          String stFullName = browser.getText(RESKINNED_WIDGET_EMAIL_LABEL);

          if(welcomelabel.contains(stFullName))
          {
                actVal= 0; /*Sign in successful with given username*/
                break Block;
          }
          else
          {
                actVal= 1; /*Username is incorrect*/
                break Block;
          } 
                
      }  
      }
    }
    	catch (SeleniumException sexp)
  {
    	Reporter.log(sexp.getMessage());   
  } 
    	returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stYahooLoginEmailScreen",flow, hm);    
    	if(flow.contains("STOP"))
    {
    	assertEquals("PASS",returnVal);
    }

    	return returnVal;
    }
    // ********************[ stYahooLoginEmailScreen function End ]*************************
    
 // ********************[ stExternalEmailGoogleLogin function Start ]*************************    
    public String stExternalEmailGoogleLogin(int dataId,int ExpVal,String flow )
    {
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String confirmationtitle = hm.get("LoginConfirmationTitle");
    STCommonLibrary comLib=new STCommonLibrary();
    
    String stNewWindowTitle = null;
    
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try 
    {
          xPath.add(SIGNIN_GOOGLE_USERNAME);
          errorMsg.add("Username field on Google page not present");
    
          comLib.stVerifyObjects(xPath, errorMsg, flow); 
          
          xPath.clear();
          errorMsg.clear();
    
  Block:
  {	    
      
    /*Typing username and Password*/
    if(!"".equals(emailadrs))
    {
          browser.focus(SIGNIN_GOOGLE_USERNAME);
          browser.click(SIGNIN_GOOGLE_USERNAME);
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
                
          browser.type(SIGNIN_GOOGLE_USERNAME, emailadrs );
          
    }
    
    if(!"".equals(password))
    {
          browser.focus(SIGNIN_GOOGLE_PASSWORD);
                browser.click(SIGNIN_GOOGLE_PASSWORD);
          
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
          browser.type(SIGNIN_GOOGLE_PASSWORD, password );                                                                      
    }
    
    /*Verify the Sign in Button */
    xPath.add(SIGNIN_GOOGLE_SIGNIN_BUTTON);
    errorMsg.add("Sign in Button is not present on Google Sign-in page");    
    comLib.stVerifyObjects(xPath, errorMsg, "STOP");
    
    xPath.clear();
    errorMsg.clear();
    
          /*Click on Sign in Button */
    
          browser.click(SIGNIN_GOOGLE_SIGNIN_BUTTON);
          
          try {
               Thread.sleep(60000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }     
          
    
    String allwindowtitle [] = browser.getAllWindowTitles();
    int numberOfwindows= allwindowtitle.length;
    System.out.println(" No of open windows= " + numberOfwindows);
    
    /* Fetching Window Title */
    stNewWindowTitle = browser.getTitle();
    System.out.println(stNewWindowTitle);
    
    /* Selecting the native window */
    if (numberOfwindows <= 2)
    {
          browser.selectWindow(stNewWindowTitle);
    }else 
    	/* Breaking the block if there's an error on Google Sign in Window */
    {
          actVal = 3; /* Unknown error occurred on Google Sign in page */   
          break Block;
    }    
    
    if(stNewWindowTitle.contains(confirmationtitle))
    {
        actVal= 0; /* Sign in successful with given username */
        break Block;
    }
    else
    {
        actVal= 1; /* Username is incorrect */
        break Block;
    } 
    	
   	}          
    }
          catch (SeleniumException sexp)
          {
              Reporter.log(sexp.getMessage());   
          } 
    returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stExternalEmailGoogleLogin",flow, hm);    
    if(flow.contains("STOP"))
    {
          assertEquals("PASS",returnVal);
    }
    
    return returnVal;
    }
	// ********************[ stExternalEmailGoogleLogin function End ]*************************
    
 // ********************[ stExternalEmailYahooLogin function Start ]*************************    
    public String stExternalEmailYahooLogin(int dataId,int ExpVal,String flow )
    {
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String confirmationtitle = hm.get("LoginConfirmationTitle");
    STCommonLibrary comLib=new STCommonLibrary();
    
    String stNewWindowTitle = null;
    
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try 
    {
          xPath.add(SIGNIN_YAHOO_USERNAME_NEW);
          errorMsg.add("Username field on Yahoo page not present");
    
          comLib.stVerifyObjects(xPath, errorMsg, flow); 
          
          xPath.clear();
          errorMsg.clear();
    
  Block:
  {	    
      
    /*Typing username and Password*/
    if(!"".equals(emailadrs))
    {
          browser.focus(SIGNIN_YAHOO_USERNAME_NEW);
          browser.click(SIGNIN_YAHOO_USERNAME_NEW);
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
                
          browser.type(SIGNIN_YAHOO_USERNAME_NEW, emailadrs );
          
    }
    
    if(!"".equals(password))
    {
          browser.focus(SIGNIN_YAHOO_PASSWORD_NEW);
                browser.click(SIGNIN_YAHOO_PASSWORD_NEW);
          
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
          browser.type(SIGNIN_YAHOO_PASSWORD_NEW, password );                                                                      
    }
    
    /*Verify the Sign in Button */
    xPath.add(SIGNIN_YAHOO_SIGNIN_BUTTON);
    errorMsg.add("Sign in Button is not present on Yahoo Sign-in page");    
    comLib.stVerifyObjects(xPath, errorMsg, "STOP");
    
    xPath.clear();
    errorMsg.clear();
    
          /*Click on Sign in Button */
    
          browser.click(SIGNIN_YAHOO_SIGNIN_BUTTON);
          
          try {
               Thread.sleep(60000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }     
          
    
    String allwindowtitle [] = browser.getAllWindowTitles();
    int numberOfwindows= allwindowtitle.length;
    System.out.println(" No of open windows= " + numberOfwindows);
    
    /* Fetching Window Title */
    stNewWindowTitle = browser.getTitle();
    System.out.println(stNewWindowTitle);
    
    /* Selecting the native window */
    if (numberOfwindows <= 2)
    {
          browser.selectWindow(stNewWindowTitle);
    }else 
    	/* Breaking the block if there's an error on Google Sign in Window */
    {
          actVal = 3; /* Unknown error occurred on Google Sign in page */   
          break Block;
    }    
    
    if(stNewWindowTitle.contains(confirmationtitle))
    {
        actVal= 0; /* Sign in successful with given username */
        break Block;
    }
    else
    {
        actVal= 1; /* Username is incorrect */
        break Block;
    } 
    	
   	}          
    }
          catch (SeleniumException sexp)
          {
              Reporter.log(sexp.getMessage());   
          } 
    returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stExternalEmailYahooLogin",flow, hm);    
    if(flow.contains("STOP"))
    {
          assertEquals("PASS",returnVal);
    }
    
    return returnVal;
    }
	// ********************[ stExternalEmailYahooLogin function End ]*************************
    
 // ********************[ stExternalEmailOutlookLogin function Start ]*************************    
    public String stExternalEmailOutlookLogin(int dataId,int ExpVal,String flow )
    {
          int actVal=1000;
          String returnVal=null;
          hm.clear();
    
    hm=STFunctionLibrary.stMakeData(dataId, "Login");
    String emailadrs = hm.get("EmailAddress");            
    String password = hm.get("Password");
    String confirmationtitle = hm.get("LoginConfirmationTitle");
    STCommonLibrary comLib=new STCommonLibrary();
    
    String stNewWindowTitle = null;
    
    
    Vector<String> xPath=new Vector<String>();
    Vector<String> errorMsg=new Vector<String>();
    
    try 
    {
          xPath.add(SIGNIN_OUTLOOK_USERNAME);
          errorMsg.add("Username field on Outlook page not present");
    
          comLib.stVerifyObjects(xPath, errorMsg, flow); 
          
          xPath.clear();
          errorMsg.clear();
    
  Block:
  {	    
      
    /*Typing username and Password*/
    if(!"".equals(emailadrs))
    {
          browser.focus(SIGNIN_OUTLOOK_USERNAME);
          browser.click(SIGNIN_OUTLOOK_USERNAME);
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
                
          browser.type(SIGNIN_OUTLOOK_USERNAME, emailadrs );          
    }
    
    if(!"".equals(password))
    {
          browser.focus(SIGNIN_OUTLOOK_PASSWORD);
                browser.click(SIGNIN_OUTLOOK_PASSWORD);
          
                try {
                      Thread.sleep(3000);
                } catch (InterruptedException e) {
                      e.printStackTrace();
                }
          browser.type(SIGNIN_OUTLOOK_PASSWORD, password ); 
    }
    
    /*Verify the Sign in Button */
    xPath.add(SIGNIN_OUTLOOK_SIGNIN_BUTTON);
    errorMsg.add("Sign in Button is not present on Outlook Sign-in page");    
    comLib.stVerifyObjects(xPath, errorMsg, "STOP");
    
    xPath.clear();
    errorMsg.clear();
    
          /*Click on Sign in Button */
    
          browser.click(SIGNIN_OUTLOOK_SIGNIN_BUTTON);
          
          try {
               Thread.sleep(15000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }     
          
    
    String allwindowtitle [] = browser.getAllWindowTitles();
    int numberOfwindows= allwindowtitle.length;
    System.out.println(" No of open windows= " + numberOfwindows);
    
    /* Fetching Window Title */
    stNewWindowTitle = browser.getTitle();
    System.out.println(stNewWindowTitle);
    
    /* Selecting the native window */
    if (numberOfwindows <= 2)
    {
          browser.selectWindow(stNewWindowTitle);
    }else 
    	/* Breaking the block if there's an error on Google Sign in Window */
    {
          actVal = 3; /* Unknown error occurred on Google Sign in page */   
          break Block;
    }    
    
    if(stNewWindowTitle.contains(confirmationtitle))
    {
    	System.out.println(stNewWindowTitle);
    	System.out.println(confirmationtitle);
        actVal= 0; /* Sign in successful with given username */
        break Block;
    }
    else
    {
    	System.out.println(stNewWindowTitle);
    	System.out.println(confirmationtitle);
    	actVal= 1; /* Username is incorrect */
        break Block;
    } 
    	
   	}          
    }
          catch (SeleniumException sexp)
          {
              Reporter.log(sexp.getMessage());   
          } 
    returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stExternalEmailOutlookLogin",flow, hm);    
    if(flow.contains("STOP"))
    {
          assertEquals("PASS",returnVal);
    }
    
    return returnVal;
    }
	// ********************[ stExternalEmailOutlookLogin function End ]*************************

}
