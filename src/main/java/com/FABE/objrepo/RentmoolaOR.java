package com.FABE.objrepo;

import org.openqa.selenium.By;


public class RentmoolaOR 
{

public static  class HomePageOR
	{
		
		public static final By SignUp       =By.xpath(".//a[text()='Sign Up']");
		public static final By LogIn =By.xpath( "//a[contains(.,'Log In')]");
		public static final By PaymentMethods =By.xpath( "//a[contains(.,'Payment Methods')]");
		public static final By makePayment =By.xpath( "//a[text()='Make A Payment']");
		public static final By paymentMethod=By.xpath("//*[@id='j_idt288']/div[1]/div[2]/a/div");
	}
	public static class SignUpOR 
	{
		
		public static final By FirstName=By.xpath(".//input[@id='signup:firstname'][@placeholder='First Name']");
		public static final By LastName	=By.xpath(".//input[@id='signup:lastname'][@placeholder='Last Name']");
		public static final By GmailEmail =By.xpath("//input[@type='email']");
		public static final By GmailPassword=By.xpath("//input[@type='password']");
		public static final By Email=By.xpath(".//input[@id='signup:email'][@placeholder='Email']");
		public static final By ReEnterPassword =By.xpath( "//form[@id='signup']/div[5]/div/div[contains(.,'Re-enter your password')]");
		public static final By PasswordLengthValidation =By.xpath( "//form[@id='signup']/div[5]/div/div[contains(.,'Password must be at least 8 characters long')]");
		public static final By PhoneNumber=By.xpath(".//*[@id='signup:phone']");
		public static final By Password=By.xpath(".//input[@id='signup:password'][@placeholder='Password']");
		public static final By ConfirmPassword=By.xpath(".//input[@id='signup:confirmPassword'][@placeholder='Confirm Password']");
		public static final By SignUpButton =By.xpath("//input[@class='btn btn-lg btn-primary btn-block'][@value='Sign Up']");
		public static final By FaceBookButton=By.xpath("//a[@class='button facebook radius tiny expand standalone'][@href='#']");
		public static final By PasswordMustMatch=By.xpath("//form[@id='signup']/div[5]/div/div[contains(.,'Passwords must match')]");
        public static final By RentMoolaMailLink=By.xpath("//span[starts-with(.,'Confirm Your Email')]");
		public static final By Maildeletion     =By.xpath("(//div[@class='asa']//div[@class='ar9 T-I-J3 J-J5-Ji'])[position()=2]");
		public static final By ConfirmMail     =By.xpath("//a[contains(.,'Confirm Email')]");
		public static final By UseAnotherAccount =By.xpath("//p[contains(.,'Use another account')]");
		public static final By NextButton=By.xpath("//span[contains(.,'Next')]");
		public static final By reSignup =By.xpath( "//form[@id='signup']/div[2]/div/div[contains(text(),'The email address you entered is already registered with RentMoola')]");
		public static final By ThreeMaildeletion    =By.xpath("//div[@class='asa']//div[@class='ar9 T-I-J3 J-J5-Ji']");
		public static final By RecurringPaymentConfirmation=By.xpath("//span[starts-with(.,'Recurring Payments Confirmation')]");
    	public static final By CheckBox1     =By.xpath("(//div[@role='checkbox'])[1]");
    	public static final By CheckBox2    =By.xpath("(//div[@role='checkbox'])[2]");
    	public static final By CheckBox3     =By.xpath("(//div[@role='checkbox'])[3]");
    	
    	public static final By ForgotPasswordLink=By.xpath("//*[@id='loginForm']/div/a[@data-cbox-title='Reset Password']");
	}
		
	public static class ConfirmationOR 
	{
		public static final By ConfirmationMessage     =By.xpath("//h4[contains(.,'Your email has been successfully confirmed!')]");
		
		//old one
		public static final By ConfirmationLoginButton=By.xpath("//a[contains(.,'Log In')]");
		public static final By PaymentConfirmation=By.xpath("//span[contains(.,'Payment Confirmation')][1]");

	}
	public static class LoginOR 
	{

