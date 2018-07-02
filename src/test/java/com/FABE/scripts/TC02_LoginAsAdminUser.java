/*'########################################################
	' Objective            :     Verify Admin user's Login view.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.workflows.ApplicationUtil;

public class TC02_LoginAsAdminUser extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME,SheetName);
	}

	@Test
	public void testTC02_LoginAsAdminUser() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
					
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC02_LoginAsAdminUser")) {
					child = extent.startTest("FABORY_Login", strDesc);
					iterationReport(iLoop - 1, strDesc+ ":   Start");
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					
					
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					verifyAdminMenu();
					verifyWelcomeMenu();
					
					
					logoutWebShop();
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
