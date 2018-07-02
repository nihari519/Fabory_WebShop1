/*'########################################################
	' Objective            :     Verify Admin user's Login view.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
*/	

package com.FABE.scripts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.HomePageOR;
import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.objrepo.FaboryOR.SignUpOR;
import com.FABE.workflows.ApplicationUtil;

public class TC03_VerifyProductsMenu extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC03_VerifyProductsMenu() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
				String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
				
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC03_VerifyProductsMenu")) {
					child = extent.startTest("FABORY_Products Menu", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					
					verifyProductMenuAndCategories();
					
					//click on the home page
					driver.findElement(By.xpath("//*[@id='slider-1']/div/div/div")).click();
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

	private void verifyProductMenuAndCategories() throws Throwable {
		//verify Products Menu
		if(driver.findElements(HomePageOR.ProductsMenu).size()!=0)
			SuccessReport("Products Menu", "is displayed Successful");
		else
			failureReport("Products Menu","is not displayed");
		//verify categories and sub-categories displayed under Products menu
		JSClick(HomePageOR.ProductsMenu,"Products Menu");
		isWebElementPresent(HomePageOR.ProductsMenuFastenersCategory,"Fasteners category is displayed under Products menu");
		List<WebElement> ele=driver.findElements(HomePageOR.FastenersSubcategories);
		if(ele.size()>=1)
			SuccessReport("Fasteners", "Subcategories are displayed under Fasteners");
		else
			failureReport("Fasteners", "Subcategories are not displayed under Fasteners");
		
		isWebElementPresent(HomePageOR.ProductsMenuIndustrialsCategory,"Industrials category is displayed under Products menu");
		List<WebElement> ele1=driver.findElements(HomePageOR.IndustrialsSubcategories);
		if(ele1.size()>=1)
			SuccessReport("Industrials", "Subcategories are displayed under Industrials");
		else
			failureReport("Industrials", "Subcategories are not displayed under Industrials");
		
	}

	

	

}
