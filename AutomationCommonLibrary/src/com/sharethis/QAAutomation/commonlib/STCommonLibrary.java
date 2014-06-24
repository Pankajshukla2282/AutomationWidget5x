package com.sharethis.QAAutomation.commonlib;


import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.*;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;
//import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import junit.framework.TestCase;
import org.testng.Reporter;
import bsh.EvalError;
import bsh.Interpreter;
import com.thoughtworks.selenium.SeleniumException;

/*******************************************************************************
 * STCommonLibrary class used for performing common actions e.g. LogOut,Click
 * etc.
 * 
 * @see <br>
 *      This class contain following functions:
 * @see
 *      <li> stClick()
 * @see
 *      <li> stClick()
 * @see
 *      <li> stClickAndVerify
 * @see
 *      <li> stLogOut()
 * @see
 *      <li> stLogResult()
 * @see
 *      <li> stTearDown()
 * @see
 *      <li> stVerifyObjects()
 * @see
 *      <li> stWaitForElement()
 ******************************************************************************/

public class STCommonLibrary extends TestCase {

	// *********************[ Logout Function ]*********************************


	// *******************[ Logout Function End ]*******************************

	// ********************[ stTearDown Function ]*******************************

	/***************************************************************************
	 * Function for closing browser.
	 * 
	 * @param red -
	 *            For formatting Report according to result 0 For Green, 1 for
	 *            Red
	 **************************************************************************/
	public void stTearDown(StringBuffer red) {
		try {
			browser.stop();
			if ("0".equalsIgnoreCase(red.toString()))
				assertEquals(1, 0);
		} catch (SeleniumException sexp) {
			Reporter.log(sexp.getMessage());
		}
	}

	// ******************[ stTearDown Function End/ ]****************************

	// ***************[ stClickAndVerify Function Start ]************************

