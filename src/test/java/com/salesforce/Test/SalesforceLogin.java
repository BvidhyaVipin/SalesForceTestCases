package com.salesforce.Test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.bases.BaseTestForSalesForce;
import com.salesforce.utilities.Constants;
import com.salesforce.utilities.ExtentReportsUtility;
import com.salesforce.utilities.PropertiesUtility;

@Listeners(com.salesforce.utilities.Listener.class)

public class SalesforceLogin extends BaseTestForSalesForce {
	protected Logger Login_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Test
	public void testcase1() throws InterruptedException {
		
	
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver), showTitle);
	
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		//String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		//enter username
		WebElement user_name=driver.findElement(By.id("username"));
		enterData(user_name,username,"username");
		
		//clear password
		WebElement pass_word=driver.findElement(By.id("password"));
		enterData(pass_word, "", "password");
		
		//click login
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login");
		Thread.sleep(3000);
		//check error message displayed
		WebElement error=driver.findElement(By.id("error"));
		String expError="Please enter your password.";
		String actError=getTextFromElement(error, "error");
		assertEquals(actError,expError );
		extent.logTestInfo("error message verified");
		Login_log.info("test case 1 passed");
		extent.logTestInfo("test case 1 passed");
	}
	
	@Test
	public void testcase2() throws InterruptedException {
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver), showTitle);
	
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		//enter username
		WebElement user_name=driver.findElement(By.id("username"));
		Thread.sleep(5000);
		enterData(user_name,username,"username");
		
		//clear password
		WebElement pass_word=driver.findElement(By.id("password"));
		enterData(pass_word, passWord, "password");
		
		//click login
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login");


		String homepage="Home Page ~ Salesforce - Developer Edition";
		assertEquals(getTitle(driver), homepage);
		
		Login_log.info("test case 2 completed");
		extent.logTestInfo("test case 2 passed");

	}

	@Test
	public void testcase3() throws InterruptedException {
		
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver), showTitle);
		
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
		//enter username
		WebElement user_name=driver.findElement(By.id("username"));
		Thread.sleep(5000);
		enterData(user_name,username,"username");
	
		WebElement pass_word=driver.findElement(By.id("password"));
		enterData(pass_word, passWord, "password");
		//remember me
		WebElement rememberMe=driver.findElement(By.xpath("//input[@id='rememberUn']"));
		clickElement(rememberMe,"remember");
		//click login
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login");
		
		//verifying home page
		String homepage="Home Page ~ Salesforce - Developer Edition";
		assertEquals(getTitle(driver), homepage);
		
		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
		clickElement(namedownarrow,"downarrow");
		//click logout
		WebElement logoutbutton=driver.findElement(By.linkText("Logout"));
		clickElement(logoutbutton,"logout");
		Thread.sleep(5000);
	
	
		WebElement checkusername=driver.findElement(By.id("idcard-identity"));
		waitForVisibility(checkusername, 30, "user name field");
		//Login_log.info(checkusername.getAttribute("span"));
		
		assertEquals(getTextFromElement(checkusername,"username"), username);
        	
     
		Login_log.info("test case 3 passed");
		extent.logTestInfo("test case 3 passed");
	}  
	@Test
	public void testcase4A() throws InterruptedException {
	
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver), showTitle);
		
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		
		//enter username
		WebElement user_name=driver.findElement(By.id("username"));
		waitForVisibility(user_name, 30, "usename");
		enterData(user_name,username,"username");
		WebElement forgotpassword=driver.findElement(By.id("forgot_password_link"));
		clickElement(forgotpassword,"forgot");

//		String forgot=driver.getTitle();
		//enter user name in the field
		WebElement typeid=driver.findElement(By.id("un"));
		waitForVisibility(typeid, 30, "forgot password username");
		enterData(typeid,"vidhya@tekarch.com","typeid");
		//click on continue button
	    WebElement cont=driver.findElement(By.id("continue"));
		clickElement(cont,"continue");
		
		//checking title of the page 
		String expPage="Check Your Email | Salesforce";
		assertEquals(getTitle(driver),expPage);
		
		Login_log.info("test case 4A is completed");
		extent.logTestInfo("test case 4A passed");
	}
	@Test
	public void testcase4B() throws InterruptedException {
		
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver), showTitle);
		
		WebElement username=driver.findElement(By.id("username"));
		enterData(username,"123","username");
		WebElement password=driver.findElement(By.id("password"));
		enterData(password,"22321","password");
		
	
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login");
		WebElement errorWrongUserNamePassword=driver.findElement(By.xpath("//*[@id='error' and @class='loginError']"));
		String ActError=getTextFromElement(errorWrongUserNamePassword,"error");
		String error="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		assertEquals(ActError, error);
		extent.logTestInfo("errormessage displayed");
		
		
	    Login_log.info("test case 4b is completed");
	    extent.logTestInfo("test case 4b is completed");

	}

}


