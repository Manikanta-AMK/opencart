package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.basePage;

public class myAccountPage extends basePage {

	public myAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement myAccountdropdown;
//	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logoutLink;
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") WebElement lnkLogout;
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement  txtMyAccount;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']") WebElement logoutLinkFromDropdown;
	
	public void clickOnMyAccountdropdown()
	{
		myAccountdropdown.click();
	}
	
	public boolean  logoutLinkIsDisplayed()
	{
		try {
		return lnkLogout.isDisplayed();
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
	
	public void clickOnLogoutLink()
	{
		lnkLogout.click();
	}
	
	public void logout()
	{
		logoutLinkFromDropdown.click();
	}
}
