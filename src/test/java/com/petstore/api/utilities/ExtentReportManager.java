package com.petstore.api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest currentTest;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // timestamp
        String repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
        sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pest Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "pavan");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest = extent.createTest(result.getMethod().getMethodName());
        currentTest.createNode(result.getMethod().getMethodName());
        currentTest.assignCategory(result.getMethod().getGroups());
        currentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest = extent.createTest(result.getMethod().getMethodName());
        currentTest.createNode(result.getMethod().getMethodName());
        currentTest.assignCategory(result.getMethod().getGroups());
        currentTest.log(Status.FAIL, "Test Failed");
        currentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest = extent.createTest(result.getMethod().getMethodName());
        currentTest.createNode(result.getMethod().getMethodName());
        currentTest.assignCategory(result.getMethod().getGroups());
        currentTest.log(Status.SKIP, "Test Skipped");
        currentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        if (extent != null) {
            extent.flush();
        }
    }

    // New method for logging values with color
    public void logValuesWithColor(String label, List<Number> values, String methodName) {
    	 currentTest = extent.createTest(methodName);
        if (currentTest == null) {
            throw new IllegalStateException("ExtentTest is not initialized. Ensure that a test is running.");
        }

        for (Number value : values) {
            double numericValue;

            if (value instanceof Float) {
                numericValue = ((Float) value).doubleValue();
            } else if (value instanceof Double) {
                numericValue = (Double) value;
            } else {
                // Handle other cases or throw an exception
                throw new IllegalArgumentException("Unsupported number type: " + value.getClass().getName());
            }

            if (numericValue < 0) {
                currentTest.info(MarkupHelper.createLabel(label + ": " + numericValue, ExtentColor.RED));
            } else {
                currentTest.info(MarkupHelper.createLabel(label + ": " + numericValue, ExtentColor.GREEN));
            }
        }
    }
}