/*'########################################################
	' Objective            :     Verify Admin user's Login view.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
*/	

package com.FABE.scripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.FABE.workflows.ApplicationUtil;

public class TC01_LoginAsAdminUser_Uat_env extends ApplicationUtil {
//	Screen screen =new Screen();
//	Pattern img=new Pattern();
//	Screen scn=new Screen();
//	Pattern img1=new Pattern("E:\\Automation\\Fabory_WebShop\\Logos\\Chrome_Auth_window.JPG");
//	Pattern img2=new Pattern("E:\\Automation\\Fabory_WebShop\\Logos\\Auth_Username.JPG");
//	Pattern img3=new Pattern("E:\\Automation\\Fabory_WebShop\\Logos\\Auth_Password");
//	Pattern img4=new Pattern("E:\\Automation\\Fabory_WebShop\\Logos\\Auth_signin");

	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, "Smoke");
	}

	@Test
	public void testTC01_LoginAsAdminUser_Uat_env() throws Throwable {
//		 Screen s = new Screen();
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String SheetName = "Smoke";
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
					
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC01_LoginAsAdminUser_Uat_env")) {
					child = extent.startTest("FABORY_Login", strDesc);
					iterationReport(iLoop - 1, strDesc+ ":   Start");
					String Username = Excelobject.getCellData(SheetName, "CONFIRM EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "FIRST NAME", iLoop).toString();
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String PWD = Excelobject.getCellData(SheetName, "PASSWORD", iLoop);
	              
					
					String URL = configProps.getProperty("UAT_URL");
					driver.get(URL);
					executionDelay(5000);
					
					//driver.get("http://developer:d3v3l0p3r2018@uat.fabory.com/en");
					executionDelay(5000);
				
//				        String webPage = "http://www.domain.com/";
//				        String Uname = "developer";
//				        String password = "d3v3l0p3r2018";
//
//				        String authString = Uname + ":" + password;
//				        System.out.println("auth string: " + authString);
//				        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
//				        String authStringEnc = new String(authEncBytes);
//				        System.out.println("Base64 encoded auth string: " + authStringEnc);
//
//				        URL url = new URL(webPage);
//				        URLConnection urlConnection = url.openConnection();
//				        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
//				        InputStream is = urlConnection.getInputStream();
//				        InputStreamReader isr = new InputStreamReader(is);
				        
					
//					
//					//driver.navigate().to(URL);
//					Runtime.getRuntime().exec("E:\\Automation\\Fabory_WebShop\\Resources\\HandleAuthenticationWindow_FF.exe");
//					executionDelay(5000);
					//sikuli script
//
//					   //scn.wait(img1, 10);
//					   scn.click(img2);
//					   scn.type(img2,"fabory");
//					   scn.type(img3,"f4b0ry#uat2018!");
//					   scn.click(img4);
//					   
					//IE autoIt script
//					Runtime.getRuntime().exec("E:\\Automation\\Fabory_WebShop\\Resources\\HandleAuthenticationWindow_IE.exe");
//					executionDelay(5000);
					
				
					
//					Credentials credentials = new UserAndPassword(Username, Password);
//                    driver.switchTo().alert().setCredentials(credentials);
//                    driver.switchTo().alert().accept();
//					UserAndPassword UP = new UserAndPassword(Username, Password);
//					driver.switchTo().alert().authenticateUsing(UP);		   
					

					
//					
                    String navURL=driver.getCurrentUrl();
            		if (navURL.endsWith(URL))
            		SuccessReport("Launced URL", ""+URL);
            		else 
            		failureReport("Launced URL ",""+navURL);
            		driver.close();
//            		
//            		
//            		
//					applicationLogin(EmailAddress, PWD);
//					verifyAdminMenu();
//					verifyWelcomeMenu();
					
					
//					logoutWebShop();
				    parent.appendChild(child);
					// This will mark end of the one row in data sheet
					iterationReport(iLoop - 1, "Completed");
                    
				}

			} catch (Exception e) {
				catchBlock(e);
			}

		}
	}

	

	

}
