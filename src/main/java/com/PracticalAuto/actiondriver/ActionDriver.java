package com.PracticalAuto.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PracticalAuto.base.BaseClass;

public class ActionDriver {
	
private WebDriver driver;
private WebDriverWait wait;

public ActionDriver(WebDriver driver) {
    this.driver = driver;

    int explicitWait = Integer.parseInt(
            BaseClass.prop.getProperty("explicitwait")
    );

    this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
}

public void enterText(By by, String value) {
    try {
    	waitForEleentToisible(by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(value);
    } catch (Exception e) {
        System.out.println("Unable to enter text: " + e.getMessage());
    }
}
public String getText(By by) {
    try {
        waitForEleentToisible(by);
        return driver.findElement(by).getText();
    } catch (Exception e) {
        System.out.println("Unable to get the text: " + e.getMessage());
        return "";
    }
}

public void compareText(By by, String expectedText) {
    try {
        waitForEleentToisible(by);

        String actualText = driver.findElement(by).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Text are matching: " + actualText + " equals " + expectedText);
        } else {
            System.out.println("Text are not matching: " + actualText + " not equals " + expectedText);
        }
    } catch (Exception e) {
        System.out.println("Unable to compare the text: " + e.getMessage());
    }
}
public boolean isDisplayed(By by) {
    try {
        waitForEleentToisible(by);

        boolean isDisplayed = driver.findElement(by).isDisplayed();

        if (isDisplayed) {
            System.out.println("Element is visible");
            return isDisplayed;
        } else {
            return isDisplayed;
        }
    } catch (Exception e) {
        System.out.println("Element is not displayed: " + e.getMessage());
        return false;
    }
}
public void waitForPageLoad(int timeoutInSeconds) {
    try {
    	wait.withTimeout(Duration.ofSeconds(timeoutInSeconds))
        .until(webDriver -> ((JavascriptExecutor) webDriver)
        .executeScript("return document.readyState.equals(\"complete\")"));

    System.out.println("Page loaded successfully");
    }

       
     catch (Exception e) {
        System.out.println("Page did not load within " + timeoutInSeconds +
                " seconds: " + e.getMessage());
    }
}
public void scrollToElement(By by) {
    try {
    	applyBorder(by,"orange");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        System.out.println("scrolling");
    } catch (Exception e) {
        System.out.println("Unable to locate element: " + e.getMessage());
    }
}


public void click(By by) {
    try {
    	applyBorder(by,"orange");
    	waitForElementToBEClickable(by);
        driver.findElement(by).click();
    } catch (Exception e) {
    	applyBorder(by,"green");
        System.out.println("Unable to click element: " + e.getMessage());
    }
}
public void clicki(By by) {
    try {
    	
    	waitForElementToBEClickable(by);
        driver.findElement(by).click();
    } catch (Exception e) {
    	
        System.out.println("Unable to click element: " + e.getMessage());
    }
}

public void sendingkey(By by,String name) {
    try {
    	applyBorder(by,"orange");
    	waitForElementToBEClickable(by);
        driver.findElement(by).sendKeys(name);
    } catch (Exception e) {
    	applyBorder(by,"green");
        System.out.println("Unable to send keys: " + e.getMessage());
    }
}
//Wait for the element to be clickable
public void waitForElementToBEClickable(By by) {
	try {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	} catch (Exception e) {
		System.out.println("Element is not clickable :"+ e.getMessage());
	
	}
}


//Wait for element to visible
public void waitForEleentToisible(By by) { 
try {
	applyBorder(by,"red");
	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
} catch (Exception e) {
	applyBorder(by,"green");
	System.out.println("Element is not visible :"+ e.getMessage());
	e.printStackTrace();
}
}

//Utility Method to Border an element
public void applyBorder(By by,String color) {
  try {
      //Locate the element
      WebElement element = driver.findElement(by);
      //Apply the border
      String script = "arguments[0].style.border='3px solid "+color+"'";
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript(script, element);
      Thread.sleep(500);
      
  } catch (Exception e) {
      
  }

}

public void hover(By by ) {
	WebElement ele = driver.findElement(by);
	Actions act = new Actions(driver);
	act.moveToElement(ele);
}


}