package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.ACCOUNT_PAYMENT_LIST_PAGE;

/**
 * @author michelle
 */
public class AccountPage extends BasePage {
    private static final FindBy PAYMENT = FindBy.testId("Payment");

    @Step("click payment link")
    public void clickPayment() {
        ActionFactory.click(PAYMENT);
        ActionFactory.redirectTo(ACCOUNT_PAYMENT_LIST_PAGE);
    }
}