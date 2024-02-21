package com.salesforce.Test;


import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.salesforce.bases.BaseTestForSalesForce;




@Listeners(com.salesforce.utilities.Listener.class)

public class SalesForceDropDownMenu extends BaseTestForSalesForce {
	@Test
	public void testCase5() throws InterruptedException {
		login();
		//check the home page logged with correct user name
		WebElement userdisplay=driver.findElement(By.id("userNavLabel"));
		String expecteduser="vivi Abcd";
		String user=userdisplay.getText();
		assertEquals(user,expecteduser);
		
		//user menu drop down
		clickUserDropDown();

		System.out.println("test case 5 completed");
	}
	@Test
	public void testCase6() throws InterruptedException, AWTException {//not completed
		login();
		//click on use menu dropdown
		clickUserDropDown();
		//click on my profile option and profile should display
		WebElement myprofile=driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[1]"));
		clickElement(myprofile, "my profile");
		
		//click on edit profile button and edit
		WebElement arrow=driver.findElement(By.xpath("//*[@id=\"moderatorMutton\"]"));
		arrow.click();
		WebElement edit=driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[1]/div/div[1]/div[1]/div/ul/li[2]/a"));
		clickElement(edit, "edit");
	
		//Edit profile pop up window is displayed with contact and About tabs to edit.
		WebElement newframe=driver.findElement(By.id("aboutMeContentId"));
		driver.switchTo().frame(newframe);
		System.out.println("switched to new frame");
      
		//click on about button and change last name and save
		Thread.sleep(3000);
		WebElement about=driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));//
		getTextFromElement(about, "about");
////		if(!about.isSelected()) {
////			clickElement(about, "about");
////		}
//		//  
		WebElement lastname=driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		enterData(lastname, "Abcd", "last name");
		WebElement saveall=driver.findElement(By.xpath("//input[@value='Save All']"));
		clickElement(saveall, "save all");
		//user profile name should change
		
		driver.switchTo().defaultContent();
		WebElement check=driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[1]/div/div[2]/span[3]"));
		System.out.println(check.getAttribute("title"));
		assertEquals(check.getAttribute("title"),"vivi Abcd  ");
		
		//post hi
		WebElement postClick=driver.findElement(By.id("publisherAttachTextPost"));
		clickElement(postClick, "post");
		
		//System.out.println("switched to post frame");
		//click on post line
		
//find iframe for write inside
//		switch to iframe  for ideentify frame //*[@='cke_43_contents']/iframe
//   textbox by xpahth /html/body
		
//enter text or send keys
//switch back to parent frame
//and share button
		WebElement post=driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]"));
		post.click();
//		WebElement postFrame=driver.findElement(By.className("cke_wysiwyg_frame cke_reset"));
//		driver.switchTo().frame(postFrame);
		Robot r=new Robot();
		WebElement postany=driver.findElement(By.xpath("/html"));
		clickElement(postany,"post");
		
		
		r.keyPress(KeyEvent.VK_H);
		r.keyRelease(KeyEvent.VK_H);
		r.keyPress(KeyEvent.VK_I);
		r.keyRelease(KeyEvent.VK_I);

		WebElement share=driver.findElement(By.id("publishersharebutton"));
		clickElement(share, "share");
		
		
		//click publish
		//click on file link and add photo
		
		
		WebElement file=driver.findElement(By.id("publisherAttachContentPost"));
		clickElement(file, "file");
		WebElement ChooseFronDeskTopfile=driver.findElement(By.xpath("//*[@id=\"chatterUploadFileAction\"]"));
		clickElement(ChooseFronDeskTopfile, "desktop file upload");
		WebElement Choosefile=driver.findElement(By.id("chatterFile"));
		
		waitForVisibility(Choosefile, 30,"choose file");
//		//clickElement(Choosefile, "choose file");
		Choosefile.sendKeys("C:\\Users\\VIBIN\\Desktop\\tekarch\\assignments.docx");
		//*[@id="publishersharebutton"]
