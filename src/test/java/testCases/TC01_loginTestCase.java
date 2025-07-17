package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseTest;

@Test
public class TC01_loginTestCase extends BaseTest {

	homePage hp = new homePage(driver);
	loginPage lp = new loginPage(driver);
	myAccountPage map = new myAccountPage(driver);

	public void loginTest() throws InterruptedException {
		try {
			log.info("******* Finished TC_01_Login Testcase *******");
			hp = new homePage(driver);
			hp.clickOnMyAccount();
			log.info("Clicked on my account");
			hp.selectLoginOption();
			log.info("selected loginoption");
			lp = new loginPage(driver);
			if (lp.validateReturningCustomerText().isDisplayed()) {
				Assert.assertEquals(lp.validateReturningCustomerText().getText(), "Returning Customer",
						"Return Customer Text is mismatching");
			} else {
				log.info("Return Customer text is not displayed");
			}
			lp.enterLoginCredentials(prop.getProperty("mail"), prop.getProperty("password"));
			log.info("login credentials enterd");
			lp.clickOnLoginBtn();
			log.info("clicked on login button");
			map.txtMyAccountIsDisplayed();
			log.info("My Account Text is displayed");
//		Thread.sleep(2000);
//		map.clickOnMyAccountbutton();
//		log.info("clicked on my account");
//		map.logoutOptionIsDisplayed();
//		log.info("logout option is displayed");
		} catch (Exception e) {
			Assert.fail();
		}
		log.info("******* Finished TC_01_Login Testcase *******");
	}

}
