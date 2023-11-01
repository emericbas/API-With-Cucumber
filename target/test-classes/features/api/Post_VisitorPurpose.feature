Feature: Bir yönetici olarak API baglantisi üzerinden yeni bir visitor purpose kaydi olusturabilmek istiyorum.

  Scenario: /api/visitorsPurposeAdd endpoint'ine gecerli authorization bilgileri ve
  dogru datalar (visitors_purpose, description) iceren bir POST body gönderildiginde
  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Success" oldugu dogrulanmali

    Given Api kullanicisi "api/visitorsPurposeAdd" path parametreleri set eder
    Then Api kullanicisi "deneme visitors" visitors_purpose ,"deneme description" description bilgileriyle yeni bir visitor purpose kaydi olusturur
    Then Api kullanicisi donen status kodunun 200 oldugunu dogrular
    Then Api kullanicisi donen response bodysindeki message degerinin "Success" oldugunu dogrular