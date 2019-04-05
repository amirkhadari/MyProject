package datadriven;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class HclAmpTag {
	
	Xls_Reader1 reader;
	WebDriver driver;
	String Sheet = "Sheet3";
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		
	}
	
	
	@Test
	public void validateStyleTagsTest() {
		int rowcount = reader.getRowCount(Sheet);
//		System.out.println(rowcount);
		for(int row=2; row<=rowcount; row++) {
			String url = reader.getCellData(Sheet, "URL", row);
			driver.get(url);
			
//			System.out.println(url);
			
			List<WebElement> tags = driver.findElements(By.xpath("//style[@type='text/css']"));
			System.out.println(tags.size());
			
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