//		WebElement share=driver.findElement(By.id("publishersharebutton"));
		clickElement(share, "share");
		
		//profile photo updation
		//mouse hover to profile
		
		WebElement profile=driver.findElement(By.xpath("//*[@id=\"photoSection\"]/span[2]/img[1]"));
	     mouseOverOnElement(profile, "profile");
	     try {
		WebElement delete=driver.findElement(By.id("deletePhoto"));
		clickElement(delete, "delete profile photo");
		Thread.sleep(3000);
		//WebElement framedelete=driver.findElement(By.id("simpleDialog0Content"));
	   
	    // driver.switchTo().frame(framedelete);
	     WebElement ok=driver.findElement(By.id("simpleDialog0button0"));
	     clickElement(ok, "ok");
	    // mouseOverOnElement(profile, "profile");
	     }
	     catch(Exception e) {
	    	 System.out.println("no profile photo");
	     }
	     
	     finally {
	    	 WebElement addphoto=driver.findElement(By.xpath("//*[@id=\"uploadLink\"]"));
	 	     waitForVisibility(addphoto, 30, "add photo");
	 	     mouseOverOnElement(profile, "profile");
	 	     clickElement(addphoto, "add photo");
	 	     Thread.sleep(3000);
	 	     
	 	     WebElement frame=driver.findElement(By.id("uploadPhotoContentId"));
	 	     driver.switchTo().frame(frame);
	 	     WebElement choose=driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadInputFile\"]"));
	 	     waitForVisibility(choose, 30, "choose");
	 	     
	 	     choose.sendKeys("E:\\Untitled.png");
	 	     WebElement saveButton=driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
	 	     clickElement(saveButton, "save");
	 	     driver.switchTo().defaultContent();
	 	     
	 	     driver.switchTo().frame("uploadPhotoContentId");
	 	     Thread.sleep(3000);
	 	    
	 	     
	 	     WebElement saveButtonagain=driver.findElement(By.id("j_id0:j_id7:save"));
	 	     waitForVisibility(saveButtonagain, 30, "savebutton");
	 	     clickElement(saveButtonagain, "save");
	 	     driver.switchTo().defaultContent();
	     }
	     Thread.sleep(30);
	     mouseOverOnElement(profile, "profile");
	     WebElement photo=driver.findElement(By.xpath("//*[@id=\"photoSection\"]/span[2]/img[1]"));
		waitForVisibility(photo, 30, "photo");
//	     if(photo.getAttribute("src").contains("https://tekarch-a-dev-ed.develop.file.force.com/profilephoto/729GB000000ovQk/F")) {
//	    	 System.out.println("profile photo updated");
//	    	 
//	     }else {
//	    	 System.out.println("failed to upload profile photo");
//	     }
	     
//	     JavascriptExecutor js=(JavascriptExecutor)driver;
//	     WebElement addphoto=driver.findElement(By.xpath("//*[@id=\"uploadLink\"]"));
//	     js.executeScript("arguments[0].click();",addphoto);
//	     WebElement choose=driver.findElement(By.xpath("//*[@id=\"j_id0:uploadFileForm:uploadInputFile\"]"));
//	     js.executeScript("arguments[0].value='Desktop/Untitled.png'",choose);
	    
	     
//	     js.executeScript("arguments[1].click();",choose);
//	     js.executeScript("arguments[2].sendKeys(\"Desktop/Untitled.png\");",choose);
//	     choose.sendKeys();
		
		
		System.out.println("test case 6 completed");
		
		
	}
	@Test
	public void testCase7() throws InterruptedException {//not completed
		login();
		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
		clickElement(namedownarrow,"downarrow");
		//user menu dropdown verify
		//click my settings page should display
		
		WebElement mysetting=driver.findElement(By.xpath("//a[@title='My Settings']"));
		clickElement(mysetting, "my settings");
		String mytitle="My Settings";
		WebElement mysettingDisplay=driver.findElement(By.xpath("//a[@id='PersonalSetup_font']/span[2]"));
		assertEquals(getTextFromElement(mysettingDisplay, "my settings"),mytitle);
		
		
		//Click on personal link and select Login History link.Click on Download login  history link.
		WebElement personal=driver.findElement(By.xpath("//*[@id='PersonalInfo_font']"));
		clickElement(personal, "personal link");
		WebElement loginHistory=driver.findElement(By.xpath("//a[@id='LoginHistory_font']/span"));
		clickElement(loginHistory, "login history");
		
		//Login history is displayed  
		WebElement loginHistoryPage=driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_title\"]"));
		String logintitle="Login History";
		Thread.sleep(2000);
		assertEquals(getTextFromElement(loginHistoryPage, "login history"),logintitle);
		
		//click download the data is downloaded in .csv format.
		WebElement download=driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		clickElement(download, "download");
		System.out.println("downloaded");
		//confirm it is in csv format

		
		//click on display and layout
		WebElement displayAndLayout=driver.findElement(By.xpath("//div[@id='DisplayAndLayout']"));
		clickElement(displayAndLayout, "display and layout");
		WebElement customize=driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickElement(customize, "customize my tabs");
		
		WebElement availableTabs=driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		select(availableTabs,"availableTabs").selectByValue("report");
	
		//using add button add account to the selected tabs
		WebElement add=driver.findElement(By.xpath("//img[@alt='Add']"));
		clickElement(add,"add");
		
		List <WebElement> selectedTabs=driver.findElements(By.xpath("//select[@id='duel_select_1']"));
		//verifying account is present in selected tabs 
		for(int i=0;i<selectedTabs.size();i++) {
			if(selectedTabs.get(i).getAttribute("value").equals("report")) {
				System.out.println("report is added to selected tabs ");
			}
			else {
				System.out.println("failed to add report");
 		}
		}
		//click on save
		WebElement save=driver.findElement(By.xpath("//input[@value=' Save ']"));
		clickElement(save,"save");
		//checking reports available on tab bar
		WebElement reportId=driver.findElement(By.xpath("//li[@id='report_Tab']"));
		Thread.sleep(2000);
		if(reportId.isDisplayed()) {
			System.out.println("report is visible on the tab bar");
		}
		else {
			System.out.println("report is not visible in the tab bar");
		}
		//click on email
		WebElement clickemail=driver.findElement(By.xpath("//div[@id='EmailSetup']/a[1]"));
		clickElement(clickemail, "email");
		WebElement emailSettings=driver.findElement(By.xpath("//*[@id=\"EmailSetup_child\"]/div[1]"));
		clickElement(emailSettings, "email settings");
		//enter name field
		WebElement Emailname=driver.findElement(By.xpath("//input[@id='sender_name']"));
		enterData(Emailname, "vidhya", "email name");
		//enter mail field
		WebElement email=driver.findElement(By.xpath("//input[@id='sender_email']"));
		enterData(email, "vidhya@tekarch.com", "email ");
		//select radio button
		WebElement radiobutton=driver.findElement(By.xpath("//input[@id='auto_bcc1' and @value='1' ]"));
		if(!radiobutton.isSelected()) {
			clickElement(radiobutton, "radiobutton for auto bcc");
		}
		//click on save button
		WebElement saveemail=driver.findElement(By.xpath("//input[@value=' Save ' ]"));
		clickElement(saveemail, "save");
		acceptAlert(driver);
		WebElement pageemail=driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div/div[1]/div[1]/h1"));
		getTextFromElement(pageemail, "email page");
		WebElement message=driver.findElement(By.xpath("//*[@id=\"meSaveCompleteMessage\"]"));
		if(message.isDisplayed()) {
			System.out.println("successfully updated email");
		}
		else {
			System.out.println("failed to update email");
		}
		WebElement calender=driver.findElement(By.xpath("//div[@id='CalendarAndReminders']/a/span[2]"));
		clickElement(calender, "calender");
		//click on activity
		WebElement calenderActivity=driver.findElement(By.xpath("//a[@id='Reminders_font']"));
		clickElement(calenderActivity, "calender activity");
		//click on open test remainder
		WebElement opentest=driver.findElement(By.xpath("//input[@value='Open a Test Reminder']"));
		clickElement(opentest, "open test remainder");
		String titleofpop=driver.switchTo().frame(0).getTitle();
