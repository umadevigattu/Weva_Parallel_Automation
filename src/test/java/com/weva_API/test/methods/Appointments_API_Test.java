package com.weva_API.test.methods;

import org.Weva.constants.Constants;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@Listeners(com.weva.common.ExtentReportListeners.class)

public class Appointments_API_Test {
	@Test
	public void appointments_get_API_Validation_test() {
		RequestSpecification request = RestAssured.given();
		request.baseUri(Constants.BaseURI);
		Response response = request.given().auth().preemptive().oauth2(Constants.BearerToken).param("pageNumber", "1")
				.param("pageSize", "2").get("/appointments");
		System.out.println("Responce code is " + response.getStatusCode());
	}

}
