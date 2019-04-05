package datadriven;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class RedirectionUrls {
	
	Xls_Reader1 reader;
	String url = "https://straighttalk.hcltech.com/isaac-sacolick";
	boolean redirect = false;
	String SheetN = "LTTS";
	
	@Test
	public void Redirection() {
		
//		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
//				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
//		int rowcount = reader.getRowCount(SheetN);
//		for (int row = 2; row<=rowcount; row++) {
//			
//			String url = reader.getCellData(SheetN, "URL", row);
		try {
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
			int status = conn.getResponseCode();
			if (status != HttpsURLConnection.HTTP_OK) {
				if (status == HttpsURLConnection.HTTP_MOVED_TEMP
					|| status == HttpsURLConnection.HTTP_MOVED_PERM
						|| status == HttpsURLConnection.HTTP_SEE_OTHER)
				redirect = true;
			}
			System.out.println(status);
			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");
				conn = (HttpsURLConnection) new URL(newUrl).openConnection();
				System.out.println("Redirect to URL : " + newUrl);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	}

}
