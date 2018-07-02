/*'########################################################
	' Objective            :     Verify Admin user is able to create a new user and delte the user from backoffice.
	' Test Case            :     TC4_VerifyUserCreationByAdminUser
	' Author               :     Niharika
	' Date Created         :     04-May-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.ConfirmationOR;
import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.workflows.ApplicationUtil;

public class TC04_VerifyUserCreationByAdminUser extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC04_VerifyUserCreationByAdminUser() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);			
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC04_VerifyUserCreationByAdminUser")) {
				child = extent.startTest("FABORY_Products Menu", strDesc);
				iterationReport(iLoop - 1, strDesc +":         Start");
				String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
				String UserEmailAddress = Excelobject.getCellData(SheetName, "CONFIRM EMAIL ADDRESS", iLoop);
				String FName = Excelobject.getCellData(SheetName, "FIRST NAME", iLoop);
				String LName = Excelobject.getCellData(SheetName, "LAST NAME", iLoop);
				String DeptName = Excelobject.getCellData(SheetName, "COMPANY NAME", iLoop);
				String Position = Excelobject.getCellData(SheetName, "VAT", iLoop);
				String Phone = "6767676767";
				String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();				
			
				    
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					navigateToUsersPage();
					createNewUser(FName,LName,UserEmailAddress,Phone,DeptName, Position);
					
					//delete the user created above
					logoutWebShop();
					deleteUserBackoffice(UserEmailAddress);					
					logoutBackOffice();
				    parent.appendChild(child);
					// This will mark end of the one row in data sheet
					iterationReport(iLoop - 1, "Completed");
                    
				}

			} catch (Exception e) {
				catchBlock(e);
			}

		}
	}
//Add new user
   private void createNewUser(String FName,String LName,String UserEmailAddress,String Phone,String DeptName,String Position) 
   {
	try {
		JSClick(LoginOR.UserAddLink,"User Add link");
		if(driver.findElements(LoginOR.NewUserHeader).size()!=0)
		SuccessReport("New User Page", "Navigation to New User page is successful");
		else
		failureReport("New User Page", "Navigation to New User page is unsuccessful");
		
		//Fill in user details
		Select titlecode=new Select(driver.findElement(By.name("titleCode")));
		titlecode.selectByValue("ms");
		jsType(LoginOR.NewUserPageFirstName ,FName,"First Name");
		jsType(LoginOR.NewUserPageLastName ,LName,"Last Name");
		jsType(LoginOR.NewUserPageEmail ,UserEmailAddress,"Email");
		jsType(LoginOR.NewUserPagePhone ,Phone,"Phone");
		jsType(LoginOR.NewUserDepartment ,DeptName,"Department");
		jsType(LoginOR.NewUserPosition ,Position,"Position");
		JSClick(LoginOR.NewUserSaveButton,"Save button");
		
		String msg=driver.findElement(ConfirmationOR.UserConfirmationMessage).getText();
		if(msg.contains("Customer successfully created"))
		SuccessReport("New User", "User creation is successful");
		else
		failureReport("New User", "User creation is unsuccessful");	
		
		} catch (Throwable e) {
		failureReport("Exception", "@createNewUser() :"+e);
		}
   }

	private void navigateToUsersPage() throws Throwable {
		JSClick(LoginOR.AdminDropdownIcon,"Admin dropdown menu");
		JSClick(LoginOR.UsersMenu, "Users Menu");
		executionDelay(1000);
		String hdr=driver.findElement(LoginOR.UsersPageHeader).getText();
		if(hdr.contains("All Users"))
		SuccessReport("All Users Page", "Navigation to All Users page is successful");
		else
		failureReport("All Users Page", "Navigation to All Users page is unsuccessful");
		
	}

	

	

}
