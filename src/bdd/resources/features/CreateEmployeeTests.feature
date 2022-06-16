#Author : Sounak Paul

Feature: Serenity BDD Tests to test ADD employee service

  @AddNonExistingEmployee @AddEmployee @EmployeeServiceTests
  Scenario Outline: Validate ADD employee service for a non-existing employee
    Given the employee request contains "<first_name>", "<last_name>", "<phone_number>"
    When the "ADD" employee endpoint is invoked
    Then the response status code should be 201
    And the response should contain "Employee created" for the given employee ID

    Examples:
      | first_name | last_name | phone_number |
      | TEST5      | TEST6     | 765432190    |
      | TEST8      | TEST9     | 456789904    |

  @AddExistingEmployee @AddEmployee @EmployeeServiceTests
  Scenario Outline: Validate ADD employee service for an existing employee
    Given the employee request contains "<id>" "<first_name>", "<last_name>", "<phone_number>"
    When the "ADD" employee endpoint is invoked
    Then the response status code should be 202
    And the response should contain "Employee exists in database" for the given employee ID

    Examples:
      | id   | first_name | last_name | phone_number |
      | 4597 | TEST5      | TEST6     | 765432190    |
      | 1121 | TEST8      | TEST9     | 456789904    |