package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.EMAIL_LOGIN_PAGE;

/**
 * @author michelle
 */
public class LoginPage extends BasePage {
    private static final FindBy LOGIN_WITH_EMAIL = FindBy.testId("login_with_email_on_login_page");

    @Step("click login with email")
    public void clickLoginWithEmail() {
        ActionFactory.click(LOGIN_WITH_EMAIL);
        ActionFactory.redirectTo(EMAIL_LOGIN_PAGE);
    }
}
