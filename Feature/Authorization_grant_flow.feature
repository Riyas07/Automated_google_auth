@Authorization_grant_flow
Feature: feature to create Google 2.0 Authorization Grant flow
  Scenario: resource owner to connect google mail account using client Application
    Given generate Access and refresh token by user Access code
    When get the user profile
    Then send the email to random person
    Then list out all the messages send bu user
    Then sort the message send to riyasmuhammed182 mail id message
    Then delete the message