		public static final By Username=By.xpath("//input[@id='login:username']");
		public static final By Password=By.xpath( "//input[@id='login:password']");
		public static final By Login   =By.xpath("//input[@type='submit']");
		public static final By RentMoolaPaymentMail=By.xpath("//span[starts-with(.,'Start Making Payments')]");//it is present in Email.
		public static final By LogInButton=By.xpath("//a[contains(.,'Log In')]");//it is present in Email start making Payments
		public static final By Rewards=By.xpath( "(//a[contains(.,'Rewards')])[2]");
		public static final By MoolaPerks=By.xpath( "//h1[contains(.,'MoolaPerks')]");
		public static final By ForgotPassword=By.xpath("//span[contains(text(),'Forgot your password?')]");
		public static final By ForgotEmail=By.xpath("//input[contains(@name,':email')]");
		public static final By ResetPassword=By.xpath("//a[text()='Reset Password']");
		public static final By NewPassword=By.xpath("//input[@type='password'][contains(@name,':newPassword')]");
		public static final By ConfirmPassword=By.xpath("//input[@type='password'][contains(@name,':confirmPassword')]");
		public static final By ContinueMyAccount=By.xpath("//a[contains(text(),'Continue To My Account')]");
		public static final By PasswordResetUpdate=By.xpath("//h4[contains(text(),'Your password has been reset')]");


	}
	
	public static class ManagerOR
	{
		public static final By PendingApprovals=By.xpath( "//a[@href='/manager/approvals' and contains(.,'Review Pending Approvals')]");
		public static final By SearchButton =By.xpath( "//i[@class='fa fa-search']");
		public static final By ApproveButton =By.xpath( "//input[@type='submit' and @value='Approve']");
		public static final By VerifyApproveMessage=By.xpath("//span[contains(.,' Tenant approved. The tenant will now be able to make payments from their RentMoola account.')]");
		public static final By LogOut               =By.xpath("((//a[text()='Log Out'])[3])");
		public static final By LogOutMenu =By.xpath( "//a[@href='/manager/settings/details' and contains(.,'My Account')]");
		public static final By DashBoard =By.xpath( "//a[@class='top-bar-link active' and contains(.,'Dashboard')]");
		public static final By Invoices =By.xpath( "((//a[contains(.,'Resident Invoices')])[3])");
		public static final By ResidentInvoiceButton =By.xpath("//a[text()='Invoice Resident']");
		public static final By ResidentSearch=By.xpath( ".//input[contains(@id,'tenantname')]");
		public static final By ResidentSearchButton=By.xpath("//a[contains(@id,'searchTenant')]");
		public static final By InvoiceAmount=By.xpath("//input[contains(@id,'chargeAmount')]");
		public static final By InvoiceChargeType=By.xpath("//select[contains(@id,'chargeType')]");
		public static final By InvoiceSend=By.xpath("//a[contains(.,'Send')]");
		public static final By InvoiceTextField=By.xpath("//textarea[contains(@id,'paymentNote')]");
		public static final By VerifyEmail=By.xpath("//span[contains(.,'Email sent')]");
		public static final By ResidentsSearchTextbox=By.xpath("//*[@placeholder='Search tenants, properties, managers, transactions...']");
		public static final By SearchedResidentLink=By.xpath("//span[contains(.,'Sahil Thareja')]");
		public static final By ChargeDateTextbox=By.xpath("//span[contains(.,'Sahil Thareja')]");
	}
	
