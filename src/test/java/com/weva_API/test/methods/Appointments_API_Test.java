package com.weva_API.test.methods;

import org.Weva.constants.Constants;
import org.junit.jupiter.api.Assertions;
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
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void Find_A_Doctor_get_API_Validation_test() {
		RequestSpecification request = RestAssured.given();
		request.baseUri(Constants.BaseURI);
		Response response = request.given().auth().preemptive().oauth2(Constants.BearerToken).param("popularSpecialties", "true")
				.param("popularSpecialtiesCount", "15").get("/specialty");
		System.out.println("Responce code is " + response.getStatusCode());
		Assertions.assertEquals(200, response.getStatusCode());
	}

	@Test
	public void Family_Member_apptmnts_get_API_Validation_test() {
		RequestSpecification request = RestAssured.given();
		request.baseUri(Constants.BaseURI);
		Response response = request.given().auth().preemptive().oauth2(Constants.BearerToken).param("pageNumber", "1")
				.param("pageSize", "2").param("familyMembersAppointments", "true").get("/appointments");
		System.out.println("Responce code is " + response.getStatusCode());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	@Test
	public void Diagnosis_get_API_Validation_test() {
		RequestSpecification request = RestAssured.given();
		request.baseUri(Constants.BaseURI);
		Response response = request.given().auth().preemptive().oauth2(Constants.BearerToken).get("/diagnoses");
		System.out.println("Responce code is " + response.getStatusCode());
		Assertions.assertEquals(200, response.getStatusCode());
	}
}
