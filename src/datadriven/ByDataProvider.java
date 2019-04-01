package datadriven;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.testutil.Data;
import com.qa.testutil.TestUtil;

public class ByDataProvider {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.hcltech.com/contact-us/customer");
	}
	
	@DataProvider	
	public Iterator<Object[]> getData() {
		ArrayList<Object[]> mydata = Data.ImportData();
		return mydata.iterator();
	}
	
	@Test(dataProvider="getData")
	public void EnterData(String name, String mail, String organisation, 
			String phone, String country, String relation, String query) {
		
		WebElement firstname = driver.findElement(By.xpath("//input[@id='edit-submitted-full-name']"));	
		WebElement mailid = driver.findElement(By.xpath("//input[@id='edit-submitted-email-address']"));
		WebElement organ = driver.findElement(By.xpath("//input[@id='edit-submitted-organization']"));
		WebElement mobilephone = driver.findElement(By.xpath("//input[@id='edit-submitted-phone']"));
		WebElement CountRY = driver.findElement(By.xpath("//select[@id='edit-submitted-country']"));
		WebElement RELation = driver.findElement(By.xpath("//select[@id='edit-submitted-query-type']"));
		WebElement quer = driver.findElement(By.xpath("//textarea[@id='edit-submitted-message-comments']"));
		
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
