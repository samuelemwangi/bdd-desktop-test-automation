Feature: Calculator

  Scenario Outline: Addition
    Given I have opened a calculator
    When I add "<Num1>" and "<Num2>"
    And I click equals
    Then The result should be "<Sum>"

    Examples:
      | Num1 | Num2 | Sum |
      | 3    | 4    | 7   |
      | 4    | 4    | 8   |
      | 404  | 409  | 813 |
      | 010  | 010  | 20  |

  Scenario Outline: Subtraction
    Given I have opened a calculator
    When I subtract "<Num2>" from "<Num1>"
    And I click equals to subtract
    Then The minus result should be "<Diff>"

    Examples:
      | Num1 | Num2 | Diff |
      | 6    | 3    | 3    |