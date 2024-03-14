package app.demoappservice.demo.api;

import com.wonder.http.APIResponse;
import com.wonder.http.HTTPClients;
import com.wonder.http.WonderHttpClient;
import io.qameta.allure.Step;

import java.util.Map;

import static com.wonder.util.MapUtils.mapOf;

/**
 * @author michelle
 */
public class SessionAPI {
    @Step("api init session")
    public static void initSession() {
        APIResponse sessionResponse = HTTPClients.WONDER.postRequest("/v2/session").withData(mapOf(
            "version", "3.15.0",
            "device", Map.of(
                "device_unique_id", "bf406a019ed80000",
                "manufacturer", "app tester",
                "model", "app tester",
                "os_version", "app tester"))).send().assertSuccess();
        WonderHttpClient.setDeviceId(sessionResponse.getString("device_id"));
        WonderHttpClient.setSessionToken(sessionResponse.getString("session_token"));
    }
}
