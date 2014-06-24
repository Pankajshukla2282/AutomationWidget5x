package com.sharethis.QAAutomation.commonlib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import junit.framework.TestCase;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**************************************************************************************
 * STFunctionLibrary class file used for generating results and fetching data from xml file.
 * 
 *  @see <br> This class contain following functions: 
 *  @see <li>  stMakeData()
 *  @see <li>  stEndResult()
 *  @see <li>  stRetValDes()
 *  @see <li>  stSaveEndResult()
 **************************************************************************************/

public class STFunctionLibrary extends TestCase{
	public static Document doc,docch, docie, docsf;	
	public static HashMap<String, String> hm = new HashMap<String, String>();

	//*******************************[ HashMap Function Start ]***********************************
	/*********************************************************************************************
	 * Function for fetching data from xml file
	 * @param dataid - Id to fetch the data from ZimbrData.xml file.
	 * @param shname - Data sheet name
	 * @return Hash Map
	 *********************************************************************************************/
	public static HashMap<String, String> stMakeData(int dataId,String shname) 
		{
			try 
			{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(".//Data//Data.XML"));
			// normalize text representation
			doc.getDocumentElement ().normalize ();
			NodeList sheetName= doc.getElementsByTagName(shname);
			Element sheetName1 = (Element)sheetName.item(0);
			NodeList Col=sheetName1.getElementsByTagName("DataID"+dataId);
			Element Col1 = (Element)Col.item(0);
			int totalcol=Col1.getChildNodes().getLength();
			
			for(int s=0; s<totalcol; s++)
			{
				Node firstColNode=Col1.getChildNodes().item(s);
				if(firstColNode.getNodeType() == Node.ELEMENT_NODE)
				{
					hm.put(firstColNode.getNodeName(), firstColNode.getTextContent());
				}
			}//end of for clause

			}//end of try
			catch (SAXParseException err)
			{
				System.out.println ("** Parsing error" + ", line "
				+ err.getLineNumber () + ", uri " + err.getSystemId ());
				
			}
			catch (SAXException e)
			{
				Exception x = e.getException ();
				((x == null) ? e : x).printStackTrace ();
			}
			catch (Throwable t)
			{
				t.printStackTrace ();
			}
			return hm;

		}//end of Function
	//********************************[ HashMap Function End ]************************************		
	
	//******************************[ stRetValDes Function Start ]*********************************		
	/*********************************************************************************************
	 * Function for create TestNG report.
	 * @param expVal - Expected value for result
	 * @param actVal - Actual Value for result
	 * @param functionName - Name of the caller function.
	 * @param flow - "" for continue or "STOP,TC_ID" for stop the execution of script if function fails.
	 * @param data - Hash Map used for Function xml.
	 * @return PASS or FAIL
	 *********************************************************************************************/
	public static String stRetValDes (int expVal,int actVal,String functionName,String flow,HashMap<String, String> data)
	{
		String status;
		status=null;
	    try {
	    	
	    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	            Document doc = docBuilder.parse (new File(".//Data//Function.XML"));
	
	            // normalize text representation
	            doc.getDocumentElement ().normalize ();
	            NodeList functionList=doc.getElementsByTagName(functionName);
	            Element functionNode = (Element)functionList.item(0);
	            NodeList DescriptionList=functionNode.getElementsByTagName("Description");
	            Element Description = (Element)DescriptionList.item(0);
	            String Des=Description.getTextContent();
	            if (expVal==actVal){
	            	status="PASS";
	            	NodeList retValList=functionNode.getElementsByTagName("Val"+expVal);        		            	
	            	Element retVal = (Element)retValList.item(0);
	            	String retValDes=retVal.getTextContent();	            	
	            	Reporter.log("<br /><div style =\"background-color:#EDEDA9;color:green;\"><strong>"+functionName+"-"+Des+" [PASS]</strong><br />"+retValDes+"<br /><strong>Data used:</strong><br />"+data.toString()+"</div>");
	            }
	            else
	            {
	            	NodeList expValList=functionNode.getElementsByTagName("Val"+expVal);
	            	Element expValNode = (Element)expValList.item(0);
	            	NodeList actVal_List=functionNode.getElementsByTagName("Val"+actVal);
	            	Element actVal_Node = (Element)actVal_List.item(0);
	            	String expValDes=expValNode.getTextContent();
	            	String actValDes=actVal_Node.getTextContent();
	            	Reporter.log("<br /><div style =\"background-color:#EDEDA9;color:red;\"><strong>"+functionName+"-"+Des+" [FAIL]<br />"+"Expected is "+"'"+expValDes+"'"+"but in actual "+"'"+actValDes+"'"+"<br />Data used:<br />"+data.toString()+"</strong></div>");		     
	            	status="FAIL";
	            	if (flow.contains("STOP"))
	            		{
	            			String tcID[]=flow.split(",");
	            			for(int i=1;i<tcID.length;i++)
	            			{
	            				stEndResult(tcID[i], "FAIL"); 
	            				Reporter.log("<div style =\" background-color:#FDFF10;color:red;\"><strong>"+tcID[i]+"--->FAIL</strong></div>");		            		    	
	            			}
	            			assertEquals(0,1);		            		
	            		}
	            }
	            data.clear();
	            
	        }catch (SAXParseException err) {
	        System.out.println ("** Parsing error" + ", line " 
	             + err.getLineNumber () + ", uri " + err.getSystemId ());
	        

	        }catch (SAXException e) {
	        Exception x = e.getException ();
	        ((x == null) ? e : x).printStackTrace ();

	        }catch (Throwable t) {
	        t.printStackTrace ();
	        }
		return status;

	 }//end of function

	//*******************************[ stRetValDes Function End ]**********************************
	
	//*********************************[ stEndResult Function ]************************************	
	/*********************************************************************************************
	 * Function for Create EndResult.xml file
	 * @param tcName - Test ID 
	 * @param status - Test Case Status
	 *********************************************************************************************/
	public static void stEndResult (String tcName,String status)
	{
		String skipCount="",failCount="",passCount="";	
		int skipNo=0;
		try 
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();				
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.parse(".//CustomizeResult//EndResult.XML");			
			
			NodeList feturesTab=doc.getDocumentElement().getChildNodes();     
            int noOfFeaturesff=feturesTab.getLength();
            for (int i=0;i<noOfFeaturesff;i++)
            {
            	Node featureNodeff= doc.getDocumentElement().getChildNodes().item(i);
            	if(featureNodeff.getNodeType() == Node.ELEMENT_NODE)
            	{
	            	NodeList tcListff=featureNodeff.getChildNodes().item(1).getChildNodes();
	            	int noOfTCff=tcListff.getLength();
	            	for (int j=0;j<noOfTCff;j++)
	            	{
	            		Node TC= tcListff.item(j);
		            	if(TC.getNodeType() == Node.ELEMENT_NODE)
		            	{
		            		String tcName1=TC.getNodeName();
		            		if (tcName1.contentEquals(tcName))
		            		{
		            			Element featureNode1 =(Element)featureNodeff;
		            			NodeList PassFailList =featureNode1.getElementsByTagName(status);
		            			Node PassFail= PassFailList.item(0);
		            			PassFail.appendChild(TC);
		            			passCount = String.valueOf(featureNode1.getElementsByTagName("PASS").item(0).getChildNodes().getLength());
		            			failCount = String.valueOf(featureNode1.getElementsByTagName("FAIL").item(0).getChildNodes().getLength());
		            			int Nc=featureNode1.getElementsByTagName("SKIP").item(0).getChildNodes().getLength();
		            			for (int k=0; k<Nc;k++)
		            			{
		            				if (featureNode1.getElementsByTagName("SKIP").item(0).getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE)
		            				{
		            					skipNo=skipNo+1;
		            				}
		            			}
		            			skipCount = String.valueOf(skipNo);
		            			featureNode1.setAttribute("PASS", passCount);
		            			featureNode1.setAttribute("FAIL", failCount);
		            			featureNode1.setAttribute("SKIP", skipCount);
		            			stSaveEndResult();
		            			return ;
		            		}
		            	}	
	            	}
            	}
            }  
            
		}
		
		catch (ParserConfigurationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//*********************************[ stEndResult Function ]************************************		

	//*******************************[ stSaveEndResult Function ]**********************************		
	/*********************************************************************************************
	 * Function for saving EndResult.xml file
	 *********************************************************************************************/
	public static void stSaveEndResult()
	{
		try
	    {   
			FileOutputStream fileOutPut=new FileOutputStream(".//CustomizeResult//EndResult.XML");
			
			com.sun.org.apache.xml.internal.serialize.OutputFormat format = new com.sun.org.apache.xml.internal.serialize.OutputFormat(doc);
			
	    	com.sun.org.apache.xml.internal.serialize.XMLSerializer output = new com.sun.org.apache.xml.internal.serialize.XMLSerializer(fileOutPut, format);
	    		    	
	    	output.serialize(doc);
	        
	        fileOutPut.close();
	        
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	}	
	//******************************[ zSaveEndResult Function End ]*******************************	
	
}//End Class
	
