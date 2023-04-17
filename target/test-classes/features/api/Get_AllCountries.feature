@API


Feature: Used to create a new user

  Scenario: Success Response
    Given Api kullanicisi "api/profile/allCountries" path parametreleri set eder

    Then AllCountries  icin Get request gonderilir