package app.demoappservice.demo.usecase;

import app.demoappservice.demo.api.AddressAPI;
import app.demoappservice.demo.api.CartAPI;
import app.demoappservice.demo.api.PaymentAPI;
import app.demoappservice.demo.api.SessionAPI;
import app.demoappservice.demo.api.UserAPI;
import app.demoappservice.demo.model.Address;
import app.demoappservice.demo.model.Payment;
import app.demoappservice.demo.model.Restaurant;
import app.demoappservice.demo.model.UserInfo;
import com.wonder.allure.Allure;
import com.wonder.appium.ActionFactory;

import static app.demoappservice.demo.page.PageFactory.ACCOUNT_PAGE;
import static app.demoappservice.demo.page.PageFactory.ACCOUNT_PAYMENT_LIST_PAGE;
import static app.demoappservice.demo.page.PageFactory.ADD_PAYMENT_PAGE;
import static app.demoappservice.demo.page.PageFactory.EMAIL_LOGIN_PAGE;
import static app.demoappservice.demo.page.PageFactory.HOME_PAGE;
import static app.demoappservice.demo.page.PageFactory.LOGIN_PAGE;
import static app.demoappservice.demo.page.PageFactory.MEAL_DETAIL_PAGE;
import static app.demoappservice.demo.page.PageFactory.RESTAURANT_DETAIL_PAGE;
import static app.demoappservice.demo.page.PageFactory.RESTAURANT_LIST_PAGE;
import static app.demoappservice.demo.page.PageFactory.WELCOME_PAGE;

/**
 * @author michelle
 */
public class DemoCaseImpl implements DemoCase {
    String email;

    @Override
    public void beforeGroup() {
        Allure.step("API create a user", () -> {
            UserInfo userInfo = UserAPI.createUser();
            AddressAPI.addAddress(Address.DEFAULT_ADDRESS);
            PaymentAPI.addPayment(Payment.DEFAULT, true);
            email = userInfo.email;
        });
        Allure.step("User login app", () -> {
            WELCOME_PAGE.clickLoginButton();
            LOGIN_PAGE.clickLoginWithEmail();
            EMAIL_LOGIN_PAGE.loginWithEmail(email, "pwd11111");
            ActionFactory.takeScreenshot("home_page");
        });
    }

    @Override
    public void beforeTest() {
        ActionFactory.terminalApp();
        ActionFactory.launchApp();
        ActionFactory.redirectTo(HOME_PAGE);
    }

    @Override
    public void checkRestaurantInfoAddItemToCart() {
        HOME_PAGE.clickToRLP();
        RESTAURANT_LIST_PAGE.clickRestaurantToRDP(Restaurant.BAR_NAKAZAWA);
        ActionFactory.assertValueIs(RESTAURANT_DETAIL_PAGE.getBrandName(), Restaurant.BAR_NAKAZAWA.nickName);
        RESTAURANT_DETAIL_PAGE.swipeToDiningOptionBanner();
        ActionFactory.assertTextExist("DELIVERY");
        ActionFactory.assertTextExist("PICKUP");
        RESTAURANT_DETAIL_PAGE.clickMeal("Tuna & Yellowtail Roll");
        MEAL_DETAIL_PAGE.clickTakeItButton();
        ActionFactory.assertElementExist(RESTAURANT_DETAIL_PAGE.getViewOrderButton());
        ActionFactory.assertTextExist("$10.00");
        ActionFactory.takeScreenshot("add_item_to_cart");
    }

    @Override
    public void checkAddPaymentFunction() {
        HOME_PAGE.clickToAccountPage();
        ACCOUNT_PAGE.clickPayment();
        ACCOUNT_PAYMENT_LIST_PAGE.clickAddPaymentButton();
        ADD_PAYMENT_PAGE.addPayment(Payment.MASTERCARD);
        ActionFactory.waitForTextExist(Payment.MASTERCARD.paymentAppearanceName);
        ActionFactory.takeScreenshot("add_new_payment");
    }

    @Override
    public void afterGroup() {

    }

    @Override
    public void afterTest() {
        SessionAPI.initSession();
        UserAPI.login(email);
        CartAPI.emptyCart();
    }
}
