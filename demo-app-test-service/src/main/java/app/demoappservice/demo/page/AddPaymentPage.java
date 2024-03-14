package app.demoappservice.demo.page;

import app.demoappservice.demo.model.Payment;
import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.ACCOUNT_PAYMENT_LIST_PAGE;

/**
 * @author michelle
 */
public class AddPaymentPage extends BasePage {
    public static final FindBy CVC_INPUT = FindBy.testId("cvc input");
    public static final FindBy CARD_INPUT = FindBy.testId("card input");
    public static final FindBy ZIP_CODE_INPUT = FindBy.testId("zip code input");
    public static final FindBy EXPIRATION_DATE_INPUT = FindBy.testId("expiration date input");
    public static final FindBy CONFIRM = FindBy.testId("confirm_on_add_payment_page");

    @Step("add payment {0}")
    public void addPayment(Payment payment) {
        ActionFactory.sendKeys(CARD_INPUT, payment.creditCardNumber);
        ActionFactory.sendKeys(EXPIRATION_DATE_INPUT, Payment.EXPIRED_DATE);
        ActionFactory.sendKeys(CVC_INPUT, Payment.CVC);
        ActionFactory.sendKeys(ZIP_CODE_INPUT, Payment.ZIP_CODE);
        ActionFactory.click(CONFIRM);
        ActionFactory.redirectTo(ACCOUNT_PAYMENT_LIST_PAGE);
    }
}
