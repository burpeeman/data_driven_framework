package com.amazon.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.amazon.base.base_class;

public class utilities extends base_class {

	public static boolean is_element_present(String xpath) {

		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	public static String screenshot(String test_name) {
		
		Date d = new Date();
		
		String date = d.toString().replace(":", "_").replace(" ", "_");
		
		String filepath =System.getProperty("user.dir")+"//target//screenshot//"+test_name+date+".png";
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
		return filepath;
	}

	@DataProvider
	public String[][] dataprovider(Method M) {
		
		
		
		String[][] getdata=null; 
		
		String sheet=null;
		if(M.getName().equals("signUp")) {

		excel = new ExcelReader(
				System.getProperty("user.dir") + "//src//test//resources//ExcelFiles//data_providers.xlsx");
		
		 sheet = "Sheet1";
		}
		
		else if(M.getName().equals("login")) {
			
			excel = new ExcelReader(
					System.getProperty("user.dir") + "//src//test//resources//ExcelFiles//data_providers.xlsx");
			
			 sheet = "Sheet2";
		}
		
		int row_count = excel.getRowCount(sheet);
		int cols_count = excel.getColumnCount(sheet);
		
		getdata = new String[row_count-1][cols_count];
		
		for(int i=2;i<=row_count;i++) {
			
			for(int j=0;j<cols_count;j++) {
				
				getdata[i-2][j] = excel.getCellData(sheet, j, i);
				
			}
			
		}
		
		
		return getdata;
	}

}
