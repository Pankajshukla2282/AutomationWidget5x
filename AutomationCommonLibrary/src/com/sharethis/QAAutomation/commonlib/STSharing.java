package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static junit.framework.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import org.testng.Reporter;
import com.thoughtworks.selenium.SeleniumException;

public class STSharing {
	
	// ********************[ stSharingToFacebook function Start ]*************************
	/***************************************************************************
	    * stSharingToFacebook function is used to share the post on Facebook. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing is done successfully. <br>
	    * 1 - Failed to share.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stSharingToFacebook(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String username = hm.get("Username");		
   	String password = hm.get("Password");
   	String message = hm.get("Message");
   	String sharingtitle = hm.get("SharingTitle");
	String action = hm.get("Action");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
   		
   		/* Generating phrase to make username and email Id unique */
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        String stFormat = sdf.format(cal.getTime());
   		
        Block:
        {	if (browser.isElementPresent(FACEBOOK_USERNAME))
        	{    	
	    	xPath.add(FACEBOOK_PASSWORD);
	    	errorMsg.add("Password field not present"); 
	    	
	    	xPath.add(FACEBOOK_LOGIN_BUTTON);
	    	errorMsg.add("Log In Button not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
		
				        	
        	/*Typing username and Password*/
	    	if(!"".equals(username))
	    	{
	    		browser.focus(FACEBOOK_USERNAME);
				browser.click(FACEBOOK_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(FACEBOOK_USERNAME, username );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(FACEBOOK_PASSWORD);
				browser.click(FACEBOOK_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(FACEBOOK_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Click on Sign in Button */
	    	comLib.stClickAndVerify(FACEBOOK_LOGIN_BUTTON, FACEBOOK_SHARING_LINK, 0, "");
	    	
	    	xPath.add(FACEBOOK_MESSAGE_BOX);
	    	errorMsg.add("Message tezt box not present"); 
	    	
	    	xPath.add(FACEBOOK_CANCEL_BUTTON);
	    	errorMsg.add("Log In Button not present"); 
	    	
	    	xPath.add(FACEBOOK_SHARING_TITLE);
	    	errorMsg.add("Log In Button not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
    		
    		browser.type(FACEBOOK_MESSAGE_BOX, message + stFormat);
        }
        else
        {
        	browser.type(FACEBOOK_MESSAGE_BOX, message + stFormat);
        }	
    		
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		String getsharingtitle = browser.getText(FACEBOOK_SHARING_TITLE);
    		
    		if ("Share".equalsIgnoreCase(action))
    		{
    			comLib.stClick(FACEBOOK_SHARING_LINK, "Sharing link is not present", "STOP");
    		}
    		else if ("Cancel".equalsIgnoreCase(action))
    		{
    			comLib.stClick(FACEBOOK_CANCEL_BUTTON, "Cancel button is not present", "STOP");
    		}
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);
						
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_WINDOW);
			errorMsg.add("Widget Window is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
					
			/* Comparing Sharing Title */            
            if(sharingtitle.contains(getsharingtitle))
            {
            	actVal= 0; /*Sharing is done on Facebook */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Failed to share*/
            	break Block;
            } 
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharingToFacebook",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stSharingToFacebook function End ]*************************
	
	// ********************[ stFacebookSharingVerification function Start ]*************************
	/***************************************************************************
	    * stFacebookSharingVerification function is used to verify the shared post on Facebook. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing is post is matching. <br>
	    * 1 - Sharing post is not matching.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stFacebookSharingVerification(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String url = hm.get("URL");		
   	String newwindowtitle = hm.get("NewWindowTitle");
   	String sharingtitle = hm.get("SharingTitle");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
		
		Block:
        {			        	
        	/*Open new window for facebook*/
   			browser.openWindow(url, newwindowtitle);
        	try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	/*Select newly open window*/
        	browser.selectWindow(newwindowtitle);
        	
        	/* Clicking on Home link to refresh the FB wall */
			comLib.stClick(FACEBOOK_HOME_LINK, "Facebook Home link not found on FB wall", "");
			
        	try {
				Thread.sleep(25000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
			
	    	/*Verification of shared post on Facebook */
	    	
	    	xPath.add(FACEBOOK_POST_TITLE);
	    	errorMsg.add("Shared Title is not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
    		
    		String getposttitle = browser.getText(FACEBOOK_POST_TITLE);
    		//String getposttitle = browser.getText(FACEBOOK_POST_SHARED_TITLE);
    		System.out.println("getposttitle " +getposttitle);
    		System.out.println("sharingtitle :" +sharingtitle);
					
			/* Comparing Sharing Title */            
            if(sharingtitle.contains(getposttitle))
            {
            	actVal= 0; /*Sharing Post is matching on Facebook */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Sharing post is not matching*/
            	break Block;
            } 		
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stFacebookSharingVerification",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stFacebookSharingVerification function End ]*************************
	
	// ********************[ stSharingToTwitter function Start ]*************************
	/***************************************************************************
	    * stSharingToTwitter function is used to share the post on Twitter. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing is done successfully. <br>
	    * 1 - Failed to share.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stSharingToTwitter(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String username = hm.get("Username");		
   	String password = hm.get("Password");
   	String message = hm.get("Message");
   	String sharingtitle = hm.get("SharingTitle");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
   		
   		Block:
        {	
   		xPath.add(TWITTER_TWEET_MSG_BOX);
    	errorMsg.add("Tweet Message box not present"); 
	
		comLib.stVerifyObjects(xPath, errorMsg, "STOP");	
		xPath.clear();
		errorMsg.clear();
		
		String getsharingtitle = browser.getText(TWITTER_TWEET_MSG_BOX);
		
		/*If User is Already Logged in Twitter Account*/
		if (browser.isElementPresent(TWITTER_USER_LOGIN_HEADER))
		{
			browser.focus(TWITTER_TWEET_MSG_BOX);
			browser.click(TWITTER_TWEET_MSG_BOX);
			
			browser.type(TWITTER_TWEET_MSG_BOX, message + " " + getsharingtitle);
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		else
		{   			
    		xPath.add(TWITTER_USERNAME);
    		errorMsg.add("'Username' field not present");
    	
	    	xPath.add(TWITTER_PASSWORD);
	    	errorMsg.add("Password field not present"); 
	    	
	    	xPath.add(TWITTER_SIGN_AND_TWEET_BUTTON);
	    	errorMsg.add("Tweet Button not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, "STOP");	
    		xPath.clear();
    		errorMsg.clear();  
				        	
        	/*Typing username and Password*/
	    	if(!"".equals(username))
	    	{
	    		browser.focus(TWITTER_USERNAME);
				browser.click(TWITTER_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(TWITTER_USERNAME, username );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(TWITTER_PASSWORD);
				browser.click(TWITTER_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(TWITTER_PASSWORD, password );    					    				    		
	    	}
            
	    	browser.focus(TWITTER_TWEET_MSG_BOX);
			browser.click(TWITTER_TWEET_MSG_BOX);
			
			browser.type(TWITTER_TWEET_MSG_BOX, message + " " + getsharingtitle);
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			/*Click on Tweet button*/
			comLib.stClick(TWITTER_SIGN_AND_TWEET_BUTTON, "Tweet Button is not present", "STOP");
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);
						
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_WINDOW);
			errorMsg.add("Widget Window is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
			xPath.clear();
    		errorMsg.clear();
					
			/* Comparing Sharing Title */            
            if(getsharingtitle.contains(sharingtitle))
            {
            	actVal= 0; /*Sharing is done on Twitter */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Failed to share*/
            	break Block;
            } 
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharingToTwitter",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stSharingToTwitter function End ]*************************
	
	// ********************[ stTwitterSharingVerification function Start ]*************************
	/***************************************************************************
	    * stTwitterSharingVerification function is used to verify the shared post on Twitter. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing is post is matching. <br>
	    * 1 - Sharing post is not matching.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stTwitterSharingVerification(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String url = hm.get("URL");		
   	String newwindowtitle = hm.get("NewWindowTitle");
   	String sharingtitle = hm.get("SharingTitle");
   	String userName = hm.get("Username");
   	String password = hm.get("Password");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
		
		Block:
        {		
        	/*Open new window for Twitter*/
   			browser.openWindow(url, newwindowtitle);
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
			
			if(browser.isElementPresent(TWITTER_SIGNIN_USERNAME))
			{
				if(!"".equals(userName))
		    	{
		    		browser.focus(TWITTER_SIGNIN_USERNAME);
					browser.click(TWITTER_SIGNIN_USERNAME);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
		    		browser.type(TWITTER_SIGNIN_USERNAME, userName );
		    		
		    	}
		    	
		    	if(!"".equals(password))
		    	{
		    		browser.focus(TWITTER_SIGNIN_PASSWORD);
					browser.click(TWITTER_SIGNIN_PASSWORD);
		    		
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    		browser.type(TWITTER_SIGNIN_PASSWORD, password );    					    				    		
		    	}
		    	
		    	/*Verify and Click on Sign in Button */	
		    	browser.isElementPresent(TWITTER_SIGNIN_SIGNIN_BUTTON);
		    	
		    	
		    	browser.click(TWITTER_SIGNIN_SIGNIN_BUTTON);
		    	try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
        	
        	
	    	/*Verification of shared post on Facebook */
	    	
	    	xPath.add(TWITTER_POST_SHARED_TITLE);
	    	errorMsg.add("Shared Title is not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
    		
    		String getposttitle = browser.getText(TWITTER_POST_SHARED_TITLE);  
    		System.out.println("getposttitle "+getposttitle);
					
			/* Comparing Sharing Title */            
            if(getposttitle.contains(sharingtitle))
            {
            	actVal= 0; /*Sharing Post is matching on Facebook */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Sharing post is not matching*/
            	break Block;
            } 
            
            
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stTwitterSharingVerification",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stTwitterSharingVerification function End ]*************************
	
	// ********************[ stSharingToLinkedIn function Start ]*************************
	/***************************************************************************
	    * stSharingToLinkedIn function is used to share the post on LinkedIn. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing is done successfully. <br>
	    * 1 - Failed to share.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stSharingToLinkedIn(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String username = hm.get("Username");		
   	String password = hm.get("Password");
   	String message = hm.get("Message");
   	String sharingtitle = hm.get("SharingTitle");
	String action = hm.get("Action");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
	String successalertmsg = "";
	
   	try {
   		
   		/* Generating phrase to make username and email Id unique */
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        String stFormat = sdf.format(cal.getTime());
   		
   		Block:
        {	
   		if (browser.isElementPresent(LINKEDIN_USERNAME))
   		{    	
	    	xPath.add(LINKEDIN_PASSWORD);
	    	errorMsg.add("Password field not present"); 
	    	
	    	xPath.add(LINKEDIN_SIGNIN_BUTTON);
	    	errorMsg.add("Sign In Button not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
		
				        	
        	/*Typing username and Password*/
	    	if(!"".equals(username))
	    	{
	    		browser.focus(LINKEDIN_USERNAME);
				browser.click(LINKEDIN_USERNAME);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
	    		browser.type(LINKEDIN_USERNAME, username );
	    		
	    	}
	    	
	    	if(!"".equals(password))
	    	{
	    		browser.focus(LINKEDIN_PASSWORD);
				browser.click(LINKEDIN_PASSWORD);
	    		
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		browser.type(LINKEDIN_PASSWORD, password );    					    				    		
	    	}
	    	
	    	/*Click on Sign in Button */
	    	comLib.stClickAndVerify(LINKEDIN_SIGNIN_BUTTON, LINKEDIN_SHARING_LINK, 0, "");
	    	
	    	xPath.add(LINKEDIN_MESSAGE_BOX);
	    	errorMsg.add("Message text box not present"); 
	    	
	    	xPath.add(LINKEDIN_SHARING_CANCEL_BUTTON);
	    	errorMsg.add("Sharing Cancel Button not present"); 
	    	
	    	xPath.add(LINKEDIN_SHARING_LINK);
	    	errorMsg.add("Sharing Link not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
    		
    		browser.type(LINKEDIN_MESSAGE_BOX, message + stFormat);
   		}
   		
   		else
   			
   		{
   			browser.type(LINKEDIN_MESSAGE_BOX, message + stFormat);
   		}
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		if ("Share".equalsIgnoreCase(action))
    		{
    			comLib.stClickAndVerify(LINKEDIN_SHARING_LINK, LINKEDIN_SUCCESS_ALERT, 0, "STOP");
    			
    			successalertmsg = browser.getText(LINKEDIN_SUCCESS_ALERT);
    			
    			xPath.add(LINKEDIN_CLOSE_WINDOW_BUTTON);
    	    	errorMsg.add("Window Close button not present"); 
        	
        		comLib.stVerifyObjects(xPath, errorMsg, flow);	
        		xPath.clear();
        		errorMsg.clear();
        		browser.click(LINKEDIN_CLOSE_WINDOW_BUTTON);
    		}
    		else if ("Cancel".equalsIgnoreCase(action))
    		{
    			comLib.stClick(LINKEDIN_SHARING_CANCEL_BUTTON, "Cancel button is not present", 
    					"STOP");
    		}
    		
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	    	/*IF no Error occurs*/				
			browser.selectWindow(null);
						
			/*Verification of Email Label on widget*/
			xPath.add(RESKINNED_WIDGET_WINDOW);
			errorMsg.add("Widget Window is not present");
			comLib.stVerifyObjects(xPath, errorMsg, "");
					
			/* Comparing Sharing Title */            
            if(successalertmsg.contains("You have successfully shared this link."))
            {
            	actVal= 0; /*Sharing is done on Facebook */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Failed to share*/
            	break Block;
            } 
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharingToLinkedIn",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stSharingToLinkedIn function End ]*************************
	
	// ********************[ stLinkedInSharingVerification function Start ]*************************
	/***************************************************************************
	    * stFacebookSharingVerification function is used to verify the shared post on LinkedIn. <br>
	    * Expected values:<br>
	    * -----------------------<br>
	    * 0 - Sharing post is matching. <br>
	    * 1 - Sharing post is not matching.<br>
	    * 
	    * @param flow=
		 *            Defines the flow on the test script. If Flow="STOP", script
		 *            execution will be stopped in cases of any failure. If
		 *            Flow="Continue", script execution will not stop even in case
		 *            of any failures.
		 * @return= 1. "PASS", 2. "FAIL"
	    **************************************************************************/
	
	public String stLinkedInSharingVerification(int dataId,int ExpVal,String flow )
	{		
		int actVal=1000;
		String returnVal=null;
		hm.clear();
   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
   	String url = hm.get("URL");		
   	String newwindowtitle = hm.get("NewWindowTitle");
   	String sharingtitle = hm.get("SharingTitle");
   	String userName=hm.get("Username");
   	String password=hm.get("Password");
   	STCommonLibrary comLib=new STCommonLibrary();
   	
   	Vector<String> xPath=new Vector<String>();
	Vector<String> errorMsg=new Vector<String>();
   	
   	try {
		
		Block:
        {			        	
        	/*Open new window for facebook*/
   			browser.openWindow(url, newwindowtitle);
        	try {
				Thread.sleep(20000);
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
			/* For Multipost Share */
			if(browser.isElementPresent(LINKEDIN_SIGNIN_USERNAME))
			{
				/*Typing username and Password*/
		    	if(!"".equals(userName))
		    	{
		    		browser.focus(LINKEDIN_SIGNIN_USERNAME);
					browser.click(LINKEDIN_SIGNIN_USERNAME);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
		    		browser.type(LINKEDIN_SIGNIN_USERNAME, userName );
		    		
		    	}
		    	
		    	if(!"".equals(password))
		    	{
		    		browser.focus(LINKEDIN_SIGNIN_PASSWORD);
					browser.click(LINKEDIN_SIGNIN_PASSWORD);
		    		
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    		browser.type(LINKEDIN_SIGNIN_PASSWORD, password );    					    				    		
		    	}
		    	
		    	try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	
		    	
		    	/*Verify and Click on Sign in Button */	
		    	browser.isElementPresent(LINKEDIN_SIGNIN_SIGNIN_BUTTON);
		    	
		    	try {
					Thread.sleep(8000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    	
		    	browser.click(LINKEDIN_SIGNIN_SIGNIN_BUTTON);
		    	try {
					Thread.sleep(35000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
        	
/*			if(browser.isElementPresent(LINKEDIN_HOME_LINK))
			{
			browser.click(LINKEDIN_HOME_LINK);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	browser.click(LINKEDIN_LINKEDIN_HOME_LINK);
        	try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}*/
			
	    	/*Verification of shared post on LinkedIn */
	    	
	    	xPath.add(LINKEDIN_POST_SHARED_TITLE);
	    	errorMsg.add("Shared Title is not present"); 
    	
    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
    		xPath.clear();
    		errorMsg.clear();
    		
    		String getposttitle = browser.getText(LINKEDIN_POST_SHARED_TITLE);
    		System.out.println("getposttitle for linkedin : " +getposttitle);
					
			/* Comparing Sharing Title */            
            if(getposttitle.contains(sharingtitle))
            {
            	actVal= 0; /*Sharing Post is matching on LinkedIn */
            	break Block;
            }
            else
            {
            	actVal= 1; /*Sharing post is not matching*/
            	break Block;
            } 
			
        }  		

   	}
			    	
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stLinkedInSharingVerification",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 	
	}
	// ********************[ stLinkedInSharingVerification function End ]*************************
	
	// ********************[ stSharingViaFastShare function Start ]*************************
	public String stSharingViaFastShare (String targetXPath, int ExpVal, String flow)
	{
		
		int actVal = 1000;
		String returnVal = null;
		
		STCommonLibrary comLib = new STCommonLibrary ();
		
		Vector<String> xPath = new Vector<String> ();
		Vector<String> errorMsg = new Vector<String> ();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat ("ddMMyyHHmmss");
		String stFormat = sdf.format(cal.getTime());
		
		try {
			comLib.stClick(targetXPath, "Target is not present", "STOP");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Block : {
			
			/*Fetching the Header label*/
			String label = browser.getText(FASTSHARE_WINDOW_HEADER);
			
			/*Verify the Text Box*/
			xPath.add(FASTSHARE_WINDOW_TEXTBOX);
			errorMsg.add("Message Text box is not present");
			
			comLib.stVerifyObjects(xPath, errorMsg, "STOP");
			xPath.clear();
			errorMsg.clear();
			
			browser.focus(FASTSHARE_WINDOW_TEXTBOX);
			browser.click(FASTSHARE_WINDOW_TEXTBOX);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			browser.type(FASTSHARE_WINDOW_TEXTBOX, "Hello User!!!" + stFormat);
			
			/*Verify the Share Button*/
			xPath.add(FASTSHARE_WINDOW_SHARE_BUTTON);
			errorMsg.add("Share button is not present");
			
			comLib.stVerifyObjects(xPath, errorMsg, "STOP");
			xPath.clear();
			errorMsg.clear();
			/*Click on Share Button*/
			comLib.stClickAndVerify(FASTSHARE_WINDOW_SHARE_BUTTON, FASTSHARE_DONE_SCREEN, 0, "STOP");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			String successmsg = browser.getText(FASTSHARE_DONE_SCREEN);
			System.out.println("This messgae shoul be appeared" + successmsg);
			
			if (successmsg.contains("Your message was successfully shared!"))
			{
				actVal = 0;
				break Block;
			}
			else
			{
				actVal = 1;
				break Block;
			}
			
		}
		}
		catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharingViaFastShare",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 			
	}
	// ********************[ stSharingViaFastShare function End ]*************************
	
	public String stTwitterSharedURLVerification(int dataId,int ExpVal,String flow)
	{
		int actVal = 1000;
		String returnVal = null;
		hm.clear();
	   	hm=STFunctionLibrary.stMakeData(dataId, "Post");
	   	String url = hm.get("URL");		
	   	String newwindowtitle = hm.get("NewWindowTitle");
	   	String sharingtitle = hm.get("SharingTitle");
	   	STCommonLibrary comLib=new STCommonLibrary();
	   	
	   	Vector<String> xPath=new Vector<String>();
		Vector<String> errorMsg=new Vector<String>();
	   	
	   	try {
			
			Block:
	        {			        	
	        	/*Select newly open window
	        	browser.selectWindow(newwindowtitle);*/
	        	
	        	try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	
		    	/*Verification of URL post on Twitter */
		    	
		    	xPath.add(TWITTER_SHARED_URL);
		    	errorMsg.add("Shared URL is not present on twitter.com"); 
	    	
	    		comLib.stVerifyObjects(xPath, errorMsg, flow);	
	    		xPath.clear();
	    		errorMsg.clear();
	    		
	    		comLib.stClickAndVerifyLink(TWITTER_SHARED_URL, dataId, 0, 1, ""); 		
						
				/* Comparing Sharing Title */            
	            /*if(getposttitle.contains(sharingtitle))
	            {
	            	actVal= 0; Sharing Post is matching on Facebook 
	            	break Block;
	            }
	            else
	            {
	            	actVal= 1; Sharing post is not matching
	            	break Block;
	            } */
				
	        }  	
	   	}catch (SeleniumException sexp)
		{
			Reporter.log(sexp.getMessage());	
		} 
		//TWITTER_SHARED_URL
		
		returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSharingViaFastShare",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}
		return returnVal; 
	}

}
