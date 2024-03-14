package app.demoappservice.demo.model;

/**
 * @author Michelle
 */
public enum Payment {
    DEFAULT("Visa - 4242", "4242424242424242"),
    MASTERCARD("Mastercard - 4444", "5555555555554444"),
    AMEX("Amex - 0005", "378282246310005"),
    DISCOVER("Discover - 1117", "6011111111111117"),
    INVALID("INVALID PAYMENT", "4242424242420000"),
    EXPIRED("EXPIRED CARD", "4000000000000060");

    public final String paymentAppearanceName;
    public final String creditCardNumber;
    public static final String EXPIRED_DATE = "1234";
    public static final String EXPIRED_YEAR = "2034";
    public static final String EXPIRED_MONTH = "12";
    public static final String CVC = "000";
    public static final String ZIP_CODE = "49931";

    Payment(String paymentAppearanceName, String creditCardNumber) {
        this.paymentAppearanceName = paymentAppearanceName;
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "[" + creditCardNumber + "]";
    }
}
