package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.registrationPage;
import testBase.BaseTest;

public class TC_01_registrationTestcase{

	public WebDriver driver;
	
//	public void setup()
//	{
//		initialization();
//	}
	
	@Test()
	public void Registration()
	{
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		registrationPage registerationpage = new registrationPage(driver);
		registerationpage.clickOnMyAccount();
		registerationpage.selectRegisterOption();
		registerationpage.enterTxtFirtName("firstname");
		registerationpage.enterTxtLastName("lastname");
		registerationpage.enterEmail("email");
		registerationpage.enterPhonenumber("12304569887");
		registerationpage.enterPassword("password");
		registerationpage.enterConfirmPassword("confirmpassword");
		registerationpage.clickOnPrivacyChkBox();
		registerationpage.clickOnContinueButton();
	}
}
