package com.salesforce.Test;


import static org.testng.Assert.assertEquals;

import java.util.List;

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

public class SalesforceCreateAccount extends BaseTestForSalesForce {
	protected Logger createAccount_log=LogManager.getLogger();
	protected ExtentReportsUtility extent=ExtentReportsUtility.getInstance();
	@Test
	public void testCase10() throws InterruptedException {
		login();
		//click + tab and all tabs page should display
		WebElement plus=driver.findElement(By.xpath("//li[@id=\"AllTab_Tab\"]/a/img"));
		clickElement(plus, "plus");
		WebElement allTabs=driver.findElement(By.xpath("//div[@class='content']/h1"));
		assertEquals(getTextFromElement(allTabs,"all tabs"), "All Tabs");
	
		//click customize tabs and customize my tabs page should display
		WebElement customizeTabs=driver.findElement(By.xpath("//input[@value='Customize My Tabs']"));
		clickElement(customizeTabs, "customizeTabs");
		
		
		WebElement customizeTabsDisplay=driver.findElement(By.xpath("//div[@class='content']/h1"));
		assertEquals(getTextFromElement(customizeTabsDisplay,"customize tabs"), "Customize My Tabs");
		
		//select account tab from available tabs
		
		WebElement availableTabs=driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		select(availableTabs,"availableTabs").selectByValue("Account");
	
		//using add button add account to the selected tabs
		WebElement add=driver.findElement(By.xpath("//img[@alt='Add']"));
		clickElement(add,"add");
		
		List<WebElement> selectedTabs=driver.findElements(By.xpath("//select[@id='duel_select_1']"));
		//verifying account is present in selected tabs 
		for(int i=0;i<selectedTabs.size();i++) {
			assertEquals(selectedTabs.get(i).getAttribute("value"), "Account");
		}
		//click on save
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save,"save");
	
		String showTitle="Login | Salesforce";
		assertEquals(getTitle(driver),showTitle);
		//logout salesforce account 
		logout();
		
		//login again and check tabs if it displays accounts button
		login();
		
		//find the account element and is displayed to verify that account is in the tab bar
		WebElement accountId=driver.findElement(By.xpath("//li[@id='Account_Tab']"));
		
		SoftAssert so=new SoftAssert();
		so.assertTrue(accountId.isDisplayed());
		
