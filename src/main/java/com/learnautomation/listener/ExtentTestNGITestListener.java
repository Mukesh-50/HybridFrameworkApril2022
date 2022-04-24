package com.learnautomation.listener;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.learnautomation.helper.Utility;


public class ExtentTestNGITestListener implements ITestListener{
	
	static ExtentReports extent=ExtentManager.getInstance();
	ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();
	

	public synchronized void onFinish(ITestContext context) {
		System.out.println("LOG:INFO- Report getting generated");
		extent.flush();
		System.out.println("LOG:INFO- Report Generated");
	}
	
	
	public synchronized void onTestStart(ITestResult result) {
		
		ExtentTest child=extent.createTest(result.getMethod().getMethodName());
		parentTest.set(child);
	}


	public synchronized void onTestSuccess(ITestResult result) {	
		parentTest.get().pass("Test Passed");
	}

	
	public synchronized void onTestFailure(ITestResult result) 
	{
		
		WebDriver driver=null;
		try 
		{
			//driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		
			IClass cls=result.getTestClass();
			
			Class c1=cls.getRealClass();
			
			Field f1=c1.getDeclaredField("driver");
			
		    Object o1=f1.get(result.getInstance());
			
		    driver=(WebDriver)o1;
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		parentTest.get().fail("Test Failed "+result.getThrowable().getMessage(),
				MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.captureScreenshotInBase64(driver)).build());
	
	}

	
	public synchronized void onTestSkipped(ITestResult result) {
		parentTest.get().skip("Test Skipped");
	}


	
	
	
	
}
