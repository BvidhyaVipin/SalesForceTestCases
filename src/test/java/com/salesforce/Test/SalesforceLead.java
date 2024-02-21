package com.salesforce.Test;




import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.salesforce.bases.BaseTestForSalesForce;
import com.salesforce.utilities.ExtentReportsUtility;


@Listeners(com.salesforce.utilities.Listener.class)

public class SalesforceLead extends BaseTestForSalesForce{
	protected Logger Lead_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Test
	private void testCase20() throws InterruptedException {
		login();
		clickLead();
		Lead_log.info("test case 20 completed");
		extent.logTestpassed("test case 20 completed");
			
	}
	@Test
  	private void testCase21() throws InterruptedException{
		login();
		clickLead();
		
		//drop down element having select tag
		//actual texts in dropdown
		
		List<String> expectedLeadDropDown=new ArrayList<>();
		expectedLeadDropDown.add("All Open Leads");
		expectedLeadDropDown.add("My Unread Leads");
		expectedLeadDropDown.add("Recently Viewed Leads");
		expectedLeadDropDown.add("Today's Leads");
		expectedLeadDropDown.add("View - Custom 1");
		expectedLeadDropDown.add("View - Custom 2");
//		for(String exp:expectedLeadDropDown) {
//			System.out.println(exp);
//		}
		
		WebElement dropdownlead=driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		waitForVisibility(dropdownlead, 30, "dropdown lead");
		clickElement(dropdownlead, "dropdownlead");
		
		
		Select select=new Select(dropdownlead);
		//to get all options of a drop down
		List <WebElement> dropdownLeads=select.getOptions();
		
//		for(WebElement act:dropdownLeads) {
//			System.out.println(act.getText());
//		}
		//compare with expectedlead dropdown
		for(int i=0;i<dropdownLeads.size();i++) {
			assertEquals(dropdownLeads.get(i).getText(), expectedLeadDropDown.get(i));
			}
	
		Lead_log.info("test case 21 completed");
		extent.logTestpassed("test case 21 completed");
}
	@Test
	private void testCase22() throws InterruptedException {
		
		//before doing this check the drop down display
		login();
		clickLead();
		//dropdown clicked
		WebElement dropdownlead=driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		clickElement(dropdownlead, "dropdownlead");
		waitForVisibility(dropdownlead, 30, "lead dropdown");
		//select my unread
		Select select=new Select(dropdownlead);
		
		select.selectByValue("00BGB00000GesZh");
		
		Lead_log.info("my unread lead selected");
		extent.logTestInfo("my unread lead selected");
		logout();
		login();
		clickLead();
		WebElement drop=driver.findElement(By.xpath("//*[@id='fcf' and @name='fcf']"));
		String actText=drop.getAttribute("value");
		String expText="00BGB00000GesZh";
		assertEquals(expText, actText);
		Lead_log.info("test case 22 completed");
		extent.logTestpassed("test case 22 completed");
}
	@Test
	private void testCase23() throws InterruptedException {
		login();
		clickLead();
		WebElement dropdownlead=driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		clickElement(dropdownlead, "dropdownlead");
		waitForVisibility(dropdownlead, 30, "lead dropdown");
		//select todays lead
		Select select=new Select(dropdownlead);
		select.selectByVisibleText("Today's Leads");
		WebElement drop=driver.findElement(By.xpath("//*[@id='fcf' and @name='fcf']"));
		String actText=drop.getAttribute("value");
		String expText="00BGB00000GesZv";
		assertEquals(expText, actText);
		extent.logTestInfo("showing todsays lead");
		Lead_log.info("test case 23 completed");
		extent.logTestpassed("test case 23 completed");
       
	}
	@Test
	private void testCase24() throws InterruptedException {
		login();
		clickLead();
		WebElement newlead=driver.findElement(By.xpath("//input[@type='button' and @title='New']"));
		clickElement(newlead, "new");
		WebElement leadnew=driver.findElement(By.xpath(" //div[@class='content']/h2"));
		assertEquals(getTextFromElement(leadnew, "new lead"),"New Lead");
		WebElement lastname=driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
		enterData(lastname,"ABCD", "lastname");
		//company name entered
		WebElement companyName=driver.findElement(By.xpath("//input[@id='lea3']"));
		enterData(companyName,"ABCD", "company name");
		//save button clicked
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ' and @name='save']"));
		clickElement(save, "save");
		WebElement newABCD=driver.findElement(By.xpath("//div[@id='contactHeaderRow']/div[2]/h2"));
		assertEquals(getTextFromElement(newABCD, "new lead"),"ABCD");
	}
}


	


