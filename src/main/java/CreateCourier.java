import io.restassured.response.ValidatableResponse;
import model.Courier;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class CreateCourier extends ScooterRestClient {

    @Step("Create courier with empty data")
    public ValidatableResponse postEmptyData() {
        Courier courier = new Courier();
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Create courier with login only")
    public ValidatableResponse postLoginOnly(String login) {
        Courier courier = new Courier(login);
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Create courier without first name")
    public ValidatableResponse postDataWithoutFirstName(String login, String password) {
        Courier courier = new Courier(login, password);
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }

    @Step("Create courier with login, password, first name")
    public ValidatableResponse postFullData(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return given().spec(baseSpec())
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then();
    }
}