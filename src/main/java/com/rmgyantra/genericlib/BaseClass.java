package com.rmgyantra.genericlib;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	@BeforeSuite
	public void beforeSuiteConfig() {
		baseURI = "http://localhost:8084";
		port = 8084;
		DataBaseUtilities.connectToDB();
	}
	
	
	
	@AfterSuite
	public void confibAfterSuite() throws SQLException {

		DataBaseUtilities.closeDb();
	}
	
}
