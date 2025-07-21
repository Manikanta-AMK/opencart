package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.accountCreationSuccessMsgPage;
import pageObjects.homePage;
import pageObjects.registrationPage;
import testBase.BaseTest;
import utilities.commonlib;

public class TC01_registrationTestcase extends BaseTest {

	commonlib cmLib = new commonlib();
	

	@Test(groups={"Regression","Sanity","Smoke","Master"})
	public void Registration() throws InterruptedException {
		log.info("******* Started TC_01_registrationTestcase *******");
		try {
			homePage hp = new homePage(driver);
			accountCreationSuccessMsgPage acsmp = new accountCreationSuccessMsgPage(driver);
			log.info("Clicking on My account");
			hp.clickOnMyAccount();
			log.info("Clicking on Register option");
			hp.selectRegisterOption();
			registrationPage registerationpage = new registrationPage(driver);
			log.info("Entering Customer details");
			registerationpage.enterTxtFirtName("firstname");
			registerationpage.enterTxtLastName("lastname");
			registerationpage.enterEmail(cmLib.randomMailId());
			registerationpage.enterPhonenumber(cmLib.randomPhoneNumber());
			registerationpage.enterPassword("password");
			registerationpage.enterConfirmPassword("password");
			registerationpage.clickOnPrivacyChkBox();
			log.info("Clicking on continue button");
			registerationpage.clickOnContinueButton();
			log.info("Validating succes messge and clicking on continue button");
			String msg = acsmp.getAccountCreationMessage();
			if(msg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}else {
				log.error("test failed");
				log.debug("debug logs");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		log.info("******* Finished TC_01_registrationTestcase *******");

	}
}
