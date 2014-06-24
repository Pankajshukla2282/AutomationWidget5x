package com.sharethis.QAAutomation.createAccountUsingWidget;

/**********************************************************************
 * Test Cases Covered: ST_CAW_01
 *********************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.REGISTRATION_EMAIL_LABEL;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STRegisterAndSign;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

public class WidgetCreateAccountWithValidCredentials {
	StringBuffer red = new StringBuffer("1");
	StringBuffer username=new StringBuffer("");

	@Parameters( { "STLBDataID", "STLBExpVal", "STRDataID", "STRExpVal", "STBCDataID"})
	@Test
	public void test_WidgetCreateAccountWithValidEmail(int STLBDataID,
			int STLBExpVal, int STRDataID, int STRExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		* ST_CAW_01- This scenario is to check Creating a new ShareThis Account with valid Email id 
		1. Browse to any website.
		2. Click on ShareThis widget to launch.
		3. Click on Sign in link, login window appears.
		4. Click on Register link
		5. Provide all required valid information and click on register button.
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STRegisterAndSign register = new STRegisterAndSign();
				
		String status;
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUTH_WIDGET_SIGNIN_LINK, STBCDataID, 0, 1, "");
		
		//********* Click on Registration link************		
//		comLib.stClickAndVerify(CREATE_ONE_TODAY_LINK1, REGISTRATION_EMAIL_LABEL, 0, "");
		
		System.out.println("STRDataID "+STRDataID);
		System.out.println("STRExpVal " +STRExpVal);
		
		//***********Fill Registartion page**********
		register.stRegistration(STRDataID, STRExpVal, "", username);
		
		//********* Click on Register Button************
		status = register.stClickonRegister(0, "");
		
		comLib.stLogResult(status, "ST_CAW_01", "STOP", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
