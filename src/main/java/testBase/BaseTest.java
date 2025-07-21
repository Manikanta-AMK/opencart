package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	public static WebDriver driver;
	public Logger log;
	public Properties prop;
	public ExtentSparkReporter sparkReporter;
	public filelib flib;

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

	@BeforeClass(groups = { "Regression", "Sanity", "Smoke", "Master", })
	@Parameters({ "os", "browser" })                           // String os, String br required while running through xml file
	public void initialization(String os, String browser) throws MalformedURLException {
		log = LogManager.getLogger(this.getClass());
		PropertyConfigurator.configure(constants.log4jPath);

		// remote server
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities Capabilities = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows"))
			{
				Capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				Capabilities.setPlatform(Platform.MAC);
			} else {
				log.info("no matching OS");
				return;
			}
			switch (browser.toLowerCase()) 
			{
			case "chrome":
				Capabilities.setBrowserName("chrome");
				break;
			case "firefox":
				Capabilities.setBrowserName("edge");
				break;
			case "edge":
				Capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid browser name");
				return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/"),Capabilities);
		}

		// browser for local
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
//			 browser = prop.getProperty("browser");
//				if (browser.equalsIgnoreCase("chrome")) {
//					WebDriverManager.chromedriver().setup();
//					driver = new ChromeDriver();
//				} else if (browser.equalsIgnoreCase("edge")) {
//					WebDriverManager.edgedriver().setup();
//					driver = new EdgeDriver();
//				} else if (browser.equalsIgnoreCase("firefox")) {
//					WebDriverManager.firefoxdriver().setup();
//					driver = new FirefoxDriver();
//				} else {
//					System.out.println("Please initialize a valid browser in config.properties.");
//				}
			
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser name");
				return;
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		log.info("cookies deleted");
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups = { "Regression", "Sanity", "Smoke", "Master" })
	public void teardown() {
		driver.quit();
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
