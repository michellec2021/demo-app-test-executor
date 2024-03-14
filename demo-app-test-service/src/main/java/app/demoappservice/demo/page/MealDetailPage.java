package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.RESTAURANT_DETAIL_PAGE;

/**
 * @author michelle
 */
public class MealDetailPage extends BasePage {
    private static final FindBy TAKE_IT_BUTTON = FindBy.testId("take it button");

    @Step("click take it button")
    public void clickTakeItButton() {
        ActionFactory.waitForElementExist(TAKE_IT_BUTTON).click();
        ActionFactory.redirectTo(RESTAURANT_DETAIL_PAGE);
    }
}
