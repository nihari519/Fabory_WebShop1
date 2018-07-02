package com.FABE.objrepo;

import org.openqa.selenium.By;


public class FaboryOR {

	public static class CommonOR {
/*
		public static final By	windowTitile			=By.xpath( "//div[@id='windowHeaderPanel']/table/tbody/tr/td[1]/div");
		public static final By	windowClose			=By.xpath( "//div[@id='windowHeaderPanel']/table/tbody/tr/td[2]/div/img");
		public static final By selectbox=By.xpath(  "//select[@class='promoListBox']");*/
		public static final By  PopupClose=By.xpath("//button[@id='cboxClose']");
		public static final By  Minicart_icon=By.xpath("//*[@id='navbar-primary']/descendant::a[@class='mini-cart-link  zmdi zmdi-shopping-cart']");
		public static final By  Minicart_Value=By.xpath("//*[@id='navbar-primary']/descendant::a[@class='mini-cart-link  zmdi zmdi-shopping-cart']/span[1]");
		
	}


	public static  class HomePageOR
	{
		
		public static final By LogIn =By.xpath( "//a/descendant::span[contains(text(),'login / signup')]");
		public static final By Register=By.cssSelector("div.register-benefits-container > a");
		public static final By CoutryGlobeIcon=By.xpath("//*[@id='navbar-secondary']/div/descendant::a/i[@class='zmdi zmdi-globe']");
		public static final By GlobeNLCountry=By.xpath("//*[@id='navbar-secondary']/descendant::a[@id='NL__netherlands__orgunit']");
		public static final By GlobeNLLangEng=By.xpath("//*[@id='navbar-secondary']/descendant::a[@class='language-to-select'][@id='language__en_NL']");
		
