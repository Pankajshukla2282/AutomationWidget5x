package com.sharethis.QAAutomation.SignInWidget;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;

import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STLogin;
import com.sharethis.QAAutomation.commonlib.STRegisterAndSign;

/*******************************************************************************
 * WidgetSignInWithInvalidShareThisCredentials class covered following test cases:
 * ST_SIW_02, ST_SIW_03<br>
 * TestNG annotations used
 * 
 * @Parameters - to read the input parameters from testng.xml file.
 * @Test - For test case
 * 
 * @see <br>
 *      int STLBDataID - Data ID for fetching data form Data.xml for
 *      stLaunchBrowser class.
 * @see <br>
 *      int STLBExpVal - Expected value according to expected result of test case
 *      also for making result for STLaunchBrowser class.
 * @see <br>
 *      int STBCDataID - Data ID for fetching data form Data.xml for
 *      stSetSearchOption class.
 * @see <br>
 *      int STRDataID - Data ID for fetching data form Data.xml for
 *      stSetSearchOption class.
 * @see <br>
 *      int STRExpVal - Expected value according to expected result of test case
 *      also for making result for stSetSearchOption class.
 *  
 * @see <br>
 *      StringBuffer red - Used for tracking fail test case.
 * 
 * @see <br>
 *      stLaunchBrowser class used for following function:
 * @see
 *      <li> stLaunch()
 * 
 * @see <br>
 *      CommonXPath class for calling XPath of elements.
 ******************************************************************************/

public class WidgetSignInWithInvalidShareThisCredentials {
	StringBuffer red = new StringBuffer("1");

	@Parameters( { "STLBDataID", "STLBExpVal", "STLDataID", "STLExpVal", "STBCDataID"})
	@Test
	public void test_WidgetSignInWithInvalidSharethisAccount(int STLBDataID,
			int STLBExpVal, int STLDataID, int STLExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_SIW_02- This scenario is to Signing-in into widget with invalid ShareThis credentials
		 1. Browse to any website. Click on Browser plugin or Bookmarklet or button in the 
		 	address bar or Button available on webpage
		 2. Click on ShareThis icon present in the widget footer
		 3. Provide invalid ShareThis login details and click on sign-in.
		 
		 * ST_SIW_03- This scenario is to check Creating a new ShareThis Account without 
		 * providing data in available text fields
		 1. Browse to any website.
		 2. Click on ShareThis widget to launch.
		 3. Click on Sign in link, login window appears.
		 4. Click on Register link
		 5. Try to register without providing any data.  
		 **********************************************************************/	
		
		STCommonLibrary comLib = new STCommonLibrary();	
		STLogin login = new STLogin ();
//		STRegisterAndSign register = new STRegisterAndSign();
		String emailerrormsg = "";
		String pwderrormsg = "";	
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");		
		
		/* Execution of ST_SIW_03 */
		Vector <String> xpath = new Vector<String>();
		Vector<String> errorMsg = new Vector<String>();
		
		xpath.add(SIGNIN_BUTTON);
		errorMsg.add("Sign in button is not present");
		
		comLib.stVerifyObjects(xpath, errorMsg, "");
		
		browser.click(SIGNIN_BUTTON);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (browser.isElementPresent(SIGNIN_ERROR_PANEL))
		{
			emailerrormsg = browser.getText(SIGNIN_ERROR_PANEL);
			System.out.println(emailerrormsg);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
//		if (browser.isElementPresent(SIGNIN_PASSWORD_ERRORMSG))
//		{
//			 pwderrormsg = browser.getText(SIGNIN_PASSWORD_ERRORMSG);
//		}
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		if ((emailerrormsg.contains("* Username is required")) )
		{
			status = "PASS";
			browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
		}else
		{
			status = "FAIL";
			browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
		}
		
		comLib.stLogResult(status, "ST_SIW_03", "", red);
		
		/* Execution of ST_SIW_02 */
		//********* Click on Sign In Button************
		status = login.stSharethisLogin(STLDataID, STLExpVal, "STOP");
		
		comLib.stLogResult(status, "ST_SIW_02", "STOP", red);		
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
}
