package com.qa.testutil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestUtil {

	public static void dropdownSelect(WebElement e, String s) {
		Select select = new Select(e);
		select.selectByVisibleText(s);
	}

}
