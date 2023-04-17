package utilities;

import hooks.api.HooksAPI;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;
    public static String generateToken(){
        spec=new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1","api","pp2","login");
        Map<String,Object> dataCredential=new HashMap<>();
        dataCredential.put("email",ConfigReader.getProperty("email"));
        dataCredential.put("password",ConfigReader.getProperty("password"));

        Response response=given().spec(spec).contentType(ContentType.JSON).header("Accept","application/json").when().
                body(dataCredential).post("/{pp1}/{pp2}");

        JsonPath respJP=response.jsonPath();// icindeki degeri ilmak icin  JsonPath'e donusturduk
      //  System.out.println("Benim token : "+ respJP.getString("token"));
String token=respJP.getString("token");
        return token;
    }



}
