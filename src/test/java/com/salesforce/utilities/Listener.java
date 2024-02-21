package com.salesforce.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.salesforce.bases.BaseTest;



public class Listener extends BaseTest implements ITestListener {
	protected Logger Listener_log=LogManager.getLogger();
	protected ExtentReportsUtility extent_report=ExtentReportsUtility.getInstance();

	@Override
	public void onTestStart(ITestResult result) {
		Listener_log.info(result.getMethod().getMethodName()+" is started");
		extent_report.startSingleTestReport(result.getMethod().getMethodName()+" is started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Listener_log.info(result.getMethod().getMethodName()+" is completed successfully");
		extent_report.logTestpassed(result.getMethod().getMethodName()+" is completed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Listener_log.error(result.getMethod().getMethodName()+" is failed");
		extent_report.logTestFailed(result.getMethod().getMethodName()+" is failed");
		String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		System.out.println("filename="+filename);
		String path=Constants.SCREENSHOTS_DIRECTORY_PATH+filename+".png"; // 2024_02_13_02_11_23.png
		//WebDriver driver=getDriverInstance();
		
		takeScreenShot(path);
	
		extent_report.logTestWithscreenshot(System.getProperty("user.dir")+"/reports/screenshots/"+filename+".png");
		extent_report.logTestFailedWithException(result.getThrowable());
		
	}

	@Override
	public void onStart(ITestContext context) {
		Listener_log.info(context.getName()+" is started");
		extent_report.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		Listener_log.info(context.getName()+" is ended");
		extent_report.endReport();

		
	}

}
