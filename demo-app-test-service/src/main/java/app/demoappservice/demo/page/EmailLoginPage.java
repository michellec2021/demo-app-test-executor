package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.HOME_PAGE;

/**
 * @author michelle
 */
public class EmailLoginPage extends BasePage {
    private static final FindBy EMAIL_INPUT = FindBy.testId("email_input_on_email_login_page");
    private static final FindBy PASSWORD_INPUT = FindBy.testId("password_input_on_email_login_page");
    private static final FindBy LOGIN_BUTTON = FindBy.testId("login_button_on_email_login_page");

    @Step("login with email {0}")
    public void loginWithEmail(String email, String password) {
        ActionFactory.sendKeys(EMAIL_INPUT, email);
        ActionFactory.sendKeys(PASSWORD_INPUT, password);
        ActionFactory.click(LOGIN_BUTTON);
        ActionFactory.redirectTo(HOME_PAGE);
    }
}
