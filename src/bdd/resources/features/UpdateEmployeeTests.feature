#Author : Sounak Paul

Feature: Serenity BDD Tests to test UPDATE employee service

  @UpdateExistingEmployee @UpdateEmployee @EmployeeServiceTests
  Scenario Outline: Validate UPDATE employee service for an existing employee
    Given the employee request to be updated contains "<id>" "<first_name>", "<last_name>", "<phone_number>"
    When the "UPDATE" employee endpoint is invoked
    Then the response status code should be 200
    And the response should contain employee details "<first_name>", "<last_name>", "<phone_number>"

    Examples:
      | id   | first_name | last_name | phone_number |
      | 2079 | TEST5      | TEST6     | 765432190    |
      | 2092 | TEST8      | TEST9     | 456789904    |

  @UpdateNonExistingEmployee @UpdateEmployee @EmployeeServiceTests
  Scenario Outline: Validate UPDATE employee service for a non-existing employee
    Given the employee request to be updated contains "<id>" "<first_name>", "<last_name>", "<phone_number>"
    When the "UPDATE" employee endpoint is invoked
    Then the response status code should be 404

    Examples:
      | id     | first_name | last_name | phone_number |
      | 207923 | TEST5      | TEST6     | 765432190    |
      | 209255 | TEST8      | TEST9     | 456789904    |