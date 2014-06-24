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

public class MultipostShare {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID", "STPDataID", "STPExpVal", "STPDataID2", "STPDataID3"})
	@Test
	public void test_MultipostShare(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID, int STPDataID, int STPExpVal, int STPDataID2, int STPDataID3) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		STSharing sharing = new STSharing ();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_STM_04- This scenario is to verify multipost share to FB, Twitter & LinkedIn via OAuth Widget.
		1. Browse to any website. Launch widget
		2. Sign in to widget using an account which is linked to FB, Twitter and LinkedIn
		3. Click on Share and verify post that gets shared across all the services
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STOAuthSignIn oauthLogin= new STOAuthSignIn();
		STDoneScreen stDoneScreen=new STDoneScreen();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
		
		//********Click on FB icon *****		
		status=comLib.stClickAndVerifyLink(OAUTH_WIDGET_FB_BIGICON, STBCDataID, 0, 1, "STOP");
		System.out.println("status of stClickAndVerifyLink" +status);
				
		//********* FB sign in ************
//		status = oauthLogin.stOAuthSignInTwitter(STLDataID, STLExpVal, "STOP");
		status=oauthLogin.stOAuthSignInFacebook(STLDataID, STLExpVal, "STOP");
		System.out.println("status of stOAuthSignInFacebook" +status);
		
		/* Click on 'Share' link and wait for Done screen */
		status=comLib.stClickAndVerify(OAUTH_WIDGET_SHARE_BUTTON, OAUTH_WIDGET_DONE_SCREEN, 0, "");
		
		/* Verifying 'Shared To' Text on Done screen */
		status=stDoneScreen.stVerifySharedToText("All",4, "");
		comLib.stLogResult(status, "ST_DS_07", "", red);
		
		//********* Perform Verification sharing on Facebook************
		status = sharing.stFacebookSharingVerification(STPDataID, STPExpVal, "STOP");
		
		if(status.equalsIgnoreCase("FAIL"))
		{
			comLib.stLogResult(status, "ST_STM_04", "", red);
		}
		
		/* Verification of Twitter Share */ 
		status = sharing.stTwitterSharingVerification(STPDataID2, STPExpVal, "STOP");
		
		if(status.equalsIgnoreCase("FAIL"))
		{
			comLib.stLogResult(status, "ST_STM_04", "", red);
		}
		
		/* Sharing post on Linkedin not getting reflected immediately hence commenting this part */
		/* Verification of LinkedIn Share */
//		status = sharing.stLinkedInSharingVerification(STPDataID3, STPExpVal, "");
//		if(status.equalsIgnoreCase("FAIL"))
//		{
//			comLib.stLogResult(status, "ST_STM_04", "", red);
//		}
//		
//		comLib.stLogResult(status, "ST_STM_04", "STOP", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