//		Set<String> handles=driver.getWindowHandles();
		System.out.println(titleofpop);
//window and window handles    
//size is 2
//switched to handle
//   
		System.out.println("activity reaminder pop up displayed");
		
		System.out.println("test case 7 completed");
		
	}
	@Test
	public void testCase8() throws InterruptedException {
	
		login();
		//user menu verification
		clickUserDropDown();
//		WebElement namedownarrow=driver.findElement(By.id("userNav-arrow"));
//		clickElement(namedownarrow,"downarrow");
		//click on develope rconsole
		WebElement developerconsole=driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		clickElement(developerconsole, "developer console");
		String current=driver.getWindowHandle();
		System.out.println("current window:"+current);
		Set<String> handles=driver.getWindowHandles();
		System.out.println("size:"+handles.size());
		for(String handle:handles) {
			System.out.println(handle);
			if(!current.equals(handle)) {
				driver.switchTo().window(handle);
				String title=driver.getTitle();
				System.out.println("the title of the window is:"+title);
				Thread.sleep(3000);
				//WebElement close=driver.findElement(by.xpath)
				WebElement test=driver.findElement(By.xpath("//div[@id='testMenuEntry']"));
				clickElement(test, "test");
				WebElement runAll=driver.findElement(By.xpath("//span[@id='testRunAllButton-textEl']"));
				clickElement(runAll, "run all");
				WebElement result=driver.findElement(By.xpath("//div[@id='messagebox-1001-displayfield-inputEl']"));
				waitForVisibility(result, 30, "result");
			     String show=getTextFromElement(result, "result");
			     Thread.sleep(3000);
			     System.out.println("text from page is:" +show);
			    
			    WebElement ok=driver.findElement(By.xpath("//span[@id='button-1005-btnInnerEl']"));
			    clickElement(ok, "ok");
			     
			     
			}
		}
		driver.switchTo().window(current);
		logout();
		
		System.out.println("test case 8 completed");
		
	}	
	@Test
	public void testCase9() throws InterruptedException {
		
		//verify use menu drop down
		login();
		//usermenu dropdown verification
		clickUserDropDown();
		//logout
		WebElement logoutbutton=driver.findElement(By.linkText("Logout"));
		clickElement(logoutbutton,"logout");
		Thread.sleep(3000);
		String showTitle="Login | Salesforce";
		
		assertEquals(getTitle(driver),showTitle);
	
		System.out.println("test case 9 completed");
	}
		

}
