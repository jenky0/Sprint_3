import io.restassured.response.ValidatableResponse;
import model.Courier;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class AuthCourier extends ScooterRestClient{

    @Step("Auth with login only")
    public ValidatableResponse postLoginOnly(String login) {
        Courier courier = new Courier(login);
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }

    @Step("Auth with login and password")
    public ValidatableResponse postFullAuthData(String login, String password) {
        Courier courier = new Courier(login, password);
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login")
                .then();
    }
}