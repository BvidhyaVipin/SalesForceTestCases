            package com.salesforce.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportsUtility  {
	public static ExtentReports extent_report ;
	public static ExtentSparkReporter extent_spark;
	public static ExtentTest extent_test;
	private static ExtentReportsUtility extent;
	
	private ExtentReportsUtility() {
		
	}
	
	public static ExtentReportsUtility getInstance() {
		if(extent==null) {
			extent=new ExtentReportsUtility();
		}

		return extent;
	}
	
	public void startExtentReport() {
		extent_report= new ExtentReports();
		extent_spark = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
		extent_report.setSystemInfo("Host Name", "Salesforce");
		extent_report.setSystemInfo("Environment", "QA");
		extent_report.setSystemInfo("User Name", "vidhya");
		
		extent_spark.config().setDocumentTitle("Test Execution Report");
		extent_spark.config().setReportName("salesforce tests");
		extent_spark.config().setTheme(Theme.DARK);
		extent_report.attachReporter(extent_spark);	
	}
	
	public void startSingleTestReport(String methodName) {
		extent_test=extent_report.createTest(methodName);
	}
	public void endReport() {
		extent_report.flush();
	}
	
	public void logTestInfo(String text) {
		//System.out.println("test logger object="+testLogger);
		extent_test.log(Status.INFO,text);
		//testLogger.info(text);
	}
	
	public void logTestpassed(String text) {
		extent_test.log(Status.PASS,MarkupHelper.createLabel(text, ExtentColor.GREEN));
		//testLogger.pass(MarkupHelper.createLabel(text, ExtentColor.GREEN));
	}
	
	public void logTestFailed(String text) {
		extent_test.log(Status.FAIL,MarkupHelper.createLabel(text, ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Throwable e) {
		extent_test.log(Status.FAIL,e);
	
		}
	
	public void logTestWithscreenshot(String path) {
		//testLogger.addScreenCaptureFromBase64String(path);
		extent_test.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
	}

}
