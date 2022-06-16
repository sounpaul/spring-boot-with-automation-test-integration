#Author : Sounak Paul

Feature: Serenity BDD Tests to test GET employee service

  @GetExistingEmployeeById @GetEmployee @EmployeeServiceTests
  Scenario Outline: Validate GET employee service for an existing employee
    Given the employee details will be fetched for "id","<id>"
    When the "GET_EMPLOYEE_BY_ID" employee endpoint is invoked
    Then the response status code should be 200
    And the response should contain employee details "<first_name>", "<last_name>", "<phone_number>"

    Examples:
      | id    | first_name | last_name | phone_number |
      | 11387 | ABCD       | Paul      | 986876       |
      | 2330  | TEST5      | TEST6     | 765432190    |

  @GetNonExistingEmployeeById @GetEmployee @EmployeeServiceTests
  Scenario Outline: Validate GET employee service for an non-existing employee
    Given the employee details will be fetched for "id","<id>"
    When the "GET_EMPLOYEE_BY_ID" employee endpoint is invoked
    Then the response status code should be 404

    Examples:
      | id     |
      | 425676 |
      | 765433 |

  @GetEmployeeDetailsByFirstOrLastName @GetEmployee @EmployeeServiceTests
  Scenario Outline: Validate GET employee service for existing employees searched by first or last name
    Given the employee details will be fetched for "<query_parameter_name>","<query_parameter_value>"
    When the "<endpoint>" employee endpoint is invoked
    Then the response status code should be 200
    And details for multiple employees should be fetched

    Examples:
      | query_parameter_name | query_parameter_value | endpoint                  |
      | first_name           | TEST5                 | GET_EMPLOYEE_BY_FIRSTNAME |
      | first_name           | ABCD                  | GET_EMPLOYEE_BY_FIRSTNAME |
      | last_name            | TEST9                 | GET_EMPLOYEE_BY_LASTNAME  |
      | last_name            | Paul                  | GET_EMPLOYEE_BY_LASTNAME  |

  @GetNonExistingEmployeeDetailsByFirstOrLastName @GetEmployee @EmployeeServiceTests
  Scenario Outline: Validate GET employee service for non-existing employees searched by first or last name
    Given the employee details will be fetched for "<query_parameter_name>","<query_parameter_value>"
    When the "<endpoint>" employee endpoint is invoked
    Then the response status code should be 404

    Examples:
      | query_parameter_name | query_parameter_value | endpoint                  |
      | first_name           | TEST5678              | GET_EMPLOYEE_BY_FIRSTNAME |
      | first_name           | ABCD2345              | GET_EMPLOYEE_BY_FIRSTNAME |
      | last_name            | TEST9123              | GET_EMPLOYEE_BY_LASTNAME  |
      | last_name            | Paul1234              | GET_EMPLOYEE_BY_LASTNAME  |