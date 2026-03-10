package RestAssuredP001;

import Files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    @Test
    public void basics() {

        // Validating AddPlace API
        // given: All the input details
        // when: Submit the API
        // then: Validate the response
        //Add Place -> Update Place with New Address -> Get Place to validate if the New Address is present in the response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payLoad.AddPlace())
                .when().post("maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .body("status", equalTo("OK"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        // To parse the response
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);

    }
}
