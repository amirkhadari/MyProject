package com.qa.testutil;

import java.util.ArrayList;

import com.qa.test.Xls_Reader1;

public class GetDataFromExcel {
	
	static Xls_Reader1 reader;
	
	public static ArrayList<Object[]> getDataFromExcel() {
		
		ArrayList<Object[]> mydata = new ArrayList<Object[]>();
		
		try {
			reader = new Xls_Reader1("/home/innoraft/Amir/eclipse-oxygen-workspace/"
					+ "MyProject/src/com/qa/testdata/gur99data.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int rownum=2; rownum<=reader.getRowCount("Sheet1"); rownum++) {
			
			
			String name = reader.getCellData("Sheet1", "CustomerName", rownum);
			String gender = reader.getCellData("Sheet1", "Gender", rownum);
			String date = reader.getCellData("Sheet1", "DOB", rownum);
			String Address = reader.getCellData("Sheet1", "Address", rownum);
			String City = reader.getCellData("Sheet1", "City", rownum);
			String State = reader.getCellData("Sheet1", "State", rownum);
			String PIN = reader.getCellData("Sheet1", "PIN", rownum);
			String MobileNumber = reader.getCellData("Sheet1", "MobileNumber", rownum);
			String E_Mail = reader.getCellData("Sheet1", "E_Mail", rownum);
			String Password = reader.getCellData("Sheet1", "Password", rownum);
			
			Object ob[] = {name, gender, date, Address, City, State, PIN, MobileNumber, E_Mail, Password };
			mydata.add(ob);
		}
		return mydata;
		
	}

}
