package com.sharethis.QAAutomation.OAuth.Email;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

public class SendEmailWithoutLoginWithCaptcha {
	
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STEDataID", "STEExpVal"})
	@Test
	public void test_SendEmailWithoutLoginToMultipleAddresses(int STLBDataID, int STLBExpVal, 
			int STEDataID, int STEExpVal) {
		
		STCommonLibrary comLib = new STCommonLibrary();	
		STEmail email = new STEmail ();
		String status;
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on Email Service link*****		
		comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
		
		/***********************************************************************
		 * ST_ETF_07- This scenario is to check if captcha comes up on sharing to more than 5 email addresses
		1. Browse to any website. Launch widget
		2. Click on Email chicklet
		3. Either type in more than 6 email address in "TO" field and then click on email 
		 **********************************************************************/		
		//**********Compose email with Valid Address*************
		email.stComposeEmail(STEDataID, STEExpVal, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID, 3, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_07", "", red);		

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
