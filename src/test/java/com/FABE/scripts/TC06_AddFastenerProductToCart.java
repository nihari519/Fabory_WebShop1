/*'########################################################
	' Objective            :     Verify Fastener product add to cart from ADP Master page.
	' Test Case            :     testTC1_SignUp
	' Author               :     Niharika
	' Date Created         :     03-May-2018
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

public class TC06_AddFastenerProductToCart extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC06_AddFastenerProductToCart() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				
				
			
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC06_AddFastenerProductToCart")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					
					
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					
					//verify categories and sub-categories displayed under Products menu
					JSClick(HomePageOR.ProductsMenu,"Products Menu");
					if(isWebElementPresent(HomePageOR.ProductsMenu_Bolts,"Bolts category under Products menu"))
					JSClick(HomePageOR.ProductsMenu_Bolts,"Bolts Category");
					isWebElementPresent(ALPOR.ALP_BoltsHeader, "ALP Page for BOLTS category");
					isWebElementPresent(ALPOR.ALP_filters_Categoryheader,"Category Filter Header");
					
					//click on first category displayed
					JSClick(ALPOR.ALP_filters_Category1,"First Catgory displayed");
					executionDelay(1500);
					
					//check if catgory listing page is displayed and navigate to ALP page.
					if(driver.findElements(ALPOR.ALP_filters_Category1).size()!=0)
					{
						JSClick(ALPOR.ALP_filters_Category1,"first filter displayed");
						JSClick(ALPOR.ALP_FirstMaster_ViewButton,"Master Product's View button");
						if(driver.findElements(ADPOR.ADP_ShowVariants_Button).size()!=0)
						{
						JSClick(ADPOR.ADP_ShowVariants_Button,"ShowVariants button");
						SuccessReport("ADP", "Navigation to Master ADP Page is Successful");
						}
						else
						failureReport("ADP", "Navigation to Master ADP Page is Unsuccessful");						
					}
					
					else
					{					
						JSClick(ALPOR.ALP_FirstMaster_ViewButton,"Master Product's View button");
						if(driver.findElements(ADPOR.ADP_ShowVariants_Button).size()!=0)
						SuccessReport("ADP", "Navigation to Master ADP Page is Successful");
						else
						failureReport("ADP", "Navigation to Master ADP Page is Unsuccessful");				
						
					}
					
					//add to cart button click
					String ArticleNum=driver.findElement(ADPOR.VariantTable_Row1_ArticleNumber).getText().toString();
					JSClick(ADPOR.VariantTable_Row1_Addtocart,"Add to cart");
										
					//Navigate to cart page
					JSClick(CommonOR.Minicart_icon,"Minicart");
					executionDelay(1000);
					if(isWebElementPresent(CartOR.Cart_Header, "Cart header"))
					SuccessReport("Cart", "Navigation to Cart Page is Successful");
					else
					failureReport("Cart", "Navigation to Cart Page is Unsuccessful");
					
					//verify if above added product is displayed on cart page
					if(driver.findElements(By.xpath("//div[@class='item__info']/a/span[text()='"+ArticleNum+"']")).size()!=0)
					SuccessReport("Cart", "Product :"+ArticleNum+ "added to the Cart");
					else
					failureReport("Cart", "Product is not added to the Cart");
	
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
