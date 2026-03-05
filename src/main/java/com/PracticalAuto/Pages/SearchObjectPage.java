package com.PracticalAuto.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.PracticalAuto.actiondriver.ActionDriver;

public class SearchObjectPage {
	public ActionDriver actiondriver;

	public SearchObjectPage(WebDriver driver) {
		this.actiondriver = new ActionDriver(driver);
	}
    // Locators
    private By productTab = By.xpath("//a[@href='/products']");  
    private By searchTextField = By.xpath("//input[@id='search_product']");  
    private By searchIcon = By.xpath("//button[@id='submit_search']");  
    private By scrolelement = By.xpath("(//h4[@class='panel-title'])[3]");
    // Actions

    // Click on Product Tab
    public void clickOnProductTab() {
    	actiondriver.click(productTab);
    }

    // Enter text in Search field
    public void enterSearchText(String productName) {
    	
    	actiondriver.sendingkey(searchTextField,productName);
    }

    // Click on Search icon
    public void clickOnSearchIcon() {
    	actiondriver.click(searchIcon);
    	actiondriver.scrollToElement(scrolelement);
    }

    // Combined method (Best Practice)
    public void searchProduct(String productName) {
        clickOnProductTab();
        enterSearchText(productName);
        clickOnSearchIcon();
        
    }
}