		public static final By ProductsMenu=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-toggle='dropdown']/span[text()='Products']");
		public static final By ProductsMenuFastenersCategory=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-toggle='dropdown']/span[text()='Products']/parent::a/following-sibling::ul/descendant::li[text()='Fasteners']");
		public static final By ProductsMenuIndustrialsCategory=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-toggle='dropdown']/span[text()='Products']/parent::a/following-sibling::ul/descendant::li[text()='Industrials']");
		public static final By ProductsMenu_Bolts=By.xpath("//*[@id='navbar-primary']/descendant::a[text()='Bolts']");
		
		
		public static final By FastenersSubcategories=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-toggle='dropdown']/span[text()='Products']/parent::a/following-sibling::ul/descendant::li[text()='Fasteners']/following-sibling::li");
		public static final By IndustrialsSubcategories=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-toggle='dropdown']/span[text()='Products']/parent::a/following-sibling::ul/descendant::li[text()='Industrials']/following-sibling::li");
		
		public static final By HeaderPhoneNumber=By.cssSelector("#navbar-secondary > div > div > div:nth-child(1) > a");
		public static final By KnowledgeCenter=By.xpath("//*[@id='navbar-primary']/descendant::ul/li[3]/a[text()='Knowledge Center']");
		public static final By FastenersTab=By.xpath("//*[@id='homepage-fasteners-tab']");
		public static final By IndustrialsTab=By.xpath("//*[@id='homepage-industrials-tab']");
		public static final By SearchTexbox=By.cssSelector("#search");
		public static final By SearchButton=By.cssSelector("#search-btn > button");
		public static final By CartIcon=By.cssSelector("#navbar-primary > div > div.navbar-extra > div.yCmsContentSlot.cart.nav-cart > a");
		public static final By Slider1=By.cssSelector("#slider-1 > div > div");
		public static final By ChildSlider1=By.cssSelector("#section-home-top > div > div > div:nth-child(1) > div > div > div > div.row.mt-4.visible-lg > div:nth-child(1) > div");
		public static final By ChildSlider2=By.cssSelector("#section-home-top > div > div > div:nth-child(1) > div > div > div > div.row.mt-4.visible-lg > div:nth-child(2) > div");
		public static final By PopularProductsHeader=By.xpath("//*[@id='section-home-populair']/descendant::h2[text()='Popular Products']");
		public static final By PopularProduct1=By.xpath("//*[@id='section-home-populair']/descendant::a[1]/figure/figcaption/p");
		public static final By HomeNotifcation=By.xpath("//div[@class='alert alert-warning alert-dismissable']");
	}
	public static class SignUpOR {
		//personal details
		public static final By EmailAddress =By.xpath(".//input[@id='register_email']");
		public static final By EmailRequiredLabel=By.xpath("//input[@id='register_email']/following-sibling::label");
		
		public static final By ConfirmEmail=By.xpath(".//input[@id='register.confirm.email']");
		public static final By ConfEmailRequiredLabel=By.xpath("//input[@id='register.confirm.email']/following-sibling::label");
		
		public static final By FirstName=By.xpath(".//input[@id='register_firstName'][@name='firstName']");
		public static final By FirstNameRequiredLabel=By.xpath("//input[@id='register_firstName']/following-sibling::label");
		
		public static final By LastName	=By.xpath(".//input[@id='register_lastName'][@name='name']");
		public static final By LastNameRequiredLabel=By.xpath("//input[@id='register_lastName']/following-sibling::label");
		
		public static final By ExistingCmpyChkbox=By.xpath(".//input[@id='register_existingCompany']");
		public static final By ExistingCmpyLabel=By.xpath(".//input[@name='_existingCompany']']");
		
		//company details
		public static final By CompanyName=By.xpath(".//input[@id='register_companyName']");
		public static final By CompanyRequiredLabel=By.xpath("//input[@id='register_companyName']/following-sibling::label");
		public static final By VatNumber=By.xpath(".//input[@id='register_vat']");
		public static final By PhoneNumber=By.xpath(".//input[@id='register_telephone']");
		public static final By PhoneRequiredLabel=By.xpath("//input[@id='register_telephone']/following-sibling::label");
		public static final By AddressLine1=By.xpath("//input[@id='register_addressline1']");
		public static final By AddressRequiredLabel=By.xpath("//input[@id='register_addressline1']/following-sibling::label");
		public static final By PostalCode=By.xpath("//input[@id='address_postcode']");
		public static final By PostalCodeRequiredLabel=By.xpath("//input[@id='address_postcode']/following-sibling::label");
		public static final By TownCity=By.xpath("//input[@id='register_towncity']");
		public static final By TownCityRequiredLabel=By.xpath("//input[@id='register_towncity']/following-sibling::label");
		public static final By CompanyID=By.xpath(".//input[@id='register_existingCompanyName']");
		
		//opt-in checkboxes
		public static final By NewsChechbox=By.xpath("//input[@name='_optinNews']");
		public static final By ActionsChechbox=By.xpath("//input[@name='_optinActions']");
		public static final By AcceptChechbox=By.xpath("//input[@name='_acceptConditionsPolicy']");
		//name=acceptConditionsPolicy
		public static final By AcceptChechboxReqLabel=By.xpath("//input[@name='_acceptConditionsPolicy']/parent::label/label");
		
		public static final By RegisterButton =By.xpath("//button[contains(text(),'Register')]");		
		public static final By RegisterpageHeader =By.xpath("//div[@class='headline'][contains(text(),'Create an Account')]");		
		public static final By CountryDropdown =By.xpath("//select[@id='country_selector']");
		
		//confirmation
		public static final By AlertMsg=By.xpath("//div[contains(@class,'alert-dismissable')]/button");
		
		//Cookies notification
		public static final By CookieAlert= By.cssSelector("#cc_banner > p > a.cc_btn.cc_btn_accept_all");
		
		//Forget Password
    	public static final By ForgotPasswordLink=By.xpath("//*[@id='loginForm']/div/a[@data-cbox-title='Reset Password']");
    	public static final By ResetPasswordPopup_Header=By.xpath("//*[@id='cboxTitle']/div/span[text()='Reset Password']");
    	public static final By ForgotPassword_Email=By.xpath("//input[@id='forgottenPwd.email']");
    	public static final By ResetPassword_Button=By.xpath("//*[@id='forgottenPwdForm']/div/button[@type='submit']");
    	public static final By Email_Error=By.xpath("//span[@id='email.errors'][text()='Please enter a valid email']");
    	public static final By ValidEmail_Message=By.xpath("//div[@id='cboxContent']/descendant::div[contains(text(),'Password reset instructions have been sent to your e-mail address. Please contact customer support if you require additional assistance.')]");
    	public static final By ModalPopup_Close=By.xpath("//span[@class='glyphicon glyphicon-remove']");
    	public static final By ResetPassword_msg=By.xpath("//div[contains(text(),'Password reset instructions have been sent to your e-mail address. Please contact customer support if you require additional assistance.')]");
		
	}
	
	

	public static class LoginOR {
		
        public static final By LoginLinkHeader=By.xpath("//*[@id='navbar-secondary']/descendant::a/span[text()='login / signup']");
		public static final By Username=By.xpath("//input[@id='j_username']");		
		public static final By Password=By.xpath( "//input[@id='j_password']");
		public static final By Login   =By.xpath("//button[@type='submit'][contains(text(),'Login')]");
		public static final By WelcomeLink=By.xpath("//a[contains(text(),'Welcome')]");
		public static final By InvalidUserMsgbox=By.xpath("//button[@class='close']");
		
		//Menu options
		public static final By AdminMenuDropdown=By.xpath("//*[@id='navbar-secondary']/descendant::a[@data-toggle='dropdown']/span[contains(text(),'Admin')]");
		public static final By AdminDropdownIcon=By.xpath("//*[@id='navbar-secondary']/descendant::a[2]/b[@class='caret']");
		public static final By BudgetsMenu=By.xpath("//*[@id='navbar-secondary']/descendant::a[contains(text(),'Budgets')]");
		public static final By UsersMenu=By.xpath("//*[@id='navbar-secondary']/descendant::a[contains(text(),'Users')]");
		public static final By WelcomeDropdownIcon=By.xpath("//*[@id='navbar-secondary']/descendant::li/a[contains(text(),'Welcome')]/b");
		public static final By LogoutEnglish=By.xpath("//*[@id='navbar-secondary']/descendant::ul[@class='dropdown-menu']/li/a[text()='Log Out']");
		
		//Budgets page
		public static final By BudgetsHeader=By.xpath("//div[@class='account-section-header']");
		public static final By CreateBudgetLink=By.xpath("//a[text()='Create New Budget']");
		public static final By CreateBudgetHeader=By.xpath("//descendant::div[@class='account-section']/descendant::span[@class='label']");
		public static final By BackLink=By.xpath("//div[@class='back-link']/descendant::span[1]");
		public static final By BudgetName=By.xpath("//input[@id='text.company.budget.name']");
		public static final By BudgetAmount=By.xpath("//input[@id='text.company.budget.amount']");
		public static final By CurrecyDropdown=By.xpath("//select[@id='text.company.budget.currency']");
		public static final By RecurrenceDropdown=By.xpath("//select[@id='text.company.budget.recurrencePeriod']");
		public static final By Budgetstartdate=By.xpath("//input[@id='budgetStartDate']");
		public static final By Budgetenddate=By.xpath("//input[@id='budgetEndDate']");
		public static final By BudgetCancel=By.xpath("//a[text()='Cancel']");
		public static final By BudgetSave=By.xpath("//button[@id='SubmitBudget_button'][text()='Save']");
		public static final By BudgetCreateSucess=By.xpath("//div[@class='alert alert-info alert-dismissable']");
				
		//Footer components
		public static final By CustomerServiceButton=By.xpath("//*[@id='section-footer']/div[1]/descendant::a[text()='Customer Service']");		
		public static final By CustomerServiceSection=By.xpath("//*[@id='section-footer']/div[1]/descendant::h3[text()='Customer Service']");			
		public static final By NewsActionsSection=By.xpath("//*[@id='section-footer']/div[1]/descendant::h3[text()='News and Actions']");
		public static final By NewsActionsButton=By.xpath("//*[@id='section-footer']/div[1]/descendant::a[text()='Next']");
		public static final By NewsActionsEmail=By.xpath("//*[@id='section-footer']/div[1]/descendant::input[@type='email']");		
		public static final By AboutFaborySection=By.xpath("//*[@id='section-footer']/div[1]/descendant::h3[text()='About Fabory']");		
		public static final By AboutFaboryFirstLink=By.xpath("//*[@id='section-footer']/div[1]/descendant::ul/li/descendant::a[1]");		
		public static final By  FooterTermsConditions=By.xpath("//*[@id='section-footer']/descendant::a[text()='Terms and conditions']");
		
		
		//Navigation bar elements
		public static final By  NavbarLogo=By.xpath("//*[@id='navbar-primary']/descendant::a[@class='navbar-brand']");
		public static final By  NavbarProducts=By.xpath("//*[@id='navbar-primary']/descendant::a/span[text()='Products']");
		public static final By  NavbarKnowledgeCenter=By.xpath("//*[@id='navbar-primary']/descendant::a[text()='Knowledge Center']");
		public static final By  NavbarShops=By.xpath("//*[@id='navbar-primary']/descendant::a[text()='Shops']");
		
		
		//Minicart
		public static final By  MiniCart=By.xpath("//*[@id='navbar-primary']/descendant::a[@data-mini-cart-name='Cart']");		
		public static final By  MiniCartPopup=By.xpath("//*[@id='cboxLoadedContent']/descendant::div[@class='mini-cart-body']");
		public static final By  CartPopupContinueShopping=By.xpath("//*[@id='cboxLoadedContent']/descendant::div[@class='mini-cart-body']/a[contains(text(),'Continue Shopping')]");
		public static final By  CartPopupCheckout=By.xpath("//*[@id='cboxLoadedContent']/descendant::div[@class='mini-cart-body']/a[contains(text(),'Checkout')]");
		public static final By  CartPopupShowAll=By.xpath("//*[@id='cboxLoadedContent']/descendant::a[text()='Show All']");
		
	    //reset password popup
		public static final By ForgotPassword=By.xpath("//a[contains(text(),'Forgot your password?')]");
		public static final By ResetPasswordPopup=By.xpath("//*[@id='cboxTitle']/div/span[text()='Reset Password']");
		public static final By ResetPasswordEmail=By.xpath("//*[@id='forgottenPwd.email']");
		public static final By ResetPasswordbutton=By.xpath("//*[@id='forgottenPwdForm']/descendant::button[contains(text(),'Reset Password')]");
		
        //User Page
		public static final By UsersPageHeader=By.xpath("//div[@class='account-section-header']");
		public static final By UserAddLink=By.xpath("//a[@class='button add'][text()='Add']");
		public static final By NewUserHeader=By.xpath("//span[@class='label'][contains(text(),'New User')]");
		public static final By NewUserPageFirstName=By.xpath("//input[@id='user.firstName'][@type='text']");
		public static final By NewUserPageLastName=By.xpath("//input[@id='user.lastName'][@type='text']");
		public static final By NewUserPageEmail=By.xpath("//input[@id='user.email'][@type='text']");
		public static final By NewUserPagePhone=By.xpath("//input[@id='user.phone'][@type='text']");
		public static final By NewUserDepartment=By.xpath("//input[@id='user.department'][@type='text']");
		public static final By NewUserPosition=By.xpath("//input[@id='user.profession'][@type='text']");
		public static final By NewUserSaveButton=By.xpath("//button[@class='btn btn-block btn-primary save'][text()='Save']");
		public static final By UserLink=By.xpath("//a[contains(@href,'niharika4net@gmail.com')]");
		public static final By UserDetailsPageHeader=By.xpath("//span[@class='label'][contains(text(),'User Details')]");
		public static final By BudgetAddLink=By.xpath("//div[3][@class='account-list-header']/div/a/span[@class='visible-xs'][text()='Add']");
		public static final By AssignBudgetRadioButton=By.xpath("//a[contains(text(),'Test Budget')]/parent::td/following-sibling::td/input[@type='radio']");
		public static final By DoneButton=By.xpath("//a[contains(text(),'Done')]");
		public static final By BudgetAddedLink=By.xpath("//div[@class='account-list-header'][contains(text(),'Budgets')]/following-sibling::div/descendant::a[text()='Test Budget']");
		
	}
		
