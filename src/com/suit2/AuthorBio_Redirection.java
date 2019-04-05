package com.suit2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class AuthorBio_Redirection {
	
	Xls_Reader1 reader;
	WebDriver driver;
	String Sheet = "Blogger_Author";
	
	@BeforeMethod
	public void setUP() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		reader.addColumn(Sheet, "Status");
	}
	@Test
	public void validateNodeidTest() {
		int rowcount = reader.getRowCount(Sheet);
		for(int row=2; row<=rowcount; row++) {
			String url = reader.getCellData(Sheet, "URL", row);
			System.out.println(url);
			driver.get(url);
			try {
				String actual = driver.findElement(By.xpath("//div[@class='node node-author-bio clearfix']")).getAttribute("id");
				System.out.println(actual);
				String expected = reader.getCellData(Sheet, "Node_id", row);
				System.out.println(expected);
				if(actual.contentEquals(expected)) {
					reader.setCellData(Sheet, "Status", row, "Pass");
				} else {
					reader.setCellData(Sheet, "Status", row, "Fail");
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
