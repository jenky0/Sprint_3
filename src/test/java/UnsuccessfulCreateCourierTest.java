import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class UnsuccessfulCreateCourierTest {

    CreateCourier createCourier = new CreateCourier();
    String courierLogin = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("Courier create without all required data password")
    @Description("Courier create without password and first name")
    public void createCourierWithLoginOnly() {

        ValidatableResponse response = createCourier.postLoginOnly(courierLogin);
        response.assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
