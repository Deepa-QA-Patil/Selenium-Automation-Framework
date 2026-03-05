package com.AutoPractice.test;

import com.PracticalAuto.Pages.LoginPage;
import com.PracticalAuto.Pages.LogoutPage;
import com.PracticalAuto.base.BaseClass;
import com.PracticalAuto.utilities.DataProviders;
import com.PracticalAuto.utilities.ExtentManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LogoutTest extends BaseClass{
	private LogoutPage logoutpage;
	private LoginPage loginpge;
	

    @BeforeMethod
    public void setupPages() {

      

        ExtentManager.startTest("TestStarted");

        loginpge = new LoginPage(getDriver());
        logoutpage = new LogoutPage(getDriver());   // 🔥 Initialize this also
    }
	
	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void verifyAValidLoginTest(String username,String password ) {

	    // Perform login
	    loginpge.login(username, password);
	    logoutpage.logout();
	}
//@Test	
//public void verifyLogout() {
//	logoutpage.logout();
//}


}
	
	
	
	
	


