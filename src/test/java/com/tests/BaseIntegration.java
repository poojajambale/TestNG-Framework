package com.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.utils.TestUtils;

public class BaseIntegration {
	
	WebDriver driver;
	WebElement email, password, login;
	SoftAssert sa;
	Properties prop;
	TestUtils test = new TestUtils();
	String tName;
	
	@BeforeSuite
	public void doSetup() throws IOException {
		prop = test.readProp();
		
		if(prop.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		}
		else {
			driver = new EdgeDriver();
		}
		
		driver.get(prop.getProperty("siteUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@DataProvider
	public Object[][] loginData() throws IOException{
		return test.readExcelData("Sheet1");
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
//		sa.assertAll();
	}
}
