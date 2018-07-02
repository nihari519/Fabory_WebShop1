/*'########################################################
	' Objective            :     Add New Address on Checkout Page.
	' Test Case            :     TC13_CheckoutAddNewAddress
	' Author               :     Niharika
	' Date Created         :     06-Jun-2018
*/	

package com.FABE.scripts;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FABE.objrepo.FaboryOR.AddressOR;
import com.FABE.objrepo.FaboryOR.CheckoutOR;
import com.FABE.objrepo.FaboryOR.CommonOR;
import com.FABE.workflows.ApplicationUtil;

public class TC13_CheckoutAddNewAddress extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC13_CheckoutAddNewAddress() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
		
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC13_CheckoutAddNewAddress")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					String Article = Excelobject.getCellData(SheetName, "PRODUCT1", iLoop);
					String Country = Excelobject.getCellData(SheetName, "COUNTRY", iLoop);
					String City = Excelobject.getCellData(SheetName, "CITY", iLoop);
					String PostalCode = Excelobject.getCellData(SheetName, "POST CODE", iLoop);
					String Addressline1 = Excelobject.getCellData(SheetName, "ADDRESS LINE 1", iLoop);
					String FirstName = Excelobject.getCellData(SheetName, "FIRST NAME", iLoop);
					String SurName = Excelobject.getCellData(SheetName, "LAST NAME", iLoop);
				
	
					navigateToWebsite();
					applicationLogin(EmailAddress, Password);
					
					//Navigate to cart page and if cart empty, add product to cart
					String var=driver.findElement(CommonOR.Minicart_Value).getText();
					if(!var.contentEquals("0"))
					click(CommonOR.Minicart_icon,"Minicart");
					else					
					addProductToCartAndVerify(Article);	
					executionDelay(2000);					
					
					//Navigate to checkout page
					navigateToCheckout();
					
					//verify checkout page step1 -Shipping Address 
					isElementDisplayed(CheckoutOR.ShippingAddresHeader,"Shipping Address Section");
					if(isElementDisplayed(CheckoutOR.Checkout_ShippingAddress,"Shipping Address"))
					SuccessReport("Checkout Page", "Shipping Address is displaying");
					else
					{
						click(AddressOR.NewAddress_Button, "New Address");
						executionDelay(1000);
						selectBySendkeys(AddressOR.StateProvince,Country, "State/Province");
						executionDelay(3000);
						selectByIndex(AddressOR.Title,1,"Title");
						type(AddressOR.FirstName,FirstName,"First Name");
						type(AddressOR.LastName,SurName,"Last Name");
						type(AddressOR.AddressLine1,Addressline1,"AddressLine1");
						type(AddressOR.TownCity,City,"Town/City");
						type(AddressOR.PostalCode,PostalCode,"Postal Code");
						click(AddressOR.SaveAsShippingCheckbox,"Save Shipping Address");
						click(AddressOR.Submit,"Submit");
						executionDelay(2000);
						//verify New Address on Checkout page.
						if(isElementDisplayed(CheckoutOR.Checkout_ShippingAddress,"Shipping Address"))
						SuccessReport("Checkout Page", "Shipping Address Added is displaying Successfully");
						else
						failureReport("Checkout Page", "Shipping Address Added is not displaying");
						
					}
					
                    
					
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
