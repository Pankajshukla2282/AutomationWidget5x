package com.sharethis.QAAutomation.OAuth.Email;

/**********************************************************************
 * Test Cases Covered: ST_ETF_13, ST_ETF_16
 *********************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;
import com.sharethis.QAAutomation.commonlib.STOAuthWidgetSignOut;

public class SendEmailAfterLoginToValidAddress {
	
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STEDataID", "STLDataID",
					"STLExpVal", "STBCDataID"})
	@Test
	public void test_SendEmailAfterLoginToValidAddress(int STLBDataID, int STLBExpVal, 
			int STEDataID, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STCommonLibrary comLib = new STCommonLibrary();	
		STEmail email = new STEmail ();
		STLogin login = new STLogin ();
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		STOAuthWidgetSignOut signOut=new STOAuthWidgetSignOut();
		
	   	Vector<String> xPath=new Vector<String>();
		Vector<String> errorMsg=new Vector<String>();
		
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");	
		
		//********Login on Widget***********
		login.stSharethisLogin(STLDataID, STLExpVal, "STOP");
		
		//********Click on Email Service link*****		
		comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
		
		/***********************************************************************
		 * ST_ETF_11- This scenario is to Sharing as Email without an email address in "To" field 
		 * after sign-in to widget
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage. Sign-in into widget using credentials of any of the service
		 2. Sign in on Widget.
		 3. Click on Email chicklet
		 4. Without entering email address in "TO" field, click on share  
		 
		 * ST_ETF_16- This scenario is to test if 'Recent Contacts' are populated after signing out of widget
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage. Sign-in into widget using credentials of any of the service
		 2. Sign in on Widget.
		 3. Click on Email chicklet
		 4. Click on Share
		 5. Sign out of Widget and click on Email chicklet again
		 **********************************************************************/		
		//**********Compose email without To field*************
		email.stComposeEmail(STEDataID, 0, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID, 0, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_13", "", red);
		
		/* Clicking on Share Again link */
//		status=comLib.stClickAndVerify(OAUTH_WIDGET_SHARE_AGAIN_LINK, OAUTH_WIDGET_EMAIL_BIGICON, 0, "STOP");
//		
//		status=signOut.stSignOutOfOAuthWidget(1, "");
//		
//		if(status.equalsIgnoreCase("PASS"))
//		{
//			comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
//			
//			/* Verifying LinkedIn icon */
//			xPath.add(OAUTH_WIDGET_RECENT_EMAIL);
//			errorMsg.add("Recent Email list not present on Email page of Widget, as per the expected behavior.");
//			
//			/* Verifying objects */
//			status=comLib.stVerifyObjects(xPath, errorMsg, "");
//			
//			if(status.equalsIgnoreCase("FAIL"))
//			{	/* Logging result as PASS when recent contact list is not present */
//				comLib.stLogResult("PASS", "ST_ETF_16", "", red);
//			}else
//			{	/* Else logging for failure */
//				comLib.stLogResult("FAIL", "ST_ETF_16", "", red);
//			}
//			
//			/* Clearing the two vectors */
//			xPath.clear();
//			errorMsg.clear();
//					
//		}
//		else
//		{
//			comLib.stLogResult(status, "ST_ETF_16", "", red);
//		}
		
		//comLib.stTearDown(red);	

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}


}
