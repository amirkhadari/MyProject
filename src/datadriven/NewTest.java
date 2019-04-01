package datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.hcltech.com/");
	}
  @Test
  public void HclTitle() {
	  String actualTitle = driver.getTitle();
	  String expectedTitle = "HCL Technologies: IT Services, Digital Solutions, Technology Consulting";
	  
	  Assert.assertEquals(actualTitle, expectedTitle);
  }
  @Test
  public void HclLogo() {
	  boolean b = driver.findElement(By.xpath("//img[@alt='HCL logo']")).isDisplayed();
	  Assert.assertTrue(b, "Logo is not displayed");
  }
  @AfterMethod
  public void tearDown() {
	  driver.quit();
  }
}
