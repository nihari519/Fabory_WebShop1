/*'########################################################
	' Objective            :     Verify search for a product and Add to cart from ADP page.
	' Test Case            :     TC7_SearchForProductAndAddToCart
	' Author               :     Niharika
	' Date Created         :     29-May-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.ADPOR;
import com.FABE.objrepo.FaboryOR.ALPOR;
import com.FABE.objrepo.FaboryOR.CartOR;
import com.FABE.objrepo.FaboryOR.CommonOR;
import com.FABE.objrepo.FaboryOR.HomePageOR;
import com.FABE.workflows.ApplicationUtil;

public class TC07_SearchForProductAndAddToCart extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC07_SearchForProductAndAddToCart() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC07_SearchForProductAndAddToCart")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					String Article = Excelobject.getCellData(SheetName, "PRODUCT1", iLoop);
					
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					
					//search for a product,add to cart and verify product on cart page
					addProductToCartAndVerify(Article);					
	
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