	public static class PrimaryPaymentMethod
	{
		  public static By PaymentsLinkHeader =By.xpath( "//*[@class='navbar-collapse justify-content-sm-center']//a[contains(.,'Payments')]");
		  public static final By ManagePaymentMethods =By.xpath( "//a[@href='/resident/payments/methods']");
		  public static final By AddACard =By.xpath( "//p[contains(text(),'Add New MasterCard')]");
		  public static final By SelectTheNonPrimaryCard =By.xpath( "(//div[contains(@class,'card payment-method')])[position()=2]");
		  public static final By SelectThePrimaryCard =By.xpath( "//span[@class='badge badge-primary text-uppercase' and contains(.,'Primary')]");
		  public static final By DeleteButton =By.xpath( "//a[contains(.,'Delete')]");
		  public static final By DeleteMessage =By.xpath( "//div[contains(@class,'alert alert-info alert-dismissible fade show')]");
		  public static final By DeletePrimaryCardMessage =By.xpath( "//div[contains(@class,'alert alert-danger alert-dismissible fade show')]");
		  public static final By PaymentMethods =By.xpath( "//a[text()='Payment Methods']");
		  public static final By SkipButton =By.xpath( "//a[contains(text(),'Skip')]");
		  public static final By SkipNextRecurringPayment =By.xpath( "//input[@value='Skip Next Recurring Payment']");
		  public static final By SkipMessage =By.xpath( "//div[@class='alert alert-info alert-dismissible fade show']");
		  public static final By ViewReceipt =By.xpath("(//a[contains(.,'View Receipt')])[1]");
		  public static final By PaymentOnReceipt=By.xpath("//p[@class='h1 card-title text-center py-3 mb-0']//strong");
		  public static final By PaymentOnDashboard=By.xpath("(//table[@class='table table-striped table-responsive-sm'])[1]//tr[1]//td[2]");
		  public static final By FirstCard =By.xpath("//form[contains(@id,'j_idt')]/div[2]/div[1]/a/div/div[2]/div/div/p");
		  public static final By SecondCard =By.xpath("//form[contains(@id,'j_idt')]/div[2]/div[3]/a/div/div[2]/div/div/p");
		  public static final By MakePrimary =By.xpath("//a[contains(.,'Make Primary')]");
		  public static final By Delete =By.xpath("//div[3]/div/div/div[3]/a[2]");
		  public static final By PrimaryCard =By.xpath("//span[text()='Primary']");
		  public static final By ChangeCardMessage=By.xpath("//span[contains(.,'You have successfully set a new primary payment method.')]");
			
		 }
	
