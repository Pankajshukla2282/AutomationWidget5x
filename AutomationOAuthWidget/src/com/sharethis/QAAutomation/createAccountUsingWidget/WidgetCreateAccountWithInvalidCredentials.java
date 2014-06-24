package com.sharethis.QAAutomation.createAccountUsingWidget;

/**********************************************************************
 * Test Cases Covered: ST_CAW_02, ST_CAW_03, ST_CAW_04
 *********************************************************************/

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;
import com.sharethis.QAAutomation.commonlib.STRegisterAndSign;

/*******************************************************************************
 * WidgetCreateAccountWithInvalidCredentials class covered following test cases:
 * ST_CAW_02;<br>
 * ST_CAW_03;<br>
 * ST_CAW_04;<br>
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
public class WidgetCreateAccountWithInvalidCredentials {
	StringBuffer red = new StringBuffer("1");
	StringBuffer username=new StringBuffer("");

	@Parameters( { "STLBDataID", "STLBExpVal", "STRDataID", "STRExpVal", "STBCDataID"})
	@Test
	public void test_WidgetCreateAccountWithInvalidEmail(int STLBDataID,
			int STLBExpVal, int STRDataID, int STRExpVal, int STBCDataID) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        String stFormat = sdf.format(cal.getTime());
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_CAW_02- This scenario is to check Creating a new ShareThis Account with invalid email id
		 1. Browse to any website.
		 2. Click on ShareThis widget to launch.
		 3. Click on Sign in link, login window appears.
		 4. Click on Register link
		 5. Provide invalid information and click on register button.
		 
		 * ST_CAW_03- This scenario is to check Creating a new ShareThis Account without 
		 * providing data in available text fields
		 1. Browse to any website.
		 2. Click on ShareThis widget to launch.
		 3. Click on Sign in link, login window appears.
		 4. Click on Register link
		 5. Try to register without providing any data. 
		 
		 * ST_CAW_04- This scenario is to check Creating a new ShareThis account with existing username
		 1. Browse to any website.
		 2. Click on ShareThis widget to launch.
		 3. Click on Sign in link, login window appears.
		 4. Click on Register link
		 5. Try to register with existing account.
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();	
		STRegisterAndSign register = new STRegisterAndSign();
				
		String status;
		String actcreationerrormsg = "";
		
		Vector<String> xPath=new Vector<String>();
        Vector<String> errorMsg=new Vector<String>();  
		
		//********** Launch Widget******		
		launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
		
		//********Click on Sign in link*****		
		comLib.stClickAndVerifyLink(OAUth_WIDGET_SIGN_IN_LINK, STBCDataID, 0, 1, "");
		
		//********* Click on Register Button without entering any fields************
		/* Execution of CAW_03 */ 
//		status = register.stClickonRegister(2, "");
		xPath.add(CREATEACCOUNT_BUTTON);
        errorMsg.add("Create account button is not present");
        comLib.stVerifyObjects(xPath, errorMsg, "STOP");
        xPath.clear();
        errorMsg.clear();
        
        browser.click(CREATEACCOUNT_BUTTON);
        try {
            Thread.sleep(30000);
      } catch (InterruptedException e1) {
            e1.printStackTrace();
      }
      
      if(browser.isElementPresent(SIGNIN_ERROR_PANEL));
      actcreationerrormsg = browser.getText(SIGNIN_ERROR_PANEL).trim();
      System.out.println(actcreationerrormsg);
//      if(("!Error Username is required Password is required. Confirm Password is required." +
//      		" Please accept the terms of service to proceed.").contains(actcreationerrormsg))
    	  if (actcreationerrormsg.contains("* Username is required"))
      {
    	  status = "PASS";
    	  System.out.println("Item 1 Passed");
      }else
      {
    	  status = "FAIL";
    	  System.out.println("Item 1 Failed");
      }
      browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
      try {
          Thread.sleep(30000);
    } catch (InterruptedException e1) {
          e1.printStackTrace();
    }
		/* Logging results for CAW_03 */ 
		comLib.stLogResult(status, "ST_CAW_03", "", red);
		
		/* Execution of CAW_04 */ 
