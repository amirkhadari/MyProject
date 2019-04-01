package com.suit2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLS_Reader {

	public static void main(String[] args) throws Exception {
		File file = new File("/home/innoraft/Amir/eclipse-oxygen-workspace/MyProject/src/com/qa/testdata/abcd.xlsx");
		FileInputStream ip = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(ip);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		int rownum = sheet1.getLastRowNum();
		System.out.println(rownum++);
		for(int row=1; row<rownum; row++) {
			String url = sheet1.getRow(row).getCell(0).getStringCellValue();
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.connect();
			int rc = connection.getResponseCode();
			String rm = connection.getResponseMessage();
			System.out.println(url+"--->"+rc+"--->"+rm);
		}

	}

}
