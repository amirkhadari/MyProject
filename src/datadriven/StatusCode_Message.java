package datadriven;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class StatusCode_Message {
	
	Xls_Reader1 reader;
	
	@Test
	public void brokenlinks() {
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/abcd.xlsx");
		
		int rowcount = reader.getRowCount("Sheet1");
		for(int row = 2; row<=rowcount; row++) {
			String url = reader.getCellData("Sheet1","Path", row);
			
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.connect();
				int code = connection.getResponseCode();
				String code1 = String.valueOf(code);
				reader.setCellData("Sheet1", "StatusCode", row, code1);
				String Message = connection.getResponseMessage();
				reader.setCellData("Sheet1", "StatusMessage", row, Message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}
