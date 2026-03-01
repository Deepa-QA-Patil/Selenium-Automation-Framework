package com.AutoPractice.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.PracticalAuto.base.BaseClass;

public class DummyClass extends BaseClass {

    @Test
    public void dummyTest() {

        String title = getDriver().getTitle();

        Assert.assertEquals(
                title,
                "Automation Exercise - Signup / Login",
                "Test failed - Title is not matching"
        );

        System.out.println("Test Passed - Title is matching");
    }
}
