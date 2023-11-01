Feature: US_26 Bir yönetici olarak API baglantisi üzerinden yeni bir duyuru kaydi olusturabilmek istiyorum.
  Scenario: TC_01 /api/addNotice endpoint'ine gecerli authorization bilgileri ve
  dogru datalar (type, title, description, slug) iceren bir POST body gönderildiginde
  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Success" oldugu dogrulanmali





    Scenario Outline: : TC_02 /api/addNotice endpoint'ine gecersiz authorization bilgileri veya
    yanlis datalar (type, title, description, slug) iceren bir POST body gönderildiginde
    dönen status code'in 403 oldugu ve response body'deki message bilgisinin "failed" oldugu dogrulanmali

      Examples:



      Scenario: TC_03 API uzerinden olusturulmak istenen yeni duyuru kaydinin olustugu, API uzerinden dogrulanmali.