package com.sharethis.QAAutomation.commonlib;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import java.io.*;
import junit.framework.TestCase;

public class ServerStartup extends TestCase {
	public String Proxy1,Port1;
	@BeforeClass
	@Parameters ({"Proxy","Port"})
    public void FatchParameters(String Proxy,String Port){
		Proxy1=Proxy;
		Port1=Port;    	
    }
	@Test
	public void serverstart() throws IOException{	

		File dir = new File(".\\Log\\");
		FilenameFilter filter = new FilenameFilter() {
		     public boolean accept(File dir, String name) {
		            return name.endsWith(".lck");
		        }
		   }; 
		   
		String[] children = dir.list(filter);
		if (children.length == 0) {
			Runtime.getRuntime().exec("cmd /c java -Dhttp.proxyHost="+Proxy1+" -Dhttp.proxyPort="+Port1+" -jar .\\jarFiles\\selenium-server.jar -interactive");//	-log .\\Log\\ServerLog.txt");
			System.out.println(" " );
			System.out.println("********************************");
			System.out.println("*  Started the Selenium Server *");
			System.out.println("********************************");
			System.out.println(" " );
	
		  } else {
			    System.out.println(" " );
			    System.out.println(" " );
			  	System.out.println("*****************************");
				System.out.println("* Server is already running *");
				System.out.println("*****************************");
				System.out.println(" " );
				System.out.println(" " );

		  }
	}
		@AfterSuite
		public void SuiteEnd()throws IOException{
			Runtime.getRuntime().exec("taskkill /F -IM java.exe");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" " );
			System.out.println(" " );
			System.out.println("****************************");
			System.out.println("* Test Execution Completed *");
			System.out.println("****************************");
			System.out.println(" " );
			System.out.println(" " );
		    
		}
				
	}
