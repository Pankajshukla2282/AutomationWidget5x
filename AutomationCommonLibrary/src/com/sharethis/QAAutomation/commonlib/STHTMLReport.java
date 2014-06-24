package com.sharethis.QAAutomation.commonlib;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/****************************************************************************
 * STHTMLReport class used for create HTML report from EndResult.XML file
 *
 *  @see <br> This class contain following functions
 *  @see <li>  stFetchData() 
 *  @see <li>  stHTMLReport()
 ****************************************************************************/

public class STHTMLReport
{
	String featureName;
	int passCount,failCount,skipCount;
	StringBuffer listOfPassTestCase=new StringBuffer("");
	StringBuffer listOfFailTestCase=new StringBuffer("");
	StringBuffer listOfSkipTestCase=new StringBuffer("");
	public static Document doc;	
	
	//*******************************[ stFetchData Function Start ]***********************************
	/************************************************************************************************
	 * Function used for Fetching data from EndResult.xml file.
	 * @return Vector
	 ************************************************************************************************/
	public static Vector<STHTMLReport> stFetchData (){	
		Vector<STHTMLReport> features=new Vector<STHTMLReport>();	
        

			try 
			{				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();			
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();			
				doc = docBuilder.parse(".//CustomizeResult//EndResult.XML");
				//doc = docBuilder.parse(".//CustomizeResult//CH-EndResult.XML");	
	            NodeList FeturesTab=doc.getDocumentElement().getChildNodes();     
	            
	            
	            int noOfFeatures=FeturesTab.getLength();
	            for (int i=0;i<noOfFeatures;i++)
	            {
	            	Node FeatureNode= doc.getDocumentElement().getChildNodes().item(i);
	            	if(FeatureNode.getNodeType() == Node.ELEMENT_NODE)
	            	{
	            		STHTMLReport report=new STHTMLReport();
	            		report.featureName=FeatureNode.getNodeName();
	            		
	            		Element Feature=(Element) FeatureNode;
	            		report.passCount=Integer.parseInt(Feature.getAttribute("PASS"));
	            		report.failCount=Integer.parseInt(Feature.getAttribute("FAIL"));
	            		report.skipCount=Integer.parseInt(Feature.getAttribute("SKIP"));
	            		
	            		Element FeatureNode1 =(Element)FeatureNode;
		            	NodeList passList =FeatureNode1.getElementsByTagName("PASS").item(0).getChildNodes();
		            	int noOfTC=passList.getLength();
		            	int k=0;
		            	for (int j=0;j<noOfTC;j++)
		            	{
		            		Node TC= passList.item(j);
			            	if(TC.getNodeType() == Node.ELEMENT_NODE)
			            	{
			            		report.listOfPassTestCase.append(","+TC.getNodeName());		            		
			            		k++;
			            	}
			            }
		            	NodeList failList =FeatureNode1.getElementsByTagName("FAIL").item(0).getChildNodes();
		            	noOfTC=failList.getLength();
		            	k=0;
		            	for (int j=0;j<noOfTC;j++)
		            	{
		            		Node TC= failList.item(j);
			            	if(TC.getNodeType() == Node.ELEMENT_NODE)
			            	{
			            		report.listOfFailTestCase.append(","+TC.getNodeName());		            		
			            		k++;
			            	}
			            }	
		            	NodeList skipList =FeatureNode1.getElementsByTagName("SKIP").item(0).getChildNodes();
		            	noOfTC=skipList.getLength();
		            	k=0;
		            	for (int j=0;j<noOfTC;j++)
		            	{
		            		Node TC= skipList.item(j);
			            	if(TC.getNodeType() == Node.ELEMENT_NODE)
			            	{
			            		report.listOfSkipTestCase.append(","+TC.getNodeName());		            		
			            		k++;
			            	}
			            }
		            	features.add(report);
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
			return features;
	}
	//********************************[ stFetchData Function End ]************************************	
	
	//*******************************[ STHTMLReport Function Start ]***********************************	
	/*************************************************************************************************
	 * Function used for creation of HTML reports.
	 *************************************************************************************************/
	public void stHTMLReport(String productName) 
	{		
		//Fetching System Date and Time for Automation log
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        
		try {
			int totalPass=0,totalFail=0,totalSkip=0;			
			Vector<STHTMLReport> features=new Vector<STHTMLReport>();
			features=stFetchData ();
			BufferedWriter writer=new BufferedWriter(new FileWriter(".//CustomizeResult//ReportHeader.html"));
			writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">				  ");
			writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" >                                                                                              ");
			writer.write("<head>                                                                                                                                      ");
//			writer.write("    <title>ShareThis Automation Testing Report</title>                                                                                      ");
			writer.write("    <title>ShareThis Automation Testing Report for "  + productName + "</title>                                                             ");
			writer.write("</head> 	                                                                                                                                  ");
			
			
            writer.write("</head>                                                                                                                                     ");
            writer.write("<body>                                                                                                                                      ");
            writer.write("    <div style=\"text-align: center;font-size: 14pt; color: #3366ff;font-weight: bold \">                                                   ");
            writer.write("     <h2> ShareThis Automation Testing Report for "+productName+ "</h2></div>                                                                                            ");
            writer.write("    <div style=\"text-align: center;font-size: 14pt; color: #000000 \">																	  ");
            writer.write("<h4> Automation Run log : " + dateNow + "</h4></div>                                                                                        ");			
			writer.write("<body>                                                                                                                                      ");
			writer.write("    <div style=\"text-align: center;font-size: 16pt; color: #3366ff;font-weight: bold \">                                                   ");
			writer.write("     ShareThis Automation Testing Report</div><br>                                                                                          ");
			writer.write("    <table align=center style=\"border:gray thin double;\" border=\"1\" bordercolor=\"gray\" cellpadding=\"5\">                             ");
			writer.write("        <tr style=\"font-weight: bold; text-transform: capitalize;text-align: center; border:gray thin double\" bgcolor=\"#ffcccc\">        ");
			writer.write("            <td nowrap>Component</td>                                                                                                       ");
			writer.write("            <td nowrap style=\"color: #009900\">Pass</td>                                                                                   ");
			writer.write("            <td nowrap style=\"color: #ff0033\">Fail</td>                                                                                   ");
			writer.write("            <td nowrap style=\"color: #0000ff\">Skip</td>                                                                                   ");
			writer.write("            <td nowrap>Pass Percentage</td>                                                                                                ");
			writer.write("        </tr>                                                                                                                               ");
			NumberFormat form = NumberFormat.getInstance();
			form.setMaximumFractionDigits(2);
			for(int index=0;index<features.size();index++)
			{
				totalPass=totalPass+features.get(index).passCount;
				totalFail=totalFail+features.get(index).failCount;
				totalSkip=totalSkip+features.get(index).skipCount;
				int total=(features.get(index).passCount+features.get(index).failCount+features.get(index).skipCount);
				double percentage=0;
				if(total>0)
					percentage=(((features.get(index).passCount)*100.00)/total);
				writer.write("        <tr style=\"text-transform: capitalize;text-align: center\">                                                                        ");
				writer.write("            <td nowrap>                                                                                                                     ");
				writer.write(			  features.get(index).featureName																								   );
				writer.write("            </td>                                                                                                                           ");
				writer.write("            <td nowrap style=\"color: #009900\">                                                                                            ");
				writer.write(				Integer.toString(features.get(index).passCount));
				writer.write("            </td>                                                                                                                           ");
				writer.write("            <td nowrap style=\"color: #ff0033\">                                                                                            ");
				writer.write(             	Integer.toString(features.get(index).failCount));
				writer.write("            </td>                                                                                                                           ");
				writer.write("            <td nowrap style=\"color: #0000ff\">                                                                                            ");
				writer.write(				Integer.toString(features.get(index).skipCount));
				writer.write("            </td>                                                                                                                           ");
				writer.write("            <td nowrap>                                                                                                                     ");
				writer.write(				form.format(percentage)+"%");
				writer.write("            </td>                                                                                                                           ");
				writer.write("        </tr>                                                                                                                               ");
			}
			int total=(totalPass+totalFail+totalSkip);
			double percentage=0;
			if(total>0)
				percentage=((totalPass*100.00)/total);
			writer.write("        <tr style=\"text-transform: capitalize;text-align: center\">                                                                        ");
			writer.write("            <td nowrap><strong>                                                                                                                     ");
			writer.write(			  "Total"																								   );
			writer.write("            </strong></td>                                                                                                                           ");
			writer.write("            <td nowrap style=\"color: #009900\">                                                                                            ");
			writer.write("             <a href=\"PassTestCases.html\" target=\"footer\" style=\"color: #009900\">"+Integer.toString(totalPass)+"</a>");
			writer.write("            </td>                                                                                                                           ");
			writer.write("            <td nowrap style=\"color: #ff0033\">                                                                                            ");
			writer.write("             <a href=\"FailTestCases.html\" target=\"footer\" style=\"color: #ff0033\">"+Integer.toString(totalFail)+"</a>");
			writer.write("            </td>                                                                                                                           ");
			writer.write("            <td nowrap style=\"color: #0000ff\">                                                                                            ");
			writer.write("             <a href=\"SkipTestCases.html\" target=\"footer\" style=\"color: #0000ff\">"+Integer.toString(totalSkip)+"</a>");
			writer.write("            </td>                                                                                                                           ");
			writer.write("            <td nowrap>                                                                                                                     ");
			writer.write(				form.format(percentage)+"%");
			writer.write("            </td>                                                                                                                           ");
			writer.write("        </tr>																																  ");
			writer.write("    </table>                                                                                                                                ");
			writer.write("</body>                                                                                                                                     ");
			writer.write("</html>                                                                                                                                     ");
			writer.close();
			
			/******************************************************** Pass HTML PAGE ****************************************************************/
			
			BufferedWriter passHTMLWriter=new BufferedWriter(new FileWriter(".//CustomizeResult//PassTestCases.html"));
			passHTMLWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">                    ");
			passHTMLWriter.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" >                                                                                                   ");
			passHTMLWriter.write("<head>                                                                                                                                           ");
			passHTMLWriter.write("    <title>Pass TestCases</title>                                                                                                                ");
			passHTMLWriter.write("</head>                                                                                                                                          ");
			passHTMLWriter.write("<body>                                                                                                                                           ");
			passHTMLWriter.write("    <div style=\"text-align: center;font-size: 16pt; color: #3366ff;font-weight: bold \">                                                        ");
			passHTMLWriter.write("     Pass Test Cases</div><br>                                                                                                                   ");
			passHTMLWriter.write("    <table align=center>                                                                                                                         ");
			passHTMLWriter.write("        <tr>                                                                                                                                     ");
			for(int index=0;index<features.size();index++)
			{				
				passHTMLWriter.write("            <td nowrap  valign=top>                                                                                                                          ");
				passHTMLWriter.write("                 <table align=center style=\"border:gray thin double;\" border=\"1\" bordercolor=\"gray\" cellpadding=\"5\">                     ");
				passHTMLWriter.write("                    <tr style=\"font-weight: bold; text-transform: capitalize;text-align: center; border:gray thin double\" bgcolor=\"#ffcccc\"> ");
				passHTMLWriter.write("                        <td nowrap>"+features.get(index).featureName+"</td>                                                                                                ");
				passHTMLWriter.write("                    </tr>                                                                                                                        ");
				
				String []passList=features.get(index).listOfPassTestCase.toString().split(",");
				Arrays.sort(passList);
				for(int i=0;i<passList.length;i++)
				{
					passHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					passHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					passHTMLWriter.write(                         passList[i]  );
					passHTMLWriter.write("                        </td>                                                                                                                    ");
					passHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				if(passList.length==1)
				{
					passHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					passHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					passHTMLWriter.write(                         	"None"  );
					passHTMLWriter.write("                        </td>                                                                                                                    ");
					passHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				passHTMLWriter.write("                </table>                                                                                                                         ");
				passHTMLWriter.write("            </td>                                                                                                                                ");
			}
			passHTMLWriter.write("        </tr>                                                                                                                                    ");
			passHTMLWriter.write("    </table>                                                                                                                                     ");
			passHTMLWriter.write("</body>                                                                                                                                          ");
			passHTMLWriter.write("</html>                                                                                                                                          ");
			passHTMLWriter.write("                                                                                                                                                 ");
			passHTMLWriter.close();
			
			/******************************************************** FAIL HTML PAGE ****************************************************************/
			
			BufferedWriter failHTMLWriter=new BufferedWriter(new FileWriter(".//CustomizeResult//failTestCases.html"));
			failHTMLWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">                    ");
			failHTMLWriter.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" >                                                                                                   ");
			failHTMLWriter.write("<head>                                                                                                                                           ");
			failHTMLWriter.write("    <title>Fail TestCases</title>                                                                                                                ");
			failHTMLWriter.write("</head>                                                                                                                                          ");
			failHTMLWriter.write("<body>                                                                                                                                           ");
			failHTMLWriter.write("    <div style=\"text-align: center;font-size: 16pt; color: #3366ff;font-weight: bold \">                                                        ");
			failHTMLWriter.write("     Fail Test Cases</div><br>                                                                                                                   ");
			failHTMLWriter.write("    <table align=center>                                                                                                                         ");
			failHTMLWriter.write("        <tr>                                                                                                                                     ");
			for(int index=0;index<features.size();index++)
			{
				failHTMLWriter.write("            <td nowrap valign=top>                                                                                                                          ");
				failHTMLWriter.write("                 <table align=center style=\"border:gray thin double;\" border=\"1\" bordercolor=\"gray\" cellpadding=\"5\">                     ");
				failHTMLWriter.write("                    <tr style=\"font-weight: bold; text-transform: capitalize;text-align: center; border:gray thin double\" bgcolor=\"#ffcccc\"> ");
				failHTMLWriter.write("                        <td nowrap>"+features.get(index).featureName+"</td>                                                                                                ");
				failHTMLWriter.write("                    </tr>                                                                                                                        ");
				String []failList=features.get(index).listOfFailTestCase.toString().split(",");
				Arrays.sort(failList);
				for(int i=0;i<failList.length;i++)
				{
					failHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					failHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					failHTMLWriter.write(                        failList[i]);
					failHTMLWriter.write("                        </td>                                                                                                                    ");
					failHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				if(failList.length==1)
				{
					failHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					failHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					failHTMLWriter.write(                        	"None");
					failHTMLWriter.write("                        </td>                                                                                                                    ");
					failHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				failHTMLWriter.write("                </table>                                                                                                                         ");
				failHTMLWriter.write("            </td>                                                                                                                                ");
			}
			failHTMLWriter.write("        </tr>                                                                                                                                    ");
			failHTMLWriter.write("    </table>                                                                                                                                     ");
			failHTMLWriter.write("</body>                                                                                                                                          ");
			failHTMLWriter.write("</html>                                                                                                                                          ");
			failHTMLWriter.write("                                                                                                                                                 ");
			failHTMLWriter.close();
			
			/******************************************************** SKIP HTML PAGE ****************************************************************/
			
			BufferedWriter skipHTMLWriter=new BufferedWriter(new FileWriter(".//CustomizeResult//SkipTestCases.html"));
			skipHTMLWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">                    ");
			skipHTMLWriter.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" >                                                                                                   ");
			skipHTMLWriter.write("<head>                                                                                                                                           ");
			skipHTMLWriter.write("    <title>Skip TestCases</title>                                                                                                                ");
			skipHTMLWriter.write("</head>                                                                                                                                          ");
			skipHTMLWriter.write("<body>                                                                                                                                           ");
			skipHTMLWriter.write("    <div style=\"text-align: center;font-size: 16pt; color: #3366ff;font-weight: bold \">                                                        ");
			skipHTMLWriter.write("     Skip Test Cases</div><br>                                                                                                                   ");
			skipHTMLWriter.write("    <table align=center>                                                                                                                         ");
			skipHTMLWriter.write("        <tr>                                                                                                                                     ");
			for(int index=0;index<features.size();index++)
			{
				skipHTMLWriter.write("            <td nowrap valign=top>                                                                                                                          ");
				skipHTMLWriter.write("                 <table align=center style=\"border:gray thin double;\" border=\"1\" bordercolor=\"gray\" cellpadding=\"5\">                     ");
				skipHTMLWriter.write("                    <tr style=\"font-weight: bold; text-transform: capitalize;text-align: center; border:gray thin double\" bgcolor=\"#ffcccc\"> ");
				skipHTMLWriter.write("                        <td nowrap>"+features.get(index).featureName+"</td>                                                                                                ");
				skipHTMLWriter.write("                    </tr>                                                                                                                        ");
				String []skipList=features.get(index).listOfSkipTestCase.toString().split(",");
				Arrays.sort(skipList);
				for(int i=0;i<skipList.length;i++)
				{
					skipHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					skipHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					skipHTMLWriter.write(                        skipList[i]);
					skipHTMLWriter.write("                        </td>                                                                                                                    ");
					skipHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				if(skipList.length==1)
				{
					skipHTMLWriter.write("                    <tr style=\"text-transform: capitalize;text-align: center\">                                                                 ");
					skipHTMLWriter.write("                        <td nowrap>                                                                                                              ");
					skipHTMLWriter.write(                        	"None");
					skipHTMLWriter.write("                        </td>                                                                                                                    ");
					skipHTMLWriter.write("                    </tr>                                                                                                                        ");
				}
				skipHTMLWriter.write("                </table>                                                                                                                         ");
				skipHTMLWriter.write("            </td>                                                                                                                                ");
			}
			skipHTMLWriter.write("        </tr>                                                                                                                                    ");
			skipHTMLWriter.write("    </table>                                                                                                                                     ");
			skipHTMLWriter.write("</body>                                                                                                                                          ");
			skipHTMLWriter.write("</html>                                                                                                                                          ");
			skipHTMLWriter.write("                                                                                                                                                 ");
			skipHTMLWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//********************************[ STHTMLReport Function End ]************************************	
}
