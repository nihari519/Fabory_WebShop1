/*'########################################################
	' Objective            :     Create a new budget,assign budget to the user, verify the validation on cart page and delete the budget from backoffice.	
	' Test Case            :     TC14_CreateNewBudgetAndVerify
	' Author               :     Niharika
	' Date Created         :     07-Jun-2018
*/	

package com.FABE.scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.List;

import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.workflows.ApplicationUtil;

public class TC14_CreateNewBudgetAndVerify extends ApplicationUtil {
	String SheetName = configProps.getProperty("InputWorkSheet");
	
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}

	@Test
	public void testTC14_CreateNewBudgetAndVerify() throws Throwable {
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;
				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
		
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC14_CreateNewBudgetAndVerify")) {
					child = extent.startTest("FABORY_Add to Cart", strDesc);
					iterationReport(iLoop - 1, strDesc +":         Start");
					
					String EmailAddress = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
					String Password = Excelobject.getCellData(SheetName, "PASSWORD", iLoop).toString();
					String Article = Excelobject.getCellData(SheetName, "PRODUCT1", iLoop);

				    Calendar cal= Calendar.getInstance();
				    int i=cal.get(Calendar.DATE);
	

				    navigateToWebsite();
					applicationLogin(EmailAddress, Password);					
					JSClick(LoginOR.AdminDropdownIcon,"Admin dropdown");
					JSClick(LoginOR.BudgetsMenu,"Budgets Menu");
					
					if(driver.findElement(LoginOR.BudgetsHeader).getText().contains("All Budgets"))						
							SuccessReport("Budgets", "Page is displaying");
							else
							failureReport("Budgets", "Page is not displaying");
					
					JSClick(LoginOR.CreateBudgetLink,"Create New Budget");
					executionDelay(2000);
					isElementDisplayed(LoginOR.CreateBudgetHeader,"Create Budget");
					type(LoginOR.BudgetName,"Test Budget","Budget Name");
					jsType(LoginOR.BudgetAmount,"100","Budget amount");
					selectByIndex(LoginOR.CurrecyDropdown,1,"Currency");
					selectByIndex(LoginOR.RecurrenceDropdown,1,"Budget Recurrence");
					click(LoginOR.Budgetstartdate,"Start date");
					selectTodayDateFromMultiDateCalendar(i);
					executionDelay(2000);
					click(LoginOR.Budgetenddate,"End Date");
					selectTodayDateFromMultiDateCalendar(i+7);
					executionDelay(2000);
					JSClick(LoginOR.BudgetSave,"Save Budget");
					executionDelay(2000);
					String msg=driver.findElement(LoginOR.BudgetCreateSucess).getText();
					if(msg.contains("Budget created successfully"))
					SuccessReport("Budget", "Creation is Successful");
					else
					failureReport("Budget", "Creation is Unsuccessful");					
					
					
					//assign budget to the user from webshop
					//launchUrl(URL);
					//applicationLogin(EmailAddress, Password);
					click(LoginOR.AdminMenuDropdown,"Admin Menu");
					click(LoginOR.UsersMenu,"Users Menu");
					click(LoginOR.UserLink,"drilldown user");
					if(isElementDisplayed(LoginOR.UserDetailsPageHeader,"User Details"))
					JSClick(LoginOR.BudgetAddLink,"Budget Add");
					executionDelay(1000);
					click(LoginOR.AssignBudgetRadioButton,"Activate Budget");
					JSClick(LoginOR.DoneButton,"Done");
					if(isElementDisplayed(LoginOR.BudgetAddedLink,"Budget on User details page"))
					SuccessReport("Budget", "Activation to the user is Successful");
					else
					failureReport("Budget", "Activation to the user is Unsuccessful");
					//verify budget exceeded validation on cart page.
					boolean priceStatus=navigatetoCart();
					if(priceStatus)
					driver.close();
					else
					{	
					//verify budget validation message on cart
					WebElement qty=driver.findElement(By.xpath("//input[@value][@type='number']"));
					qty.sendKeys(Keys.chord(Keys.CONTROL, "a"), "1000");
					//qty.sendKeys("2000");
					executionDelay(1000);
					click(By.xpath("//div[@class='totals']"),"Focus change");
					executionDelay(1000);
					click(By.xpath("//form[@id='order-simulate-form-bottom']/button[contains(text(),'update')]"),"Update button");
					executionDelay(2000);
					//click(By.xpath("//button[@id='checkout-button-bottom']"),"Checkout");
					//executionDelay(1000);
					List<WebElement> notify=driver.findElements(By.xpath("//div[@class='alert alert-warning alert-dismissable']"));
					if(notify.size()>0)
					{
					if(driver.findElement(By.xpath("//div[@class='alert alert-warning alert-dismissable']")).getText().contains("Budget Exceeded"))
					SuccessReport("Cart Page", "Budget Exceeded notification is displaying");
					else
					failureReport("Cart Page", "Budget Exceeded notification is not displaying");
					}
					else
					failureReport("Cart Page", "Budget Exceeded notification is not displaying");
					
					
					deleteBudgetBackoffice("Test Budget");
					//logoutWebShop();
			    	logoutBackOffice();
					}
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