	public static class WelcomeOR 
	{
		public static final By SetUpPaymentsNowButton=By.xpath("//a[contains(.,'Set Up Payments Now')]");	
		public static final By PostalCode            =By.xpath("//input[contains(@id,'postalCode')]");
		public static final By SearchButton          =By.xpath("//input[@value='Search']");
		public static final By PropertySignUpButton=By.xpath("//a[contains(.,'Sign Up')]");
		
	}
	public static class SetUpPaymentsOR
	{
		public static final By ResidentType       =By.xpath("//select[contains(@id,'residentType')]");
		public static final By ResidencyStatus    =By.xpath("//select[contains(@id,'residentStatus')]");
		public static final By PhoneNumber         =By.xpath("//input[contains(@id,'phone')]");
		public static final By Continue            =By.xpath("//input[@type='submit' and @value='Continue']");
		public static final By Recurring          =By.xpath("//label[contains(.,'Recurring Payments')]");
		public static final By OneTime          =By.xpath("//label[contains(.,'One Time Payments')]");
		public static final By FirstBillingDate   =By.xpath("//label[contains(.,'First Billing Date')]/following-sibling::div//input");
		public static final By SetAmEndDate       =By.xpath("//span[contains(@class,'custom-control-description')]");
		public static final By FinalBillingDate  =By.xpath("//input[contains(@id,'end-billing-date')][contains(@class,'form-control input-date final-billing-date-field')]");
		public static final By Closecalender      =By.xpath("//label[contains(.,'Rent')][@class='custom-control-description']");
		public static final By Rent               =By.xpath("//input[contains(@name,'recurring-charge:0')][1]");
		public static final By OneTimePaymentsButton =By.xpath("//label//div[contains(.,'One Time Payments')]");
		public static final By Confirm            =By.xpath("//input[@type='submit' and @value='Confirm']");
		public static final By PendingApprovalMessage =By.xpath("//h4[contains(.,'You are now pending approval')]");
		public static final By ViewMyAccount           =By.xpath("//a[contains(.,'View My Account')]");
		public static final By MakeAPaymentDisabled    =By.xpath("//span[contains(@class,'disabled')][contains(.,'Make A Payment')]");
		public static final By LogOutButton            =By.xpath("//a[contains(.,'Log Out')]");
		public static final By MakeAPaymentEnabled     =By.xpath( "(//a[@class='btn btn-lg btn-danger mt-1'][ //a[contains(.,'Make A Payment')]])[1]");
		public static final By CreditCard              =By.xpath("//p[contains(.,'Add New Credit Card')]");
		public static final By CardNumber              =By.xpath("//input[contains(@class,'form-control cc-number-field')]");
		public static final By ExpiryMonth             =By.xpath("//select[contains(@class,'form-control cc-expiry-month-field')]");
		public static final By ExpiryYear         =By.xpath("//select[contains(@class,'form-control cc-expiry-year-field')]");
		public static final By SecurityNumber          =By.xpath("//input[contains(@class,'form-control cc-security-field')]");
		public static final By CardHolder         =By.xpath("//input[contains(@class,'form-control cc-holder-field')]");       
		public static final By CountryField       =By.xpath("//select[contains(@class,'form-control') and contains(@id,'Country')]");
		public static final By StateField         =By.xpath("//select[contains(@class,'form-control') and contains(@id,'Province')]");
		public static final By ZipCode            =By.xpath("//input[contains(@id,'billingPostalCode')]");
		public static final By Street             =By.xpath("(//input[contains(@id,'billingStreet')])[position()=1]");
		public static final By BillingCity        =By.xpath("//input[contains(@id,'billingCity')]");
		public static final By DepositeFirstCharge =By.xpath("//input[contains(@id,'onetime-charge:0')]");
		public static final By DebitCard            =By.xpath("//p[contains(.,'Add New MasterCard/Visa Debit')]");
		public static final By UnpaidInvoiceMessage            =By.xpath("//h4[contains(.,'You have an unpaid invoice')]");
		public static final By ClickHere =By.xpath("//a[contains(.,'here')]");
		public static final By PayNow =By.xpath("//a[contains(.,'Pay Now')]");
		public static final By SetUpPayments =By.xpath("//a[contains(.,'Set Up Payments')]");
		public static final By MyProperty =By.xpath("//*[@class='navbar-collapse justify-content-sm-center']//a[contains(.,'My Property')]");
		public static final By StopPayments =By.xpath("//a[contains(.,'Stop Payments')]");
		public static final By StopPaymentMessage =By.xpath("//div[contains(@class,'alert alert-info alert-dismissible fade show')]");
		public static final By StopPaymentsPopUp =By.xpath( "//input[@type='submit'][@value='Stop Payments']"); 
		public static final By ManagePayments =By.xpath("//a[contains(.,'Pay Now')]");
		public static final By Edit =By.xpath("//a[contains(.,'Edit')]");
		public static final By recurringDate =By.xpath("//input[contains(@name,'billing-date')]");
		public static final By Save =By.xpath("//a[contains(.,'Save')]");
		public static final By InvalidDate =By.xpath("//div[contains(text(),'You already have an actively scheduled payment for this date.')]");
		public static final By UseMyAddress=By.xpath("//span[contains(.,'Use my address')]");
		public static final By myLastPayment=By.xpath("//p[contains(.,'Scheduled')]");
		public static final By ScheduledPayment=By.xpath("//div[text()='You already have an actively scheduled payment for this date.']");
		public static final By totalAmount=By.xpath("//tr[contains(.,'Total')]/td[contains(.,'CAD')]");
		public static final By repeatpreviouspayment=By.xpath("//p[contains(.,'Repeat my previous payment of')]");
		
	}
	public static class MakeAPaymentOR
	{
		public static final By chargeType =By.xpath("//select[contains(@id,'chargeType') and contains(@class,'form-control form-control-lg chargeType')]");
		public static final By Payment    =By.xpath("//input[contains(@class,'form-control form-control-lg input-price')][contains(@id,'chargeAmount')]");	
		public static final By AddCharge  =By.xpath("//input[contains(@class,'addCharge')]");
		public static final By SchedulePaymentRadioButton=By.xpath("//span[contains(.,'Make this payment on a future date?')]");
		public static final By SchedulePaymentDate=By.xpath("//input[contains(@id,'payInFutureDate')]");
		public static final By SuccessImage=By.xpath("//img[@class='img-fluid card-img-top card-img-bottom']");
		public static final By ReturnToMyAccount =By.xpath( "//a[contains(.,'Return To My Account')]");
		public static final By MainPaymentButton =By.xpath( "//a[contains(.,'Payment Methods')]");
		public static final By MakeAPayment =By.xpath( "//div[@class='list-group']//div[contains(.,'Make A Payment')]");
		public static final By VerifyCreditcardPage =By.xpath( "//div[@class='container']//h1");
	}
	
