package app.demoappservice.demo.page;

import app.demoappservice.demo.model.Restaurant;
import com.wonder.appium.ActionFactory;
import com.wonder.appium.element.FindBy;
import com.wonder.page.BasePage;
import io.qameta.allure.Step;

import static app.demoappservice.demo.page.PageFactory.RESTAURANT_DETAIL_PAGE;

/**
 * @author michelle
 */
public class RestaurantListPage extends BasePage {
    private FindBy getRestaurantFindBy(String restaurantName) {
        return FindBy.testId(restaurantName + " restaurant");
    }

    @Step("swipe and click restaurant {0} to RDP")
    public void clickRestaurantToRDP(Restaurant restaurant) {
        ActionFactory.swipeToMiddleOfElement(getRestaurantFindBy(restaurant.nickName), 15000);
        ActionFactory.click(getRestaurantFindBy(restaurant.nickName));
        ActionFactory.redirectTo(RESTAURANT_DETAIL_PAGE);
    }
}