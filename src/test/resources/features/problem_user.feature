Feature: As a user, I should be able to login using login page.


  Background: Common steps for all scenarios
    Given The user is on the homepage


  @ui
  Scenario: Login using provided problem_user credentials
    When The user enters provided problem_user credentials
    Then The user should be able to login
    But Images are not loading for this user
