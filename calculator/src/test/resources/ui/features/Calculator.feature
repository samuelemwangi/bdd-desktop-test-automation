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
      | 40   | 44   | 84  |
      | 400  | 424  | 824 |