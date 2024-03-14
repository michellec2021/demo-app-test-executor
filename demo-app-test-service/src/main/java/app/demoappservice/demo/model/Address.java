package app.demoappservice.demo.model;

import com.wonder.exception.RequiredException;
import com.wonder.util.ClassUtils;
import core.framework.api.json.Property;
import core.framework.json.JSON;
import core.framework.util.ClasspathResources;
import core.framework.util.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Michelle
 */
public class Address {
    private static final List<Address> ADDRESSES = new ArrayList<>();

    static {
        Object address = JSON.fromJSON(Types.list(Address.class), ClasspathResources.text("address.json"));
        ADDRESSES.addAll(ClassUtils.convertToList(address, Address.class));
    }

    public static final Address DEFAULT_ADDRESS = getAddress("Default Address");
    public static final Address HDR_ADDRESS = getAddress("Hdr Address");
    public static final Address REMOTE_ADDRESS = getAddress("Remote Address");
    public static final Address STATUS_TEST_ADDRESS = getAddress("Status Test Address");
    public static final Address ALTER_HDR_DELIVERY_ADDRESS = getAddress("Alter Hdr Delivery Address");
    public static final Address NO_OPEN_RESTAURANT_ADDRESS = getAddress("No Open Restaurant Address");
    public static final Address INVENTORY_TEST_ADDRESS = getAddress("Inventory Test Address");
    public static final Address ONLY_OPENING_SOON_RES_ADDRESS = getAddress("Only Opening Soon Res Address");


    public static Address getAddress(String name) {
        Optional<Address> addresses = ADDRESSES.stream()
            .filter(address -> address.name.equals(name)).findAny();
        if (addresses.isPresent()) {
            return addresses.get();
        } else {
            throw new RequiredException("init address failed, address =" + name);
        }
    }

    @Property(name = "name")
    public String name;
    @Property(name = "address-short-name")
    public String addressShortName;
    @Property(name = "address-longer-name")
    public String addressLongerName;
    @Property(name = "address-full-Name")
    public String addressFullName;
    @Property(name = "search-address-key")
    public String searchAddressKey;
    @Property(name = "latitude")
    public String latitude;
    @Property(name = "longitude")
    public String longitude;
    @Property(name = "city")
    public String city;
    @Property(name = "state")
    public String state;
    @Property(name = "county")
    public String county;
    @Property(name = "zip-code")
    public String zipCode;

    @Override
    public String toString() {
        return "[" + addressShortName + ']';
    }
}
