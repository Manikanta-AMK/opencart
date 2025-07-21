package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.basePage;

public class loginPage extends basePage {

	public loginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
	WebElement txtReturningCustomer;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtBxEmail;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtBxPassword;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	@FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
	WebElement linkForgotPassword;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement msgWarning;

	public WebElement validateReturningCustomerText() {
		return txtReturningCustomer;
	}

	public void enterLoginCredentials(String email, String password) {
		txtBxEmail.sendKeys(email);
		txtBxPassword.sendKeys(password);
	}

	public void clickOnLoginBtn() {
		btnLogin.click();
	}

	public void clickOnForgotPasswordLink() {
		linkForgotPassword.click();
	}

	public boolean validateWarningMessageIsDisplayed() {
		try {
			return msgWarning.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
