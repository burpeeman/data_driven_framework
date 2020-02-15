package com.amazon.test.cases;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.base.base_class;
import com.amazon.utils.utilities;

public class elements_present extends base_class {
	
	@BeforeClass
	public void intial() {
	
		if(!driver.getTitle().equals("HackerRank")) {
			
			driver.get(config.getProperty("website"));	
			
		}
		
	}
	
	@Test
	public void detect_element_presence() {
		
if(utilities.runmode("elements_present")==false) {
			
			throw new SkipException("The runmode is no");
		}
		//footer[@id='colophon' and @class='hr__site-footer']
		SoftAssert softassert = new SoftAssert();

		softassert.assertEquals(utilities.is_element_present("//header[@id='masthead']"), true,
				"Header section is missing");
		
		softassert.assertEquals(utilities.is_element_present("//footer[@id='colophon' and @class='hr__site-footer']"), true,
				"footer section is missing");
		
		softassert.assertAll();
		
	}
	
}
