package com.salesforce.Test;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.salesforce.bases.BaseTestForSalesForce;
import com.salesforce.utilities.ExtentReportsUtility;


@Listeners(com.salesforce.utilities.Listener.class)

public class SalesforceCreateOpportunity extends BaseTestForSalesForce {
	protected Logger Opportunity_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Test
	public void testcase15() throws InterruptedException {
		login();
		opportunity();
		List<String> expectedOpportunityDropDown=new ArrayList<>();

		expectedOpportunityDropDown.add("All Opportunities");
		expectedOpportunityDropDown.add("Closing Next Month");
		expectedOpportunityDropDown.add("Closing This Month");
		expectedOpportunityDropDown.add("My Opportunities");
		expectedOpportunityDropDown.add("New Last Week");
		expectedOpportunityDropDown.add("New This Week");
		expectedOpportunityDropDown.add("Opportunity Pipeline");
		expectedOpportunityDropDown.add("Private");
		expectedOpportunityDropDown.add("Recently Viewed Opportunities");
		expectedOpportunityDropDown.add("Won");
		
		WebElement dropdownOpp=driver.findElement(By.xpath("//select[@id='fcf']"));
		//selectThis(dropdownOpp, "Opprotunity dropdown");
		//Select select=new Select(dropdownOpp);
		//to get all options of a drop down
		
		List <WebElement> dropdownOppor=select(dropdownOpp, "dropdown").getOptions();
		for(int i=0;i<dropdownOppor.size();i++) {
			assertEquals(dropdownOppor.get(i).getText(), expectedOpportunityDropDown.get(i));
		}
		Opportunity_log.info("test case 15 completed");
		extent.logTestpassed("test case 15 completed");
		
	}
	@Test
	public void testcase16() throws InterruptedException {
	  login();
	  opportunity();
	  // click on new button
	  WebElement newbutton=driver.findElement(By.xpath("//input[@value=' New ' and @type='button']"));
	  clickElement(newbutton, "new");
	  //verify the page
	  WebElement newpage=driver.findElement(By.xpath("//div[@class='content']/h1"));
	  
	  assertEquals(getTextFromElement(newpage,"new page "), "Opportunity Edit");

	  WebElement oppName=driver.findElement(By.xpath("//input[@id='opp3']"));
	  enterData(oppName, "abcd", "opprtunity name");
	  
	  WebElement AccountName=driver.findElement(By.xpath("//input[@id='opp4']"));
	  enterData(AccountName, "tekarch","account name");
	  //enter data from calender
	  WebElement closeDate=driver.findElement(By.id("opp9"));
	  clickElement(closeDate, "close date");
	  driver.findElement(By.id("calMonthPicker")).click();
	  List <WebElement> months=driver.findElements(By.id("calMonthPicker"));
	  for(int i=0;i<months.size();i++) {
		 // System.out.println(months.get(i).getText());
		 
		  if((months.get(i).getText()).contains("July")) {
			  select(months.get(i), "month").selectByVisibleText("July");  
		  }
	  }
	  driver.findElement(By.id("calYearPicker")).click();
	  List <WebElement> years=driver.findElements(By.id("calYearPicker"));
	  for(int i=0;i<years.size();i++) {
		  if(years.get(i).getText().contains("2025")) {
			  select(years.get(i), "year").selectByVisibleText("2025");
		  }
	  }
	  List <WebElement> dates=driver.findElements(By.xpath("//*[@id=\"datePickerCalendar\"]"));
	
	  //List <WebElement> dates=driver.findElements(By.xpath("//*[@id=\"calRow3\"]/td"));
	  for(int i=0;i<dates.size();i++) {
		  //System.out.println(dates.get(i).getText());
//		  Thread.sleep(5000);
		  if(dates.get(i).getText().contains("17")) {
			  //select(dates.get(i),"date").selectByVisibleText("17");
			  clickElement(dates.get(i), "date");
		  }
	  }  
	   
	  WebElement stage=driver.findElement(By.xpath("//select[@id='opp11']"));
	  select(stage,"stage").selectByValue("Qualification");
	  
	  WebElement Probability=driver.findElement(By.xpath("//input[@id='opp12']"));
	  enterData(Probability, "4", "probability");
	  
	  WebElement LeadSource=driver.findElement(By.xpath("//*[@id=\"opp6\"]"));
	  select(LeadSource,"lead source").selectByIndex(1);
	 
	  WebElement save=driver.findElement(By.xpath("//td[@id=\"topButtonRow\"]/input[1]"));
	  clickElement(save, "save");
	  
	  WebElement savedpage=driver.findElement(By.xpath("//h2[@class='pageDescription']"));
	  assertEquals(getTextFromElement(savedpage, "save page "), "abcd");
	
	  Opportunity_log.info("test case 16 completed");
	  extent.logTestpassed("test case 16 completed");
		
	}
	@Test
	public void testcase17() throws InterruptedException {
		//if problem with this change the dropdown seection to any thing other than pipeline
		login();
		opportunity();
		WebElement dropdownOpp=driver.findElement(By.xpath("//select[@id='fcf']"));
		//select(dropdownOpp, "dropdown").selectByVisibleText("New This Week");
		//waitForVisibility(dropdownOpp, 30, "dropdown");
		select(dropdownOpp,"dropdown opportunity").selectByVisibleText("Opportunity Pipeline");
		
		WebElement dropdownOpppage=driver.findElement(By.id("00BGB00000GesbG_listSelect"));
		assertEquals(dropdownOpppage.getAttribute("value"), "00BGB00000GesbG");
		
		 Opportunity_log.info("test case 17 completed");
		 extent.logTestpassed("test case 17 completed");
		
	}
	@Test
	public void testcase18() throws InterruptedException {
		login();
		opportunity();
		WebElement stuckOpp=driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickElement(stuckOpp, "stuck opportunity");
		WebElement stuckOpppage=driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		assertEquals(getTextFromElement(stuckOpppage, "stuck opportunity"), "Stuck Opportunities");
		
		Opportunity_log.info("test case 18 completed");
		 extent.logTestpassed("test case 18 completed");
		
	}
	@Test
	public void testcase19() throws InterruptedException {
		login();
		opportunity();
		WebElement quarterreport=driver.findElement(By.xpath("//select[@id='quarter_q']"));
		
		select(quarterreport,"Quarter report").selectByVisibleText("Current and Next 3 FQ");
		extent.logTestInfo("urrent and Next 3 FQ is selected");
		
		
		WebElement quarterinclude=driver.findElement(By.xpath("//select[@id='open']"));
		select(quarterinclude,"closed").selectByVisibleText("Closed Opportunities");
		extent.logTestInfo("closed is selected");
		WebElement run=driver.findElement(By.xpath("//input[@value='Run Report']"));
		clickElement(run, "run report");
		
		WebElement runreport=driver.findElement(By.xpath("//div[@id='status']"));
		waitForVisibility(runreport, 30, "run report");
		
		assertEquals(getTextFromElement(runreport,"run report"), "Complete");
		
		Opportunity_log.info("test case 19 completed");
		 extent.logTestpassed("test case 19 completed");
		
		
	}


}
