package com.FABE.scripts;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FABE.objrepo.FaboryOR.GmailOR;
import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.objrepo.FaboryOR.SignUpOR;
import com.FABE.workflows.ApplicationUtil;

public class TC15_VerifyForgotPasswordLink extends ApplicationUtil 
{  
	String SheetName = configProps.getProperty("InputWorkSheet");
	@BeforeClass
	public void excelLoad() throws Throwable {
		// Provide sheet path and sheet name , will return the respective sheet object
		inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, SheetName);
	}


	@Test
	public void testTC15_VerifyForgotPasswordLink() throws Throwable {
		String GURL = configProps.getProperty("Gmail_URL");
		for (int iLoop = 2; iLoop <= inputSheet.getPhysicalNumberOfRows(); iLoop++) {
			try {
				RowFailNum = 0;

				String Testcase = Excelobject.getCellData(SheetName, "Testcase", iLoop);
				String strDesc = Excelobject.getCellData(SheetName, "Description", iLoop);
				String runStatus = Excelobject.getCellData(SheetName, "RunStatus", iLoop);
				String Email = Excelobject.getCellData(SheetName, "EMAIL ADDRESS", iLoop);
				String Pwd = Excelobject.getCellData(SheetName, "PASSWORD", iLoop);
				Boolean flag=null;
				
				if (runStatus.trim().equalsIgnoreCase("Y") && Testcase.trim().equalsIgnoreCase("TC15_VerifyForgotPasswordLink")) {
					child = extent.startTest("Forgot Password Link", strDesc);
					iterationReport(iLoop - 1, strDesc + " Start");
					navigateToWebsite();
					click(LoginOR.LoginLinkHeader,"Login/Signup link");					
					isWebElementPresent(SignUpOR.ForgotPasswordLink, "Forgot Password Link");
					//verify popup
					click(SignUpOR.ForgotPasswordLink,"Forgot Password");	
					isWebElementPresent(SignUpOR.ResetPasswordPopup_Header,"Reset Password Popup");
					if(isWebElementPresent(SignUpOR.ForgotPassword_Email,"Email Address"))
					{
						type(SignUpOR.ForgotPassword_Email,"","Email Address");
						click(SignUpOR.ResetPassword_Button,"Reset Password Button");
						executionDelay(1000);
						if(isWebElementPresent(SignUpOR.Email_Error,"Email invalid error Message"))
						{
							SuccessReport("Email", "Validation message displayed Successfully");
							flag=true;
						}
						else
							failureReport("Email","Validation message not displayed");
						if(flag)
						{   executionDelay(1000);
						    jsType(SignUpOR.ForgotPassword_Email, Email,"Email Address");
							click(SignUpOR.ResetPassword_Button,"Reset Password Button");
							executionDelay(3000);
							if(isWebElementPresent(SignUpOR.ValidEmail_Message,"Reset email sent confirmation message on modal popup"))
							click(SignUpOR.ModalPopup_Close,"Close Modal Popup");
							executionDelay(1000);
							isWebElementPresent(SignUpOR.ResetPassword_msg,"Reset email sent confirmation message on login page ");							
						}
						//verify email is received
						driver.manage().deleteAllCookies();
						executionDelay(1000);
						driver.navigate().to(GURL);
						executionDelay(3000);
						type(GmailOR.EmailTextbox,Email,"Email Textbox");
						JSClick(GmailOR.EmailNext,"Next button");
						executionDelay(3000);
						type(GmailOR.PwdTextbox,Pwd,"Password Textbox");
						JSClick(GmailOR.PasswordNext,"Next button");
						executionDelay(3000);
						if(isWebElementPresent(GmailOR.GmailHome,"Gmail Home Page"))
						{
							if(isWebElementPresent(GmailOR.PasswordResetUnreadEmailSubject,"Forgot Password Email Subject"))
							{
								SuccessReport("Email", "ForgotPassword email received");
								click(GmailOR.PasswordResetUnreadEmailSubject, "Open Email");
								executionDelay(1000);
								if(isWebElementPresent(GmailOR.EmailSender,"Fabory Email sender"))
								{  List<WebElement> showEmailBody=driver.findElements(GmailOR.ShowEmailBody);
								   if(showEmailBody.size()>0)
								   {
									click(GmailOR.ShowEmailBody,"Show Email Body");   								   
									executionDelay(1000);
								   }
									click(GmailOR.Email_ResetPasswordLink,"Reset Password Link");
									executionDelay(3000);
									//Window Handles code should be implemented
									String parent= driver.getWindowHandle();
									Set<String> handles=driver.getWindowHandles();
									Iterator<String> I1=handles.iterator();
									I1.next();
									do
									{
										String childWindow=I1.next();
										if(!parent.equals(childWindow))
										{
											driver.switchTo().window(childWindow);
											String title=driver.switchTo().window(childWindow).getTitle();
											System.out.println("Page Title: "+title);
											if(title.contentEquals("Reset Password"))
											{
												//accept cookies alert
												executionDelay(2000);
												if(driver.findElements(SignUpOR.CookieAlert).size()!=0)
												click(SignUpOR.CookieAlert,"Accept Cookies");
												else
										        System.out.println("Cookies alert is not displayed");
												//reset password
												type(GmailOR.NewPassword, "testen12#", "New Password");
												executionDelay(1000);
												type(GmailOR.ConfirmPassword,"testen","Confirm Password");
												executionDelay(1000);
												JSClick(GmailOR.UpdateButton,"Update button");
												if(isWebElementPresent(GmailOR.ConfirmPwdValidateMessage,"Confirm Password validation Message"))
												SuccessReport("Confirm Password", "Validation message displayed Successfully");
												else
												failureReport("Confirm Password","Validation message not displayed");
												driver.close();
											}
											else
											continue;
										}										
									}while(I1.hasNext());
									
									driver.switchTo().window(parent);
								}								
							}
	
							
							else
								failureReport("Email","ForgotPassword email not received");		
						}						
					}
					click(GmailOR.UserAccount,"Gmail User");
					executionDelay(1000);
					click(GmailOR.SignOut,"Sign out");
					executionDelay(3000);
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