//		xPath.add(REGISTRATION_EMAIL);
//        errorMsg.add("Email field is not present");
//        
//        xPath.add(REGISTRATION_PASSWORD_LABEL);
//        errorMsg.add("Password Label is not present");
//        
//        xPath.add(REGISTRATION_PASSWORD);
//        errorMsg.add("Password field is not present");
//        
//        comLib.stVerifyObjects(xPath, errorMsg, "STOP");
//        
//        browser.focus(REGISTRATION_EMAIL_LABEL);
//        browser.click(REGISTRATION_EMAIL_LABEL);
//        try {
//            Thread.sleep(30000);
//      } catch (InterruptedException e1) {
//            e1.printStackTrace();
//      }
//		browser.type(REGISTRATION_EMAIL, "testnguser1001@sharethis.com" );	
//		
//		browser.focus(REGISTRATION_PASSWORD_LABEL);
//		browser.click(REGISTRATION_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_PASSWORD, "testing123" );	
//		
//		browser.focus(REGISTRATION_CONFIRM_PASSWORD_LABEL);
//		browser.click(REGISTRATION_CONFIRM_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_CONFIRM_PASSWORD, "testing123" );	
//		
//		browser.check(TERMS_CHECKBOX);
//		xPath.clear();
//		errorMsg.clear();
//		//********* Click on Register Button************
////		status = register.stClickonRegister(-1, "");
//		browser.click(CREATEACCOUNT_BUTTON);
//        try {
//            Thread.sleep(30000);
//      } catch (InterruptedException e1) {
//            e1.printStackTrace();
//      }
//      
//      if(browser.isElementPresent(EMAIL_ERROR_MSG));
//      actcreationerrormsg = browser.getText(EMAIL_ERROR_MSG);
//      
//      if(actcreationerrormsg.contains("An account has already been registered with " +
//                        "this email address."))
//      {
//    	  status = "PASS";
//      }else
//      {
//    	  status = "FAIL";
//      } 		
//		/* Logging Results for CAW_04 */ 
//		comLib.stLogResult(status, "ST_CAW_04", "", red);
//		
//		/* Execution of SIW_04 */
//		browser.focus(REGISTRATION_PASSWORD_LABEL);
//		browser.click(REGISTRATION_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_PASSWORD, "tes" );
//		
//		browser.focus(REGISTRATION_CONFIRM_PASSWORD_LABEL);
//		browser.click(REGISTRATION_CONFIRM_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_CONFIRM_PASSWORD, "tes" );
//		
//		browser.focus(REGISTRATION_EMAIL_LABEL);
//		browser.click(REGISTRATION_EMAIL_LABEL);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		String email="test" + stFormat +"@"+ "gmail.com";
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		browser.type(REGISTRATION_EMAIL, email);	
//		
//		browser.check(TERMS_CHECKBOX);
//		//********* Click on Register Button************
////		status = register.stClickonRegister(8, "");
//		browser.click(CREATEACCOUNT_BUTTON);
//        try {
//            Thread.sleep(30000);
//      } catch (InterruptedException e1) {
//            e1.printStackTrace();
//      }
//      
//      if(browser.isElementPresent(SIGNIN_ERROR_PANEL));
//      actcreationerrormsg = browser.getText(SIGNIN_ERROR_PANEL);
//      System.out.println(actcreationerrormsg);
//      
//      if(actcreationerrormsg.contains("*Password should be atleast 6 characters long."+
//                         "*Confirm Password should be atleast 6 characters."))
//      {
//    	  status = "PASS";
//    	  System.out.println("Item 2 Passed");
//      }else
//      {
//    	  status = "FAIL";
//    	  System.out.println("Item 2 Failed");
//      }
//      browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
//      try {
//          Thread.sleep(30000);
//    } catch (InterruptedException e1) {
//          e1.printStackTrace();
//    }
//		
//		/* Logging Results for ST_SIW_04 */
//		comLib.stLogResult(status, "ST_SIW_04", "", red);
//		
//		/* Execution of SIW_05 */
//		browser.focus(REGISTRATION_PASSWORD_LABEL);
//		browser.click(REGISTRATION_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_PASSWORD, "testing123" );
//		
//		browser.focus(REGISTRATION_CONFIRM_PASSWORD_LABEL);
//		browser.click(REGISTRATION_CONFIRM_PASSWORD_LABEL);		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}		
//		browser.type(REGISTRATION_CONFIRM_PASSWORD, "different123" );
//		
//		//********* Click on Register Button************
////		status = register.stClickonRegister(-8, "");
//		browser.click(CREATEACCOUNT_BUTTON);
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//      
//        if(browser.isElementPresent(SIGNIN_ERROR_PANEL));
//        actcreationerrormsg = browser.getText(SIGNIN_ERROR_PANEL);
//        System.out.println(actcreationerrormsg);
//      
//        if(actcreationerrormsg.contains("Password and Confirm Password does not match."))
//        {	
//    	  status = "PASS";
//        }else
//        {
//    	  status = "FAIL";
//        }
//      browser.click(SIGNIN_ERROR_PANEL_OK_BUTTON);
//      try {
//          Thread.sleep(30000);
//    } catch (InterruptedException e1) {
//          e1.printStackTrace();
//    }
//		
//		/* Logging Results for ST_SIW_)% */
//		comLib.stLogResult(status, "ST_SIW_05", "", red);
//		
//			
//		//***********Fill Registartion page**********
//		//* Execution of CAW_02 */
//		register.stRegistration(STRDataID, STRExpVal, "", username);
//		
//		//********* Click on Register Button************
////		status = register.stClickonRegister(-2, "");
//		browser.click(CREATEACCOUNT_BUTTON);
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//        if(browser.isElementPresent(COMMON_SIGN_OUT_LINK))
//        {	
//      	  status = "PASS";
//          }else
//          {
//      	  status = "FAIL";
//          }
//		
//		comLib.stLogResult(status, "ST_CAW_02", "STOP", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}

}
