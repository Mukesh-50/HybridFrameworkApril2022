package com.learnautomation.dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	
	@DataProvider(name="login")
	public static Object[][] getData() throws IOException
	{
		return	ExcelUtility.getDataFromSheet("LoginData");
	}
	
	@DataProvider(name="User")
	public static Object[][] getDataForUser() throws IOException
	{
		return	ExcelUtility.getDataFromSheet("User");
	}
	
	
	@DataProvider(name="Sales")
	public static Object[][] getDataForSales() throws IOException
	{
		return	ExcelUtility.getDataFromSheet("Sales");
	}
	
	
	
	@DataProvider(name="Product")
	public static Object[][] getDataForProduct() throws IOException
	{
		return	ExcelUtility.getDataFromSheet("Product");
	}
	
}