public static class Backoffice_LoginOR {
		
		//Back office login Elements
		public static final By BO_Username=By.xpath("//input[@class='login z-textbox'][@name='j_username']");
		public static final By BO_Password=By.xpath("//input[@class='login z-textbox'][@name='j_password']");
		public static final By BO_Login=By.xpath("//button[@type='submit']");
		public static final By BO_Logout=By.xpath("//img[contains(@src,'logout')]");
		
		public static final By BO_Home=By.xpath("//span[@class='z-label'][text()='Home']");
		public static final By B2BCommerceLink=By.xpath("//span[@class='z-label'][text()='B2B Commerce']");
		public static final By B2BCustomerLink=By.xpath("//span[@class='z-label'][text()='B2B Customer']");
		public static final By B2BBudgetLink=By.xpath("//span[@class='z-label'][text()='B2B Budget']");
		public static final By SearchTextbox=By.xpath("//input[@class='z-bandbox-input z-bandbox-rightedge']");
		public static final By SearchButton=By.xpath("//button[@type='button'][text()='Search']");
		public static final By AdvanceSearchButton=By.xpath("//button[@title='Switch search mode']");
		public static final By SearchNameInput=By.xpath("//span[(text()='Name')]/parent::td/following-sibling::td[2]/div/div[@class='yw-advancedsearch-local-editor z-div']/div/input");
		
