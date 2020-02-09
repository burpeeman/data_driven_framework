package com.amazon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.amazon.utils.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class base_class {
	public static WebDriver driver=null;
	public static Properties object = new Properties();
	public static Properties config = new Properties();
	public FileInputStream fis;
	public static ExcelReader excel;
	public ExtentHtmlReporter HtmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setup() {

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//Porperties//object.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				object.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//Porperties//config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//test//resources//excutiables//chromedriver.exe");
				driver = new ChromeDriver();
			}

			driver.get(config.getProperty("website"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

	}
	
	@BeforeTest
	public void ExtentSetup() {
	
		HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//target//extent.html");
		
	HtmlReporter.config().setEncoding("utf-8");
	HtmlReporter.config().setDocumentTitle("data diven project");
	HtmlReporter.config().setReportName("test summary");
	HtmlReporter.config().setTheme(Theme.STANDARD);
	
	extent = new ExtentReports();
	extent.attachReporter(HtmlReporter);
	
	extent.setSystemInfo("automation engg", "Rishabh");
		
	}
	
	@AfterTest
	public void ExtentTeardown() {
		
	extent.flush();	
		
	}

}
