package com.suit1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class NewCustomerForm {
	
	WebDriver driver;
	Xls_Reader1 reader;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void navigatesNewCustomerPage() throws InterruptedException {
		driver.get("http://www.demo.guru99.com/V4/");
		driver.findElement(By.name("uid")).sendKeys("mngr180839");
		driver.findElement(By.name("password")).sendKeys("UvEqYqA");
		driver.findElement(By.name("btnLogin")).click();
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Thread.sleep(2000);
	}
	
	@Test(priority=2)
	public void EnterCredentials() throws InterruptedException {
		
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace"
				+ "/MyProject/src/com/qa/testdata/gur99data.xlsx");
		
		

		int rowcount = reader.getRowCount("Sheet1");
		System.out.println(rowcount);
		
		for(int rownum=2; rownum<=rowcount; rownum++) {
		
			WebElement custname =driver.findElement(By.xpath("//input[@name='name']"));
			WebElement male =driver.findElement(By.xpath("//input[@value='m']"));
			WebElement female =driver.findElement(By.xpath("//input[@value='f']"));
			WebElement dob =driver.findElement(By.xpath("//input[@name='dob']"));
			WebElement address =driver.findElement(By.xpath("//textarea[@name='addr']"));
			WebElement city =driver.findElement(By.xpath("//input[@name='city']"));
			WebElement state =driver.findElement(By.xpath("//input[@name='state']"));
			WebElement pin =driver.findElement(By.xpath("//input[@name='pinno']"));
			WebElement mobile =driver.findElement(By.xpath("//input[@name='telephoneno']"));
			WebElement emailid =driver.findElement(By.xpath("//input[@name='emailid']"));
			WebElement password =driver.findElement(By.xpath("//input[@name='password']"));
			
			WebElement submit = driver.findElement(By.xpath("//input[@name='sub']"));
			WebElement reset = driver.findElement(By.xpath("//input[@name='res']"));
			
		
			Thread.sleep(2000);
			
			String name = reader.getCellData("Sheet1", "CustomerName", rownum);
			String gender = reader.getCellData("Sheet1", "Gender", rownum);
			String date = reader.getCellData("Sheet1", "DOB", rownum);
			String Address = reader.getCellData("Sheet1", "Address", rownum);
			String City = reader.getCellData("Sheet1", "City", rownum);
			String State = reader.getCellData("Sheet1", "State", rownum);
			String PIN = reader.getCellData("Sheet1", "PIN", rownum);
			String MobileNumber = reader.getCellData("Sheet1", "MobileNumber", rownum);
			String E_Mail = reader.getCellData("Sheet1", "E_Mail", rownum);
			String Password = reader.getCellData("Sheet1", "Password", rownum);
			
			custname.clear();
			custname.sendKeys(name);
				if(gender.equals("f")) {
						female.click();
				}else {
						male.click();
				}
			dob.clear();
			dob.sendKeys(date);
			address.clear();
			address.sendKeys(Address);
			city.clear();
			city.sendKeys(City);
			state.clear();
			state.sendKeys(State);
			pin.clear();
			pin.sendKeys(PIN);
			mobile.clear();
			mobile.sendKeys(MobileNumber);
			emailid.clear();
			emailid.sendKeys(E_Mail);
			password.clear();
			password.sendKeys(Password);
			
			boolean s = submit.isDisplayed();
			Assert.assertTrue(s, "Submit button is not displayed");
			
			boolean r = reset.isDisplayed();
			Assert.assertTrue(r, "Reset button is not displayed");
			
			boolean s1 = submit.isEnabled();
			Assert.assertTrue(s1, "Submit button is not enabled");
			
			boolean r1 = reset.isEnabled();
			Assert.assertTrue(r1, "Reset button is not enabled");
			
			submit.click();
			
			Thread.sleep(2000);
			WebElement message = driver.findElement(By.xpath("//p[@class='heading3']"));
			String successmessage = message.getText();
			
				if(successmessage.equals("Customer Registered Successfully!!!")) {
					reader.setCellData("Sheet1", "Status", rownum, "Pass");
					String custid = driver.findElement(By.xpath("//td[text()='Customer ID']//following::td[1]")).getText();
					reader.setCellData("Sheet1", "CustomerID", rownum, custid);
			
				}else {
					reader.setCellData("Sheet1", "Status", rownum, "Fail");
				}
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		}
	}
/*
	@AfterClass
	public void tearDown() {
		driver.quit();
	}*/

}