	public static class QuickPayOR
	{
		public static final By RMcheck=By.xpath("//div[contains(@class,'card payment-method')]//p[contains(.,'Add New ACH')]");
		public static final By SelecteBank=By.xpath(".//select[contains(@id,'bankSelect')]");
		public static final By CustomerID=By.xpath(".//input[contains(@id,'quickpay:field')]");
		public static final By InstituitionNumber=By.xpath(".//input[contains(@id,'ddInstituteNumber')]");
		public static final By DeleteButton=By.xpath("//a[text()='Delete']");
		public static final By BackButton=By.xpath("//input[contains(@value,'Back')]");
		public static final By RMQuickPay =By.xpath("//a[contains(.,'RM QuickPay')]");
		public static final By QuickPayEmail=By.xpath("//input[@id='quickpay:email']");
		public static final By QuickPayConfirmEmail=By.xpath("//input[@id='quickpay:confirmEmail']");
		public static final By QuickPayZipCode=By.xpath("//input[@id='quickpay:postalCode']");
		public static final By QPSubmit=By.xpath("//input[@value='Submit' and @type='submit']");
		public static final By ResidencyStatus =By.xpath( "//select[@id='quickpay:property']");
		public static final By FirstName=By.xpath("//input[contains(@id,'firstname')]");
		public static final By LastName=By.xpath("//input[contains(@id,'lastname')]");
		public static final By PhoneNumber=By.xpath("//input[contains(@id,'contactNumber')]");
		public static final By SuiteNo=By.xpath("//input[contains(@id,'residentUnit')]");
		public static final By Continue=By.xpath("//input[@value='Continue' and @type='submit']");
		public static final By SuccessContinue=By.xpath("//a[contains(.,'Continue')]");
		public static final By CreateAccount=By.xpath("//h4[contains(.,'Create your RentMoola account today!')]");
		public static final By Password=By.xpath("//input[@id='quickpay-signup:password']");
		public static final By ConfirmPassword=By.xpath("//input[@id='quickpay-signup:confirmPassword']");
		public static final By PropertyNotListedMessage=By.xpath("//h4[contains(.,'my property listed?')]");
		public static final By FirstNameErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter your first name')]");
		public static final By LastNameErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter your last name')]");
		public static final By PhoneNoErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter a phone number')]");
		public static final By CreditCardErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter your credit card number')]");
		public static final By ExpiryMonthErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Select your expiry month')]");
		public static final By ExpiryYearErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Select your expiry year')]");
		public static final By CardHolderNameErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter cardholder name')]");
		public static final By CVVErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter a CVV')]");
		public static final By CountryErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Select your country')]");
		public static final By ZipCodeErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter a billing ZIP/postal code')]");
		public static final By StreetErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter a billing street')]");
		public static final By CityErrorMessage=By.xpath("//div[@class='invalid-feedback'][contains(.,'Enter a billing city')]");
		public static final By Backbutton=By.xpath("//a[contains(.,'Back')]");
	}
	
public static class QuickPayBankOR 
{
		
