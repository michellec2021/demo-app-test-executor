package app.demoappservice.demo.usecase;

import com.wonder.annotation.AfterGroup;
import com.wonder.annotation.AfterTest;
import com.wonder.annotation.BeforeGroup;
import com.wonder.annotation.BeforeTest;
import com.wonder.annotation.CaseNos;
import com.wonder.annotation.Epic;
import com.wonder.annotation.Feature;
import com.wonder.annotation.Priority;
import com.wonder.annotation.PriorityEnum;
import com.wonder.annotation.Stories;
import com.wonder.annotation.Type;
import com.wonder.annotation.TypeEnum;

/**
 * @author michelle
 */
public interface DemoCase {
    @BeforeGroup
    void beforeGroup();

    @BeforeTest
    void beforeTest();

    @CaseNos("RDP-001")
    @Epic("RDP")
    @Feature("Restaurant Info")
    @Stories("Check restaurant info and add item to cart")
    @Priority(PriorityEnum.HIGH)
    @Type(TypeEnum.UI)
    void checkRestaurantInfoAddItemToCart();

    @CaseNos("Account-001")
    @Epic("Account")
    @Feature("Payment")
    @Stories("Check add payment function")
    @Priority(PriorityEnum.HIGH)
    @Type(TypeEnum.UI)
    void checkAddPaymentFunction();

    @AfterGroup
    void afterGroup();

    @AfterTest
    void afterTest();
}
