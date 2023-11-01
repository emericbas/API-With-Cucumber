package stepDefinitions.api;

import hooks.api.HooksAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import pojos.Pojo_RegisterCustomer;
import utilities.Authentication;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CommonAPI {
    Response response;
    int basariliStatusCode=200;
    int basarisizStatusCode=403;
  public static  String fullPath;
 Pojo_RegisterCustomer reqBody;
    JSONObject reqBodyJson;
    @Given("Api kullanicisi {string} path parametreleri set eder")
    public void api_kullanicisi_path_parametreleri_set_eder(String rawPaths) {
       //https://trendlifebuy.com/api/register
       // HooksAPI.spec.pathParams("pp1","api","pp2","register");
        //api/register
      String[] paths=  rawPaths.split("/");//["api","register"]

        StringBuilder tempPath=new StringBuilder("{");

        for (int i = 0; i <paths.length ; i++) {
            String key="pp"+i;//pp0 pp1 pp2....
            String value=paths[i].trim();//tedbiren bosluklari silip,sadece yaziyi getirecek

            HooksAPI.spec.pathParam(key,value);
            tempPath.append(key +"}/{");

        }
      //  System.out.println("tempPath = " + tempPath);
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));// en sondaki { silindi
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));// en sona gelen / silindi
      //  System.out.println("tempPath = " + tempPath);

         fullPath=tempPath.toString();//stringBuilder String'e donustu  /{pp0}/{pp1}/{pp2}




    }


    @Then("Register Customer icin gerekli Request Body {string},{string},{string},{string},{string},{string},{string},{string},{string} hazirla")
    public void registerCustomerIcinGerekliRequestBodyHazirla(String first_name, String last_name, String username, String email, String password, String password_confirmation, String user_type, String phone, String referral_code) {

        /*
        {
    "first_name": "sdad",
    "last_name": "sdsd",
    "username":"sdsadas",
    "email": "daasdasdss@gmail.com",
    "password": "As.123123",
    "password_confirmation": "As.123123",
    "user_type": "customer",
    "phone":"121123123",
    "referral_code": "44546545464546"
    }
         */


         reqBody=new Pojo_RegisterCustomer(first_name,last_name,username,email,password,password_confirmation,user_type,phone,referral_code);



    }


    @Then("Register Customer icin Post Request gonder")
    public void registerCustomerIcinPostRequestGonder() {

        Response response= given()
                .spec(HooksAPI.spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(reqBody)
                .post(fullPath);

        response.prettyPrint();

    }

    //------PostLogin-----------



    @Then("Login icin  {string} ve {string} hazirla")
    public void loginIcinVeHazirla(String email, String password) {


         reqBodyJson=new JSONObject();
        reqBodyJson.put("email", ConfigReader.getProperty(email));
        reqBodyJson.put("email", ConfigReader.getProperty(password));
    }

    @Then("Login icin Post request gonderilir")
    public void loginIcinPostRequestGonderilir() {

        Response response=given().spec(HooksAPI.spec).contentType(ContentType.JSON).header("Accept","application/json").when().
                body(reqBodyJson.toString()).post(fullPath);
        response.prettyPrint();
    }

    @Then("AllCountries  icin Get request gonderilir")
    public void allcountriesIcinGetRequestGonderilir() {
//Authorization
        Response response=given().spec(HooksAPI.spec).headers("Authorization","Bearer "+ HooksAPI.token)
                .contentType(ContentType.JSON).header("Accept","application/json").when().get(fullPath);
        response.prettyPrint();
    }


    //=================Proje Icin Stepler====================


    @Then("Api kullanici OPD List icin gonderdigi Get Request sonucunda donen status kodunun ikiyuz oldugunu dogrular")
    public void apiKullaniciOPDListIcinGonderdigiGetRequestSonucundaDonenStatusKodununIkiyuzOldugunuDogrular() {

        response = given()
                .spec(HooksAPI.spec)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);

        // response.prettyPrint();

        assertEquals(basariliStatusCode,response.getStatusCode());


    }

    @Then("Api kullanicisi {string} visitors_purpose ,{string} description bilgileriyle yeni bir visitor purpose kaydi olusturur")
    public void apiKullanicisiVisitors_purposeDescriptionBilgileriyleYeniBirVisitorPurposeKaydiOlusturur(String visitors_purpose, String description) {

        /*
        {
             "visitors_purpose":"deneme purpose",
              "description":"deneme description"
    `   }
         */

        reqBodyJson = new JSONObject();

        reqBodyJson.put("visitors_purpose",visitors_purpose);
        reqBodyJson.put("description",description);

        response = given()
                .spec(HooksAPI.spec)
                .headers("Authorization","Bearer "+HooksAPI.token)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBodyJson.toString())
                .post(fullPath);
        response.prettyPrint();



    }

    @Then("Api kullanicisi donen status kodunun {int} oldugunu dogrular")
    public void apiKullanicisiDonenStatusKodununOldugunuDogrular(int statusCode) {

        assertEquals(statusCode,response.getStatusCode());

    }

    @Then("Api kullanicisi donen response bodysindeki message degerinin {string} oldugunu dogrular")
    public void apiKullanicisiDonenResponseBodysindekiMessageDegerininOldugunuDogrular(String message) {

        JsonPath resJP = response.jsonPath();
        assertEquals(message,resJP.getString("message"));
    }
    @Then("Api kullanicisi id'si {int} olan kaydin visitors_purpose {string},description {string}, created_at {string} expected datasi hazirlanir")
    public void api_kullanicisi_id_si_olan_kaydin_visitors_purpose_description_created_at_expected_datasi_hazirlanir(Integer id, String visitors_purpose, String description, String created_at) {

        /*
     {
    "status": 200,
    "message": "Success",
    "Token_remaining_time": 1313,
    "lists": [
        {
            "id": "4",
            "visitors_purpose": "Visit",
            "description": "Visitor centers used to provide fairly basic information about the place, corporation or event they are celebrating, acting more as the entry way to a place. The role of the visitor center has been rapidly evolving over the past 10 years to become more of an experience and to tell the story of the place or brand it represents. Many have become destinations and experiences in their own right.",
            "created_at": "2021-10-29 01:25:09"
        },
        {
            "id": "19",
            "visitors_purpose": "feridun bey",
            "description": "bayram 123 111",
            "created_at": "2023-04-12 08:34:56"
        }
                ]
    }
         */

    }
    @Then("Api kullanicisi visitors Purpose List gormek icin Get request gonderir")
    public void api_kullanicisi_visitors_purpose_list_gormek_icin_get_request_gonderir() {

        response =  given()
                .spec(HooksAPI.spec)
                .headers("Authorization", "Bearer " + HooksAPI.token)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @Then("Api kullanicisi visitorsPurposeList donen response body icindeki id'si {string} olan kaydin visitors_purpose {string},description {string}, created_at {string} oldugunu dogrular")
    public void apiKullanicisiVisitorsPurposeListDonenResponseBodyIcindekiIdSiOlanKaydinVisitors_purposeDescriptionCreated_atOldugunuDogrular(String arg0, String arg1, String arg2, String arg3) {
        JsonPath resJp = response.jsonPath();
        assertEquals(arg0, resJp.get("lists[6].id") );
        assertEquals(arg1, resJp.get("lists[6].visitors_purpose") );
        assertEquals(arg2, resJp.get("lists[6].description") );
        // assertEquals(arg3, resJp.get("lists[6].created_at") );
    }

}
