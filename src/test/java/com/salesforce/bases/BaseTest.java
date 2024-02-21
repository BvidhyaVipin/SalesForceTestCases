package com.salesforce.bases;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.google.common.io.Files;
import com.salesforce.utilities.ExtentReportsUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver=null;
	protected Wait<WebDriver>wait =null;
	protected Logger Base_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	
	public void launchWebdriver(String browsername) {
		switch(browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			Base_log.info("browser instance chrome is created");
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			Base_log.info("browser instance firefox is created");
			driver.manage().window().maximize();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
			Base_log.info("browser instance safari is created");
			driver.manage().window().maximize();
			break;
		case "opera":
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
			Base_log.info("browser instance opera is created");
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			Base_log.info("browser instance edge is created");
			driver.manage().window().maximize();
			break;
		default:
			Base_log.info("unsupported driver");
		}
		
	}
	public String getTitle(WebDriver driver) {
		String titleOfPage=driver.getTitle();
		Base_log.info("title of page extracted");
		extent.logTestInfo("title of page extracted");
		return titleOfPage;
		
	}
	
	public void switchToAlert(WebDriver driver) throws InterruptedException {
		Alert alert=driver.switchTo().alert();
		alert.wait();
		
	}
		
	public String getTextFromAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		Base_log.info("switched to alert");
		extent.logTestInfo("switched to alert");
		String alertmessage=alert.getText();
		return alertmessage;
		
	}	
	public String getTextFromElement(WebElement ele,String obj) {
		String data=ele.getText();
		Base_log.info("text is extracted from "+obj);
		extent.logTestInfo("text is extracted from "+obj);
		return data;
				
	}		
	public void acceptAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		alert.accept();
		Base_log.info("alert accepted");
		extent.logTestInfo("alert accepted");
		
	}
	public void dismisstAlert(WebDriver driver) {
		Alert alert=driver.switchTo().alert();
		alert.dismiss();;
		Base_log.info("alert dismissed");
		extent.logTestInfo("alert dismissed");
		
	}
	public void geturl(String url) {
		driver.get(url);
		Base_log.info(url + " is entered");
	}
	public void enterData(WebElement element,String data,String objectname ) {
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			Base_log.info(objectname+" entered");
			extent.logTestInfo(objectname+" entered");
		}
		else {
			Base_log.info(objectname+" not entered");
			extent.logTestInfo(objectname+" not entered");
		}
		
	}
	public void clickElement(WebElement element,String objectname) {
		if(element.isEnabled()) {
		element.click();
		Base_log.info(objectname+" button is clicked");
		extent.logTestInfo(objectname+" button is clicked");
	    }
	else {
		Base_log.info(objectname + " not enabled");
		extent.logTestInfo(objectname + " not enabled");
		
	}
	}
	public void close(WebDriver driver) {
		driver.close();
		Base_log.info("Browser instance closed");
		extent.logTestInfo("Browser instance closed");
		driver=null;
	}
	public void waitForVisibility(WebElement ele,int time,String objectName) {
		Base_log.info(objectName+" is waited for visibility");
		extent.logTestInfo(objectName+" is waited for visibility");
		WebDriverWait wait;
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void mouseOverOnElement(WebElement ele,String objectName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		Base_log.info("curser moved to the web element "+objectName);
		extent.logTestInfo("curser moved to the web element "+objectName);
	}
	public void contextClickOnElement(WebElement ele,String objectname) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		Base_log.info("right click performed on element "+objectname);
	}
	public void switchToNewWindowFrom(String frame) {
		driver.switchTo().window(frame);
		Base_log.info("switched to new window");
		
	}
	//single element scrren shot
	public void takeScreenShot(WebElement ele,String path) {
		TakesScreenshot screencapture=(TakesScreenshot)driver;
		File src=screencapture.getScreenshotAs(OutputType.FILE);
		File destination=new File(path);
		try {
			Files.copy(src, destination);
			Base_log.info("captured the element screenshot");
			//extent_report.logTestInfo("captured the element screenshot");
		}
		catch(IOException e){
			e.printStackTrace();
			Base_log.info("went wrong when capturing the screen");
			//extent_report.logTestFailed("captured the screen");
			
		}
	}
	//entire screenshot
	public void takeScreenShot(String path) {
		TakesScreenshot screencapture=(TakesScreenshot)driver;
		File src=screencapture.getScreenshotAs(OutputType.FILE);
		File destination=new File(path);
		try {
			Files.copy(src, destination);
			Base_log.info("captured the screen shot");
			extent.logTestInfo("captured the screen shot");
			
		}
		catch(IOException e){
			e.printStackTrace();
			Base_log.info("went wrong when capturing the screen");
			extent.logTestInfo("went wrong when capturing the screen");
		}
	}
	public Select select(WebElement ele,String obj) {
		Select select=new Select(ele);
		System.out.println(obj+" is selected");
		
		return select;
	}
	
	

}
