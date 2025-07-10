package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.basePage;

public class accountCreationSuccessMsgPage  extends basePage{

	public accountCreationSuccessMsgPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	@FindBy(xpath="(//a[normalize-space()='Continue'])[1]") WebElement btnContinueFromRegistrationPage;
	
	public String getAccountCreationMessage() {
	    String message = "";

	    try {
	        if (msgConfirmation.isDisplayed()) {
	            message = msgConfirmation.getText();
	            btnContinueFromRegistrationPage.click();  
	        }
	    } catch (Exception e) {
	        message = "Exception occurred: " + e.getMessage();
	    }

	    return message;
	}
}
