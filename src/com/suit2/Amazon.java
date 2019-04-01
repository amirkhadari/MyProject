package com.suit2;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Amazon {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
	}
	@Test()
	public void LogIn() throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(), 'Hello, Sign in')]"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@rel='nofollow' and @data-nav-role='signin']//child::span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("9030404209");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Amir@786");
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		Thread.sleep(1000);
	}
	@Test(dependsOnMethods="LogIn")
	public void searchAndSelect() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone 6s");
		driver.findElement(By.xpath("//input[@type='submit' and @class='nav-input']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//h2[text()='Apple iPhone 6s (32GB) - Gold']")).click();
		Thread.sleep(2000);
		
	}
	@Test(dependsOnMethods="searchAndSelect")
	public void buyAndRemovefromCart() throws InterruptedException {

		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();
		
		String parentwindowId = it.next();
		String childwindowId = it.next();
		
		driver.switchTo().window(childwindowId);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@id='hlb-view-cart-announce']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete' and @aria-label='Delete Apple iPhone 6s (32GB) - Gold']")).click();
		Thread.sleep(1000);
		driver.close();
		
		driver.switchTo().window(parentwindowId);
	}
	@Test(dependsOnMethods="buyAndRemovefromCart")
	public void signOut() throws InterruptedException {
		Actions move = new Actions(driver);
		move.moveToElement(driver.findElement(By.xpath("//span[text()='Hello, Amir']"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Sign Out']")).click();
		Thread.sleep(1000);
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
