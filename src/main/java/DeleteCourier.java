import io.restassured.response.ValidatableResponse;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class DeleteCourier extends ScooterRestClient {

    @Step("Delete courier")
    public ValidatableResponse deleteCourierByID(int id) {
        return given().spec(baseSpec())
                .and()
                .when()
                .delete("/api/v1/courier/" + id)
                .then();
    }
}
