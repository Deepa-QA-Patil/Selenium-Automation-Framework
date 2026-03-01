package com.AutoPractice.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.PracticalAuto.Pages.LoginPage;
import com.PracticalAuto.base.BaseClass;
import com.PracticalAuto.utilities.DataProviders;
import com.PracticalAuto.utilities.ExtentManager;

public class LoginPageTest extends BaseClass {
	
	 private LoginPage loginPage;
	 
	 @BeforeMethod
	    public void setupPages() {
		 ExtentManager.startTest("TestStarted");
	        loginPage = new LoginPage(getDriver());
	    }
	 @Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	    public void verifyAValidLoginTest(String username,String password ) {

	        // Perform login
	        loginPage.login(username, password);

}
	 
	 @Test(dataProvider = "invalidata", dataProviderClass = DataProviders.class)
	    public void verifyInValidLoginTest(String username,String password) throws InterruptedException {
	        loginPage.login(username,password );
	        Thread.sleep(5000);

}
}
