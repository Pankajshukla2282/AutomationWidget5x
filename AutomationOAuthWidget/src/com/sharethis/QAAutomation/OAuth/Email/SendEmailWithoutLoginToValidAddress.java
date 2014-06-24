package com.sharethis.QAAutomation.OAuth.Email;

/*************************************************************
 * Test Cases Covered: ST_ETF_05;ST_ETF_06
 *************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;

import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STEmail;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

public class SendEmailWithoutLoginToValidAddress {
	
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STEDataID", "STEExpVal"})
	@Test
	public void test_SendEmailWithoutLoginToValidAddress(int STLBDataID, int STLBExpVal, 
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
		Vector<String> xPath=new Vector<String>();
		Vector<String> errorMsg=new Vector<String>();
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on Email Service link*****		
		comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
		
		/***********************************************************************
		 * ST_ETF_05- This scenario is to Sharing as Email with an valid email address in 
		 * "To" & "From" field
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on Email chicklet 
		 3. Enter email address in "To" & "From" field and click on share 
		 **********************************************************************/		
		//**********Compose email with Valid Address*************
		email.stComposeEmail(STEDataID, STEExpVal, "STOP");
		
		//**********Click on Email Send Button*************
		status = email.stSendOrCancelEmail(STEDataID, 0, "STOP");
		
		comLib.stLogResult(status, "ST_ETF_05", "", red);		
		/***********************************************************************
		 * ST_ETF_14- This scenario is to Checking Recent email address on email page of widget
		   Make sure that share is sent as email to multiple recipients after signing-in into 
		   widget
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on Email chicklet 
		 **********************************************************************/	
		comLib.stClickAndVerify(OAUTH_WIDGET_CLOSE_BUTTON, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Email Service link*****		
		comLib.stClickAndVerify(OAUTH_WIDGET_EMAIL_BIGICON, OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION, 0, "STOP");
		
		xPath.add(OAUTH_WIDGET_RECENT_EMAIL);
 		errorMsg.add("Recent Contact header not present");
 		
 		status =comLib.stVerifyObjects(xPath, errorMsg, "STOP");
 		comLib.stLogResult(status, "ST_ETF_14", "STOP", red);
 		
 		xPath.clear();
 		errorMsg.clear();
 		/***********************************************************************
		 * ST_ETF_15- This scenario is to Checking if 'Recent Contacts' comes up for 
		 * an unsigned user
		   Make sure that share is sent as email to multiple recipients without signing-in 
		   into widget
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on Email chicklet
		 **********************************************************************/	
 		String recentcontactvalue = browser.getText(OAUTH_WIDGET_RECENT_EMAIL1);
 		System.out.println(recentcontactvalue);
 		
 		if (recentcontactvalue.contains("testnguser..."))
 		{
			status = "PASS";
		}else
		{
			status = "FAIL";
		}
 		comLib.stLogResult(status, "ST_ETF_15", "STOP", red);
		
		/*comLib.stTearDown(red);*/
 		//email.stVerifyRecentContacts(STEDataID, 0, "");

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
