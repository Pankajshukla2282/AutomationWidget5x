package com.sharethis.QAAutomation.OAuth.Email;

/*************************************************************
 * Test Cases Covered: ST_ETF_03;ST_ETF_04
 *************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

public class SendEmailWithoutLoginWithInvalidCredentialsinFromField {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STEDataID", "STEDataID1"})
	@Test
	public void test_SendEmailWithoutLoginWithInvalidCredentialsinFromField(int STLBDataID, int STLBExpVal, 
			int STEDataID, int STEDataID1) {
		
		STCommonLibrary comLib = new STCommonLibrary();	
		STEmail email = new STEmail ();
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
		
		//********Click on Email Service link*****		
		comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
		
		/***********************************************************************
		 * ST_ETF_03- This scenario is to Sharing as Email without an email address in "From" field
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on Email chicklet 
		 3. Enter email address in "To" field and keep the "From" field blank and click on share  
		 **********************************************************************/			
		//**********Compose email without To field*************
		email.stComposeEmail(STEDataID, 0, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID, -1, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_03", "", red);
		
		/***********************************************************************
		 * ST_ETF_04- This scenario is to Sharing as Email with an invalid email address in "From" 
		 * field
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on Email chicklet 
		 3. Enter email address in "To" field and invalid id in "From" field and click on share  
		 **********************************************************************/	
		//**********Compose email without To field*************
		email.stComposeEmail(STEDataID1, 0, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID1, -1, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_04", "", red);

}
	
	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
	

}
