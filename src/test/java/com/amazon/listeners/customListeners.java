package com.amazon.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.base.base_class;
import com.amazon.utils.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class customListeners extends base_class implements ITestListener {
	
	ExtentReports extent = utilities.ExtentManager();

	public void onTestStart(ITestResult result) {
		
	utilities.test = extent.createTest(result.getName());	

	}

	public void onTestSuccess(ITestResult result) {

	String method = result.getMethod().getMethodName();
	
	Markup m = MarkupHelper.createLabel(method, ExtentColor.GREEN);
utilities.test.pass(m);
		
	}

	public void onTestFailure(ITestResult result) {

		String method = result.getMethod().getMethodName();
		
		Markup m = MarkupHelper.createLabel(method, ExtentColor.RED);
		utilities.test.fail(m);
		try {
			utilities.test.addScreenCaptureFromPath(utilities.screenshot(result.getMethod().getMethodName()), "Error screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