	/***************************************************************************
	 * Function for click on element and verification of element after click.
	 * 
	 * @param targetXPath -
	 *            Object's XPath to click.
	 * @param verifyXpath -
	 *            Object's XPath to verify after click on target XPath.
	 * @param expVal -
	 *            0 for successful completion, 1 for Error.
	 * @param flow - ""
	 *            for continue or "STOP,TC_ID" for stop the execution of script
	 *            if function fails.
	 * @return PASS or FAIL
	 **************************************************************************/
	public String stClickAndVerify(String targetXPath, String verifyXpath,
			int expVal, String flow) {
		int actVal = 1000;
		String returnVal = null;
		Block: {
			hm.put("targetXPath", targetXPath);
			hm.put("verifyXpath", verifyXpath);
			//browser.doubleClick(targetXPath);
			browser.focus(targetXPath);
			browser.click(targetXPath);
			//browser.mouseDown(targetXPath);
			//browser.mouseUp(targetXPath);
			
			


			if (stWaitForElement(verifyXpath, 50)) {
				actVal = 0;
				break Block;
			}
			actVal = 1;
		}

		returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
				"stClickAndVerify", flow, hm);
		if (flow.contains("STOP")) {
			assertEquals("PASS", returnVal);
		}
		return returnVal;
	}

	// *******************[ stClickAndVerify Function End ]**********************

	// *******************[ stVerifyObjects Function Start ]*********************

	/***************************************************************************
	 * Function for the verification of elements.
	 * 
	 * @param targetXPaths -
	 *            Objects XPath to verify.
	 * @param Error_Msg -
	 *            Error message if target XPath not found.
	 * @param flow - ""
	 *            for continue or "STOP,TC_ID" for stop the execution of script
	 *            if function fails.
	 * @return PASS or FAIL
	 **************************************************************************/
	public String stVerifyObjects(Vector<String> targetXPaths,
			Vector<String> errorMsg, String flow) {
		int actVal = 1000, count = 0;
		String returnVal = null;
		hm.clear();

		for (int index = 0; index < targetXPaths.size(); index++) {

			if (stWaitForElement(targetXPaths.get(index), 100)) {
				count++;
				hm.put("<div><strong>targetXPath" + index + "</strong></div>",
						targetXPaths.get(index)
								+ "<div><strong> -- PASS</strong></div>");
			} else {
				hm.put("<div><strong>targetXPath" + index + "</strong></div>",
						targetXPaths.get(index) + "<div><strong>"
								+ " -- FAIL --> " + errorMsg.get(index)
								+ "</strong></div>");
			}

		}
		if (count == targetXPaths.size())
			actVal = 0;
		else
			actVal = 1;

		returnVal = STFunctionLibrary.stRetValDes(0, actVal, "stVerifyObjects",
				flow, hm);

		if (flow.contains("STOP")) {
			assertEquals("PASS", returnVal);
		}
		return returnVal;
	}

	// ******************[ stVerifyObjects Function End ]************************

	// ********************[ stLogResult Function ]******************************

	/***************************************************************************
	 * Function for logging report of test case e.g. PASS,FAIL or SKIP.
	 * 
	 * @param status -
	 *            This parameter takes 3 type of values PASS,FAIL,SKIP
	 * @param tcID -
	 *            Test Case ID
	 * @param flow - ""
	 *            for continue or "STOP,TC_ID" for stop the execution of script
	 *            if function fails.
	 * @param red -
	 *            For formatting Report according to result 0 For Green, 1 for
	 *            Red
	 **************************************************************************/
	public void stLogResult(String status, String tcID, String flow,
			StringBuffer red) {
		com.sharethis.QAAutomation.commonlib.STFunctionLibrary.stEndResult(tcID, status);
		if (status.equalsIgnoreCase("PASS")) {
			Reporter
					.log("<div style =\" background-color:#FDFF10;color:green;\"><strong>"
							+ tcID + "--->" + status + "</strong></div>");
		} else {
			red.replace(0, 1, "0");
			Reporter
					.log("<div style =\" background-color:#FDFF10;color:red;\"><strong>"
							+ tcID + "--->" + status + "</strong></div>");
			if (flow.contains("Stop")) {
				assertEquals(0, 1);
			} else
				red.replace(0, 1, "0");
		}
	}

	// *****************[ stLogResult Function End ]*****************************

	// ****************[ stClick Function Start ]****************************

	/***************************************************************************
	 * Function for clicking on element
	 * 
	 * @param targetXPath -
	 *            XPath of the Element
	 * @param errorMsg -
	 *            Error message if target XPath not found.
	 * @param flow - ""
	 *            for continue or "STOP,TC_ID" for stop the execution of script
	 *            if function fails.
	 * @return PASS or FAIL
	 **************************************************************************/
	public String stClick(String targetXPath, String errorMsg, String flow) {
		int actVal = 1000;
		String returnVal = null;
		hm.clear();
		Block: {

			if (stWaitForElement(targetXPath, 500)) {
				actVal = 0;
				hm.put("<div><strong>targetXPath</strong></div>", targetXPath
						+ "<div><strong>" + " -- PASS </strong></div>");

				browser.click(targetXPath);
				break Block;

			}
			actVal = 1;
			hm.put("<div><strong>targetXPath</strong></div>", targetXPath
					+ "<div><strong>" + " -- FAIL --> " + errorMsg
					+ "</strong></div>");
		}

		returnVal = STFunctionLibrary.stRetValDes(0, actVal, "stClick", flow,
				hm);
		if (flow.contains("STOP")) {
			assertEquals("PASS", returnVal);
		}
		return returnVal;
	}

	// *****************[ stClick Function End ]*****************************

	
	// *******************[ zClick function Start ]*****************************
	/***************************************************************************
	 * Function for using Hot Keys
	 * 
	 * @param sleepTime -
	 *            Time to wait element
	 * @param args -
	 *            Key combination e.g. "VK_N","VK_M"
	 **************************************************************************/
	public void stClickKey(int sleepTime, String... args) {
		try {
			Interpreter In = new Interpreter();
			try {
				In.set("rb2", new Robot());
				In.eval("rb2.mouseMove(70,515" + ");");
				for (String s : args)
					In.eval("rb2.keyPress(java.awt.event.KeyEvent." + s + ");");
				for (String s : args)
					In.eval("rb2.keyRelease(java.awt.event.KeyEvent." + s
							+ ");");
			} catch (EvalError e1) {
				e1.printStackTrace();
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ********************[ stClickKey function End ]******************************
	
	/***************************************************************************
     * stMaxSleep is for setting maximum sleep time or counter.
     **************************************************************************/
    public static int stMaxSleep= 15;
    
    /***************************************************************************
     * stWaitForLoad is Wait for the Element to Load.
     **************************************************************************/
    public static int stWaitForLoad = 300;
    
    /***************************************************************************
     * ST_CLOSE is for passing value 0 to new window related functions
     **************************************************************************/
    public static int ST_CLOSE = 0; 
    /***************************************************************************
     * ST_NOT_CLOSE is for passing value 1 to new window related functions
     **************************************************************************/
    public static int ST_NOT_CLOSE = 1;

	// ****************[ stWaitForElement function Start ]***********************
	/***************************************************************************
	 * Function for checking the presence of the element
	 * 
	 * @param locator -
	 *            XPath of the element to find
	 * @param counter -
	 *            Number of times check the element
	 * @return true or false
	 **************************************************************************/
	public boolean stWaitForElement(String locator, int counter) {
		int i = 0;
		while (i < counter) {
			try {
				i++;
				try {
	    			Thread.sleep(1000);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}		
				if (browser.isElementPresent(locator))
					return (true);

			} catch (SeleniumException e) {
				i++;
			}
		}
		return false;
	}

	// *****************[ stWaitForElement function End ]************************

	// *******************[ stClickOnTab function Start ]************************
	/***************************************************************************
	 * Function for clicking on tab
	 * 
	 * @param tabName -
	 *            Tab name to be click e.g. Email, AddressBook, Preferences
	 * @param flow=
	 *            Defines the flow on the test script. If Flow="STOP", script
	 *            execution will be stopped in cases of any failure. If
	 *            Flow="Continue", script execution will not stop even in case
	 *            of any failures.
	 * @return= 1. "PASS", 2. "FAIL"
	 **************************************************************************/
	public String stClickOnTab(String targetXPath, String tabName,
			String verifyXPath, String flow) {
		String returnVal = null;
		int actVal = 100;
		try {
			Vector<String> xPath = new Vector<String>();
			Vector<String> errorMsg = new Vector<String>();
			STCommonLibrary comLib = new STCommonLibrary();

			xPath.add(targetXPath);
			errorMsg.add(tabName + " tab not present");
			comLib.stVerifyObjects(xPath, errorMsg, "STOP");
			browser.doubleClick(targetXPath);
			browser.mouseDown(targetXPath);
			browser.mouseUp(targetXPath);
			if (comLib.stWaitForElement(verifyXPath, 2000)) {
				actVal = 0;
			} else {
				actVal = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		hm.clear();
		hm.put("Tab to open", tabName);
		hm.put("Verification XPath", verifyXPath);
		returnVal = STFunctionLibrary.stRetValDes(0, actVal, "stClickOnTab", flow,
				hm);
		if (flow.contains("STOP")) {
			assertEquals("PASS", returnVal);
		}
		return returnVal;
	}
	// ********************[ stClickOnTab function End ]*************************
	
	// ********************[ stClickAndVerifyLink function Start ]*************************
	
	/***************************************************************************
	 * st_clickAndVerifyLink function is used for clicking on the target link
     * and verify the newly opened window's URL <br>
     * Expected values:<br>
     * -----------------------<br>
     * -1 - If any element is not present<br>
     * 0 - If Target link is opened in the new window successfully and URL of
     * the new window contains verification URL<br>
     * 1 - If Target link is opened in the new window successfully but URL of
     * the new window does not contain verification URL<br>
     * 2 - If Target link is opened in the new window but title of the window
     * does not contain verification title <br>
     * 3 - If Target link is not opened in the new window <br>
     * 
     * @param stDataID
     *            Data ID for fetching the data from XML file required for
     *            verifying new window
     * @param stCloseWindow
     *            For closing newly open window 0- for close 1- for not to close
     * @return object of STResult class that contain result of the verifications
	 * @param flow - ""
	 *            for continue or "STOP,TC_ID" for stop the execution of script
	 *            if function fails.
	 * @return PASS or FAIL
	 **************************************************************************/
	public String stClickAndVerifyLink(String targetXPath, int DataID,
			int expVal, int stCloseWindow, String flow) {
		int actVal = 1000;
		String returnVal = null;
		hm.clear();
		hm=STFunctionLibrary.stMakeData(DataID, "Breadcrum");
		String windowtitle = hm.get("WindowTitle");
		String url = hm.get("PageUrl");
		String stParent = null;
		try
        {
            /* Fetch the parent window title */
             stParent = browser.getTitle();

            Block:
            {
                /* Checking target */
                if (!browser.isElementPresent(targetXPath))
                {
                	actVal= -1;
                    break Block;
                }

                /* Clicking on stTarget on web site */
                browser.click(targetXPath);

                /* stNewWindowTitle used for containing the new window title */
                String stNewWindowTitle = null;

                int i = 0;
                int k = 0;
                while (k < stMaxSleep && stNewWindowTitle == null)
                {
                    /* Fetching window title */
                    String[] stWindowTitles = browser.getAllWindowTitles();
                    /* Getting the count of total windows */
                    i = stWindowTitles.length;

                    /* while loop for checking all window names */
                    while (i > 0)
                    {
                        /*
                         * Checking windows names with the name which we fetched
                         * from the data files
                         */
                        if (stWindowTitles[--i].toLowerCase().contains(
                        		windowtitle.toLowerCase()))
                        {
                            /* Saving new window name */
                            stNewWindowTitle = stWindowTitles[i];
                        }
                    }
                    try {
        				Thread.sleep(30000);
        			} catch (InterruptedException e) {
        				e.printStackTrace();
        			}
                    k++;
                }

                /* Fetching window title */
                String[] stWindowTitles = browser.getAllWindowTitles();
                i = stWindowTitles.length;
                
                /*
                 * If total number of titles are 1 then there would be no new
                 * windows
                 */
                if (i > 1)
                {
                    /* while loop for checking all window names */
                    while (i > 0)
                    {
                        /*
                         * Checking windows names with the name which we fetched
                         * from the data files
                         */
                        if (stWindowTitles[--i].toLowerCase().contains(
                        		windowtitle.toLowerCase()))
                        {
                            /* Saving new window name */
                            stNewWindowTitle = stWindowTitles[i];
                            
                        }

                    }
                    /*
                     * If new window has the title which is mentioned in the
                     * data file then stNewWindowTitle should not be null
                     */
                    if (stNewWindowTitle != null)
                    {
                        /* Selecting window */
                        browser.selectWindow(stNewWindowTitle);                                       

                        /* Fetching URL for verification */
                        String stURL = browser.getLocation().toLowerCase();

                        if (stURL.contains(url.toLowerCase()))
                        {
                           	actVal= -2;
                           	
                        } else
                        {                           
                        	actVal=1;
                        }
                                              
                                            
                        if (stCloseWindow == ST_CLOSE)
                        {
                            try
                            {
                                // Closing Newly open window
                                for (int j = 0; j < stWindowTitles.length; j++)
                                {
                                    if (stWindowTitles[j]
                                            .equalsIgnoreCase(stNewWindowTitle))
                                    {
                                        browser
                                                .selectWindow(stWindowTitles[j]);
                                        browser.close();
                                    }
                                }
                            } catch (Exception e)
                            {
                                // Selecting Parent window
                                browser.selectWindow(stParent);
                            }
                            // Selecting Parent window
                            browser.selectWindow(stParent);
                        }

                    }
                    /*
                     * If new window does not have the title which is mentioned
                     * in the data file then stNewWindowTitle should be null
                     */
                    else
                    {
                    	actVal=2;

                    	returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
                				"stClickAndVerifyLink", flow, hm);
                    }
                    /*
                     * Breaking the 'Block' because other steps are not required
                     * to test.
                     */
                    break Block;
                } else
                {
                	actVal= 0;
                	
                	returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
            				"stClickAndVerifyLink", flow, hm);
                    break Block;
                }
            }

        } catch (Exception e)
        {
            /* Selecting Parent window */
            browser.selectWindow(stParent);

            returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
    				"stClickAndVerifyLink", flow, hm);
    		if (flow.contains("STOP")) {
    			assertEquals("PASS", returnVal);
        }
		
		}
        
        
		return returnVal;
	}
	// ********************[ stClickAndVerifyLink function End ]*************************
	

}// End class

