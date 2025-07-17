package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.basePage;

public class homePage extends basePage {

	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']") WebElement myAccountDropDown;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement registerOption;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement loginOption;
	
	public void clickOnMyAccount()
	{
		myAccountDropDown.click();
	}
	
	public void selectRegisterOption()
	{
		registerOption.click();
	}
	
	public void selectLoginOption()
	{
		loginOption.click();
	}
	
}
