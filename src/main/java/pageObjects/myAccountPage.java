package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.reactivex.rxjava3.exceptions.Exceptions;
import testBase.basePage;

public class myAccountPage extends basePage {

	public myAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement myAccount;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']") WebElement logoutOption;
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement  txtMyAccount;
	
	
	
	public void clickOnMyAccountbutton()
	{
		myAccount.click();
	}
	
	public boolean  logoutOptionIsDisplayed()
	{
		try {
		return logoutOption.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean  txtMyAccountIsDisplayed()
	{
		try {
		return (txtMyAccount.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
}
