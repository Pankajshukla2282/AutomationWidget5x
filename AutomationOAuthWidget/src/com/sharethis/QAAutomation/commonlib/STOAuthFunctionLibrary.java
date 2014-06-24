package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;


public class STOAuthFunctionLibrary {
	
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
	public String stClickAndVerifyOAuthLink(String targetXPath, int DataID,
			int expVal, int stCloseWindow, String flow) {
		int actVal = 1000;
		String returnVal = null;
		hm.clear();
		hm=STFunctionLibrary.stMakeData(DataID, "Breadcrum");
		String windowtitle = hm.get("WindowTitle");
		System.out.println("windowtitle from data sheet" +windowtitle);
		
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
                while (stNewWindowTitle == null && k <stMaxSleep)
                {
                	System.out.println("stNewWindowTitle" +stNewWindowTitle);
                    /* Fetching window title */
                    String[] stWindowTitles = browser.getAllWindowTitles();
                    /* Getting the count of total windows */
                    i = stWindowTitles.length;
                    
                    System.out.println("Number of Windows : " +i);

                    /* while loop for checking all window names */
                    while (i > 0)
                    {
                        /*
                         * Checking windows names with the name which we fetched
                         * from the data files
                         */
                    	
                    	System.out.println(" Window i " +stWindowTitles[--i]);
                    	System.out.println("windowtitle : " +windowtitle);
                    	i=i-1;
                        if (stWindowTitles[i].toLowerCase().contains(
                        		windowtitle.toLowerCase()))
                        {
                            /* Saving new window name */
                            stNewWindowTitle = stWindowTitles[i];
                            actVal=4;
                            break;
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
                  /*  while (i > 0)
                    {
                        
                         * Checking windows names with the name which we fetched
                         * from the data files
                         
                        if (stWindowTitles[--i].toLowerCase().contains(
                        		windowtitle.toLowerCase()))
                        {
                             Saving new window name 
                            stNewWindowTitle = stWindowTitles[i];
                            
                        }

                    }*/
                    /*
                     * If new window has the title which is mentioned in the
                     * data file then stNewWindowTitle should not be null
                     */
                    if (stNewWindowTitle != null)
                    {
     /*                    Selecting window 
                        browser.selectWindow(stNewWindowTitle);                                       

                         Fetching URL for verification 
                        String stURL = browser.getLocation().toLowerCase();

                        if (stURL.contains(url.toLowerCase()))
                        {
                           	actVal= -2;
                           	
                        } else
                        {                           
                        	actVal=1;
                        	
                        }
                         Logging the status of URL verification 
                        returnVal = STFunctionLibrary.stRetValDes(-2, actVal,
                				"stClickAndVerifyLink", flow, hm);*/
                                             
                                          
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

            System.out.println("actVal : "+actVal);
            System.out.println("expVal : " +expVal);
            returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
    				"stClickAndVerifyLink", flow, hm);
    		if (flow.contains("STOP")) {
    			assertEquals("PASS", returnVal);
        }
		
		}
        
        
		return returnVal;
	}
}
