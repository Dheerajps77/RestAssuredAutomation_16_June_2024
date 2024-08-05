package com.petstore.api.tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petstore.api.utilities.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class APITestToGetColourCountOfNegativeAndPositive {
    private ExtentReportManager extentReportManager;

    public static void disableSslVerification() {
        RestAssured.config = RestAssuredConfig.config().sslConfig(
            SSLConfig.sslConfig().relaxedHTTPSValidation());
    }

    @BeforeClass
    public void setUp() {
        extentReportManager = new ExtentReportManager();
        extentReportManager.onStart(null); // Initialize ExtentReports
        disableSslVerification();
    }

    @Test
    public void testAPIResponse() {
        Response response = given()
                .baseUri("https://mocky.io")
                .when()
                .get("/v3/9d72cad7-2b19-4d3b-8bf7-a7d6968a1d5f") // Use the actual URL of your mock API
                .then()
                .log().all()
                .extract()
                .response();

        List<Number> positiveValues = response.jsonPath().getList("data.positiveValues");
        List<Number> negativeValues = response.jsonPath().getList("data.negativeValues");

        extentReportManager.logValuesWithColor("Positive Value", positiveValues, "Positive Values Method");
        extentReportManager.logValuesWithColor("Negative Value", negativeValues, "Negative Values Method");
    }

    @AfterClass
    public void tearDown() {
        if (extentReportManager != null) {
            extentReportManager.onFinish(null); // Finalize ExtentReports
        } else {
            System.err.println("ExtentReportManager is not initialized.");
        }
    }
}
