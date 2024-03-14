package app.demoappservice.demo.api;

import app.demoappservice.demo.model.UserInfo;
import com.wonder.http.HTTPClients;
import com.wonder.util.RandomUtils;
import io.qameta.allure.Step;

import java.util.Map;

import static com.wonder.util.MapUtils.mapOf;

/**
 * @author michelle
 */
public class UserAPI {
    @Step("api create random user")
    public static UserInfo createUser() {
        String email = RandomUtils.randomRegisterEmail();
        SessionAPI.initSession();
        String firstName = email.split("@")[0];
        String phoneNumber = RandomUtils.randomPhoneNumber();
        HTTPClients.WONDER.postRequest("/user/sms-verification-code").withData(Map.of("phone", phoneNumber)).send().assertSuccess();
        return HTTPClients.WONDER.postRequest("/v3/user/register").withData(mapOf(
            "email", email,
            "password", "pwd11111",
            "full_name", firstName + " cc",
            "first_name", firstName,
            "last_name", "cc",
            "phone", phoneNumber,
            "code", "123456",
            "type", "NORMAL"
        )).send().assertSuccess().getBean(UserInfo.class);
    }

    @Step("api login user {0}")
    public static void login(String email) {
        HTTPClients.WONDER.putRequest("/v4/user/login").withData(Map.of("email", email,
            "password", "pwd11111",
            "type", "NORMAL"
        )).send().assertSuccess();
    }
}
