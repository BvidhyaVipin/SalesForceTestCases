package com.salesforce.Test;


import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.bases.BaseTestForSalesForce;




@Listeners(com.salesforce.utilities.Listener.class)
public class SalesForceRandomScenerios extends BaseTestForSalesForce {
	@Test
	public void testcase33() throws InterruptedException {
		//login
		login();
		//click on home tab
		homeTab();

		WebElement homeUser=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		waitForVisibility(homeUser, 30, "home user");
		assertEquals(getTextFromElement(homeUser,"user"),"vivi Abcd");
		
		clickElement(homeUser, "user");
		
		
		System.out.println("test case 33 completed");
		
	}
	@Test
	public void testcase34() throws InterruptedException {
		login();
		homeTab();
		//click user
		WebElement homeUser=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		clickElement(homeUser, "user");
		//click on edit
		WebElement arrow=driver.findElement(By.xpath("//*[@id=\"moderatorMutton\"]"));
		arrow.click();
		WebElement edit=driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));
		clickElement(edit, "edit");
	
		WebElement newframe=driver.findElement(By.id("aboutMeContentId"));
		waitForVisibility(newframe, 30, "new frame");
		driver.switchTo().frame(newframe);
		System.out.println("switched to new frame");

		//click on about button and change last name and save
		WebElement about=driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		waitForVisibility(about, 30, "about");
		getTextFromElement(about, "about");
//		if(!about.isSelected()) {
//			clickElement(about, "about");
//		}
		//  
		WebElement lastname=driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		enterData(lastname, "Abcd", "last name");
		WebElement saveall=driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(saveall, "save all");
		// problem with this element
//		WebElement check=driver.findElement(By.xpath("//div[@id='chatterTab']/div/div[1]/div[2]/span[3]"));
//		waitForVisibility(check, 30, "check");
//		System.out.println(check.getAttribute("title"));
//		if(check.getText().contains("Abcd")) {
//			System.out.println("profile last name changed");
//		}
//		else {
//			System.out.println("failed to add change last names");
//		}
//		WebElement user=driver.findElement(By.xpath("//*[@id=\"tailBreadcrumbNode\"]"));
		
