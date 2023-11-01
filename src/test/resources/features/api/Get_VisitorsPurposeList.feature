Feature:

  Scenario: Response body icindeki list icerigi (id:"19" olan icerigin visitors_purpose:"feridun bey", description:"bayram 123 111", created_at: "2023-01-20 06:49:12" oldugu) dogrulanmali.

    Given Api kullanicisi "api/visitorsPurposeList" path parametreleri set eder
    Then Api kullanicisi id'si 19 olan kaydin visitors_purpose "feridun bey",description "bayram 123 111", created_at "2023-01-20 06:49:12" expected datasi hazirlanir
    Then Api kullanicisi visitors Purpose List gormek icin Get request gonderir
    Then Api kullanicisi visitorsPurposeList donen response body icindeki id'si "19" olan kaydin visitors_purpose "feridun bey",description "bayram 123 111", created_at "2023-01-20 06:49:12" oldugunu dogrular