package app.demoappservice.demo.api;

import app.demoappservice.demo.model.Address;
import com.wonder.http.HTTPClients;
import com.wonder.util.MapUtils;
import io.qameta.allure.Step;

/**
 * @author michelle
 */
public class AddressAPI {
    @Step("api add address {0}")
    public static void addAddress(Address address) {
        HTTPClients.WONDER.postRequest("/v5/address").withData(MapUtils.mapOf(
            "delivery_instructions", "autotest",
            "latitude", address.latitude,
            "longitude", address.longitude,
            "address_name", address.addressLongerName,
            "address_short_name", address.addressShortName,
            "drop_off_type", "LEAVE_AT_DOOR",
            "state", address.state,
            "city", address.city,
            "county", address.county,
            "zip_code", address.zipCode,
            "delivery_instructions", "autotest",
            "delivery_option", "LEAVE_AT_DOOR",
            "street_number", "530"
        )).send().assertSuccess();
    }
}
