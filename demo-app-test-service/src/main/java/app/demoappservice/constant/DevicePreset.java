package app.demoappservice.constant;

import com.wonder.model.DeviceInfo;
import com.wonder.properties.DevicePresetReader;

/**
 * @author michelle
 */
public class DevicePreset {
    public static final DeviceInfo V_ANDROID12 = DevicePresetReader.android("emulator-5554");
    public static final DeviceInfo R_SAMSUNG_S21 = DevicePresetReader.android("R5CR11MVDDE");
    public static final DeviceInfo V_IOS12 = DevicePresetReader.ios("iPhone 12@15.0");
    public static final DeviceInfo V_IOS8 = DevicePresetReader.ios("iPhone 8@15.0");
    public static final DeviceInfo R_IPHONE11 = DevicePresetReader.ios("00008030-001078460AF8802E");
}
