package datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Guru99BankLoginTest {
	WebDriver driver;
	/**
	 * This test case will initialize webdriver and launch URL
	 */
	@Test(priority = 1,groups = {"bonding", "strong_ties"})
	public void LaunchURL() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.demo.guru99.com/V4/");
	}
	
	/**
	 * This test case will check heading of the Guru99Bank page
	 */
	
	@Test(priority = 2, groups = {"bonding"})
	public void VerifyLaunchPage() {
		boolean b = driver.findElement(By.xpath("//h2[contains(text(), 'Guru99 Bank')]")).isDisplayed();
		Assert.assertTrue(b, "This is not and Guru99Bank");
		System.out.println("This is Guru99Bank Login Page");
	}
	/**
	 * This test case will enter Credentials(username, Password) and Login to the page
	 */
	@Test(priority = 3, groups = {"bonding", "strong_ties"})
	public void EnterCredentials() {
		driver.findElement(By.name("uid")).sendKeys("mngr180839");
		driver.findElement(By.name("password")).sendKeys("UvEqYqA");
		driver.findElement(By.name("btnLogin")).click();
	}
	/**
	 * This test case will check Manager id Presence in DashBoard
	 */
	@Test(priority =4, groups = {"strong_ties"})
	public void VerifyLoggedInPage() {
		boolean b = driver.findElement(By.xpath("//td[contains(text(), 'Manger Id : mngr180839')]")).isDisplayed();
		Assert.assertTrue(b, "This is not mngr180839");
		System.out.println("Manager id is seen in DashBoard");
	}
	/**
	 * This test case will check the New Account, New Customer, Deposit, Withdraw, FundTransfer, BalanceEnquiry
	 * Links will be present in the left side of the panel 
	 */
	@Test(priority = 5, groups = {"bonding"})
	public void VerifyHyperLinks() {
		boolean b1 = driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).isDisplayed();
		Assert.assertTrue(b1, "New Customer Link is not Available");
		System.out.println("New Customer Link is Available");
		boolean b2 = driver.findElement(By.xpath("//a[@href='addAccount.php']")).isDisplayed();
		Assert.assertTrue(b2, "New Account Link is not Available");
		System.out.println("New Account Link is Available");
		boolean b3 = driver.findElement(By.xpath("//a[@href='DepositInput.php']")).isDisplayed();
		Assert.assertTrue(b3, "Deposit Link is not Available");
		System.out.println("Deposit Link is Available");
		boolean b4 = driver.findElement(By.xpath("//a[@href='WithdrawalInput.php']")).isDisplayed();
		Assert.assertTrue(b4, "WithDrawl Link is not Available");
		System.out.println("WithDrawl Link is Available");
		boolean b5 = driver.findElement(By.xpath("//a[@href='FundTransInput.php']")).isDisplayed();
		Assert.assertTrue(b5, "FundTransfer Link is not Available");
		System.out.println("FundTransfer Link is Available");
		boolean b6 = driver.findElement(By.xpath("//a[@href='BalEnqInput.php']")).isDisplayed();
		Assert.assertTrue(b6, "BalanceEnquiry Link is not Available");
		System.out.println("BalanceEnquiry Link is Available");
	}
}
