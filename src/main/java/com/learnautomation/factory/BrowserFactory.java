package com.learnautomation.factory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	public static WebDriver startBrowser(String browser) {
		WebDriver driver = null;

		if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("GC")
				|| browser.equalsIgnoreCase("Google Chrome")) {
			
			WebDriverManager.chromedriver().setup();

			ChromeOptions opt = new ChromeOptions();

			opt.setAcceptInsecureCerts(true);

			Map<String, Object> myMap = new HashMap<String, Object>();

			myMap.put("download.default_directory", System.getProperty("user.dir") + "/downloads/");

			opt.setExperimentalOption("prefs", myMap);

			driver = new ChromeDriver(opt);
		} else if (browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Firefox")
				|| browser.equalsIgnoreCase("Mozila")) {
			
			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions opt = new FirefoxOptions();

			opt.setAcceptInsecureCerts(true);

			driver = new FirefoxDriver(opt);
		} else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("EG")
				|| browser.equalsIgnoreCase("Microsoft Edge")) {
			WebDriverManager.edgedriver().setup();

			EdgeOptions opt = new EdgeOptions();

			opt.setAcceptInsecureCerts(true);

			driver = new EdgeDriver(opt);
		} else {
			System.out.println("Sorry we do not support this browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return driver;

	}

	public static void closeBrowser(WebDriver driver) {
		
		driver.quit();
		
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM chromedriver.exe /T");
			runtime.exec("taskkill /F /IM geckodriver.exe /T");
			runtime.exec("taskkill /F /IM msedgedriver.exe /T");
			
		} catch (Exception e) {
			e.getMessage();
		}

	}

}
