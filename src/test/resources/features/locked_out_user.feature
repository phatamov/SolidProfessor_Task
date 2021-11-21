Feature: As a user, I should be able to login using login page.


  Background: Common steps for all scenarios
    Given The user is on the homepage


  @ui
  Scenario: Login using provided locked_out_user credentials
    When The user enters provided locked_out_user credentials
    Then The user should not be able to login and get error message
