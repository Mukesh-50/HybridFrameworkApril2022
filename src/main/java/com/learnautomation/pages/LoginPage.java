package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{

	WebDriver driver;
	
	  public LoginPage(WebDriver driver) 
	  {
		  this.driver=driver;
	  }
	

	By username=By.id("txtUsername");
	
	By password=By.name("txtPassword");
	
	By loginButton=By.xpath("//input[@id='btnLogin']");
	

	
	public Dashboard loginToApplication(String uname,String pass)
	{
		
		driver.findElement(username).sendKeys(uname);
		
		driver.findElement(password).sendKeys(pass);
		
		driver.findElement(loginButton).click();
		
		Dashboard dash=new Dashboard(driver);
		
		return dash;
		
	}
	
	
}
