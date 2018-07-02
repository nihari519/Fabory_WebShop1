/*'########################################################
	' Objective            :     Verify order submission through 'SEPA Bank Transfer'.
	' Test Case            :     TC11_VerifySepaBankTransferPayment
	' Author               :     Niharika
	' Date Created         :     06-JUN-2018
*/	

package com.FABE.scripts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import com.FABE.objrepo.FaboryOR.CheckoutOR;
import com.FABE.objrepo.FaboryOR.CommonOR;
import com.FABE.workflows.ApplicationUtil;

public class TC11_VerifySepaBankTransferPayment extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC11_VerifySepaBankTransferPayment() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
		
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC11_VerifySepaBankTransferPayment")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					String Article = Excelobject.getCellData(SheetName, "PRODUCT1", iLoop);
					
					
				
	
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
					if(isElementDisplayed(CheckoutOR.SA_PONumber,"PO Number"))
					driver.findElement(CheckoutOR.SA_PONumber).clear();
					executionDelay(2000);
					driver.findElement(CheckoutOR.SA_PONumber).sendKeys("1234");
					isElementDisplayed(CheckoutOR.SA_RequestedDeliveryDate,"Requested Delivery Date");
					executionDelay(2000);
					JSClick(CheckoutOR.NextButton1, "Next button");
					executionDelay(2000);
					
//					{	executionDelay(2000);					
//						List<WebElement> ele=driver.findElements(CheckoutOR.DatePickerErrorMsg);
//						if(ele.size()!=0)
//						{
//							String message =driver.findElement(CheckoutOR.DatePickerErrorMsg).getText().toString();
//							System.out.println("message displayed is"+message);
//							String[] errtext;
//							errtext=message.split("\\s+");
//							for(int i=1; i<errtext.length; i++)
//							{
//							System.out.println(""+i +":"+errtext[i]);
//							}							
//							String EarliestDate=errtext[9];							
//							EarliestDate=errtext[9].substring(0, 10);
//							System.out.println("Earliest Possible date: "+EarliestDate);
//							
//							//Stamp the earliest possible date in Requested datepicker
//							JavascriptExecutor executor = (JavascriptExecutor) driver;
//							executor.executeScript("document.getElementById('checkout_delivery_date').value = '" + EarliestDate + "'");
//							executionDelay(3000);
//							JSClick(CheckoutOR.NextButton1, "Next button");
//							executionDelay(3000);
//							
//					   }//inner if
//					}//outer if
					
					   //Select credit card Payment Method 
					  if(isElementDisplayed(CheckoutOR.CardPayment_RadioButton,"Payment Type - Card"))
					  {
						driver.findElement(CheckoutOR.CardPayment_RadioButton).click();
						 executionDelay(4000);
						    List<WebElement> button=driver.findElements(CheckoutOR.NextButton2);
						    if(button.size()>0)
							{
						    	JSClick(CheckoutOR.NextButton2, "Next button");		
							    executionDelay(3000);
							}
						    else continue;
						
						
						//verify step3 - payment and billing address section 
						if(isElementDisplayed(CheckoutOR.Payment_BillingAddress,"Payment & Billing Address"))
						{   selectByIndex(CheckoutOR.Title_PaymentBilling,1,"Title");
						    executionDelay(1000);
						    JSClick(CheckoutOR.SEPABankTransfer_radiobutton,"SEPA Bank Transfer");
							JSClick(CheckoutOR.Next_PaymentBilling,"Next button");
							executionDelay(3000);
						}
						
						//verify checkout page step4 - Final Review and submit the Order.
						if(isElementDisplayed(CheckoutOR.FinalReviewHeader, "Final Review Header"))
						{   
						
						isElementDisplayed(CheckoutOR.FinalReviewVAT,"VAT");
						isElementDisplayed(CheckoutOR.FinalReviewOrderTotal,"Order Total");
						driver.findElement(CheckoutOR.FinalReviewTermsCheckbox).click();
						executionDelay(1000);
						JSClick(CheckoutOR.SubmitOrder,"Submit Order");
						if(isElementDisplayed(CheckoutOR.SEPAContinue,"SEPA Continue Page"))
						JSClick(CheckoutOR.SEPAContinue,"SEPA page Continue button");
						type(CheckoutOR.SEPA_Adyen_Email,EmailAddress,"Email Address");
						executionDelay(2000);
						type(CheckoutOR.SEPA_Email_Note,"Test Email","Email Note");
						executionDelay(2000);
						click(CheckoutOR.SEPAContinue,"SEPA page Continue button");
						//confirmation page is not displaying from local
						
						}
						
						//verify Order confirmation page.
						if(isElementDisplayed(CheckoutOR.OrderSuccess,"Order Confirmation Page"))
						{
						SuccessReport("Order confirmation Page", " is Displayed");
						String OrderNum=driver.findElement(CheckoutOR.ConfirmPage_OrderNumber).getText().toString();
						SuccessReport("Order placed is",""+OrderNum);
						}
						else
						failureReport("Order confirmation Page", " is not Displayed");
					  }
					  else
						  failureReport("Credit card payment", "option is not available for user.");
                    
					
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
