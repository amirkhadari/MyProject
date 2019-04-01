package com.suit1;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Flipkart {
	
	WebDriver driver;
	@BeforeClass
	public void Set_Up() {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com/");
	}
	@Test
	public void Login() throws InterruptedException {
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(), 'Login & Signup')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='_2zrpKA']")).sendKeys("9030404209");
		//driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI jUwFiZ']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv']")).sendKeys("Amir@786");
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
		Thread.sleep(2000);
	}
	@Test(dependsOnMethods="Login")
	public void Search_SelectProduct() throws InterruptedException {
		driver.findElement(By.xpath("//input[@class='LM6RPg']")).sendKeys("iphone 6s");
		Thread.sleep(1000);
		WebElement search = driver.findElement(By.xpath("//button[@class='vh79eN']"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click()", search);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(), 'Apple iPhone 6s (Gold, 32 GB)')]")).click();
		Thread.sleep(1000);
		
	}
	@Test(dependsOnMethods="Search_SelectProduct")
	public void AddtoCart_RemovefromCart() throws InterruptedException {
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();
		
		String parentwindowid = it.next();
		String childwindowid =it.next();
		
		driver.switchTo().window(childwindowid);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Remove')]")).click();
		Thread.sleep(1000);
		driver.close();
		
		driver.switchTo().window(parentwindowid);
		
	}
	@Test(dependsOnMethods="AddtoCart_RemovefromCart")
	public void LogOut() {
	
		Actions builder = new Actions(driver);
		Action hover = builder.moveToElement(driver.findElement(By.xpath("//div[contains(text(), 'My Account' )]"))).build();
		hover.perform();
		driver.findElement(By.xpath("//a[@class='_2k68Dy']")).click();
	}
	
	@AfterClass
	public void quit() {
		driver.close();
	}

}
