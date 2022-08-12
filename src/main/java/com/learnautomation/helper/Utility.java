package com.learnautomation.helper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}

	public static ArrayList<String> brokenImageChecker(List<WebElement> allImages) {

		ArrayList<String> brokenImage = new ArrayList<String>();

		for (WebElement ele : allImages) {
			String href = ele.getAttribute("src");

			try {
				URL url = new URL(href);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				connection.setConnectTimeout(5000);

				connection.connect();

				int code = connection.getResponseCode();

				if (code < 400) {
					System.out.println("Image Displayed - Status Code " + code + " -URL " + url + " Status "
							+ connection.getResponseMessage());
				} else {
					System.out.println("Image Broken - Status Code " + code + "- URL " + url + " Status "
							+ connection.getResponseMessage());
					brokenImage.add(href);
				}
			} catch (MalformedURLException e) {
				System.out.println("URL not formatted " + e.getMessage());
				brokenImage.add(href);
			} catch (IOException e) {
				System.out.println("Connection Failed " + e.getMessage());
				brokenImage.add(href);
			} catch (Exception e) {
				System.out.println("Something went wrong " + e.getMessage());
				brokenImage.add(href);
			}

		}

		return brokenImage;

	}

	public static ArrayList<String> brokenLinkChecker(List<WebElement> allLinks) {

		ArrayList<String> brokenLinks = new ArrayList<String>();

		for (WebElement ele : allLinks) {
			String href = ele.getAttribute("href");

			try {
				URL url = new URL(href);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				connection.setConnectTimeout(5000);

				connection.connect();

				int code = connection.getResponseCode();

				if (code < 400) {
					System.out.println("Link working - Status Code " + code + " -URL " + url + " Status "
							+ connection.getResponseMessage());
				} else {
					System.out.println("Link Broken - Status Code " + code + "- URL " + url + " Status "
							+ connection.getResponseMessage());
					brokenLinks.add(href);
				}
			} catch (MalformedURLException e) {
				System.out.println("URL not formatted " + e.getMessage());
				brokenLinks.add(href);
			} catch (IOException e) {
				System.out.println("Connection Failed " + e.getMessage());
				brokenLinks.add(href);
			} catch (Exception e) {
				System.out.println("Something went wrong " + e.getMessage());
				brokenLinks.add(href);
			}

		}

		return brokenLinks;

	}

	public static void clickOnElement(WebDriver driver, By locator) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.elementToBeClickable(locator));

		try 
		{
			highLightElement(driver, element);
			element.click();

		} catch (ElementNotInteractableException e) {

			System.out.println("WebElement Click Failed - Retry with click from JavaScript");

			waitForElement(3);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click()", element);

			// ((JavascriptExecutor)driver).executeScript("arguments[0].click()",element);

		}

	}

	public static void clickOnElement(WebDriver driver, By locator, int time) {
		WebElement element = new WebDriverWait(driver, Duration.ofSeconds(time))
				.until(ExpectedConditions.elementToBeClickable(locator));

		try {
			element.click();

		} catch (ElementNotInteractableException e) {

			System.out.println("WebElement Click Failed - Retry with click from JavaScript");

			waitForElement(3);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].click()", element);

			// ((JavascriptExecutor)driver).executeScript("arguments[0].click()",element);

		}

	}

	public static boolean enableWebElement(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		boolean status = false;

		try {
			status = (Boolean) js.executeScript("arguments[0].removeAttribute('disabled');",
					driver.findElement(locator));
		} catch (Exception e) {

			status = false;
		}

		return status;
	}

	public static void scrollForWebElement() {

	}

	public static void waitForElement(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

		}
	}

	

	public static void selectValueFromList(WebDriver driver, By locator, String value) {
		List<WebElement> allElements = driver.findElements(locator);

		for (WebElement ele : allElements) {
			System.out.println("Values from calendar is " + ele.getText());

			if (ele.getText().contains(value)) {
				ele.click();
				break;
			}
		}
	}

	public static void swithToWindowUsingIndex(WebDriver driver, int index) {

		Set<String> allWindowsAsSet = driver.getWindowHandles();

		List<String> allWindowsAsList = new ArrayList<String>(allWindowsAsSet);

		String child = allWindowsAsList.get(index);

		driver.switchTo().window(child);

	}

	public static void swithToWindowUsingTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String handle : allWindows) {
			if (driver.switchTo().window(handle).getTitle().contains(title)) {
				break;
			}
		}
	}

	public static void swithToWindowUsingURL(WebDriver driver, String urlToMatch) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String handle : allWindows) {
			if (driver.switchTo().window(handle).getCurrentUrl().contains(urlToMatch)) {
				break;
			}
		}
	}

	public static void swithToChildWindow(WebDriver driver) {
		Set<String> allWindows = driver.getWindowHandles();

		Iterator<String> itr = allWindows.iterator();

		while (itr.hasNext()) {
			String child = itr.next();

			if (!child.equals(driver.getWindowHandle())) {
				driver.switchTo().window(child);
			}

		}
	}

	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public static void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public static String captureAlertMsg(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public static void enterAlertMsg(WebDriver driver, String msgToEnter) {
		driver.switchTo().alert().sendKeys(msgToEnter);
	}

	public static void swithToFrameUsingIndex(WebDriver driver, int indexOfIframe) {
		driver.switchTo().frame(indexOfIframe);
	}

	public static void swithToFrameUsingNameOrId(WebDriver driver, String nameOrIdOfFrame) {
		driver.switchTo().frame(nameOrIdOfFrame);
	}

	public static void swithToFrameUsingWebElement(WebDriver driver, WebElement ele) {
		driver.switchTo().frame(ele);
	}

	public static void swithBackToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public static WebElement waitForWebElement(WebDriver driver, int time, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static boolean waitForTitle(WebDriver driver, int time, String titleToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		return wait.until(ExpectedConditions.titleContains(titleToWait));
	}

	public static Alert waitForAlert(WebDriver driver, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void captureScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File dest = new File("./Screenshots/OrangeHRM_" + getCurrentDateTime() + ".png");

		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println("Something went wrong while copying file " + e.getMessage());
		}

	}

	
	public static String captureScreenshotInBase64(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;

		return ts.getScreenshotAs(OutputType.BASE64);

	}
	
	public static String getCurrentDateTime() {

		return new SimpleDateFormat("HH_mm_ss_dd_MMM_yyyy").format(new Date());

		/*
		 * Date currentDate=new Date();
		 * 
		 * SimpleDateFormat myDateFormat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		 * 
		 * System.out.println(myDateFormat.format(currentDate));
		 * 
		 * 
		 */

	}

	//For Dropdowns using By Locators
	public static void DropdownSelectByValue(WebDriver driver,By dropdown, String Value)
	{
		Select sel=new Select(driver.findElement(dropdown));
		sel.selectByValue(Value);
	}
	public static void DropdownSelectByIndex(WebDriver driver,By dropdown, String Index)
	{
		Select sel=new Select(driver.findElement(dropdown));
		sel.selectByValue(Index);
	}
	public static void DropdownSelectByVisibleText(WebDriver driver,By dropdown, String VisibleText)
	{
		Select sel=new Select(driver.findElement(dropdown));
		sel.selectByValue(VisibleText);
	}
	
	//For Dropdowns using WebElements
	public static void DropdownSelectByValue(WebDriver driver,WebElement dropdown, String Value)
	{
		Select sel=new Select(dropdown);
		sel.selectByValue(Value);
	}
	public static void DropdownSelectByIndex(WebDriver driver,WebElement dropdown, String Index)
	{
		Select sel=new Select(dropdown);
		sel.selectByValue(Index);
	}
	public static void DropdownSelectByVisibleText(WebDriver driver,WebElement dropdown, String VisibleText)
	{
		Select sel=new Select( dropdown);
		sel.selectByValue(VisibleText);
	}
	




}
