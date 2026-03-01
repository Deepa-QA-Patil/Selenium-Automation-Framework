package com.PracticalAuto.listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.PracticalAuto.base.BaseClass;
import com.PracticalAuto.utilities.ExtentManager;

public class TestListner implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		ExtentManager.startTest(testname);
		ExtentManager.logStep("Test Started: "+ testname);
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), testname);
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    String testname = result.getMethod().getMethodName();

	    // 🔥 Create test if not already started
	    if (ExtentManager.isTestStarted() == false) {
	        ExtentManager.startTest(testname);
	    }

	    String failmessage = result.getThrowable().getMessage();
	    ExtentManager.logStep(failmessage);
	    ExtentManager.logFailure(BaseClass.getDriver(), "Test failed!!", testname);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		ExtentManager.logSkip("Test skiped" + testname);
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getReporter();
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.endTest();
		ITestListener.super.onFinish(context);
	}
	

}
