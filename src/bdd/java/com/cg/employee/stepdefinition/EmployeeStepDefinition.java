package com.cg.employee.stepdefinition;

import com.cg.employee.models.EmployeeDetails;
import com.cg.employee.models.EmployeeUpdateRequest;
import com.cg.employee.steps.EmployeeSteps;
import com.cg.employee.utils.BDDUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class EmployeeStepDefinition {

    @Steps
    EmployeeSteps employeeSteps;

    @Given("the employee request contains {string}, {string}, {string}")
    public void the_employee_request_contains(String firstName, String lastName, String phoneNumber) {
        int id = BDDUtils.randomIdGenerator();
        Serenity.setSessionVariable("id").to(String.valueOf(id));
        employeeSteps.createEmployeeRequest(id, firstName, lastName, phoneNumber);
    }

    @Given("the employee request contains {string} {string}, {string}, {string}")
    public void theEmployeeRequestContains(String id, String firstName, String lastName, String phoneNumber) {
        Serenity.setSessionVariable("id").to(id);
        employeeSteps.createEmployeeRequest(Integer.parseInt(id), firstName, lastName, phoneNumber);
    }

    @When("the {string} employee endpoint is invoked")
    public void the_employee_endpoint_is_invoked(String requestType) {
        employeeSteps.invokeEmployeeService(requestType);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        employeeSteps.validateHttpStatusCode(expectedStatusCode);
    }

    @And("the response should contain {string} for the given employee ID")
    public void the_response_should_contain(String expectedMsg) {
        employeeSteps.validateResponseBody(Serenity.sessionVariableCalled("id"), expectedMsg);
    }

    @And("the response should contain employee details {string}, {string}, {string}")
    public void theResponseShouldContainEmployeeDetails(String first_name, String last_name, String phone_number) {
        employeeSteps.validateEmployeeDetails(first_name, last_name, phone_number);
    }

    @Given("the employee details will be fetched for {string},{string}")
    public void theEmployeeDetailsWillBeFetchedFor(String queryParamName, String queryParamValue) {
        Serenity.setSessionVariable(queryParamName).to(queryParamValue);
    }

    @And("details for multiple employees should be fetched")
    public void detailsForMultipleEmployeesShouldBeFetched() {
        employeeSteps.validateMultipleEmployeeDetails();
    }

    @Given("the employee request to be updated contains {string} {string}, {string}, {string}")
    public void theEmployeeRequestToBeUpdatedContains(String id, String firstName, String lastName, String phoneNumber) {
        Serenity.setSessionVariable("id").to(id);
        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest(firstName, lastName, phoneNumber);
        Serenity.setSessionVariable("employeeUpdateRequest").to(employeeUpdateRequest);
    }

    @Given("the employee details will be deleted for {string}")
    public void theEmployeeDetailsWillBeDeletedFor(String id) {
        Serenity.setSessionVariable("id").to(id);
    }
}
