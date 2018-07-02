package com.FABE.accelerators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.FABE.support.ConfiguratorSupport;
import com.FABE.support.HtmlReportSupport;
import com.FABE.support.MyListener;
import com.FABE.support.ReportStampSupport;
import com.FABE.util.SendMail;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import jxl.Sheet;
import jxl.Workbook;

public class TestEngine extends SendMail {

	public static Logger logger = Logger.getLogger(TestEngine.class.getName());

	public static ConfiguratorSupport configProps = new ConfiguratorSupport("config.properties");
	public static ExtentReports extent;
	public ExtentTest parent;
	public ExtentTest child;
	public static String environment = configProps.getProperty("Environment");
	//public static ConfiguratorSupport counterProp = new ConfiguratorSupport(configProps.getProperty("counterPath"));
	public static String ParallelExec=configProps.getProperty("parallel");
	public static int iStepNo = 0;
	public String currentSuite = "";
	public String method = "";
	public boolean flag = false;
	public boolean pFlag = false;
	public WebDriver webDriver = null;
	public static EventFiringWebDriver driver = null;
	public DesiredCapabilities capabilities;
	public int stepNum = 0;
	public int PassNum = 0;
	public int FailNum = 0;
	public int RowFailNum = 0;
	public String testName = "";
	public String testCaseExecutionTime = "";
	public StringBuffer x = new StringBuffer();
	public String finalTime = "";
	public boolean isSuiteRunning = false;
	public static Map<String, String> testDescription = new LinkedHashMap<String, String>();
	public static Map<String, String> testResults = new LinkedHashMap<String, String>();
	public String URL = configProps.getProperty("URL");
	public int countcompare = 0;
	public String browsertype;
	static int len = 0;
	static int i = 0;
	public ITestContext itc;
	public String groupNames = null;
	public int failCount=0;
	public int passCount=0;
	public static File zip = new File("");
	public Map<Integer, Object[]> data = new LinkedHashMap<Integer, Object[]>();
	private static int h = 1;
	public boolean ImpersonateFlag = false;
	
	public static final String USERNAME = "chandu407";
	public static final String ACCESS_KEY = "8cd4d2b3-bd67-4e91-9e11-f24dd1958f33";
	public static final String SURL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

	public static final String BStack_USERNAME = "mummanavasu2";
	public static final String BStack_AUTOMATE_KEY = "rUuGh1Mx9KuU1DFFX5m6";
	public static final String BURL = "https://" + BStack_USERNAME + ":" + BStack_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public String browser=configProps.getProperty("Browser");
	
	

	
	//@Parameters({ "parallel" })
	@BeforeSuite(alwaysRun = true)
	public void suiteFirst(ITestContext ctx) throws Throwable 
	{
		itc = ctx;
//        ParallelExec = parallel;
		ReportStampSupport.calculateSuiteStartTime();
        Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		suiteStartTime = formattedDate.replace(":", "_").replace(" ", "_");
        new File("Results/HTML" + suiteStartTime + "/Screenshots").mkdirs();
        HtmlReportSupport.copyLogos();

	}

