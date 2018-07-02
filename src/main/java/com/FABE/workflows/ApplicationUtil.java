package com.FABE.workflows;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
//import org.openqa.selenium.Alert;

import com.FABE.accelerators.ActionEngine;
import com.FABE.objrepo.FaboryOR.*;

public class ApplicationUtil extends ActionEngine {
	public static String BackOfficeURL = "/backoffice/login.zul";
	public static String BackofficeUser=configProps.getProperty("BO_UserName");
	public static String BackofficePassowrd=configProps.getProperty("BO_PassWord");
    
	public void gmailSignIn(String userName, String password) throws Exception {
		try {
			
//			type(SignUpOR.GmailEmail, userName, "Enter Password on gmail");
//			click(SignUpOR.NextButton, "");
//			type(SignUpOR.GmailPassword, password, "Enter Password on gmail");
//			click(SignUpOR.NextButton, "Click on next button");
//			
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

    //Registration as New Company
	public void signUpNew(String EmailAddress, String ConfirmEmail, String FirstName, String LastName, String CompanyName, String PhoneNumber, String AddressLine1,
			String PostalCode, String City, String Country, String Message) {
		try {
			//Select NL country and EN Language from Globe icon
			JSClick(HomePageOR.LogIn, "login/Sigup link on HomePage");
			JSClick(HomePageOR.Register, "Create Account button on LoginPage");
			JSClick(HomePageOR.CoutryGlobeIcon, "Country Globe Icon");
			JSClick(HomePageOR.GlobeNLCountry,"NL Country");
			JSClick(HomePageOR.GlobeNLLangEng,"English language");
			executionDelay(2000);
			
			//Fill Registration form
			jsType(SignUpOR.EmailAddress, EmailAddress, "Enter FirstName");
			jsType(SignUpOR.ConfirmEmail, ConfirmEmail, "Enter FirstName");
			jsType(SignUpOR.FirstName, FirstName, "Enter FirstName");
			jsType(SignUpOR.LastName, LastName, "Enter LastName");
			jsType(SignUpOR.CompanyName, CompanyName, "Enter CompanyName");
			jsType(SignUpOR.PhoneNumber, PhoneNumber, "Enter Telephone");
			jsType(SignUpOR.AddressLine1, AddressLine1, "Enter Address Line");
			jsType(SignUpOR.PostalCode, PostalCode, "Enter PostalCode");
			executionDelay(1000);
			jsType(SignUpOR.TownCity, City, "Enter City");
				
			//Accept Policies checkbox
			JSClick(SignUpOR.AcceptChechbox,"Accept checkbox");

			//click on Register button
			JSClick(SignUpOR.RegisterButton, "Click on Register Button");		
			
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	//Registration as New Company
		public void signUpExisting(String EmailAddress, String ConfirmEmail, String FirstName, String LastName, String CompanyID, String Message) {
			try {
				JSClick(HomePageOR.LogIn, "login/Sigup link on HomePage");
				JSClick(HomePageOR.Register, "Create Account button on LoginPage");
				
				jsType(SignUpOR.EmailAddress, EmailAddress, "Enter FirstName");
				jsType(SignUpOR.ConfirmEmail, ConfirmEmail, "Enter FirstName");
				jsType(SignUpOR.FirstName, FirstName, "Enter FirstName");
				jsType(SignUpOR.LastName, LastName, "Enter LastName");
				JSClick(SignUpOR.ExistingCmpyChkbox, "Existing Company Checkbox");
				executionDelay(1000);
				jsType(SignUpOR.CompanyID, CompanyID, "Enter CompanyID");
						
					
				//Accept Policies checkbox
				JSClick(SignUpOR.AcceptChechbox,"Accept checkbox");
				//click on Register button
				if(driver.findElement(SignUpOR.RegisterButton).isEnabled())									
				click(SignUpOR.RegisterButton, "Click on Register Button");		
				else
					failureReport("Register Button", "Is not Enableed/clickable");
				
			} catch (Throwable e) {

				failureReport("Exception", "@signUpExisting :"+e);
			}
		}
	
	//search for a B2BCustomer and delete.
	public void searchAndDeleteB2bCustomer(String Customer) throws InterruptedException
	{
	try {
		JSClick(Backoffice_LoginOR.B2BCommerceLink,"B2B Commerce Link");
		JSClick(Backoffice_LoginOR.B2BCustomerLink,"B2B Customer Link");
		
		//search for the customer
		jsType(Backoffice_LoginOR.SearchTextbox,Customer,"Search textbox");
		executionDelay(2000);
		JSClick(Backoffice_LoginOR.SearchButton,"search button");
		executionDelay(2000);
		String emailXpath= "//span[text()='"+Customer+"']";
		By CustomerSignup=By.xpath(emailXpath);
		
		//select and delete the customer
		if(driver.findElements(CustomerSignup).size()!=0)
		{
		JSClick(CustomerSignup,"Customer record");
		JSClick(Backoffice_LoginOR.SelectCheckbox,"Select checkbox");
		executionDelay(1000);
		JSClick(Backoffice_LoginOR.DeleteIcon,"Delete Icon");	
		if(driver.findElements(Backoffice_LoginOR.ConfirmPopupMsg).size()!=0)
		JSClick(Backoffice_LoginOR.ConfirmYes,"Delete confirmation");				
		if(driver.findElements(Backoffice_LoginOR.NOResults).size()!=0)
			SuccessReport("Customer", "Deletion is Successful");
		else
			failureReport("Customer","Deletion is Unsuccessful");
		}
		else
		System.out.println("Customer searched is not displayed");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			failureReport("Failed","@searchAndDeleteB2bCustomer() with exception: "+e);
		}
		
	}

	//search for a B2BCustomer and delete.
		public void searchAndDeleteBudget(String budget) throws Throwable
		{
			WebElement ele=driver.findElement(Backoffice_LoginOR.B2BCommerceLink);
			JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
			myExecutor.executeScript("arguments[0].click();", ele);
			executionDelay(2000);
			
			//click on B2B Customer
			WebElement ele1=driver.findElement(Backoffice_LoginOR.B2BBudgetLink);
			JavascriptExecutor myExecutor1 = ((JavascriptExecutor) driver);
			myExecutor1.executeScript("arguments[0].click();", ele1);
			executionDelay(1000);
			
			//search for the budget
			JSClick(Backoffice_LoginOR.AdvanceSearchButton,"Advanced search");
			executionDelay(1000);
			driver.findElement(Backoffice_LoginOR.SearchNameInput).sendKeys(budget);
			executionDelay(3000);
			driver.findElement(Backoffice_LoginOR.SearchButton).click();
			executionDelay(2000);
			
//			String emailXpath= "//span[text()='"+budget+"']";
//			By CustomerSignup=By.xpath(emailXpath);
			//select and delete the budget displayed
			List <WebElement> chkbox= driver.findElements(By.xpath("//span[@class='z-listheader-checkable']"));
			if(chkbox.size()>0)
			{
			driver.findElement(By.xpath("//span[@class='z-listheader-checkable']")).click();
			executionDelay(2000);
			driver.findElement(Backoffice_LoginOR.DeleteIcon).click();	
			executionDelay(1000);
			List <WebElement> ele11=driver.findElements(Backoffice_LoginOR.ConfirmPopupMsg);
			if(ele11.size()>0)
			driver.findElement(Backoffice_LoginOR.ConfirmYes).click();
			executionDelay(1000);
			
			if(driver.findElements(Backoffice_LoginOR.NOResults).size()!=0)
				SuccessReport("Budget", "Deletion is Successful");
			else
				failureReport("Budget","Deletion is Unsuccessful");
			
			}
			else
			System.out.println("Budget searched is not displayed");
			
		}
	
	//search for an Article.
		public void searchProduct(String Product) throws Throwable
		{
			executionDelay(1000);
			JSClick(HomePageOR.SearchTexbox,"search Textbox");			
			driver.findElement(HomePageOR.SearchTexbox).sendKeys(Product);
			executionDelay(1000);
			JSClick(HomePageOR.SearchButton,"Search button");
			executionDelay(1000);
			if(driver.findElement(By.xpath("//*[@id='section-breadcrumb']/descendant::ol/li[text()='"+Product+"']")).isDisplayed())
				SuccessReport("Article:"+Product, "Search is Successful");
			else
				failureReport("Article:"+Product,"Search is Unsuccessful");
		}
		
	//Add product to cart
	public void addProductToCart() throws Throwable
	{   
		if(driver.findElements(ADPOR.ADP_Addtocart).size()!=0)
			SuccessReport("ADP", "Navigation to ADP Page is Successful");
			else
			failureReport("ADP", "Navigation to ADP Page is Unsuccessful");						
		//add to cart
	    JSClick(ADPOR.ADP_Addtocart,"Add to cart");
	    
	}
	
	
	//navigate to Cart page
	public boolean navigatetoCart() throws Throwable
	{   boolean flag=false;
		JSClick(CommonOR.Minicart_icon,"Minicart");
		executionDelay(1000);
		if(isWebElementPresent(CartOR.Cart_Header, "Cart header"))
		{
		SuccessReport("Cart", "Navigation is Successful");
		WebElement CartPrice=driver.findElement(By.xpath("//span[@class='cart__top--amount']"));
		if(CartPrice.isDisplayed() && CartPrice.getText().contains("XX.XX"))
		{
		flag=true;
		failureReport("Cart", "Prices on cart is displaying as XX.XX");
		}
		else
		SuccessReport("Cart", "Prices on cart are displaying as expected");
		}
		else if(isWebElementPresent(HomePageOR.HomeNotifcation,"Error Notification"))
		{	
		WebElement errorNotify=driver.findElement(By.xpath("//div[@class='alert alert-warning alert-dismissable']"));		
		failureReport("Cart Navigation is Unsuccessful", "With error"+errorNotify.getText());	
		}
		return flag;
	}

	

	
	//verify product on cart page
	public void verifyProductAddedToCart(String Product)
	{
		if(driver.findElements(By.xpath("//div[@class='item__info']/a/span[text()='"+Product+"']")).size()!=0)
			SuccessReport("Product", ""+Product+ ", is added to the Cart");
			else
			failureReport("Product", ""+Product+ ", is not added to the Cart");
	}
	
	public void navigateToCheckout() throws Throwable
	{
		if(isWebElementPresent(CartOR.Cart_Header,"Cart Header"))
		{
		JSClick(CheckoutOR.Checkout_button,"Checkout button");
		executionDelay(2000);
		String header=driver.findElement(CheckoutOR.Checkout_Header).getText().toString();
		if(header.contains("Secure Checkout"))
		SuccessReport("Checkout", "Navigation is Successful");
		else
		failureReport("Checkout", "Navigation is Unsuccessful");
		}
		else
			failureReport("Cart", "Navigation is Unsuccessful");
	}
	//search for a product,add to cart and verify product on cart page
	public void addProductToCartAndVerify(String Product) throws Throwable {
		searchProduct(Product); 								
		addProductToCart();				    				
		navigatetoCart();					
		
		//verify if above added product is displayed on cart page
		verifyProductAddedToCart(Product);
		
	}
    
	//backoffice login function
	public void boLogin(String Username, String Password) throws Throwable
	{
		jsType(Backoffice_LoginOR.BO_Username, Username, "Enter Username for login");
		jsType(Backoffice_LoginOR.BO_Password, Password, "Enter Password for login");
		JSClick(Backoffice_LoginOR.BO_Login, "Login Button");
		executionDelay(3000);
	}
    
	////verify Admin menu option
	public void verifyAdminMenu()
	{
		if(driver.findElements(LoginOR.AdminMenuDropdown).size()!=0)
			SuccessReport("Admin Menu", "is displayed Successful");
		else
			failureReport("Admin Menu","is not displayed");
	}

	//verify Welcome menu option
	public void verifyWelcomeMenu()
	{
		String ele=driver.findElement(By.xpath("//*[@id='navbar-secondary']/descendant::a[@data-toggle='dropdown'][2]")).getText();
		if(ele.contains("Wel"))
			SuccessReport("Welcome Menu", "is displayed Successful");
		else
			failureReport("Welcome Menu","is not displayed");
	}
//logout from webshop
		public void logoutWebShop() throws Throwable
		{
			JSClick(LoginOR.WelcomeDropdownIcon,"Welcome Dropdown");
			JSClick(LoginOR.LogoutEnglish,"Logout");
			executionDelay(2000);
			if(driver.findElements(LoginOR.LoginLinkHeader).size()!=0)
		    SuccessReport("Logout", "is Successful");
		    else 
			failureReport("Logout", "is Unsucessful");
		}
		
		
//Backoffice Logout
	public void logoutBackOffice() throws Throwable
		{   executionDelay(1000);
			JSClick(Backoffice_LoginOR.BO_Logout,"Logout");
			executionDelay(2000);
			if(driver.getCurrentUrl().contains(BackOfficeURL))
		    SuccessReport("Logout", "is Successful");
		    else 
			failureReport("Logout", "is Unsucessful");
		}
	
//Login issue fix for backoffice	
	public void backofficeLogin(String Username, String Password) throws Throwable {
		
			boLogin(Username, Password);			
			if(driver.getCurrentUrl().equalsIgnoreCase(BackOfficeURL))
			{
			driver.navigate().refresh();
			boLogin(Username, Password);
			}
			else						
			if(driver.findElements(Backoffice_LoginOR.BO_Home).size()!=0)
				SuccessReport("Backoffice Login", "is Successful");
		    else 
			 failureReport("Backoffice Login", "is Unsucessful");
		
	}	
	
	
	//Login into the webshop Application	
	public void applicationLogin(String Email, String Password){
			try {
				executionDelay(1000);
				JSClick(LoginOR.LoginLinkHeader,"Login/Signup link");
				jsType(LoginOR.Username, Email, "Enter Email Id for login");
				jsType(LoginOR.Password, Password, "Enter Password for login");
				JSClick(LoginOR.Login, "Login Button");
				executionDelay(1000);
			
			} catch (Throwable e) {

				failureReport("Exception", "@applicationLogin : "+e);
			}
		}
	
	//delete a user from backoffice
	public void deleteUserBackoffice(String Email) throws Throwable {
		
		navigateToBackoffice();
		backofficeLogin(BackofficeUser, BackofficePassowrd);
		searchAndDeleteB2bCustomer(Email);
		executionDelay(5000);
		
	}
	
	//delete a budget from backoffice
	public void deleteBudgetBackoffice(String name) throws Throwable {
			navigateToBackoffice();
			backofficeLogin(BackofficeUser, BackofficePassowrd);
			searchAndDeleteBudget(name);
			executionDelay(5000);
			
		}
	/*'########################################################
	' Objective            :     Enter Credit Card Details
   
	'########################################################
*/	
	
	public void creditcardpayment(String cardNumber, String cardholder, String securitycode) throws Exception {
		try {
//			click(SetUpPaymentsOR.CreditCard, "Click on Credit card payment option");
//			type(SetUpPaymentsOR.CardNumber, cardNumber, "Enter Card number");
//			selectByIndex(SetUpPaymentsOR.ExpiryMonth, 8, "Expiry month is selected");
//			selectByIndex(SetUpPaymentsOR.ExpiryYear, 2, "Expiry year is elected");
//			type(SetUpPaymentsOR.CardHolder, cardholder, "Enter card holder name");
//			type(SetUpPaymentsOR.SecurityNumber, securitycode, "Enter security number");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter creditcardpaymentdetails
    
	'########################################################
*/	

	public void creditcardpaymentdetails(String cardNumber, String cardholder, String securitycode, String ExpiryMonth,
			String ExpiryYear) throws Exception {
		try {
//			click(SetUpPaymentsOR.CreditCard, "Click on Credit card payment option");
//			type(SetUpPaymentsOR.CardNumber, cardNumber, "Enter Card number");
//			selectByVisibleText(SetUpPaymentsOR.ExpiryMonth, ExpiryMonth, "Expiry month is selected");
//			selectByVisibleText(SetUpPaymentsOR.ExpiryYear, ExpiryYear, "Expiry Year is selected");
//			type(SetUpPaymentsOR.CardHolder, cardholder, "Enter card holder name");
//			type(SetUpPaymentsOR.SecurityNumber, securitycode, "Enter security number");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter debitcardpaymentdetails
	' Function Name        :     debitcardpaymentdetails
         :    
	'########################################################
*/	
	
	public void debitcardpaymentdetails(String cardNumber, String cardholder, String securitycode, String ExpiryMonth,
			String ExpiryYear) throws Exception {
		try {
//			click(SetUpPaymentsOR.DebitCard, "Click on Debit card payment option");
//			type(SetUpPaymentsOR.CardNumber, cardNumber, "Enter Card number");
//			selectByVisibleText(SetUpPaymentsOR.ExpiryMonth, ExpiryMonth, "Expiry month is selected");
//			selectByVisibleText(SetUpPaymentsOR.ExpiryYear, ExpiryYear, "Expiry month is selected");
//			type(SetUpPaymentsOR.CardHolder, cardholder, "Enter card holder name");
//			type(SetUpPaymentsOR.SecurityNumber, securitycode, "Enter security number");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter Bank Details
	' Function Name        :     RMChequeSelectBank
   
	'########################################################
*/	
	public void RMChequeSelectBank(String RoutingNumberInstutionNumber, String TransitNumber, String AccountNumber,
			String FirstName, String LastName, String Country, String Province, String PhoneNumber, String Street1,
			String Streeet2, String City, String ZipCode) {
		try {
//			selectByIndex(QuickPayOR.SelecteBank, 1, "Select Bank");
//			type(QuickPayOR.InstituitionNumber, RoutingNumberInstutionNumber, "Enter Institution number");
//			type(QuickPayBankOR.TransitNumber, TransitNumber, "Enter Payment for particular charge");
//			type(QuickPayBankOR.AccountNumber, AccountNumber, "Enter account number");
//			type(QuickPayBankOR.Firstname, FirstName, "Enter First Name");
//			type(QuickPayBankOR.Lastname, LastName, "Enter First Name");
//			selectByVisibleText(QuickPayBankOR.Countryname, Country, "Enter country name");
//			selectByVisibleText(QuickPayBankOR.Provincename, Province, "Enter Province name");
//			type(QuickPayBankOR.Phone, PhoneNumber, "Enter Phone Number");
//			type(QuickPayBankOR.Street1, Street1, "Enter Street1");
//			type(QuickPayBankOR.Street2, Streeet2, "Enter Streeet2");
//			type(QuickPayBankOR.City, City, "Enter City");
//			type(QuickPayBankOR.Postal, ZipCode, "Enter Zip code");
//			click(QuickPayBankOR.Agreebutton, "Click on Agree button");
//			click(SetUpPaymentsOR.Continue, "Click on Continue Button");
//			click(SetUpPaymentsOR.Confirm, "Click on Continue Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Enter Bank Details
	' Function Name        :     RMChequeNoBank
  
	'########################################################
*/	

	public void RMChequeNoBank(String RoutingNumber, String TransitNumber, String AccountNumber, String BankName,
			String FirstName, String LastName, String Country, String Province, String PhoneNumber, String Street1,
			String Streeet2, String City, String ZipCode) {
		try {
//			type(QuickPayBankOR.RoutingNumber, RoutingNumber, "Enter Routing number less than 9");
//			type(QuickPayBankOR.AccountNumber, AccountNumber, "Enter account number");
//			type(QuickPayBankOR.WriteBankName, BankName, "Enter bank name");
//			type(QuickPayBankOR.Firstname, FirstName, "Enter First Name");
//			type(QuickPayBankOR.Lastname, LastName, "Enter First Name");
//			selectByVisibleText(QuickPayBankOR.Countryname, Country, "Enter country name");
//			selectByVisibleText(QuickPayBankOR.Provincename, Province, "Enter Province name");
//			type(QuickPayBankOR.Phone, PhoneNumber, "Enter Phone Number");
//			type(QuickPayBankOR.Street1, Street1, "Enter Street1");
//			type(QuickPayBankOR.Street2, Streeet2, "Enter Streeet2");
//			type(QuickPayBankOR.City, City, "Enter City");
//			type(QuickPayBankOR.Postal, ZipCode, "Enter Zip code");
//			click(QuickPayBankOR.Agreebutton, "Click on Agree button");
//			click(SetUpPaymentsOR.Continue, "Click on Continue Button");
//			click(SetUpPaymentsOR.Confirm, "Click on Continue Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter Billing Address Details
	' Function Name        :     BillingAddress
  
	'########################################################
*/	

	public void BillingAddress(String Country, String State, String ZipCode, String Street, String City)
			throws Exception {
		try {
//			selectByVisibleText(SetUpPaymentsOR.CountryField, Country, "Select country");
//			executionDelay(2000);
//			selectByVisibleText(SetUpPaymentsOR.StateField, State, "Select State");
//			type(SetUpPaymentsOR.ZipCode, ZipCode, "Enter Zip Code");
//			type(SetUpPaymentsOR.Street, Street, "Enter street 1");
//			type(SetUpPaymentsOR.BillingCity, City, "Enter City");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter Property Setup Details
	' Function Name        :     PropertySetUp

	'########################################################
*/	
	
	public void PropertySetUp(String PostalCode, String PhoneNumber) throws Exception {

		try {
//			click(WelcomeOR.SetUpPaymentsNowButton, "Click on Set Up payments Button Now");
//			type(WelcomeOR.PostalCode, PostalCode, "Enter Postal Code");
//			click(WelcomeOR.SearchButton, "Click on Search Button");
//			click(WelcomeOR.PropertySignUpButton, "Click on Sign Up Button");
//			selectByIndex(SetUpPaymentsOR.ResidentType, 1, "Select Resident Status from Resident Type Drop Down");
//			selectByIndex(SetUpPaymentsOR.ResidencyStatus, 1,
//					"Select I'm just moving in to default street from Residency Status Drop Down");
//			type(SetUpPaymentsOR.PhoneNumber, PhoneNumber, "Enter Postal Code");
//			click(SetUpPaymentsOR.Continue, "Click on Continue Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Add Multiple Charges
	' Function Name        :     addChargeType

	'########################################################
*/	
	
	public void addChargeType(String[] chargeTypes, ResultSet chargeTypeValues) throws Exception {
		try {

			for (int i = 0; i < chargeTypes.length;) {
				String value = chargeTypeValues.getString(chargeTypes[i]);
				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
//					selectByIndex(MakeAPaymentOR.chargeType, i + 1, "Select Charge Type");
//					type(MakeAPaymentOR.Payment, value, "Enter Payment for particular charge");
//					click(MakeAPaymentOR.AddCharge, "Click on Add Charge Button");
					i++;
				}
			}
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Add Single Charge
	' Function Name        :     addChargeType
   
	'########################################################
*/	
	public void addChargeType(String chargeType, String chargeValue) throws Exception {
		try {
//			selectByVisibleText(MakeAPaymentOR.chargeType, chargeType, "Select Charge Type");
//			type(MakeAPaymentOR.Payment, chargeValue, "Enter Payment for particular charge");
//			click(MakeAPaymentOR.AddCharge, "Click on Add Charge Button");

		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Add All Charges
	' Function Name        :     addAllChargeTypes
 
	'########################################################
*/	
    
	public void addAllChargeTypes(String LateFee, String Rent, String PetDamageFee, String SecurityDeposit,
			String BikeStorage, String Other, String FirstMonthRent, String LastMonthRent, String Deposit,
			String ElevatorFee, String KeyFobDeposit, String HydroDeposit, String Parking, String Storage, String CAM,
			String OperatingEstimate, String Insurance, String TaxEstimate, String Invoice, String Utility,

			String LateFeeValue, String RentValue, String PetDamageFeeValue, String SecurityDepositValue,
			String BikeStorageValue, String OtherValue, String FirstMonthRentValue, String LastMonthRentValue,
			String DepositValue, String ElevatorFeeValue, String KeyFobDepositValue, String HydroDepositValue,
			String ParkingValue, String StorageValue, String CAMValue, String OperatingEstimateValue,
			String InsuranceValue, String TaxEstimateValue, String InvoiceValue, String UtilityValue) {

		try {
			addChargeType(LateFee, LateFeeValue);
			addChargeType(Rent, RentValue);
			addChargeType(PetDamageFee, PetDamageFeeValue);
			addChargeType(SecurityDeposit, SecurityDepositValue);
			addChargeType(BikeStorage, BikeStorageValue);
			addChargeType(Other, OtherValue);
			addChargeType(FirstMonthRent, FirstMonthRentValue);
			addChargeType(LastMonthRent, LastMonthRentValue);
			addChargeType(Deposit, DepositValue);
			addChargeType(ElevatorFee, ElevatorFeeValue);
			addChargeType(KeyFobDeposit, KeyFobDepositValue);
			addChargeType(HydroDeposit, HydroDepositValue);
			addChargeType(Parking, ParkingValue);
			addChargeType(Storage, StorageValue);
			addChargeType(CAM, CAMValue);
			addChargeType(OperatingEstimate, OperatingEstimateValue);
			addChargeType(Insurance, InsuranceValue);
			addChargeType(TaxEstimate, TaxEstimateValue);
			addChargeType(Invoice, InvoiceValue);
			addChargeType(Utility, UtilityValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addChargeTypes(String[] chargeTypes, ResultSet chargeTypeValues, Class thisClass) throws Exception {
		try {
			for (int i = 0; i < chargeTypes.length;) {
				String value = chargeTypeValues.getString(chargeTypes[i]);
				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {

//					selectByIndex(MakeAPaymentOR.chargeType, i + 1, "Select Charge Type");
//					type(MakeAPaymentOR.Payment, value, "Enter Payment for particular charge");
//					click(MakeAPaymentOR.AddCharge, "Click on Add Charge Button");
					i++;
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addChargeTypes(List<String> data) throws Exception {
		try {
			for (int i = 0; i < data.size();) {
				String value = data.get(i);
				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
//					selectByIndex(MakeAPaymentOR.chargeType, i + 1, "Select Charge Type");
//					type(MakeAPaymentOR.Payment, value, "Enter Payment for particular charge");
//					click(MakeAPaymentOR.AddCharge, "Click on Add Charge Button");
					i++;
				}
			}
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}

	public void RecurringPayment(String Rent, String billDate) throws Exception {
		try {
//			click(SetUpPaymentsOR.Recurring, "Click on Recurring Payment");
//			type(SetUpPaymentsOR.FirstBillingDate, billDate, "Enter The first Billing Date");
//			click(SetUpPaymentsOR.Rent, "Close calender");
//			type(SetUpPaymentsOR.Rent, Rent, "Enter The Rent Charges");
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void RecurringPaymentWithFinalDate(String Rent, String billDate, String finalDate) throws Exception {
		try {
//			click(SetUpPaymentsOR.Recurring, "Click on Recurring Payment");
//			type(SetUpPaymentsOR.FirstBillingDate, billDate, "Enter The first Billing Date");
//			click(SetUpPaymentsOR.SetAmEndDate, "Click on Set an end Date");
//			type(SetUpPaymentsOR.FinalBillingDate, finalDate, "Enter The Final Billing Date");
//			click(SetUpPaymentsOR.Rent, "Close calender");
//			type(SetUpPaymentsOR.Rent, Rent, "Enter The Rent Charges");
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Logged into Rent moola Application Using QuickPay Credentials
	' Function Name        :     addAllChargeTypes
  
	'########################################################
*/	
	
	public void QPLogin(String email, String PostalCode) throws Exception {
		try {
//			click(QuickPayOR.RMQuickPay, "Click on RM Quick Pay Button");
//			type(QuickPayOR.QuickPayEmail, email, "Enter Email Id for login");
//			type(QuickPayOR.QuickPayConfirmEmail, email, "Enter Confirm Email Id for login");
//			type(QuickPayOR.QuickPayZipCode, PostalCode, "Enter Postal Code for login");
//			click(QuickPayOR.QPSubmit, "Click on Submit Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter QuickPay Property Details
	' Function Name        :     QPPropertyDetails

	'########################################################
*/	

	public void QPPropertyDetails(String property, String firstName, String lastName, String contactNumber,
			String residentUnit) {
		try {
			
//			type(QuickPayOR.FirstName, firstName, "Enter firstName");
//			type(QuickPayOR.LastName, lastName, "Enter lastName");
//			type(QuickPayOR.PhoneNumber, contactNumber, "Enter contactNumber");
//			type(QuickPayOR.SuiteNo, residentUnit, "Enter SuiteNo");
//			click(QuickPayOR.Continue, "Click on Continue Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Enter QuickPay Property Details with Customer
	' Function Name        :     QPPropertyDetailsWithCustomer
  
	'########################################################
*/	
	public void QPPropertyDetailsWithCustomer(String property, String firstName, String lastName, String contactNumber,
			String residentUnit, String Customer) throws Exception {
		try {
//			selectByIndex(QuickPayOR.ResidencyStatus, 1, "Select Property from Your property Drop down");
//			type(QuickPayOR.FirstName, firstName, "Enter firstName");
//			type(QuickPayOR.LastName, lastName, "Enter lastName");
//			type(QuickPayOR.PhoneNumber, contactNumber, "Enter contactNumber");
//			type(QuickPayOR.SuiteNo, residentUnit, "Enter SuiteNo");
//			type(QuickPayOR.CustomerID, Customer, "Enter Customer ID");
//			scrollingToElementofAPage(QuickPayOR.Continue);
//			click(QuickPayOR.Continue, "Click on Continue Button");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public boolean CompareStrings(String db, String webpage) {

		System.out.println("db " + db);
		System.out.println("webpage " + webpage);
		if (webpage.contains(db))
			return true;
		else
			return false;

	}

	public void ConfirmData(String Amount, String cardHolder, String cardNumber) {
		try {

			
			String Amount_Webpage = getText(ConfirmPageOR.Fees, "get Fee text");
			Amount_Webpage = Amount_Webpage.replace(",", "");
            Assert.assertEquals("Amounts are different", CompareStrings(Amount, Amount_Webpage));
			System.out.println("1");

			verifyTextUsingData(ConfirmPageOR.CardHolder, cardHolder);
			
			System.out.println("2");
			
			String cardNumberdata = getText(ConfirmPageOR.cardNumber, "get Fee text");
			CompareStrings(cardNumberdata.substring(15), cardNumber.substring(12));
			System.out.println("3");
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public void ConfirmData2(String Amount, String cardHolder, String cardNumber, String Street, String City,
			String Country, String PostalCode, String Email, String PhoneNumber, String SuiteNo) {
		try {

			String Amount_Webpage = getText(ConfirmPageOR.Fees, "get Fee text");
			Amount_Webpage = Amount_Webpage.replace(",", "");

			Assert.assertEquals("Amounts are different", CompareStrings(Amount, Amount_Webpage));
			System.out.println("1");

			verifyTextUsingData(ConfirmPageOR.CardHolder, cardHolder);
			
			System.out.println("2");
			
			String cardNumberdata = getText(ConfirmPageOR.cardNumber, "get Fee text");
			CompareStrings(cardNumberdata.substring(15), cardNumber.substring(12));
			System.out.println("3");
			
			verifyTextUsingData(ConfirmPageOR.Property, Street);
			
			verifyTextUsingData(ConfirmPageOR.PostalCode, PostalCode);
			
			verifyTextUsingData(ConfirmPageOR.Email, Email);

			/*
			 * Assert.assertTrue("PhoneNumber are different", CompareStrings(PhoneNumber,
			 * (explicitWaitForElement(ConfirmPageOR.PhoneNumber, driver)).getText()));
			 */
			verifyTextUsingData(ConfirmPageOR.PhoneNumber, PhoneNumber);

			/*
			 * Assert.assertTrue("SuiteNo are different", CompareStrings(SuiteNo,
			 * (explicitWaitForElement(ConfirmPageOR.SuiteNo, driver)).getText()));
			 */
			verifyTextUsingData(ConfirmPageOR.SuiteNo, SuiteNo);

		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*
	 * public void disableRecurringPayment(String message,String
	 * Recurringdate,String Rent, Class className) {
	 * 
	 * //ApplicationUtil.PropertySetUp(PostalCode,PhoneNumber,thisClass); try {
	 * click(PrimaryPaymentMethod.PaymentsLinkHeader, ),className,
	 * "Click on Payments Menu");
	 * if(isWebElementPresent(PaymentsOR.btnSetupRecurringPayments,)) {
	 * 
	 * click(PaymentsOR.btnSetupRecurringPayments, ),className,
	 * "Click on Setup Recurring Pyaments button");
	 * 
	 * type(SetUpPaymentsOR.recurringDate, "08/12/2017" ,
	 * "Enter The first Billing Date"); type(PaymentsOR.recurringchargesRent,
	 * Rent),className, "Enter Rent"); click(PaymentsOR.btnSave, ),className,
	 * "Click On Save Button"); click(PaymentsOR.disableButton, ),className,
	 * "Click on Disable Button"); click(PaymentsOR.disableRecurringPaymentsButton,
	 * ),className, "Click on Disable payments recurring button");
	 * verifyTextUsingData(PaymentsOR.paymentmessage, message),className,
	 * "Verify disable text");
	 * 
	 * }
	 * 
	 * else {
	 * 
	 * //click(PrimaryPaymentMethod.PaymentsLinkHeader, ),className,
	 * "Click on Payments Menu"); click(PaymentsOR.disableButton, ),className,
	 * "Click on Disable Button"); click(PaymentsOR.disableRecurringPaymentsButton,
	 * ),className, "Click on Disable payments recurring button");
	 * verifyTextUsingData(PaymentsOR.paymentmessage, message),className,
	 * "Verify disable text"); } } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 */
	/*'########################################################
	' Objective            :     Stop the Payment
	' Function Name        :     stopPayment
      
	'########################################################
*/	
	public void stopPayment(String message) throws Exception {
		try {
			click(MyPropertyOR.MyProperty, "Click on My Property");
			click(MyPropertyOR.StopPayments, "Click on Stop Payments");
			click(MyPropertyOR.StopPaymentsPopup, "Click on Stop Payments");
			/*
			 * Assert.assertTrue("Please stop the payment", CompareStrings(message,
			 * (explicitWaitForElement(MyPropertyOR.StopPaymentsUpdated,
			 * driver)).getText()));
			 */
			verifyTextUsingData(MyPropertyOR.StopPaymentsUpdated, message);
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Change the Password
	' Function Name        :     changePassword
 
	'########################################################
*/	
	public void changePassword(String currentPassword, String newPassword, String confirmPassword, String message)
			throws Exception {
		try {
			type(ProfilePageOR.CurrentPassword, currentPassword, "Enter currentPassword");
			type(ProfilePageOR.NewPassword, newPassword, "Enter newPassword");
			type(ProfilePageOR.ConfirmPassword, confirmPassword, "Enter confirmPassword");
			click(ProfilePageOR.SavePassword, "Click on Save");
			/*
			 * Assert.assertTrue("Password not updated, please enter valid data",
			 * CompareStrings(message, getText(ProfilePageOR.PasswordUpdated, "driver")));
			 */
			verifyTextUsingData(ProfilePageOR.PasswordUpdated, message);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}
	/*'########################################################
	' Objective            :     Change the Mobile Number
	' Function Name        :     changeMobileNumber
   
	'########################################################
*/	
	public void changeMobileNumber(String NewPhoneNumber, String message) throws Exception {
		try {
			type(ProfilePageOR.PhoneNumber, NewPhoneNumber, "Enter new phone number");
			click(ProfilePageOR.SavePhone, "Click on Save");

			verifyTextUsingData(ProfilePageOR.ProfileUpdated, message);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public void saveSubscription(String message) throws Exception {
		try {
			click(ProfilePageOR.SubscriptionCheckBox, "Click on subscription checkbox");
			click(ProfilePageOR.SaveWithCheckbox, "Click on Save");
			/*
			 * Assert.assertTrue("subscription not updated, please check the subscription ",
			 * CompareStrings(message,
			 * (explicitWaitForElement(ProfilePageOR.SubscriptionsUpdated,
			 * driver)).getText()));
			 */

			verifyTextUsingData(ProfilePageOR.SubscriptionsUpdated, message);
		} catch (Throwable e) {

			e.printStackTrace();
		}
	}

	public void addChargeTypesforRecurring(String[] chargeTypes, ResultSet chargeTypeValues) throws Exception {
		try {
			for (int i = 0; i < chargeTypes.length;) {
				String value = chargeTypeValues.getString(chargeTypes[i]);
				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
					By Charge = By.xpath("//input[contains(@id,'recurring-charge:" + i + ":recurring-amount')]");

					try {
						type(Charge, value, "Enter Payment for particular charge");
					} catch (Throwable e) {

						e.printStackTrace();
					}

					i++;
				}
			}
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	/*
	 * public void addChargeTypesforRecurring(HashMap<String,String> data) throws
	 * Exception { try { int i; for ( i = 0; i < data.size();) { //String value =
	 * chargeTypeValues.getString(chargeTypes[i]); if ((value.equals(null)) ||
	 * (value.equals("0")) || (value.isEmpty())) { i++; } else { By Charge =
	 * By.xpath("//input[contains(@id,'recurring-charge:" + i +
	 * ":recurring-amount')]");
	 * 
	 * 
	 * type(Charge, data.get(i), "Enter Payment for particular charge");
	 * 
	 * i++; }
	 * 
	 * } catch (Throwable e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	public void addChargeTypesforRecurring(List<String> data) throws Exception {
		try {
			int i;
			for (i = 0; i < data.size();) {
				String value = data.get(i);
				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
					By Charge = By.xpath("//input[contains(@id,'recurring-charge:" + i + ":recurring-amount')]");

					type(Charge, value, "Enter Payment for particular charge");

					i++;
				}
			}
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void addChargeTypesforRecurring(String chargeType, String chargeValue) {

		try {
			// By Charge = By.xpath("//input[contains(@id,'recurring-charge:" + i +
			// ":recurring-amount')]");
			// type(Charge, chargeValue, "Enter Payment for particular charge");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addChargeTypesforFirstTime(List<String> data) {
		try {
			for (int i = 0; i < data.size();) {
				String value = data.get(i);

				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
					By FirstTimeCharge = By.xpath("//input[contains(@id,'onetime-charge:" + i + ":onetime-amount')]");

					type(FirstTimeCharge, value, "Enter Payment for particular charge");

					i++;
				}
			}
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void addChargeTypesforFirstTime(String[] firstTimechargeTypes, ResultSet firstTimechargeTypeValues) {
		try {
			for (int i = 0; i < firstTimechargeTypes.length;) {
				String value;

				value = firstTimechargeTypeValues.getString(firstTimechargeTypes[i]);

				if ((value.equals(null)) || (value.equals("0")) || (value.isEmpty())) {
					i++;
				} else {
					By FirstTimeCharge = By.xpath("//input[contains(@id,'onetime-charge:" + i + ":onetime-amount')]");

					type(FirstTimeCharge, value, "Enter Payment for particular charge");

					i++;
				}
			}
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	public void addChargeTypesforFirstTime(String firstTimechargeTypes, String firstTimechargeTypeValues) {
		try {

			/*
			 * By FirstTimeCharge = By.xpath("//input[contains(@id,'onetime-charge:" + i +
			 * ":onetime-amount')]");
			 * 
			 * type(FirstTimeCharge, firstTimechargeTypeValues,
			 * "Enter Payment for particular charge");
			 */
		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	

}
