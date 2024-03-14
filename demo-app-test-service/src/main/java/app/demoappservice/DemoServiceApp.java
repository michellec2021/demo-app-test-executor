package app.demoappservice;

import com.wonder.model.StartUpInfo;
import com.wonder.properties.PropertiesLoader;
import com.wonder.runner.ExecutorService;
import core.framework.module.App;

import static app.demoappservice.demo.page.PageFactory.WELCOME_PAGE;

/**
 * @author michelle
 */
public class DemoServiceApp extends App {
    private final StartUpInfo startUpInfo;

    public DemoServiceApp(StartUpInfo startUpInfo) {
        this.startUpInfo = startUpInfo;
    }

    @Override
    protected void initialize() {
        http().httpPort(startUpInfo.httpPort);
        PropertiesLoader.instance().loadProperties("app.properties").loadProperties("sys.properties");
        ExecutorService executorService = new ExecutorService(startUpInfo);
        context.startupHook.add(() -> executorService.startExecutor(WELCOME_PAGE));
    }
}
