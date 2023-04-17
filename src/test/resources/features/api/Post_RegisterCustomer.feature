
  Feature: Used to create a new user

    Scenario: Success Response
      Given Api kullanicisi "api/register" path parametreleri set eder
      Then Register Customer icin gerekli Request Body "Emine","Bas","eminem","eb@gmail.com","1234.abce","1234.abce","customer","05367777777","44546545464546" hazirla
      Then Register Customer icin Post Request gonder