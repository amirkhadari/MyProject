package datadriven;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.test.Xls_Reader1;

public class ResponseCode {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		
		Xls_Reader1 reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				              +"MyProject/src/com/qa/testdata/abcd.xlsx");
		
		int rowcount = reader.getRowCount("Sheet1");
		System.out.println(rowcount);
		
		for(int row=2; row<rowcount; row++) {
			String Url = reader.getCellData("Sheet1", "Path", row);
			HttpURLConnection connection = (HttpURLConnection) new URL(Url).openConnection();
			connection.connect();
			int rc = connection.getResponseCode();
			String rm = connection.getResponseMessage();
			System.out.println(Url+"--->"+rc+"--->"+rm);
		}
		
		
	}

}
