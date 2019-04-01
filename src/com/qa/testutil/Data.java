package com.qa.testutil;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.qa.test.Xls_Reader1;

public class Data {
	
	static Xls_Reader1 reader;
	
	@Test
	public static ArrayList<Object[]> ImportData() {
		
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
				+ "MyProject/src/com/qa/testdata/practicedata.xlsx");
		
		int rowcount = reader.getRowCount("Sheet1");
		for(int row = 2; row<=rowcount; row++) {
			String name =reader.getCellData("Sheet1", "FullName", row);
			String mail =reader.getCellData("Sheet1", "BusinessE_Mail", row);
			String organisation = reader.getCellData("Sheet1", "Organization", row);
			String phone = reader.getCellData("Sheet1", "Mobile", row);
			String country = reader.getCellData("Sheet1", "Country", row);
			String relation = reader.getCellData("Sheet1", "Relationship", row);
			String query = reader.getCellData("Sheet1", "Query", row);
			
			Object hcldata[] = {name, mail, organisation, phone, country, relation, query};
			data.add(hcldata);
		}
		return data;
	}

}
