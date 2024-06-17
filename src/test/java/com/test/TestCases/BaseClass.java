package com.test.TestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.test.Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	ReadConfig readconfig=new ReadConfig();
	String url=readconfig.getBaseUrl();
	String browser= readconfig.getBrowser();
	public String emailAddress = readconfig.getEmail() ;
	String password = readconfig.getPassword();

	public WebDriver driver;
	public Logger log;

	@BeforeClass
	public void setup() {
		
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			break;
		default :
			driver =null;
			break;

			//case "
		}
		
		driver.get(url);
		//implicit wait of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
//		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,500)","");
//		//for logging 
		log = LogManager.getLogger("test");

	}
	@AfterClass
	public void teardown() {

//		driver.close();
		driver.quit();
	}

	public void captureScreenShot(WebDriver driver,String testName) throws IOException
	{
		//step1: convert webdriver object to TakesScreenshot interface
		TakesScreenshot screenshot = ((TakesScreenshot)driver);

		//step2: call getScreenshotAs method to create image file

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");

		//step3: copy image file to destination
		FileUtils.copyFile(src, dest);


	}


}