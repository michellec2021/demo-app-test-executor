package app.demoappservice.demo.api;

import app.demoappservice.demo.model.Payment;
import com.wonder.http.HTTPClients;
import io.qameta.allure.Step;

import static com.wonder.util.MapUtils.mapOf;

/**
 * @author michelle
 */
public class PaymentAPI {
    @Step("api add payment")
    public static void addPayment(Payment payment, boolean isDefault) {
        String stripeId = HTTPClients.STRIPE.postRequest("/v1/payment_methods").withFormData(mapOf(
            "billing_details[address][postal_code]", Payment.ZIP_CODE,
            "type", "card",
            "card[exp_year]", Payment.EXPIRED_YEAR,
            "card[number]", payment.creditCardNumber,
            "card[cvc]", Payment.CVC,
            "card[exp_month]", Payment.EXPIRED_MONTH
        )).send().assertSuccess().getString("id");

        HTTPClients.WONDER.postRequest("/v2/credit-card").withData(mapOf(
            "stripe_payment_method_id", stripeId,
            "is_default", isDefault
        )).send().assertSuccess();
    }
}
