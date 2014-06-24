package com.sharethis.QAAutomation.OAuth.FastShare;

/**********************************************************************
 * Test Cases Covered: ST_FS_12;ST_FS_13;ST_FS_14;ST_FS_15;ST_FS_16;
 *********************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import java.util.Vector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;
import com.sharethis.QAAutomation.commonlib.STSharing;

public class EnableThroughOauthWidget {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STBCDataID", "STLDataID", "STLExpVal", "STPDataID", "STPExpVal"})
	@Test
	public void test_EnableThroughOauthWidget(int STLBDataID,int STLBExpVal, int STBCDataID,
				 int STLDataID, int STLExpVal, int STPDataID, int STPExpVal) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		Vector<String> xPath = new Vector<String> ();
		Vector<String> errorMsg = new Vector<String> ();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		STCommonLibrary comLib = new STCommonLibrary();	
		STLogin login = new STLogin ();
		STSharing share = new STSharing ();
				
		String status;
		
//		launchBrowser.stOpenBrowserInstance(STPDataID, STPExpVal, "STOP");

		/***********************************************************************
		 * ST_FS_12- This scenario is used to verify that widget 5x should present on site
		 1. Browse to http://sharethis.com or any other publisher domain which contains Widget 5x.
		 2. Click on Try it out and  Launch widget.
		 **********************************************************************/
		//********** Launch Widget******		
		status = launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		comLib.stLogResult(status, "ST_FS_12", "STOP", red);	
		
		//********Click on Sign In*******			
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");
		
		/***********************************************************************
		 * ST_FS_13- OAuth Login: Through Widget 5x
		 1. Browse to http://sharethis.com or any other publisher domain which contains Widget 5x.
		 2. Launch widget.
		 3. Click on any service chicklets say Facebook.
		 4. OAuth with Facebook service.
		 **********************************************************************/		
		//********Sign In with Facebook*******	
		status = login.stFacebookLogin(STLDataID, STLExpVal, "STOP");
		comLib.stLogResult(status, "ST_FS_13", "STOP", red);		
		/***********************************************************************
		 * ST_FS_14-  This scenario is to verify the Fast Share checkmark get displayed on chiklets.
		 1. Browse to http://sharethis.com or any other publisher domain which contains Widget 5x.
		 2. Launch widget.
		 3. Click on any service chicklets say Facebook.
		 4. OAuth with Facebook service.
		 5. Refresh the page and Observe the Chicklets.
		 **********************************************************************/
		browser.click(OAUTH_WIDGET_CLOSE_BUTTON);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//*******Refresh Browser to get Fastshare checkmark**********
		browser.refresh();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		xPath.add(FASTSHARE_CHECKMARK_FACEBOOK);
		errorMsg.add("Fastshare checkbox is not present on facebook");
		
		status = comLib.stVerifyObjects(xPath, errorMsg, "STOP");
		
		comLib.stLogResult(status, "ST_FS_14", "STOP", red);
		xPath.clear();
		errorMsg.clear();
		/***********************************************************************
		 * ST_FS_15-  This is to verify Fast share functionality works properly.
		 1. Click on the Green checkmark.
		 2. Text Box will appear.
		 ***********************************************************************
		 *ST_FS_16-  This is to verify Fast share functionality works properly.
		 1. Wite some comment in text field
		 2. Click on Share Button.
		 **********************************************************************/
		status = share.stSharingViaFastShare(FASTSHARE_CHECKMARK_FACEBOOK, 0, "");
		
		comLib.stLogResult(status, "ST_FS_15", "STOP", red);
		comLib.stLogResult(status, "ST_FS_16", "STOP", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}


}
