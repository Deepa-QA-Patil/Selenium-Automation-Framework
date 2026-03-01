package com.PracticalAuto.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.PracticalAuto.actiondriver.ActionDriver;


public class LoginPage {
	
	public ActionDriver actiondriver;
	
	public LoginPage(WebDriver driver) {
		this.actiondriver = new ActionDriver(driver);
	}
	
	
	 private By userNameField = By.xpath("//input[@data-qa='login-email']");
	    private By passwordField = By.xpath("//input[@placeholder='Password']");
	    private By loginButton = By.xpath("//button[normalize-space()='Login']");
	    
	    public void login(String username, String password) {
	    	actiondriver.enterText(userNameField,username);
	    	actiondriver.enterText(passwordField,password);
	    	actiondriver.click(loginButton);
	    }
}