		public static final By CustomerSignup=By.xpath("//span[text()='niharika.arelly+a1@testingxperts.com']");
		public static final By SelectCheckbox=By.xpath("//span/i[@class='z-listitem-icon z-icon-check']");
		public static final By DeleteIcon=By.xpath("//img[@title='Delete Action [Delete]']");
		public static final By ConfirmPopupMsg=By.xpath("//table/descendant::span[text()='Do you really want to delete the selected entities?']");
		public static final By ConfirmYes=By.xpath("//table/descendant::button[@type='button'][text()='Yes']");
		public static final By NOResults=By.xpath("//td[text()='No entries']");
}
	//to be worked on
	public static class LandingOR {
		//Admin dropdown options
		public static final By AdminLink=By.xpath("//*[@id='navbar-secondary']/descendant::a[@data-toggle='dropdown']/span[contains(text(),'Admin')]");
		public static final By AdminCompanyProfile=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[1]/a[contains(text(),'Company profile')]");
		public static final By AdminUsers=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[2]/a[contains(text(),'Users')]");
		public static final By AdminBudgets=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[3]/a[contains(text(),'Budgets')]");
		public static final By AdminPermissions=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[4]/a[contains(text(),'Permissions')]");
		
		//Welcome dropdown options
		public static final By WelcomeAccount=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[5]/ul/li[1]/a[contains(text(),'Account')]");
		public static final By WelcomeQuickOrder=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[5]/ul/li[2]/a[contains(text(),'Quick order')]");
		public static final By WelcomeListCatalog=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[5]/ul/li[3]/a[contains(text(),'Lists & catalogs')]");
		public static final By WelcomeOrders=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[5]/ul/li[4]/a[contains(text(),'Orders')]");		
		public static final By Logout=By.xpath("//*[@id='navbar-secondary']/descendant::ul/li[5]/ul/li[5]/a[contains(text(),'Log Out')]");
	}
	
	
	public static class ALPOR {
		public static final By ALP_BoltsHeader=By.xpath("//*[@id='section-alp']/descendant::h1/strong[text()='Bolts']");
		public static final By ALP_filters_Categoryheader=By.xpath("//aside[@data-src='alp-filters']/div/descendant::a[text()='faboryCategoryPath'][1]");
		public static final By ALP_filters_Category1=By.xpath("//aside[@data-src='alp-filters']/div/descendant::div[@id='filters']/descendant::ol[1]/li[1]");
		public static final By ALP_FirstMaster_ViewButton=By.xpath("//*[@id='alp-products-showmore-navigation']/descendant::a[@class='btn btn-primary view-button'][@type='button'][1]");
		
	}
	public static class ADPOR {
		public static final By ADP_ShowVariants_Button=By.xpath("//*[@id='section-adp-details']/descendant::a[contains(text(),'Show variants')]");
		public static final By VariantTable_Row1_ArticleNumber=By.xpath("//tbody[contains(@data-add-to-cart-action,'/cart/add')]/tr[1]/td/i[@data-toggle='modal']/parent::td/following-sibling::td[1]/a");
		public static final By VariantTable_Row1_Addtocart=By.xpath("//tbody[contains(@data-add-to-cart-action,'/cart/add')]/tr[1]/td/i[@data-toggle='modal']/parent::td/following-sibling::td/a[contains(text(),'Add')]");
		public static final By ADP_Addtocart=By.xpath("//*[@id='section-adp-details']/descendant::button/i[@class='zmdi zmdi-shopping-cart']");
		
	}
	
	public static class CartOR{
		public static final By Cart_Header=By.xpath("//h1[@class='cart-headline']");
	}
	public static class ConfirmationOR {
		public static final By RegistrationConfirmationMessage=By.xpath("//div[contains(@class,'alert-dismissable')]");
		
		public static final By UserConfirmationMessage=By.xpath("//div[@class='alert alert-info alert-dismissable']");
	}
	public static class AddressOR
	{
		public static final By NewAddress_Button=By.xpath("//*[@id='selectDeliveryMethodForm']/descendant::button[contains(text(),'New address')]");
		public static final By StateProvince=By.xpath("//select[@id='address.country']");
		public static final By Title=By.xpath("//select[@id='address.title']");
		public static final By FirstName=By.xpath("//input[@id='address.firstName']");
		public static final By LastName=By.xpath("//input[@id='address.surname']");
		public static final By AddressLine1=By.xpath("//input[@id='address.line1']");
		public static final By TownCity=By.xpath("//input[@id='address.townCity']");
		public static final By PostalCode=By.xpath("//input[@id='address.postcode']");
		public static final By SaveAsShippingCheckbox=By.xpath("//input[@type='checkbox'][@name='saveInAddressBook']");
		public static final By Submit=By.xpath("//div[@id='addressform_button_panel']/descendant::button[@type='submit'][contains(text(),'Save')]");
		public static final By Cancel=By.xpath("//div[@id='addressform_button_panel']/descendant::a[contains(text(),'Cancel')]");
	}
	public static class CheckoutOR
	{
		public static final By Checkout_button=By.xpath("//*[@id='checkout-button-bottom']");
		public static final By Checkout_Header=By.xpath("//div[@class='checkout-headline']");
		public static final By ShippingAddresHeader=By.xpath("//div[@class='title'][text()='Shipping Address']");
		public static final By SA_PONumber=By.xpath("//input[@name='purchaseOrderNumber']");
		public static final By SA_RequestedDeliveryDate=By.xpath("//input[@name='requestedDeliveryDate']");
		public static final By DeliveryCheckbox=By.xpath("//input[@name='deliveryModeCode']");
		public static final By Checkout_ShippingAddress=By.xpath("//div[text()='Shipping Address']/parent::div/span");
		public static final By AddressBook_Button=By.xpath("//*[@id='selectDeliveryMethodForm']/descendant::button[contains(text(),'Address Book')]");
		public static final By PickupCheckbox=By.xpath("//*[@id='deliveryModeCode2']");
		public static final By DefaultPickupAddress=By.xpath("//select[@id='pos_selector']/parent::div/descendant::div/span");
		public static final By NextButton1=By.xpath("//*[@id='goToNextStep']");
		public static final By DatePicker=By.xpath("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-current-day']");
		public static final By DatePickerErrorMsg=By.xpath("//*[@id='selectDeliveryMethodForm']/descendant::div[@class='help-block']");
		///span[contains(text(),'Selected delivery date is')]");
		public static final By PaymentTypeHeader=By.xpath("//a/div[@class='title'][text()='Payment Type']");
		public static final By CardPayment_RadioButton=By.xpath("//input[@type='radio'][@id='PaymentTypeSelection_CARD']");
		public static final By AccountPayment_RadioButton=By.xpath(" //input[@type='radio'][@id='PaymentTypeSelection_ACCOUNT']");
		public static final By NextButton2=By.xpath("//*[@id='choosePaymentType_continue_button'][contains(text(),'Next')]");
		public static final By Next_PaymentBilling=By.xpath("//button[contains(@class,'checkout-next')][@type='button']");
		public static final By Payment_BillingAddress=By.xpath("//a/div[@class='title'][text()='Payment & Billing Address']");
		public static final By Title_PaymentBilling=By.xpath("//*[@id='address.title']");
		
		//credit card payment
		public static final By CardPayment_CCNumber=By.xpath("//dl[@id='checkout-payment-method-load']/dt/following-sibling::div/descendant::*[@id='creditCardNumber']");
		public static final By CardPayment_CCName=By.xpath("//dl[@id='checkout-payment-method-load']/dt/following-sibling::div/descendant::*[@id='creditCardHolderName']");
		public static final By CC_ExpirationAugMonth=By.xpath("//select[@id='adyen_cc_expiration']");
		public static final By CC_Expirationyear2018=By.xpath("//select[@id='adyen_cc_expiration_yr']");
		public static final By CC_CVVNumber=By.xpath("//input[@title='Card Verification Number']");
		
		//SEPA Bank Transfer
		public static final By SEPABankTransfer_radiobutton=By.xpath("//input[@title='SEPA Bank Transfer'][@type='radio']");
		public static final By FinalReview=By.xpath("//div[@class='title'][text()='Final Review']");
		public static final By SEPAContinue=By.xpath("//input[@type='submit'][@value='continue']");
		public static final By SEPA_Adyen_Email=By.xpath("//input[@name='suppliedEmailAddress']");
		public static final By SEPA_Email_Note=By.xpath("//textarea[@name='reminderNote']");
		
		//SEPA Direct Debit
		public static final By SEPADirectDebit=By.xpath("//input[@title='SEPA Direct Debit'][@type='radio']");
		public static final By AccountHolderName=By.xpath("//input[@id='sepadirectdebit.ownerName']");
		public static final By AdyenCountry=By.xpath("//select[@id='sepadirectdebit.bankCountryCode']");
		public static final By IBAN=By.xpath("//input[@id='sepadirectdebit.bankAccountNumber']");
		public static final By SEPAAccept=By.xpath("//input[@id='sepadirectdebit.acceptDirectDebit']");
		
		public static final By SEPAStep3Header=By.xpath("//h2[text()='Step 3: Please review and complete your payment']");
		public static final By Pay_button=By.xpath("//input[@type='submit'][@value='pay']");
		//Final Review
		public static final By FinalReviewHeader=By.xpath("//a/div[@class='title'][text()='Final Review']");
		public static final By FinalReviewOrderTotal=By.xpath("//div[@class='checkout-order-summary']/descendant::div[@class='totals']");
		public static final By FinalReviewVAT=By.xpath("//div[@class='checkout-order-summary']/descendant::div[@class='tax'][2]");
		public static final By FinalDeliveryCost=By.xpath("//div[@class='checkout-order-summary']/descendant::div[@class='subtotal']");
		public static final By FinalReviewTermsCheckbox=By.xpath("//div[@class='place-order-form hidden-xs']/descendant::input[@type='checkbox']");
		public static final By SubmitOrder=By.xpath("//div[@class='place-order-form hidden-xs']/descendant::button[@type='submit']");
		public static final By OrderSuccess=By.xpath("//div[@class='checkout-success']/div/div[contains(text(),'Thank you for your Order!')]");
		public static final By ConfirmPage_OrderNumber=By.xpath("//div[contains(text(),'Thank you for your Order!')]/parent::div/p[text()='Your Order Number is']/b");
	}
	
	
	
	public static class GmailOR{
		public static final By GooggleLogo=By.xpath("//div[@id='logo'][@title='Google']");
		public static final By EmailTextbox=By.xpath("//input[@type='email']");	
		public static final By EmailNext=By.xpath("//content/span[text()='Next']");	
		public static final By PwdTextbox=By.xpath("//input[@type='password']");	
		public static final By PasswordNext=By.xpath("//div[@id='passwordNext']/content/span[text()='Next']");
		public static final By GmailHome=By.xpath("//div/span[text()='Gmail']");
		public static final By UserAccount=By.xpath("//a[@role='button'][contains(@title,'fwstestuser.tx@gmail.com')]");
		public static final By SignOut=By.xpath("//a[text()='Sign out']");
		public static final By PasswordResetUnreadEmailSubject=By.xpath("//span/b[text()='Forgotten Password']");
		public static final By EmailSender=By.xpath("//h3/span[@email='noreply@fabory.com']");
		public static final By Email_ResetPasswordLink=By.xpath("//a[contains(@href,'fabory.com')]/u[text()='click here']");
		public static final By ShowEmailBody=By.xpath("//div[@class='ajR']/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif']");
		
		//Reset Password page
		public static final By PageTitle=By.xpath("//title[contains(text(),'Reset Password')]");
		public static final By NewPassword=By.xpath("//input[@name='pwd'][@type='password']");
		public static final By ConfirmPassword=By.xpath("//input[@name='checkPwd'][@type='password']");
		public static final By UpdateButton=By.xpath("//button[contains(text(),'Update')]");
		public static final By ConfirmPwdValidateMessage=By.xpath("//label[text()='Please enter the same value again.']");
		
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
	//Locators of PaymentsMenu
	
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
	  //public static final By payment=By.xpath("//div[contains(@class,'nav-title firepath-matching-node')]");
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


