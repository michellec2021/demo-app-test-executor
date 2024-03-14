package app.demoappservice.demo.api;

import com.wonder.http.HTTPClients;
import io.qameta.allure.Step;

/**
 * @author michelle
 */
public class CartAPI {
    @Step("api empty shop cart")
    public static void emptyCart() {
        HTTPClients.WONDER.deleteRequest("/v2/cart").send().assertSuccess();
    }
}
