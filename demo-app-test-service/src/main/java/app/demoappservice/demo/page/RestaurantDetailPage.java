package app.demoappservice.demo.page;

import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.MEAL_DETAIL_PAGE;

/**
 * @author michelle
 */
public class RestaurantDetailPage extends BasePage {
    private static final FindBy BRAND_NAME = FindBy.testId("restaurant_name_on_rdp");
    private static final FindBy DINING_OPTION_BANNER = FindBy.testId("dining_option_banner_on_restaurant_detail_page");
    private static final FindBy VIEW_ORDER_BUTTON = FindBy.testId("view_order_button_on_restaurant_detail_page");

    public FindBy getBrandName() {
        return BRAND_NAME;
    }

    public FindBy getViewOrderButton() {
        return VIEW_ORDER_BUTTON;
    }

    public FindBy getMealFindBy(String mealName) {
        return FindBy.testId(mealName + " meal");
    }

    @Step("swipe to dining option banner")
    public void swipeToDiningOptionBanner() {
        ActionFactory.swipeToMiddleOfElement(DINING_OPTION_BANNER);
    }

    @Step("click meal {0} on RDP")
    public void clickMeal(String mealName) {
        ActionFactory.swipeToMiddleOfElement(getMealFindBy(mealName), 15000);
        ActionFactory.click(getMealFindBy(mealName));
        ActionFactory.redirectTo(MEAL_DETAIL_PAGE);
    }
}
