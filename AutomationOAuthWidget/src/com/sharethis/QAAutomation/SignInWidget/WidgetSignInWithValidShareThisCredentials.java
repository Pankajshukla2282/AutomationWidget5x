package com.sharethis.QAAutomation.SignInWidget;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;

public class WidgetSignInWithValidShareThisCredentials {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID"})
	@Test
	public void test_WidgetSignInWithSharethisAccount(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_CASI_11- This scenario is to SSigning-in into widget with valid newly created 
		 * ShareThis credentials
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on sign in link present in the widget footer
		 3. Provide valid ShareThis login details and click on sign-in.
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STLogin login = new STLogin ();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");		
				
		//********* Click on Sign In Button************
		status = login.stSharethisLogin(STLDataID, STLExpVal, "");
		
		comLib.stLogResult(status, "SIW_01", "STOP", red);		
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
