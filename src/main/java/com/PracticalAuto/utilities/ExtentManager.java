package com.PracticalAuto.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static Map<Long, WebDriver> driverMap = new HashMap<>();
	
	
	//Initialize the Extent Report
	public synchronized static ExtentReports getReporter() {
	    if (extent == null) {
	      String  reportPath = System.getProperty("user.dir") + "/src/test/resources/ExtentReport/ExtentReports.html";
	      ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	      spark.config().setReportName("Automation Test Report");
	      spark.config().setDocumentTitle("OrangeHRM Report");
	      spark.config().setTheme(Theme.DARK);

	      extent = new ExtentReports();
	      extent.attachReporter(spark);
	      // Adding system information
	      extent.setSystemInfo("Operating System", System.getProperty("os.name"));
	      extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	      extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    }
	    return extent;
	}
	// Start the Test
	public static ExtentTest startTest(String testName) {
	    ExtentTest extentTest = getReporter().createTest(testName);
	    test.set(extentTest);
	    return extentTest;
	}

	//End a Test
	public static void endTest() {
	    getReporter().flush();
	}
	//Get Current Thread's test
	public static ExtentTest getTest() {
	    ExtentTest extentTest = test.get();

	    if (extentTest == null) {
	        throw new IllegalStateException(
	            "ExtentTest is NULL. Did you forget to call startTest()?");
	    }

	    return extentTest;
	}
	//Method to get the name of the current test
	public static String getTestName() {
	    ExtentTest currentTest = getTest();
	    if (currentTest != null) {
	        return currentTest.getModel().getName();
	    } else {
	        return "No test is currently  active for this thread ";
	    }
	}
	
	public static boolean isTestStarted() {
	    return test.get() != null;
	}
	//Log a step
	public static void logStep(String logMessage) {
	    getTest().info(logMessage);
	}

	//Log a step validation with screenshot
	public static void logStepWithScreenshot(WebDriver driver, String logMessage) {
	    getTest().pass(logMessage);
	    //Screenshot method
	    attachScreenshot(driver, logMessage);
	}

	//Log a Failure
	public static void logFailure(WebDriver driver, String logMessage, String screenshotPath) {
		String colormsg= String.format("<span style='color:red;'>%s</span>",logMessage);
	    getTest().fail(colormsg);
	    //Screenshot method
	    attachScreenshot(driver, logMessage);
	}

	//Log a skip
	public static void logSkip(String logMessage) {
		String colormsg= String.format("<span style='color:oragne;'>%s</span>",logMessage);
	    getTest().skip(colormsg);
	}

	//Take a screenshot with date and time in the file
	public static String takeScreenshot(WebDriver driver, String screenshotName) {

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
	            .format(new Date());

	    // Better location (NOT inside src)
	    String screenshotDir = System.getProperty("user.dir")
	            + "/src/test/resources/screenshots/";

	    // Create folder if not exists
	    File directory = new File(screenshotDir);
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    String destPath = screenshotDir
	            + screenshotName + "_" + timeStamp + ".png";

	    File finalPath = new File(destPath);

	    try {
	        FileUtils.copyFile(src, finalPath);
	        System.out.println("Screenshot saved at: " + finalPath.getAbsolutePath());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return convertToBase64(finalPath);  // IMPORTANT
	}

	    
	
	
	
	

	//Convert screenshot to Base64 format
	public static String convertToBase64(File screenShotFile) {
	    String base64Format = "";
	    //Read the file content into a byte array
	    
	    try {
	    	byte[] fileContent  = FileUtils.readFileToByteArray(screenShotFile);
	    	base64Format = Base64.getEncoder().encodeToString(fileContent);
	    	
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return base64Format;
	}
	
	//Attach screenshot to report using Base64
	public static void attachScreenshot(WebDriver driver, String message) {
	    try {
	        String screenShotBase64 = takeScreenshot(driver, getTestName());
	        getTest().info(message, com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotBase64).build());
	    } 
	    catch (Exception e) {
	        getTest().fail("Failed to attach screenshot:" + message);
	        e.printStackTrace();
	    }
	}






	//Register WebDriver for current Thread
	public static void registerDriver(WebDriver driver) {
	    driverMap.put(Thread.currentThread().getId(), driver);
	}

}
