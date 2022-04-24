package com.learnautomation.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learnautomation.helper.Utility;

public class ExtentManager 
{
	
	public static ExtentReports extent;
	
	
	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			return createInstance();
		}
		else
		{
			return extent;
		}
	}
	
	
	public static ExtentReports createInstance()
	{
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/AutomationRepor"+Utility.getCurrentDateTime()+".html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("OrangeHRM Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
	}

}
