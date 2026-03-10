package RestAssuredP001;

import Files.payLoad;
import io.restassured.RestAssured;
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
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payLoad.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .body("status", equalTo("OK"))
                .header("Server", "Apache/2.4.52 (Ubuntu)");
        //Add Place -> Update Place with New Address -> Get Place to validate if the New Address is present in the response

    }
}
