package app.demoappservice.demo.model;

import com.wonder.exception.RequiredException;
import com.wonder.util.ClassUtils;
import core.framework.api.json.Property;
import core.framework.json.JSON;
import core.framework.util.ClasspathResources;
import core.framework.util.Types;

import java.util.List;
import java.util.Optional;

/**
 * @author michelle
 */
public class Restaurant {
    public static final Restaurant TABLENO1 = new Restaurant("Table No. 1");
    public static final Restaurant MAYDAN = new Restaurant("Maydan");
    public static final Restaurant BAR_NAKAZAWA = new Restaurant("Bar Nakazawa");
    public static final Restaurant BARRIO_DELIVERY_ONLY = new Restaurant("Barrio Café");
    public static final Restaurant JBIRD_PICKUP_ONLY = new Restaurant("JBird by Jonathan Waxman");
    public static final Restaurant BOBBY_FLAY_STEAK_PAUSED = new Restaurant("Bobby Flay Steak");
    public static final Restaurant LIMESALT_OPENING_SOON = new Restaurant("Limesalt");
    public static final Restaurant TEJAS_BARBECUE_CLOSED = new Restaurant("Tejas Barbecue");
    public static final Restaurant CHUKO_COMING_SOON = new Restaurant("Chuko");
    public static final Restaurant CHAI_PANI = new Restaurant("Chai Pani");
    public static final Restaurant ALANZA_IN_STORE_ONLY = new Restaurant("Alanza");
    public static final Restaurant INVENTORY_TEST = new Restaurant("App Automation Inventory Test");
    public static final Restaurant CHIOS_TAVERNA = new Restaurant("Chios Taverna by Michael Symon");

    private static void initRestaurant(Restaurant restaurant, List<Restaurant> restaurants) {
        Optional<Restaurant> optionalRestaurant = restaurants.stream()
            .filter(restaurantInfo -> restaurant.nickName.equals(restaurantInfo.nickName)).findAny();
        if (optionalRestaurant.isPresent()) {
            Restaurant matchedRestaurant = optionalRestaurant.get();
            restaurant.name = matchedRestaurant.name;
            restaurant.cuisineStyle = matchedRestaurant.cuisineStyle;
            restaurant.priceRate = matchedRestaurant.priceRate;
            restaurant.shortDescription = matchedRestaurant.shortDescription;
            restaurant.hasCarouselImage = matchedRestaurant.hasCarouselImage;
        } else {
            throw new RequiredException("init restaurant failed, restaurant =" + restaurant.nickName);
        }
    }

    @Property(name = "name")
    public String name;
    @Property(name = "nick_name")
    public String nickName;
    @Property(name = "cuisine_style")
    public String cuisineStyle;
    @Property(name = "price_rate")
    public String priceRate;
    @Property(name = "short_description")
    public String shortDescription;
    @Property(name = "has_carousel_image")
    public boolean hasCarouselImage;

    public Restaurant() {
    }

    public Restaurant(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return nickName;
    }

    static {
        Object jsonObject = JSON.fromJSON(Types.list(Restaurant.class), ClasspathResources.text("restaurant.json"));
        List<Restaurant> restaurants = ClassUtils.convertToList(jsonObject, Restaurant.class);

        initRestaurant(TABLENO1, restaurants);
        initRestaurant(MAYDAN, restaurants);
        initRestaurant(BAR_NAKAZAWA, restaurants);
        initRestaurant(BARRIO_DELIVERY_ONLY, restaurants);
        initRestaurant(JBIRD_PICKUP_ONLY, restaurants);
        initRestaurant(BOBBY_FLAY_STEAK_PAUSED, restaurants);
        initRestaurant(LIMESALT_OPENING_SOON, restaurants);
        initRestaurant(TEJAS_BARBECUE_CLOSED, restaurants);
        initRestaurant(CHUKO_COMING_SOON, restaurants);
        initRestaurant(ALANZA_IN_STORE_ONLY, restaurants);
        initRestaurant(CHAI_PANI, restaurants);
        initRestaurant(INVENTORY_TEST, restaurants);
        initRestaurant(CHIOS_TAVERNA, restaurants);
    }
}
