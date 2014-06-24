package com.sharethis.QAAutomation.oauthUIandFunctions;

import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import java.util.Vector;

import org.testng.annotations.*;

import com.sharethis.QAAutomation.commonlib.STCommonLibrary;
import com.sharethis.QAAutomation.commonlib.STHTMLReport;
import com.sharethis.QAAutomation.commonlib.STLaunchBrowser;

/*******************************************************************************
 * WidgetUIVerification class covered following test cases:
 * ST_CASI_01;ST_CASI_02;ST_CASI_03;ST_CASI_04;ST_CASI_05;ST_CASI_06;<br>
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

public class WidgetUIVerification {
	StringBuffer red = new StringBuffer("1");
	
	@Parameters({"STLBDataID","STLBExpVal","STBCDataID1","STBCDataID2"})
	@Test
	public void test_WidgetUIVerification(int STLBDataID, int STLBExpVal, int STBCDataID1, int STBCDataID2 )
	{
		STLaunchBrowser launchBrowser = new STLaunchBrowser();
		launchBrowser.stLaunch(STLBDataID, STLBExpVal, "STOP");
	
	try {
		Thread.sleep(8000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
	/***********************************************************************
	 * ST_WUF_01- This scenario is to verify that Widget get launch.
	 * 1. Browse to any website.
	   2. Click on ShareThis chicklet		  
	 **********************************************************************/
	
	STCommonLibrary comLib = new STCommonLibrary();
	
	Vector<String> xPaths = new Vector<String>();
	Vector<String> errorMsg = new Vector<String>();
	String status;
	
	status=launchBrowser.stLaunchOauthWidget(OAUTH_WIDGET, OAUTH_WIDGET_WINDOW, 0, "STOP");
	comLib.stLogResult(status, "ST_WUF_01", "STOP", red);
	
	/***********************************************************************
	 *ST_WUF_02- This scenario is to verify following elements on Widget :
	 *Email, FB, Linkedin and Twitter buttons 
	 *"More" button in the footer. 
	 *"Sign in","Do not Track" links and "Share" button should be present
	 *Header should show " Share this with your friends"
	 *Widget should show Title, URL & Thumbnail and summary of the webpage
	 *Message box should be present with text "Write Your Comment here"
	  1. Browse to any website.
	  2. Click on ShareThis widget
	 **********************************************************************/
	
	xPaths.add(OAUTH_WIDGET_SIGNIN_LINK);
	errorMsg.add("Sign In Link not found");
	
	xPaths.add(OAUTH_DO_NOT_TRACK_LINK);
	errorMsg.add("Do Not Track Link not found");
	
	xPaths.add(OAUTH_WIDGET_EMAIL_BIGICON);
	errorMsg.add("Email Icon Not found");
	
	xPaths.add(OAUTH_WIDGET_FB_BIGICON);
	errorMsg.add("FB Icon Not found");
	
	xPaths.add(OAUTH_WIDGET_TWITTER_BIGICON);
	errorMsg.add("Twitter Icon Not found");
	
	xPaths.add(OAUTH_WIDGET_LINKEDIN_BIGICON);
	errorMsg.add("LinkedIn Icon Not found");
	
	xPaths.add(OAUTH_WIDGET_MORE_LINK);
	errorMsg.add(" 'More Link'  Not found");
	
	xPaths.add(OAUTH_WIDGET_EMAIL_LABEL );
	errorMsg.add(" Widget Header not present");
	
	xPaths.add(OAUTH_WIDGET_CLOSE_BUTTON);
	errorMsg.add("Close button not present on OAUth Widget");
	
	xPaths.add(OAUTH_WIDGET_MESSAGE_BOX);
	errorMsg.add("Message Box not present on OAUth Widget");
	
	xPaths.add(OAUTH_WIDGET_TITLE);
	errorMsg.add("Title of the article not present on OAUth Widget");
	
	xPaths.add(OAUTH_WIDGET_SNIPPET);
	errorMsg.add("Snippet of the article not present on OAUth Widget");
	
	xPaths.add(OAUTH_WIDGET_URL);
	errorMsg.add("URL of the article not present on OAUth Widget");
	
	xPaths.add(OAUTH_WIDGET_IMAGE_LINK);
	errorMsg.add("Thumbnail of the article not present on OAUth Widget");
	
	 /*Verifying elements in xPaths vector*/
	status = comLib.stVerifyObjects(xPaths, errorMsg, "ST_WUF_02");
	
	System.out.println("Status for WUF_02 " +status);
	
	/* Cleaning XPaths and errorMsg Vectors for future */
	xPaths.clear();
	errorMsg.clear();
	
	comLib.stLogResult(status, "ST_WUF_02", "", red);
	
	/***********************************************************************
	 *ST_WUF_03 - Checking "More" / "Back to Default View" functionality :
	 1. Browse to any website. Click on ShareThis button to launch widget 5x
	 2. Click on "More" link
	 3. Click on "Back to Default View" link
	 **********************************************************************/
	
	 /*Clicking on "More" link in Widget and waiting for next frame to load */
	status=comLib.stClickAndVerify(OAUTH_WIDGET_MORE_LINK, OAUTH_WIDGET_MORE_SERVICES_FRAME, 0, "STOP");
	comLib.stWaitForElement(OAUTH_WIDGET_MORE_SERVICES_FRAME, 10);
	
	xPaths.add(OAUTH_WIDGET_MORE_SERVICES_FRAME);
	errorMsg.add("'More' link clicked, but More services frame not present on Widget");
	
	xPaths.add(OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK);
	errorMsg.add("'Back to Default View' link not present");
	
	/* Verifying elements in xPaths vector*/
	status=comLib.stVerifyObjects(xPaths, errorMsg, "STOP");
	
	/* Cleaning XPaths and errorMsg Vectors for future */
	xPaths.clear();
	errorMsg.clear();
	
	/*Stop execution if elements not present*/ 
	if (!status.equalsIgnoreCase("PASS"))
	{
		comLib.stLogResult(status, "ST_WUF_03", "STOP", red);
	}
	else
	{	
	/* Clicking on 'Back to default view' link */
	comLib.stClickAndVerify(OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK, OAUTH_WIDGET_EMAIL_BIGICON, 0, "STOP");
	
	xPaths.add(OAUTH_WIDGET_EMAIL_LABEL);
	errorMsg.add("'Back to Default View' link clicked, but Widget header not present ");
	
	status=comLib.stVerifyObjects(xPaths, errorMsg, "STOP");
	
	comLib.stLogResult(status, "ST_WUF_03", "", red);
	 
	/*Cleaning XPaths and errorMsg Vectors for future */
	xPaths.clear();
	errorMsg.clear();
	}
	
	 /***********************************************************************
	 *ST_WUF_05 - Checking "Powered By ShareThis" link in the footer :
	 1. Browse to any website. Click on ShareThis button to launch widget 5x
	 2. Click on "Powered By ShareThis" link in the widget footer
	 **********************************************************************/
	 /* Clicking on footer link and verifying newly opened page */
	 status=comLib.stClickAndVerifyLink(OAUTH_POWERED_BY_SHARETHIS_LINK, STBCDataID2, 0, 0, "STOP");
	 //System.out.println("Status of click and verify link : " +status);
		 
	 comLib.stLogResult("PASS", "ST_WUF_05", "", red);	 
	
	 /***********************************************************************
	 *ST_WUF_06 - Checking Footer "Do Not Track" link :
	 1. Browse to any website. Click on ShareThis button to launch widget 5x
	 2. Click on "Powered By ShareThis" link in the widget footer
	 **********************************************************************/
	
	  /*Clicking on footer link and verifying newly opened page */
	 status=comLib.stClickAndVerifyLink(OAUTH_DO_NOT_TRACK_LINK, STBCDataID1, 0, 0, "STOP");
		 
	 comLib.stLogResult("PASS", "ST_WUF_06", "", red); 
	
	 /***********************************************************************
	 *ST_WUF_04 - Closing widget using 'x' button :
	 1. Browse to any website. Click on ShareThis button to launch widget 5x
	 2. Click on "Close(x)" button
	 **********************************************************************/
	 
	 xPaths.add(OAUTH_WIDGET_CLOSE_BUTTON);
	 errorMsg.add("Close button not present on Widget");
	 
	 status=comLib.stVerifyObjects(xPaths, errorMsg, "STOP");
	 
	 /*Cleaning XPaths and errorMsg Vectors for future */
	 xPaths.clear();
	 errorMsg.clear();
	 
	 if(status.equalsIgnoreCase("FAIL"))
	 {
		 comLib.stLogResult("FAIL", "ST_WUF_04", "", red); 
	 }
	 else
	 {
		 /* Clicking CLose Button */
		 status=comLib.stClickAndVerify(OAUTH_WIDGET_CLOSE_BUTTON, OAUTH_WIDGET_OVERLAY, 1, "STOP");
		 
		 comLib.stLogResult(status, "ST_WUF_04", "", red);
	 }
		
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
    	report.stHTMLReport("OAuth Widget");
    }
	

}
