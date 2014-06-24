package com.sharethis.QAAutomation.SignInWidget;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.OAUTH_WIDGET;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.OAUTH_WIDGET_SIGNIN_LINK;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.OAUTH_WIDGET_WINDOW;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.RESKINNED_WIDGET;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.RESKINNED_WIDGET_SIGNIN_LINK;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.RESKINNED_WIDGET_WINDOW;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;

public class WidgetSignInWithYahooAccount {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID"})
	@Test
	public void test_WidgetSignInWithYahooAccount(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID) {
		
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_SIW_09- This scenario is to Signing-in into widget with valid Yahoo credentials
		 1. Browse to any website. 
		 2. Click on sharethis widget to launch widget.
		 3. Click on Sign in link, login window appears.
		 4. Click on Yahoo icon
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STLogin login = new STLogin ();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");	
				
		//********* Click on Sign In Button************
		status = login.stYahooLogin(STLDataID, STLExpVal, "");
		
		comLib.stLogResult(status, "ST_SIW_09", "STOP", red);		
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
