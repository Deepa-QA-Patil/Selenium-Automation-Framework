package com.PracticalAuto.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.PracticalAuto.actiondriver.ActionDriver;

public class LogoutPage {
public ActionDriver actiondriver;
	
	public LogoutPage(WebDriver driver) {
		this.actiondriver = new ActionDriver(driver);
	}
	
	
	 private By Logoutfield = By.xpath("//a[normalize-space()='Logout']");
	   
	    
	    public void logout() {
	    	
	    	actiondriver.click(Logoutfield);
	    }
	    
}
