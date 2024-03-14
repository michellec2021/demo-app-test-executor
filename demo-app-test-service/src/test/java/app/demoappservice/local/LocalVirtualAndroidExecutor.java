package app.demoappservice.local;

import app.demoappservice.constant.DevicePreset;
import app.demoappservice.demo.usecase.DemoCase;
import com.wonder.appium.AppiumFactory;
import com.wonder.properties.PropertiesLoader;
import com.wonder.runner.AppTestEngine;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.demoappservice.demo.page.PageFactory.WELCOME_PAGE;

/**
 * @author michelle
 */
public class LocalVirtualAndroidExecutor {
    @Test
    public void execute() {
        PropertiesLoader.instance().loadProperties("app.properties").loadProperties("sys.properties");
        AppiumFactory.instance()
            .appPath("/Users/mixuanchen/Downloads/WONDER_QA_1709803125.apk")
            .connectAppium(DevicePreset.V_ANDROID12);
        Map<Class<?>, List<String>> caseMap = new HashMap<>();
        caseMap.put(DemoCase.class, List.of("checkRestaurantInfoAddItemToCart", "checkAddPaymentFunction"));
        AppTestEngine customizedRunner = new AppTestEngine(WELCOME_PAGE);
        customizedRunner.runCases(caseMap);
        AppiumFactory.instance().disconnect();
    }
}
