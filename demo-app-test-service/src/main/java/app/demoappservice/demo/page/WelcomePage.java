package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.AppiumFactory;
import com.wonder.appium.device.AbstractDevice;
import com.wonder.appium.device.AndroidDevice;
import com.wonder.appium.device.IOSDevice;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.LOGIN_PAGE;

/**
 * @author michelle
 */
public class WelcomePage extends BasePage {
    private static final FindBy LOGIN_BUTTON = FindBy.testId("login_button_on_welcome_page");

    @Step("click login button to login page")
    public void clickLoginButton() {
        ActionFactory.waitForElementDisplay(LOGIN_BUTTON).click();
        AbstractDevice device = AppiumFactory.instance().getDevice();
        if (device instanceof IOSDevice || device instanceof AndroidDevice && device.getDoubleOsVersion() >= 13) {
            ActionFactory.waitForAlertExist().accept();
        }
        ActionFactory.redirectTo(LOGIN_PAGE);
    }
}