		createAccount_log.info("teat case 10 is completed");
		extent.logTestpassed("teat case 10 is completed");
	
		
	}
	@Test
	public void testCase10B() throws InterruptedException{
		//launch and login
		login();
		//account should be displayed on the tab
		WebElement account=driver.findElement(By.id("Account_Tab"));
		SoftAssert so=new SoftAssert();
		so.assertTrue(account.isDisplayed());
		clickElement(account, "account");
		//click on the new button
		WebElement newbutton=driver.findElement(By.xpath(" //input[@value=' New '] "));
		clickElement(newbutton, "new button");
		
		
		
		//New Account edit page is displayed. Enter <Account name>  select type as Technology partner from drop down, 
		WebElement accountname=driver.findElement(By.xpath("//input[@id='acc2']"));
		enterData(accountname, "hello", "account name");
		WebElement type=driver.findElement(By.xpath("//select[@id='acc6']"));
		select(type, "type").selectByValue("Technology Partner");
		//set customer priority as high 
		WebElement priority=driver.findElement(By.xpath("//*[@id=\"00NGB00000Oa91m\"]"));
		select(priority,"priority").selectByVisibleText("High");
		//account page should displayed with details
		WebElement save=driver.findElement(By.xpath(" //input[@value=' Save '] "));
		clickElement(save, "save");
		WebElement accountdetailpage=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[1]/table/tbody/tr/td[1]/h2"));
		
		so.assertEquals(getTextFromElement(accountdetailpage, "account details"), "Account Detail");
		//so.assertAll();
		createAccount_log.info("teat case 10B is completed");
		extent.logTestpassed("teat case 10 isB completed");
	}
	@Test
	public void testCase11() throws InterruptedException{
		//launch and login
		login();
		
		//click on accounts account page should be displayed
		WebElement account=driver.findElement(By.id("Account_Tab"));
		clickElement(account, "account tab");
		//Click on create new view link and provide <View name>, <View unique name> and click on save   
		WebElement NewViewLink=driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a"));
		clickElement(NewViewLink,"new view link");
		//Newely added View should be displayed in the account view list
//		WebElement recent =driver.findElement(By.xpath("//*[@id=\"sidebarDiv\"]/div[1]/div[2]"));
//		System.out.println(recent.getText());
//		if(getTextFromElement(recent," recent").contains("hello")) {
//			System.out.println("displayed the recent list");
//		}
//		else {
//			System.out.println("failed to display recent list");
//		}
		WebElement  viewname=driver.findElement(By.xpath("//input[@id='fname']"));
		enterData(viewname, "hello", "view name");
		WebElement uniqueName=driver.findElement(By.xpath("//input[@id='devname']"));
		enterData(uniqueName, "hi", "unique name");
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save, "save");
		
		List<WebElement> newviewaccount=driver.findElements(By.xpath("//*[@id=\"ext-gen12\"]/div/table/tbody/tr/td[4]"));
		waitForVisibility(NewViewLink, 30, "new view link");
		for(int i=0;i<newviewaccount.size();i++) {
			assertEquals(newviewaccount.get(i).getText(), "hello");
		}
	
		createAccount_log.info("teat case 11 is completed");
		extent.logTestpassed("teat case 11 completed");
	}
	@Test
	public void testCase12() throws InterruptedException{
		//launch and login
		login();
		WebElement account=driver.findElement(By.id("Account_Tab"));
		clickElement(account, "account tab");
		WebElement accountPage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		waitForVisibility(accountPage, 30, "account");
		
		assertEquals(getTextFromElement(accountPage, "accountpage"),"Accounts");
	
		//Select the <view name> from the view drop down list on the account page. Click on the Edit link the accounts page
		
		// <View name> edit page Is displayed
		//Change the <view name> to <new view name>. Select the filters field <Account name>, operator  <contains>, Value <a>.
		//In Select fields to display,  Click on save button.
		/*View page with <new view name> in the drop down is displayed. View will have Last 
		 * activity column added and the data of the
		 *  lit will have all account names which has <a> in the text.*/
		WebElement edit=driver.findElement(By.xpath("//span[@class='fFooter']/a[1]"));
		waitForVisibility(edit, 30, "edit");
		clickElement(edit, "edit");
		WebElement editPage=driver.findElement(By.xpath("//div[@class='content']/h2"));
		waitForVisibility(editPage, 30, "edit page");
		SoftAssert so=new SoftAssert();
		so.assertEquals(getTextFromElement(editPage, "edit page")," Edit View");
		
		WebElement view=driver.findElement(By.xpath("//input[@id='fname']"));
		enterData(view, "new view", "view ");
		WebElement filter=driver.findElement(By.xpath("//select[@id='fcol1']"));
		select(filter, "filter").selectByVisibleText("Account Name");
		
		WebElement operato=driver.findElement(By.xpath("//select[@id='fop1']"));
		select(operato, "operator").selectByVisibleText("contains");
		WebElement value=driver.findElement(By.xpath("//input[@id='fval1']"));
		enterData(value, "a", "value");
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save, "save");
		WebElement newpage=driver.findElement(By.xpath("//*[@class='title' and @name='fcf']"));
		waitForVisibility(newpage, 30, "new page");
		assertEquals(newpage.getAttribute("title"),"View:"); 
		//use a for loop
		//if get text from element contains a
		createAccount_log.info("teat case 12 is completed");
		extent.logTestpassed("teat case 12 completed");
	}
	@Test
	public void testcase13() throws InterruptedException {
		login();
		WebElement account=driver.findElement(By.id("Account_Tab"));
		clickElement(account, "account tab");
		//merge account
		WebElement mergeAccount=driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
		clickElement(mergeAccount, "merge");
		//enter find account data and click find accounts
		
		WebElement findData=driver.findElement(By.xpath("//input[@id='srch' ]"));
		enterData(findData,"hello" , "find account");
		WebElement findAccount=driver.findElement(By.xpath("//input[@value='Find Accounts' and @class='btn']"));
		clickElement(findAccount, "find accounts");
		if(driver.findElement(By.xpath("//input[@id='cid0' ]")).isSelected() &&driver.findElement(By.xpath("//input[@id='cid1' ]")).isSelected()) {
			System.out.println("accounts are selected");
		}
		WebElement next=driver.findElement(By.xpath("//input[@value=' Next ' ]"));
		clickElement(next, "next");
		//merge my account page displayed
		WebElement mergePage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div/div[1]/div[1]/h1"));
		assertEquals(getTextFromElement(mergePage, "mergePage"),"Merge My Accounts");
	
		//click merge
	
		WebElement merge=driver.findElement(By.xpath("//input[@value=' Merge 'and @class='btn']"));
		clickElement(merge, "merge");
		acceptAlert(driver);
		WebElement accountPage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		assertEquals(getTextFromElement(accountPage, "accountpage"),"Accounts");
		
		createAccount_log.info("teat case 13 is completed");
		extent.logTestpassed("teat case 13 completed");
		
		
	}
	@Test
	public void testcase14() throws InterruptedException {
		login();
		//click on accounts
		WebElement account=driver.findElement(By.id("Account_Tab"));
		clickElement(account, "account tab");
		WebElement accountPage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		assertEquals(getTextFromElement(accountPage, "accountpage"),"Accounts");
		
		//create account report
		WebElement accountReport=driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		clickElement(accountReport, "account report");
		WebElement date=driver.findElement(By.xpath("//*[@id=\"ext-gen152\"]"));
		clickElement(date, "date");
	
		WebElement dateToday=driver.findElement(By.xpath("//button[contains(text(),'Today')]"));
		clickElement(dateToday, "todays date");
		WebElement save=driver.findElement(By.xpath("//*[@id=\"ext-gen49\"]"));
		clickElement(save, "save");
		//<report name>, <report unique name>
		//WebElement pop=driver.findElement(By.xpath("//*[@id=\"saveReportDlg\"]"));

		WebElement reportName=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_reportNameField\"]"));
		enterData(reportName, "report", "report");
		WebElement reportUniqueNmae=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_DeveloperName\"]"));
		enterData(reportUniqueNmae, "Uniq", "unique name");
		//wait until save and run button enabled
		Thread.sleep(2000);//wait until save and run button enabled
		WebElement saveandRun=driver.findElement(By.xpath("//button[contains(text(),'Save and Run Report')]"));
		clickElement(saveandRun,"save");
		Thread.sleep(3000);
		WebElement reportname=driver.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		waitForVisibility(reportname, 30,"report");
		
		assertEquals(getTextFromElement(reportname, "report name"),"report");
		
		createAccount_log.info("teat case 14 is completed");
		extent.logTestpassed("teat case 14 completed");
		
	}
 
}
