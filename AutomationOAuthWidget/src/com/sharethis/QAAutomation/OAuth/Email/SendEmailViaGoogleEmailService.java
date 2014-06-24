package com.sharethis.QAAutomation.OAuth.Email;

/*************************************************************
 * Test Cases Covered: ST_ESM_06
 *************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import java.util.Vector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;

public class SendEmailViaGoogleEmailService {
	StringBuffer red = new StringBuffer("1");
	
	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID", 
		"STEEDataID", "STEEExpVal"})
	@Test
public  void test_SendEmailViaGoogleEmailService(int STLBDataID, int STLBExpVal, int STLDataID, 
		int STLExpVal, int STBCDataID, int STEEDataID, int STEEExpVal) {
		
		STCommonLibrary comLib = new STCommonLibrary();
		STEmail email = new STEmail ();
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		STLogin login = new STLogin ();
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
		
		comLib.stClick(OAUTH_WIDGET_EMAIL_BIGICON, "Email Chiklet is not Present", "STOP");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		/***********************************************************************
		 * ST_ESM_06- Email sharing using Google service
		1. Browse to sharethis.com or any publisher site having widget 5x
		2. Launch Widget 5x
		3. Click on Email chicklet
		4. Select Gmail service
		 **********************************************************************/
		//***********Verification of Google Email service**********		
		xPath.add(EXTERNAL_EMAIL_GMAIL);
		errorMsg.add("Google Service is not present");			
		comLib.stVerifyObjects(xPath, errorMsg, "");
		
		xPath.clear();
		errorMsg.clear();
		
		//*******Click on Google Email service*********
		comLib.stClickAndVerifyLink(EXTERNAL_EMAIL_GMAIL, STBCDataID, 0, 1, "STOP");
		
		//*******Login With Google Account**********
		login.stExternalEmailGoogleLogin(STLDataID, STLExpVal, "STOP");	
				
		//*******Compose email *******
		status = email.stComposeEmailForGmailEmailService(STEEDataID, STEEExpVal, "STOP");
				
		comLib.stLogResult(status, "ST_ESM_06", "", red);		
				
		comLib.stTearDown(red);

	}
		@AfterTest
		public void CloseBrowser (){
			STCommonLibrary comLib = new STCommonLibrary ();
			red.replace(0, 1, "1");
			comLib.stTearDown(red);
		}
}
