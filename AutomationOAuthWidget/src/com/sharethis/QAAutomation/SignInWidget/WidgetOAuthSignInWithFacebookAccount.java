package com.sharethis.QAAutomation.SignInWidget;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STOAuthFunctionLibrary;
import com.sharethis.QAAutomation.commonlib.STOAuthSignIn;

public class WidgetOAuthSignInWithFacebookAccount {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID"})
	@Test
	public void test_OAuthSignInWithFacebookAccount(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_SIW_08- This scenario is to Signing-in into widget with valid Facebook credetials.
		 1. Browse to any website. 
		 2. Click on sharethis widget to launch widget.
		 3. Click on Sign in link, login window appears.
		 4. Click on Facebook icon
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STOAuthSignIn oauthLogin= new STOAuthSignIn();
		STOAuthFunctionLibrary oauthcomLib = new STOAuthFunctionLibrary();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on FB icon link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_FB_BIGICON, STBCDataID, 0, 1, "");
		//oauthcomLib.stClickAndVerifyOAuthLink(OAUTH_WIDGET_FB_BIGICON, STBCDataID, 4, 1, "");
				
		//********* FB sign in ************
		status = oauthLogin.stOAuthSignInFacebook(STLDataID, STLExpVal, "");
		
		comLib.stLogResult(status, "ST_SIW_11", "STOP", red);		
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
}
