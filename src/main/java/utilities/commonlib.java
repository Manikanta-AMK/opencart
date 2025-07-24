package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import testBase.BaseTest;

public class commonlib extends BaseTest {
	
	//ExtentReportManager exrp = new ExtentReportManager();
	
	
	public void backNavigation()
	{
		driver.navigate().back();
		System.out.println("navigate back");
	}
	
	public void frontnavigation()
	{
		driver.navigate().forward();
	}
	
	public void pageRefresh()
	{
		driver.navigate().refresh();
	}
	
	public static void elementStatus(int checkType, WebElement element, String elementName)
	{
		switch(checkType)
		{
		case 1: 
			try {
				element.isDisplayed();
				ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is displayed", ExtentColor.ORANGE));
				
			}
			catch(Exception e)
			{
				ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is not displayed", ExtentColor.GREY));
			}
			break;
			
		case 2:
			try {
				element.isEnabled();
				ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is enabled", ExtentColor.ORANGE));
			}
			catch(Exception e)
			{
				ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is  disabled", ExtentColor.PINK));
			}
		    break;
		
		case 3:
			try {
				element.isSelected();
				ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is selected", ExtentColor.ORANGE));
			}
		    catch(Exception e)
			{
		    	ExtentReportManager.test.info(MarkupHelper.createLabel(elementName+" is not selected", ExtentColor.YELLOW));
			}
		    break;
		}
	}

	public void pageTitleVerification(String actual, String expected, String pagetitle)
	{
		Assert.assertEquals(actual, expected);
		
		if(actual.equals(expected))
		{
			Assert.assertEquals(pagetitle+" is displayed, passed", true);
		}else {
			Assert.assertEquals(pagetitle+" is not displayed, failed", false);
		}
	}
	

	public void staticDropDownVisibleTxt(WebElement element, String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void staticDropDownByIndex(WebElement element, String text, int number)
	{
		Select sel = new Select(element);
        sel.selectByIndex(number);
	}

	public void staticDropDownByValue(String value, WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void staticDropDownDeSelectAll(WebElement element)
	{
		Select sel = new Select(element);
		sel.deselectAll();
	}

	public void staticDropDownByIndex(int index, WebElement element)
	{
		Select sel = new Select(element);
		sel.deselectByIndex(index);
	}
	
	public void ActionsClass(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}
	
   public String randomMailId()
	{
		 String randomAlphaNumberic = RandomStringUtils.randomAlphanumeric(6);
		 return randomAlphaNumberic +"@mail.com";
	}
   
   public String randomPhoneNumber()
	{
		 String randomAlphaNumberic = RandomStringUtils.randomNumeric(10).toString();
		 return randomAlphaNumberic +"@mail.com";
	}
	
	public void mousehover(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public Robot robotclassEnter() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		return robot;
	}
	
	public void robotclassDown() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
	}

	public void robotclassUp() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_UP);
	}

	public void robotclassRight() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
	}

	public void robotclassLeft() throws AWTException
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
	}

}
