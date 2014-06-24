package com.sharethis.QAAutomation.OAuth.Sharing;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STDoneScreen;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STOAuthSignIn;
import com.sharethis.QAAutomation.commonlib.STSharing;

public class SharingToLinkedIn {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID", "STPDataID", "STPExpVal"})
	@Test
	public void test_SharingToLinkedIn(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID, int STPDataID, int STPExpVal) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		STSharing sharing = new STSharing ();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_STM_03- This scenario is to verify sharing to LinkedIn via OAuth Widget.
		1. Browse to any website. Launch widget
		2. Click on LinkedIn chicklet
		3. Enter valid Credentials and sign in to widget. Click on Share
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STOAuthSignIn oauthLogin= new STOAuthSignIn();
		STDoneScreen stDoneScreen=new STDoneScreen();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on LinkedIn icon *****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_LINKEDIN_BIGICON, STBCDataID, 0, 1, "STOP");
				
		//********* LinkedIn sign in ************
		status = oauthLogin.stOAuthLinkedinAccount(STLDataID, STLExpVal, "STOP");
		System.out.println("status for stOAuthLinkedinAccount : " +status);
		
		if(status.equalsIgnoreCase("FAIL"))
		{
			comLib.stLogResult(status, "ST_STM_03", "STOP", red);
		}else
		{
		/* Click on 'Share' link and wait for Done screen */
		comLib.stClickAndVerify(OAUTH_WIDGET_SHARE_BUTTON, OAUTH_WIDGET_DONE_SCREEN, 0, "");
		
		/* Verifying 'Shared To' Text on Done screen */
		status=stDoneScreen.stVerifySharedToText("LinkedIn",3, "");
		System.out.println(" ST_DS_05 : " +status);
		comLib.stLogResult(status, "ST_DS_05", "", red);
		
		//********* Perform Verification sharing on LinkedIn************
		status = sharing.stLinkedInSharingVerification(STPDataID, STPExpVal, "STOP");
		
		comLib.stLogResult(status, "ST_STM_03", "STOP", red);
		}
		
		comLib.stTearDown(red);
		

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
}
