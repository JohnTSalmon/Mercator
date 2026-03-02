Feature: Test Platform Login With Playwright

  Background:
    Given I open a browser

  Scenario: Login
    Given I open the URL :
      | https://www.saucedemo.com/ |
    And I login with the following credentials
      | user          | password     |
      | standard_user | secret_sauce |
    When I select the lowest price item
    And I complete the purchase with the following details:
      | firstName | lastName | postCode |
      | Johan     | Bach     | LS1 123    |
    Then I should see the following confirmation message:
      | Thank you for your order! |




