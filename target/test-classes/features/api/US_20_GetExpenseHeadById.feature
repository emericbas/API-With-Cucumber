

@API
Feature: US_20 Bir yönetici olarak API baglantisi üzerinden id girerek ilgili harcama verilerine erisebilmeliyim.

  Scenario Outline: TC_01 /api/getExpenseHeadById endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir GET body gönderildigindedönen status code'in 200 oldugu veresponse body'deki message bilgisinin "Success" oldugu dogrulanmaliGecersiz authorization bilgileri ve gecersiz data (id) iceren bir GET body gönderildigindedönen status code'in 403 oldugu ve response body'deki message bilgisinin "failed" oldugu dogrulanmali
    Given  Api kullanicisi "api/getExpenseHeadById" path parametreleri set eder
    Then  Api kullanici gecerli authorization bilgileri "<email>", "<password>" ve dogru data "<id>"gonderdigi Get Request sonucunda donen status kodunun ikiyuz oldugunu dogrular
    Then Api kullanici gecersiz authorization bilgileri "<email>", "<password>" ve yanlis data "<id>"gonderdigi Get Request sonucunda donen status kodunun dort yuz uc oldugunu dogrular

    Examples:|  email  | |password| |   id   |

            | emine.bas.admin@heallifehospital.com ||heallife123  |   |20     |




    Scenario: TC_02 Response body icindeki datalar (id, exp_category, description, is_active, is_deleted, created_at) dogrulanmali.