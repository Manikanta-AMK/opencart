package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.constants;
import utilities.filelib;

public class BaseTest {

	public ExtentReports extent;
	public ExtentTest logger;
	public WebDriver driver;
	public Logger log;
	public Properties prop;
	public ExtentSparkReporter sparkReporter;
	public filelib flib;
	// private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

	public BaseTest() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(constants.configpath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	//@Parameters({"os","browser"})
	public void initialization() { // String os, String br required while running through xml file
		log = LogManager.getLogger(this.getClass());
		PropertyConfigurator.configure(constants.log4jPath);
		
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Please initialize a valid browser in config.properties.");
		}
		
		
//		switch (br.toLowerCase()) 
//		{
//		 case "chrome": 
//			 driver = new ChromeDriver();
//			 break;
//		 case "firefox":
//			 driver = new FirefoxDriver();
//			 break;
//		 case "edge":
//			 driver = new EdgeDriver();
//             break;
//		default: System.out.println("Invalid browser name");
//			break;
//		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies(); log.info("cookies deleted");
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

//	public static WebDriver getdriver()
//	{
//		return tldriver.get();
//	}

	/*
	 * @BeforeTest public void extentReportMethod() { sparkReporter = new
	 * ExtentSparkReporter(System.getProperty("user.dir") +
	 * "/ExtentReports/Extentreport.html"); extent = new ExtentReports();
	 * extent.attachReporter(sparkReporter);
	 * sparkReporter.config().setDocumentTitle("FreeCogmentoCRM");
	 * sparkReporter.config().setReportName("Automation Test Result Report");
	 * sparkReporter.config().setTheme(Theme.DARK); extent.setSystemInfo("Author",
	 * "AMK"); extent.setSystemInfo("Os", "windows 11");
	 * extent.setSystemInfo("Browser", "Chrome"); }
	 * 
	 * @AfterMethod public void onTestFailure(ITestResult result) { if
	 * (result.getStatus() == ITestResult.FAILURE) { logger.log(Status.FAIL,
	 * MarkupHelper.createLabel(result.getName() + " - Testcase failed ",
	 * ExtentColor.RED)); logger.log(Status.FAIL,
	 * MarkupHelper.createLabel(result.getThrowable() + " - Testcase failed ",
	 * ExtentColor.RED)); } else if (result.getStatus() == ITestResult.SKIP) {
	 * logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() +
	 * " - Testcase skipped ", ExtentColor.BLUE)); logger.log(Status.SKIP,
	 * MarkupHelper.createLabel(result.getThrowable() + " - Testcase skipped ",
	 * ExtentColor.BLUE)); } else { logger.log(Status.PASS,
	 * MarkupHelper.createLabel(result.getName() + " - Testcase passed ",
	 * ExtentColor.GREEN)); } driver.quit();
	 * 
	 * 
	 * 
	 * public void setupDriver(String browserName, Method testmethod) throws
	 * Throwable { flib = new filelib(); log = Logger.getLogger("CogmentoCRM");
	 * PropertyConfigurator.configure(constants.log4jPath); WebDriver driver = null;
	 * if (browserName.equalsIgnoreCase(flib.readpropertydata("browser","chrome")))
	 * { WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * log.info("Chrome browser opened"); } else if
	 * (browserName.equalsIgnoreCase(flib.readpropertydata("browser","firefox"))) {
	 * WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver();
	 * log.info("Firefox browser opened"); } else if
	 * (browserName.equalsIgnoreCase(flib.readpropertydata("browser","edge"))) {
	 * WebDriverManager.edgedriver().setup(); driver = new EdgeDriver();
	 * log.info("Edge browser opened"); }
	 * 
	 * logger = extent.createTest(testmethod.getName());
	 * getdriver().manage().deleteAllCookies(); log.info("cookies deleted");
	 * getdriver().manage().window().maximize(); log.info("Browser maximized");
	 * getdriver().get(prop.getProperty("url")); log.info("url launched");
	 * getdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 * tldriver.set(driver);
	 * 
	 * }
	 */



//	@AfterClass
//	public void onFinish(ITestContext context) {
//		extent.flush();
//	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
