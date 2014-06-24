package com.sharethis.QAAutomation.doneScreen;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STDoneScreen;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

public class DoneScreenLinksVerification {
	
StringBuffer red = new StringBuffer("1");
	
	@Parameters({"STLBDataID","STLBExpVal"})
	@Test
	public void test_doneScreenLinksVerification(int STLBDataID, int STLBExpVal)
	{
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		STCommonLibrary comLib = new STCommonLibrary();
		Vector<String> xPaths = new Vector<String>();
		Vector<String> errorMsg = new Vector<String>();
		STDoneScreen stDoneScreen = new STDoneScreen();
		String status;
		
		/* Launch Browser using data in Login Datasheet */
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
	
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*Launching OAUTH Widget */
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		/*Verifying presence of 'More' link */
		xPaths.add(OAUTH_WIDGET_MORE_LINK);
		errorMsg.add(" 'More Link'  Not found");
		
		 /*Verifying elements in xPaths vector*/
		status = comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_02");
		
		/* Cleaning XPaths and errorMsg Vectors for future */
		xPaths.clear();
		errorMsg.clear();
		
		/*Clicking on "More" link in Widget and waiting for next frame to load */
		status=comLib.stClickAndVerify(OAUTH_WIDGET_MORE_LINK, OAUTH_WIDGET_MORE_SERVICES_FRAME, 0, "STOP");
		comLib.stWaitForElement(OAUTH_WIDGET_MORE_SERVICES_FRAME, 10);
		
		xPaths.add(OAUTH_WIDGET_MORE_SERVICES_FRAME);
		errorMsg.add("'More' link clicked, but More services frame not present on Widget");
		
		xPaths.add(OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK);
		errorMsg.add("'Back to Default View' link not present");
		
		/* Verifying elements in xPaths vector*/
		status=comLib.stVerifyObjects(xPaths, errorMsg, "");
		
		/* Cleaning XPaths and errorMsg Vectors for future */
		xPaths.clear();
		errorMsg.clear();
		
		/* Clicking on any post and closing the newly opened landing window */
		//comLib.stClickAndVerifyLink(OAUTH_WIDGET_FIRST_POST_LINK, 1, -1, 1, "");
		
		status=stDoneScreen.stOpenDoneScreen(OAUTH_WIDGET_FIRST_POST_LINK, 1, "");
		
		/***********************************************************************
		 * ST_WUF_01- This scenario is to verify that Widget get launch.
		 * 1. Browse to any website.
		   2. Click on ShareThis chicklet		  
		 **********************************************************************/
		
		
		comLib.stLogResult(status, "ST_DS_01", "STOP", red);
		
	}
	
	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
