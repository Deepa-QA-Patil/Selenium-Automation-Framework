package com.AutoPractice.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.PracticalAuto.Pages.LoginPage;
import com.PracticalAuto.Pages.SearchObjectPage;
import com.PracticalAuto.base.BaseClass;
import com.PracticalAuto.utilities.DataProviders;
import com.PracticalAuto.utilities.ExtentManager;

public class SearchObjectTest extends BaseClass {
	private LoginPage loginpge;
	private SearchObjectPage srcpage;
	
	
	@BeforeMethod
    public void setupPages() {

      

        ExtentManager.startTest("TestStarted");

        loginpge = new LoginPage(getDriver());
        srcpage = new SearchObjectPage(getDriver());
    }
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void Searching_product(String username,String password ) {

	    // Perform login
	    loginpge.login(username, password);
	    srcpage.clickOnProductTab();
	    srcpage.enterSearchText("Stylish Dress");
	    srcpage.clickOnSearchIcon();
	   
	    
	    
	    
	}

}
