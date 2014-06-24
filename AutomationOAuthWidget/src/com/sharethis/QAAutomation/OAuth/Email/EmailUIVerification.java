package com.sharethis.QAAutomation.OAuth.Email;

/*************************************************************
 * Test Cases Covered: ST_ESM_01,ST_ESM_02,ST_ESM_04,ST_ESM_05
 *************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import java.util.Vector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

public class EmailUIVerification {
	StringBuffer red = new StringBuffer("1");
	
	@Parameters( { "STLBDataID", "STLBExpVal"})
	@Test
	public  void test_EmailUIVerification(int STLBDataID, int STLBExpVal) {
		
		STCommonLibrary comLib = new STCommonLibrary();
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		String status;
		
		Vector<String> xPath = new Vector<String> ();
		Vector<String> errorMsg = new Vector<String> ();
		
		//*********Launch Widget*********
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
				
		/***********************************************************************
		 * ST_ESM_01- Verify the New UI for Email sharing, contains External email services, Oauth options
		 1. Browse to sharethis.com or any publisher site having widget 4x
		 2. Launch Email Widget 5x		
		 **********************************************************************
		 * ST_ESM_04- Verification of "?" link 
		 1. Browse to sharethis.com or any publisher site having widget 4x
		 2. Launch Email Widget 5x	
		 3. Verify the "?" link
		 **********************************************************************/
		comLib.stClick(OAUTH_WIDGET_EMAIL_BIGICON, "Email Chiklet is not Present", "STOP");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		//***********Verification of UI**********		
		xPath.add(EXTERNAL_EMAIL_GMAIL);
		errorMsg.add("External Gmail email service is not present");
		
		xPath.add(EXTERNAL_EMAIL_YAHOO);
		errorMsg.add("External Yahoo email service is not present");
		
		xPath.add(EXTERNAL_EMAIL_OUTLOOK);
		errorMsg.add("External Outlook email service is not present");
		
		xPath.add(EXTERNAL_EMAIL_NATIVE_EMAIL);
		errorMsg.add("External Native email service is not present");
		
		xPath.add(EMAIL_FACEBOOK_OAUTH);
		errorMsg.add("Facebook Oauth is not present");
		
		xPath.add(EMAIL_LINKEDIN_OAUTH);
		errorMsg.add("LinkedIn Oauth is not present");
		
		xPath.add(EMAIL_TWITTER_OAUTH);
		errorMsg.add("Twitter Oauth is not present");
		
		xPath.add(EMAIL_YAHOO_OAUTH);
		errorMsg.add("Yahoo Oauth is not present");
		
		xPath.add(EMAIL_GOOGLE_OAUTH);
		errorMsg.add("Google Oauth is not present");
		
		xPath.add(EMAIL_PAGE_INFO_LINK);
		errorMsg.add("Page Info(?) link is not present");
		
		status = comLib.stVerifyObjects(xPath, errorMsg, "");
		
		comLib.stLogResult(status, "ST_ESM_01", "", red);
		comLib.stLogResult(status, "ST_ESM_04", "", red);
		
		xPath.clear();
		errorMsg.clear();
		
		/***********************************************************************
		 * ST_ESM_05- Click on More Services link
		 1. Browse to sharethis.com or any publisher site having widget 5x
		 2. Launch Email Widget 5x
		 3. Click on More services link	
		 **********************************************************************/
		xPath.add(OAUTH_WIDGET_MORE_LINK);
		errorMsg.add("More Link is not present");		
		comLib.stVerifyObjects(xPath, errorMsg, "");
		
		status = comLib.stClickAndVerify(OAUTH_WIDGET_MORE_LINK, 
				OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK, 0, "STOP");
		
		comLib.stLogResult(status, "ST_ESM_05", "STOP", red);
		
		comLib.stClick(OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK, 
				"Back to default view link is not present", "STOP");
		xPath.clear();
		errorMsg.clear();
		/***********************************************************************
		 * ST_ESM_02- Verification of "Go Back" Link
		 1. Browse to sharethis.com or any publisher site having widget 5x
		 2. Launch Email Widget 5x
		 3. Click on Go Back link	
		 **********************************************************************/
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		xPath.add(EMAIL_GO_BACK_LINK);
		errorMsg.add("Go Back link is not present");		
		comLib.stVerifyObjects(xPath, errorMsg, "STOP");
		
		status = comLib.stClickAndVerify(EMAIL_GO_BACK_LINK, OAUTH_WIDGET_MESSAGE_BOX, 0, "STOP");
		
		comLib.stLogResult(status, "ST_ESM_02", "", red);	
		
		comLib.stTearDown(red);

	}
		@AfterTest
		public void CloseBrowser (){
			STCommonLibrary comLib = new STCommonLibrary ();
			red.replace(0, 1, "1");
			comLib.stTearDown(red);
		}
}
