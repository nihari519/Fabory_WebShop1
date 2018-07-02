/*'########################################################
	' Objective            :     Verify New User Registeration for Existing Company.
	' Test Case            :     TC8_SignUpExistingCompany
	' Author               :     Niharika
	' Date Created         :     31-May-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.ConfirmationOR;
import com.FABE.objrepo.FaboryOR.SignUpOR;
import com.FABE.workflows.ApplicationUtil;

public class TC08_SignUpExistingCompany extends ApplicationUtil {
	public static String BackofficeUser=configProps.getProperty("BO_UserName");
	public static String BackofficePassowrd=configProps.getProperty("BO_PassWord");
	String SheetName = configProps.getProperty("InputWorkSheet");
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC08_SignUpExistingCompany() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC08_SignUpExistingCompany")) {
				child = extent.startTest("FABORY_Registration_New", strDesc);
				iterationReport(iLoop - 1, strDesc+":      Start");
				String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
				String ConfirmEmail = Excelobject.getCellData(SheetName, "CONFIRM EMAIL ADDRESS", iLoop);
				String FirstName = Excelobject.getCellData(SheetName, "FIRST NAME", iLoop);
				String LastName = Excelobject.getCellData(SheetName, "LAST NAME", iLoop);
				String CompanyID = "1130897";				
				String Message = Excelobject.getCellData(SheetName, "MESSAGE", iLoop).toString();
				
			
				
					navigateToWebsite();
					signUpExisting(EmailAddress,ConfirmEmail,FirstName, LastName, CompanyID ,Message);
					executionDelay(1000);
					
					//verify registration notification
					if(isWebElementPresent(SignUpOR.AlertMsg, "Registration Confirmation alert"))
					{		
                   	String notification = driver.findElement(ConfirmationOR.RegistrationConfirmationMessage).getText().toString();
					System.out.println("list of items: "+notification);						
					if(notification.contains(Message))
						SuccessReport("Registration", "is Successful");
					else
						failureReport("Registration Failed", "Notification displayed:"+notification);
					}
					else
					failureReport("User Registration", "Failed");
			        //delete the user from backoffice
					deleteUserBackoffice(EmailAddress);
					
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
