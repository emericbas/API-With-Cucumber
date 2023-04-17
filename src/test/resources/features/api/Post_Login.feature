

Feature: Used to create a new user

  Scenario: Success Response
    Given Api kullanicisi "api/login" path parametreleri set eder
    Then Login icin  "email" ve "password" hazirla
    Then Login icin Post request gonderilir