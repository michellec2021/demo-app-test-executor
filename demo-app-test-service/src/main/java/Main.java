import app.demoappservice.DemoServiceApp;
import com.wonder.model.StartUpInfo;

/**
 * @author michelle
 */
public class Main {
    public static void main(String[] args) {
        StartUpInfo startUpInfo = StartUpInfo.interpretArgs(args);
        new DemoServiceApp(startUpInfo).start();
    }
}