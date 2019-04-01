package com.suit2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.suit2.ListenersI.class)
public class Listener_demo {
	WebDriver driver;
	@Test(priority=1)
	public void LaunchURL() {
			System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://www.demo.guru99.com/V4/");
	}
	@Test(priority=2)
	public void EnterCredentials() {
		driver.findElement(By.name("uid")).sendKeys("mngr180839");
		driver.findElement(By.name("password")).sendKeys("UvEqYqA");
		driver.findElement(By.name("btnLogin")).click();
	}
	@Test(priority=3)
	public void FailTest() {
		driver.findElement(By.xpath("//input[@id='Amir']")).click();
		
	}
}
