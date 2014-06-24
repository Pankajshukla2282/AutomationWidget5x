package com.sharethis.QAAutomation.OAuth.Sharing;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;

import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STOAuthSignIn;
import com.sharethis.QAAutomation.commonlib.STSharing;

public class SharingToWordpress {

		StringBuffer red = new StringBuffer("1");

		@Parameters( { "STLBDataID", "STLBExpVal"})
		@Test
		public void test_SharingToWordpress(int STLBDataID,
				int STLBExpVal) {
			
			STLaunchBrowser launchBrowser = new STLaunchBrowser();
			launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
			
			Vector<String> xPaths = new Vector<String>();
			Vector<String> errorMsg = new Vector<String>();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			/***********************************************************************
			 * ST_STM_05- This scenario is to verify sharing on clicking non text area
			1. Browse to any website. Launch widget
 			2. Click on wordpress icon from the list of posts
 			3. Click on non text area
			 **********************************************************************/		
			STCommonLibrary comLib = new STCommonLibrary();	
					
			String status;
			
			//********** Launch Widget******		
			launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET,OAUTH_WIDGET_WINDOW, 0, "STOP");	
			
			status=comLib.stClickAndVerify(OAUTH_WIDGET_MORE_LINK, OAUTH_WIDGET_MORE_SERVICES_FRAME, 0, "STOP");
			comLib.stWaitForElement(OAUTH_WIDGET_MORE_SERVICES_FRAME, 10);
			
			xPaths.add(OAUTH_WIDGET_MORE_SERVICES_FRAME);
			errorMsg.add("'More' link clicked, but More services frame not present on Widget");
			
			/* Verifying elements in xPaths vector*/
			status=comLib.stVerifyObjects(xPaths, errorMsg, "STOP");
			
			
			/* Cleaning XPaths and errorMsg Vectors for future */
			xPaths.clear();
			errorMsg.clear();
			
			if (!status.equalsIgnoreCase("PASS"))
			{
				comLib.stLogResult(status, "ST_WUF_05", "STOP", red);
			}
			else
			{
				/* Clicking on Wordpress post link */
				comLib.stClickAndVerify(OAUTH_WIDGET_WORDPRESS_POST_LINK, OAUTH_WIDGET_WORDPRESS_CANCEL_LINK, 0, "STOP");
				
				status=comLib.stClickAndVerify(OAUTH_WIDGET_NON_TEXT_AREA, OAUTH_WIDGET_WIDGGET_ERROR_MESSAGE, 1, "");
				
			}
			
			if(browser.isElementPresent(OAUTH_WIDGET_WIDGGET_ERROR_MESSAGE))
			{
				comLib.stLogResult("FAIL", "ST_STM_05", "STOP", red);
			}
			else
			{
			comLib.stLogResult(status, "ST_STM_05", "STOP", red);		
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
