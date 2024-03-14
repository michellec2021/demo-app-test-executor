package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.ACCOUNT_PAGE;
import static app.demoappservice.demo.page.PageFactory.RESTAURANT_LIST_PAGE;

/**
 * @author michelle
 */
public class HomePage extends BasePage {
    private static final FindBy ACCOUNT_TAB = FindBy.testId("account_on_home_page");
    private static final FindBy ORDER_TAB = FindBy.testId("order_on_home_page");

    @Step("click order tab to RLP")
    public void clickToRLP() {
        ActionFactory.waitForElementDisplay(ORDER_TAB).click();
        ActionFactory.redirectTo(RESTAURANT_LIST_PAGE);
    }

    @Step("click account tab to account page")
    public void clickToAccountPage() {
        ActionFactory.click(ACCOUNT_TAB);
        ActionFactory.redirectTo(ACCOUNT_PAGE);
    }
}
