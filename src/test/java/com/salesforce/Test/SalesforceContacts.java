package com.salesforce.Test;


import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.bases.BaseTestForSalesForce;
import com.salesforce.utilities.ExtentReportsUtility;



@Listeners(com.salesforce.utilities.Listener.class)
public class SalesforceContacts extends BaseTestForSalesForce {
	protected Logger contacts_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Test
	public void testCase25() throws InterruptedException {
		//launch and login
		login();
		//click on contacts tab
		contactClick();

		//click on new button
		WebElement newButton=driver.findElement(By.xpath("//input[@value=' New ']"));
		clickElement(newButton,"new button");
		//verify new contact page displayed
		WebElement newContactPage=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		waitForVisibility(newContactPage, 30, "new contact");
		assertEquals(getTextFromElement(newContactPage,"new contact page "),"New Contact");
		
		//enter last name
		WebElement lastName=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterData(lastName,"AbC", "lastName");
		//WebElement lastNameVerification=driver.findElement(By.xpath("//*[@id=\"name_lastcon2\"]"));
		
		assertEquals(lastName.getAttribute("value"), "AbC");
		//enter account name and verify
		
		WebElement AccountName=driver.findElement(By.xpath("//*[@id=\"con4\"]"));
		enterData(AccountName,"tekarch", "account name");
		WebElement accountNameVerification=driver.findElement(By.xpath("//input[@id='con4' and @name='con4']"));
		assertEquals(accountNameVerification.getAttribute("value"),"tekarch");
	
		//click save
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ' and @class='btn']"));
		clickElement(save,"save");
		//verification
		WebElement contactNewVerification=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[1]/table/tbody/tr/td[1]"));
		assertEquals(contactNewVerification.getText(),"Contact Detail");
	
		contacts_log.info("test case 25 completed");
		extent.logTestpassed("test case 25 completed");
		
		
	}
	@Test
	public void testCase26() throws InterruptedException {
		login();
		contactClick();
		//create new view
		WebElement newview=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickElement(newview, "new view");
		WebElement newviewpage=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		assertEquals(getTextFromElement(newviewpage, "new view page"),"Create New View");
		
		
		WebElement viewName=driver.findElement(By.xpath("//*[@id=\"fname\"]"));
		enterData(viewName, "hello", "view name");
		
		WebElement uniqueName=driver.findElement(By.xpath("//*[@id=\"devname\"]"));
		enterData(uniqueName, "ht", "unique name");
		
		WebElement save=driver.findElement(By.xpath("//input[@name='save' and @value=' Save ']"));
		clickElement(save, "save");
		
		WebElement disp=driver.findElement(By.xpath("//select[@class='title']"));
		waitForVisibility(disp, 60, "display");
		//Select select=new Select(disp);
		//assertEquals(getTextFromElement(disp,"display ").contains("h"));
		//assertEquals(getTextFromElement(disp,"display ") ,"h");
		
		
		assertEquals(getTextFromElement(disp,"display "),"ht");

	
		contacts_log.info("test case 26 completed");
		extent.logTestpassed("test case 26 completed");
		
	}
	@Test
	public void testCase27() throws InterruptedException{
		login();
		contactClick();
		//click on dropdown and select recenetly clicked
		WebElement recent=driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		select(recent, "view dropdown ").selectByVisibleText("Recently Created");
		Thread.sleep(3000);
		if(getTextFromElement(recent, "view dropdown").contains("Recently Created")) {
			System.out.println("recently created contacts displayed");
		}
		else {
			System.out.println("failed to display revent contact page");
		}
		
		System.out.println("test case 27 completed");
		
	}
	@Test
	public void testCase28() throws InterruptedException {
		login();
		contactClick();
		WebElement dropdown=driver.findElement(By.xpath("//select[@id='fcf']"));
		select(dropdown, "dropdown").selectByVisibleText("My Contacts");
		Thread.sleep(3000);
		if(getTextFromElement(dropdown,"dropdown").contains("My Contacts")) {
			System.out.println("my contacts displayed");
		}
		else {
			System.out.println("failed to display my contacts");
		}
		
		System.out.println("test case 28 completed");
		
	}
	@Test
	public void testCase29() throws InterruptedException {
		login();
		contactClick();
		WebElement oneContact=driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/th"));
		clickElement(oneContact, "one contact");
		WebElement contactInformation=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		
		assertEquals(getTextFromElement(contactInformation, "contact information"),"Contacts");
		
		System.out.println("test case 29 completed");
	}
	@Test
	public void testCase30() throws InterruptedException {
		login();
		contactClick();
		WebElement newview=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a"));
		clickElement(newview, "new view");
		WebElement newviewpage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2"));
		assertEquals(getTextFromElement(newviewpage, "new view page"),"Edit View");
		
		WebElement uniquename=driver.findElement(By.xpath("//input[@id='devname']"));
		enterData(uniquename, "EFGH", "unique name");
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save, "save");
		Thread.sleep(3000);
		WebElement err=driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
		waitForVisibility(err, 30, "error");
		assertEquals(err," You must enter a value");
	
		System.out.println("test case 30 completed");
		
	}
	@Test
	public void testCase31() throws InterruptedException {
		login();
		contactClick();
		WebElement newview=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a"));
		clickElement(newview, "new view");
		WebElement newviewpage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2"));
		waitForVisibility(newviewpage, 30, "Edit View");
		assertEquals(getTextFromElement(newviewpage, "new view page"),"Edit View");
		
		WebElement uniquename=driver.findElement(By.xpath("//input[@id='devname']"));
		enterData(uniquename, " EFGH", "unique name");
		WebElement cancel=driver.findElement(By.xpath("//input[@value='Cancel']"));
		clickElement(cancel, "cancel");
		WebElement page=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2"));
		waitForVisibility(page, 30, "page");
		assertEquals(getTextFromElement(page, "page"),"Home");

		contacts_log.info("test case 31 completed");
		extent.logTestpassed("test case 31 completed");
		
	}
	@Test
	public void testCase32() throws InterruptedException {
		login();
		contactClick();
		WebElement newButton=driver.findElement(By.xpath("//input[@value=' New ']"));
		clickElement(newButton,"new button");
		//verify new contact page displayed
		WebElement newContactPage=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
		waitForVisibility(newContactPage, 30, "new contact page");
		assertEquals(getTextFromElement(newContactPage, "new contact page "),"New Contact");
		
		WebElement lastname=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterData(lastname,"Indian", "last name");
		WebElement accountname=driver.findElement(By.xpath("//input[@id='con4']"));
		enterData(accountname, "Global Media", "account name");
		WebElement saveANdNew=driver.findElement(By.xpath("//input[@value='Save & New']"));
		clickElement(saveANdNew, "save and new ");
		//error meesage displayed :Review all error messages below to correct your data.
		WebElement error=driver.findElement(By.id("errorDiv_ep"));
		waitForVisibility(error, 30, "error");
		SoftAssert so=new SoftAssert();
		so.assertEquals(getTextFromElement(error, "error"),"Error: Invalid Data. Review all error messages below to correct your data.");
	 
		contacts_log.info("test case 32 completed");
		extent.logTestpassed("test case 32 completed");
		
	}


}
