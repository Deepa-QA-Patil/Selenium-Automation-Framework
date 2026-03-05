package com.AutoPractice.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.PracticalAuto.Pages.Addcart;
import com.PracticalAuto.Pages.LoginPage;
import com.PracticalAuto.Pages.SearchObjectPage;
import com.PracticalAuto.base.BaseClass;
import com.PracticalAuto.utilities.DataProviders;
import com.PracticalAuto.utilities.ExtentManager;

public class CartItemTest extends BaseClass{
	private LoginPage login;
	private SearchObjectPage srcpge;
	private Addcart acart;
	
	@BeforeMethod
    public void setupPages() {

      

        ExtentManager.startTest(" Testing of adding to cart is Started");

        login = new LoginPage(getDriver());
        srcpge = new SearchObjectPage(getDriver());
        acart =new Addcart(getDriver());
        
        
    }	
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)

	public void AddingITem(String username,String password) throws InterruptedException {
		login.login(username, password);
		srcpge.clickOnProductTab();
		srcpge.enterSearchText("Stylish Dress");
		srcpge.clickOnSearchIcon();
		acart.proceedingcart();
		
	}
	

}
