package datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;
import com.suit1.TestDataImporter;

public class AuthorBio_content {
	WebDriver driver;
	Xls_Reader1 reader;
	String Sheet = "STO2";
//	String url = "https://straighttalk.hcltech.com/mark-settle";
	@BeforeMethod()
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		reader.addColumn(Sheet, "Status");
		reader.addColumn(Sheet, "Linkedin_Button");
		reader.addColumn(Sheet, "Twitter_Button");
		reader.addColumn(Sheet, "BlogPost");
		
	}
	
	@Test()
	public void validateAuthorbioContentTest() throws InterruptedException {
		int rowcount = reader.getRowCount(Sheet);
		for(int row =2; row<=rowcount; row++) {
			String url = reader.getCellData(Sheet, "URL", row);
			driver.get(url);
			Thread.sleep(2000);
			try {
				boolean author =driver.findElement(By.xpath("//div[@class='node node-author-bio clearfix']")).isDisplayed();
				if(author) {
						reader.setCellData(Sheet, "Status", row, "This is Author Bio");
				} else {
					reader.setCellData(Sheet, "Status", row, "This is not Author Bio");
				}
			} catch (NoSuchElementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try {
					boolean linkedin = driver.findElement(By.xpath("//a[text()='LinkedIn']")).isDisplayed();
					if(linkedin) {
						reader.setCellData(Sheet, "Linkedin_Button", row, "Present");
					} else {
					reader.setCellData(Sheet, "Linkedin_Button", row, "Not Present");
					}
					boolean twitter = driver.findElement(By.xpath("//a[text()='Twitter']")).isDisplayed();
					if(twitter) {
						reader.setCellData(Sheet, "Twitter_Button", row, "Present");
					} else {
					reader.setCellData(Sheet, "Twitter_Button", row, "Not Present");
					}
					boolean blogpost = driver.findElement(By.xpath("//div[starts-with(text(),'Selected Blog Posts:')]")).isDisplayed();
					if(blogpost) {
						reader.setCellData(Sheet, "BlogPost", row, "Present");
					} else {
						reader.setCellData(Sheet, "BlogPost", row, "Not Present");
					}
				} catch (NoSuchElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
	}
	@AfterMethod()
	public void tearDown() {
		driver.close();
	}

}
