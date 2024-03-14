package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.ADD_PAYMENT_PAGE;

/**
 * @author michelle
 */
public class AccountPaymentListPage extends BasePage {
    private static final FindBy ADD_PAYMENT_BUTTON = FindBy.testId("add_payment_on_account_payment_list_page");

    @Step("click add payment button")
    public void clickAddPaymentButton() {
        ActionFactory.click(ADD_PAYMENT_BUTTON);
        ActionFactory.redirectTo(ADD_PAYMENT_PAGE);
    }
}
