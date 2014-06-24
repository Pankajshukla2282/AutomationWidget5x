package com.sharethis.QAAutomation.commonlib;

import static com.sharethis.QAAutomation.commonlib.STFunctionLibrary.hm;
import static com.sharethis.QAAutomation.commonlib.STLaunchBrowser.browser;
import static com.sharethis.QAAutomation.XPaths.CommonXPath.*;

public class STDoneScreen {
	
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
    
    
    public String stOpenDoneScreen (String targetXPath, int expVal, String flow)
    {
		int actVal = 1000;
		String returnVal = null;
		
		Block:
		{
        if (!browser.isElementPresent(targetXPath))
        {
        	actVal= -1;
            break Block;
        }
        
        /* Clicking on stTarget on web site */
        browser.click(targetXPath);

        try {
        	System.out.println("sleeping");
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

            /* Fetching window title */
            String[] stWindowTitles = browser.getAllWindowTitles();
            
            /* Getting the count of total windows */
            int i = stWindowTitles.length;
            System.out.println("Total no windows ="+ i);
            
//            if(i>0)
//            {
//            browser.selectWindow(stWindowTitles[i+1]);
//            browser.close();
            browser.selectWindow(null);
            
            if(browser.isElementPresent(OAUTH_WIDGET_DONE_SCREEN))
            {
            actVal=1;
            }
            else
            {
            actVal=0;
            }
//            }else
//            {
//            	actVal=2;
//            }
		}
        
    	returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
				"stOpenDoneScreen", flow, hm);
    	return returnVal;
    }
    
    public String stVerifySharedToText (String service, int expVal, String flow)
    {
		int actVal = 1000;
		String returnVal = null;
		STCommonLibrary comLib = new STCommonLibrary();
		String fbShareMessage="Shared to: Facebook";
		String twitterShareMessage="Shared to: Twitter";
		String linkedinShareMessage="Shared to: LinkedIn";
		String allShareMessage[]={"Shared To","LinkedIn","Facebook"};
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comLib.stWaitForElement(OAUTH_WIDGET_SHARED_TO_TEXT, 10);
		
		String actualSharedToText=browser.getText(OAUTH_WIDGET_SHARED_TO_TEXT);
		System.out.println("actualSharedToText : " +actualSharedToText);
		
		if(service.equalsIgnoreCase("FB"))
		{
			if(actualSharedToText.equalsIgnoreCase(fbShareMessage))
			{
				actVal=1;
			}
			else
			{
				actVal=-1;
			}
			
		}
		
		if(service.equalsIgnoreCase("Twitter"))
		{
			if(actualSharedToText.equalsIgnoreCase(twitterShareMessage))
			{
			actVal=2;
			
			}else
			{
				actVal=-2;
			}
    }
    	
		if(service.equalsIgnoreCase("LinkedIn"))
		{
			if(actualSharedToText.equalsIgnoreCase(linkedinShareMessage))
			{
			actVal=3;
			
			}else
			{
				actVal=-3;
			}
		}
		
		if(service.equalsIgnoreCase("All"))
		{
			for(int count=0;count<allShareMessage.length;count++)
			{
			if(actualSharedToText.contains(allShareMessage[count]))
			{
				actVal=4;
				System.out.println("allShareMessage[count] :" +allShareMessage[count]);
			
			}else
			{
			actVal=-4;
			}
			}
		}
    	
       	returnVal = STFunctionLibrary.stRetValDes(expVal, actVal,
				"stVerifySharedToText", flow, hm);
    	return returnVal;
    }

}
