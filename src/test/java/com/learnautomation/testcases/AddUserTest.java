package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.learnautomation.base.BaseClass;
import com.learnautomation.dataProvider.ConfigUtlity;
import com.learnautomation.dataProvider.DataProviders;
import com.learnautomation.helper.Utility;
import com.learnautomation.pages.AddUser;
import com.learnautomation.pages.Dashboard;
import com.learnautomation.pages.LoginPage;

public class AddUserTest extends BaseClass{
	
	LoginPage login;
	Dashboard dash;
	AddUser adusr;
	public WebDriver driver;
	
	@BeforeMethod
	public void setupDriver()
	{
		driver=getDriver();
	}

	@Test(description = "Login to application", priority=1,dataProvider = "login",dataProviderClass = DataProviders.class)
	public void loginApp(String uname,String pass)
	{
		
		if(uname!=null && pass!=null)
		{
		login=new LoginPage(driver);
		
		dash=login.loginToApplication(uname,pass);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed");
		}
		else
		{
			throw new SkipException("Null Login Data");
		}
	}
	
	@Test(description="Add Users", priority=2, dataProvider="User", dataProviderClass = DataProviders.class)
	public void addUserDetails(String Uname, String Pwd, String URole, String EmpName)
	{
		adusr=new AddUser(driver);
		adusr.UserDetails(Uname, Pwd, URole, EmpName);
		Utility.waitForElement(1);
		Assert.assertTrue(AddUser.ActualSaveMessage.contains(ConfigUtlity.getValue("ExpectedSaveMessage")));
		Utility.captureScreenshot(driver);
	}
	
	
	@Test(description = "Logout from application",dependsOnMethods = "loginApp", priority =3)
	public void logoutTest()
	{
		dash.logOut();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed");
	}
	

}
