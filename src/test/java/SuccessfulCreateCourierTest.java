import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class SuccessfulCreateCourierTest {

    CreateCourier createCourier = new CreateCourier();
    DeleteCourier deleteCourier = new DeleteCourier();
    AuthCourier authCourier = new AuthCourier();

    String courierLogin = RandomStringUtils.randomAlphabetic(10);
    String courierPassword = RandomStringUtils.randomAlphabetic(10);
    String courierFirstName = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("Create courier with non-unique login")
    @Description("Unsuccessful create courier with non-unique login")
    public void createNewCourierWithNonUniqueLogin() {

        //создаем курьера
        ValidatableResponse create = createCourier.postFullData(courierLogin, courierPassword, courierFirstName);
        create.assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));

        //создание курьера с неуникальным логином
        ValidatableResponse auth = createCourier.postFullData(courierLogin, courierPassword, courierFirstName);
        auth.assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void cleanUp() {

        //логинимся курьером с целью узнать id
        ValidatableResponse auth = authCourier.postFullAuthData(courierLogin, courierPassword);
        auth.assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
        int courierId = auth.extract().body().path("id");

        //удаляем курьера
        ValidatableResponse delete = deleteCourier.deleteCourierByID(courierId);
        delete.assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}