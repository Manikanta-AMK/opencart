package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.BaseTest;
import utilities.dataprovider;

public class TC03_LoginDDT extends BaseTest {

	homePage hp;
	loginPage lp;
	myAccountPage map;

	@Test(dataProvider = "LoginData", dataProviderClass = dataprovider.class)
	public void Verify_LoginDDT(String email, String password, String exp) {

		try {
			hp = new homePage(driver);
			lp = new loginPage(driver);
			map = new myAccountPage(driver);

			log.info("******* Finished TC_03_Verify_LoginDDT *******");
			hp.clickOnMyAccount();
			log.info("Clicked on my account");
			hp.selectLoginOption();
			log.info("selected loginoption");
			if (lp.validateReturningCustomerText().isDisplayed()) {
				Assert.assertEquals(lp.validateReturningCustomerText().getText(), "Returning Customer",
						"Return Customer Text is mismatching");
				log.info("Returning Customer is displayed");
			} else {
				log.info("Return Customer text is not displayed");
			}
			
			lp.enterLoginCredentials(email, password);
			log.info("login credentials enterd");
			lp.clickOnLoginBtn();
			log.info("clicked on login button");

			if (exp.equalsIgnoreCase("valid")) {
				if (lp.validateWarningMessage() == true) {
					log.info("warning message is not displayed ");
				} else if (map.txtMyAccountIsDisplayed() == true) {
					log.info("My Account Text is displayed");
					Assert.assertTrue(map.logoutLinkIsDisplayed());
					log.info("logout link is displayed");
					map.clickOnLogoutLink();
					log.info("clicked on logout link");
					Assert.assertTrue(hp.txtAccountLogoutIsDisplayed());
					log.info("account logout text is displayed");
					Assert.assertTrue(true);
				} else {
				Assert.assertTrue(false);
			}
		}

			if (exp.equalsIgnoreCase("invalid")) {
				if (lp.validateWarningMessage() == true) {
					log.info("warning message is displayed ");
				} else if (map.txtMyAccountIsDisplayed() == true) {
					log.info("My Account Text is displayed");
					Assert.assertTrue(map.logoutLinkIsDisplayed());
					log.info("logout link is displayed");
					map.clickOnLogoutLink();
					log.info("clicked on logout link");
					Assert.assertTrue(hp.txtAccountLogoutIsDisplayed());
					log.info("account logout text is displayed");
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		log.info("******* Finished TC_03_Verify_LoginDDT *******");
	}
}