//		WebElement usermenu=driver.findElement(By.id("userNavLabel"));
//		waitForVisibility(usermenu, 60, "use menu");
//		SoftAssert so=new SoftAssert();
//		so.assertEquals(getTextFromElement(usermenu, "user menu"),"vivi Abcd");
		
		System.out.println("test case 34 completed");
		
	}
	@Test
	public void testcase35() throws InterruptedException {
		login();
		//click + tab and all tabs page should display
		WebElement plus=driver.findElement(By.xpath("//li[@id=\"AllTab_Tab\"]/a/img"));
				clickElement(plus, "plus");
				WebElement allTabs=driver.findElement(By.xpath("//div[@class='content']/h1"));
				
				assertEquals(getTextFromElement(allTabs,"all tabs"),"All Tabs");
			
				//click customize tabs and customize my tabs page should display
				WebElement customizeTabs=driver.findElement(By.xpath("//input[@value='Customize My Tabs']"));
				clickElement(customizeTabs, "customizeTabs");
				
				WebElement customizeTabsDisplay=driver.findElement(By.xpath("//div[@class='content']/h1"));
				assertEquals(getTextFromElement(customizeTabsDisplay,"customize tabs"),"Customize My Tabs");
					
				
				//select any tab from Selected tabs tabs
				
				WebElement selectedTabs=driver.findElement(By.xpath("//*[@id=\"duel_select_1\"]"));
				select(selectedTabs,"selected tabs").selectByValue("Chatter");
			
				//using remove button remove chatter form selected tabs
				WebElement remove=driver.findElement(By.xpath("//*[@id=\"duel_select_0_left\"]/img"));
				clickElement(remove,"remove");
				
				List<WebElement> availableTabs=driver.findElements(By.xpath("//*[@id=\"duel_select_0\"]"));
				//verifying chatter is present in available tabs 
				for(int i=0;i<availableTabs.size();i++) {
					if(availableTabs.get(i).getAttribute("value").equals("Chatter")) {
						System.out.println("chatter is remover from selected tabs ");
					}
					else {
						System.out.println("failed to remove chatter");
		 		}
				}
				//click on save
				WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
				clickElement(save,"save");
			
				//logout salesforce account 
				logout();

				//login again and check tabs if it displays accounts button
				login();
				
				//find the account element and is displayed to verify that account is in the tab bar
				List<WebElement> findChatter=driver.findElements(By.id("tabBar"));
				
				Thread.sleep(2000);
				for(int i=0;i<findChatter.size();i++) {
					if(findChatter.get(i).getText().equals("Chatter"))
				 {
					System.out.println("failed to remove chatter from the tab bar");
				}
				else {
					System.out.println("chatter is not  visible on the tab bar");
				}
				}
		
			System.out.println("test case 35 completed");	
	}
	@Test
	public void testcase36() throws InterruptedException {
		login();
		WebElement home=driver.findElement(By.id("home_Tab"));
		clickElement(home, "home");
		WebElement homeuser=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1"));
		waitForVisibility(homeuser, 30, "user");
		if(homeuser.getAttribute("title").contains("vivi")) {
			System.out.println("home page displayed with correct user name");
		}else {
			System.out.println("failed to load home page with correct user name");
		}
		WebElement date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		System.out.println(getTextFromElement(date, "date"));
		clickElement(date, "date");
		WebElement dateclick=driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[1]/div[1]/h1"));
		if(getTextFromElement(dateclick, "date link").contains("Calendar for vivi Abcd - Day View")) {
			System.out.println("date page for user displayed");
		}else {
			System.out.println("failed to display date page");
		}
		WebElement timeclick=driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:4:j_id64\"]/a"));
		clickElement(timeclick, "time 8pm");
		WebElement timepage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2"));
		waitForVisibility(timepage, 30, "timepage");
		if(getTextFromElement(timepage, "time page").equals(" New Event")) {
			System.out.println("displaye the new event page");
		}else {
			System.out.println("failed to display the new event page");
		}
		String current=driver.getWindowHandle();
		System.out.println(current);
		WebElement searchCombo=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(searchCombo,"search combo");
		
		waitForVisibility(searchCombo, 30, "search combo");
		Set<String> handles=driver.getWindowHandles();
		for(String handle:handles) {
			
			if(!current.equals(handle)) {
				driver.switchTo().window(handle);
				driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a")).click();
				driver.switchTo().window(current);
			}
			
		}
		WebElement subject=driver.findElement(By.xpath("//*[@id=\"evt5\"]"));
		waitForVisibility(subject, 30, "subject");
		if(getTextFromElement(subject, "subject").equals("Other")) {
			System.out.println("other is displayed");
		}
		else {
			System.out.println("failed to display other");
		}
		WebElement endTime=driver.findElement(By.id("EndDateTime_time"));
		clickElement(endTime, "end time");
		String []et={"12:00 am","12:30 am","1:00 am","1:30 am","2:00 am","2:30 am","3:00 am","3:30 am","4:00 am","4:30 am","5:00 am","5:30 am","6:00 am","6:30 am","7:00 am",
				"7:30 am","8:00 am","8:30 am","9:00 am","9:30 am","10:00 am","10:30 am","11:00 am","11:30 am","12:00 pm","12:30 pm","1:00 pm","1:30 pm","2:00 pm","2:30 pm","3:00 pm","3:30 pm","4:00 pm","4:30 pm","5:00 pm","5:30 pm","6:00 pm","6:30 pm","7:00 pm",
				"7:30 pm","8:00 pm","8:30 pm","9:00 pm","9:30 pm","10:00 pm","10:30 pm","11:00 pm","11:30 pm"};
		List<String> expectedtimerange=new ArrayList<String>(Arrays.asList(et));
		List<WebElement> timerange=driver.findElements(By.id("simpleTimePicker"));
		Thread.sleep(5000);
		for(int i=0;i<timerange.size();i++) {
			if(timerange.get(i).getText().equals(expectedtimerange.get(i))){
				System.out.println("verified");
			}
			else {
				System.out.println("not verified");
			}
		}
		for(int i=0;i<timerange.size();i++) {
			if(timerange.get(i).getText().equals("9:00 am")){
				timerange.get(i).click();
			}
		}
		WebElement Savebutton=driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		clickElement(Savebutton, "save");
		WebElement calenderpage=driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[1]/div[1]/h1"));
		if(calenderpage.isDisplayed()) {
			System.out.println("new event page displayed");
		}else {
			System.out.println("failed to load new event page");
		}
		WebElement occupied=driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id69:4:j_id71:0:j_id72:calendarEvent:i\"]/div/div"));
		if(getTextFromElement(occupied, "event").contains("Other")) {
			System.out.println("other event displayed");
		}
		else {
			System.out.println("failed to display other event");
		}
		
		System.out.println("test case 36 completed");
		
	}
	@Test
	public void testcase37() throws InterruptedException {
		login();
		WebElement home=driver.findElement(By.id("home_Tab"));
		clickElement(home, "home");
		WebElement homeuser=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1"));
		SoftAssert so=new SoftAssert();
		so.assertEquals(homeuser.getAttribute("title"),"vivi Abcd");
		
		WebElement date=driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
		System.out.println(getTextFromElement(date, "date"));
		clickElement(date, "date");
		WebElement dateclick=driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[1]/div[1]/h1"));
		assertEquals(getTextFromElement(dateclick, "date link"),"Calendar for vivi Abcd - Day View");
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement fourpm= driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:20:j_id64\"]/a"));
		js.executeScript("arguments[0].scrollIntoView(true)",fourpm);
		js.executeScript("arguments[0].click();", fourpm);
		
		String current=driver.getWindowHandle();
		//System.out.println(current);
		WebElement searchCombo=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
		clickElement(searchCombo,"search combo");
		
		Thread.sleep(5000);
		Set<String> handles=driver.getWindowHandles();
		for(String handle:handles) {
			if(!current.equals(handle)) {
				driver.switchTo().window(handle);
				driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]/a")).click();
				driver.switchTo().window(current);
			}
			
		}
		WebElement subject=driver.findElement(By.xpath("//*[@id=\"evt5\"]"));
		waitForVisibility(subject, 30, "subject");
		so.assertEquals(getTextFromElement(subject, "subject"),"Other");
		
		Thread.sleep(3000);
		WebElement endTime=driver.findElement(By.id("EndDateTime_time"));
		clickElement(endTime, "end time");
		String []et={"12:00 am","12:30 am","1:00 am","1:30 am","2:00 am","2:30 am","3:00 am","3:30 am","4:00 am","4:30 am","5:00 am","5:30 am","6:00 am","6:30 am","7:00 am",
				"7:30 am","8:00 am","8:30 am","9:00 am","9:30 am","10:00 am","10:30 am","11:00 am","11:30 am","12:00 pm","12:30 pm","1:00 pm","1:30 pm","2:00 pm","2:30 pm","3:00 pm","3:30 pm","4:00 pm","4:30 pm","5:00 pm","5:30 pm","6:00 pm","6:30 pm","7:00 pm",
				"7:30 pm","8:00 pm","8:30 pm","9:00 pm","9:30 pm","10:00 pm","10:30 pm","11:00 pm","11:30 pm"};
		List<String> expectedtimerange=new ArrayList<String>(Arrays.asList(et));
		List<WebElement> timerange=driver.findElements(By.id("simpleTimePicker"));
		Thread.sleep(3000);
		for(int i=0;i<timerange.size();i++) {
			if(timerange.get(i).getText().equals(expectedtimerange.get(i))){
				System.out.println("verified");
			}
			else {
				System.out.println("not verified");
			}
		}
		for(int i=0;i<timerange.size();i++) {
			if(timerange.get(i).getText().equals("7:00 pm")){
				timerange.get(i).click();
			}
		}
		
		WebElement recurenceCheckbox=driver.findElement(By.xpath("//*[@id=\"IsRecurrence\"]"));
		if(!recurenceCheckbox.isSelected()) {
			clickElement(recurenceCheckbox, "reccurance");
		}
		WebElement radioweekly=driver.findElement(By.xpath("//*[@id=\"rectypeftw\"]"));
		if(!radioweekly.isSelected()) {
			clickElement(radioweekly, "weekly radio");
		}
		
		WebElement recurs=driver.findElement(By.xpath("//input[@id='wi' and @name='wi']"));
		if(recurs.getAttribute("value").equals("1")) {
			System.out.println("recurs every 1 week");
		}
		List<WebElement> days=driver.findElements(By.xpath("//*[@id=\"w\"]/div[2]"));
		for(WebElement day:days) {
			if(!day.isSelected()) {
				System.out.println("no day selected");
			}
			else {
				System.out.println("current day is selected");
			}
		}
		
		WebElement endDate=driver.findElement(By.id("RecurrenceEndDateOnly"));
		clickElement(endDate, "end date");
		WebElement today=driver.findElement(By.xpath("//*[@id=\"datePicker\"]/div[2]/div/a"));
		clickElement(today, "today");
		
		
		WebElement Save=driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		clickElement(Save, "save");
		WebElement calender=driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[1]/div[1]/h1"));
		if(calender.isDisplayed()) {
			System.out.println("new event page displayed");
		}else {
			System.out.println("failed to load new event page");
		}
		WebElement occupied=driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id69:20:j_id71:0:j_id72:calendarEvent:i\"]/div/div"));
		if(getTextFromElement(occupied, "event").contains("Other")) {
			System.out.println("other event displayed");
		}
		else {
			System.out.println("failed to display other event");
		}
		WebElement monthview=driver.findElement(By.xpath("//*[@id=\"bCalDiv\"]/div/div[2]/span[2]/a[3]/img"));
		clickElement(monthview, "month view");
		WebElement monhtviewpage=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1"));
		assertEquals(getTextFromElement(monhtviewpage, "month vie page"),"Calendar for vivi Abcd - Month View");
		
		//current date and 1 week from today blocked verify
		
		System.out.println("test case 37 completed");
		
	}


}
