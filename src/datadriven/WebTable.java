package datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/home/innoraft/Amir/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://demo.automationtesting.in/WebTable.html");
		
		String Number = driver.findElement(By.xpath("//div[text()='balajikalidindi@gmail.com']//parent::div[@role='gridcell']"
				+ "//following-sibling::div[4]//child::div")).getText();
		
		System.out.println(Number);

	}

}
