package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.basePage;

public class registrationPage  extends basePage{
	
	WebDriver driver;
	public registrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']") WebElement myAccountDropDown;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement registerOption;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") WebElement loginOption;
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtBxFirtName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtBxLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtBxEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtBxTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtBxPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtBxConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement privacyChkBox;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//a[normalize-space()='login page']") WebElement loginpageLink;
	
	public void clickOnMyAccount()
	{
		myAccountDropDown.click();
	}
	public void selectloginOption()
	{
		loginOption.click();
	}
	public void selectRegisterOption()
	{
		registerOption.click();
	}
	public void enterTxtFirtName(String firstname)
	{
		txtBxFirtName.sendKeys(firstname);
	}
	public void enterTxtLastName(String lastName)
	{
		txtBxLastName.sendKeys(lastName);
	}
	public void enterEmail(String email)
	{
		txtBxEmail.sendKeys(email);
	}
	public void enterPhonenumber(String email)
	{
		txtBxTelephone.sendKeys(email);
	}
	public void enterPassword(String password)
	{
		txtBxPassword.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmpassword)
	{
		txtBxConfirmPassword.sendKeys(confirmpassword);
	}
	public void clickOnPrivacyChkBox()
	{
		privacyChkBox.click();
	}
	public void clickOnContinueButton()
	{
		btnContinue.click();
	}
	public void clickOnLoginPageLink()
	{
		loginpageLink.click();
	}
	
//	public void clickOnMyAccount()
//	{
//		myAccount.click();
//	}
	
	

}
