package com.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseIntegration{

	@BeforeMethod
	public void locateElements() {
		email = driver.findElement(By.id("email"));
		password = driver.findElement(By.id("pass"));
		login = driver.findElement(By.name("login"));
	}
	
	@Test(dataProvider = "loginData")
	public void doLogin(String testName, String emailId, String pass) throws InterruptedException {
		tName = testName;
		email.clear();
		email.sendKeys(emailId);
		password.clear();
		password.sendKeys(pass);
		login.click();
//		Thread.sleep(1000);
	}
	
	@AfterMethod
	public void doAssert() throws InterruptedException {
		String actTitle = driver.getTitle();
		String expTitle;
		
		if(tName.equals("Both are valid")) {
			expTitle = "Facebook";
		}else {
			expTitle = "Log in to Facebook";
		}
		
//		sa.assertEquals(actTitle, expTitle);
		Assert.assertEquals(actTitle, expTitle);
		Thread.sleep(2000);
	}
}
