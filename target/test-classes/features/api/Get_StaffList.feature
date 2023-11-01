Feature:  Bir yönetici olarak API baglantisi üzerinden OPD List'e erisebilmeliyim.

  Scenario:api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

    Given  Api kullanicisi "api/opdList" path parametreleri set eder
    Then  Api kullanici OPD List icin gonderdigi Get Request sonucunda donen status kodunun ikiyuz oldugunu dogrular