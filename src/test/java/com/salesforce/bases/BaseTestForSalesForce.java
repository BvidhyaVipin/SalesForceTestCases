package com.salesforce.bases;



import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import com.salesforce.utilities.Constants;

import com.salesforce.utilities.PropertiesUtility;



public class BaseTestForSalesForce extends BaseTest {
	  protected Logger BaseForSales_log=LogManager.getLogger();
	 // protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Parameters("browserName")
	@BeforeMethod 
	public void beforemethod(@Optional ("chrome") String name) {
		BaseForSales_log.info("........before method......");
		launchWebdriver("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
		geturl(url);
	}
	
	public void login() throws InterruptedException {
		String username=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String passWord=PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES,"password");
	    extent.logTestInfo("user name and password extracted");
		WebElement user_name=driver.findElement(By.id("username"));
		waitForVisibility(user_name, 30, "username");
		enterData(user_name,username,"username");
		
		WebElement pass_word=driver.findElement(By.id("password"));
		enterData(pass_word, passWord, "password");
		
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login");
		
	}
	public void clickLead() throws InterruptedException{
		WebElement lead=driver.findElement(By.xpath("//li[contains(@id,'Lead_Tab')]"));
		clickElement(lead, "lead");
		WebElement leadpage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		String explead="Leads";
		String actLead=getTextFromElement(leadpage, "leadpage");
		waitForVisibility(leadpage, 30, "lead elements");
		//BaseForSales_log.info(getTextFromElement(leadpage, "leadpage"));
		assertEquals(explead, actLead);
		extent.logTestpassed("lead page verified");
		
	}
	@AfterMethod
	public void aftermethod() {
		close(driver);
		BaseForSales_log.info("browser instance closed");
		extent.logTestInfo("browser instance closed");
		BaseForSales_log.info("......after method.........");
	}
	

	
	public void logout() throws InterruptedException {
		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
		clickElement(namedownarrow,"downarrow");
		//click logout
		WebElement logoutbutton=driver.findElement(By.linkText("Logout"));
		clickElement(logoutbutton,"logout");
		String showTitle="Login | Salesforce";
		Thread.sleep(3000);
		assertEquals(getTitle(driver), showTitle);
	
		BaseForSales_log.info("logout successful");
		extent.logTestInfo("logout successful");
	}

	public Select leadDropdown(WebElement dropdownlead) {
		Select select=new Select(dropdownlead);
		return select;
	}
	public void opportunity() {
		WebElement opprtunity=driver.findElement(By.xpath("//a[@title='Opportunities Tab']"));
		clickElement(opprtunity, "opprtunity");
		
		WebElement oppText=driver.findElement(By.xpath("//a[contains(@name,'skiplink')]/following::div/h1"));
		String expopp="Opportunities";
		
		assertEquals(getTextFromElement(oppText, "opprtunity"), expopp);
		extent.logTestInfo("opprotunity page verified");
		BaseForSales_log.info("opprotunity page verified");
		
	}
//	}
//	public Select select(WebElement ele,String obj) {
//		Select select=new Select(ele);
//		BaseForSales_log.info(obj+" is selected");
//		
//		return select;
//	}
	public void homeTab() {
		WebElement home=driver.findElement(By.id("home_Tab"));
		clickElement(home, "home tab");
		WebElement homeverification=driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
		assertEquals(homeverification.getAttribute("title"),"Home Tab - Selected");
		
	}
	public void contactClick() {
		WebElement contacts=driver.findElement(By.xpath("//li[@id='Contact_Tab']"));
		clickElement(contacts,"contacts tab");
	
		//verify it is contact page
		WebElement contactPage=driver.findElement(By.xpath("//h1[@class='pageType']"));
		assertEquals(getTextFromElement(contactPage, "contact page "), "Contacts");
		extent.logTestInfo("contacts page verified");
		BaseForSales_log.info("contacts page verified");
		
	}
	public void clickUserDropDown() throws InterruptedException {
//		String[] array={"My Profile","My Settings","Developer Console","Switch to Lightning Experience", "Logout"};
//		List<String> expectedDropDown=new ArrayList<>(Arrays.asList(array));
		
		
		List<String> expectedDropDown=new ArrayList<>();

		expectedDropDown.add("My Profile");
		expectedDropDown.add("My Settings");
		expectedDropDown.add("Developer Console");
		expectedDropDown.add("Switch to Lightning Experience");
		expectedDropDown.add("Logout");
		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
		clickElement(namedownarrow, "user dropdown");
		
		List <WebElement> dropdownUse=namedownarrow.findElements(By.xpath("//*[@id=\"userNav-menuItems\"]"));
		Thread.sleep(3000);
		ArrayList<String> actualdropdownuser=new ArrayList<>();
		for(WebElement e:dropdownUse) {
			actualdropdownuser.add(e.getText());
		}
		
		//BaseForSales_log.info(actualdropdownuser);
		for(int i=0;i<actualdropdownuser.size();i++) {
			
			if(actualdropdownuser.get(i).contains(expectedDropDown.get(i))) {
				BaseForSales_log.info("verified ");
			}
			else {
				BaseForSales_log.info("not verified");
 		}
		}
		}
	
	}
	

