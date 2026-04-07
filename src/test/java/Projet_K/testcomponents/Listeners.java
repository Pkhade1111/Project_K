package Projet_K.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Project_K.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReprotObject();
//	ExtentReports extent =new ExtentReports();
	ThreadLocal <ExtentTest> extendTest = new ThreadLocal<ExtentTest>();//for running the test parallel smoothly use this
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extendTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extendTest.get().log(Status.FAIL, "Test failed");
		extendTest.get().fail(result.getThrowable());
		try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
				.get(result.getInstance());
		}catch (Exception e1)
		{
			e1.printStackTrace();
		}

		String filepath = null;
		try {
			filepath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extendTest.get().skip("thest has been skip ");
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			}catch (Exception e1)
			{
				e1.printStackTrace();
			}

			String filepath = null;
			try {
				filepath = getScreenShot(result.getMethod().getMethodName(), driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extendTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
			
		}

	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	extent.flush();
	}

}
