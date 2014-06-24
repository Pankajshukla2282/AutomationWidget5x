package com.sharethis.QAAutomation.OAuth.Email;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;

public class SendEmailAfterLoginWIthInvalidCredentialsinToField {

	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STEDataID", "STEDataID1", "STLDataID",
					"STLExpVal", "STBCDataID"})
	@Test
	public void test_SendEmailWithAfterLoginWIthInvalidCredentialsinToField(int STLBDataID, int STLBExpVal, 
			int STEDataID, int STEDataID1, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STCommonLibrary comLib = new STCommonLibrary();	
		STEmail email = new STEmail ();
		STLogin login = new STLogin ();
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
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
		 **********************************************************************/		
		//**********Compose email without To field*************
		email.stComposeEmail(STEDataID, 0, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID, -2, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_11", "", red);
		
		/***********************************************************************
		 * ST_ETF_12- This scenario is to Sharing as Email with an invalid email address in "To" 
		 * field after sign-in to widget
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Sign in on Widget.
		 3. Click on Email chicklet
		 4. Without entering email address in "TO" field, click on share  
		 **********************************************************************/	
		//**********Compose email with invalid To field*************
		email.stComposeEmail(STEDataID1, 0, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID1, -2, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_12", "", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}


}
