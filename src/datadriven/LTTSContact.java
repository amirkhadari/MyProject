package datadriven;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LTTSContact {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.ltts.com/contact-us");
		
		driver.findElement(By.xpath("//a[text()='I Accept']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='selected-flag']")).click();
		List<WebElement> options = driver.findElements(By.className("country-name"));
		int size = options.size();
		System.out.println(size);
		for(int i=0; i<size; i++) {
			String countryname = options.get(i).getText();
			if(countryname.equals("Mongolia (Монгол)")) {
				options.get(i).click();
			}
		}
	}

}
