package com.suit1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class BreadCrumbTestSTO {
	
	WebDriver driver;
	Xls_Reader1 reader;
	String Sheet = "STO";
	
	@BeforeMethod 
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		
//		reader.addColumn(Sheet, "BreaderCrumb");
//		reader.addColumn(Sheet, "HeaderPath");
	}
	@DataProvider
	public Object[][] getURL() {
		Object[][] data = TestDataImporter.getTestData(Sheet);
		return data;
	}
	
	@Test(dataProvider ="getURL")
	public void validateBreadCrumbTest(String url) throws InterruptedException {
			driver.get(url);
			Thread.sleep(1000);
			boolean breadcrumb = driver.findElement(By.xpath("//ol[@class='breadcrumb breadcrumb-list']")).isDisplayed();
			Assert.assertTrue(breadcrumb, "BreadCrumb is not Displayed");
			
			WebElement HomePageLink = driver.findElement(By.xpath("//a[text()='Home']"));
			HomePageLink.click();
			Thread.sleep(2000);
			
			String actualtitle = driver.getTitle();
			String exptitle = "Straight Talk | Insights From & For C-Level Technology Executives";
			Assert.assertEquals(actualtitle, exptitle);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