	//@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void first(ITestContext ctx) throws Throwable 
	{
		
		itc = ctx;
	
		if (browser.equalsIgnoreCase("firefox")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\Firefox\\" + browser + "ExecutionResults.html", false,Locale.ENGLISH);
		} else if (browser.equalsIgnoreCase("chrome")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\Chrome\\" + browser + "ExecutionResults.html", true,Locale.ENGLISH);
		} else if (browser.equalsIgnoreCase("ie")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\IE\\" + browser + "ExecutionResults.html", false,Locale.ENGLISH);
		} else if (browser.equalsIgnoreCase("cafari")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\Safari\\" + browser + "ExecutionResults.html", false,Locale.ENGLISH);
		} else if (browser.contains("browserstack")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\BrowserStack\\" + browser + "ExecutionResults.html",
					false,Locale.ENGLISH);
		} else if (browser.contains("saucelab")) {
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\Reports\\Saucelab\\" + browser + "ExecutionResults.html",
					false,Locale.ENGLISH);
		}
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\config.xml"));
		
		extent.addSystemInfo("Environment", environment);
		extent.addSystemInfo("Browser", browser);
		
}

	//@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void firstBeforeClass(ITestContext ctx) throws Throwable
	{
		
		browsertype = browser;
		itc = ctx;

	}

	@AfterTest(alwaysRun = true)
	public void first1(ITestContext ctx) throws Throwable {
		itc = ctx;
		HtmlReportSupport.createHtmlSummaryReport(browsertype, URL);
		ReportStampSupport.calculateSuiteExecutionTime();
		closeSummaryReport(browsertype);
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownFirefox(ITestContext ctx) throws Throwable {
        
		WriteExcelfile();
		File source = new File("Results/HTML" + suiteStartTime + "/SummaryResults_IE.xls");
		File dest = new File("Results/SummaryFiles/" + suiteStartTime + "-" + "_SummaryResults_IE.xls");
		FileUtils.copyFile(source, dest);
        SendMail.attachReportsToEmail("Results/HTML" + suiteStartTime + "/SummaryResults_IE.xls");
        SendMail.attachReportsToEmail("Results/HTML" + suiteStartTime);

	}

	public static void cleanUP() throws IOException {

		FileUtils.deleteDirectory(new File("Results/HTML"));
		new File("Results/HTML/Screenshots").mkdirs();
		HtmlReportSupport.copyLogos();

	}

	@BeforeMethod(alwaysRun = true)
	public void reportHeader(Method method, ITestContext ctx) throws Throwable {
		
		itc = ctx;
		parent = extent.startTest(method.getName());
		DesiredCapabilities dr = null;
		if (ParallelExec.equalsIgnoreCase("YES")) {
			if (browsertype.equalsIgnoreCase("firefox")) {
				dr = DesiredCapabilities.firefox();
				dr.setBrowserName(browsertype);
				dr.setPlatform(Platform.WINDOWS);
			} else if (browsertype.equalsIgnoreCase("ie")) {
				dr = DesiredCapabilities.internetExplorer();
				dr.setBrowserName("internet explorer");
				dr.setPlatform(Platform.WINDOWS);
			} else if (browsertype.equalsIgnoreCase("chrome")) {
				File dir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				if (!dir.exists())
					dir.mkdir();

				DesiredCapabilities capabilities = new DesiredCapabilities();

				File chromeExecutable = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\chromedriver_2.38.exe");
				System.setProperty("webdriver.chrome.driver", chromeExecutable.getAbsolutePath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.default_directory",System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--start-maximized");
				Map<String, Object> prefss = new HashMap<String, Object>();
				prefss.put("credentials_enable_service", false);
				prefss.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefss);

				options.setExperimentalOption("forceDevToolsScreenshot", true);

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				webDriver = new ChromeDriver(capabilities);
			    i = i + 1;
			} else if (browsertype.equalsIgnoreCase("browserstack")) {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "7.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "XP");
				caps.setCapability("browserstack.debug", "true");

				webDriver = new RemoteWebDriver(new URL(BURL), caps);
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("saucelab")) {
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setCapability("platform", "Windows 7");
				caps.setCapability("version", "43.0");

				webDriver = new RemoteWebDriver(new URL(SURL), caps);
				i = i + 1;
			}
			
		} else if (ParallelExec.equalsIgnoreCase("NO")) {

			if (browsertype.equalsIgnoreCase("firefox")) {
				File file = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette",true);				
				webDriver = new FirefoxDriver(capabilities);
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("ie")) {
				File file = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				webDriver = new InternetExplorerDriver();
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("chrome")) 
			{
				

				File dir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				if (!dir.exists())
					dir.mkdir();

				DesiredCapabilities capabilities = new DesiredCapabilities();

				File chromeExecutable = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\chromedriver_2.38.exe");
				System.setProperty("webdriver.chrome.driver", chromeExecutable.getAbsolutePath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.default_directory",System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-popup-blocking");
				Map<String, Object> prefss = new HashMap<String, Object>();
                prefss.put("credentials_enable_service", false);
                prefss.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefss);
                options.setExperimentalOption("forceDevToolsScreenshot", true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                webDriver = new ChromeDriver(capabilities);
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("safari")) {
				webDriver = new SafariDriver();
			} else if (browsertype.contains("saucelab")) {
				DesiredCapabilities capabilities = null;
				if (browsertype.contains("chrome")) {
					capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability("version", "52.0");
					System.out.println("chrome in sauce");
					
				}
				if (browsertype.contains("firefox")) {
					capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("version", "52.0");
					System.out.println("firefox in sauce");
				}
				if (browsertype.contains("internet")) {
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
					capabilities.setCapability("ie.ensureCleanSession", true);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
					capabilities.setCapability("version", "11.0");
					System.out.println("IE in sauce");
				}
				capabilities.setCapability("platform", "Windows 10");
				capabilities.setCapability("name", "Barney Demo");
				webDriver = new RemoteWebDriver(new URL(SURL), capabilities);
				System.out.println("driver in sauce");
			} else if (browsertype.contains("browserstack")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				if (browsertype.contains("chrome")) {
					capabilities.setCapability("browser", "Chrome");
					capabilities.setCapability("browser_version", "52.0");
					System.out.println("chrome in browserstack");
				}
				if (browsertype.contains("firefox")) {
					capabilities.setCapability("browser", "Firefox");
					capabilities.setCapability("browser_version", "52.0");
					System.out.println("chrome in browserstack");

				}
				if (browsertype.contains("internet")) {
					capabilities.setCapability("browser", "IE");
					capabilities.setCapability("browser_version", "11.0");
					System.out.println("IE in browserstack");
				}
				capabilities.setCapability("os", "Windows");
				capabilities.setCapability("os_version", "8.1");
				capabilities.setCapability("resolution", "1024x768");
				capabilities.setCapability("browserstack.debug", "true");
				webDriver = new RemoteWebDriver(new URL(BURL), capabilities);
				System.out.println("driver in browserstack");
			}

			driver = new EventFiringWebDriver(webDriver); 
			MyListener myListener = new MyListener();
			driver.register(myListener);

			try {

				driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);

				if (browsertype.equalsIgnoreCase("Firefox")||browsertype.equalsIgnoreCase("IE")||browsertype.equalsIgnoreCase("Chrome")) {
					driver.manage().window().maximize();
				}

	//			 driver.get(URL);
				currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
			} catch (Exception e1) {
				System.out.println(e1);
			}

			calculateTestCaseStartTime();

			flag = false;

			tc_name = method.getName().toString() + "-" + browsertype;
			String[] ts_Name = this.getClass().getName().toString().split("\\.");
			packageName = ts_Name[0] + "." + ts_Name[1] + "." + ts_Name[2];

			testHeader(tc_name);

			stepNum = 0;
			PassNum = 0;
			FailNum = 0;
			testName = method.getName();
			String[] tmp = testName.split("_");
			String desc = testName.replaceAll("_", " ") + " Script";
			testDescription.put(testName + "-" + browsertype, desc);

		}
	

	}

	public  void invokeBrowser()
	{
		DesiredCapabilities dr = null;
		if (ParallelExec.equalsIgnoreCase("YES")) {
			if (browsertype.equalsIgnoreCase("firefox")) {
				dr = DesiredCapabilities.firefox();
				dr.setBrowserName(browsertype);
				dr.setPlatform(Platform.WINDOWS);
			} else if (browsertype.equalsIgnoreCase("ie")) {
				dr = DesiredCapabilities.internetExplorer();
				dr.setBrowserName("internet explorer");
				dr.setPlatform(Platform.WINDOWS);
			} else if (browsertype.equalsIgnoreCase("chrome")) {
				File dir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				if (!dir.exists())
					dir.mkdir();

				DesiredCapabilities capabilities = new DesiredCapabilities();

				File chromeExecutable = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\chromedriver_2.38.exe");
				System.setProperty("webdriver.chrome.driver", chromeExecutable.getAbsolutePath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.default_directory",
				System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--start-maximized");
				Map<String, Object> prefss = new HashMap<String, Object>();
				prefss.put("credentials_enable_service", false);
				prefss.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefss);

				options.setExperimentalOption("forceDevToolsScreenshot", true);

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				webDriver = new ChromeDriver(capabilities);
			    i = i + 1;
			} else if (browsertype.equalsIgnoreCase("browserstack")) {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "7.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "XP");
				caps.setCapability("browserstack.debug", "true");

				try {
					webDriver = new RemoteWebDriver(new URL(BURL), caps);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("saucelab")) {
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setCapability("platform", "Windows 7");
				caps.setCapability("version", "43.0");

				try {
					webDriver = new RemoteWebDriver(new URL(SURL), caps);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i = i + 1;
			}
			
		}
		else if (ParallelExec.equalsIgnoreCase("NO")) 
		{
			
         if (browsertype.equalsIgnoreCase("firefox"))
         {
        	 File file = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette",true);
				webDriver = new FirefoxDriver(capabilities);
				i = i + 1;
			}
			
			 else if (browsertype.equalsIgnoreCase("ie")) {
				File file = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				webDriver = new InternetExplorerDriver();
				i = i + 1;
			} 
			
			else if (browsertype.equalsIgnoreCase("chrome")) 
			{
				

				File dir = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				if (!dir.exists())
					dir.mkdir();

				DesiredCapabilities capabilities = new DesiredCapabilities();

				File chromeExecutable = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\Drivers\\chromedriver_2.38.exe");
				System.setProperty("webdriver.chrome.driver", chromeExecutable.getAbsolutePath());
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.default_directory",
						System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
				
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-popup-blocking");
				Map<String, Object> prefss = new HashMap<String, Object>();
                prefss.put("credentials_enable_service", false);
                prefss.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefss);
                options.setExperimentalOption("forceDevToolsScreenshot", true);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                webDriver = new ChromeDriver(capabilities);
				i = i + 1;
			} else if (browsertype.equalsIgnoreCase("safari")) {
				webDriver = new SafariDriver();
			} else if (browsertype.contains("saucelab")) {
				DesiredCapabilities capabilities = null;
				if (browsertype.contains("chrome")) {
					capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability("version", "52.0");
					System.out.println("chrome in sauce");
					
				}
				if (browsertype.contains("firefox")) {
					capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("version", "52.0");
					System.out.println("firefox in sauce");
				}
				if (browsertype.contains("internet")) {
					capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
					capabilities.setCapability("ie.ensureCleanSession", true);
					capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
					capabilities.setCapability("version", "11.0");
					System.out.println("IE in sauce");
				}
				capabilities.setCapability("platform", "Windows 10");
				capabilities.setCapability("name", "Barney Demo");
				try {
					webDriver = new RemoteWebDriver(new URL(SURL), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("driver in sauce");
			} else if (browsertype.contains("browserstack")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				if (browsertype.contains("chrome")) {
					capabilities.setCapability("browser", "Chrome");
					capabilities.setCapability("browser_version", "52.0");
					System.out.println("chrome in browserstack");
				}
				if (browsertype.contains("firefox")) {
					capabilities.setCapability("browser", "Firefox");
					capabilities.setCapability("browser_version", "52.0");
					System.out.println("chrome in browserstack");

				}
				if (browsertype.contains("internet")) {
					capabilities.setCapability("browser", "IE");
					capabilities.setCapability("browser_version", "11.0");
					System.out.println("IE in browserstack");
				}
				capabilities.setCapability("os", "Windows");
				capabilities.setCapability("os_version", "8.1");
				capabilities.setCapability("resolution", "1024x768");
				capabilities.setCapability("browserstack.debug", "true");
				try {
					webDriver = new RemoteWebDriver(new URL(BURL), capabilities);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("driver in browserstack");
			}

			driver = new EventFiringWebDriver(webDriver); 
			MyListener myListener = new MyListener();
			driver.register(myListener);

			try {

				driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);

				if (browsertype.equalsIgnoreCase("Firefox") || browsertype.equalsIgnoreCase("IE")
						|| browsertype.equalsIgnoreCase("Chrome")) {
					driver.manage().window().maximize();
				}
				 driver.get(URL);
				//currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
			} catch (Exception e1) {
				System.out.println(e1);
			}
		
		
	}
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		extent.endTest(parent);
		extent.flush();
		calculateTestCaseExecutionTime();
		closeDetailedReport(browsertype);
		System.out.println("browser :" + strTestName);
		String[] test = strTestName.split("-");
		String currentBrwoser = test[1];
//		if (FailNum != 0) {
//			testResults.put(tc_name, "FAIL");
//		System.out.println("current Browser:" + currentBrwoser);	
//			failCount++;
//		}
//
//		else if (PassNum != 0) {
//			testResults.put(tc_name, "PASS");
//			passCount++;
//		}
		
		if (pFlag)
		{
			testResults.put(tc_name, "PASS"); 				
		}			
		else  
		{
			testResults.put(tc_name, "FAIL");
			System.out.println("current Browser:" + currentBrwoser);			
		}
			
		driver.quit();
        
	}

	@AfterClass(alwaysRun = true)
	public void close() {

	}

	public void calculateTestCaseStartTime() 
	{
		iStartTime = System.currentTimeMillis();
	}

	
	public void calculateTestCaseExecutionTime() 
	{
		iEndTime = System.currentTimeMillis();
		iExecutionTime = (iEndTime - iStartTime);
		TimeUnit.MILLISECONDS.toSeconds(iExecutionTime);
		HtmlReportSupport.executionTime.put(tc_name, String.valueOf(TimeUnit.MILLISECONDS.toSeconds(iExecutionTime)));
	}

	public void onSuccess(String strStepName, String strStepDes) 
	{

		strTestName = strTestName.replaceAll("_", " ");
		String status = "PASS";
		File file = new File("Results/HTML" + suiteStartTime + "/" + strTestName + "_Results" + ".html");
		Writer writer = null;
		stepNum = stepNum + 1;

		try {
			
			if (!map.get(packageName + ":" + tc_name).equals("FAIL")) {
				map.put(packageName + ":" + tc_name, status);
				
			}
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			writer.write(
					"<td class='Pass' align='center'><img  src='./Screenshots/passed.ico' width='18' height='18'/></td> ");
			
			PassNum = PassNum + 1;
			cmdEndTime = System.currentTimeMillis();
			cmdTime = cmdEndTime - cmdStartTime;
			int time = (int) TimeUnit.MILLISECONDS.toSeconds(cmdTime);
			if (time == 0)
				time = 1;
			writer.write("<td><small>" + time + " Sec " + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onWarning(String strStepName, String strStepDes) {
		Writer writer = null;
		String status = "Warning/Failed";
		try {
			
			strTestName = strTestName.replaceAll("_", " ");
            File file = new File("Results/HTML" + suiteStartTime + "/" + strTestName + "_Results" + ".html");// "SummaryReport.html"
            writer = new FileWriter(file, true);
			stepNum = stepNum + 1;
            writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			FailNum = FailNum + 1;
            writer.write("<td>" + status + "</td> ");
			cmdEndTime = System.currentTimeMillis();
			cmdTime = cmdEndTime - cmdStartTime;
			int time = (int) TimeUnit.MILLISECONDS.toSeconds(cmdTime);
			if (time == 0)
				time = 1;
			writer.write("<td><small>" + time + " Sec " + "</small></td> ");
			writer.write("</tr> ");
			writer.close();

		} catch (Exception e) {

		}

	}

	public void onFailure(String strStepName, String strStepDes, String stepExecTime) 
	{
		Writer writer = null;
		try {
			
			String status = "FAIL";
			strTestName = strTestName.replaceAll("_", " ");
			File file = new File("Results/HTML" + suiteStartTime + "/" + strTestName + "_Results" + ".html");

			writer = new FileWriter(file, true);
			stepNum = stepNum + 1;

			writer.write("<tr class='content2' >");
			writer.write("<td>" + stepNum + "</td> ");
			writer.write("<td class='justified'>" + strStepName + "</td>");
			writer.write("<td class='justified'>" + strStepDes + "</td> ");
			
			RowFailNum = RowFailNum + 1;

			writer.write("<td class='Fail' align='center'><a  href='" + "./Screenshots" + "/"
					+ strStepDes.replaceAll("[^\\w]", "_") + stepExecTime + ".jpeg'"
					+ " alt= Screenshot  width= 15 height=15 style='text-decoration:none;'><img  src='./Screenshots/failed.ico' width='18' height='18'/></a></td>");
			FailNum = FailNum + 1;
			cmdEndTime = System.currentTimeMillis();
			cmdTime = cmdEndTime - cmdStartTime;
			int time = (int) TimeUnit.MILLISECONDS.toSeconds(cmdTime);
			if (time == 0)
				time = 1;
			writer.write("<td><small>" + time + " Sec " + "</small></td> ");
			writer.write("</tr> ");
			writer.close();
			if (!map.get(packageName + ":" + tc_name).equals("PASS")) 
			{
				map.put(packageName + ":" + tc_name + ":", status);
				
			}
			
		} catch (Exception e) {

		}

	}

	public boolean closeDetailedReport(String browser) {

		
		strTestName = strTestName.replaceAll("_", " ");
		File file = new File("Results/HTML" + suiteStartTime + "/" + strTestName + "_Results" + ".html");// "SummaryReport.html"
		Writer writer = null;

		try {
			writer = new FileWriter(file, true);
			writer.write("</table>");
			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("</colgroup>");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'> ");
			writer.write("<th colspan='4'>Execution Time In Seconds (Includes Report Creation Time) : "
					+ executionTime.get(tc_name) + "&nbsp;</th> ");
			writer.write("</tr> ");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Steps Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + PassNum + "</td>");
			writer.write("<td class='fail'>&nbsp;Steps Failed&nbsp;: </td>");
			writer.write("<td class='fail'>" + FailNum + "</td>");
			writer.write("</tr>");
			if(PassNum==stepNum)
				pFlag=true;
				else 
				pFlag=false;				
			
			writer.close();
			

		} catch (Exception e) {

		}
		return pFlag;
	}

	public void closeSummaryReport(String browser) {
		for(int i=0; i<testResults.size();i++)
		{
			String value = (new ArrayList<String>(testResults.values())).get(i);
		if(value.contains("PASS"))
		{	System.out.println("testResultsMap: "+value);
		     passCount++;
		}
		else if(value.contains("FAIL"))
		{	System.out.println("testResultsMap: "+value);
			failCount++;
	     }
		}
		File file = new File("Results/HTML" + suiteStartTime + "/SummaryResults_" + browser + ".html");// "SummaryReport.html"
		Writer writer = null;
		try {
			writer = new FileWriter(file, true);

			writer.write("<table id='footer'>");
			writer.write("<colgroup>");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' />");
			writer.write("<col style='width: 25%' /> ");
			writer.write("</colgroup> ");
			writer.write("<tfoot>");
			writer.write("<tr class='heading'>");
			writer.write("<th colspan='4'>Total Duration  In Minutes (Including Report Creation) : "+ (iSuiteExecutionTime) + "</th>");
			writer.write("</tr>");
			writer.write("<tr class='content'>");
			writer.write("<td class='pass'>&nbsp;Tests Passed&nbsp;:</td>");
			writer.write("<td class='pass'> " + passCount + "</td> ");
			writer.write("<td class='fail'>&nbsp;Tests Failed&nbsp;:</td>");
			writer.write("<td class='fail'> " + failCount + "</td> ");
			writer.write("</tr>");
			writer.write("</tfoot>");
			writer.write("</table> ");
			writer.close();

		} catch (Exception e) {

		}
	}

	public void WriteExcelfile() throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();

		File dir = new File("Results/HTML" + suiteStartTime);
		int sheetno = 0;
		int c = 0;
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().endsWith("html")) {
					System.out.println(child.getName());

					System.out.println("Entire File Path:" + child.getAbsolutePath());
					sheetno++;
					Document html = Jsoup.parse(FileUtils.readFileToString(new File(child.getAbsolutePath())));

					Elements elements = html.body().getElementById("main").getElementsByTag("tr");

					setTrElements(elements.get(0), new Object[elements.get(0).getElementsByTag("th").size() + 1]);

					for (Element Elemen : elements) 
					{

						
						setElements(Elemen, new Object[Elemen.getElementsByTag("td").size()]);
					}

					HSSFSheet sheet = workbook.createSheet(child.getName().replace(".html", ""));

					Set<Integer> keyset = data.keySet();

					int rownum = 0;
					for (Integer key : keyset) {
						Row row = sheet.createRow(rownum++);
						Object[] objArr = data.get(key);
						int cellnum = 0;
						for (Object obj : objArr) {
							Cell cell = row.createCell(cellnum++);
							if (obj instanceof Date)
								cell.setCellValue((Date) obj);
							else if (obj instanceof Boolean)
								cell.setCellValue((Boolean) obj);
							else if (obj instanceof String)
								cell.setCellValue((String) obj);
							else if (obj instanceof Double)
								cell.setCellValue((Double) obj);

							sheet.autoSizeColumn(cellnum);
						}
					}

					try {
						FileOutputStream out = new FileOutputStream(
								new File("Results/HTML" + suiteStartTime + "/SummaryResults_IE.xls"));

						workbook.write(out);
						out.close();
						System.out.println("Excel written successfully..");

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < rownum; i++) {
						Object key = data.keySet().iterator().next();
						data.remove(key);
					}
				} else {
				}

			}
		} else {
		}

	}

	private void setTrElements(Element es, Object[] m) {

		int y = 0;
		for (Element k : es.getElementsByTag("th")) {
			m[y++] = Jsoup.parse(k.childNodes().get(0).toString()).text();
		}

		data.put(h, m);
		h++;
	}

	private void setElements(Element es, Object[] m) {
		int y = 0;
		for (Element k : es.getElementsByTag("td")) {
			m[y++] = Jsoup.parse(k.childNodes().get(0).toString()).text();
		}

		data.put(h, m);
		h++;
	}
	
public void iterationReport(int iLoop, String strStatus) throws Throwable {

		File file = new File("Results/HTML" + suiteStartTime + "/" + strTestName + "_Results" + ".html");
		Writer writer = null;

		try {
			iStepNo = 0;
			writer = new FileWriter(file, true);
			writer.write("<tr class='content2'>");
			if (strStatus.contains("Start")) {
				writer.write("<td colspan='2' height='25' ><b> Test Case " + iLoop + "</b></td>");
				writer.write("<td colspan='4' height='25' ><b> " + strStatus + "</b></td>");
			} else if (strStatus.contains("")) {
				writer.write("<td colspan='2' height='25' ><b>  Test Case " + iLoop + " </b></td>");
				writer.write("<td colspan='4' height='25' ><b> Completed</b></td>");
			}

			writer.write("</tr> ");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void openBrowser() {

		if (browsertype.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
			i = i + 1;
		} else if (browsertype.equalsIgnoreCase("ie")) {
			File file = new File("Drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			webDriver = new InternetExplorerDriver();
			i = i + 1;
		} else if (browsertype.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_2.38.exe");
			webDriver = new ChromeDriver();
			i = i + 1;

		} else if (browsertype.equalsIgnoreCase("browserstack")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
			i = i + 1;

		}

		driver = new EventFiringWebDriver(webDriver);
		System.out.println("url opened" + URL);
		MyListener myListener = new MyListener();
		driver.register(myListener);

		try {

			if (browsertype.equalsIgnoreCase("IE")) {
				driver.manage().timeouts().implicitlyWait(5 * 2, TimeUnit.SECONDS);
			} else {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}

			if (browsertype.equalsIgnoreCase("browserstack") || browsertype.equalsIgnoreCase("Firefox")
					|| browsertype.equalsIgnoreCase("IE") || browsertype.equalsIgnoreCase("Chrome")) {
				driver.manage().window().maximize();
			}

//			driver.get(URL);

		} catch (Exception e1) {
			System.out.println(e1);
		}
	}

	

	public static FirefoxProfile FirefoxDriverProfile() throws Exception 
	{
		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", "C://IN01612/WKProject//GBS_Automation//TestData//PDFDownloads");
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;

	}

	public ArrayList<String> getsheetName() 
	{
		ArrayList<String> sheets = new ArrayList<String>();
		try {
			String FilePath = System.getProperty("user.dir") + "\\Macros\\" + configProps.getProperty("MacroFile");

			FileInputStream fs = new FileInputStream(FilePath);
			Workbook wb = Workbook.getWorkbook(fs);

			String sheet = configProps.getProperty("SheetName");
			Sheet sh = wb.getSheet(sheet);
			int rows = sh.getRows();

			for (int row = 1; row < rows; row++) {

				String SheetName = sh.getCell(2, row).getContents();
				if (SheetName.equalsIgnoreCase("YES")) {

					sheets.add(sh.getCell(1, row).getContents());
				}

			}
			wb.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return sheets;

	}

	
}
