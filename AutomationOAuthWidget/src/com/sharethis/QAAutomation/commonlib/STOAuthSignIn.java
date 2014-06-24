package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static junit.framework.Assert.assertEquals;


import java.util.Vector;
import org.testng.Reporter;

import sun.security.x509.AVA;

import com.thoughtworks.selenium.SeleniumException;

public class STOAuthSignIn {
	
	public String stOAuthSignInFacebook (int dataId, int ExpVal, String flow)
	{
		String returnVal=null;
		int actVal=1000;
		hm.clear();
		
	 	hm=STFunctionLibrary.stMakeData(dataId, "Login");
	   	String emailadrs = hm.get("EmailAddress");		
	   	String password = hm.get("Password");
	   	String welcomelabel = hm.get("WelcomeLabel");
	   	String stFullName =null;
	   	
	   	STCommonLibrary comLib=new STCommonLibrary();
	   	//STOAuthFunctionLibrary oauthcomLib = new STOAuthFunctionLibrary();
	   	
	   	Vector<String> xPath=new Vector<String>();
		Vector<String> errorMsg=new Vector<String>();
		
		Block :
		{
			try
			{
		
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
		
		/*Verification of Sign out link on widget*/
		browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
		
		/*Verification of Email Label on widget*/
		xPath.add(OAUTH_WIDGET_EMAIL_USERNAME);
		errorMsg.add("Username on OAuth Widget is not present");
		
		xPath.add(OAUTH_WIDGET_FACEBOOK_GREEN_CHECKED_BUTTON);
		errorMsg.add("FB footer button on OAuth widget is not highlighted after sign in");
		
		comLib.stVerifyObjects(xPath, errorMsg, "");
		xPath.clear();
		errorMsg.clear();
		
        /* Fetching Full Name */
	    stFullName = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
	    System.out.println("***" + stFullName + "***");
	    
	    if(stFullName.contains(welcomelabel))
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
			catch (SeleniumException sexp)
			{
				Reporter.log(sexp.getMessage());	
			} 
		}
		
		System.out.println("actval for oauth sign in" +actVal);
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stOAuthSignInFacebook",flow, hm);
		System.out.println("returnVal for stOAuthSignin" +returnVal);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	
	
	public String stOAuthSignInTwitter (int dataId, int ExpVal, String flow)
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
	    	
	    	//browser.click(SIGNIN_TWITTER_SIGNIN_BUTTON);
	    	comLib.stClick(SIGNIN_TWITTER_SIGNIN_BUTTON, "Sign in button absent on twitter.com", "STOP");
	    	try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	
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
			if (browser.isElementPresent(OAUTH_WIDGET_EMAIL_LABEL))
			{
			/*Verification of Sign out link on widget*/
			browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			
			/* Verifying Twitter Green Check mark */
			xPath.add(OAUTH_WIDGET_TWITTER_GREEN_CHECKED_BUTTON);
			errorMsg.add("Twitter footer button on OAuth widget is not highlighted after sign in");
			
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
            /* Fetching Full Name */
    	    String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
    	    
    	    if(stFullName.contains(welcomelabel))
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

	returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stOAuthSignInTwitter",flow, hm);
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
	}
	return returnVal; 
	}
	
	
	public String stOAuthLinkedinAccount (int dataId, int ExpVal, String flow)
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
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
	    	String allwindowtitle [] = browser.getAllWindowTitles();
	    	int Numberofwindows= allwindowtitle.length;
	    	
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

			/*Verification of Sign out link on widget*/
			browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK);
			
			/*Verification of Email Label on widget*/
			xPath.add(OAUTH_WIDGET_EMAIL_LABEL);
			errorMsg.add("Email Label on Widget is not present");
			
			/* Verifying Twitter Green Check mark */
			xPath.add(OAUTH_WIDGET_LINKEDIN_GREEN_CHECKED_BUTTON);
			errorMsg.add("LinkedIn footer button on OAuth widget is not highlighted after sign in");
			
			comLib.stVerifyObjects(xPath, errorMsg, "");
			
            /* Fetching Full Name */
    	    String stFullName = browser.getText(OAUTH_WIDGET_EMAIL_USERNAME);
    	    
    	    if(stFullName.contains(welcomelabel))
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
			/*Checking Welcome Label for OAuth Widget*/
			
   	}
   	catch (SeleniumException sexp)
	{
		Reporter.log(sexp.getMessage());	
	} 

	returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stOAuthLinkedinAccount",flow, hm);
	
	if(flow.contains("STOP")){
		assertEquals("PASS",returnVal);
	}
	return returnVal; 
	}
	
}



