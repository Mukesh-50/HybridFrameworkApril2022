package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard {

	WebDriver driver;
	
	public Dashboard(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By welcome=By.id("welcome");
	
	By logoutText=By.linkText("Logout");
	
	
	public void logOut()
	{
		driver.findElement(welcome).click();

		try 
		{
			driver.findElement(logoutText).click();
			
		} catch (Exception e) 
		{
			System.out.println("Click on logout failed "+e.getMessage());
			
			System.out.println("Trying again");
			
			driver.findElement(welcome).click();
			
			driver.findElement(logoutText).click();
		}

	}
	
}
