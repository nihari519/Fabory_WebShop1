
package com.FABE.accelerators;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.FABE.objrepo.FaboryOR.HomePageOR;
import com.FABE.objrepo.FaboryOR.LoginOR;
import com.FABE.objrepo.FaboryOR.SignUpOR;
import com.FABE.support.ExcelReader;
import com.FABE.support.ReportStampSupport;
import com.FABE.util.ValidatePDF;
import com.relevantcodes.extentreports.LogStatus;



public class ActionEngine extends TestEngine 
{

	public XSSFSheet inputSheet = null;
	
	public ExcelReader Excelobject = new ExcelReader();
	//public XSSFSheet inputSheet = Excelobject.getSheetObject(TESTDATAWORKBOOKHOME, "Demo");
	public ValidatePDF PDFObject = new com.FABE.util.ValidatePDF();
	public static String TESTDATAWORKBOOKHOME = configProps.getProperty("TestDataWorkBookHome");
	public static long lSleep_VLow = Long.valueOf(configProps.getProperty("Sleep_VLow").toString());
	public static long lSleep_Low = Long.valueOf(configProps.getProperty("Sleep_Low").toString());
	public static long lSleep_Medium = Long.valueOf(configProps.getProperty("Sleep_Medium").toString());
	public static long lSleep_High = Long.valueOf(configProps.getProperty("Sleep_High").toString());
	public WebDriverWait wait;
	String gStrErrMsg = "";
	public static boolean blnEventReport = false;
	public static String Env=configProps.getProperty("Environment");
	public String DEV_URL = configProps.getProperty("DEV_URL");
	public String UAT_URL = configProps.getProperty("UAT_URL");
	public String DEV_BOURL = configProps.getProperty("DEV_BOURL");
	public String UAT_BOURL = configProps.getProperty("UAT_BOURL");

	/*'########################################################
	' Objective            :     Click on Web Element
	       :    
	'########################################################
*/	

	public boolean type1(By locator, String testdata, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"), testdata);
			Thread.sleep(250);
			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag;

		} 
		return flag;
	}
	/*'########################################################
	' Objective            :     Click on Web Element
     :    
	'########################################################
*/	
	public boolean click(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			Thread.sleep(lSleep_Low);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			//if (flag && blnEventReport) {
				if (flag ) {
				SuccessReport("Click on " + locatorName, "Successfully clicked on: \"" + locatorName + "\"");
				// return true;
			} else if (!flag) {
				failureReport("Click on " + locatorName, "Unable to click on: \"" + locatorName + "\"");

			}
			
		}
		return flag;
	}

	public boolean clickWithOutReport(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag && blnEventReport) {
				
			} else if (!flag) {
				
			}
			
		}
		return flag;
	}

	
	/*'########################################################
	' Objective            :     Move to select Particular WebElement
   
	'########################################################
*/	
	public boolean moveToElement(By locator, String locatorName) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(locator)).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	/*'########################################################
	' Objective            :     Verify the Element is Present or not on the WebPage(Ex:CheckBox,Button,Radio Button etc)
       
	'########################################################
*/	
	public boolean isElementPresent(By by, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		// boolean flag = false;
		try {
			driver.findElement(by).isDisplayed();
			flag = true;
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			
		}
	}
	/*'########################################################
	' Objective            :     Verify the Element is Present on PopUp Page
	          :    
	'########################################################
*/	

	public boolean isPopUpElementPresent(By by, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			if (driver.findElement(by).isDisplayed())
				flag = true;
			else
				flag = false;
			return flag;
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (flag && blnEventReport) {
				SuccessReport("IsElementPresent ", "Able to locate the element \"" + locatorName + "\"");
			} else if (!flag) {
				failureReport("check IsElementPresent", "\"" + locatorName + "\"Element is not present on the page");
			}
		}
	}