		public static final By TransitNumber =By.xpath( ".//input[contains(@id,'TransitNumber')]");
		public static final By RoutingNumber =By.xpath( ".//input[contains(@id,'RountingNumber')]");
		public static final By AccountNumber =By.xpath( ".//input[contains(@id,'AccountNumber')]");
        public static final By Firstname =By.xpath( ".//input[contains(@id,'billingFirstname')]");
		public static final By Lastname =By.xpath( ".//input[contains(@id,'billingLastname')]");
	    public static final By WriteBankName =By.xpath( "//input[contains(@name,'BankName')]");
		public static final By Countryname =By.xpath( ".//select[contains(@id,'billingCountry')]");
		public static final By Provincename =By.xpath(".//select[contains(@id,'billingProvince')]");
        public static final By Phone =By.xpath(".//input[contains(@id,'billingPhone')]");
        public static final By Street1 =By.xpath(".//input[contains(@id,'billingStreet1')]");
        public static final By Street2 =By.xpath(".//input[contains(@id,'billingStreet2')]");
        public static final By City =By.xpath(".//input[contains(@id,'City')]");
        public static final By Postal =By.xpath(".//input[contains(@id,'billingPostalCode')]");
        public static final By Agreebutton =By.xpath("(//span[contains(@class,'custom-control-description')])[position()=2]");
}

	public static class InvalidOR
	{
		  public static final By Transit =By.xpath("//div[contains(.,'Must be 5 digits long')][@class='invalid-feedback']" );
          public static final By AccNumber =By.xpath("//div[contains(.,'Length is greater than allowable maximum ')][@class='invalid-feedback']");
          public static final By InvalidBank =By.xpath("//div[contains(.,'Select a bank or credit union from the given list')][@class='invalid-feedback']" );
		  public static final By FieldEmpty =By.xpath("(//div[contains(.,'This field cannot be empty')][@class='invalid-feedback'])[1]");
		  public static final By RoutNumber =By.xpath("//div[contains(.,'Length is less than allowable minimum of')][@class='invalid-feedback']");
		  public static final By InvalidRouting9 =By.xpath("//input[contains(@class,'form-control dd-routing-number is-invalid')]//following-sibling::div" );
		  public static final By InvalidAccount7 =By.xpath("//input[contains(@class,'form-control dd-account-number is-invalid')]//following-sibling::div" );
          public static final By InvalidDate =By.xpath( "//div[contains(.,'Your final recurring billing date must be after your next recurring billing date')][@class='invalid-feedback']");
	}

	public static class ConfirmPageOR
	{
		public static final By Fees=By.xpath("//td//div[contains(.,'CAD')]");
		public static final By CardHolder=By.xpath("//td[contains(text(),'Card/Account Holder')]//following-sibling::td");
		public static final By cardNumber=By.xpath("//td[contains(text(),'Card Number')]//following-sibling::td");
		public static final By Property=By.xpath("//td[contains(text(),'Property')]//following-sibling::td");
		public static final By Street=By.xpath("//td[contains(text(),'Street')]//following-sibling::td");
		public static final By City=By.xpath("//td[contains(text(),'City')]//following-sibling::td");
		public static final By Province=By.xpath("//td[contains(text(),'Province')]//following-sibling::td");
		public static final By Country=By.xpath("//td[contains(text(),'Country')]//following-sibling::td");
		public static final By PostalCode =By.xpath("//td[contains(text(),'Postal Code')]//following-sibling::td");
		public static final By Name =By.xpath("//td[contains(text(),'Name')]//following-sibling::td");
		public static final By Email =By.xpath("//td[contains(text(),'Email')]//following-sibling::td");
		public static final By PhoneNumber =By.xpath("//td[contains(text(),'Phone Number')]//following-sibling::td");
		public static final By SuiteNo =By.xpath("//td[contains(text(),'Unit/Suite No.')]//following-sibling::td");
	}
	
