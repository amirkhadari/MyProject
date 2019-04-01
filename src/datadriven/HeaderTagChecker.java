package datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class HeaderTagChecker {
	
	 Xls_Reader1 reader;
	 WebDriver driver;
	 String Sheetname = "Sheet2";
	
	@BeforeMethod
	 public void setUP() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		
		
	}
	@Test
	public void ExtractingHeaderTags() {
			int rowcount = reader.getRowCount(Sheetname);
			
			for(int row = 2; row<=rowcount; row++) {
				String url = reader.getCellData(Sheetname, "URL", row);
				driver.get(url);
				
				int h1 = driver.findElements(By.tagName("h1")).size();
				String h1count = Integer.toString(h1);
//				System.out.println(h1count);
				
				reader.setCellData(Sheetname,"H1", row, h1count);
				
				int h2 = driver.findElements(By.tagName("h2")).size();
				String h2count = Integer.toString(h2);
//				System.out.println(h2count);
				
				reader.setCellData(Sheetname,"H2", row, h2count);
				
				int h3 = driver.findElements(By.tagName("h3")).size();
				String h3count = Integer.toString(h3);
//				System.out.println(h2count);
				
				reader.setCellData(Sheetname,"H3", row, h3count);
				
				int h4 = driver.findElements(By.tagName("h4")).size();
				String h4count = Integer.toString(h4);
//				System.out.println(h2count);
				
				reader.setCellData(Sheetname,"H4", row, h4count);
				
				int h5 = driver.findElements(By.tagName("h5")).size();
				String h5count = Integer.toString(h5);
//				System.out.println(h2count);
				
				reader.setCellData(Sheetname,"H5", row, h5count);
				
				int h6 = driver.findElements(By.tagName("h6")).size();
				String h6count = Integer.toString(h6);
//				System.out.println(h2count);
				
				reader.setCellData(Sheetname,"H6", row, h6count);
				
		}

	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
