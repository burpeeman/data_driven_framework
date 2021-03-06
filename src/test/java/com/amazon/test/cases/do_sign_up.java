package com.amazon.test.cases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.base_class;
import com.amazon.utils.utilities;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class do_sign_up extends base_class {

	@BeforeClass
	public void intialize() {

		driver.get("https://www.hackerrank.com/signup/?h_r=home&h_l=body_top_center_button']");

		driver.findElement(By.xpath("//li[@id='Sign up']")).click();
	}

	@BeforeMethod
	public void cleanup() {

		driver.findElement(By.xpath("//input[@type='text' and @name='name']")).clear();
		driver.findElement(By.xpath("//input[@type='text' and @name='email']")).clear();
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).clear();

	}

	@Test(dataProviderClass = utilities.class, dataProvider = "dataprovider")
	public void signUp(String name, String email, String password) {
		
if(utilities.runmode("do_sign_up")==false) {
			
			throw new SkipException("The runmode is no");
		}

		driver.findElement(By.xpath("//input[@type='text' and @name='name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@type='text' and @name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//span[contains(text(),'Create An Account')]//parent::div//parent::button"))
				.click();

	}

}
