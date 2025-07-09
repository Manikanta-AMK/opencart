package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.BaseTest;


public class suiteListener  implements ITestListener, IAnnotationTransformer{

	public static ExtentTest test;
	public static ExtentReports extent;
	WebDriver driver = BaseTest.getdriver(); 
	
	public void onTestFailure(ITestResult result) {
	    

	    if (driver != null) {
	        String filename = System.getProperty("user.dir") + File.separator + "screenshots" +
	                File.separator + result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(src, new File(filename));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("WebDriver is null. Screenshot not captured.");
	    }
	}

	
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName()+"method passed",true);
		String filename = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
	    File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(file, new File(filename+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
              annotation.setRetryAnalyzer(retryAnalyzer.class);
	}
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.INFO, result.getName() + "method started");
	}
}
