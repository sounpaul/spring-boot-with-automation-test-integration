#Author : Sounak Paul

Feature: Serenity BDD Tests to test DELETE employee service

  @DeleteExistingEmployee @DeleteEmployee @EmployeeServiceTests
  Scenario Outline: Validate DELETE employee service for an existing employee
    Given the employee details will be deleted for "<id>"
    When the "DELETE" employee endpoint is invoked
    Then the response status code should be 200
    And the response should contain "Employee deleted" for the given employee ID

    Examples:
      | id   |
      | 1537 |
      | 4019 |

  @DeleteNonExistingEmployee @DeleteEmployee @EmployeeServiceTests
  Scenario Outline: Validate DELETE employee service for a non-existing employee
    Given the employee details will be deleted for "<id>"
    When the "DELETE" employee endpoint is invoked
    Then the response status code should be 404
    And the response should contain "Employee do not exist in database" for the given employee ID

    Examples:
      | id   |
      | 1818 |
      | 1871 |