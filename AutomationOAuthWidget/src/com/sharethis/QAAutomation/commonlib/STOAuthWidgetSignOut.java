package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;


import java.util.Vector;

public class STOAuthWidgetSignOut {
	String returnVal=null;
	int actVal=1000;
	String status="";
	
	public String stSignOutOfOAuthWidget (int ExpVal, String flow)
	{
		hm.clear();
	   	STCommonLibrary comLib=new STCommonLibrary();
	   	Vector<String> xPath=new Vector<String>();
		Vector<String> errorMsg=new Vector<String>();
		
	Block:
	{
		/* Checking Sign Out link on Widget */
		if(!browser.isElementPresent(OAUTH_WIDGET_SIGNOUT_LINK))
		{
			actVal=-1;
			break Block;
		}
		
		status=comLib.stClickAndVerify(OAUTH_WIDGET_SIGNOUT_LINK, OAUTH_WIDGET_SIGNIN_LINK, 0, "");
		System.out.println("Status stClickAndVerify : " +status);
		
		if (status.equalsIgnoreCase("PASS"))
		{
		/*Verification of Email Label on widget*/
		xPath.add(OAUTH_WIDGET_EMAIL_BIGICON);
		errorMsg.add("Email Icon on Widget is not present after sign out");
		
		/* Verifying FB icon*/
		xPath.add(OAUTH_WIDGET_FB_BIGICON);
		errorMsg.add("FB icon not present after sign out");
		
		/* Verifying Twitter icon */
		xPath.add(OAUTH_WIDGET_TWITTER_BIGICON);
		errorMsg.add("Twitter icon not present after sign out");
		
		/* Verifying LinkedIn icon */
		xPath.add(OAUTH_WIDGET_LINKEDIN_BIGICON);
		errorMsg.add("Twitter icon not present after sign out");
		
		/* Verifying objects */
		status=comLib.stVerifyObjects(xPath, errorMsg, "");
		
		/* Clearing the two vectors for future */
		xPath.clear();
		errorMsg.clear();
		
		if(status.equalsIgnoreCase("PASS"))
		{
			/* Sign out was successful */
			actVal=1;
			break Block;
		}
		else
		{	/* Unknown error */
			actVal=-2;
			break Block;
		}
		
		}
		else
		{	/* Sign out link not present after signing out */
			actVal=0;
			break Block;
		}
		
	}
		

	returnVal=STFunctionLibrary.stRetValDes(ExpVal, actVal, "stSignOutOfOAuthWidget",flow, hm);
		if(flow.contains("STOP")){
			assertEquals("PASS",returnVal);
		}

		return returnVal; 
	}
	
	
}
