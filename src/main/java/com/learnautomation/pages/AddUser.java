package com.learnautomation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.learnautomation.helper.Utility;

public class AddUser {

	public static String ActualSaveMessage="";
	WebDriver driver;
	
	public AddUser(WebDriver driver)
	{
		this.driver=driver;
		
	}
	
	By AdminButton=By.id("menu_admin_viewAdminModule");
	By Addbutton=By.name("btnAdd");
	By EmployeeNameTextBox=By.id("systemUser_employeeName_empName");
	By UserNameTextBox=By.id("systemUser_userName");
	By PassWordTextBox=By.id("systemUser_password");
	By ConfirmPWDTextBox=By.id("systemUser_confirmPassword");
	By UserRoleDropDown=By.id("systemUser_userType");
	By SaveButton=By.xpath("//input[@id='btnSave']");
	By SavedMessage=By.xpath(".//div[contains(@class,'message success fadable')]");
	By AlreadyExists=By.xpath("//span[@for='systemUser_userName']");
	public void UserDetails(String Username, String Password, String UserRole,String EmpName)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
	try
	{
		wait.until(ExpectedConditions.elementToBeClickable(AdminButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Addbutton)).click();
	}
	catch(Exception e)
	{	
		System.out.println("Failed to find Add button. Trying again "+e.getMessage());
		//driver.findElement(AdminButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(Addbutton)).click();
	}
	if(Username==null && Password==null && UserRole==null && EmpName==null)
	{
		System.out.println("Null User data");
		throw new SkipException("Null User Data");
	}
	else
	{
	wait.until(ExpectedConditions.elementToBeClickable(EmployeeNameTextBox)).sendKeys(EmpName);
	wait.until(ExpectedConditions.elementToBeClickable(UserNameTextBox)).sendKeys(Username);
	wait.until(ExpectedConditions.elementToBeClickable(PassWordTextBox)).sendKeys(Password);
	wait.until(ExpectedConditions.elementToBeClickable(ConfirmPWDTextBox)).sendKeys(Password);
	Utility.DropdownSelectByValue(driver,wait.until(ExpectedConditions.elementToBeClickable(UserRoleDropDown)), UserRole);
	//wait.until(ExpectedConditions.elementToBeClickable(SaveButton)).click();
	Utility.clickOnElement(driver, SaveButton);
	WebElement smsg=wait.until(ExpectedConditions.presenceOfElementLocated(SavedMessage));
	ActualSaveMessage=smsg.getText();
	System.out.println("Actual Save message is :" + ActualSaveMessage);
	}
	}

}
