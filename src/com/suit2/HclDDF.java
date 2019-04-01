package com.suit2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;
import com.qa.testutil.TestUtil;

public class HclDDF {
	
	WebDriver driver;
	Xls_Reader1 reader;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		driver.get("https://www.hcltech.com/contact-us/customer");
		
	}
	@Test
	public void EnterCred() {
		reader= new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		
		int rowcount = reader.getRowCount("Sheet1");
		System.out.println(rowcount);
		
		for(int rownum=2; rownum<=rowcount; rownum++) {
			
			
			WebElement firstname = driver.findElement(By.xpath("//input[@id='edit-submitted-full-name']"));	
			WebElement mailid = driver.findElement(By.xpath("//input[@id='edit-submitted-email-address']"));
			WebElement organ = driver.findElement(By.xpath("//input[@id='edit-submitted-organization']"));
			WebElement mobilephone = driver.findElement(By.xpath("//input[@id='edit-submitted-phone']"));
			WebElement CountRY = driver.findElement(By.xpath("//select[@id='edit-submitted-country']"));
			WebElement RELation = driver.findElement(By.xpath("//select[@id='edit-submitted-query-type']"));
			WebElement quer = driver.findElement(By.xpath("//textarea[@id='edit-submitted-message-comments']"));
				
			
			String name =reader.getCellData("Sheet1", "FullName", rownum);
			String mail =reader.getCellData("Sheet1", "BusinessE_Mail", rownum);
			String organisation = reader.getCellData("Sheet1", "Organization", rownum);
			String phone = reader.getCellData("Sheet1", "Mobile", rownum);
			String country = reader.getCellData("Sheet1", "Country", rownum);
			String relation = reader.getCellData("Sheet1", "Relationship", rownum);
			String query = reader.getCellData("Sheet1", "Query", rownum);
			
			firstname.clear();
			firstname.sendKeys(name);
			mailid.clear();
			mailid.sendKeys(mail);
			organ.clear();
			organ.sendKeys(organisation);
			mobilephone.clear();
			mobilephone.sendKeys(phone);
			TestUtil.dropdownSelect(CountRY, country);
			TestUtil.dropdownSelect(RELation, relation);
			quer.clear();
			quer.sendKeys(query);
			
			
		}
	}

}
