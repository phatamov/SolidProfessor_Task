Feature: As a user, I should be able to login using login page.


  Background: Common steps for all scenarios
    Given The user is on the homepage


  @ui
  Scenario: Login using provided performance_glitch_user valid credentials
    When The user enters provided performance_glitch_user credentials
    Then The user should be able to login