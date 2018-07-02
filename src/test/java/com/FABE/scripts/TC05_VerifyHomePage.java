/*'########################################################
	' Objective            :     Verify components on Home Page.
	' Test Case            :     TC5_VerifyHomePage
	' Author               :     Niharika
	' Date Created         :     09-May-2018
*/	

package com.FABE.scripts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FABE.objrepo.FaboryOR.HomePageOR;
import com.FABE.workflows.ApplicationUtil;
public class TC05_VerifyHomePage extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC05_VerifyHomePage() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);			
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC05_VerifyHomePage")) {
				child = extent.startTest("FABORY_Home Page", strDesc);
				iterationReport(iLoop - 1, strDesc +":         Start");
				String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
				String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();				
			
				    
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					isElementDisplayed(HomePageOR.HeaderPhoneNumber,"Phone Number");
					isElementDisplayed(HomePageOR.ProductsMenu,"Products Menu");
					isElementDisplayed(HomePageOR.KnowledgeCenter,"Knowledge Center");
					isElementDisplayed(HomePageOR.FastenersTab,"Fasteners Tab");
					isElementDisplayed(HomePageOR.IndustrialsTab,"Industrials Tab");
					isElementDisplayed(HomePageOR.SearchTexbox,"Search Textbox");
					isElementDisplayed(HomePageOR.SearchButton,"Search button");
					isElementDisplayed(HomePageOR.CartIcon,"Cart Icon");
					isElementDisplayed(HomePageOR.Slider1,"Slider1");
					isElementDisplayed(HomePageOR.ChildSlider1,"Child Slider1");
					isElementDisplayed(HomePageOR.ChildSlider2,"Child Slider2");
					isElementDisplayed(HomePageOR.PopularProductsHeader,"Popular Products Header");
					isElementDisplayed(HomePageOR.PopularProductsHeader,"First Popular Product");
					JSClick(HomePageOR.PopularProduct1,"Popular Product1");
					
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
