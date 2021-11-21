Feature: As a user, I should be able to login using login page.


  Background: Common steps for all scenarios
    Given The user is on the homepage


  @ui
  Scenario: Login using valid credentials
    When The user enters the valid credentials
    Then The user should be able to login and land on the homepage

  @ui
  Scenario Outline: Login using invalid credentials through examples

    When The user enters the invalid credentials as "<username>" for username and "<password>" for password
    Then The user should not be able to login and land on the homepage

    Examples: invalid username and password list
      | username        | password        | comments                              |
      | standard_user   | invalidPassword | Valid Username and Invalid Password   |
      |                 |                 | Empty Space For Username and Password |
      | invalidUsername | invalidPassword | Invalid Username and Invalid Password |
