package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseTest;

public class TC01_loginTestCase extends BaseTest {

	homePage hp;
	loginPage lp;
	myAccountPage map;

	@Test(groups={"Regression","Sanity","Smoke","Master"})
	public void loginTest() throws InterruptedException {
		try {
			
			hp = new homePage(driver);
			lp = new loginPage(driver);
			map = new myAccountPage(driver);
			
			log.info("******* Started TC_01_Login Testcase *******");
			hp.clickOnMyAccount();
			log.info("Clicked on my account");
			hp.selectLoginOption();
			log.info("selected loginoption");
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
			Assert.assertTrue(map.txtMyAccountIsDisplayed());
			log.info("My Account Text is displayed");
			Assert.assertTrue(map.logoutLinkIsDisplayed());
			log.info("logout link is displayed");
			map.clickOnLogoutLink();
			log.info("clicked on logout link");
			Assert.assertTrue(hp.txtAccountLogoutIsDisplayed());
			log.info("account logout text is displayed");
		} catch (Exception e) {
			Assert.fail();
		}
		log.info("******* Finished TC_01_Login Testcase *******");
	}
}
