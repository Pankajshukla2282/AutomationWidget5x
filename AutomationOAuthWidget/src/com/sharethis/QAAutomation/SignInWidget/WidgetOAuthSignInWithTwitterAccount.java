package com.sharethis.QAAutomation.SignInWidget;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;
import com.sharethis.QAAutomation.commonlib.STOAuthFunctionLibrary;
import com.sharethis.QAAutomation.commonlib.STOAuthSignIn;

public class WidgetOAuthSignInWithTwitterAccount {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID"})
	@Test
	public void test_OAuthSignInWithTwitterAccount(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_SIW_13- This scenario is to Signing-in into widget with valid Twitter credetials.
		 1. Browse to any website. 
		 2. Click on sharethis widget to launch widget.
		 3. Click on Twitter icon and sign in
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STOAuthSignIn oauthLogin= new STOAuthSignIn();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on Twitter icon link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_TWITTER_BIGICON, STBCDataID, 0, 1, "");
				
		//********* Twitter sign in ************
		status = oauthLogin.stOAuthSignInTwitter(STLDataID, STLExpVal, "");
		
		comLib.stLogResult(status, "ST_SIW_13", "STOP", red);		
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
}
