package com.sharethis.QAAutomation.OAuthWidget;

/**********************************************************************
 * Test Cases Covered: ST_WUF_01;ST_WUF_02;ST_WUF_03;ST_WUF_06;ST_WUF_07
 *********************************************************************/
import java.util.Vector;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.sharethis.QAAutomation.commonlib.*;


/*******************************************************************************
 * ShareThisWidgetLaunchVerification class covered following test cases:
 * ST_WUF_01; ST_WUF_02; ST_WUF_03; ST_WUF_06; ST_WUF_07<br>
 * TestNG annotations used
 * 
 * @Parameters - to read the input parameters from testng.xml file.
 * @Test - For test case
 * 
 * @see <br>
 *      int ZLBDataID - Data ID for fetching data form Data.xml for
 *      stLaunchBrowser class.
 * @see <br>
 *      int ZLBExpVal - Expected value according to expected result of test case
 *      also for making result for ZLaunchBrowser class.
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
 *      ZXPath class for calling XPath of elements.
 ******************************************************************************/

public class WidgetLaunchVerification {
	StringBuffer red = new StringBuffer("1");


	@Parameters( { "STLBDataID", "STLBExpVal"})
	@Test
	public void test_ShareThisWidgetLaunchVerification(int STLBDataID,
			int STLBExpVal) {
		
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/***********************************************************************
		 * ST_WUF_01- This scenario is to verify that Widget get launch.
		 * 1. Browse to any website.
		   2. Click on ShareThis widget		  
		 **********************************************************************/		
		STCommonLibrary comLib = new STCommonLibrary();		

		Vector<String> xPaths = new Vector<String>();
		Vector<String> errorMsg = new Vector<String>();
		String status;
		
		status=launchBrowser.stLaunchWidget(OAUTH_WIDGET, RESKINNED_WIDGET_WINDOW, 0, "STOP");
		comLib.stLogResult(status, "ST_WUF_01", "", red);
		/***********************************************************************
		 *ST_WUF_02- This scenario is to verify Sign In and Do not track link.
		 *1. Browse to any website.
		  2. Click on ShareThis widget
		 **********************************************************************/
		xPaths.add(RESKINNED_WIDGET_SIGNIN_LINK);
		errorMsg.add("Sign In Link not found");
		xPaths.add(RESKINNED_WIDGET_DONOTTRACK_LINK);
		errorMsg.add("Do Not Track Link not found");
		
		comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_02");
		comLib.stLogResult("PASS", "ST_WUF_02", "Continue", red);
		/***********************************************************************
		 *ST_WUF_03- This scenario is to verify Search field on Widget.
		 *1. Browse to any website.
		  2. Click on ShareThis widget
	      3. Verfiy the Search Field.
		 **********************************************************************/
		xPaths.clear();
		errorMsg.clear();
		
		xPaths.add(RESKINNED_WIDGET_SEARCH_FIELD);
		errorMsg.add("Widget window not found");
		
		comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_01");
		comLib.stLogResult("PASS", "ST_WUF_03", "Continue", red);
		/***********************************************************************
		 *ST_WUF_07- This scenario is to verify Scroll bar on Widget.
		 *1. Browse to any website.
		  2. Click on ShareThis widget
	      3. Verfiy the Scroll Bar.
		 **********************************************************************/
		xPaths.clear();
		errorMsg.clear();
		
		xPaths.add(RESKINNED_WIDGET_SCROLLBAR);
		errorMsg.add("Scrollbar is not present");
		
		comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_07");
		comLib.stLogResult("PASS", "ST_WUF_07", "Continue", red);
		/***********************************************************************
		 *ST_WUF_06- This scenario is to verify Closing of widget.
		 *1. Browse to any website.
		  2. Click on ShareThis widget to launch.
		  3. Close the Widget
		 **********************************************************************/
		xPaths.clear();
		errorMsg.clear();
		
		xPaths.add(RESKINNED_WIDGET_CLOSE_BUTTON);
		errorMsg.add("Close button is not found");
				
		comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_06");
		
		status=comLib.stClick(RESKINNED_WIDGET_CLOSE_BUTTON, "Failed to Close widget", "STOP");
		comLib.stLogResult(status, "ST_WUF_06", "Continue", red);
		
		comLib.stTearDown(red);

	}

	@AfterTest
	public void CloseBrowser() {
		STCommonLibrary comLib = new STCommonLibrary();
		red.replace(0, 1, "1");
		comLib.stTearDown(red);
	}
	
	@AfterSuite
    public void HTMLReport()
    {
    	STHTMLReport report=new STHTMLReport();
    	report.stHTMLReport("Re-skinned Widget");
    }

}
