package com.amazon.test.cases;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.base.base_class;
import com.amazon.utils.utilities;
import com.beust.jcommander.Parameter;

public class do_login extends base_class {

	@BeforeClass
	public void intialize() {
		
		


driver.get("ttps://www.hackerrank.com/signup/");
driver.findElement(By.xpath("//li[@id='Log in']")).click();
	}

	@BeforeMethod
	public void cleanup() {

		driver.findElement(By.xpath("//input[@type='text' and @name='username']")).clear();
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).clear();
		
	}
	
	
	

	@Parameters({"name", "password"})
	@Test
	public void login(String name, String password) {
		
if(utilities.runmode("do_login")==false) {
			
			throw new SkipException("The runmode is no");
		}

		driver.findElement(By.xpath("//input[@type='text' and @name='username']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys(password);
		driver.findElement(
				By.xpath("//span[@class='ui-text' and contains(text(),'Log In')]//parent::div//parent::button"))
				.click();
		
		//span[contains(text(),'Invalid login or password. Please try again.')]//parent::div[@class='form-alert message-error']
		
		
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(utilities.is_element_present("//span[contains(text(),'Invalid login or password. Please try again.')]//parent::div[@class='form-alert message-error']"), true);
		softassert.assertAll();
	}

}
