package com.cg.employee.steps;

import com.cg.employee.models.EmployeeDetails;
import com.cg.employee.models.EmployeeResponse;
import com.cg.employee.models.EmployeeUpdateRequest;
import com.cg.employee.utils.BDDConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.rest;

public class EmployeeSteps {

    Response response;
    EnvironmentVariables envVars;

    private static Logger logger = LoggerFactory.getLogger(EmployeeSteps.class);

    @Step
    public void createEmployeeRequest(int id, String firstName, String lastName, String phoneNumber) {
        EmployeeDetails employeeDetails = new EmployeeDetails(id, firstName, lastName, phoneNumber);
        System.out.println("Storing employee request to Serenity session varibale : " + employeeDetails);
        Serenity.setSessionVariable("employeeRequest").to(employeeDetails);
    }

    @Step
    public void invokeEmployeeService(String requestType) {
        RestAssured.baseURI = EnvironmentSpecificConfiguration.from(envVars).getProperty("app.baseURL");
        RequestSpecification requestSpecification = rest().given().log().all().relaxedHTTPSValidation();
        EmployeeDetails employeeDetails = Serenity.sessionVariableCalled("employeeRequest");
        String endpoint = null;
        if (requestType.equals("ADD")) {
            endpoint = BDDConstants.ADD_EMPLOYEE_ENDPOINT;
            response = requestSpecification
                    .headers("Content-Type", "application/json")
                    .body(employeeDetails)
                    .when()
                    .post(endpoint);

        } else if (requestType.equals("GET_EMPLOYEE_BY_ID")) {
            endpoint = BDDConstants.GET_EMPLOYEE_BY_ID_ENDPOINT;
            response = requestSpecification
                    .queryParam("id", Serenity.sessionVariableCalled("id").toString())
                    .when()
                    .get(endpoint);
        } else if (requestType.equals("GET_EMPLOYEE_BY_FIRSTNAME")) {
            endpoint = BDDConstants.GET_EMPLOYEE_BY_FIRSTNAME_ENDPOINT;
            response = requestSpecification
                    .queryParam("first_name", Serenity.sessionVariableCalled("first_name").toString())
                    .when()
                    .get(endpoint);
        } else if (requestType.equals("GET_EMPLOYEE_BY_LASTNAME")) {
            endpoint = BDDConstants.GET_EMPLOYEE_BY_LASTNAME_ENDPOINT;
            response = requestSpecification
                    .queryParam("last_name", Serenity.sessionVariableCalled("last_name").toString())
                    .when()
                    .get(endpoint);
        } else if (requestType.equals("DELETE")) {
            endpoint = BDDConstants.DELETE_EMPLOYEE_ENDPOINT;
            response = requestSpecification
                    .queryParam("id", Serenity.sessionVariableCalled("id").toString())
                    .when()
                    .delete(endpoint);
        } else if (requestType.equals("UPDATE")) {
            endpoint = BDDConstants.UPDATE_EMPLOYEE_ENDPOINT;
            EmployeeUpdateRequest employeeUpdateRequest = Serenity.sessionVariableCalled("employeeUpdateRequest");
            response = requestSpecification
                    .headers("Content-Type", "application/json")
                    .queryParam("id", Serenity.sessionVariableCalled("id").toString())
                    .when()
                    .body(employeeUpdateRequest)
                    .put(endpoint);
        }
        System.out.println("Submitted request to employee service : " + endpoint);
    }

    @Step
    public void validateHttpStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.then().log().all().extract().statusCode());
    }

    @Step
    public void validateResponseBody(String id, String expectedMsg) {
        EmployeeResponse employeeResponse = response.then().log().all().extract().as(EmployeeResponse.class);
        System.out.println("Response : " + employeeResponse);
        Assert.assertEquals(id, String.valueOf(employeeResponse.getId()));
        Assert.assertEquals(expectedMsg, employeeResponse.getMsg());
    }

    @Step
    public void validateEmployeeDetails(String first_name, String last_name, String phone_number) {
        EmployeeDetails employeeDetails = response.then().extract().as(EmployeeDetails.class);
        Assert.assertEquals(Integer.parseInt(Serenity.sessionVariableCalled("id")), employeeDetails.getId());
        Assert.assertEquals(first_name, employeeDetails.getFirst_name());
        Assert.assertEquals(last_name, employeeDetails.getLast_name());
        Assert.assertEquals(phone_number, employeeDetails.getPhone_number());
    }

    @Step
    public void validateMultipleEmployeeDetails() {
        List<EmployeeDetails> employeeDetails = response.then().extract().as(List.class);
        Assert.assertTrue(employeeDetails.size() > 0);
    }
}
