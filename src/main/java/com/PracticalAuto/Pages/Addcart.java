package com.PracticalAuto.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.PracticalAuto.actiondriver.ActionDriver;

public class Addcart {
	public ActionDriver actiondriver;

	public Addcart(WebDriver driver) {
		this.actiondriver = new ActionDriver(driver);
	}
    private By searchedimg = By.xpath("//img[@alt='ecommerce website products']"); 
    private By addcart = By.xpath("//div[@class='productinfo text-center']//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart']");
    private By continueshop = By.xpath("//div[@class='productinfo text-center']//a[@class='btn btn-default add-to-cart'][normalize-space()='Add to cart']");
    private By cartlabel = By.xpath("//body[1]/header[1]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[3]/a[1]");
    private By proceed =By.xpath("//a[normalize-space()='Proceed To Checkout']");
    private By place =By.xpath("//a[normalize-space()='Place Order']");
    private By cardname = By.xpath("//input[@name='name_on_card']");
    private By cardno =By.xpath("//input[@name='card_number']");
    private By cvc =By.xpath("//input[@placeholder='ex. 311']");
    private By xm = By.xpath("//input[@placeholder='MM']");
    private By xy = By.xpath("//input[@placeholder='YYYY']");
    private By submit = By.id("submit");
    public void hoveringcart() throws InterruptedException {
    	actiondriver.hover(searchedimg);
    	Thread.sleep(5000);
    	
    }
    public void cartitem() throws InterruptedException {
    	actiondriver.click(addcart);
    	Thread.sleep(5000);
    	actiondriver.click(continueshop);
    	actiondriver.click(cartlabel);
    	actiondriver.click(proceed);
    	Thread.sleep(3000);
    	actiondriver.scrollToElement(place);
    	Thread.sleep(3000);
    	actiondriver.click(place);
    	
    	
    }
   
    public void addingdetails() throws InterruptedException {
    	actiondriver.sendingkey(cardname,"Deepa");
    	actiondriver.sendingkey(cardno, "45566556");
    	actiondriver.sendingkey(cvc, "34");
    	actiondriver.sendingkey(xm, "06");
    	actiondriver.sendingkey(xy, "2028");
    	Thread.sleep(3000);
    	actiondriver.clicki(submit);
    	Thread.sleep(3000);
    	actiondriver.clicki(submit);
    }
    public void proceedingcart() throws InterruptedException {
//    	hoveringcart();
    	cartitem();
    	addingdetails();
    }
    

}