//Objective     :     Enter the Data in TextBox/TextArea
	public boolean type(By locator, String testdata, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testdata);
			Thread.sleep(750);
			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;

		} finally {
			if (flag) {
				SuccessReport("Type Data in " + locatorName,
						"Data typing action is performed on \"" + locatorName + "\" with data \"" + testdata + "\"");
			} else if (!flag) {
				failureReport("Type Data in " + locatorName, "Data typing action is not performed on \"" + locatorName
						+ "\" with data \"" + testdata + "\"");
			}
		}
		return flag;
	}
	/*'########################################################
	' Objective            :     select the date from calendar picker.
	  
	'########################################################
*/	
	public void selectTodayDateFromMultiDateCalendar(int day) throws InterruptedException {
		// We are using a special XPath style to select the day of current month.
		// It will ignore the previous or next month day and pick the correct one.
		By calendarXpath = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='"+ day + "']");
		driver.findElement(calendarXpath).click();
        // Intentional pause for 2 seconds.
		Thread.sleep(2000);
		
	}
	
	
	/*'########################################################
	' Objective            :     Enter the Data in TextBox/TextArea with out clearing the Existing data
	  
	'########################################################
*/	
	public boolean typewithoutclear(By locator, String testdata, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(testdata);
			flag = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;

		} finally {
			if (flag && blnEventReport) {
				SuccessReport("Type Data in " + locatorName,
						"Data typing action is performed on \"" + locatorName + "\" with data \"" + testdata + "\"");
			} else if (!flag) {
				failureReport("Type Data in " + locatorName,
						"Data typing action is not perform on \"" + locatorName + "\" with data \"" + testdata + "\"");
			}
		}
		return flag;
	}

	public boolean jsType(By locator, String testdata, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('value', arguments[1])", element, testdata);
			flag = true;
			executionDelay(1000);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return false;
		} finally {
			if (flag) {
				SuccessReport("Type Data in " + locatorName,
						"Data typing action is performed on \"" + locatorName + "\" with data \"" + testdata + "\"");
			} else if (!flag) {
				failureReport("Type Data in " + locatorName,
						"Data typing action is not perform on \"" + locatorName + "\" with data \"" + testdata + "\"");
			}
		}
		return flag;
	}
	/*'########################################################
	' Objective            :     Moves the Mouse to middle of the element
	          :    
	'########################################################
*/	
	
	public boolean mouseover(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				
			} else {
				
			}
		}
	}
	/*'########################################################
	' Objective            :     Click-and-hold at the location of the source element, moves by a given offset, then releases the mouse.
     
	'########################################################
*/	
	
	public boolean draggable(By source, int x, int y, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {

			return false;

		} finally {
			if (flag && blnEventReport) {
				SuccessReport("Draggable ", "Draggable Action is performed on \"" + locatorName + "\"");
			} else if (!flag) {
				failureReport("Draggable ", "Draggable action is not performed on \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     Click-and-hold at the location of the source element, moves to the location of the target element, then releases
      
	'########################################################
*/	
	
	
	public boolean draganddrop(By source, By target, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag && blnEventReport) {
				SuccessReport("DragAndDrop ", "DragAndDrop Action is performed on \"" + locatorName + "\"");
			} else if (!flag) {
				failureReport("DragAndDrop ", "DragAndDrop action is not performed on \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     To slide an object to some distance
    
	'########################################################
*/	
	
	
	public boolean slider(By slider, int x, int y, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();

		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Slider ", "Slider Action is performed on \"" + locatorName + "\"");
			} else {
				failureReport("Slider ", "Slider action is not performed on \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     To right click on an element
        
	'########################################################
*/	
	

	public boolean rightclick(By by, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("RightClick ", "RightClick Action is performed on \"" + locatorName + "\"");
			} else {
				failureReport("RightClick ", "RightClick action is not performed on \"" + locatorName + "\"");
			}
		}
	}

	/*'########################################################
	' Objective            :     Wait for title of  An Element
        
	'########################################################
*/	

	public boolean waitForTitlePresent(By locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		boolean bValue = false;

		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElements(locator).size() > 0) {
					flag = true;
					bValue = true;
					break;
				} else {
					driver.wait(50);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				SuccessReport("WaitForTitlePresent ", "Launched successfully expected Title");
			} else {
				failureReport("WaitForTitlePresent ", "Title is wrong");
			}
		}
		return bValue;
	}
	/*'########################################################
	' Objective            :     Wait for Element Present
    
	'########################################################
*/	
	
	public boolean waitForElementPresent(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		for (int i = 0; i < 100; i++) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				flag = false;
				Thread.sleep(50);
			}

		}

		return flag;

	}

	public boolean waitForElementPresentWithoutReport(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		for (int i = 0; i < 100; i++) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					flag = true;
					return flag;
				}
			} catch (Exception e) {
				
				if (i == 5)

					failureReport("WaitForElementPresent ", "Falied to locate the element \"" + locator + "\"");

				else
					continue;
			}

		}

		return flag;

	}

	/*'########################################################
	' Objective            :     click and Wait For Element
          
	'########################################################
*/	
	public boolean clickAndWaitForElementPresent(By locator, By waitElement, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			click(locator, locatorName);
			waitForElementPresent(waitElement, locatorName);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("ClickAndWaitForElementPresent ",
						"Successfully performed clickAndWaitForElementPresent action");
			} else {
				failureReport("ClickAndWaitForElementPresent ",
						"Failed to perform clickAndWaitForElementPresent action");
			}
		}
	}
	/*'########################################################
	' Objective            :     Select Value from dropdown using sendkeys
	 
	'########################################################
*/	
	
	public boolean selectBySendkeys(By locator, String value, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Select value from " + locatorName,
						"\"" + value + "\"is Selected from the DropDown\"" + locatorName + "\"");
			} else {
				failureReport("Select value from " + locatorName,
						"\"" + value + "\"is Not Selected from the DropDown\"" + locatorName + "\"");
				
			}
		}
	}
	/*'########################################################
	' Objective            :     Select value from DropDown by using selectByIndex
       
	'########################################################
*/	
	
	public boolean selectByIndex(By locator, int index, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			executionDelay(2000);
			Select s = new Select(driver.findElement(locator));			
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Select ",
						"Option at index \"" + index + "\"is Selected from the DropDown \"" + locatorName + "\"");
			} else {
				failureReport("Select ",
						"Option at index \"" + index + "\"is Not Selected from the DropDown \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     Select value from DropDown by using value
	'  
	'########################################################
*/	
	

	public boolean selectByValue(By locator, String value, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("Select", "Option with value attribute \"" + value + "\"is Selected from the DropDown \""
						+ locatorName + "\"");
			} else {
				failureReport("Select", "Option with value attribute \"" + value
						+ "\"is Not Selected from the DropDown \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     Select value from DropDown by using VisibleText
	   
	'########################################################
*/	
	

	public boolean selectByVisibleText(By locator, String visibletext, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			Thread.sleep(1000);
			s.selectByVisibleText(visibletext);
			
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag && blnEventReport) {
				SuccessReport("Select",
						"\"" + visibletext + "\" is Selected from the DropDown \"" + locatorName + "\"");
			} else if (!flag) {
				failureReport("Select",
						"\"" + visibletext + "\" is Not Selected from the DropDown \"" + locatorName + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     Switch To Window By Using Title
	   
	'########################################################
*/	
	

	public Boolean switchWindowByTitle(String windowTitle, int count) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			  Set<String> windowList = driver.getWindowHandles();
              String[] array = windowList.toArray(new String[0]);
              driver.switchTo().window(array[count - 1]);

			if (driver.getTitle().contains(windowTitle)) 
			{
				flag = true;
			} else 
			{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectWindow", "Navigated to the window with title: \"" + windowTitle + "\"");
			} else {
				failureReport("SelectWindow", "The Window with title: \"" + windowTitle + "\"is not Selected");
			}
		}
	}

	public Boolean switchToNewWindow(String strWindow) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				SuccessReport("Select Window", strWindow + " Window is selected");
			} else {
				failureReport("Select Window", strWindow + " Window is not selected");
			}
		}
	}

	public Boolean switchToOldWindow() throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {

			Set<String> s = driver.getWindowHandles();
			Object popup[] = s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				SuccessReport("SelectWindow", "Focus navigated to the window with title:");
			} else {
				failureReport("SelectWindow", "The Window with title: is not Selected");
			}
		}
	}
	/*'########################################################
	' Objective            :     To get column count and print data in Columns
	  
	'########################################################
*/	
	
	public int getColumncount(By locator) throws Exception {
		cmdStartTime = System.currentTimeMillis();
		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}
	/*'########################################################
	' Objective            :     To get row count and print data in rows
	
	'########################################################
*/	
	
	public int getRowCount(By locator) throws Exception {
		cmdStartTime = System.currentTimeMillis();
		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	/*'########################################################
	' Objective            :     Verify alert present or not

	'########################################################
*/	
	
	
	public boolean Alert() throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean presentFlag = false;
		Alert alert = null;

		try {

			
			alert = driver.switchTo().alert();
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) 
		{
			
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				SuccessReport("Alert ", "The Alert is handled successfully");
			} else {
				failureReport("Alert ", "There was no alert to handle");
			}
		}

		return presentFlag;
	}
	/*'########################################################
	' Objective            :     To launch URL
	
	'########################################################
*/	
	
	public boolean launchUrl(String url) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {

			driver.navigate().to(url);
            ImplicitWait();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Launching URL", "Successfully launched \"" + url + "\"");
			} else {
				failureReport("Launching URL", "Failed to launch \"" + url + "\"");
			}
		}
	}

	public boolean isAlertPresent() throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		try {
			Thread.sleep(500);
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException Ex) {
			return false;
		} 
	}

	
	/*'########################################################
	' Objective            :     Verify check box is checked or not
 
	'########################################################
*/	
	public boolean isChecked(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} 
		return bvalue;
	}

	public boolean isChecked_Report(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean bvalue = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}

		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (flag && blnEventReport) {
				SuccessReport("IsChecked", "\"" + locatorName + "\"is Selected");

			} else {
				failureReport("IsChecked", "\"" + locatorName + "\"is not Selected");
			}
		}
		return bvalue;
	}

	/*'########################################################
	' Objective            :     Verify Element is enable or not
	
	'########################################################
*/	

	public boolean isEnabled(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (flag && blnEventReport) {
				SuccessReport("IsEnabled ", "\"" + locatorName + "\" is Enabled");
			} else {
				failureReport("IsEnabled ", "\"" + locatorName + "\" is not Enabled");
			}
		}
		return value;
	}
	/*'########################################################
	' Objective            :     Verify Element is enable or not
	
	'########################################################
*/	
	

	public boolean isEnabled_WithoutReport(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		Boolean value = false;
		boolean flag = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) 
		{

			flag = false;

		}
		return value;
	}
	/*'########################################################
	' Objective            :     Verify Element is Disabled or not

	'########################################################
*/	
	

	public boolean isDisabled(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		Boolean value = false;
		boolean flag = false;
		try {
			if (!driver.findElement(locator).isEnabled()) {
				flag = true;
				value = true;
			}

		} catch (Exception e) {

			flag = false;

		} finally {
			if (flag && blnEventReport) {
				SuccessReport("IsDisabled", "\"" + locatorName + "\" is Disabled");
			} else {
				failureReport("IsDisabled", "\"" + locatorName + "\" is not Disabled");
			}
		}
		return value;
	}
	/*'########################################################
	' Objective            :     Element visible or not
	  
	'########################################################
*/	
	
	public boolean isVisible(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		Boolean value = false;
		boolean flag = false;
		try {

			value = driver.findElement(locator).isDisplayed();
			value = true;
			flag = true;
		} catch (Exception e) {
			flag = false;
			value = false;

		} finally {
			if (flag) {
				SuccessReport("IsVisible ", "\"" + locatorName + "\" Element is Visible");
			} else {
				failureReport("IsVisible", "\"" + locatorName + "\"Element is Not Visible");
			}
		}
		return value;
	}
	/*'########################################################
	' Objective            :     Get the CSS value of an element

	'########################################################
*/	
	

	public String getCssValue(By locator, String cssattribute, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		String value = "";
		boolean flag = false;
		try {
			if (isElementPresent(locator, "locatorName")) {
				value = driver.findElement(locator).getCssValue(cssattribute);
				flag = true;
			}
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("GetCssValue ", "Was able to get Css value from \"" + locatorName + "\"");
			} else {
				failureReport("GetCssValue ", "Was not able to get Css value from \"" + locatorName + "\"");
			}
		}
		return value;
	}

	/*'########################################################
	' Objective            :     Check the expected value is available or not
 
	'########################################################
*/	
	public boolean assertValue(String expvalue, By locator, String attribute, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {

			Assert.assertEquals(expvalue, getAttribute(locator, attribute, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertValue ", "\"" + locatorName + "\" is present in the page");
				return false;
			} else {
				failureReport("AssertValue ", "\"" + locatorName + "\" is not present in the page");
			}
		}
		return flag;
	}
	/*'########################################################
	' Objective            :     Check the text is present or not
	
	'########################################################
*/	
	
	public boolean assertTextPresent(String text) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			Assert.assertTrue(isTextPresent(text));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertTextPresent ", "\"" + text + "\" is not present in the page ");
				return false;
			} else {
				failureReport("AssertTextPresent ", "\"" + text + "\" is present in the page ");
			}
		}
		return flag;
	}
	/*'########################################################
	' Objective            :     Assert Element Present or Not
	  
	'########################################################
*/	
	
	public boolean assertElementPresent(By by, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			Assert.assertTrue(isElementPresent(by, locatorName));
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertElementPresent ", "\"" + locatorName + "\"is present in the page ");
				return false;
			} else {
				failureReport("AssertElementPresent ", "\"" + locatorName + "\" is not present in the page ");
			}
		}
		return flag;

	}
	/*'########################################################
	' Objective            :     Assert Element Present or Not
	
	'########################################################
*/	
	
	public boolean assertElementNotPresent(By by, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			if (!isElementPresent(by, locatorName))
				;
			flag = true;
		} catch (Exception e) {

		} finally {
			if (flag) {
				SuccessReport("AssertElementNotPresent ", "\"" + locatorName + "\"is Not present in the page ");
				return false;
			} else {
				failureReport("AssertElementNotPresent ", "\"" + locatorName + "\" is present in the page ");
			}
		}
		return flag;

	}
	/*'########################################################
	' Objective            :     Assert text on element
   
	'########################################################
*/	
	

	public boolean assertText(By by, String text) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			Thread.sleep(lSleep_Low);
			if (getText(by, text).trim().replaceAll("\n", "").replaceAll(" ", "").toLowerCase()
					.contains(text.trim().replaceAll("\n", "").replaceAll(" ", "").toLowerCase())) {
				flag = true;
				return true;
			} else {
				flag = false;
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("AssertText ", "\"" + text + "\" is  present in the element ");
				return false;
			} else {
				failureReport("AssertText " + "\"" + text + "\"", " Invalid Text/Text is not present ");
			}
		}

	}
	/*'########################################################
	' Objective            :     Assert text on element
	
	'########################################################
*/	
	
	
	public boolean verifyText(By by, String text, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		String vtxt = "";

		try {

			vtxt = getText(by, locatorName).trim();
			System.out.println("Text:" + vtxt);
			if (vtxt.equals(text.trim())) {
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("VerifyText ", "\"" + text + "\" is present in the location \"" + locatorName + "\"");
			} else {
				failureReport("VerifyText ", "Expected Text:" + text + "    " + "Actual Text:" + vtxt);
			}
		}
	}

	/*'########################################################
	' Objective            :     Return the Title of Current Page
	
	'########################################################
*/	

	public String getTitle() throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			SuccessReport("Title ", "Title of the page is: \"" + text + "\"");
		}
		return text;
	}

	/*'########################################################
	' Objective            :     Assert Title of the page.
  
	'########################################################
*/	
	public boolean asserTitle(String title) throws Throwable {
		boolean flag = false;
		cmdStartTime = System.currentTimeMillis();
		try {
			By windowTitle = By.xpath("//title[contains(text(),'" + title + "')]");
			if (waitForTitlePresent(windowTitle)) {
				Assert.assertEquals(getTitle(), title);
				flag = true;
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

			if (flag) {
				SuccessReport("AsserTitle ", "Page title is verified: \"" + title + "\"");
			} else {
				failureReport("AsserTitle ", "Page title is not matched with: \"" + title + "\"");
			}
		}

	}
	/*'########################################################
	' Objective            :     Verify Title of the page.
	  
	'########################################################
*/	
	
	public boolean verifyTitle(String title) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;

		try {
			getTitle().equals(title);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

		finally {
			if (flag) {
				SuccessReport("VerifyTitle ", "Page title is verified: \"" + title + "\"");
			} else {
				failureReport("VerifyTitle ", "Page title is not matched with: \"" + title + "\"");
			}
		}
	}
	/*'########################################################
	' Objective            :     Verify text present or not
	   
	'########################################################
*/	
	
	public boolean verifyTextPresent(String text) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		;
		if ((driver.getPageSource()).contains(text)) {
			SuccessReport("VerifyTextPresent ", "\"" + text + "\"is present in the page");
		} else {
			failureReport("VerifyTextPresent ", "\"" + text + "\"is not present in the page");

		}
		return flag;
	}

	

	public String getAttribute(By by, String attribute, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		String value = "";
		if (isElementPresent(by, locatorName)) {
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/*'########################################################
	' Objective            :     Verify text present or not
	
	'########################################################
*/	

	public boolean isTextPresent(String text) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean value = false;
		if (driver.getPageSource().contains(text)) {
			value = true;
			flag = true;
		} else {
			System.out.println("is text " + text + " present  " + value);
			flag = false;
		}
		if (value) {
			SuccessReport("IsTextPresent ", "\"" + text + "\"is present in the page ");
		} else {
			failureReport("IsTextPresent ", "\"" + text + "\"is  not present in the page ");

		}
		return value;
	}

	/*'########################################################
	' Objective            :     Verify inner Text of the Element
	  
	'########################################################
*/	

	public String getText(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		String text = "";
		
		try {
			if (isElementPresent(locator, locatorName)) {
				text = driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return text;
	}

	public String getFirstSelectedOption(By locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		String value = "";
		if (isElementPresent(locator, "")) {
			Select s = new Select(driver.findElement(locator));
			value = s.getFirstSelectedOption().getText().trim();
		}
		return value;
	}

	public int getElementsSize(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		int text = 0;
		try {
			if (driver.findElement(locator).isDisplayed()) {
				text = driver.findElements(locator).size();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return text;
	}

	/*'########################################################
	' Objective            :     Capture Screenshot
	
	'########################################################
*/	
	public void screenShot(String fileName) {
		cmdStartTime = System.currentTimeMillis();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*'########################################################
	' Objective            :     Click on the Link
	   
	'########################################################
*/	

	public boolean mouseHoverByJavaScript(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("MouseOver ", " MouserOver Action is performed on \"" + locatorName + "\"");
			} else {
				failureReport("MouseOver ", " MouseOver Action is not performed on \"" + locatorName + "\"");
			}
		}
	}

	public boolean JSClick(By locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			flag = true;
            executionDelay(1500);
		}

		catch (Exception e) 
		{
			throw e;

		} 
		finally {
			if (flag ) {
				SuccessReport("Click on " + locatorName, " Click Action is performed on \"" + locatorName + "\"");
			} else if (!flag) {
				failureReport("Click on " + locatorName, " Click Action is not performed on \"" + locatorName + "\"");
			}
		}
		return flag;
	}

	public boolean waitForFrameToLoadAndSwitchToIt(By by, String LocatorName) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		cmdStartTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			if (blnEventReport == true) {
				
			}

			return true;
		} catch (Exception e) {
			

			return false;
		}

	}

	public boolean waitForFrameToLoadAndSwitchToItWithOutReport(By by, String LocatorName) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 12);

		cmdStartTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			if (blnEventReport == true) {
				
			}

			return true;
		} catch (Exception e) {
			

			return false;
		}

	}
	/*'########################################################
	' Objective            :     Switch the focus to selected frame using frame index
	 
	'########################################################
*/	
	
	public boolean switchToFrameByIndex(int index) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", " Frame with index \"" + index + "\" is selected");
			} else {
				failureReport("SelectFrame ", " Frame with index \"" + index + "\" is not selected");
			}
		}
	}
	/*'########################################################
	' Objective            :     Switch the focus to selected frame using frame ID

	'########################################################
*/	
	
	public boolean switchToFrameById(String idValue) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} 
	}
	/*'########################################################
	' Objective            :     Switch to frame using frame Name
	
	'########################################################
*/	
	
	public boolean switchToFrameByName(String nameValue) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag && blnEventReport) {
				SuccessReport("SelectFrame ", "Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				failureReport("SelectFrame ", "Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}
	/*'########################################################
	' Objective            :     Switch to Default Frame

	'########################################################
*/	
	
	public boolean switchToDefaultFrame() throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag && blnEventReport) {
				
			} else if (!flag) {
				
			}
		}
	}
	/*'########################################################
	' Objective            :     Switch to Frame Using FrameName

	'########################################################
*/	
	
	public boolean switchToFrameByLocator(By lacator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.switchTo().frame(driver.findElement(lacator));
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("SelectFrame ", "Frame with Name \"" + locatorName + "\" is selected");
			} else {
				failureReport("SelectFrame ", "Frame \"" + locatorName + "\" is not selected");
			}
		}
	}
	/*'########################################################
	' Objective            :     Wait For Element Until Element is on WebPage

	'########################################################
*/	
	
	public void ImplicitWait() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public boolean waitUntilTextPresents(By by, String expectedText, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		wait = new WebDriverWait(driver, 120);
		boolean flag = false;

		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, expectedText));

			flag = true;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport(" WaitUntilTextPresent ",
						"Successfully located element \"" + locator + "\" with text \"" + expectedText + "\"");
			} else {
				failureReport("WaitUntilTextPresent ",
						"Falied to locate element \"" + locator + "\" with text \"" + expectedText + "\"");
			}

		}

	}

	/*'########################################################
	' Objective            :     Click on Element

	'########################################################
*/	

	public boolean click1(WebElement locator, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Click ", " Able to click on \"" + locatorName + "\"");
			} else {
				failureReport("Click ", " Unable to click on \"" + locatorName + "\"");
			}
		}

	}
	/*'########################################################
	' Objective            :     wait driver until given driver time
	
	'########################################################
*/	

	public WebDriverWait driverwait() {

		wait = new WebDriverWait(driver, 30);
		return wait;
	}

	/*'########################################################
	' Objective            :     Wait until visibility of Elements on WebPage
	 
	'########################################################
*/	


	public boolean waitForVisibilityOfElement(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} 
		
	}

	/*'########################################################
	' Objective            :     Wait until Element to be Clickable
	  
	'########################################################
*/	

	public boolean waitForElementToBeClickable(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} 
		
	}

	/*'########################################################
	' Objective            :     Wait until Element to be Clickable

	'########################################################
*/	

	public boolean waitForVisibilityOfElementWithoutReport(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		wait = new WebDriverWait(driver, 30);

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	/*'########################################################
	' Objective            :     wait driver until Invisibility of Elements on WebPage
	
	'########################################################
*/	
	
	public boolean waitForInVisibilityOfElement(By by, String locator) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		wait = new WebDriverWait(driver, 60);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("WaitForInVisibilityOfElement ", " Element  \"" + locator + "\"  is not visible");
			} else {
				failureReport("WaitForInVisibilityOfElement ", " Element \"" + locator + "\"  is  visible");
			}
		}

	}

	public List<WebElement> getElements(By locator) {

		List<WebElement> ele = driver.findElements(locator);

		return ele;
	}
	/*'########################################################
	' Objective            :     Assert the TextMatching
	
	'########################################################
*/	

	public boolean assertTextMatching(By by, String text, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			String ActualText = getText(by, text).trim();
			if (ActualText.contains(text)) {
				flag = true;
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				SuccessReport("Verify \"" + locatorName + "\"", "\"" + text + "\" is  present in the element ");
			} else {
				failureReport("Verify \"" + locatorName + "\"", "\"" + text + "\" is not present in the element");
			}
		}

	}

	

	public boolean isElementDisplayed(By objLocator, String strLocatorName) throws Throwable {
		boolean blnFlag = false;
		try {
			Thread.sleep(100);
			driver.findElement(objLocator).isDisplayed();
			blnFlag = true;
			return blnFlag;
		} catch (Exception e) {
			gStrErrMsg = e.getMessage();
			return blnFlag;
		} finally {
			if (!blnFlag)
			failureReport("Check " + strLocatorName + " visibility", strLocatorName + "");
			else
			{
//			else if (blnEventReport && blnFlag)
//				List <WebElement> ele=driver.findElements(objLocator);
//				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
				SuccessReport("Check " + strLocatorName + " visibility", strLocatorName + " is visible");
		}
		}
	}

	public boolean isElementNotDisplayed(By objLocator, String strLocatorName) throws Throwable {
		boolean blnFlag = false;
		try {
			if (!driver.findElement(objLocator).isDisplayed()) {
				blnFlag = true;
				return blnFlag;
			} else {
				return blnFlag;
			}
		} catch (Exception e) {
			gStrErrMsg = e.getMessage();
			blnFlag = true;
			return blnFlag;
		} finally {
			if (!blnFlag && !blnEventReport)
				failureReport("Check " + strLocatorName + " is not visibile", strLocatorName + " is visible");
			else if (blnEventReport && blnFlag)
				SuccessReport("Check " + strLocatorName + " is not visibile", strLocatorName + " is not visible");
		}
	}

	public void executeJavaScriptOnElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

	public boolean hitKey(By locator, Keys keyStroke, String locatorName) throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(keyStroke);
			flag = true;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			if (flag) {
				SuccessReport("Type ", "Data typing action is performed on \"" + locatorName + "\" with data is \""
						+ keyStroke + "\"");
			} else {
				failureReport("Type ", "Data typing action is not perform on \"" + locatorName + "\" with data is \""
						+ keyStroke + "\"");

			}
		}
	}

	public Collection<WebElement> getWebElementsByTagInsideLocator(By locator, String tagName, String locatorName)
			throws Throwable {
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		Collection<WebElement> elements;
		try {
			WebElement element = driver.findElement(locator);
			elements = element.findElements(By.tagName(tagName));
			flag = true;
		} catch (NoSuchElementException e) {
			throw e;
		} finally {
			if (flag) {
				SuccessReport("Type ",
						"Data typing action is performed on \"" + locatorName + "\" with data is \"" + tagName + "\"");
			} else {
				failureReport("Type", "Data typing action is not performed on  \"" + locatorName + "\" with data \""
						+ tagName + "\"");
			}
		}
		return elements;
	}

	public void mouseOverElement(WebElement element, String locatorName) throws Throwable 
	{
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		    } 
		   catch (Exception e) 
		 {
			e.printStackTrace();
		 } 
		 finally {
			if (flag) {
				SuccessReport("MouseOver ", " MouserOver Action is performed on " + locatorName);
			} else {
				failureReport("MouseOver ", " MouseOver action is not performed on \"" + locatorName + "\"");
			}
		}
	}

	@Parameters({ "browser" })
	public void SuccessReport(String strStepName, String strStepDes) 
	{
		try {
			int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));
			switch (intReporterType) {
			case 1:

				break;
			case 2:
				if (configProps.getProperty("OnSuccessScreenshot").equalsIgnoreCase("True")) {
					screenShot("Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_")
							+ ".jpeg");
				}
				onSuccess(strStepName, strStepDes);

				break;

			default:
				if (configProps.getProperty("OnSuccessScreenshot").equalsIgnoreCase("True")) {
					screenShot("Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_")
							+ ".jpeg");
				}
				onSuccess(strStepName, strStepDes);
				break;
			}

			if (configProps.getProperty("OnSuccessScreenshot").equalsIgnoreCase("True")) {

				child.log(LogStatus.PASS, strStepName + child.addScreenCapture(screenShotForPass(driver)), strStepDes);

			} else {
				child.log(LogStatus.PASS, strStepName, strStepDes);
			}
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}

	}
	/*'########################################################
	' Objective            :     Failure Report
	
	'########################################################
*/	

	public void failureReport(String strStepName, String strStepDes) 
	{
		try {
			int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));

			String stepExecTime = ReportStampSupport.stepExecTime();
			switch (intReporterType) 
			{
			case 1:
				flag = true;
				break;
			case 2:
				screenShot("Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_")
						+ stepExecTime + ".jpeg");
				flag = true;
				onFailure(strStepName, strStepDes, stepExecTime);
				break;

			default:
				flag = true;
				screenShot("Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_")
						+ ReportStampSupport.stepExecTime() + ".jpeg");
				onFailure(strStepName, strStepDes, stepExecTime);
				break;
			}

			child.log(LogStatus.FAIL, strStepName + child.addScreenCapture(screenShotForPass(driver)), strStepDes);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	public String screenShotForPass(WebDriver driver) throws IOException 
	{
		String src_path = "";
		if (browsertype.equalsIgnoreCase("firefox")) 
		{
			src_path = System.getProperty("user.dir") + "\\Reports\\Firefox\\Screenshots\\";
		} else if (browsertype.equalsIgnoreCase("Chrome")) 
		{
			src_path = System.getProperty("user.dir") + "\\Reports\\Chrome\\Screenshots\\";
		} else if (browsertype.equalsIgnoreCase("ie")) 
		{
			src_path = System.getProperty("user.dir") + "\\Reports\\IE\\Screenshots\\";
		} else if (browsertype.equalsIgnoreCase("safari")) 
		{
			src_path = System.getProperty("user.dir") + "\\Reports\\Safari\\Screenshots\\";
		}

		UUID uuid = UUID.randomUUID();
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			
			FileUtils.copyFile(scrFile, new File(src_path + uuid + ".png"));
		} catch (IOException e) {
			System.out.println("Unhadled exception occure while generating screenshot:\n" + e.toString());
		}
		return src_path + uuid + ".png";
	}

	public void warningReport(String strStepName, String strStepDes) throws Throwable 
	{
		int intReporterType = Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) 
		{
		case 1:
			flag = true;
			break;
		case 2:
			screenShot(
					"Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_") + ".jpeg");
			flag = true;
			onWarning(strStepName, strStepDes);
			break;

		default:
			flag = true;
			screenShot(
					"Results/HTML" + suiteStartTime + "/Screenshots/" + strStepDes.replaceAll("[^\\w]", "_") + ".jpeg");
			onWarning(strStepName, strStepDes);
			break;
		}

	}
	/*'########################################################
	' Objective            :     VerifyElementPresent
	
	'########################################################
*/	
	
	public boolean VerifyElementPresent(By by, String locatorName) throws Throwable 
	{
		cmdStartTime = System.currentTimeMillis();
		boolean flag = false;

		try {
			if (isElementPresent(by, locatorName)) 
			{
				flag = true;

			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		finally 
		{
			if (flag) 
			{
				SuccessReport("VerifyElementPresent ", locatorName + " is Displayed");
			} 
			else 
			{
				failureReport("VerifyElementPresent ", locatorName + " is not Displayed");
			}
		}
		return flag;
	}
	/*'########################################################
	' Objective            :     waitProcessComplete

	'########################################################
*/	

	public void waitProcessComplete(By by) throws Throwable 
	{
		cmdStartTime = System.currentTimeMillis();
		int count = 1;
		while (count < 500) 
		{

			try {
				if (driver.findElement(by).isDisplayed()) {
					Thread.sleep(300);
				} else 
				{
					Thread.sleep(1000);
					break;
				}
			} catch (Exception e)
			{
				break;
			}
			count++;
		}

	}
	/*'########################################################
	' Objective            :     To Get the Count of Elements
	   
	'########################################################
*/	
	public int getListCount(By by, String locatorName) throws Throwable 
	{
		int count = 0;

		cmdStartTime = System.currentTimeMillis();
		try {
			if (isElementPresent(by, locatorName)) {
				count = driver.findElements(by).size();
			}

			return count;

		} 
		catch (Exception e) 
		{
			failureReport("Frame Selection Exception Info :",
					"Unable to select the frame with locator :" + locatorName);

			return count;

		}
	}
	/*'########################################################
	' Objective            :     To Get the List of Elements
	    
	'########################################################
*/	

	public List<WebElement> getWebElements(By by, String locatorName) throws Throwable 
	{
		List<WebElement> webElements = null;

		cmdStartTime = System.currentTimeMillis();
		try {
			if (isElementPresent(by, locatorName)) {
				webElements = driver.findElements(by);
			}

			return webElements;

		} 
		catch (Exception e) 
		{
			failureReport("Exception Info :", "Unable to find with locator :" + locatorName);
			return null;

		}
	}

	/*'########################################################
	' Objective            :     TO Get the Current Date
	  
	'########################################################
*/	
	public String getCurrentDate(String strFormat) 
	{
		try {

			DateFormat dateFormat = new SimpleDateFormat(strFormat);
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date dateObj = new Date();
			return dateFormat.format(dateObj);
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	/*'########################################################
	' Objective            :     TO Get the Tomorrow Date
	  
	'########################################################
*/	
	public String getTomorrowDate(String strFormat) 
	{
		try 
		{
			DateFormat dateFormat = new SimpleDateFormat(strFormat);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			return dateFormat.format(cal.getTime());
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	/*'########################################################
	' Objective            :     TO Get the Start of Year
	  
	'########################################################
*/	

	public String getStartofYear() 
	{
		try 
		{
			Calendar cal = Calendar.getInstance();
			String currentYear = Integer.toString(cal.get(Calendar.YEAR));
			String startOfYear = "01/01/" + currentYear;
			return startOfYear;

		} 
		catch (Exception e) 
		{
			return null;
		}
	}
	/*'########################################################
	' Objective            :     TO Get the TimeStamp
	 
	'########################################################
*/	
	
	public String getTimeStamp(String strFormat) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(strFormat);
			String timeStamp = dateFormat.format(new Date());
			return timeStamp;
		} catch (Exception e) {
			return null;
		}
	}

	/*'########################################################
	' Objective            :     Catch the Exception Date of creation
	
	'########################################################
*/	
	
	public void catchBlock(Exception e) throws Throwable
	{

		System.out.println("1");
		executionDelay(10000);
		if (e.getMessage() != null)
			gStrErrMsg = e.getMessage();
		System.out.println(gStrErrMsg);
		e.printStackTrace();
		parent.appendChild(child);
		failureReport("Exception Description", gStrErrMsg);
		closeSummaryReport(browsertype);
		driver.quit();
		openBrowser();
		Thread.sleep(lSleep_VLow);

	}
	/*'########################################################
	' Objective            :     Explicitly Wait to Click on Element
	
	'########################################################
*/	
	
	public static WebElement explicitWaitForElement(By by) {

		
		WebDriverWait wait = new WebDriverWait(driver, 45);
        return wait.until(ExpectedConditions.elementToBeClickable(by));

	}
	/*'########################################################
	' Objective            :     Scroll to Particular Element of Page
 
	'########################################################
*/	
	public static boolean scrollingToElementofAPage(By by) {

		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		return true;
	}
	/*'########################################################
	' Objective            :     Verify the Web Element is Present or not on the WebPage
	
	'########################################################
*/	
	 
	public boolean isWebElementPresent(By by, String locatorName) {

		boolean flag = false;
		try {
		WebElement element = returnWebElement(by);
		flag = element.isDisplayed();
		if (flag) 
		{
			SuccessReport("Present: ", locatorName);

		} else 
		{
			failureReport("No Present: ", locatorName);
		}
	    } catch (Throwable e) 
		{

		e.printStackTrace();
	   }
	   return flag;

	}
	/*'########################################################
	' Objective            :     Verify the Web Element is Present or not on the WebPage
	  
	'########################################################
*/	
	public WebElement returnWebElement(By aBy) {

		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.presenceOfElementLocated(aBy));
		wait.until(ExpectedConditions.visibilityOfElementLocated(aBy));
		wait.until(ExpectedConditions.elementToBeClickable(aBy));
		WebElement webElement = driver.findElement(aBy);
		return webElement;

	}
	/*'########################################################
	' Objective            :     Verify the Web Element is not Present on the WebPage
	 
	'########################################################
*/	

	public boolean isWebElementNotPresent(By by) 
	{

		List<WebElement> elements = driver.findElements(by);

		if (elements.isEmpty()) 
		{
			return true;
		}

		return false;
	}
	/*'########################################################
	' Objective            :     Click on WebElement Using Java Script Executor
	
	'########################################################
*/	
	public static boolean clickUsingJS(By by,  String text) 
	{

		WebElement element = explicitWaitForElement(by);
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
        return true;

	}
	/*'########################################################
	' Objective            :     SwitchToWindow
	
	'########################################################
*/	

	public static void switchToCurrentWindow() 
	{
		for (String winHandle : driver.getWindowHandles()) 
		{
			driver.switchTo().window(winHandle);
		}
	}
	/*'########################################################
	' Objective            :     Execution Delay
	
	'########################################################
*/	
	public static void executionDelay(long time) throws InterruptedException 
	{
		Thread.sleep(time);

	}
	/*'########################################################
	' Objective            :     Verify the Expected Text and Actual Text
	
	'########################################################
*/	
	public boolean verifyTextUsingData(By by, String data)
	{

		boolean flag = false;
		try {
			WebElement element = driver.findElement(by);
			String actul_txt = element.getText();
			if (data.equals(actul_txt)) 
			{
				flag = true;
				return flag;
			}

			return flag;

		} 
		catch (NoSuchElementException ex) 
		{
			System.out.println("element absent");
			return true;
		} finally 
		{
			if (flag) 
			{
				SuccessReport("Verify:", "Actual and Expected text matched");
			} else {
				failureReport("Verify:", "Actual and Expected text is not matched");
			}
		}

	}
//navigate to webshop url and accept cookies
	public void navigateToWebsite() throws Throwable 
	{
		if(Env.contentEquals("UAT"))
		URL=UAT_URL;
		else 
		if(Env.contentEquals("DEV"))
		URL=DEV_URL;
		driver.navigate().to(URL);
		executionDelay(5000);
		//verify if navigated to the url
		String navURL=driver.getCurrentUrl();
		if (navURL.endsWith(URL))
		SuccessReport("Launced URL", ""+URL);
		else 
		failureReport("Launced URL ",""+navURL);
		
		//accept cookies alert
		if(isWebElementPresent(SignUpOR.CookieAlert,"Cookies"))
		click(SignUpOR.CookieAlert,"Accept Cookies");
		else
        System.out.println("Cookies alert is not displayed");
	
	}
	
//navigate to backoffice url
	public  void navigateToBackoffice() throws InterruptedException 
	{
		if(Env.contentEquals("UAT"))
			URL=UAT_BOURL;
			else 
			if(Env.contentEquals("DEV"))
			URL=DEV_BOURL;
		driver.navigate().to(URL);
		executionDelay(3000);
		
		String navURL=driver.getCurrentUrl();
		if (navURL.endsWith(URL))
		SuccessReport("Launced URL", ""+URL);
		else 
		failureReport("Launced URL ",""+navURL);
	    
	}
	

	
//Random number generator
	public int random(int min, int max)
	{
	return  min + (int)(Math.random() * max); 
	}
}
