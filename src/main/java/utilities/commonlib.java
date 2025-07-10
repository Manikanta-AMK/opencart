package utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.io.Files;

import net.bytebuddy.utility.RandomString;
import testBase.BaseTest;

public class commonlib {
	
	
	
	/*public void backNavigation()
	{
		driver.navigate().back();
	}
	
	public void frontnavigation()
	{
		driver.navigate().forward();
	}
	
	public void pageRefresh()
	{
		driver.navigate().refresh();
	}*/
	
	public static void elementStatus(int checkType, WebElement element, String elementName)
	{
		switch(checkType)
		{
		case 1: 
			try {
				element.isDisplayed();
				suiteListener.test.info(MarkupHelper.createLabel(elementName+" is displayed", ExtentColor.ORANGE));
				
			}
			catch(Exception e)
			{
				suiteListener.test.info(MarkupHelper.createLabel(elementName+" is not displayed", ExtentColor.GREY));
			}
			break;
			
		case 2:
			try {
				element.isEnabled();
				suiteListener.test.info(MarkupHelper.createLabel(elementName+" is enabled", ExtentColor.ORANGE));
			}
			catch(Exception e)
			{
				suiteListener.test.info(MarkupHelper.createLabel(elementName+" is not disabled", ExtentColor.PINK));
			}
		    break;
		
		case 3:
			try {
				element.isSelected();
				suiteListener.test.info(MarkupHelper.createLabel(elementName+" is selected", ExtentColor.ORANGE));
			}
		    catch(Exception e)
			{
		    	suiteListener.test.info(MarkupHelper.createLabel(elementName+" is not selected", ExtentColor.YELLOW));
			}
		    break;
		}
	}

	/*public void pageTitleVerification(String actual, String expected, String pagetitle)
	{
		Assert.assertEquals(actual, expected);
		
		if(actual.equals(expected))
		{
			Assert.assertEquals(pagetitle+" is displayed, passed", true);
		}else {
			Assert.assertEquals(pagetitle+" is not displayed, failed", false);
		}
	}*/
	

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
	/*
	public void ActionsClass(WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}*/
	
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


//	public  static void screenShots(String  screenshotName) throws IOException
//	{
//		TakesScreenshot ts =(TakesScreenshot) BaseTest.driver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		String dest = "./screenshots/"+new Date().getTime()+".png";
//		File destination = new File(dest);
//		try {
//			Files.copy(src, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//public static String path = "C:\\Users\\phani\\eclipse-workspace\\CogmentoCRM_Free\\screenshots\\";
//	
//	public static  File takeScreenshot(String screenshotName) {
//		String filename = screenshotName + ".png";
//		TakesScreenshot ts = (TakesScreenshot) baseTest.driver;
//		File srcFile = ts.getScreenshotAs(OutputType.FILE);
//		String screenshotpath = path + filename;
//
//		File dest = new File(screenshotpath);
//		try {
//			Files.copy(srcFile, dest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return dest;
//
//	}
	
	public static  void takeScreenshots(WebDriver driver, String testName) {
        try {
            // Capture screenshot and save to file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = path + testName + "_" + new Date().getTime() + ".png";
            FileHandler.copy(screenshot, new File(screenshotPath));
            System.out.println("Screenshot taken for failed test: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
    }

}