	public static class MyPropertyOR
	 {
	  public static final By MyProperty=By.xpath("//nav[2]/div/ul/li[6]/a/div");
	  public static final By StopPayments=By.xpath("//a[@data-target='#stop-payments']");
	  public static final By StopPaymentsPopup=By.xpath("//input[@value='Stop Payments']");
	  public static final By StopPaymentsUpdated=By.xpath("//span[contains(.,'Your payments have been stopped and your RentMoola account is no longer associated with the property.')]");
	  public static final By SetUpPaymentsbtn=By.xpath("//a[contains(.,'Set Up Payments')]");
	  
	 }
	
	
	public static class PaymentsOR
	 {
	  public static final By ManagePaymentMethods=By.xpath("//form[@id='j_idt321']/div/div[2]/div/a[2]/div");
	  public static final By RMcheck=By.xpath("//div[contains(@class,'card payment-method')]//p[contains(.,'Add New ACH')]");
	  public static final By RoutingNumber =By.xpath( "//input[contains(@id,'RountingNumber')]");
	  public static final By AccountNumber =By.xpath( ".//input[contains(@id,'AccountNumber')]");
	  public static final By Firstname =By.xpath( ".//input[contains(@id,'billingFirstname')]");
	  public static final By Lastname =By.xpath( ".//input[contains(@id,'billingLastname')]");
	  public static final By WriteBankName =By.xpath( "//input[contains(@name,'BankName')]");    
	  public static final By Countryname =By.xpath( ".//select[contains(@id,'billingCountry')]");
	  public static final By Provincename =By.xpath(".//select[contains(@id,'billingProvince')]");
	  public static final By Phone =By.xpath(".//input[contains(@id,'billingPhone')]");
	  public static final By Street1 =By.xpath(".//input[contains(@id,'billingStreet1')]");
	  public static final By Street2 =By.xpath(".//input[contains(@id,'billingStreet2')]");
	  public static final By City =By.xpath(".//input[contains(@id,'City')]");
	  public static final By Postal =By.xpath(".//input[contains(@id,'billingPostalCode')]");
	  public static final By Agreebutton =By.xpath("(//span[contains(@class,'custom-control-description')])[position()=2]");
	  public static final By disableButton=By.xpath("//a[contains(.,'Disable')]");
      public static final By disableRecurringPaymentsButton=By.xpath("//input[contains(@value,'Disable Recurring Payments')]");
	  public static final By paymentmessage=By.xpath("//span[text()='Your recurring payments have been successfully disabled.']");
	  public static final By btnSetupRecurringPayments=By.xpath("//a[contains(.,'Setup Recurring Payments')]");
	  public static final By txtNewRecurringDate=By.xpath("//input[@id='j_idt284:billing-date']");
	  public static final By recurringchargesRent=By.xpath("//input[@id='j_idt284:recurring-charge:0:recurring-amount']");
	  public static final By btnSave=By.xpath("//a[contains(.,'Save')]");
	  public static final By PrimaryCard=By.xpath("//span[text()='Primary']");
	  public static final By Delete=By.xpath("//a[contains(text(),'Delete')])[2]");
		 
	
	 }
	public static class ProfilePageOR
	 {
		public static final By Profile=By.xpath("//nav[2]/div/ul/li[7]/a/div");
		public static final By PhoneNumber=By.xpath("//input[contains(@id,':phone')]");
		public static final By SavePhone=By.xpath("//form[1]/div/div[3]/input");
		public static final By ProfileUpdated=By.xpath("//span[contains(.,'Your profile has been updated.')]");	
		public static final By CurrentPassword=By.xpath("//input[contains(@id,':currentPassword')]");
		public static final By NewPassword=By.xpath("//input[contains(@id,':newPassword')]");
		public static final By ConfirmPassword=By.xpath("//input[contains(@id,':confirmPassword')]");
		public static final By SavePassword=By.xpath("//form[2]/div/div[3]/input");
		public static final By PasswordUpdated=By.xpath("//span[contains(.,'Your password has been updated.')]");
		public static final By SubscriptionCheckBox=By.xpath("//form/div/div[1]/div/div/label");
		public static final By SaveWithCheckbox=By.xpath("//form/div/div[2]/input");
		public static final By SubscriptionsUpdated=By.xpath("//span[contains(.,'Your subscriptions have been updated.')]"); 
	 }

}


