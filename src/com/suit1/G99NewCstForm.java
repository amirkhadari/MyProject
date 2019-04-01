package com.suit1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;
import com.qa.testutil.GetDataFromExcel;

public class G99NewCstForm {
	
	WebDriver driver;
	Xls_Reader1 reader;
	
	@BeforeClass()
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	@Test(dataProvider="getTestData")
	public void EnterData(String name, String gender, String date, String Address,
			String City, String State, String PIN, String MobileNumber, String E_Mail, String Password) throws InterruptedException {
		
			driver.get("http://www.demo.guru99.com/V4/");
			driver.findElement(By.name("uid")).sendKeys("mngr180839");
			driver.findElement(By.name("password")).sendKeys("UvEqYqA");
			driver.findElement(By.name("btnLogin")).click();
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			Thread.sleep(2000);
		
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
		
		//WebElement submit = driver.findElement(By.xpath("//input[@name='sub']"));
		//WebElement reset = driver.findElement(By.xpath("//input[@name='res']"));
		
		
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
	
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testdata = GetDataFromExcel.getDataFromExcel();
		return testdata.iterator();
		
	}

}
