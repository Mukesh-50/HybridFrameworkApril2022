package com.learnautomation.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	static XSSFWorkbook wb;
	
	public static Object[][] getDataFromSheet(String sheetName)
	{
		
		System.out.println("************ Loading Excel Data *******************");
		
		Object [][]arr=null;
		try 
		{
			wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/Data.xlsx")));
			
			XSSFSheet sh1=wb.getSheet(sheetName);

			int row=sh1.getPhysicalNumberOfRows();
			
			int column=sh1.getRow(0).getPhysicalNumberOfCells();
			
			arr=new Object[row][column];
			
			for(int i=0;i<row;i++)
			{
				for(int j=0;j<column;j++)
				{
					arr[i][j]=getData(sheetName,i,j);
					
				}
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println("File not found "+e.getMessage());
			
		} catch (IOException e) 
		{
			System.out.println("Could not load file "+e.getMessage());
		}
		
		System.out.println("************ Data Loaded *******************");
		
		return arr;
		
	}
	
	
	public static String getData(String sheetName,int row,int column)
	{

			XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
			String data="";
			
			if(cell.getCellType()==CellType.STRING)
			{
				data=cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC)
			{
				double dataInDouble=cell.getNumericCellValue();
				data=String.valueOf(dataInDouble);
			}
			else if(cell.getCellType()==CellType.BLANK)
			{
				data="";
			}
			
			return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
