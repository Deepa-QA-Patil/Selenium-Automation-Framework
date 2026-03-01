package com.PracticalAuto.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.ITestResult;


import com.PracticalAuto.actiondriver.ActionDriver;
import com.PracticalAuto.utilities.ExtentManager;

public class BaseClass {

    public static Properties prop;

    // ThreadLocal Driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ActionDriver> actiondriver = new ThreadLocal<>();
    @BeforeSuite
    public void loadConfig() throws IOException {
        prop = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config.properties");
        prop.load(file);
     // Start the Extent Report 
      //  ExtentManager.getReporter();  moved to Listners
    }

    @SuppressWarnings("deprecation")
	private void launchBrowser() {

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
            ExtentManager.registerDriver(getDriver());
        } 
        else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
            ExtentManager.registerDriver(getDriver());
        } 
        else if (browser.equalsIgnoreCase("edge")) {
        	System.setProperty("webdriver.edge.driver",
                    "E:\\eclipse\\RealAutoPractise\\src\\main\\resources\\msedgedriver.exe");
            driver.set(new EdgeDriver());
            ExtentManager.registerDriver(getDriver());
        } 
        
        else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        actiondriver.set(new ActionDriver(getDriver()));
    }

    private void configureBrowser() {

        int implicitWait = Integer.parseInt(prop.getProperty("implicitwait"));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        getDriver().manage().window().maximize();

        try {
            getDriver().get(prop.getProperty("url"));
        } 
        catch (Exception e) {
            System.out.println("Failed to navigate to URL " + e.getMessage());
        }
    }
    

    @BeforeMethod
    public synchronized void setup() throws IOException {
    	//ExtentManager.startTest("Test Started"); moved to Listners

        System.out.println("Setting up driver for: " + this.getClass().getSimpleName());

        
        launchBrowser();
        configureBrowser();
    }

    @AfterMethod
    public void teardown() {

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    // Getter
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Static wait
    public void staticwait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }
}
