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
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class utilities extends base_class {

	public static ExtentHtmlReporter HtmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

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

		String filepath = System.getProperty("user.dir") + "//target//screenshot//" + test_name + date + ".png";

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filepath;
	}

	public static ExtentReports ExtentManager() {

		HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//target//extent.html");

		HtmlReporter.config().setEncoding("utf-8");
		HtmlReporter.config().setDocumentTitle("data diven project");
		HtmlReporter.config().setReportName("test summary");
		HtmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(HtmlReporter);

		extent.setSystemInfo("automation engg", "Rishabh");
		
		return extent;

	}

	@DataProvider
	public String[][] dataprovider(Method M) {

		String[][] getdata = null;

		String sheet = null;
		if (M.getName().equals("signUp")) {

			excel = new ExcelReader(
					System.getProperty("user.dir") + "//src//test//resources//ExcelFiles//data_providers.xlsx");

			sheet = "Sheet1";
		}

		else if (M.getName().equals("login")) {

			excel = new ExcelReader(
					System.getProperty("user.dir") + "//src//test//resources//ExcelFiles//data_providers.xlsx");

			sheet = "Sheet2";
		}

		int row_count = excel.getRowCount(sheet);
		int cols_count = excel.getColumnCount(sheet);

		getdata = new String[row_count - 1][cols_count];

		for (int i = 2; i <= row_count; i++) {

			for (int j = 0; j < cols_count; j++) {

				getdata[i - 2][j] = excel.getCellData(sheet, j, i);

			}

		}

		return getdata;
	}

}
